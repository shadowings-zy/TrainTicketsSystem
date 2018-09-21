<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList" import="model.train.TrainSeats"
	import="model.train.Train" import="model.train.TransferTrain" import="model.user.User"%>
<%
	User user = (User) request.getAttribute("user");
	String startCity = (String) request.getAttribute("startCity");
	String endCity = (String) request.getAttribute("endCity");
	String date = (String) request.getAttribute("date");
	@SuppressWarnings("unchecked")
	ArrayList<Train> trainList = (ArrayList<Train>) request.getAttribute("trainList");
	@SuppressWarnings("unchecked")
	ArrayList<TransferTrain> transferList = (ArrayList<TransferTrain>) request.getAttribute("transferList");
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
						<li><a href="/TrainTickets/setting"><i class="icon-user"></i>
								<%=user.getUname()%> </a></li>
						<li><a href="/TrainTickets/login"> 退出登录 </a></li>
					</ul>
					<ul class="breadcrumb">
						<li class="active">12307 - 订单查询</li>
					</ul>
				</div>
				<div class="main-area dashboard">
					<div class="row">
						<div class="span10">
							<div class="slate">
								<div class="page-header">
									<h2>火车票查询</h2>
								</div>
								<table class="orders-table table">
									<tbody>
										<tr>
											<td>列车号码</td>
											<td>起止时间</td>
											<td>起止车站</td>
											<td>票价</td>
											<td>购票操作</td>
										</tr>
										<%
											if (trainList.size() > 0) {
												for (int a = 0; a < trainList.size(); a++) {
										%>
										<tr>
											<td><%=trainList.get(a).getTname()%></td>
											<td><%=trainList.get(a).getStartTime()%>-<%=trainList.get(a).getEndTime()%></td>
											<td><%=trainList.get(a).getStart()%>-<%=trainList.get(a).getEnd()%></td>
											<td><%=trainList.get(a).getMileage()%></td>
											<form action="buy" method="post">
												<input type="hidden" name="uid" value="<%= user.getUid() %>">
												<input type="hidden" name="tid" value="<%= trainList.get(a).getTid() %>">
												<input type="hidden" name="startCity" value="<%= startCity %>">
												<input type="hidden" name="endCity" value="<%= endCity %>">
												<input type="hidden" name="date" value="<%= date %>">
												<td><input type="submit" name="submit" value="购票" /></td>
											</form>
										</tr>
										<tr>
											<%
												int trainListSize = trainList.get(a).getSeatsInfo().size();
												for (int b = 0; b < 5; b++) {
													if(b < trainListSize){
											%>
											<td><%=trainList.get(a).getSeatsInfo().get(b).getCtype()%>:<%=trainList.get(a).getSeatsInfo().get(b).getSeatsCount()%></td>
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
											} else {
										%>
										<tr>
											<td>未查询到火车</td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
										<%
											}
										%>
										<tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="span10">
							<div class="slate">
								<div class="page-header">
									<h2>换乘推荐</h2>
								</div>
									<table class="orders-table table">
									<tbody>
										<%
											if (transferList.size() > 0) {
												for (int a = 0; a < transferList.size(); a++) {
										%>
										<tr>
											<td>第一辆列车：<%= transferList.get(a).getFirstTname() %></td>
											<td>第二辆列车：<%= transferList.get(a).getSecondTname() %></td>
											<td>列车中转站：<%= transferList.get(a).getTransferStation() %></td>
										</tr>
										<%
												}
											} else {
										%>
										<tr>
											<td>未查询到换乘方案</td>
										</tr>
										<%
											}
										%>
									</tbody>
								</table>
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