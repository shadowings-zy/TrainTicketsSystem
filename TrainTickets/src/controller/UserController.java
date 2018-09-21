package controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import model.user.User;
import model.user.UserJDBCTemplate;

@Controller
public class UserController {
	private ApplicationContext context;

	// 个人设置-查看个人设置
	@RequestMapping(value = "/setting", method = RequestMethod.POST)
	public ModelAndView settingPage(ModelMap model, HttpServletRequest request) {
		String uid = new String(request.getParameter("uid"));

		context = new ClassPathXmlApplicationContext("Beans.xml");
		UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");
		ArrayList<User> userList = userJDBCTemplate.getUserByUid(uid);
		
		if (userList.size() == 1 && !userList.get(0).getStatus().equals("限制购票")) {
			ModelAndView settingView = new ModelAndView("setting");
			model.addAttribute("user", userList.get(0));
			return settingView;
		} else if (userList.size() == 1 && userList.get(0).getStatus().equals("限制购票")) {
			ModelAndView statusErrorView = new ModelAndView("statusError");
			return statusErrorView;
		} else {
			ModelAndView errorView = new ModelAndView("error");
			return errorView;
		}
	}

	// 更改个人设置请求
	@RequestMapping(value = "/userSetting", method = RequestMethod.POST)
	public ModelAndView setUserInfoRequest(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		String uid = new String(request.getParameter("uid"));
		String username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
		String idcard = new String(request.getParameter("idcard").getBytes("ISO-8859-1"), "UTF-8");
		String tel = new String(request.getParameter("tel").getBytes("ISO-8859-1"), "UTF-8");
		String password = new String(request.getParameter("password").getBytes("ISO-8859-1"), "UTF-8");

		context = new ClassPathXmlApplicationContext("Beans.xml");
		UserJDBCTemplate userJDBCTemplate = (UserJDBCTemplate) context.getBean("UserJDBCTemplate");

		User user = new User();
		user.setUid(uid);
		user.setUname(username);
		user.setIdcard(idcard);
		user.setTel(tel);
		user.setPassword(password);
		user.setStatus("正常");
		userJDBCTemplate.updateUser(uid, user);

		ModelAndView settingView = new ModelAndView("setting");
		model.addAttribute("user", user);
		return settingView;
	}
}
