package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.order.Order;
import model.order.OrderJDBCTemplate;
import model.train.Train;
import model.train.TrainCarriage;
import model.train.TrainJDBCTemplate;
import model.train.TrainSeats;
import model.train.TrainStop;
import model.train.TransferTrain;
import model.user.User;
import model.user.UserJDBCTemplate;

public class SQLTest {
	private static ApplicationContext context;

	public static void main(String[] args) {
//		addTrain();
//		addTrainType();
//		getTrainByTid();
		getTrainByStop();
//		getTidByTname();
//		getTransferTrain();
//		getRemainingSeats();
//		userLogIn();
//		queryOrder();
//		getAllCities();
	}
	
	public static void addUser() {
		context = new ClassPathXmlApplicationContext("Beans.xml");
	    UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate)context.getBean("UserJDBCTemplate");

	    System.out.println("添加用户");
	    User user = new User();
	    user.setIdcard("000000000000000003");
	    user.setTel("00000000003");
	    user.setUname("user3");
	    user.setPassword("user3");
	    userJDBCTemplate.addUser(user);
	}
	
	public static void updateUser() {
		context = new ClassPathXmlApplicationContext("Beans.xml");
	    UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate)context.getBean("UserJDBCTemplate");
	    
	    System.out.println("修改用户"); 
	    User user = new User();
	    user.setIdcard("000000000000000002");
	    user.setTel("00000000002");
	    user.setUname("user2");
	    user.setPassword("password2");
	    userJDBCTemplate.updateUser("1", user);
	}
	
	public static void deleteUser() {
		context = new ClassPathXmlApplicationContext("Beans.xml");
	    UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate)context.getBean("UserJDBCTemplate");
	    
	    System.out.println("删除用户");
	    userJDBCTemplate.deleteUser("2");
	}
	
	public static void userLogIn() {
		context = new ClassPathXmlApplicationContext("Beans.xml");
	    UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate)context.getBean("UserJDBCTemplate");
	    
	    System.out.println("用户登陆");
	    ArrayList<User> userList = userJDBCTemplate.getUserByUnameAndPassword("admin", "admin");
	    System.out.println(userList.size());
	    
	}

	public static void addOrder() {
		context = new ClassPathXmlApplicationContext("Beans.xml");
	    OrderJDBCTemplate orderJDBCTemplate = (OrderJDBCTemplate)context.getBean("OrderJDBCTemplate");

	    System.out.println("添加订单");
	    Order order = new Order();
	    order.setUid("1");
	    order.setTid("G1272");
	    order.setStartSid("沈阳");
	    order.setEndSid("济南");
	    order.setDate("2018-1-1 12:00:00");
	    order.setCreatAt("2018-1-1 12:00:00");
	    order.setStatus("未付款");
	    orderJDBCTemplate.addOrder(order);
	}
	
	public static void queryOrder() {
		context = new ClassPathXmlApplicationContext("Beans.xml");
	    OrderJDBCTemplate orderJDBCTemplate = (OrderJDBCTemplate)context.getBean("OrderJDBCTemplate");
	    
	    System.out.println("查询订单");
	    ArrayList<Order> orderList = orderJDBCTemplate.getOrderByUid("1");
	    System.out.println(orderList.get(0).getTname());
	    System.out.println(orderList.get(0).getFromCity());
	    System.out.println(orderList.get(0).getToCity());
	}
	
	public static void updateOrderStatus() {
		context = new ClassPathXmlApplicationContext("Beans.xml");
	    OrderJDBCTemplate orderJDBCTemplate = (OrderJDBCTemplate)context.getBean("OrderJDBCTemplate");
	    
	    System.out.println("修改订单状态");
	    orderJDBCTemplate.updateOrderStatus("2", "已付款");
	}
	
	public static void deleteOrder() {
		context = new ClassPathXmlApplicationContext("Beans.xml");
	    OrderJDBCTemplate orderJDBCTemplate = (OrderJDBCTemplate)context.getBean("OrderJDBCTemplate");
	    
	    System.out.println("删除订单");
	    orderJDBCTemplate.deleteOrder("2");
	}
	
	public static void addTrainType() {
		System.out.println("新建一种车型");
		context = new ClassPathXmlApplicationContext("Beans.xml");
		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");

		ArrayList<TrainCarriage> trainCarriage = new ArrayList<TrainCarriage>();
		List<String> carriageType = Arrays.asList("硬座", "硬座", "硬座", "硬座", "硬座", "硬座", "餐车", "硬卧", "硬卧", "硬卧", "硬卧",
				"硬卧", "软卧");
		List<String> carriageCapacity = Arrays.asList("118", "118", "118", "118", "118", "118", "0", "60", "60", "60",
				"60", "60", "32");
		for (int a = 0; a < carriageType.size(); a++) {
			TrainCarriage carriage = new TrainCarriage();
			carriage.setCtype(carriageType.get(a));
			carriage.setCapacity(carriageCapacity.get(a));
			trainCarriage.add(carriage);
		}
		trainJDBCTemplate.addTrainCarriage("快速列车", trainCarriage);
	}

	public static void addTrain() {
		System.out.println("新建一辆列车");
		context = new ClassPathXmlApplicationContext("Beans.xml");
		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");

		ArrayList<TrainStop> trainStop = new ArrayList<TrainStop>();
		List<String> stopCity = Arrays.asList("北京", "武汉", "长沙", "贵阳", "昆明");
		List<String> stopStation = Arrays.asList("北京南", "武汉", "长沙南", "贵阳北", "昆明南");
		List<String> arriveTime = Arrays.asList("11:33", "17:12", "19:21", "20:23", "22:17");
		List<String> stopTime = Arrays.asList("0", "2", "2", "2", "0");
		List<String> mileage = Arrays.asList("0", "50", "100", "150", "200");
		for (int a = 0; a < stopCity.size(); a++) {
			TrainStop stop = new TrainStop();
			stop.setCity(stopCity.get(a));
			stop.setStation(stopStation.get(a));
			stop.setArriveTime(arriveTime.get(a));
			stop.setStopTime(stopTime.get(a));
			stop.setMileage(mileage.get(a));
			trainStop.add(stop);
		}

		Train train = new Train();
		train.setTname("G407");
		train.setType("高速动车组");
		train.setStopInfo(trainStop);

		trainJDBCTemplate.addTrain(train);

	}
	
	public static void getTrainByTid() {
		System.out.println("获取火车信息");
		context = new ClassPathXmlApplicationContext("Beans.xml");
		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");
		
		Train train = trainJDBCTemplate.getTrainByTid("4");
		System.out.println(train.getTname());
		for(int a = 0;a<train.getStopInfo().size();a++) {
			System.out.println(train.getStopInfo().get(a).getStation());
		}
	}
	
	public static void getTrainByStop() {
		System.out.println("根据经停信息获取火车信息");
		context = new ClassPathXmlApplicationContext("Beans.xml");
		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");
		
		ArrayList<Train> trainList = trainJDBCTemplate.getTrainByStop("南京", "北京", "2018-09-01");
		for(int a = 0;a<trainList.size();a++) {
			System.out.println(trainList.get(a).getTname() + " " + trainList.get(a).getStart() + " " + trainList.get(a).getEnd());
		}
	}
	
	public static void getTransferTrain() {
		System.out.println("根据经停信息获取火车换乘信息");
		context = new ClassPathXmlApplicationContext("Beans.xml");
		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");
		ArrayList<TransferTrain> transfer = trainJDBCTemplate.getTrainTransfer("杭州", "济南", "2018-2-2");
		
		System.out.println(transfer.get(0).getFirstTid());
		System.out.println(transfer.get(0).getSecondTid());
		System.out.println(transfer.get(0).getFirstTname());
		System.out.println(transfer.get(0).getSecondTname());
		System.out.println(transfer.get(0).getTransferStation());
	}
	
	public static void getRemainingSeats() {
		System.out.println("余票查询");
		context = new ClassPathXmlApplicationContext("Beans.xml");
		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");
		
		ArrayList<TrainSeats> seats = trainJDBCTemplate.getRemainingSeats("4", "2018-1-1","1","2");
		System.out.println(seats.get(0).getCtype());
		System.out.println(seats.get(0).getSeatsCount());
	}
	
	public static void getAllCities() {
		System.out.println("查询所有城市");
		context = new ClassPathXmlApplicationContext("Beans.xml");
		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");
		
		ArrayList<String> city = trainJDBCTemplate.getAllCities();
		System.out.println(city);
	}
}
