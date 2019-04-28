/**
 * 初始化问题墙收藏管理详情对话框
 */
var CollectionsInfoDlg = {
    collectionsInfoData : {}
};

/**
 * 清除数据
 */
CollectionsInfoDlg.clearData = function() {
    this.collectionsInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CollectionsInfoDlg.set = function(key, val) {
    this.collectionsInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CollectionsInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CollectionsInfoDlg.close = function() {
    parent.layer.close(window.parent.Collections.layerIndex);
}

/**
 * 收集数据
 */
CollectionsInfoDlg.collectData = function() {
    this
    .set('id')
    .set('openId')
    ;
}

/**
 * 提交添加
 */
CollectionsInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/collections/add", function(data){
        Feng.success("添加成功!");
        window.parent.Collections.table.refresh();
        CollectionsInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.collectionsInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CollectionsInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/collections/update", function(data){
        Feng.success("修改成功!");
        window.parent.Collections.table.refresh();
        CollectionsInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.collectionsInfoData);
    ajax.start();
}

$(function() {

});
