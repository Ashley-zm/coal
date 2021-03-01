var table = layui.table;
//执行渲染
var tableIns = table.render({
    elem: '#roleList',
    toolbar: true,
    toolbar: '#toolbar', //开启头部工具栏，并为其绑定左侧模板
    height: 'full-100',
    cellMinWidth: 80,
    url: '/role/list',//数据接口
    page: true,//开启分页
    parseData: function (res) { //res 即为原始返回的数据
        return {
            "code": res.code, //解析接口状态
            "msg": res.msg, //解析提示文本
            "count": res.data.count, //解析数据长度
            "data": res.data.records //解析数据列表
        };
    },
    cols: [[//表头
        {field: 'roleName', title: '角色名称', width: 100},
        {field: 'createTime', title: '创建时间'},
        {field: 'modifiedTime', title: '修改时间'},
        {title: '操作', width: 165, align: 'center', toolbar: '#barDemo'}
    ]]
});

// 监听头部工具栏事件
table.on('toolbar(userTable)', function (obj) {
    switch (obj.event) {
        case 'intoAdd':
            intoAdd();
            break;
        case 'reload':
            location.reload();
            break;
    }
});
//监听工具条
table.on('tool(userTable)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）
    console.log(tr);

    let roleId = data.roleId;
    if (layEvent === 'detail') { //查看
        console.log(roleId);
        openlayer('/role/toDetail/' + roleId, '角色详情');
        showTree('/role/listResource/' + roleId+'/1', 'resource', false);

    } else if (layEvent === 'del') { //删除
        layer.confirm(function (index) {
            // layer.close(index);
            //向服务端发送删除指令
            myDelete("/role/" + roleId);
        });
    } else if (layEvent === 'edit') { //编辑
        console.log(roleId);
        openlayer('/role/toUpdate/' + roleId, '编辑角色');
        layui.form.render();
        showTree('/role/listResource/' + roleId+'/0', 'resource', true);
        mySubmit('updateSubmit', 'PUT', addIds);
    }
});

/**
 * 按条件查询
 */
function query() {
    tableIns.reload({
        where: { //设定异步数据接口的额外参数，任意设
            roleName: $("#roleName").val()
            // , createTimeRange: $("#createTimeRange").val()
        }
        , page: {
            curr: 1 //重新从第 1 页开始
        }
    });
};

/**
 * 进入新增页
 */
function intoAdd() {
    openlayer('/role/toAdd', '新增角色');
    layui.form.render();
    showTree('/role/listResource', 'resource', true);
    mySubmit('addSubmit', 'POST', addIds);
};

/**
 * 通用的资源树方法
 * @param url
 * @param id
 * @param showCheckbox
 */
function showTree(url, id, showCheckbox) {
    if (typeof (showCheckbox) == 'undefind') {
        showCheckbox = true;
    }

    $.ajax({
        url: url,
        async: false,
        type: "GET",
        success: function (res) {
            if (res.code == 0) {
                layui.tree.render({
                    elem: '#' + id,
                    data: res.data,
                    id: id,
                    showCheckbox: showCheckbox
                });
            }
        }
    })
};