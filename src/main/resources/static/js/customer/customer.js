var table = layui.table;
//执行渲染
var tableIns = table.render({
    elem: '#customerList',
    toolbar: true,
    toolbar: '#toolbar', //开启头部工具栏，并为其绑定左侧模板
    height: 'full-100',
    cellMinWidth: 80,
    url: '/customer/list',//数据接口
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
        {type: 'checkbox', fixed: 'left'},
        {field: 'companyName', title: '公司名称', width: 180, align: 'center'},
        {field: 'customerName', title: '真实姓名', width: 100, align: 'center'},
        {field: 'sex', title: '性别', width: 80, sort: true, align: 'center'},
        {field: 'age', title: '年龄', width: 80, sort: true, align: 'center'},
        {
            field: 'email', title: '邮箱', align: 'center', width: 180, templet: function (res) {
                return '<em>' + res.email + '</em>'
            }
        },
        {field: 'phone', title: '手机号码', align: 'center', width: 120},
        {field: 'address', title: '地址', align: 'center', width: 170},
        {field: 'createTime', title: '创建时间', align: 'center', width: 130},
        {field: 'modifiedTime', title: '修改时间', align: 'center', width: 130},
        {title: '操作', width: 165, align: 'center', toolbar: '#barDemo', fixed: 'right'}
    ]]
});

// 监听头部工具栏事件
table.on('toolbar(userTable)', function (obj) {
    switch (obj.event) {
        case 'toAdd':
            toAdd();
            break;
        case 'reload':
            location.reload();
            break;
    }
});
//监听行工具条
table.on('tool(userTable)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

    let customerId = data.customerId;
    if (layEvent === 'detail') { //查看
        openlayer('/customer/toDetail/' + customerId, '客户詳情', '800px', '450px');
        console.log("查看");
    } else if (layEvent === 'del') { //删除
        layer.confirm('真的删除行么', function (index) {
            layer.close(index);
            //向服务端发送删除指令
            myDelete("/customer/" + customerId);
        });
    } else if (layEvent === 'edit') { //编辑
        // console.log(customerId);
        openlayer('/customer/toUpdate/' + customerId, '修改客户', '800px', '450px');
        layui.form.render();
        mySubmit('updateSubmit', 'PUT')
    }
});

/**
 * 按条件查询
 */
function query() {
    tableIns.reload({
        where: { //设定异步数据接口的额外参数，任意设
            realName: $("#customerName").val()
            , phone: $("#phone").val()
        }
        , page: {
            curr: 1 //重新从第 1 页开始
        }
    });
}

/**
 * 进入新增页
 */
function toAdd() {
    openlayer('/customer/toAdd', '新增客户', '800px', '450px');
    //渲染radio
    layui.form.render();
    mySubmit('addSubmit', 'POST');
}


