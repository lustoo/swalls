/**
 * 初始化问题墙信息管理详情对话框
 */
var Wall1InfoDlg = {
    wall1InfoData : {}
};

/**
 * 清除数据
 */
Wall1InfoDlg.clearData = function() {
    this.wall1InfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Wall1InfoDlg.set = function(key, val) {
    this.wall1InfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
Wall1InfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
Wall1InfoDlg.close = function() {
    parent.layer.close(window.parent.Wall1.layerIndex);
}

/**
 * 收集数据
 */
Wall1InfoDlg.collectData = function() {
    this
    .set('id')
    .set('openId')
    .set('abstracts')
    .set('writerTime')
    .set('label')
    .set('writeContests')
    .set('parentObjectId')
    ;
}

/**
 * 提交添加
 */
Wall1InfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wall1/add", function(data){
        Feng.success("添加成功!");
        window.parent.Wall1.table.refresh();
        Wall1InfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wall1InfoData);
    ajax.start();
}

/**
 * 提交修改
 */
Wall1InfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wall1/update", function(data){
        Feng.success("修改成功!");
        window.parent.Wall1.table.refresh();
        Wall1InfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wall1InfoData);
    ajax.start();
}

$(function() {

});
