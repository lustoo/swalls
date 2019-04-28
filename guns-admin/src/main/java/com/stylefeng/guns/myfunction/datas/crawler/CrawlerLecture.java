package com.stylefeng.guns.myfunction.datas.crawler;

import com.stylefeng.guns.myfunction.datas.crawler.basic.Crawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class CrawlerLecture extends Crawler {
    public CrawlerLecture(String url){
        this.setUrl(url);
    }
    public void preparing(){
        try {
            this.setDoc(Jsoup.connect(this.getUrl()).get());
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

                docdetails = Jsoup.connect("http://www.usst.edu.cn"+linkHref).ignoreContentType(true).timeout(100000000).get();

            title = docdetails.title();

            contents = docdetails.text();

            contents=contents.substring(contents.indexOf("就业信息服务网")+7,contents.indexOf("友情链接"));
            contents=contents.replace("周热点新闻 月热点新闻 快速链接 信息公开 领导信箱 人才招聘 资产设备 文明文化 干部在线 学工在线 研工在线 校友总会 捐赠母校 地方校友会 杰出校友 官方微信 官方微博 QQ智慧校园 我的易班","\n\n");
            contents=contents.replace("编辑：摄影： 通讯员：设置 A+ A- 夜晚模式","\n\n    ");
            contents=contents.replace( title,"      ");
            contents=contents.replace("邮编","\n 邮编");
            contents=contents.replace("电话","\n 电话");
            contents=contents.replace("版权所有","\n 版权所有");
            contents=contents.replace("沪公网安备","\n 沪公网安备");
            contents=contents.replace("技术支持","\n 技术支持");
            contents=contents.replace("讲座时间","\n\n讲座时间");
            contents=contents.replace("讲座地点","\n\n讲座地点");
            contents=contents.replace("讲座内容","\n\n讲座内容");
            contents=contents.replace("报告时间","\n\n报告时间");
            contents=contents.replace("报告地点","\n\n报告地点");
            contents=contents.replace("报告内容","\n\n报告内容");
            contents=contents.replace("报告人简介","\n\n报告人简介");
            contents=contents.replace("摘要","\n\n摘要");
            contents=contents.replace("主讲嘉宾介绍","\n主讲嘉宾介绍");





            time=contents.substring(contents.indexOf("时间：")+3,contents.indexOf("浏览："));

            this.setKey(1);
            return title;
        } catch (IOException e) {
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


}
