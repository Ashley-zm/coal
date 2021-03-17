var table = layui.table;
//执行渲染
var tableIns = table.render({
    elem: '#saleList',
    toolbar: true,
    toolbar: '#toolbar', //开启头部工具栏，并为其绑定左侧模板
    height: 'full-110',
    cellMinWidth: 80,
    url: '/sale/list',//数据接口
    page: true,//开启分页
    limits: [8, 10, 15, 20],
    limit: 8,//每页默认显示的数量
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
        {field: 'contractName', title: '合同名称', align: 'center', width: 160},
        {field: 'productName', title: '产品名称', align: 'center', width: 110},
        {
            field: 'amount', title: '总数量', sort: true, align: 'center', width: 90, templet: function (a) {
                return a.amount + '吨'
            }
        },
        {
            field: 'price', title: '单价', sort: true, align: 'center', width: 90, templet: function (a) {
                return a.price + '元/吨'
            }
        },
        {
            field: 'total', title: '总价', sort: true, align: 'center', width: 100, templet: function (a) {
                a.total = a.total / 10000;
                return a.total + '万元';
            }
        },
        {field: 'carCode', title: '车牌号', align: 'center', width: 140},
        {field: 'driverName', title: '司机姓名', align: 'center', width: 120},
        {field: 'driverPhone', title: '手机号码', align: 'center', width: 120},
        {
            field: 'carWeigth', title: '车皮重量', align: 'center', width: 100, templet: function (a) {
                return a.carWeigth + '元/吨'
            }
        },
        {
            field: 'taxes', title: '纳税', align: 'center', width: 110, templet: function (a) {
                return a.taxes + '元'
            }
        },
        {
            field: 'profit', title: '利润', align: 'center', width: 110, templet: function (a) {
                return a.profit + '元'
            }
        },
        {field: 'totalWeigtht', title: '总重量', align: 'center', width: 110, templet: function (a) {
                return a.totalWeigtht + '吨'
            }},
        {field: 'leaveTime', title: '出厂时间', align: 'center', width: 130},
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

    let saleId = data.saleId;
    if (layEvent === 'detail') { //查看
        openlayer('/sale/toDetail/' + saleId, '出厂订单详情', '700px', '470px');
        console.log("查看");
    } else if (layEvent === 'del') { //删除
        layer.confirm('真的删除行么', function (index) {
            layer.close(index);
            //向服务端发送删除指令
            myDelete("/sale/" + saleId);
        });
    }
});

/**
 * 按条件查询
 */
function query() {
    tableIns.reload({
        where: { //设定异步数据接口的额外参数，任意设
            contractName: $("#contractName").val()
            , productName: $("#productName").val()
            , driverName: $("#driverName").val()
        }
        , page: {
            curr: 1 //重新从第 1 页开始
        }
    });
}



