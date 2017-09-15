<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆</title>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/js/jquery-3.2.1.js"></script>
</head>
<script type="text/javascript">  
var InterValObj; //timer变量，控制时间  
var count = 100; //间隔函数，1秒执行  
var curCount;//当前剩余秒数  
function reloadCode(){  
	var number = $("#number").val();
	alert(number);
      //向后台发送请求生成code并存入session中
      $.post("codeCreate.action", { time : new Date().getTime(), number : number } );
      curCount = count;  
      //读秒提示
      $("#btn").attr("disabled", "true");  
      $("#btn").val("请在" + curCount + "秒内输入验证码");  

      //启动定时器
      InterValObj = window.setInterval(SetRemainTime, 1000);
}     
function SetRemainTime() {  
    if (curCount == 0) {                  
        window.clearInterval(InterValObj);// 停止计时器  
        $("#btn").removeAttr("disabled");// 启用按钮  
        $("#btn").val("重新发送验证码");  
//         code = ""; // 清除验证码。如果不清除，过时间后，输入收到的验证码依然有效
        $.post("sessionRemove.action", { time : new Date().getTime(),codeName:'code' } ); 
          
    }else {  
        curCount--;  
        $("#btn").val("请在" + curCount + "秒内输入验证码");  
    }  
}  

</script>
<body>
<form method='post' action='login.action'>  
    <input type="text" name="number" id="number" value=""/>请输入号码<br>
    <input type="text" name="code" id="code" value=""/>
   
    <input type="button" id="btn" value="点击获取验证码" onclick="reloadCode()"/><br>
    <input type="submit" value="Login">  
    
</form>  
</body>
</html>