package DAO.context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import DAO.UserDAO;
import model.User;

public class ContextUserDAO implements UserDAO {

	private ServletContext context;
	
	public ContextUserDAO(ServletContext context) {
		this.context = context;
	}
	
	@Override
	public User findUserById(int id) {
		List<User> users = getUserDb();
		Iterator<User> it = users.iterator();
		while(it.hasNext()) {
			User dbUser = (User)it.next();
			if(dbUser.getId() == id )
				return dbUser;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int generateId() {
		List<User> users = (List<User>) this.context.getAttribute("userDb");
		if (!users.isEmpty())
			return users.size();
		else
			return 0;
	}
	
	public void resetIndices() {
		List<User> users = getUserDb();
		Iterator<User> it = users.iterator();
		int i = 0;
		while(it.hasNext()) {
			User dbUser = (User)it.next();
			dbUser.setId(i);
			i++;
		}
		this.context.setAttribute("userDb", users);
	}
	
	@Override
	public List<User> findAll() {
		return this.getUserDb();
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUserDb() {
		List<User> users = (List<User>) this.context.getAttribute("userDb");
		if(users == null) {
			users = new ArrayList<>();
			this.context.setAttribute("userDb",users);
		}
		return users;
	}
	
	@Override
	public void save(List<User> users) {
		this.context.setAttribute("userDb",users);
	}

}
