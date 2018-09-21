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

import model.train.Train;
import model.train.TrainJDBCTemplate;
import model.train.TrainStop;
import model.user.User;
import model.user.UserJDBCTemplate;

@Controller
public class AdminController {
	private ApplicationContext context;
	// 管理-查看车次
	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String adminPage(ModelMap model, HttpServletRequest request) {
		String uid = request.getParameter("uid");
		
		context = new ClassPathXmlApplicationContext("Beans.xml");
		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");
		ArrayList<Train> allTrains = trainJDBCTemplate.getAllTrains();
		
		UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");
		ArrayList<User> userList = userJDBCTemplate.getUserByUid(uid);
		
		if (userList.size() == 1 && userList.get(0).getStatus().equals("管理员")) {
			model.addAttribute("user", userList.get(0));
			model.addAttribute("allTrains", allTrains);
			return "admin";
		} else if (userList.size() == 1 && userList.get(0).getStatus().equals("正常")) {
			model.addAttribute("user", userList.get(0));
			return "notAdmin";
		} else if (userList.size() == 1 && userList.get(0).getStatus().equals("限制购票")){
			return "statusError";
		} else {
			return "error";
		}
	}

	// 设置用户身份
	@RequestMapping(value = "/setUserStatus", method = RequestMethod.POST)
	public String setUserStatusRequest(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		String uid = request.getParameter("uid");
		String userId = new String(request.getParameter("userId").getBytes("ISO-8859-1"), "UTF-8");
		String status = new String(request.getParameter("userStatus").getBytes("ISO-8859-1"), "UTF-8");

		context = new ClassPathXmlApplicationContext("Beans.xml");
		UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");
		userJDBCTemplate.setUserStatus(userId, status);
		ArrayList<User> userList = userJDBCTemplate.getUserByUid(uid);
		
		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");
		ArrayList<Train> allTrains = trainJDBCTemplate.getAllTrains();

		if (userList.size() == 1 && userList.get(0).getStatus().equals("管理员")) {
			model.addAttribute("user", userList.get(0));
			model.addAttribute("allTrains", allTrains);
			return "admin";
		} else if (userList.size() == 1 && userList.get(0).getStatus().equals("正常")) {
			model.addAttribute("user", userList.get(0));
			return "notAdmin";
		} else if (userList.size() == 1 && userList.get(0).getStatus().equals("限制购票")){
			return "statusError";
		} else {
			return "error";
		}
	}

	// 显示火车详细信息
	@RequestMapping(value = "/trainDetail", method = RequestMethod.POST)
	public String trainDetailPage(ModelMap model, HttpServletRequest request)
			throws UnsupportedEncodingException {
		String uid = request.getParameter("uid");
		String tid = new String(request.getParameter("tid").getBytes("ISO-8859-1"), "UTF-8");

		context = new ClassPathXmlApplicationContext("Beans.xml");
		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");
		Train train = trainJDBCTemplate.getTrainByTid(tid);
		
		context = new ClassPathXmlApplicationContext("Beans.xml");
		UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");
		ArrayList<User> userList = userJDBCTemplate.getUserByUid(uid);

		if (userList.size() == 1 && userList.get(0).getStatus().equals("管理员")) {
			model.addAttribute("user", userList.get(0));
			model.addAttribute("train", train);
			return "trainDetail";
		} else if (userList.size() == 1 && userList.get(0).getStatus().equals("正常")) {
			model.addAttribute("user", userList.get(0));
			return "notAdmin";
		} else if (userList.size() == 1 && userList.get(0).getStatus().equals("限制购票")){
			return "statusError";
		} else {
			return "error";
		}
	}

	// 修改火车详细信息页面
	@RequestMapping(value = "/updateTrainDetail", method = RequestMethod.POST)
	public String updateTrainDetailPage(ModelMap model, HttpServletRequest request)
			throws UnsupportedEncodingException {
		String uid = request.getParameter("uid");
		String tid = request.getParameter("tid");
		String sid = new String(request.getParameter("sid").getBytes("ISO-8859-1"), "UTF-8");
		String city = new String(request.getParameter("city").getBytes("ISO-8859-1"), "UTF-8");
		String station = new String(request.getParameter("station").getBytes("ISO-8859-1"), "UTF-8");
		String arriveTime = new String(request.getParameter("arriveTime").getBytes("ISO-8859-1"), "UTF-8");
		String stopTime = new String(request.getParameter("stopTime").getBytes("ISO-8859-1"), "UTF-8");
		String mileage = new String(request.getParameter("mileage").getBytes("ISO-8859-1"), "UTF-8");

		context = new ClassPathXmlApplicationContext("Beans.xml");
		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");
		Train train = trainJDBCTemplate.getTrainByTid(tid);
		
		TrainStop stop = new TrainStop();
		stop.setCity(city);
		stop.setStation(station);
		stop.setArriveTime(arriveTime);
		stop.setStopTime(stopTime);
		stop.setMileage(mileage);
		trainJDBCTemplate.updateTrainStop(train.getTid(), sid, stop);
		train.getStopInfo().set(Integer.parseInt(sid), stop);
		
		context = new ClassPathXmlApplicationContext("Beans.xml");
		UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");
		ArrayList<User> userList = userJDBCTemplate.getUserByUid(uid);
		
		if (userList.size() == 1 && userList.get(0).getStatus().equals("管理员")) {
			model.addAttribute("user", userList.get(0));
			model.addAttribute("train", train);
			return "trainDetail";
		} else if (userList.size() == 1 && userList.get(0).getStatus().equals("正常")) {
			model.addAttribute("user", userList.get(0));
			return "notAdmin";
		} else if (userList.size() == 1 && userList.get(0).getStatus().equals("限制购票")){
			return "statusError";
		} else {
			return "error";
		}
	}

	// 删除火车
	@RequestMapping(value = "/deleteTrain", method = RequestMethod.POST)
	public String deleteTrainRequest(ModelMap model, HttpServletRequest request) {
		String uid = request.getParameter("uid");
		String tid = request.getParameter("tid");
		
		UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");
		ArrayList<User> userList = userJDBCTemplate.getUserByUid(uid);
		
		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");
		trainJDBCTemplate.deleteTrain(tid);
		ArrayList<Train> allTrains = trainJDBCTemplate.getAllTrains();

		if (userList.size() == 1 && userList.get(0).getStatus().equals("管理员")) {
			model.addAttribute("user", userList.get(0));
			model.addAttribute("allTrains", allTrains);
			return "admin";
		} else if (userList.size() == 1 && userList.get(0).getStatus().equals("正常")) {
			model.addAttribute("user", userList.get(0));
			return "notAdmin";
		} else if (userList.size() == 1 && userList.get(0).getStatus().equals("限制购票")){
			return "statusError";
		} else {
			return "error";
		}
	}

	// 添加火车界面
	@RequestMapping(value = "/addTrain", method = RequestMethod.POST)
	public String addTrainPage(ModelMap model, HttpServletRequest request) {
		String uid = request.getParameter("uid");
		System.out.println(uid);
		
		UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");
		ArrayList<User> userList = userJDBCTemplate.getUserByUid(uid);
		
		if (userList.size() == 1 && userList.get(0).getStatus().equals("管理员")) {
			model.addAttribute("user", userList.get(0));
			return "addTrain";
		} else if (userList.size() == 1 && userList.get(0).getStatus().equals("正常")) {
			model.addAttribute("user", userList.get(0));
			return "notAdmin";
		} else if (userList.size() == 1 && userList.get(0).getStatus().equals("限制购票")){
			return "statusError";
		} else {
			return "error";
		}
	}

	// 添加火车界面
	@RequestMapping(value = "/addNewTrain", method = RequestMethod.POST)
	public String addNewTrainPage(ModelMap model, HttpServletRequest request) throws UnsupportedEncodingException {
		String uid = request.getParameter("uid");
		Train newTrain = new Train();
		ArrayList<TrainStop> stopList = new ArrayList<TrainStop>();

		String tname = new String(request.getParameter("tname").getBytes("ISO-8859-1"), "UTF-8");
		String type = new String(request.getParameter("type").getBytes("ISO-8859-1"), "UTF-8");
		int stopNum = Integer.parseInt(request.getParameter("stop"));

		for (int a = 0; a < stopNum; a++) {
			if (request.getParameter("city" + a) != null && request.getParameter("station" + a) != null
					&& request.getParameter("arriveTime" + a) != null && request.getParameter("stopTime" + a) != null
					&& request.getParameter("mileage" + a) != null) {
				TrainStop stop = new TrainStop();
				String city = new String(request.getParameter("city" + a).getBytes("ISO-8859-1"), "UTF-8");
				String station = new String(request.getParameter("station" + a).getBytes("ISO-8859-1"), "UTF-8");
				String arriveTime = new String(request.getParameter("arriveTime" + a).getBytes("ISO-8859-1"), "UTF-8");
				String stopTime = new String(request.getParameter("stopTime" + a).getBytes("ISO-8859-1"), "UTF-8");
				String mileage = new String(request.getParameter("mileage" + a).getBytes("ISO-8859-1"), "UTF-8");

				stop.setCity(city);
				stop.setStation(station);
				stop.setArriveTime(arriveTime);
				stop.setStopTime(stopTime);
				stop.setMileage(mileage);

				stopList.add(stop);
			}
		}

		newTrain.setTname(tname);
		newTrain.setType(type);
		newTrain.setStopInfo(stopList);

		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");
		trainJDBCTemplate.addTrain(newTrain);
		
		UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");
		ArrayList<User> userList = userJDBCTemplate.getUserByUid(uid);

		if (userList.size() == 1 && userList.get(0).getStatus().equals("管理员")) {
			model.addAttribute("user", userList.get(0));
			return "addTrain";
		} else if (userList.size() == 1 && userList.get(0).getStatus().equals("正常")) {
			model.addAttribute("user", userList.get(0));
			return "notAdmin";
		} else if (userList.size() == 1 && userList.get(0).getStatus().equals("限制购票")){
			return "statusError";
		} else {
			return "error";
		}
	}
}
