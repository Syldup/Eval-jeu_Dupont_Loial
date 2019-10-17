package controller;

import bo.Calcul;
import bo.Partie;
import model.CalculBean;
import model.PartieBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/question"})
public class QuestionController extends HttpServlet {

	private static final String PAGE_HOME_JSP = "/persons_list";
	private static final String PAGE_QUEST_JSP = "/WEB-INF/jsp/question.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response ) throws ServletException, IOException {

		Partie partie = PartieBean.getPartieFromSession( req );

		Calcul calcul = CalculBean.newCalcul(partie);
		req.setAttribute(CalculBean.ATT_CALCUL_SESSION , calcul);

		if ( partie.getCalculs().size() <= 10 )
			req.getRequestDispatcher( PAGE_QUEST_JSP ).forward( req, response );
		else
			response.sendRedirect( req.getContextPath() + PAGE_HOME_JSP );
	}

	@Override
	protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
		if (req.getParameter( CalculBean.FORM_FIELD_REPONCE ) != null) {
			Calcul calcul = CalculBean.getFromRequest(req);
			CalculBean.save(calcul);
		}
		doGet( req, resp );
	}
}
