/**
 * 初始化讲座信息详情对话框
 */
var LectureInfoDlg = {
    lectureInfoData : {}
};

/**
 * 清除数据
 */
LectureInfoDlg.clearData = function() {
    this.lectureInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LectureInfoDlg.set = function(key, val) {
    this.lectureInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LectureInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
LectureInfoDlg.close = function() {
    parent.layer.close(window.parent.Lecture.layerIndex);
}

/**
 * 收集数据
 */
LectureInfoDlg.collectData = function() {
    this
    .set('id')
    .set('leTitle')
    .set('leTime')
    .set('leCont')
    ;
}

/**
 * 提交添加
 */
LectureInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/lecture/add", function(data){
        Feng.success("添加成功!");
        window.parent.Lecture.table.refresh();
        LectureInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.lectureInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
LectureInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/lecture/update", function(data){
        Feng.success("修改成功!");
        window.parent.Lecture.table.refresh();
        LectureInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.lectureInfoData);
    ajax.start();
}

$(function() {

});
