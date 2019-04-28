/**
 * 问题墙收藏管理管理初始化
 */
var Collections = {
    id: "CollectionsTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Collections.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '微信用户唯一标识', field: 'openId', visible: true, align: 'center', valign: 'middle'},
            {title: '收藏问题id', field: 'collectId', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Collections.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Collections.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加问题墙收藏管理
 */
Collections.openAddCollections = function () {
    var index = layer.open({
        type: 2,
        title: '添加问题墙收藏管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/collections/collections_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看问题墙收藏管理详情
 */
Collections.openCollectionsDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '问题墙收藏管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/collections/collections_update/' + Collections.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除问题墙收藏管理
 */
Collections.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/collections/delete", function (data) {
            Feng.success("删除成功!");
            Collections.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("collectionsId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询问题墙收藏管理列表
 */
Collections.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Collections.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Collections.initColumn();
    var table = new BSTable(Collections.id, "/collections/list", defaultColunms);
    table.setPaginationType("client");
    Collections.table = table.init();
});
