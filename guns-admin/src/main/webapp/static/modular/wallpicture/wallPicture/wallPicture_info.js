/**
 * 初始化问题墙图片管理详情对话框
 */
var WallPictureInfoDlg = {
    wallPictureInfoData : {}
};

/**
 * 清除数据
 */
WallPictureInfoDlg.clearData = function() {
    this.wallPictureInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WallPictureInfoDlg.set = function(key, val) {
    this.wallPictureInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WallPictureInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
WallPictureInfoDlg.close = function() {
    parent.layer.close(window.parent.WallPicture.layerIndex);
}

/**
 * 收集数据
 */
WallPictureInfoDlg.collectData = function() {
    this
    .set('id')
    .set('fileName')
    .set('filePath')
    .set('parentObjectId')
    ;
}

/**
 * 提交添加
 */
WallPictureInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wallPicture/add", function(data){
        Feng.success("添加成功!");
        window.parent.WallPicture.table.refresh();
        WallPictureInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wallPictureInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
WallPictureInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wallPicture/update", function(data){
        Feng.success("修改成功!");
        window.parent.WallPicture.table.refresh();
        WallPictureInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wallPictureInfoData);
    ajax.start();
}

$(function() {

});
