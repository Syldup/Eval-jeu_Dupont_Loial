package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class SessionListener implements HttpSessionListener {
	private static final Logger LOGGER = Logger.getLogger( SessionListener.class.getName() );
	private int counter;
	@Override
	public void sessionCreated( HttpSessionEvent se ) {
		LOGGER.log( Level.INFO, "Création d'une nouvelle session - Il y a {0} session(s) courante(s)", ++counter );
	}
	
	@Override
	public void sessionDestroyed( HttpSessionEvent se ) {
		LOGGER.log( Level.INFO, "Création d'une session - Il y a {0} session(s) courante(s)", --counter );
	}
}
