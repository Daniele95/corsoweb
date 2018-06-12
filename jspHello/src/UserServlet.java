import java.io.IOException;
import java.util.ArrayList;
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
	
    public UserServlet() {
        super();
    }

	@Override
	// questo cambierà se metterò i dati in un database invece che nell'attuale ServletContext
	public void init() throws ServletException {
        // questo potrebbe essere un contextDAOFActory, ma anche un mySqlDAOFactory,
        // insomma qualsiasi DAOFactory!! è questa la comodità,
        // il livello Servlet non si preoccupa di chi crea l'oggetto DAO e cioè di che forma abbiano i dati sottostanti!!
        // questa la potenza dell'INTERFACCIA
		DAOFactory daoFactory = (DAOFactory) this.getServletContext().getAttribute("daoFactory");
		// così riduco la concorrenza, costruisco un solo utente DAO (così non devo 
		// chiamare più volte la daoFactory, che può essere costoso)
		this.userDAO = daoFactory.getUserDao();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// creo una nuova lista di utenti nel momento in cui la pagina del form è caricata
		
		/*List<User> users = (List<User>) this.getServletContext().getAttribute("userDb");
		if(users == null) {
			users = new ArrayList<>();
		}
		save(users);*/
		
		List<User> users = this.userDAO.getUserDb();
		//	this.userDAO.save(new User());
		
		request.setAttribute("userDb", users);
		request.getRequestDispatcher("WEB-INF/jsp/user/edit.jsp").forward(request,response);
	}
	

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// aggiungo alla lista un utente
		String id = request.getParameter("id");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String age = request.getParameter("age");
		
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAge(Integer.parseInt(age));
		
		List<User> users = (List<User>) this.userDAO.findAll();
		//List<User> users = (List<User>) this.getServletContext().getAttribute("userDb");
		if (user != null)
			users.add(user);
		
		// cambio, e mi collego al database daofactory
		this.userDAO.save(user);
		// invece che salvarli nell'attributo userDb, definito solo su questa sessione
		// save(users);
		response.sendRedirect("/jspHello/users");
		request.getSession(true).setAttribute("message", "aggiunto modificato user "+user.getId());
	}

	private void save(List<User> users) {
		this.getServletContext().setAttribute("userDb", users);
	}

}
