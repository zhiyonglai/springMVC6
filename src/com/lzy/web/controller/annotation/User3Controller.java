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
@RequestMapping("/user/data")//�����Ŀ¼
public class User3Controller {	
	
	@RequestMapping("/addUser") //��ָ��method=RequestMethod.GET �� GET POST �����Է���
	public String addUser(User user,HttpServletRequest request){
		System.out.println("userName="+user.getUserName() +"  ,password=" + user.getPassword());
		request.setAttribute("userName", user.getUserName());//������
		request.setAttribute("password", user.getPassword());//������
		return "/userManager";//���ؽ���
	}
	
	@RequestMapping("/delUser")
	public String delUser(HttpServletRequest request){
		System.out.println("delUser----------�Ż���");
		String result = "this is delUser...�Ż���";
		request.setAttribute("result", result);
		return "/annotation";
	}
	
	@RequestMapping("/toUser")
	public String toUser(HttpServletRequest request){
		System.out.println("toUser----------�Ż���");
		return "/json";
	}
	
	@RequestMapping("/addUserJson") //��ָ��method=RequestMethod.GET �� GET POST �����Է���
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
		
//		return "/json";//���ؽ���
	}
	@RequestMapping("/toImg")
	public String toImg(HttpServletRequest request){
		System.out.println("toUser----------�Ż���");
		return "/staticFile";
	}
}
