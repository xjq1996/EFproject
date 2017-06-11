package com.ef.video.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ef.video.entity.Profession;
import com.ef.video.entity.User;
import com.ef.video.service.ProfessionService;
import com.ef.video.service.UserService;
import com.ef.video.util.MD5Util;

@RequestMapping("/user/register")
public class RegisterController {
	@Autowired
	private ProfessionService professionService;
	@Autowired
	private UserService userService;
public String Register(HttpServletRequest request,Model model){
	String username=request.getParameter("username");
	String password=MD5Util.generatePassword(request.getParameter("password"));
	String professionId =request.getParameter("profession");
	String sno=request.getParameter("sno");
	String email= request.getParameter("email");
	String gender=request.getParameter("gender");
	Profession profession=professionService.findById(professionId);
	User user=new User();
	user.setSno(sno);
	user.setProfession(profession);;
	//user.setUsername(username);
	user.setPassword(password);
	user.setEmail(email);
	user.setGender(gender);
	//user.setCreateDate(new Date(new java.util.Date().getTime()));
	if(userService.save(user)!=null)
	return "login";
	return "index";
}
}
