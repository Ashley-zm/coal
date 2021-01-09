
/**
 * 公共弹出层
 * $.ajaxSettings.async=false;//先设置为同步，使openlayer方法执行完，之后再异步。
 * @param url
 * @param title
 */
function openlayer(url, title) {
    $.ajaxSettings.async = false;
    $.get(url, function (res) {
        layer.open({
            type: 1,
            title: title,
            area: ['800px', '450px'],//宽高
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
function mySubmit(filter, type) {
    layui.form.on('submit(' + filter + ')', function (data) {
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
function myDelete(url){
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