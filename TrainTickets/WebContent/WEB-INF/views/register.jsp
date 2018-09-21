<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>12307 - ZY的火车票售票系统</title>
<link rel="stylesheet" href="assets/css/style.css" type="text/css" />
</head>
<body oncontextmenu=self.event.returnValue=false onselectstart="return false">
	<div id="container">
		<form action="registerHomepage" method="post">
			<div class="username-reg-field">
				<input type="text" name="username" value="" placeholder="用户名" />
			</div>
			<div class="username-reg-field">
				<input type="text" name="idcard" value="" placeholder="身份证号" />
			</div>
			<div class="username-reg-field">
				<input type="text" name="tel" value="" placeholder="电话号码" />
			</div>
			<div class="password-reg-field">
				<input type="password" name="password" value="" placeholder="密码" />
			</div>
			<input type="submit" name="submit" value="注册" />
		</form>
	</div>
</body>
</html>
