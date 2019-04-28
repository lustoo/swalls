/**
 * 文件共享信息管理初始化
 */
var Share = {
    id: "ShareTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Share.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '标签', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '文件标题', field: 'problemName', visible: true, align: 'center', valign: 'middle'},
            {title: '文件上传时间', field: 'problemTime', visible: true, align: 'center', valign: 'middle'},
            {title: '文件名', field: 'fileName', visible: true, align: 'center', valign: 'middle'},
            {title: '文件路径', field: 'filePath', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Share.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Share.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加文件共享信息
 */
Share.openAddShare = function () {
    var index = layer.open({
        type: 2,
        title: '添加文件共享信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/share/share_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看文件共享信息详情
 */
Share.openShareDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '文件共享信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/share/share_update/' + Share.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除文件共享信息
 */
Share.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/share/delete", function (data) {
            Feng.success("删除成功!");
            Share.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("shareId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询文件共享信息列表
 */
Share.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Share.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Share.initColumn();
    var table = new BSTable(Share.id, "/share/list", defaultColunms);
    table.setPaginationType("client");
    Share.table = table.init();
});
