package com.ef.video.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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

import com.ef.video.service.UserService;
import com.ef.video.util.MD5Util;

@Controller
//@RequestMapping("/login")
public class LoginController {
	@RequestMapping(value="/login")
public String login(@RequestParam("username") String username,@RequestParam("password") String password,Model model){
		boolean rememberMe = false;
		String md5Pwd = MD5Util.generatePassword(password);	
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(username, md5Pwd, rememberMe);
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			return "login";
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
		return "index";
}

}
