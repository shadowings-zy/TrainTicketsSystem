<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList"
	import="model.train.Train" import="model.user.User"%>
<%
	User user = (User) request.getAttribute("user");
	@SuppressWarnings("unchecked")
	ArrayList<Train> allTrains = (ArrayList<Train>) request.getAttribute("allTrains");
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
						<li class="active">12307 - 管理员界面</li>
					</ul>
				</div>
				<div class="main-area dashboard">
					<div class="row">
						<div class="span10">
							<div class="slate">
								<div class="page-header">
									<h2>火车管理</h2>
								</div>
								<table class="orders-table table">
									<tbody>
										<%
											for (int a = 0; a < allTrains.size() / 5 + 1; a++) {
										%>
										<tr>
											<%
												for (int b = 0; b < 5; b++) {
													if (5 * a + b < allTrains.size()) {
											%>
											<td>
												<form action="trainDetail" method="post">
													<input type="hidden" name="uid" value=<%= user.getUid() %> />
													<input type="hidden" name="tid" value=<%=allTrains.get(5 * a + b).getTid()%> />
													<input type="submit" name="submit" value="<%=allTrains.get(5 * a + b).getTname()%>" />
												</form>
											</td>
											<%
													} else {
											%>
											<td></td>
											<%
													}
												}
											%>
										</tr>
										<%
											}
										%>
									</tbody>
								</table>
							</div>
						</div>
						<div class="span10">
							<div class="slate">
								<div class="page-header">
									<h2>添加列车</h2>
								</div>
								<form action="addTrain" method="post">
									<table class="orders-table table">
										<tbody>
											<tr>
												<td>
													<form action="addTrain" method="post">
														<input type="hidden" name="uid" value=<%= user.getUid() %> />
														<input type="submit" name="submit" value="点击此处添加列车" />
													</form>
												</td>
											</tr>
										</tbody>
									</table>
								</form>
							</div>
						</div>
						<div class="span10">
							<div class="slate">
								<div class="page-header">
									<h2>用户身份管理</h2>
								</div>
								<form action="setUserStatus" method="post">
								<input type="hidden" name="uid" value=<%= user.getUid() %> />
									<table class="orders-table table">
										<tbody>
											<tr>
												<td>用户ID：</td>
												<td>设置状态：</td>
											</tr>
											<tr>
												<td><input type="text" name="userId" value="" /></td>
												<td><select name="userStatus" value="正常">
														<option value="正常">正常</option>
														<option value="管理员">管理员</option>
														<option value="限制购票">限制购票</option>
												</select></td>
											</tr>
											<tr>
												<td><input type="submit" name="submit" value="提交修改" /></td>
												<td></td>
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