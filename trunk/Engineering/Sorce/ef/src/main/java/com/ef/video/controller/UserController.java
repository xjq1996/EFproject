package com.ef.video.controller;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ef.video.entity.Profession;
import com.ef.video.entity.User;
import com.ef.video.service.ProfessionService;
import com.ef.video.service.UserService;
import com.ef.video.util.MD5Util;
import com.ef.video.util.MailUtil;
import com.ef.video.util.MapUtil;
import com.ef.video.util.ValidateUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ProfessionService professionService;

	@RequestMapping("/findByEmail")
	public String findByEmail(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		User user = userService.findUserByEmail(email);
		model.addAttribute("user", user);
		return "users/login";
	}

	@RequestMapping("/save")
	public String save(HttpServletRequest request, Model model) {
		// String username=request.getParameter("username");
		String password = MD5Util.generatePassword(request.getParameter("password"));
		// String professionId =request.getParameter("profession");
		String sno = request.getParameter("sno");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		Profession profession = professionService.findById("1");
		User user = new User();
		user.setSno(sno);
		// 设置默认专业 用户名
		user.setProfession(profession);
		user.setUsername(sno);
		user.setPassword(password);
		user.setEmail(email);
		user.setGender(gender);
		user.setCreateDate(new Date());
		if (userService.findUserBySno(sno) != null) {
			model.addAttribute("msg", "用户已经存在");
			System.out.println("2222222222");
			return "users/register";
		}
		String text = "您好，您正在注册EF！！";
		if (userService.save(user) != null) {
			HashMap<String, User> map = MapUtil.getInstence().getMap();
			String serial = ValidateUtil.number();
			map.put(serial, user);
			MailUtil.sendemail(user.getEmail(), text, serial);
			if (userService.findUserBySno(user.getSno()) != null) {
				model.addAttribute("msg", "注册成功");
				return "users/login";
			}

		}
		model.addAttribute("msg", "注册失败,请重新注册");
		return "users/register";
	}

	@RequestMapping("/modify")
	public ModelAndView modify(@RequestParam String sno, @RequestParam String email) {
		User user = userService.findUserBySno(sno);
		ModelAndView mav = new ModelAndView();
		if (user != null) {
			if (!email.equals(user.getEmail())) {
				mav.addObject("msg", "用户名与邮箱不匹配！");
				mav.setViewName("users/forget");
				return mav;
			}
			HashMap<String, User> map = MapUtil.getInstence().getMap();
			String serial = ValidateUtil.number();
			map.put(serial, user);
			MailUtil.sendemail(email, "修改", serial);
			mav.addObject("msg", "已发送验证到你的邮箱");
			mav.addObject("user", user);
			mav.addObject("serial", serial);
			mav.addObject("user", user);
			mav.setViewName("users/modify");
		} else {
			mav.addObject("msg", "用户名不存在，请重新输入");
			mav.setViewName("users/forget");
		}
		return mav;
	}

	@RequestMapping("/update")
	public String update(@RequestParam String sno, @RequestParam String password, @RequestParam String serial) {
		User user = userService.findUserBySno(sno);
		HashMap<String, User> map = MapUtil.getInstence().getMap();
		System.out.println("update   serial======" + serial + "-------------update---------------------------");
		System.out.println(user.getId() + "**********update" + user.getPassword());
		System.out.println("--update-----delete before" + user.getId()+"----"+MD5Util.generatePassword(password));
		userService.delete(user);
		user.setPassword(MD5Util.generatePassword(password));
		System.out.println("---update----delete after" + user.getId());
		user.setStatus(0);
		userService.save(user);
		System.out.println(user.getId() + "**********update" + user.getPassword());
		System.out.println(userService.findUserBySno(user.getSno()));
		map.put(serial, user);
		System.out.println("update   serial======" + serial + "-------------update---------------------------");
		return "users/login";

	}

	@RequestMapping("/validator")
	public String reply(@RequestParam("serial") String serial) {
		System.out.println("---validator------------------------------------------");
		User user = validate(serial);
		HashMap<String, User> map = MapUtil.getInstence().getMap();
        
		if (user != null) {
			MailUtil.sendemail(user.getEmail(), "注册成功", serial);
			User u = userService.findUserBySno(user.getSno());
			if(u != null) {
				userService.delete(u);
			}
			if (userService.findUserBySno(user.getSno()) == null) {
				u.setStatus(1);
				userService.save(u);
				map.remove(serial);
				System.out.println("---validator------------------------------------------");
				return "users/login";
			}
			return "users/login_fail";
		}
		return "index";
	}

	private User validate(String serial) {
		User user = null;
		HashMap<String, User> map = MapUtil.getInstence().getMap();
		if (map.keySet().contains(serial)) {
			user = map.get(serial);
		}
		return user;

	}

}
