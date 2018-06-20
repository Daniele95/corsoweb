package com.corsojava.webapp.dao.context;

import javax.servlet.ServletContext;

import com.corsojava.webapp.dao.DaoFactory;
import com.corsojava.webapp.dao.UserDao;

public class ContextDaoFactory implements DaoFactory {
	private ServletContext context;
	
	public ContextDaoFactory(ServletContext context) {
		this.context = context;
	}
	
	@Override
	public UserDao getUserDao() {
		return new ContextUserDao(context);
	}

}
