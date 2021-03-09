var table = layui.table;
//执行渲染
var tableIns = table.render({
    elem: '#productList',
    toolbar: true,
    toolbar: '#toolbar', //开启头部工具栏，并为其绑定左侧模板
    height: 'full-120',
    cellMinWidth: 80,
    url: '/product/list',//数据接口
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
        {field: 'productName', title: '产品名称', width: '20%', align: 'center'},
        {field: 'ptotal', title: '产品总数量(吨)', width: '20%', align: 'center'},
        {field: 'price', title: '产品单价(元/吨)', width: '20%', align: 'center'},
        {
            field: 'pStatus', title: '库存状态', width: '20%', align: 'center',
            templet: function (p) {
                if (p.ptotal > '1000') {
                    return "<span style='color: #0075ff'>库存充足</span>";
                } else {
                    return "<span style='color: crimson'>库存紧缺!</span>";
                }
            }
        },
        {title: '操作', width: '20%', align: 'center', toolbar: '#barDemo', fixed: 'right'}
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
        openlayer('/product/toDetail/' + customerId, '客户詳情', '800px', '450px');
        console.log("查看");
    } else if (layEvent === 'del') { //删除
        layer.confirm('真的删除行么', function (index) {
            layer.close(index);
            //向服务端发送删除指令
            myDelete("/product/" + customerId);
        });
    } else if (layEvent === 'edit') { //编辑
        // console.log(customerId);
        openlayer('/product/toUpdate/' + customerId, '修改客户', '800px', '450px');
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
            realName: $("#productName").val()
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
    openlayer('/product/toAdd', '进厂', '800px', '450px');
    //渲染radio
    layui.form.render();
    mySubmit('addSubmit', 'POST');
}


