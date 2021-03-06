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
            len: function (value) {
                if (value < 0) {
                    return '数值必须大于零';
                }
            },
            passw: [/(.+){6,12}$/, '密码必须6到12位'],
            repass: function (value) {
                if ($('#L_pass').val() != $('#L_repass').val()) {
                    return '两次密码不一致';
                }
            },
            otherReq: function (value, item) {
                var $ = layui.$;
                var verifyName = $(item).attr('name')
                    , verifyType = $(item).attr('type')
                    , formElem = $(item).parents('.layui-form')//获取当前所在的form元素，如果存在的话
                    , verifyElem = formElem.find('input[name=' + verifyName + ']')//获取需要校验的元素
                    , isTrue = verifyElem.is(':checked')//是否命中校验
                    , focusElem = verifyElem.next().find('i.layui-icon'); //焦点元素
                if (!isTrue || !value) {
                    //定位焦点
                    focusElem.css(verifyType == 'radio' ? {"color": "#FF5722"} : {"border-color": "#FF5722"});
                    //对非输入框设置焦点
                    focusElem.first().attr("tabIndex", "1").css("outline", "0").blur(function () {
                        focusElem.css(verifyType == 'radio' ? {"color": ""} : {"border-color": ""});
                    }).focus();
                    return '必填项不能为空';
                }
            }
        });
    }
);