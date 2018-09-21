package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

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
public class TrainController {
	private ApplicationContext context;
	private static ArrayList<String> cityList = new ArrayList<String>(Arrays.asList("上海", "南京", "济南", "北京", "杭州", "天津",
			"合肥", "沈阳", "郑州", "武汉", "昆明", "贵阳", "长沙", "深圳", "广州", "西安", "南昌", "福州", "厦门"));
	private static ArrayList<String> yearList = new ArrayList<String>(Arrays.asList("2018"));
	private static ArrayList<String> monthList = new ArrayList<String>(Arrays.asList("9"));
	private static ArrayList<String> dayList = new ArrayList<String>(
			Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
					"18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"));

	// 登录界面
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	// 登录后主页-选择火车站及日期
	@RequestMapping(value = "/loginHomepage", method = RequestMethod.POST)
	public String loginHomepage(ModelMap model, HttpServletRequest request)
			throws UnsupportedEncodingException {
		String username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
		String password = new String(request.getParameter("password").getBytes("ISO-8859-1"), "UTF-8");

		context = new ClassPathXmlApplicationContext("Beans.xml");
		UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");
		ArrayList<User> userList = userJDBCTemplate.getUserByUnameAndPassword(username, password);

		if (userList.size() == 1 && !userList.get(0).getStatus().equals("限制购票")) {
			model.addAttribute("user", userList.get(0));
			model.addAttribute("cityList", cityList);
			model.addAttribute("yearList", yearList);
			model.addAttribute("monthList", monthList);
			model.addAttribute("dayList", dayList);
			return "homepage";
		} else if (userList.size() == 1 && userList.get(0).getStatus().equals("限制购票")) {
			return "statusError";
		} else {
			return "error";
		}
	}

	// 注册界面
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerPage() {
		return "register";
	}

	// 注册后主页-选择火车站及日期
	@RequestMapping(value = "/registerHomepage", method = RequestMethod.POST)
	public String registerHomepage(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		String username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
		String idcard = new String(request.getParameter("idcard").getBytes("ISO-8859-1"), "UTF-8");
		String tel = new String(request.getParameter("tel").getBytes("ISO-8859-1"), "UTF-8");
		String password = new String(request.getParameter("password").getBytes("ISO-8859-1"), "UTF-8");

		context = new ClassPathXmlApplicationContext("Beans.xml");
		UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");
		User newUser = new User();
		newUser.setUname(username);
		newUser.setIdcard(idcard);
		newUser.setTel(tel);
		newUser.setPassword(password);
		newUser.setStatus("正常");

		User user = userJDBCTemplate.addUser(newUser);

		if (user != null && !user.getStatus().equals("限制购票")) {
			model.addAttribute("user", user);
			model.addAttribute("cityList", cityList);
			model.addAttribute("yearList", yearList);
			model.addAttribute("monthList", monthList);
			model.addAttribute("dayList", dayList);
			return "homepage";
		} else if (user != null && user.getStatus().equals("限制购票")) {
			return "statusError";
		} else {
			return "error";
		}
	}

	// 主页-选择火车站及日期
	@RequestMapping(value = "/homepage", method = RequestMethod.POST)
	public String homepage(ModelMap model, HttpServletRequest request)
			throws UnsupportedEncodingException {
		String uid = new String(request.getParameter("uid"));

		context = new ClassPathXmlApplicationContext("Beans.xml");
		UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");
		ArrayList<User> userList = userJDBCTemplate.getUserByUid(uid);

		if (userList.size() == 1 && !userList.get(0).getStatus().equals("限制购票")) {
			model.addAttribute("user", userList.get(0));
			model.addAttribute("cityList", cityList);
			model.addAttribute("yearList", yearList);
			model.addAttribute("monthList", monthList);
			model.addAttribute("dayList", dayList);
			return "homepage";
		} else if (userList.size() == 1 && userList.get(0).getStatus().equals("限制购票")) {
			return "statusError";
		} else {
			return "error";
		}
	}

	// 选择列车界面
	@RequestMapping(value = "/trainShow", method = RequestMethod.POST)
	public String trainShowPage(ModelMap model, HttpServletRequest request) {
		String uid = request.getParameter("uid");
		String year = yearList.get(Integer.parseInt(request.getParameter("year")));
		String month = monthList.get(Integer.parseInt(request.getParameter("month")));
		String day = dayList.get(Integer.parseInt(request.getParameter("day")));
		String date = year + "-" + month + "-" + day;
		String startCity = cityList.get(Integer.parseInt(request.getParameter("startCity")));
		String endCity = cityList.get(Integer.parseInt(request.getParameter("endCity")));

		context = new ClassPathXmlApplicationContext("Beans.xml");
		UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");
		ArrayList<User> userList = userJDBCTemplate.getUserByUid(uid);
		
		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");
		ArrayList<Train> trainList = trainJDBCTemplate.getTrainByStop(startCity, endCity, date);
		ArrayList<TransferTrain> transferList = trainJDBCTemplate.getTrainTransfer(startCity, endCity, date);

		for (int a = 0; a < trainList.size(); a++) {
			trainList.get(a).setSeatsInfo(trainJDBCTemplate.getRemainingSeats(trainList.get(a).getTid(), date,
					trainList.get(a).getStartIndex() + "", trainList.get(a).getEndIndex() + ""));
		}

		if (userList.size() == 1 && trainList != null && !userList.get(0).getStatus().equals("限制购票")) {
			model.addAttribute("user", userList.get(0));
			model.addAttribute("trainList", trainList);
			model.addAttribute("startCity", startCity);
			model.addAttribute("endCity", endCity);
			model.addAttribute("date", date);
			model.addAttribute("transferList", transferList);
			return "trainShow";
		} else if (userList.size() == 1 && trainList != null && userList.get(0).getStatus().equals("限制购票")) {
			return "statusError";
		} else {
			return "error";
		}
	}

	// 购票界面
	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public String buyTicketPage(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		String uid = request.getParameter("uid");
		String tid = request.getParameter("tid");
		String startCity = new String(request.getParameter("startCity").getBytes("ISO-8859-1"), "UTF-8");
		String endCity = new String(request.getParameter("endCity").getBytes("ISO-8859-1"), "UTF-8");
		String date = new String(request.getParameter("date").getBytes("ISO-8859-1"), "UTF-8");
		
		context = new ClassPathXmlApplicationContext("Beans.xml");
		UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");
		ArrayList<User> userList = userJDBCTemplate.getUserByUid(uid);
		
		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");
		Train train = trainJDBCTemplate.getTrainByTid(tid);

		train.setSeatsInfo(trainJDBCTemplate.getRemainingSeats(train.getTid(), date, train.getStartIndex() + "", train.getEndIndex() + ""));
		
		if (userList.size() == 1 && !userList.get(0).getStatus().equals("限制购票")) {
			model.addAttribute("user", userList.get(0));
			model.addAttribute("train", train);
			model.addAttribute("startCity", startCity);
			model.addAttribute("endCity", endCity);
			model.addAttribute("date", date);
			model.addAttribute("seatsInfo", train.getSeatsInfo());
			return "buy";
		} else if (userList.size() == 1 && userList.get(0).getStatus().equals("限制购票")) {
			return "statusError";
		} else {
			return "error";
		}
	}

	// 发送火车票订单请求
	@RequestMapping(value = "/payForTicket", method = RequestMethod.POST)
	public String generateOrderRequest(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		String uid = request.getParameter("uid");
		String tid = request.getParameter("tid");
		String ctype = new String(request.getParameter("ctype").getBytes("ISO-8859-1"), "UTF-8");
		String startCity = new String(request.getParameter("startCity").getBytes("ISO-8859-1"), "UTF-8");
		String endCity = new String(request.getParameter("endCity").getBytes("ISO-8859-1"), "UTF-8");
		String date = new String(request.getParameter("date").getBytes("ISO-8859-1"), "UTF-8");
		String nextTo = request.getParameter("nextTo");
		if (nextTo.equals("0")) {
			nextTo = "窗口";
		} else if (nextTo.equals("1")) {
			nextTo = "过道";
		} else {
			nextTo = "无";
		}
		
		context = new ClassPathXmlApplicationContext("Beans.xml");
		UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");
		ArrayList<User> userList = userJDBCTemplate.getUserByUid(uid);
		
		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");
		Train train = trainJDBCTemplate.getTrainByTid(tid);
		
		OrderJDBCTemplate orderJDBCTemplate = (OrderJDBCTemplate) context.getBean("OrderJDBCTemplate");
		HashMap<String, String> lockedSeats = orderJDBCTemplate.lockSeat(train.getTid(), date, ctype, nextTo,
				train.getStartIndex(), train.getEndIndex());

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long now = System.currentTimeMillis();
		String nowTime = dateFormat.format(now);

		Order order = new Order();
		order.setUid(uid);
		order.setPassengerId(request.getParameter("passengerId"));
		order.setPassengerName(request.getParameter("passengerName"));
		order.setTid(train.getTid());
		order.setCid(lockedSeats.get("cid"));
		order.setLocation(lockedSeats.get("location"));
		order.setStartSid(train.getStartIndex() + "");
		order.setEndSid(train.getEndIndex() + "");
		order.setDate(date);
		order.setCreatAt(nowTime);
		order = orderJDBCTemplate.addOrder(order);
		
		if (userList.size() == 1 && !userList.get(0).getStatus().equals("限制购票")) {
			model.addAttribute("user", userList.get(0));
			model.addAttribute("order", order);
			model.addAttribute("train", train);
			model.addAttribute("startCity", startCity);
			model.addAttribute("endCity", endCity);
			model.addAttribute("date", date);
			model.addAttribute("cid", Integer.parseInt(lockedSeats.get("cid")) + 1);
			model.addAttribute("location", lockedSeats.get("location"));
			return "payForTicket";
		} else if (userList.size() == 1 && userList.get(0).getStatus().equals("限制购票")) {
			return "statusError";
		} else {
			return "error";
		}
	}

	// 购票成功界面
	@RequestMapping(value = "/buyTicketSuccessful", method = RequestMethod.POST)
	public String buyTicketsSuccessfulPage(ModelMap model, HttpServletRequest request) {
		String uid = request.getParameter("uid");
		String oid = request.getParameter("oid");
		
		context = new ClassPathXmlApplicationContext("Beans.xml");
		OrderJDBCTemplate orderJDBCTemplate = (OrderJDBCTemplate) context.getBean("OrderJDBCTemplate");
		orderJDBCTemplate.getOrderByUid(uid);
		orderJDBCTemplate.updateOrderStatus(oid, "已付款");
		
		UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");
		ArrayList<User> userList = userJDBCTemplate.getUserByUid(uid);
		
		if (userList.size() == 1 && !userList.get(0).getStatus().equals("限制购票")) {
			model.addAttribute("user", userList.get(0));
			return "buyTicketSuccessful";
		} else if (userList.size() == 1 && userList.get(0).getStatus().equals("限制购票")) {
			return "statusError";
		} else {
			return "error";
		}
	}
}
