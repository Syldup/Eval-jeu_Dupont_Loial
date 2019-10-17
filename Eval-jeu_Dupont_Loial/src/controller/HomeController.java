package controller;

import bo.Person;
import bo.User;
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

@WebServlet(urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

	private static final String PAGE_HOME_JSP = "/WEB-INF/jsp/persons_list.jsp";
	private static final String PAGE_QUEST_JSP = "/WEB-INF/jsp/question.jsp";
	private static final Logger LOGGER = Logger.getLogger( HomeController.class.getName() );

	public HomeController() {
		LOGGER.log( Level.INFO, "Je suis dans le constructeur !" );
	}

}
