<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>一闪而过</title>
</head>

<body >
<form id="dosub" method="post" action="http://zq.qingcailuobo.cn/wechat_manager/joymeterPay/scanPay">

<input type="hidden" name="cjoyId" value="${cjoyId }" />
<input type="hidden" name="accountNo" value="${accountNo }"/>
<input type="hidden" name="money"value="${money }"/>
<input type="hidden" name="access_token"value="${access_token }"/>
<input type="hidden" name="stype"value="${stype }">
<input type="hidden" name="returnUrl" value="${returnUrl }">
<input type = "submit" value="tj"/>
</form>
</body>
<script>document.forms['dosub'].submit();</script>
<script>
// function ss(){
// 	$("#dosub").submit();
// }
</script>
</html>
