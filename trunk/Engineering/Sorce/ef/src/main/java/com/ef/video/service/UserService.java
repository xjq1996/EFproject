package com.ef.video.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ef.video.dao.UserDao;
import com.ef.video.entity.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
public User findUserByUsername(String username){
	return userDao.findUserByUsername(username);
}
public User findUserByEmail(String email){
	return userDao.findUserByEmail(email);
}
public User add(User user ){
	return  null;
}
}
