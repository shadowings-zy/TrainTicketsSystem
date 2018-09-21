package test;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.order.Order;
import model.order.OrderJDBCTemplate;
import model.train.Train;
import model.train.TrainJDBCTemplate;
import model.train.TrainSeats;
import model.train.TransferTrain;
import model.user.User;
import model.user.UserJDBCTemplate;

// 测试类，包含必要的接口数据
public class SQLTest {
	private static ApplicationContext context;

	public static void main(String[] args) {
//		userLogInTest();
//		userRegisterTest();
//		updateUserTest();

//		getAllCitiesTest();
//		getTrainByStopTest();
//		getTransferTrainTest();
//		getTrainByTidTest();
//		getRemainingSeatsTest();

//		addOrderTest();
//		queryOrderTest();
//		updateOrderTest();
//		deleteOrderTest();
	}

	public static void userLogInTest() {
		context = new ClassPathXmlApplicationContext("Beans.xml");
		UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");

		System.out.println("用户登录");
		ArrayList<User> userList = userJDBCTemplate.getUserByUsernameAndPassword("user3", "123");
		if (userList.size() == 1 && !userList.get(0).getStatus().equals("限制购票")) {
			System.out.println("登录成功");
			System.out.println(userList.get(0).toString());
		} else if (userList.size() == 1 && userList.get(0).getStatus().equals("限制购票")) {
			System.out.println("您被限制购票");
		} else {
			System.out.println("登录失败，您输入的账号或密码可能有误");
		}
	}

	public static void userRegisterTest() {
		context = new ClassPathXmlApplicationContext("Beans.xml");
		UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");

		System.out.println("用户注册");

		User user = new User();
		user.setIdcard("6");
		user.setTelephone("6");
		user.setUserName("user6");
		user.setPassword("123");
		user.setStatus("正常");

		ArrayList<User> userList = userJDBCTemplate.getUserByIdcard(user.getIdcard());
		if (userList.size() == 0) {
			System.out.println("添加用户");
			User newUser = userJDBCTemplate.addUser(user);
			System.out.println(newUser.toString());
		} else {
			System.out.println("此身份证号已被注册");
		}
	}

	public static void updateUserTest() {
		context = new ClassPathXmlApplicationContext("Beans.xml");
		UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");

		System.out.println("修改用户");

		User user = new User();
		user.setIdcard("3");
		user.setTelephone("3");
		user.setUserName("user3");
		user.setPassword("123");
		user.setStatus("限制购票");

		User newUser = userJDBCTemplate.updateUser(user);
		System.out.println("修改后的结果");
		System.out.println(newUser.toString());
	}

	public static void getAllCitiesTest() {
		System.out.println("查询所有城市");
		context = new ClassPathXmlApplicationContext("Beans.xml");
		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");

		ArrayList<String> city = trainJDBCTemplate.getAllCities();
		System.out.println(city);
	}

	public static void getTrainByStopTest() {
		System.out.println("根据经停信息获取火车信息");
		context = new ClassPathXmlApplicationContext("Beans.xml");
		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");

		ArrayList<Train> trainList = trainJDBCTemplate.getTrainByStop("济南", "沈阳", "2019-01-01");
		if (!trainList.isEmpty()) {
			for (int a = 0; a < trainList.size(); a++) {
				System.out.println(trainList.get(a).toString());
			}
		} else {
			System.out.println("无火车信息");
		}
	}

	public static void getTransferTrainTest() {
		System.out.println("根据经停信息获取火车换乘信息");
		context = new ClassPathXmlApplicationContext("Beans.xml");
		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");
		ArrayList<TransferTrain> transfer = trainJDBCTemplate.getTrainTransfer("广州", "长春", "2019-01-01");

		if (!transfer.isEmpty()) {
			for (int a = 0; a < transfer.size(); a++) {
				System.out.println(transfer.get(a).getFirstTid() + " " + transfer.get(a).getSecondTid() + " "
						+ transfer.get(a).getFirstTname() + " " + transfer.get(a).getSecondTname() + " "
						+ transfer.get(a).getTransferStation());
			}
		} else {
			System.out.println("无换乘");
		}
	}

	public static void getTrainByTidTest() {
		System.out.println("获取火车信息");
		context = new ClassPathXmlApplicationContext("Beans.xml");
		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");

		Train train = trainJDBCTemplate.getTrainByTrainId("1");
		System.out.println(train.getTrainName());
		for (int a = 0; a < train.getStopInfo().size(); a++) {
			System.out.println(train.getStopInfo().get(a).getStationName());
		}
		for (int a = 0; a < train.getSeatsInfo().size(); a++) {
			System.out.println(
					train.getSeatsInfo().get(a).getCarriage() + " " + train.getSeatsInfo().get(a).getSeatLocation());
		}
	}

	public static void getRemainingSeatsTest() {
		System.out.println("余票查询");
		context = new ClassPathXmlApplicationContext("Beans.xml");
		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");

		ArrayList<TrainSeats> seats = trainJDBCTemplate.getRemainingSeats("5", "2019-01-02", "1", "5");
		int first = 0;
		int second = 0;
		for (int a = 0; a < seats.size(); a++) {
			System.out.println(seats.get(a).toString());
			if (seats.get(a).getSeatType().equals("一等座")) {
				first++;
			} else if (seats.get(a).getSeatType().equals("二等座")) {
				second++;
			}
		}
		System.out.println("一等座" + first + "个，二等座" + second + "个。");
	}

	public static void addOrderTest() {
		context = new ClassPathXmlApplicationContext("Beans.xml");
		OrderJDBCTemplate orderJDBCTemplate = (OrderJDBCTemplate) context.getBean("OrderJDBCTemplate");

		System.out.println("添加订单");
		Order order = new Order();
		order.setUserId("1");
		order.setPassengerId("1");
		order.setUserName("user1");
		order.setTrainId("1");
		order.setTrainName("G1");
		order.setCarriage("1");
		order.setSeatType("一等座");
		order.setSeatId("3");
		order.setSeatLocation("01F");
		order.setStartTime("12:00:00");
		order.setStartStopId("0");
		order.setStartStationName("沈阳北");
		order.setEndStopId("2");
		order.setEndStationName("深圳北");
		order.setDate("2019-01-01");
		order.setCreateAt("2018-12-31");
		order.setStatus("未付款");

		Order newOrder = orderJDBCTemplate.addOrder(order);
		System.out.println(newOrder.toString());
	}

	public static void queryOrderTest() {
		context = new ClassPathXmlApplicationContext("Beans.xml");
		OrderJDBCTemplate orderJDBCTemplate = (OrderJDBCTemplate) context.getBean("OrderJDBCTemplate");

		System.out.println("查询订单");
		ArrayList<Order> orderList = orderJDBCTemplate.getOrderByIdcard("000000000000000001");
		for (int a = 0; a < orderList.size(); a++) {
			System.out.println(orderList.get(a).toString());
		}
	}

	public static void updateOrderTest() {
		context = new ClassPathXmlApplicationContext("Beans.xml");
		OrderJDBCTemplate orderJDBCTemplate = (OrderJDBCTemplate) context.getBean("OrderJDBCTemplate");

		System.out.println("修改订单状态");
		Order order = new Order();
		order.setOrderId("1");
		order.setUserId("1");
		order.setPassengerId("1");
		order.setUserName("user1123");
		order.setTrainId("1");
		order.setTrainName("G1");
		order.setCarriage("1");
		order.setSeatType("一等座");
		order.setSeatId("3");
		order.setSeatLocation("01F");
		order.setStartTime("12:00:00");
		order.setStartStopId("0");
		order.setStartStationName("沈阳北");
		order.setEndStopId("2");
		order.setEndStationName("深圳北");
		order.setDate("2019-01-01");
		order.setCreateAt("2018-12-31 12:00:00");
		order.setStatus("未付款");
		orderJDBCTemplate.updateOrder("1", order);
	}

	public static void deleteOrderTest() {
		context = new ClassPathXmlApplicationContext("Beans.xml");
		OrderJDBCTemplate orderJDBCTemplate = (OrderJDBCTemplate) context.getBean("OrderJDBCTemplate");

		System.out.println("删除订单");
		orderJDBCTemplate.deleteOrderByOrderId("195");
	}
}
