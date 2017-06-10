package com.ef.video.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ef.video.service.UserService;
import com.ef.video.util.MD5Util;

@Controller
//@RequestMapping("/login")
public class LoginController {
	@Autowired
private UserService userService;
	@RequestMapping(value="/login")
public String login(HttpServletRequest request,Model model){
	String username=request.getParameter("username");
	String password=MD5Util.generatePassword(request.getParameter("password"));
	 if(MD5Util.generatePassword("123").equals(password)){
		System.out.println("mima错了");
		return "login";
	}
	else
		System.out.println("用户名错了");
		return "index";
}

}
