package DAO;

import java.util.List;
import model.User;

public interface UserDAO {
	List<User> findAll();
	User findId(int it);
	User save(User user);
	List<User> getUserDb();
}
