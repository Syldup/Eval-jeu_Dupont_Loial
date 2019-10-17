package controller;

import bo.Person;
import model.PersonBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet( name = "personController", urlPatterns = {"/persons"})
public class PersonController extends HttpServlet {
	
	private static final String PAGE_LIST_JSP = "/WEB-INF/jsp/persons_list.jsp";
	private static final String PAGE_EDIT_JSP = "/WEB-INF/jsp/person_details.jsp";
	private static final Logger LOGGER = Logger.getLogger( PersonController.class.getName() );
	private int counter;
	
	public PersonController() {
		LOGGER.log( Level.INFO, "Je suis dans le constructeur !" );
	}
	
	@Override
	public void init() throws ServletException {
		LOGGER.log( Level.INFO, "Je suis dans le init !" );
		super.init();
	}
	
	@Override
	public void destroy() {
		LOGGER.log( Level.INFO, "Je suis dans le destroy !" );
		super.destroy();
	}
	
	@Override
	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		
		
		LOGGER.log( Level.INFO, "Je suis dans le GET !" );
		
		HttpSession session = request.getSession( true );
		
		
		List<Person> dataSession = ( List<Person> ) session.getAttribute( "persons" );
		
		if ( null == dataSession ) {
			dataSession = new ArrayList<>();
			dataSession.add( new Person( 0, "Séga", "S", 500 ) );
			dataSession.add( new Person( 1, "Lass", "S", 1000 ) );
			session.setAttribute( "persons", dataSession );
		}
		
		String action = request.getParameter( "action" );
		if ( null == action ) {
			request.getRequestDispatcher( PAGE_LIST_JSP ).forward( request, response );
		} else {
			int id;
			try {
				id = Integer.parseInt( request.getParameter( "id" ) );
			} catch ( Exception e ) {
				id = -1;
			}
			switch ( action ) {
				case "details":
					Person currentPerson;
					if ( id >= 0 && id < dataSession.size() ) {
						currentPerson = dataSession.get( id );
					} else {
						currentPerson = new Person();
					}
					request.setAttribute( "currentPerson", currentPerson );
					request.getRequestDispatcher( PAGE_EDIT_JSP ).forward( request, response );
					break;
				default:
					response.sendRedirect( request.getContextPath() + "/persons" );
			}
		}
	}
	
	@Override
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		LOGGER.log( Level.INFO, "Je suis dans le POST !" );
		if ( "DELETE".equals( request.getParameter( "form-method" ) ) ) {
			doDelete( request, response );
		} else {
			
			HttpSession session = request.getSession( true );
			List<Person> dataSession = ( List<Person> ) session.getAttribute( "persons" );
			
			PersonBean model = new PersonBean();
			model.setPersonFromForm( request );
			
			int id = model.getPerson().getId();
			if ( id >= 0 && id < dataSession.size() ) {
				Person currentPerson = dataSession.get( id );
				currentPerson.setName( model.getPerson().getName() );
				currentPerson.setEmail( model.getPerson().getEmail() );
				currentPerson.setSalary( model.getPerson().getSalary() );
			} else {
				model.getPerson().setId( dataSession.size() );
				dataSession.add( model.getPerson() );
			}
			response.sendRedirect( request.getContextPath() + "/persons" );
		}
	}
	
	@Override
	protected void doDelete( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		LOGGER.log( Level.INFO, "Je suis dans le DELETE !" );
		HttpSession session = request.getSession( true );
		List<Person> dataSession = ( List<Person> ) session.getAttribute( "persons" );
		
		int id;
		try {
			id = Integer.parseInt( request.getParameter( "form-id" ) );
		} catch ( Exception e ) {
			id = -1;
		}
		if ( id >= 0 && id < dataSession.size() ) {
			dataSession.remove( id );
		}
		response.sendRedirect( request.getContextPath() + "/persons" );
	}
}
