package com.corsojava.webapp.dao.context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import com.corsojava.webapp.dao.UserDao;
import com.corsojava.webapp.model.User;

public class ContextUserDao implements UserDao {
	private ServletContext context;

	public ContextUserDao(ServletContext context) {
		this.context = context;
	}
	
	@Override
	public List<User> findAll() {
		return this.getUserDb();
	}

	@Override
	public User findById(int id) {
		List<User> users = getUserDb();
		Iterator<User> it = users.iterator();
		while (it.hasNext()) {
			User dbUser = (User) it.next();
			if (dbUser.getId() == id) {
				return dbUser;
			}
		}
		return null;
	}

	@Override
	public User save(User user) {
		List<User> users = getUserDb();
		User dbUser = findById(user.getId());
		if (dbUser != null) {
			dbUser.setFirstName(user.getFirstName());
			dbUser.setLastName(user.getLastName());
			dbUser.setAge(user.getAge());
			return dbUser;
		} else {
			user.setId(generateId());
			users.add(user);
			return user;
		}
	}
	

	private int generateId() {
		List<User> users = getUserDb();
		int maxId = 0;
		for (User user: users) {
			if (user.getId() > maxId) {
				maxId = user.getId();
			}
		}
		return maxId+1;
	}
	
	private List<User> getUserDb() {
		List<User> users = (List<User>) this.context.getAttribute("userDb");
		if (users == null) {
			users = new ArrayList<>();
			this.context.setAttribute("userDb", users);
		}
		return users;
	}
	
}
