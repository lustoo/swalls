/**
 * 教务处公告管理管理初始化
 */
var Edu = {
    id: "EduTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Edu.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '标签', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '教务处公告标题', field: 'eaTitle', visible: true, align: 'center', valign: 'middle'},
            {title: '教务处公告时间', field: 'eaTime', visible: true, align: 'center', valign: 'middle'},
            {title: '教务处公告内容', field: 'eaCont', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Edu.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Edu.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加教务处公告管理
 */
Edu.openAddEdu = function () {
    var index = layer.open({
        type: 2,
        title: '添加教务处公告管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/edu/edu_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看教务处公告管理详情
 */
Edu.openEduDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '教务处公告管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/edu/edu_update/' + Edu.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除教务处公告管理
 */
Edu.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/edu/delete", function (data) {
            Feng.success("删除成功!");
            Edu.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("eduId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询教务处公告管理列表
 */
Edu.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Edu.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Edu.initColumn();
    var table = new BSTable(Edu.id, "/edu/list", defaultColunms);
    table.setPaginationType("client");
    Edu.table = table.init();
});
