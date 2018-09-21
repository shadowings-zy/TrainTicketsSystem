<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList"
	import="model.train.Train" import="model.train.TrainStop" import="model.user.User"%>
<%
	User user = (User) request.getAttribute("user");
	Train train = (Train) request.getAttribute("train");
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
									<h2><%= train.getTname() %></h2>
								</div>
								<table class="orders-table table">
									<tbody>
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
											for (int a = 0; a < train.getStopInfo().size(); a++) {
										%>
										<tr>
											<form action="updateTrainDetail" method="post">
												<td><%= (a + 1) %></td>
												<input type="hidden" name="uid" value="<%=user.getUid()%>">
												<input type="hidden" name="tid" value="<%=train.getTid()%>">
												<input type="hidden" name="sid" value="<%=a%>">
												<td><input class="detailInput" type="text" name="city" value="<%= train.getStopInfo().get(a).getCity()%>" /></td>
												<td><input class="detailInput" type="text" name="station" value="<%= train.getStopInfo().get(a).getStation()%>" /></td>
												<td><input class="detailInput" type="text" name="arriveTime" value="<%= train.getStopInfo().get(a).getArriveTime()%>" /></td>
												<td><input class="detailInput" type="text" name="stopTime" value="<%= train.getStopInfo().get(a).getStopTime()%>" /></td>
												<td><input class="detailInput" type="text" name="mileage" value="<%= train.getStopInfo().get(a).getMileage()%>" /></td>
												<td><input class="detailInput" type="submit" name="submit" value="修改" /></td>
											</form>
										</tr>
										<%
											}
										%>
										<tr>
											<form action="deleteTrain" method="post">
												<td></td>
												<td></td>
												<td></td>
												<td>
													<input type="hidden" name="uid" value=<%=user.getUid()%> />
													<input type="hidden" name="tid" value=<%=train.getTid()%> />
													<input type="submit" name="submit" value="删除" /></td>
												<td></td>
												<td></td>
												<td></td>
											</form>
										</tr>
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