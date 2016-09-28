package com.lzy.web.controller.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {	
	
	@RequestMapping(value="/user/addUser",method=RequestMethod.GET)
	public ModelAndView addUser(){
		System.out.println("addUser-------------");
		String result = "this is addUser....";
		return new ModelAndView("/annotation","result",result);
	}
	
	@RequestMapping(value="/user/delUser",method=RequestMethod.GET)
	public ModelAndView delUser(){
		System.out.println("delUser-------------");
		String result = "this is delUser....";
		return new ModelAndView("/annotation","result",result);
	}

}
