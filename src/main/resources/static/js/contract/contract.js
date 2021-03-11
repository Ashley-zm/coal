layui.laydate.render({
    elem: '#createTimeRange',
    range: true,
    //2020-11-12 - 2020-11-20
});
var table = layui.table;
//执行渲染
var tableIns = table.render({
    elem: '#contractList',
    toolbar: true,
    toolbar: '#toolbar', //开启头部工具栏，并为其绑定左侧模板
    height: 'full-145',
    cellMinWidth: 80,
    url: '/contract/list',//数据接口
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
        {
            field: 'contractId', title: '合同序号', align: 'center', width: 90,
            templet: function (c) {
                return "<a class='contract_Id'>" + c.contractId + "</a>"
            }
        },
        {field: 'contractCode', title: '合同编号', align: 'center', width: 90},
        {field: 'customerName', title: '客户姓名', align: 'center', width: 90},
        {field: 'contractName', title: '合同名称', align: 'center', width: 180},
        {field: 'realName', title: '销售人', align: 'center', width: 80},
        {field: 'productName', title: '产品名称', align: 'center', width: 110},
        {
            field: 'amount', title: '总数量', sort: true, align: 'center', width: 100, templet: function (a) {
                return a.amount + '吨'
            }
        },
        {
            field: 'price', title: '单价', sort: true, align: 'center', width: 110, templet: function (a) {
                return a.price + '元/吨'
            }
        },
        {
            field: 'total', title: '总价', sort: true, align: 'center', width: 100, templet: function (a) {
                a.total = a.total / 10000;
                return a.total + '万元';
            }
        },
        {field: 'effectiveTime', title: '生效时间', align: 'center', width: 130},
        {field: 'expireTime', title: '到期时间', align: 'center', width: 130},
        {
            field: 'deleted',
            title: '合同状态', width: 240, align: 'center', fixed: 'right',
            templet: setState,

        },
        {
            field: 'factoryState',
            title: '是否出厂', width: 120, align: 'center', fixed: 'right', sort: true,
            toolbar: '#buttonTpl',
        },
        {title: '操作', width: 180, align: 'center', toolbar: '#barDemo', fixed: 'right'}
    ]]
});

/**
 * 获取当前时间，并将其格式化为YYYY-MM-DD
 * 判断合同状态时需要用到
 * @returns {string}
 */
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
};

/**
 * 设置合同状态
 * @param date
 * @returns {string}
 */
function setState(date) {
    var deleted = date.deleted;
    var effective = (date.effectiveTime).substring(0, 10);
    var expire = (date.expireTime).substring(0, 10);
    var now = getNowFormatDate();
    if (deleted == 0) {
        if (now < effective) {
            return "<input type='radio'name='' checked='true' title='效力待定' style='color: #597FC0 !important;'>&nbsp;" +
                "<input type='radio' name='' title='生效中'>&nbsp;" +
                "<input type='radio' name='' title='已无效'>";
        } else if (now > expire) {
            return "<input type='radio'name=''  title='效力待定'>&nbsp;" +
                "<input type='radio' name='' title='生效中'>&nbsp;" +
                "<input type='radio' name='' checked='true' title='已无效' style='background-color: crimson'>";
        } else {
            return "<input type='radio'name=''  title='效力待定'>&nbsp;" +
                "<input type='radio' name='' checked='true' title='生效中' style='background-color: green'>&nbsp;" +
                "<input type='radio' name=''  title='已无效'>";
        }

    } else {
        return "<span style='color: crimson'>已删除</span>"
    }

}

/**
 *监听头部工具栏事件
 */
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
    //禁用当前行，把当前对象的 class="layui-disabled"

    let contractId = data.contractId;
    var status = data.factoryState;
    if (layEvent === 'detail') { //查看
        console.log(contractId);
        openlayer('/contract/toDetail/' + contractId, '合同详情', '100%', '100%');
    } else if (layEvent === 'del') { //删除
        if (status == 1) {
            layer.msg('已经出厂，不能删除合同');
            return ;
        } else {
            layer.confirm('真的删除行么', function (index) {
                layer.close(index);
                //向服务端发送删除指令
                myDelete("/contract/" + contractId);
            });
        }
    } else if (layEvent === 'edit') { //编辑
        if (status == 1) {
            layer.msg('已经出厂，不能编辑合同');
            return;
        } else {
            openlayer('/contract/toUpdate/' + contractId, '编辑合同', '100%', '100%');
            layui.form.render();
            mySubmit('updateSubmit', 'PUT')
        }
    }else if (layEvent==='factory_out'){
        console.log(data);
        console.log(contractId);
        openlayer('/sale/toAdd/' + contractId, '出厂订单填写', '800px', '450px');
        //渲染radio
        layui.form.render();
        mySubmit('addSubmit', 'POST');
    }
});

/**
 * 查询方法
 * 表格重载
 * reload将再次访问servlet
 * http://localhost:8080/contract/list?page=1&limit=10
 * &contractCode=04000&contractName=&accountId=&createTimeRange=
 *
 */
function query() {
    tableIns.reload({
        where: { //设定异步数据接口的额外参数，任意设
            contractCode: $("#contractCode").val()
            , contractName: $("#contractName").val()
            , accountId: $("#accountId").val()
            , createTimeRange: $("#createTimeRange").val()
            , status: $('[name=status]:checked').val()
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
    openlayer('/contract/toAdd', '创建合同', '100%', '100%');
    //渲染radio
    layui.form.render();
    mySubmit('addSubmit', 'POST');
}
