<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="dotest">
账号:<input type="text" name="accountNo"/><br>
金额:<input type="text" name="money"/><br>
		<select class="weui-select" id="select" name="stype">
						<option value="OFFPAY">线下支付</option>
						<option value="DIRECTPAY">支付宝支付</option>
						<option value="SCANPAY">微信支付</option>
		</select>
<input type = "submit" value="tj"/>
</form>
</body>
</html>