package com.corsojava.webapp.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HelloServlet
 */
// @WebServlet(urlPatterns = { "/hello" }, initParams = {
// @WebInitParam(name = "defaultName", value = "Lucio", description = "dfsdfsd")
// })
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HelloServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logCookies(request);
		String name = request.getParameter("name");
		HttpSession session = request.getSession(true);
		if (name == null) {
			name = (String) session.getAttribute("name");
			if (name == null) {
				name = this.getServletConfig().getInitParameter("defaultName");
			}
		} else {
			session.setAttribute("name", name);
			Cookie cookie = new Cookie("myName", name);
			cookie.setMaxAge(20*60);
			response.addCookie(cookie);
		}
		request.setAttribute("user", name);
		request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void logCookies(HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				this.log(c.getName() + " " + c.getValue() + " " + c.getPath() + " " + c.getMaxAge());
			}
		}
	}
}
