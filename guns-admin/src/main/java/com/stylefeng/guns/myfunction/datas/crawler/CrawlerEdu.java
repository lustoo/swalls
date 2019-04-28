package com.stylefeng.guns.myfunction.datas.crawler;

import com.stylefeng.guns.myfunction.datas.crawler.basic.Crawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 **Edu Crawler模块**
 *
 *
 **/
public class CrawlerEdu extends Crawler {

    public CrawlerEdu(String url){
        this.setUrl(url);
    }

    public void preparing(){
        try {
            this.setDoc(Jsoup.connect(this.getUrl())
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0")
                    .header("Connection", "close")//如果是这种方式，这里务必带上
                    .timeout(800000).get());//超时时间
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setLinks(((Element) this.getDoc()).select("a[href]"));

    }

    private String title;

    private String time;

    private String contents;

    //标题
    public String title(Element link){
        this.setKey(0);
        String linkHref = link.attr("href");
        try {
            Document docdetails;
            if(linkHref.charAt(0)!='h'&&(linkHref.charAt(0)!='l'&&linkHref.charAt(1)!='x'&&linkHref.charAt(2)!='y')){
                docdetails = Jsoup.connect(this.getUrl()+linkHref).ignoreContentType(true).get();
            }
            else{
                docdetails = Jsoup.connect(linkHref).ignoreContentType(true).get();
            }
            title = docdetails.title();

            contents = docdetails.text();

            String kong=" ";

            contents=contents.replace(". ","temp");

            contents=contents.replace(" ","\n");

            StringBuilder content=new StringBuilder(contents);
            int l=content.length();
            for(int i=0;i<l-5;++i)
            {
                if(content.charAt(i)=='\n')
                {
                    for(int j=1;j<=5;++j)
                    {
                        if(content.charAt(i+j)=='\n')
                            content.replace(i,i+1,kong);
                    }
                }
            }
            /*String twoline="\n";
            for(int i=0;i<l-10;++i)
            {
                if(content.charAt(i)=='\n')
                {
                    int j=1;
                    for( j=1;j<=10;++j)
                    {
                        if(content.charAt(i+j)=='\n')
                            break;
                    }
                    if(j==10)
                    content.insert(i,twoline);
                }
            }
            */
            contents=String.valueOf(content);

            contents=contents.replace("temp",". ");

            String []text1 = contents.split("发布时间");

            String []text2 = text1[1].split("浏览次数");

            text2[0] = text2[0].substring(1, text2[0].length());

            text2[0] = text2[0].trim();

            time = text2[0];

            text2[1] = text2[1].substring(1, text2[1].length());

            Pattern pattern = Pattern.compile("[0-9]\\d*");

            Matcher matcher = pattern.matcher(text2[1]);

            text2[1] = matcher.replaceFirst("").trim();

            contents = text2[1];

            this.setKey(1);

            return title;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //时间
    public String time(){
        if(this.getKey() == 1)
            return time;
        else
            return null;
    }

    //内容
    public String contents(){
        if(this.getKey() == 1)
            return contents;
        else
            return null;
    }

/*
    public static void main( String[] args ) throws IOException {


        CrawlerEdu edu;

        edu = new CrawlerEdu("http://jwc.usst.edu.cn/");

        edu.preparing();

        int k = 0;
        for (Element link : edu.getLinks()) {
            if (k>=19&&k<=33){
                System.out.println("标题: " + edu.title(link));
                System.out.println("时间: " + edu.time());
                System.out.println("内容："+edu.contents());
            }
            k++;
        }
        // Document doc = Jsoup.connect("http://jwc.usst.edu.cn/").get();
        // //String title = doc.title();
        // //System.out.println("title is: " + title);
        // Elements links = ((Element) doc).select("a[href]");
        //int k=0;
        //for (Element link : links) {
        //    String linkHref = link.attr("href");
        //    //String linkText = link.text();
        //    if(k>=19&&k<=33)
        //    {
        //        //System.out.println("linktest:"+linkText+"  herf:"+linkHref+" baseuri  "+doc.baseUri());
        //        String href=linkHref;
        //        String detailsweb;
        //        if(href.charAt(0)!='h')
        //            detailsweb="http://jwc.usst.edu.cn"+href;
        //        else
        //            detailsweb=href;
        //        //System.out.println(detailsweb);//输出网址
        //        Document docdetails = Jsoup.connect(detailsweb).get();
        //        String titledetails = docdetails.title();
        //        String detailstext = docdetails.text();
        //
        //        String []time1 = detailstext.split("发布时间:");
        //        String []time2 = time1[1].split("浏览次数:");
        //
        //        System.out.println("标题: " + titledetails);
        //        System.out.println("时间: " + time2[0]);
        //        System.out.println("内容："+time2[1]);
        //        //System.out.println("内容："+detailstext);
        //    }
        //    k++;
        //    //获取详细页面
        //
        //}
        //
    }*/
}
