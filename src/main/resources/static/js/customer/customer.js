var table = layui.table;
//
table.render({
    elem: '#customerList',
    url: '/customer/list',//数据接口
    page: true,//开启分页
    parseData: function (res) { //res 即为原始返回的数据
        return {
            "code": res.status, //解析接口状态
            "msg": res.msg, //解析提示文本
            "count": res.data.count, //解析数据长度
            "data": res.data.records //解析数据列表
        };
    },
    cols: [[//表头
        {field: 'realName', title: '姓名'},
        {field: 'sex', title: '性别'},
        {field: 'age', title: '年龄'},
        {field: 'phone', title: '手机号码'},
        {field: 'createTime', title: '创建时间'},
        {title: '操作', toolbar: '#barDemo'}
    ]]
})