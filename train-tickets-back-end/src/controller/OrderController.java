package controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;

import model.order.Order;
import model.order.OrderJDBCTemplate;
import tools.HttpTools;
import tools.ResponseContent;

@Controller
public class OrderController {
	private ApplicationContext context;

	// 锁定座位（添加订单）
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/lockSeat", method = RequestMethod.POST)
	public void lockSeat(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String userId = new String(request.getParameter("userId"));
		String passengerId = new String(request.getParameter("passengerId"));
		String userName = new String(request.getParameter("userName"));
		String trainId = new String(request.getParameter("trainId"));
		String trainName = new String(request.getParameter("trainName"));
		String carriage = new String(request.getParameter("carriage"));
		String seatType = new String(request.getParameter("seatType"));
		String seatId = new String(request.getParameter("seatId"));
		String seatLocation = new String(request.getParameter("seatLocation"));
		String startTime = new String(request.getParameter("startTime"));
		String startStopId = new String(request.getParameter("startStopId"));
		String startStationName = new String(request.getParameter("startStationName"));
		String endStopId = new String(request.getParameter("endStopId"));
		String endStationName = new String(request.getParameter("endStationName"));
		String date = new String(request.getParameter("date"));
		String createAt = new String(request.getParameter("createAt"));
		String status = new String(request.getParameter("status"));

		context = new ClassPathXmlApplicationContext("Beans.xml");
		OrderJDBCTemplate orderJDBCTemplate = (OrderJDBCTemplate) context.getBean("OrderJDBCTemplate");

		Order order = new Order();
		order.setUserId(userId);
		order.setPassengerId(passengerId);
		order.setUserName(userName);
		order.setTrainId(trainId);
		order.setTrainName(trainName);
		order.setCarriage(carriage);
		order.setSeatType(seatType);
		order.setSeatId(seatId);
		order.setSeatLocation(seatLocation);
		order.setStartTime(startTime);
		order.setStartStopId(startStopId);
		order.setStartStationName(startStationName);
		order.setEndStopId(endStopId);
		order.setEndStationName(endStationName);
		order.setDate(date);
		order.setCreateAt(createAt);
		order.setStatus(status);

		Order newOrder = orderJDBCTemplate.addOrder(order);
		String jsonOutput = JSON.toJSONString(newOrder);
		ResponseContent responseContent = new ResponseContent("1000", jsonOutput, "successful");
		HttpTools.responseTools(response, responseContent);
	}

	// 查询订单
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/queryOrder", method = RequestMethod.POST)
	public void queryOrder(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String idcard = new String(request.getParameter("idcard"));

		context = new ClassPathXmlApplicationContext("Beans.xml");
		OrderJDBCTemplate orderJDBCTemplate = (OrderJDBCTemplate) context.getBean("OrderJDBCTemplate");

		System.out.println("查询订单");
		ArrayList<Order> orderList = orderJDBCTemplate.getOrderByIdcard(idcard);
		String jsonOutput = JSON.toJSONString(orderList);
		ResponseContent responseContent = new ResponseContent("1000", jsonOutput, "successful");
		HttpTools.responseTools(response, responseContent);
	}

	// 修改订单付款状态
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/updateOrder", method = RequestMethod.POST)
	public void updateOrder(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String orderId = new String(request.getParameter("orderId"));
		String userId = new String(request.getParameter("userId"));
		String passengerId = new String(request.getParameter("passengerId"));
		String userName = new String(request.getParameter("userName"));
		String trainId = new String(request.getParameter("trainId"));
		String trainName = new String(request.getParameter("trainName"));
		String carriage = new String(request.getParameter("carriage"));
		String seatType = new String(request.getParameter("seatType"));
		String seatId = new String(request.getParameter("seatId"));
		String seatLocation = new String(request.getParameter("seatLocation"));
		String startTime = new String(request.getParameter("startTime"));
		String startStopId = new String(request.getParameter("startStopId"));
		String startStationName = new String(request.getParameter("startStationName"));
		String endStopId = new String(request.getParameter("endStopId"));
		String endStationName = new String(request.getParameter("endStationName"));
		String date = new String(request.getParameter("date"));
		String createAt = new String(request.getParameter("createAt"));
		String status = new String(request.getParameter("status"));

		context = new ClassPathXmlApplicationContext("Beans.xml");
		OrderJDBCTemplate orderJDBCTemplate = (OrderJDBCTemplate) context.getBean("OrderJDBCTemplate");

		Order order = new Order();
		order.setOrderId(orderId);
		order.setUserId(userId);
		order.setPassengerId(passengerId);
		order.setUserName(userName);
		order.setTrainId(trainId);
		order.setTrainName(trainName);
		order.setCarriage(carriage);
		order.setSeatType(seatType);
		order.setSeatId(seatId);
		order.setSeatLocation(seatLocation);
		order.setStartTime(startTime);
		order.setStartStopId(startStopId);
		order.setStartStationName(startStationName);
		order.setEndStopId(endStopId);
		order.setEndStationName(endStationName);
		order.setDate(date);
		order.setCreateAt(createAt);
		order.setStatus(status);

		orderJDBCTemplate.updateOrder(orderId, order);
		ResponseContent responseContent = new ResponseContent("1000", "修改成功", "successful");
		HttpTools.responseTools(response, responseContent);
	}

	// 删除订单
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/deleteOrder", method = RequestMethod.POST)
	public void deleteOrder(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String orderId = new String(request.getParameter("orderId"));

		context = new ClassPathXmlApplicationContext("Beans.xml");
		OrderJDBCTemplate orderJDBCTemplate = (OrderJDBCTemplate) context.getBean("OrderJDBCTemplate");
		orderJDBCTemplate.deleteOrderByOrderId(orderId);

		ResponseContent responseContent = new ResponseContent("1000", "修改成功", "successful");
		HttpTools.responseTools(response, responseContent);
	}
}
