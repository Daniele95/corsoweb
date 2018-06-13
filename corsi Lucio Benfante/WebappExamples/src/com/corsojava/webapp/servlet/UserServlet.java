package com.corsojava.webapp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corsojava.webapp.dao.DaoFactory;
import com.corsojava.webapp.dao.UserDao;
import com.corsojava.webapp.model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao userDao;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
    }

	@Override
	public void init() throws ServletException {
		DaoFactory daoFactory = (DaoFactory) this.getServletContext().getAttribute("daoFactory");
		this.userDao = daoFactory.getUserDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		User user = null;
		if (id != null) {
			user = this.userDao.findById(Integer.parseInt(id));
		} else {
			user = new User();
		}
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/jsp/user/edit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String age = request.getParameter("age");
		User user = new User();
		user.setId(Integer.parseInt(id));
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAge(Integer.parseInt(age));
		this.userDao.save(user);
		request.getSession(true).setAttribute("message", "Aggiunto/modificato user ("+user.getId()+")");
		response.sendRedirect("/WebappExamples/users");
	}
	
}
