package com.ef.video.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ef.video.entity.User;
import com.ef.video.service.UserService;
import com.ef.video.util.MD5Util;

@Controller
//@RequestMapping("/login")
public class LoginController {
	@Autowired
	private UserService userService;
	@RequestMapping(value="/login")
public String login1(@RequestParam("sno") String sno,@RequestParam("password") String password,Model model){
		boolean rememberMe = false;
		String md5Pwd = MD5Util.generatePassword(password);	
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(sno, md5Pwd, rememberMe);
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			return "users/main";
		} catch (LockedAccountException lae) {
		    System.out.println("账号已被禁用");
			model.addAttribute("msg", "账号已被禁用");
		} catch (AuthenticationException ae) {
//			ae.printStackTrace();
			System.out.println("账号或密码错误");
			model.addAttribute("msg", "账号或密码错误");
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("登录异常");
			model.addAttribute("msg", "登录异常");
		}
		return "users/login_fail";
}
	@RequestMapping("/logout")
	public ModelAndView logout(){
		ModelAndView mav=new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		mav.setViewName("index");
		mav.addObject("user", null);
		return mav;
		
	}
	@RequestMapping(value="/login1")
	public String login(@RequestParam("sno") String sno,@RequestParam("password") String password,Model model){
	   User user=userService.findUserBySno(sno);
	   System.out.println(MD5Util.generatePassword(password)+"----------------登录密码------"+user.getPassword());
	   if(user!=null){
		if(user.getStatus()==0||user.getStatus()==){
			model.addAttribute("msg", "账号未激活");
			return "users/login_fail";
		}
		if(user.getPassword().equals(MD5Util.generatePassword(password))){
			model.addAttribute("user", user);
			
			return "users/main";
		}
		else{
			model.addAttribute("msg", "密码错误！");
			return "users/login_fail";
		}
	   }
	   else{
		   model.addAttribute("msg", "用户不存在");
		   return "users/login_fail";
	   }
	}

}
