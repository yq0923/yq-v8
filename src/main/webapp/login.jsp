<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <jsp:include page="common/script.jsp"></jsp:include>
</head>
<body style="background:url(image/22.jpg);background-size:100%">
<center>
    <H1><font color="#00fa9a">登录页面</font></H1>
    <br>
    <form class="form-horizontal">
        <div class="control-group">
            <div class="controls">
                <label class="control-label" for="userName">用户名</label>
                <input type="text" id="userName" placeholder="用户名">
            </div>
        </div>
        <br>
        <div class="control-group">
            <div class="controls">
                <label class="control-label" for="passWord"><font color="#00ffff">密码</font></label>
                <input type="password" id="passWord" placeholder="密码">
            </div>
        </div><br>
        <div class="control-group">
            <div class="controls">
                <label class="control-label" for="passWord"><font color="#00fa9a">记住密码</font></label>
                <input type="checkbox" name="rememberMe" >
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/forget.jsp">忘记密码 </a>
            </div>
        </div><br>
        <div>
            <button type="button" class="btn btn-danger " onclick="login()">
              登录
            </button>&nbsp;&nbsp;&nbsp;
            <button type="button" class="btn btn-primary " onclick="zhuce()">
                注册
            </button>
        </div>
    </form>
</center>
</body>
<script>
    function login(){
        var userName = $("#userName").val();
        var passWord = $("#passWord").val();
       var rememberMe = 0 ;
       if($("[name='rememberMe']").prop("checked")){
           rememberMe=1;
       }

        $.post(
            "/user/login.do",
            {"userName":userName,"passWord":passWord,"rememberMe":rememberMe},
            function (result){
                 if(result.status==200){
                     var flag = result.data;
                     if(flag==1){
                         bootbox.alert("用户名不存在！");
                     }else if(flag==2){
                         bootbox.alert("密码错误！");
                     }else{
                         location.href="/index.jsp"
                     }
                 }else{
                     bootbox.alert("登陆失败！");
                 }
            }
        )

    }

  function  zhuce(){
      location.href="/register.jsp"
  }


</script>

</html>
