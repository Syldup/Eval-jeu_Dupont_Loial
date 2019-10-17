package controller;

import bo.Calcul;
import bo.Partie;
import bo.Person;
import dal.DAOFactory;
import model.CalculBean;
import model.LoginBean;
import model.PartieBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/question"})
public class QuestionController extends HttpServlet {

	private static final String PAGE_HOME_JSP = "/WEB-INF/jsp/persons_list.jsp";
	private static final String PAGE_QUEST_JSP = "/WEB-INF/jsp/question.jsp";
	private static final Logger LOGGER = Logger.getLogger( QuestionController.class.getName() );

	public QuestionController() {
		LOGGER.log( Level.INFO, "Je suis dans le constructeur !" );
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response ) throws ServletException, IOException {

		Partie partie = PartieBean.getPartieFromSession( req );

		Calcul calcul = CalculBean.newCalcul(partie);
		req.setAttribute(CalculBean.ATT_CALCUL_SESSION , calcul);

		if ( partie.getCalculs().size() <= 10 )
			req.getRequestDispatcher( PAGE_QUEST_JSP ).forward( req, response );
		else
			response.sendRedirect( req.getContextPath()+PAGE_HOME_JSP );
	}

	@Override
	protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

		Calcul calcul = CalculBean.getCalculFromRequest( req );
		CalculBean.save(calcul);
		doGet( req, resp );
	}
}
