<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>忘记密码</title>
    <jsp:include page="common/script.jsp"></jsp:include>
</head>
<body style="background:url(image/21.jpg);background-size:100%">
<center>
    <H1><font color="#00fa9a">修改密码</font></H1>
    <br>
    <form class="form-horizontal">
        <div class="control-group">
            <div class="controls">
                <label class="control-label">注册手机号</label>
                <input type="text" id="phone" placeholder="注册手机号">
                <button type="button" class="btn btn-info " onclick="sendCode()">
                    获取验证码
                </button>
            </div>
        </div>
        <br>
        <div class="control-group">
            <div class="controls">
                <label class="control-label" for="passWord">验证码</label>
                <input type="password" id="code" placeholder="验证码">
            </div>
        </div><br>
        <div class="control-group">
            <div class="controls">
                <label class="control-label" for="passWord">密码</label>
                <input type="password" id="passWord" placeholder="密码">
            </div>
        </div><br>
        <div class="control-group">
            <div class="controls">
                <label class="control-label" for="passWord"><font color="#00bfff">确认密码</font></label>
                <input type="password" id="passWord2" placeholder="确认密码">
            </div>
        </div>
        <br>
        <br>
        <div>
            <button type="button" class="btn btn-danger " onclick="callBasePassword()">
                确认
            </button>
        </div>

    </form>
</center>
</body>
<script>
    function sendCode(){
        var phone = $("#phone").val();
        $.post(
            "/user/sendCode.do",
            {"phone":phone},
            function (result){
                 if(result.status==200) {
                     var res = result.data;
                     if (res == 2) {
                         bootbox.alert("发送失败！");

                     } else if (res == 3) {
                         bootbox.alert("手机号不能为空！");
                     }
                 }
            }
        )

    }

    function callBasePassword() {
        var phone = $("#phone").val();
        var code = $("#code").val();
        var passWord = $("#passWord").val();
        var rePassword = $("#passWord2").val();
        if (passWord!=null && passWord == rePassword) {


            $.post(
                "/user/callBasePassword.do",
                {"phone": phone,"code":code,"passWord":passWord},
                function (result) {
                    if (result.status == 200) {
                        location.href="/login.jsp"

                        }else if(result.status == 1001){
                        bootbox.alert("温馨提示：验证码输入错误！");
                    } else {
                        bootbox.alert("操作失败，该手机号已被注册！");
                    }
                }
            )

        }else {
            bootbox.alert("温馨提示：两次输入密码不一致！");
        }
    }


</script>

</html>
