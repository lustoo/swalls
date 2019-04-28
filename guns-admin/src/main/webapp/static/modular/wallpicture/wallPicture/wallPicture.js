/**
 * 问题墙图片管理管理初始化
 */
var WallPicture = {
    id: "WallPictureTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
WallPicture.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '文件名', field: 'fileName', visible: true, align: 'center', valign: 'middle'},
            {title: '文件路径', field: 'filePath', visible: true, align: 'center', valign: 'middle'},
            {title: '所属问题id', field: 'parentObjectId', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
WallPicture.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        WallPicture.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加问题墙图片管理
 */
WallPicture.openAddWallPicture = function () {
    var index = layer.open({
        type: 2,
        title: '添加问题墙图片管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/wallPicture/wallPicture_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看问题墙图片管理详情
 */
WallPicture.openWallPictureDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '问题墙图片管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/wallPicture/wallPicture_update/' + WallPicture.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除问题墙图片管理
 */
WallPicture.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/wallPicture/delete", function (data) {
            Feng.success("删除成功!");
            WallPicture.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("wallPictureId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询问题墙图片管理列表
 */
WallPicture.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    WallPicture.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = WallPicture.initColumn();
    var table = new BSTable(WallPicture.id, "/wallPicture/list", defaultColunms);
    table.setPaginationType("client");
    WallPicture.table = table.init();
});
