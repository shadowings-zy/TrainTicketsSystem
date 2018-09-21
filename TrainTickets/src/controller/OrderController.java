package controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.order.Order;
import model.order.OrderJDBCTemplate;
import model.train.Train;
import model.train.TrainJDBCTemplate;
import model.train.TransferTrain;
import model.user.User;
import model.user.UserJDBCTemplate;

@Controller
public class OrderController {
	private ApplicationContext context;

	// 订单-浏览所有订单
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String orderPage(ModelMap model, HttpServletRequest request) {
		String uid = request.getParameter("uid");
		
		context = new ClassPathXmlApplicationContext("Beans.xml");
		UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");
		ArrayList<User> userList = userJDBCTemplate.getUserByUid(uid);

		if (userList.size() == 1 && !userList.get(0).getStatus().equals("限制购票")) {
			OrderJDBCTemplate orderJDBCTemplate = (OrderJDBCTemplate) context.getBean("OrderJDBCTemplate");
			ArrayList<Order> orderList = orderJDBCTemplate.getOrderByUid(userList.get(0).getUid());
			model.addAttribute("user", userList.get(0));
			model.addAttribute("orderList", orderList);
			return "order";
		} else if (userList.size() == 1 && userList.get(0).getStatus().equals("限制购票")){
			return "statusError";
		} else {
			return "error";
		}
	}

	// 改签请求
	@RequestMapping(value = "/changeOrder", method = RequestMethod.POST)
	public String changeOrderRequest(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		String uid = request.getParameter("uid");
		String oid = new String(request.getParameter("oid").getBytes("ISO-8859-1"), "UTF-8");
		String startCity = new String(request.getParameter("startCity").getBytes("ISO-8859-1"), "UTF-8");
		String endCity = new String(request.getParameter("endCity").getBytes("ISO-8859-1"), "UTF-8");
		String date = new String(request.getParameter("date").getBytes("ISO-8859-1"), "UTF-8");
		
		System.out.println(startCity + endCity + date);

		context = new ClassPathXmlApplicationContext("Beans.xml");
		UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");
		ArrayList<User> userList = userJDBCTemplate.getUserByUid(uid);

		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");
		ArrayList<Train> trainList = trainJDBCTemplate.getTrainByStop(startCity, endCity, date);

		for (int a = 0; a < trainList.size(); a++) {
			trainList.get(a).setSeatsInfo(trainJDBCTemplate.getRemainingSeats(trainList.get(a).getTid(), date,
					trainList.get(a).getStartIndex() + "", trainList.get(a).getEndIndex() + ""));
		}

		ArrayList<TransferTrain> transferList = trainJDBCTemplate.getTrainTransfer(startCity, endCity, date);

		if (userList.size() == 1 && !userList.get(0).getStatus().equals("限制购票")) {
			OrderJDBCTemplate orderJDBCTemplate = (OrderJDBCTemplate) context.getBean("OrderJDBCTemplate");
			orderJDBCTemplate.deleteOrder(oid);
			
			model.addAttribute("user", userList.get(0));
			model.addAttribute("trainList", trainList);
			model.addAttribute("startCity", startCity);
			model.addAttribute("endCity", endCity);
			model.addAttribute("transferList", transferList);
			return "trainShow";
		} else if (userList.size() == 1 && userList.get(0).getStatus().equals("限制购票")){
			return "statusError";
		} else {
			return "error";
		}
	}

	// 取消订单请求
	@RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
	public String cancelOrder(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		String uid = request.getParameter("uid");
		String oid = new String(request.getParameter("oid").getBytes("ISO-8859-1"), "UTF-8");

		context = new ClassPathXmlApplicationContext("Beans.xml");
		UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");
		ArrayList<User> userList = userJDBCTemplate.getUserByUid(uid);

		if (userList.size() == 1 && !userList.get(0).getStatus().equals("限制购票")) {
			OrderJDBCTemplate orderJDBCTemplate = (OrderJDBCTemplate) context.getBean("OrderJDBCTemplate");
			orderJDBCTemplate.deleteOrder(oid);
			ArrayList<Order> orderList = orderJDBCTemplate.getOrderByUid(userList.get(0).getUid());
			model.addAttribute("user", userList.get(0));
			model.addAttribute("orderList", orderList);
			return "order";
		} else if (userList.size() == 1 && userList.get(0).getStatus().equals("限制购票")){
			return "statusError";
		} else {
			return "error";
		}
	}
}
