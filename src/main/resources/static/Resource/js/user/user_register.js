$(document).ready(function(){

    //禁止点击验证码按钮
    $("#getCode").css("cursor","not-allowed");
    $("#getCode").attr("disabled","disabled");

    //验证码点击之后设置60s禁用时间
    $("#getCode").click(function () {
        countdown();
    });
    //60s倒计时
    function countdown() {
        var count = 60;
        var resend = setInterval(function(){
            count--;
            if (count > 0){
                $("#getCode").val(count+"后重新发送");
                $("#getCode").css("cursor","not-allowed");
                $("#getCode").attr("disabled","disabled");
            }else {
                clearInterval(resend);
                $("#getCode").val("获取验证码");
                $("#getCode").css("cursor","pointer");
                $("#getCode").removeAttr("disabled");
            }
        }, 1000);
    }

    //发送验证码
    $("#getCode").click(function () {
        $.ajax({
            type: "post",
            url: "/wph/user/sendCode",
            async: false, //同步请求
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            data: JSON.stringify({telephone: $("#telephone").val(),}),
            success: function (message) {
                //注册成功
                if (message["data"]==1) {
                    alert("验证码发送成功");
                }
            },
            error: function (message) {
                alert("验证码发送失败" + message);
            }
        });
    });

    //防表单提交
	$("#register_btn").click(function(e) {
        //标志变量
        var flag = true;

        //检查用户名是否符合规则
        var defaultCheckTelephone = checkTelephone();
        if (!defaultCheckTelephone) {
            return false;
        }

        //检查验证码是否符合规则
        var defaultCheckCode = checkCode();
        if (!defaultCheckCode) {
            return false;
        }

        //检查密码是否符合规则
        var defaultCheckPassword = checkPassword();
        if (!defaultCheckPassword) {
            return false;
        }

        //检查重复密码是否符合规则
        var defaultCheckRePassword = checkRePassword();
        if (!defaultCheckRePassword) {
            return false;
        }

        //检查阅读协议是否符合规则
        var defaultCheckRead = checkRead();
        if (!defaultCheckRead) {
            return false;
        }

        //全部正确，发送登录请求
        if (flag == true) {
            $.ajax({
                type: "post",
                url: "/wph/user/register",
                async: false, //同步请求
                contentType: "application/json;charset=utf-8",
                dataType: "json",
                data: JSON.stringify({telephone: $("#telephone").val(),
                                    password: $("#password").val(),
                                    code:$("#code").val()
                        }),
                success: function (message) {
                    alert(message["code"])
                    //注册成功
                    if (message["code"]==1) {
                        $(location).attr("href","http://localhost:8080/wph/view/index/index.html");
                    }
                    else{
                        alert(message["msg"]);
                    }
                },
                error: function (message) {
                    alert("提交失败" + message);
                }
            });
        }
    });

	//鼠标失去焦点事件
    $("#telephone").blur(function (e) {
        var checkT=checkTelephone();
        //手机号正确,验证码按钮可点击
        if(checkT==true){
            $("#getCode").css("cursor","pointer");
            $("#getCode").removeAttr("disabled");
        }
        //手机号错误，验证码按钮不可点击
        else{
            $("#getCode").css("cursor","not-allowed");
            $("#getCode").attr("disabled","disabled");
        }
    });
    $("#code").blur(function (e) {
        checkCode();
    });
    $("#password").blur(function (e) {
        checkPassword();
    });
    $("#pwd").blur(function (e) {
        checkRePassword();
    });

	//鼠标获得焦点事件
    $("#telephone").focus(function () {
        $(".telephone_msg").html("");
    });
    $("#code").focus(function () {
        $(".code_msg").html("");
    });
    $("#password").focus(function () {
        $(".password_msg").html("");
    });
    $("#pwd").focus(function () {
        $(".pwd_msg").html("");
    });
    $("#r_username").click(function () {
        $(".read_msg").html("");
    });
	//检查手机号
	function checkTelephone(e){
		var flag=true;
        var pattern=/^1[34578]\d{9}$/;
        if(!pattern.test($("#telephone").val())){
        	$(".telephone_msg").html("手机号码格式错误，请重新输入");
        	$(".telephone_msg").css("color","red");
        	flag=false;
        }
        else{
            $.ajax({
                type:"post",
                url:"/wph/user/checkTelephone",
                async:false, //同步请求
                contentType: "application/json;charset=utf-8",
                dataType: "json",
                data:JSON.stringify({telephone:$("#telephone").val()}),
                success:function (message) {
                    //手机号可以使用
                    if (message["code"]==1){
                        $(".telephone_msg").css("color","green");
                        $(".telephone_msg").html(message["data"]);
                        flag=true;
                    }
                    //手机号已经被注册
                    else{
                        $(".telephone_msg").css("color","red");
                        $(".telephone_msg").html(message["msg"]);
                        flag=false;
                    }
                },
                error:function (message) {
                    alert("提交失败"+message);
                    flag=false;
                }
            });
        }
		return flag;
	}
	//验证码
    function checkCode(e) {
        var flag=true;
        if($("#code").val().length!=6){
            $(".code_msg").html("验证码长度不正确，请重新输入");
            $(".code_msg").css("color","red");
            flag=false;
        }
        return flag;
    }
    //登录密码
    function checkPassword(e){
        var flag=true;
        if($("#password").val().trim().length<6 || $("#password").val().trim().length>15)
        {
            $(".password_msg").html("密码不符合格式，请重新输入");
            $(".password_msg").css("color","red");
            flag=false;
        }
        return flag;
    }

    //重复登录密码
    function checkRePassword(e){
        var flag=true;
        if($("#password").val().trim()!=$("#pwd").val().trim())
        {
            $(".pwd_msg").html("两次输入的密码不一致，请重新输入");
            $(".pwd_msg").css("color","red");
            flag=false;
        }
        return flag;
    }

    //阅读协议
    function checkRead(e) {
        if($("[name='checkbox']").attr("checked")){
            return true;
        }
        else{
            $(".read_msg").html("接受服务条款才能注册");
            $(".read_msg").css("color","red");
            return false;
        }
    }
});

