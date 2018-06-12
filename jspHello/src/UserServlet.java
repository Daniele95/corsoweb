import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;


@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public UserServlet() {
        super();
    }

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// creo una nuova lista di utenti nel momento in cui la pagina del form è caricata
		List<User> users = (List<User>) this.getServletContext().getAttribute("userDb");
		if(users == null) {
			users = new ArrayList<>();
		}
		save(users);
		
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
		
		List<User> users = (List<User>) this.getServletContext().getAttribute("userDb");
		if (user != null)
			users.add(user);
		save(users);
		response.sendRedirect("/jspHello/users");
	}

	private void save(List<User> users) {
		this.getServletContext().setAttribute("userDb", users);
	}

}
