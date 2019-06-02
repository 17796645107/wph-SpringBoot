$(document).ready(function () {

    $("#login_submit").click(function () {
        $.ajax({
            type:"post",
            url:"/wph/user/login",
            async:false,
            contentType:"application/json;charset=utf-8",
            dataType:"json",
            data:JSON.stringify({telephone:$("#username").val(),password:$("#password").val()}),
            success:function (result) {
                if (result["code"]==1){
                    $(location).attr("href","http://localhost:8088/wph/view/user/user_message.html");
                }
                else{
                    alert(result["msg"]);
                }
            },
            error:function (error) {
                alert("提交失败"+error);
            }
        });
    });

});