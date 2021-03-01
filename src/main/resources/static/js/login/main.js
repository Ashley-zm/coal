/**
 * 打开选项卡，进入相应的模块主页
 * @param url
 * @param name
 * @param id
 */
function showTab(url, name, id) {
    let length = $("li[lay-id=" + id + "]").length;
    let element = layui.element;
    if (length == 0) {
        let fullUrl = "/" + url;
        let height = $(window).height() - 185;
        let content = '<iframe style="width: 100%;height:' + height + 'px" src="' + fullUrl + '"frameborder="0"scrolling="no">';
        element.tabAdd('menu', {
            title: name,
            content: content,
            id: id
        });
    }
    element.tabChange("menu", id);
}
$(function () {
    console.log($("#myId").val());
    localStorage.setItem("accountId", $("#myId").val());
})
function update(){
    showTab('/my/toDetail/' + $("#myId").val(),'账号详情',$("#myId").val());
    $.ajaxSettings.async = false;
    $.get('/my/toDetail/', function (res) {
        layer.open({
            type: 1,
            title: 账号详情,
            area: ['800px', '450px'],//宽高
            content: res
        });
    });
    $.ajaxSettings.async = true;
}