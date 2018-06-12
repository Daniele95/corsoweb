package DAO.context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import DAO.UserDAO;
import model.User;

// qua dentro metto tutti i metodi che accedono ai miei dati


public class ContextUserDAO implements UserDAO {

	private ServletContext context;
	
	public ContextUserDAO( ServletContext context) {
		this.context = context;
	}	
	
	@Override
	public List<User> findAll() {
		return this.getUserDb();	
	}

	@Override
	public User findId(int it) {
		return null;
	}
	
	private User findUserById(int id) {
		List<User>  users = getUserDb();
		Iterator<User> it = users. iterator();
		while(it.hasNext()) {
			User dbUser = (User)it.next();
			if(dbUser.getId() == id )
				return dbUser;
		}	
		return null;
	}

	
	public List<User> getUserDb() {
		List<User> users = (List<User>) this.context.getAttribute("userDb");
		if(users == null) {
			users = new ArrayList<>();
			this.context.setAttribute("userDb",users);
		}
		return null;
	}
	
	private int generateId(User user) {
		List<User> users = getUserDb();
		User dbUser = findId(user.getId());
		return dbUser.getId();
	}
	
	@Override
	public User save(User user) {
		List<User> users = getUserDb();
		User dbUser = findId(user.getId());
		if(dbUser != null) {
			dbUser.setFirstName(user.getFirstName());
			dbUser.setLastName(user.getLastName());
			dbUser.setAge(user.getAge());			
			return dbUser;
		} else {
			user.setId(user.getId());
		}
		return dbUser;
	}

}
