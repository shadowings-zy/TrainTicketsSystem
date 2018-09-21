<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList"
	import="model.order.Order" import="model.user.User"%>
<%
	User user = (User) request.getAttribute("user");
	@SuppressWarnings("unchecked")
	ArrayList<Order> orderList = (ArrayList<Order>) request.getAttribute("orderList");
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
						<li class="active">12307 - 订单查询</li>
					</ul>
				</div>
				<div class="main-area dashboard">
					<div class="row">
						<div class="span10">
							<div class="slate">
								<div class="page-header">
									<h2>订单信息</h2>
								</div>
								<table class="orders-table table">
									<tbody>
										<tr>
											<td>列车号码</td>
											<td>座次信息</td>
											<td>发车时间</td>
											<td>起止车站</td>
											<td>改签操作</td>
											<td>退票操作</td>
										</tr>
										<%
											if (orderList != null) {
												for (int a = 0; a < orderList.size(); a++) {
										%>
										<tr>
											<td><%=orderList.get(a).getTname()%></td>
											<td>第<%=(Integer.parseInt(orderList.get(a).getCid()) + 1)%>车厢<%=orderList.get(a).getLocation()%>座
											</td>
											<td><%=orderList.get(a).getDate()%></td>
											<td><%=orderList.get(a).getFromCity()%>-<%=orderList.get(a).getToCity()%></td>
											<td>
												<form action="changeOrder" method="post">
													<input type="hidden" name="uid" value=<%= user.getUid() %> />
													<input type="hidden" name="oid" value=<%=orderList.get(a).getOid()%> />
													<input type="hidden" name="startCity" value=<%=orderList.get(a).getFromCity()%> />
													<input type="hidden" name="endCity" value=<%=orderList.get(a).getToCity()%> />
													<input type="hidden" name="date" value=<%=orderList.get(a).getDate()%> />
													<input type="submit" name="submit" value="改签" />
												</form>
											</td>
											<td>
												<form action="cancelOrder" method="post">
													<input type="hidden" name="uid" value=<%= user.getUid() %> />
													<input type="hidden" name="oid" value=<%=orderList.get(a).getOid()%> />
													<input type="submit" name="submit" value="退票" />
												</form>
											</td>
										</tr>
										<%
											}
											} else {
										%>
										<tr>
											<td>未查询到订单</td>
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