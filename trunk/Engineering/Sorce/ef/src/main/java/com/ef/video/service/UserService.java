package com.ef.video.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ef.video.dao.UserDao;
import com.ef.video.entity.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
public User findUserBySno(String sno){
	return userDao.findUserBySno(sno);
}

public User findUserByEmail(String email){
	return userDao.findUserByEmail(email);
}
public User save(User user ){
	return userDao.save(user);
}
public User findUserByName(String username) {
	return userDao.findUserByName(username);
}

public boolean update(User user) {
	System.out.println("UserDao---------"+user.getPassword()+"----"+user.getSno());
   return userDao.update(user.getSno());	
}

public void delete(User user) {
	userDao.delete(user.getId());
}
}
