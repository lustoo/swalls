/**
 * 社团活动公告管理初始化
 */
var College = {
    id: "CollegeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
College.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '标签', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '社团活动主题', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '活动发布时间', field: 'time', visible: true, align: 'center', valign: 'middle'},
            {title: '所属社团', field: 'college', visible: true, align: 'center', valign: 'middle'},
            {title: '活动具体内容', field: 'contents', visible: true, align: 'center', valign: 'middle'},
            {title: '图片', field: 'images', visible: true, align: 'center', valign: 'middle'},
            {title: '发布者唯一标识', field: 'openId', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
College.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        College.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加社团活动公告
 */
College.openAddCollege = function () {
    var index = layer.open({
        type: 2,
        title: '添加社团活动公告',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/college/college_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看社团活动公告详情
 */
College.openCollegeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '社团活动公告详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/college/college_update/' + College.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除社团活动公告
 */
College.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/college/delete", function (data) {
            Feng.success("删除成功!");
            College.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("collegeId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询社团活动公告列表
 */
College.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    College.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = College.initColumn();
    var table = new BSTable(College.id, "/college/list", defaultColunms);
    table.setPaginationType("client");
    College.table = table.init();
});
