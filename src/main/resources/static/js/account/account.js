layui.laydate.render({
    elem: '#createTimeRange',
    range: true,
    //2020-11-12 - 2020-11-20
});
var table = layui.table;
//执行渲染
var tableIns = table.render({
    elem: '#accountList',
    toolbar: true,
    toolbar: '#toolbar', //开启头部工具栏，并为其绑定左侧模板
    height:  'full-110',
    url: '/account/list',//数据接口
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
        {field: 'username', title: '用户名', width: 100},
        {field: 'realName', title: '真实姓名', width: 100},
        {field: 'roleName', title: '角色名称', width: 100},
        {field: 'sex', title: '性别', width: 80, sort: true},
        {
            field: 'email', title: '邮箱', width: 180, templet: function (res) {
                return '<em>' + res.email + '</em>'
            }
        },
        {field: 'createTime', title: '创建时间'},
        {field: 'modifiedTime', title: '修改时间'},
        {field: '操作', width: 165, align: 'center', toolbar: '#barDemo'}
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
//监听工具条
table.on('tool(userTable)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
    var data = obj.data; //获得当前行数据
    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
    var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

    let accountId = data.accountId;
    if (layEvent === 'detail') { //查看
        console.log(accountId);
        openlayer('/account/toDetail/' + accountId, '账号详情','800px', '450px');
    } else if (layEvent === 'del') { //删除
        layer.confirm('真的删除行么', function (index) {
            layer.close(index);
            //向服务端发送删除指令
            myDelete("/account/" + accountId);
        });
    } else if (layEvent === 'edit') { //编辑
        // console.log(customerId);
        openlayer('/account/toUpdate/' + accountId, '编辑账号','800px', '450px');
        layui.form.render();
        mySubmit('updateSubmit', 'PUT')
    }
});
/**
 * 查询方法
 */
function query() {
    tableIns.reload({
        where: { //设定异步数据接口的额外参数，任意设
            realName: $("#realName").val()
            , email: $("#email").val(),
            createTimeRange: $("#createTimeRange").val()
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
    openlayer('/account/toAdd', '新增账号','800px', '450px');
    //渲染radio
    layui.form.render();
    mySubmit('addSubmit', 'POST');
}

layui.form.verify({
    //accountAdd.html中输入用户名lay-verify="required|checkUsername"
    checkUsername: function (value, item) {//value：代表表单值，item：表单的dom对象
        let error = null;
        let url = '/account/' + value;
        //在更新时，与其他账号比较，忽略本身的id用户名
        let accountId = $("input[name='accountId']").val();
        if (typeof (accountId) !== 'undefined') {
            url += '/' + accountId;
        }

        $.ajax({
            url: url,
            async: false,
            type: 'GET',
            success: function (res) {
                if (res.code == 0) {
                    if (res.data > 0) {
                        error = "用户名已经存在";
                    }
                } else {
                    error = "用户名检测出错";
                }
            }, error: function () {
                error = "用户名检测出错";
            }
        });
        if (error != null) {
            return error;
        }
    }
});
