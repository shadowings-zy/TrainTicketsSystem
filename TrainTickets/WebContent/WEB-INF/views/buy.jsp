<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.train.Train" import="model.train.TrainSeats"
	import="java.util.ArrayList" import="model.user.User" import="model.train.Train"%>
<%
	User user = (User) request.getAttribute("user");
	Train train = (Train) request.getAttribute("train");
	String startCity = (String) request.getAttribute("startCity");
	String endCity = (String) request.getAttribute("endCity");
	String date = (String) request.getAttribute("date");
	@SuppressWarnings("unchecked")
	ArrayList<TrainSeats> seatsInfo = (ArrayList<TrainSeats>) request.getAttribute("seatsInfo");
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
		<!-- 左侧导航栏 -->
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
						<li><a href="/TrainTickets/setting"><i class="icon-user"></i>
								${username} </a></li>
						<li><a href="/TrainTickets/login"> 退出登录 </a></li>
					</ul>
					<ul class="breadcrumb">
						<li class="active">12307 - 购买车票</li>
					</ul>
				</div>
				<div class="main-area dashboard">
					<div class="row">
						<div class="span10">
							<div class="slate">

								<div class="page-header">
									<h2>购票人信息</h2>
								</div>
								<form action="payForTicket" method="post">
									<input type="hidden" name="uid" value="<%= user.getUid() %>">
									<input type="hidden" name="tid" value="<%= train.getTid() %>">
									<input type="hidden" name="startCity" value="<%= startCity %>">
									<input type="hidden" name="endCity" value="<%= endCity %>">
									<input type="hidden" name="date" value="<%= date %>">
									<table class="orders-table table">
										<tbody>
											<tr>
												<td>您正在预订从<%=startCity%>到<%=endCity%>的<%=train.getTid()%>车次,请填写购票人信息</td>
											</tr>
											<tr>
												<td>乘客姓名：<input type="text" name="passengerName"
													value="" placeholder="乘客姓名" /></td>
											</tr>
											<tr>
												<td>身份证号：<input type="text" name="passengerId" value=""
													placeholder="身份证号" /></td>
											</tr>
											<tr>
												<td><select name="ctype" value="0">
														<%
															for (int a = 0; a < seatsInfo.size(); a++) {
														%>
														<option value="<%=seatsInfo.get(a).getCtype()%>"><%=seatsInfo.get(a).getCtype()%></option>
														<%
															}
														%>
												</select></td>
											</tr>
											<tr>
												<td><select name="nextTo" value="0">
														<option value="0">靠窗口</option>
														<option value="1">靠过道</option>
														<option value="2">无</option>
												</select></td>
											</tr>
											<tr>
												<td><input type="submit" name="submit" value="提交订单" />
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