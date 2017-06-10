package com.ef.video.dao;

import org.springframework.data.jpa.repository.Query;
import com.ef.video.entity.User;

public interface UserDao extends CommonDao<User,String> {
	@Query("select u from User u where u.username=?1")
public User findUserByUsername(String username);
	@Query("select u from User u where u.email=?1")
	public User findUserByEmail(String email);
}
