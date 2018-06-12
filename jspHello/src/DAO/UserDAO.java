package DAO;

import java.util.List;
import model.User;


public interface UserDAO {
	List<User> findAll();
	User findUserById(int id);
	void save(List<User> users);
	int generateId();
	void resetIndices();
}
