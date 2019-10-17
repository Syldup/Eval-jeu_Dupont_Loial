package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {
	
	@Override
	public void attributeAdded( HttpSessionBindingEvent se ) {
	
	}
	
	@Override
	public void attributeRemoved( HttpSessionBindingEvent se ) {
	
	}
	
	@Override
	public void attributeReplaced( HttpSessionBindingEvent se ) {
	
	}
}
