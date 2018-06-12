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
     
    public UsersServlet() {
        super();
        // nota bene: errore: a questo punto il contesto ancora non esiste! 
        // sto creando la servlet, sono all'inizio dell'applicazione
        // quindi non posso mettere qui la daoFactory
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

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		List<User> users =(List<User>)this.getServletContext().getAttribute("userDb");
	//	request.setAttribute("userDb", users);
		request.setAttribute("userDb", this.userDAO.findAll());
		request.getRequestDispatcher("/WEB-INF/jsp/user/list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
