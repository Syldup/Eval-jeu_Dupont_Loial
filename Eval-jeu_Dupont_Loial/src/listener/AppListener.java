package listener;

import dal.DAOFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class AppListener implements ServletContextListener {
	
	private static final Logger LOGGER = Logger.getLogger( AppListener.class.getName() );
	
	@Override
	public void contextInitialized( ServletContextEvent sce ) {
		LOGGER.log( Level.INFO, "Initialisation du contexte applicatif... " );
		try {
			DAOFactory.init( sce.getServletContext() );
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed( ServletContextEvent sce ) {
		LOGGER.log( Level.INFO, "Desctruction du contexte applicatif... " );
	}
}
