package com.lzy.web.controller.annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user2")//�����Ŀ¼
public class User2Controller {	
	
	@RequestMapping("/addUser") //��ָ��method=RequestMethod.GET �� GET POST �����Է���
	public String addUser(HttpServletRequest request, HttpServletResponse arg1){
		System.out.println("addUser----------�Ż���");
		String result = "this is addUser...�Ż���";
		request.setAttribute("result", result);//������
		return "/annotation";//���ؽ���
	}
	
	@RequestMapping("/delUser")
	public String delUser(HttpServletRequest request){
		System.out.println("delUser----------�Ż���");
		String result = "this is delUser...�Ż���";
		request.setAttribute("result", result);
		return "/annotation";
	}

}
