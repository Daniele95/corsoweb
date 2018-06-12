package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import DAO.DAOFactory;
import DAO.context.ContextDAOFactory;

@WebListener
public class DAOFactoryCreationListener implements ServletContextListener {

    public DAOFactoryCreationListener() {
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    }

    // l'unico modo per rendere un oggetto disponibile a tutta l'applicazione web
    // è metterlo come attributo del contesto
    public void contextInitialized(ServletContextEvent sce)  {
    	ServletContext sc = sce.getServletContext();
    	// l'uso del new riduce la flessibilità dell'applicazione!!
    	// in un framework più avanzato, tipo Spring lo faremmo creare a Spring, o in CDI
    	DAOFactory daoFactory = new ContextDAOFactory(sc);
    	sc.setAttribute("daoFactory", daoFactory);
    }
	
}