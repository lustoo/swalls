/**
 * 讲座信息管理初始化
 */
var Lecture = {
    id: "LectureTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Lecture.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '标题', field: 'leTitle', visible: true, align: 'center', valign: 'middle'},
            {title: '时间', field: 'leTime', visible: true, align: 'center', valign: 'middle'},
            {title: '内容', field: 'leCont', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Lecture.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Lecture.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加讲座信息
 */
Lecture.openAddLecture = function () {
    var index = layer.open({
        type: 2,
        title: '添加讲座信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/lecture/lecture_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看讲座信息详情
 */
Lecture.openLectureDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '讲座信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/lecture/lecture_update/' + Lecture.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除讲座信息
 */
Lecture.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/lecture/delete", function (data) {
            Feng.success("删除成功!");
            Lecture.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("lectureId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询讲座信息列表
 */
Lecture.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Lecture.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Lecture.initColumn();
    var table = new BSTable(Lecture.id, "/lecture/list", defaultColunms);
    table.setPaginationType("client");
    Lecture.table = table.init();
});
