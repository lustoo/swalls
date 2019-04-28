/**
 * 微信用户信息管理管理初始化
 */
var Wxuser = {
    id: "WxuserTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Wxuser.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: 'openid', field: 'openId', visible: true, align: 'center', valign: 'middle'},
            {title: '用户名', field: 'userName', visible: true, align: 'center', valign: 'middle'},
            {title: '性别', field: 'gender', visible: true, align: 'center', valign: 'middle'},
            {title: '头像？', field: 'avatarUrl', visible: true, align: 'center', valign: 'middle'},
            {title: '姓名', field: 'stuName', visible: true, align: 'center', valign: 'middle'},
            {title: '学号', field: 'stuNum', visible: true, align: 'center', valign: 'middle'},
            {title: '国家', field: 'country', visible: true, align: 'center', valign: 'middle'},
            {title: '省', field: 'province', visible: true, align: 'center', valign: 'middle'},
            {title: '城市', field: 'city', visible: true, align: 'center', valign: 'middle'},
            {title: '会话密钥', field: 'sessionKey', visible: true, align: 'center', valign: 'middle'},
            {title: '开放平台标识符', field: 'unionid', visible: true, align: 'center', valign: 'middle'},
            {title: '用户限权', field: 'power', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Wxuser.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Wxuser.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加微信用户信息管理
 */
Wxuser.openAddWxuser = function () {
    var index = layer.open({
        type: 2,
        title: '添加微信用户信息管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/wxuser/wxuser_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看微信用户信息管理详情
 */
Wxuser.openWxuserDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '微信用户信息管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/wxuser/wxuser_update/' + Wxuser.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除微信用户信息管理
 */
Wxuser.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/wxuser/delete", function (data) {
            Feng.success("删除成功!");
            Wxuser.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("wxuserId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询微信用户信息管理列表
 */
Wxuser.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Wxuser.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Wxuser.initColumn();
    var table = new BSTable(Wxuser.id, "/wxuser/list", defaultColunms);
    table.setPaginationType("client");
    Wxuser.table = table.init();
});
