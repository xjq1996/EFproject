package com.ef.video.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ef.video.entity.User;
import com.ef.video.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("/find")
	public String find(){
		System.out.println("555555");
		User user=userService.findUserByEmail("1324789616@qq.com");
		if(user.getUsername()!=null)
			System.out.println(user.getEmail());
		return "login";
	}
}
