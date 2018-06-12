import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAOFactory;
import DAO.UserDAO;
import model.User;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    private String idToRemove;
    
    public UsersServlet() {
        super();
    }

	@Override
	public void init() throws ServletException {
		DAOFactory daoFactory = (DAOFactory) this.getServletContext().getAttribute("daoFactory");
		this.userDAO = daoFactory.getUserDao();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		List<User> users = this.userDAO.findAll();		
		request.setAttribute("userDb", users);
		request.getRequestDispatcher("/WEB-INF/jsp/user/list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> users = this.userDAO.findAll();
		
		this.idToRemove = request.getParameter("remove");		
		if(this.idToRemove==null) {}
		else {
			users.remove(Integer.parseInt(idToRemove));
			this.userDAO.resetIndices();
		}
		
		request.setAttribute("userDb", users);
		request.getRequestDispatcher("/WEB-INF/jsp/user/list.jsp").forward(request, response);
		}


}
