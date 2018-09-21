<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList" import="model.user.User"%>
<%
	User user = (User) request.getAttribute("user");
	@SuppressWarnings("unchecked")
	ArrayList<String> cityList = (ArrayList<String>) request.getAttribute("cityList");
	@SuppressWarnings("unchecked")
	ArrayList<String> yearList = (ArrayList<String>) request.getAttribute("yearList");
	@SuppressWarnings("unchecked")
	ArrayList<String> monthList = (ArrayList<String>) request.getAttribute("monthList");
	@SuppressWarnings("unchecked")
	ArrayList<String> dayList = (ArrayList<String>) request.getAttribute("dayList");
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
						<li class="active">12307 - 购票系统主页</li>
					</ul>
				</div>
				<div class="main-area dashboard">
					<div class="row">
						<div class="span10">
							<div class="slate">
								<div class="page-header">
									<h2>选择城市与日期</h2>
								</div>
								<form action="trainShow" method="post">
									<input type="hidden" name="uid" value=<%= user.getUid() %>/></td>
									<table class="orders-table table">
										<tbody>
											<tr>
												<td>出发地：</td>
												<td></td>
												<td>目的地：</td>
											</tr>
											<tr>
												<td><select name="startCity" value="0">
														<%
															if (cityList != null) {
																for (int a = 0; a < cityList.size(); a++) {
														%>
														<option value="<%=a%>"><%=cityList.get(a)%></option>
														<%
															}
															} else {
														%>
														<option value="0">未查询到城市</option>
														<%
															}
														%>
												</select></td>
												<td></td>
												<td><select name="endCity" value="0">
														<%
															if (cityList != null) {
																for (int a = 0; a < cityList.size(); a++) {
														%>
														<option value="<%=a%>"><%=cityList.get(a)%></option>
														<%
															}
															} else {
														%>
														<option value="0">未查询到城市</option>
														<%
															}
														%>
												</select></td>
											</tr>
											<tr>
												<td>出发年份：</td>
												<td>出发月份：</td>
												<td>出发日期：</td>
											</tr>
											<tr>
												<td><select name="year" value="0">
														<%
															for (int a = 0; a < yearList.size(); a++) {
														%>
														<option value="<%=a%>"><%=yearList.get(a)%></option>
														<%
															}
														%>
												</select></td>
												<td><select name="month" value="0">
														<%
															for (int a = 0; a < monthList.size(); a++) {
														%>
														<option value="<%=a%>"><%=monthList.get(a)%></option>
														<%
															}
														%>
												</select></td>
												<td><select name="day" value="0">
														<%
															for (int a = 0; a < dayList.size(); a++) {
														%>
														<option value="<%=a%>"><%=dayList.get(a)%></option>
														<%
															}
														%>
												</select></td>
											</tr>
											<tr>
												<td></td>
												<td><input type="submit" name="submit"
													value="查询火车GoGoGo！" /></td>
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