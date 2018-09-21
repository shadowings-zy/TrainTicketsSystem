package tools;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class HttpTools {
	public static void responseTools (HttpServletResponse response, ResponseContent content) {
		response.setContentType("text/plain;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		String jsonContent = JSON.toJSONString(content);
		try {
			response.getWriter().write(jsonContent);
		} catch (IOException e) {
			System.out.println("error:" + e.toString());
		}
	}
}
