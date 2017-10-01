package com.ef.video.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ef.video.entity.User;
import com.ef.video.service.ProfessionService;
import com.ef.video.service.RoleService;
import com.ef.video.service.UserService;

@Controller
@RequestMapping("/normal")
public class NormalController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ProfessionService professionService;
	@RequestMapping("/user/main")
	public ModelAndView main_video(HttpServletRequest request){
		String sno=request.getParameter("sno");
		ModelAndView mav=new ModelAndView();
		User user=userService.findUserBySno(sno);
		if(user!=null){
			Integer score=user.getScore();
		if(score>=0){
			user.setScore(score-50);
			userService.save(user);
			mav.setViewName("web/normal/video/main");
		}else {
			mav.addObject("msg", "积分不足，无法观看");
			mav.setViewName("web/normal/video/recharge");
		}
		}else{
			mav.addObject("msg", "用户不存在");
			mav.setViewName("web/normal/fail");
		}
		return mav;
		
	}
}
