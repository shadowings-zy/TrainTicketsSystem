<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.user.User"%>
<%
	User user = (User) request.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>12307 - ZY的火车售票系统</title>
<link href="assets/css/bootstrap.css" rel="stylesheet">
<link href="assets/css/font-awesome.css" rel="stylesheet">
<link href="assets/css/admin.css" rel="stylesheet">
</head>
<body oncontextmenu=self.event.returnValue=false onselectstart="return false">
	<div class="container">
		<div class="row">
			<div class="span2">
				<div class="main-left-col">
					<ul class="side-nav">
						<li class="sideBlock">
							<form action="homepage" method="post">
								<i class="icon-home"></i>
								<input type="hidden" name="uid" value=<%= user.getUid() %> /></td>
								<input class = "sideInput" type="submit" name="submit" value="主页" />
							</form>
						</li>
						<li class="sideBlock">
							<form action="order" method="post">
								<i class="icon-shopping-cart"></i>
								<input type="hidden" name="uid" value=<%= user.getUid() %> /></td>
								<input class = "sideInput" type="submit" name="submit" value="订单" />
							</form>
						</li>
						<li class="sideBlock">
							<form action="setting" method="post">
								<i class="icon-cogs"></i>
								<input type="hidden" name="uid" value=<%= user.getUid() %> /></td>
								<input class = "sideInput" type="submit" name="submit" value="设置" />
							</form>
						</li>
						<li class="sideBlock">
							<form action="admin" method="post">
								<i class="icon-info-sign"></i>
								<input type="hidden" name="uid" value=<%= user.getUid() %> /></td>
								<input class = "sideInput" type="submit" name="submit" value="管理" />
							</form>
						</li>
					</ul>
				</div>
			</div>
			<div class="span10">
				<div class="secondary-masthead">
					<ul class="nav nav-pills pull-right">
						<li><a href="/TrainTickets/login"> <i class="icon-user"></i> <%=user.getUname()%> - 退出登录 </a></li>
					</ul>
					<ul class="breadcrumb">
						<li class="active">12307 - 个人设置</li>
					</ul>
				</div>
				<div class="main-area dashboard">
					<div class="row">
						<div class="span10">
							<div class="slate">
								<div class="page-header">
									<h2>设置个人信息</h2>
								</div>
								<form action="userSetting" method="post">
									<input type="hidden" name="uid" value=<%=user.getUid()%> /></td>
									<table class="orders-table table">
										<tbody>
											<tr>
												<td>用户昵称：<input type="text" name="username"
													value="<%=user.getUname()%>" placeholder="用户名" /></td>
											</tr>
											<tr>
												<td>身份证号：<input type="text" name="idcard"
													value="<%=user.getIdcard()%>" placeholder="身份证号" /></td>
											</tr>
											<tr>
												<td>联系电话：<input type="text" name="tel" value="<%=user.getTel()%>"
													placeholder="联系电话" /></td>
											</tr>
											<tr>
												<td>用户密码：<input type="text" name="password"
													value="<%=user.getPassword()%>" placeholder="密码" /></td>
											</tr>
											<tr>
												<td><input type="submit" name="submit" value="提交修改" />
												</td>
											</tr>
										</tbody>
									</table>
								</form>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="span10 footer">
							<p>&copy; 12307火车票购票系统</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>