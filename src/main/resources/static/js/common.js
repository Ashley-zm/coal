/**
 * 公共弹出层
 * $.ajaxSettings.async=false;//先设置为同步，使openlayer方法执行完，之后再异步。
 * @param url
 * @param title
 */
function openlayer(url, title, wd, ht) {
    $.ajaxSettings.async = false;
    $.get(url, function (res) {
        layer.open({
            type: 1,
            title: title,
            area: [wd, ht],//宽高
            content: res
        });
    });
    $.ajaxSettings.async = true;
}

/**
 * 监听提交事件
 * @param filter
 * @param type
 */
function mySubmit(filter, type, func) {
    layui.form.on('submit(' + filter + ')', function (data) {
        if (typeof (func) != 'undefined') {
            func(data.field);
        }
        console.log(data.elem);
        console.log(data.form);
        console.log(data.field);
        $.ajax({
            url: data.form.action,
            async: false,
            type: type,
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify(data.field),
            success: function (res) {
                if (res.code == 0) {
                    layer.closeAll();
                    query();
                } else {
                    layer.alert(res.msg);
                }

            }
        });
        return false;//false：阻止表单跳转  true：表单跳转
    });
}

/**
 * 公共删除方法
 * @param url
 */
function myDelete(url) {
    $.ajax({
        url: url,
        async: false,
        type: 'DELETE',
        success: function (res) {
            if (res.code == 0) {
                query();
            } else {
                layer.alert(res.msg);
            }

        }
    });
}

var addIds = function (field) {
    let checkedData = layui.tree.getChecked('resource');
    field.resourceIds = getIds(checkedData, [])
}

function getIds(checkedData, arr) {
    for (let i in checkedData) {
        arr.push(checkedData[i].id);
        arr = getIds(checkedData[i].children, arr)
    }
    return arr;
}

/**
 * 校验表单填入
 */
layui.use(["jquery", 'form', 'layer'],
    function () {
        var $ = layui.jquery,
            layer = layui.layer,
            form = layui.form;
        //自定义验证规则
        form.verify({
            nikename: function (value) {
                if (value.length < 5) {
                    return '昵称至少得5个字符啊';
                }
            },
            number: function (e) {
                if (e < 0 || !e || isNaN(e)) {
                    return '只能填写数字 或 数值必须大于零';
                }
            },
            passw: [/(.+){6,12}$/, '密码必须6到12位'],
            repass: function (value) {
                if ($('#L_pass').val() != $('#L_repass').val()) {
                    return '两次密码不一致';
                }
            },
            required: [/[\S]+/, "必填项不能为空"],
            phone: [/^1\d{10}$/, "请输入正确的手机号"],
            email: [/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/, "邮箱格式不正确"],
            url: [/(^#)|(^http(s*):\/\/[^\s]+\.[^\s]+)/, "链接格式不正确"],
            number: function (e) {
                if (!e || isNaN(e)) return "只能填写数字"
            },
            date: [/^(\d{4})[-\/](\d{1}|0\d{1}|1[0-2])([-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/, "日期格式不正确"],
            identity: [/(^\d{15}$)|(^\d{17}(x|X|\d)$)/, "请输入正确的身份证号"]
        });
    }
);