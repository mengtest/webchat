$(function () {
    $("#register").click(function (e) {
        e.preventDefault();
        var $username = $("#username").val();
        var $password = $("#password").val();
        $.ajax({
            type: 'post',
            url: '/user/register/',
            data: JSON.stringify({'username': $username, 'password': $password, 'nickname': $username}),
            dataType: 'json',
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                if (data['errCode'] == 0) {
                    window.location.href = "/app/login.html";
                } else {
                    alert(data['errMsg']);
                    return false;
                }
            }
        });
    });

    $("#login").click(function (e) {
        e.preventDefault();
        var $username = $("#username").val();
        var $password = $("#password").val();
        $.ajax({
            type: 'get',
            url: '/user/login/',
            data: {'username': $username, 'password': $password},
            dataType: 'json',
            success: function (data) {
                if (data['errCode'] == 0) {
                    alert("进入聊天界面!");
                    //window.location.href = "/app/login.html";
                } else {
                    alert(data['errMsg']);
                    return false;
                }
            }
        });
    });
});