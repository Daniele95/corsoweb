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


@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    private String id;
	
    public UserServlet() {
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
		
		// creo una nuova lista di utenti nel momento in cui la pagina del form è caricata
		List<User> users = this.userDAO.findAll();
		this.id = request.getParameter("id");
		
		request.setAttribute("userDb", users);
		request.getRequestDispatcher("WEB-INF/jsp/user/edit.jsp").forward(request,response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// aggiungo alla lista un utente
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String age = request.getParameter("age");
		
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		if (age!= "")
			user.setAge(Integer.parseInt(age));
		
		List<User> users = (List<User>) this.userDAO.findAll();
		if (user != null) {
			if(this.id==null) {
				user.setId( this.userDAO.generateId());
				users.add(user);
			}
			else {
				user.setId(Integer.parseInt(this.id));
				users.set(Integer.parseInt(this.id),user);
			}
		}
		
		this.userDAO.save(users);		
		response.sendRedirect("/jspHello/users");
		request.getSession(true).setAttribute("message", "aggiunto / modificato user "+user.getId());
		
	}


}
