
$.get('/my/toDetail/' + localStorage.getItem("accountId"), function (res) {
    layer.open({
        type: 1,
        title: title,
        area: ['800px', '450px'],//宽高
        content: res
    });
});