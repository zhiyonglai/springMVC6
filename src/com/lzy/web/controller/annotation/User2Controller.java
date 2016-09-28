package com.lzy.web.controller.annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user2")//请求根目录
public class User2Controller {	
	
	@RequestMapping("/addUser") //不指定method=RequestMethod.GET 则 GET POST 都可以访问
	public String addUser(HttpServletRequest request, HttpServletResponse arg1){
		System.out.println("addUser----------优化版");
		String result = "this is addUser...优化版";
		request.setAttribute("result", result);//传参数
		return "/annotation";//返回界面
	}
	
	@RequestMapping("/delUser")
	public String delUser(HttpServletRequest request){
		System.out.println("delUser----------优化版");
		String result = "this is delUser...优化版";
		request.setAttribute("result", result);
		return "/annotation";
	}

}
