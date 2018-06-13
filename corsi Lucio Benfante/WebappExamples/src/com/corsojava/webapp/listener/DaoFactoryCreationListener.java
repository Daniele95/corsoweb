package com.corsojava.webapp.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.corsojava.webapp.dao.DaoFactory;
import com.corsojava.webapp.dao.context.ContextDaoFactory;

/**
 * Application Lifecycle Listener implementation class DaoFactoryCreationListener
 *
 */
@WebListener
public class DaoFactoryCreationListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public DaoFactoryCreationListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext sc = sce.getServletContext();
		DaoFactory daoFactory = new ContextDaoFactory(sc);
		sc.setAttribute("daoFactory", daoFactory);
    }
	
}
