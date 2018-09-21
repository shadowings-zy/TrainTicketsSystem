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

import model.user.User;
import model.user.UserJDBCTemplate;
import tools.HttpTools;
import tools.ResponseContent;

@Controller
public class UserController {
	private ApplicationContext context;
	
	// 登录接口
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String username = new String(request.getParameter("username"));
		String password = new String(request.getParameter("password"));

		context = new ClassPathXmlApplicationContext("Beans.xml");
		UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");
		ArrayList<User> userList = userJDBCTemplate.getUserByUsernameAndPassword(username, password);
		
		if (userList.size() == 1 && !userList.get(0).getStatus().equals("限制购票")) {
			String jsonOutput = JSON.toJSONString(userList.get(0));
			ResponseContent responseContent = new ResponseContent("1000", jsonOutput, "successful");
			HttpTools.responseTools(response, responseContent);
		} else if (userList.size() == 1 && userList.get(0).getStatus().equals("限制购票")) {
			ResponseContent responseContent = new ResponseContent("1001", "限制购票", "successful but limited");
			HttpTools.responseTools(response, responseContent);
		} else {
			ResponseContent responseContent = new ResponseContent("2000", "出现错误", "error");
			HttpTools.responseTools(response, responseContent);
		}
	}
	
	// 注册接口
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void register(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String idcard = new String(request.getParameter("idcard"));
		String password = new String(request.getParameter("password"));
		String telephone = new String(request.getParameter("telephone"));
		String username = new String(request.getParameter("username"));

		context = new ClassPathXmlApplicationContext("Beans.xml");
	    UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate)context.getBean("UserJDBCTemplate");

	    User user = new User();
	    user.setIdcard(idcard);
	    user.setTelephone(telephone);
	    user.setUserName(username);
	    user.setPassword(password);
	    user.setStatus("正常");
	    
	    ArrayList<User> userList = userJDBCTemplate.getUserByIdcard(user.getIdcard());
	    if (userList.size() == 0) {
		    User newUser = userJDBCTemplate.addUser(user);
		    String jsonOutput = JSON.toJSONString(newUser);
			ResponseContent responseContent = new ResponseContent("1000", jsonOutput, "successful");
			HttpTools.responseTools(response, responseContent);
	    } else {
	    	ResponseContent responseContent = new ResponseContent("1001", "限制购票", "successful but limited");
			HttpTools.responseTools(response, responseContent);
	    }
	}
	
	// 修改用户信息接口
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public void updateUser(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String idcard = new String(request.getParameter("idcard"));
		String password = new String(request.getParameter("password"));
		String telephone = new String(request.getParameter("telephone"));
		String username = new String(request.getParameter("username"));
		String status = new String(request.getParameter("status"));

		context = new ClassPathXmlApplicationContext("Beans.xml");
	    UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate)context.getBean("UserJDBCTemplate");
	    
	    User user = new User();
	    user.setIdcard(idcard);
	    user.setTelephone(telephone);
	    user.setUserName(username);
	    user.setPassword(password);
	    user.setStatus(status);
	    
	    User newUser = userJDBCTemplate.updateUser(user);
	    String jsonOutput = JSON.toJSONString(newUser);
		ResponseContent responseContent = new ResponseContent("1000", jsonOutput, "successful");
		HttpTools.responseTools(response, responseContent);
	}
}
