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
		<form action="loginHomepage" method="post">
			<div class="login">数据库课设 - 12307火车售票系统</div>
			<div class="username-text">用户名:</div>
			<div class="password-text">密码:</div>
			<div class="username-field">
				<input type="text" name="username" value="" />
			</div>
			<div class="password-field">
				<input type="password" name="password" value="" />
			</div>
			<input type="submit" name="submit" value="登录" />
		</form>
	</div>
	<div id="footer">
		第一次登录 <a href="/TrainTickets/register" target="_blank" title="点击此处注册">点击此处注册</a>
	</div>
</body>
</html>