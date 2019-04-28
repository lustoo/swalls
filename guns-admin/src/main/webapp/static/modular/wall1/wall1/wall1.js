/**
 * 问题墙信息管理管理初始化
 */
var Wall1 = {
    id: "Wall1Table",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Wall1.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '对象id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '微信用户id', field: 'openId', visible: true, align: 'center', valign: 'middle'},
            {title: '摘要', field: 'abstracts', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'writerTime', visible: true, align: 'center', valign: 'middle'},
            {title: '标签', field: 'label', visible: true, align: 'center', valign: 'middle'},
            {title: '内容', field: 'writeContests', visible: true, align: 'center', valign: 'middle'},
            {title: '父对象id', field: 'parentObjectId', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Wall1.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Wall1.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加问题墙信息管理
 */
Wall1.openAddWall1 = function () {
    var index = layer.open({
        type: 2,
        title: '添加问题墙信息管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/wall1/wall1_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看问题墙信息管理详情
 */
Wall1.openWall1Detail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '问题墙信息管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/wall1/wall1_update/' + Wall1.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除问题墙信息管理
 */
Wall1.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/wall1/delete", function (data) {
            Feng.success("删除成功!");
            Wall1.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("wall1Id",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询问题墙信息管理列表
 */
Wall1.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Wall1.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Wall1.initColumn();
    var table = new BSTable(Wall1.id, "/wall1/list", defaultColunms);
    table.setPaginationType("client");
    Wall1.table = table.init();
});
