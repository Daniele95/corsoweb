package DAO.context;

import javax.servlet.ServletContext;

import DAO.DAOFactory;
import DAO.UserDAO;

public class ContextDAOFactory implements DAOFactory {

	private ServletContext context;
	
	public ContextDAOFactory(ServletContext context) {
		this.context = context;
	}
	
	@Override
	public UserDAO getUserDao() {
		return new ContextUserDAO(context);
	}

}
