/**
 * 初始化教务处公告管理详情对话框
 */
var EduInfoDlg = {
    eduInfoData : {}
};

/**
 * 清除数据
 */
EduInfoDlg.clearData = function() {
    this.eduInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
EduInfoDlg.set = function(key, val) {
    this.eduInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
EduInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
EduInfoDlg.close = function() {
    parent.layer.close(window.parent.Edu.layerIndex);
}

/**
 * 收集数据
 */
EduInfoDlg.collectData = function() {
    this
    .set('id')
    .set('eaTitle')
    .set('eaTime')
    .set('eaCont')
    ;
}

/**
 * 提交添加
 */
EduInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/edu/add", function(data){
        Feng.success("添加成功!");
        window.parent.Edu.table.refresh();
        EduInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.eduInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
EduInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/edu/update", function(data){
        Feng.success("修改成功!");
        window.parent.Edu.table.refresh();
        EduInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.eduInfoData);
    ajax.start();
}

$(function() {

});
