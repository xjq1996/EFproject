package com.ef.video.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ef.video.entity.Profession;
import com.ef.video.entity.User;
import com.ef.video.service.ProfessionService;
import com.ef.video.service.UserService;
import com.ef.video.util.MD5Util;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ProfessionService professionService;
	@RequestMapping("/findByEmail")
	public String findByEmail(HttpServletRequest request,Model model){
		String email= request.getParameter("email");
		User user =userService.findUserByEmail(email);
		model.addAttribute("user", user);
		return "login";
	}
	public String findBySno(HttpServletRequest request,Model model){
		String sno=request.getParameter("sno");
		if(userService.findUserBySno(sno)!=null);
		return "";
	}
	@RequestMapping("/save")
	public String save(HttpServletRequest request){
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
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setGender(gender);
		user.setCreateDate(new Date());
		userService.save(user);
		return "login";
	}
}
