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

import model.train.Train;
import model.train.TrainJDBCTemplate;
import model.train.TrainSeats;
import model.train.TransferTrain;
import tools.HttpTools;
import tools.ResponseContent;

@Controller
public class TrainController {
	private ApplicationContext context;
	
	// 获取所有火车经过的城市
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getAllCities", method = RequestMethod.POST)
	public void getAllCities(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		context = new ClassPathXmlApplicationContext("Beans.xml");
		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");
		ArrayList<String> city = trainJDBCTemplate.getAllCities();
		
		String jsonOutput = JSON.toJSONString(city);
		ResponseContent responseContent = new ResponseContent("1000", jsonOutput, "successful");	
		HttpTools.responseTools(response, responseContent);
	}
	
	// 查询火车接口
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getTrainByStop", method = RequestMethod.POST)
	public void getTrainByStop(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String fromCity = new String(request.getParameter("fromCity"));
		String toCity = new String(request.getParameter("toCity"));
		String date = new String(request.getParameter("date"));
		
		context = new ClassPathXmlApplicationContext("Beans.xml");
		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");
		ArrayList<Train> trainList = trainJDBCTemplate.getTrainByStop(fromCity, toCity, date);
		
		String jsonOutput = JSON.toJSONString(trainList);
		ResponseContent responseContent = new ResponseContent("1000", jsonOutput, "successful");	
		HttpTools.responseTools(response, responseContent);
	}

	// 火车换乘接口
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getTransfer", method = RequestMethod.POST)
	public void getTransfer(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String fromCity = new String(request.getParameter("fromCity"));
		String toCity = new String(request.getParameter("toCity"));
		String date = new String(request.getParameter("date"));
		
		context = new ClassPathXmlApplicationContext("Beans.xml");
		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");
		ArrayList<TransferTrain> transfer = trainJDBCTemplate.getTrainTransfer(fromCity, toCity, date);
		
		String jsonOutput = JSON.toJSONString(transfer);
		ResponseContent responseContent = new ResponseContent("1000", jsonOutput, "successful");	
		HttpTools.responseTools(response, responseContent);
	}
	
	// 根据火车id查询火车
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getTrainByTrainId", method = RequestMethod.POST)
	public void getTrainByTrainId(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String trainId = new String(request.getParameter("trainId"));
		
		context = new ClassPathXmlApplicationContext("Beans.xml");
		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");
		Train train = trainJDBCTemplate.getTrainByTrainId(trainId);
		
		String jsonOutput = JSON.toJSONString(train);
		ResponseContent responseContent = new ResponseContent("1000", jsonOutput, "successful");	
		HttpTools.responseTools(response, responseContent);
	}
	
	// 余票查询
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getRemainSeats", method = RequestMethod.POST)
	public void getRemainSeats(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String trainId = new String(request.getParameter("trainId"));
		String date = new String(request.getParameter("date"));
		String fromStopId = new String(request.getParameter("fromStopId"));
		String toStopId = new String(request.getParameter("toStopId"));
		
		context = new ClassPathXmlApplicationContext("Beans.xml");
		TrainJDBCTemplate trainJDBCTemplate = (TrainJDBCTemplate) context.getBean("TrainJDBCTemplate");
		ArrayList<TrainSeats> seats = trainJDBCTemplate.getRemainingSeats(trainId, date, fromStopId, toStopId);

		String jsonOutput = JSON.toJSONString(seats);
		ResponseContent responseContent = new ResponseContent("1000", jsonOutput, "successful");	
		HttpTools.responseTools(response, responseContent);
	}
}
