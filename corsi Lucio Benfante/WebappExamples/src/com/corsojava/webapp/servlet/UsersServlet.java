package com.corsojava.webapp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.corsojava.webapp.dao.DaoFactory;
import com.corsojava.webapp.dao.UserDao;
import com.corsojava.webapp.model.User;

/**
 * Servlet implementation class UsersServlet
 */
@WebServlet("/users")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao userDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersServlet() {
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
		request.setAttribute("users", this.userDao.findAll());
		request.getRequestDispatcher("/WEB-INF/jsp/user/list.jsp").forward(request, response);		
	}

}
