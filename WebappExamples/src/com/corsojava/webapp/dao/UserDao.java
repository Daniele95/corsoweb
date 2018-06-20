package com.corsojava.webapp.dao;

import java.util.List;

import com.corsojava.webapp.model.User;

public interface UserDao {
	List<User> findAll();
	User findById(int id);
	User save(User user);
}
