<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList"
	import="model.train.Train" import="model.train.TrainStop" import="model.user.User"%>
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
						<li class="active">12307 - 火车管理</li>
					</ul>
				</div>
				<div class="main-area dashboard">
					<div class="row">
						<div class="span10">
							<div class="slate">
								<div class="page-header">
									<h2>添加列车</h2>
								</div>
								<form action="addNewTrain" method="post">
									<table class="orders-table table">
										<tbody>
											<tr>
												<td>列车号</td>
												<td><input class="detailInput" type="text"
													name="tname" value="" /></td>
												<td>车站数</td>
												<td><input class="detailInput" type="text"
													name="stop" value="" /></td>
												<td>列车车型</td>
												<td><select name="type" value="高速动车组" class="typeSelect">
														<option value="高速动车组">G</option>
														<option value="快速列车">K</option>
												</select></td>
											</tr>
											<tr>
												<td>序号</td>
												<td>城市</td>
												<td>车站</td>
												<td>到达时间</td>
												<td>停留时间</td>
												<td>里程数</td>
												<td></td>
											</tr>
											<%
												for (int a = 0; a < 10; a++) {
											%>
											<tr>
												<td><%= (a + 1) %></td>
												<input type="hidden" name="sid" value="<%= a %>">
												<td><input class="detailInput" type="text" name="city<%= a %>"
													value="" /></td>
												<td><input class="detailInput" type="text"
													name="station<%= a %>" value="" /></td>
												<td><input class="detailInput" type="text"
													name="arriveTime<%= a %>" value="" /></td>
												<td><input class="detailInput" type="text"
													name="stopTime<%= a %>" value="" /></td>
												<td><input type="text" class="detailInput" name="mileage<%= a %>" value="" /></td>
											</tr>
											<%
												}
											%>
											<tr>
												<td>
													<input type="hidden" name="uid" value=<%= user.getUid() %> />
													<input class="detailInput" type="submit" name="submit" value="添加" />
												</td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
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