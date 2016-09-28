package com.lzy.web.controller.annotation;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lzy.web.controller.entity.User;

@Controller
@RequestMapping("/user/data")//请求根目录
public class User3Controller {	
	
	@RequestMapping("/addUser") //不指定method=RequestMethod.GET 则 GET POST 都可以访问
	public String addUser(User user,HttpServletRequest request){
		System.out.println("userName="+user.getUserName() +"  ,password=" + user.getPassword());
		request.setAttribute("userName", user.getUserName());//传参数
		request.setAttribute("password", user.getPassword());//传参数
		return "/userManager";//返回界面
	}
	
	@RequestMapping("/delUser")
	public String delUser(HttpServletRequest request){
		System.out.println("delUser----------优化版");
		String result = "this is delUser...优化版";
		request.setAttribute("result", result);
		return "/annotation";
	}
	
	@RequestMapping("/toUser")
	public String toUser(HttpServletRequest request){
		System.out.println("toUser----------优化版");
		return "/json";
	}
	
	@RequestMapping("/addUserJson") //不指定method=RequestMethod.GET 则 GET POST 都可以访问
	public void addUserJson(User user,HttpServletRequest request,HttpServletResponse response){
		
		String result = "{\"userName\":\" "+user.getUserName()+" \",\"password\":\" "+user.getPassword()+" \"}";
		
		PrintWriter out=null;
		response.setContentType("application/json");
		try {
			out = response.getWriter();
			out.write(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		return "/json";//返回界面
	}
	@RequestMapping("/toImg")
	public String toImg(HttpServletRequest request){
		System.out.println("toUser----------优化版");
		return "/staticFile";
	}
}
