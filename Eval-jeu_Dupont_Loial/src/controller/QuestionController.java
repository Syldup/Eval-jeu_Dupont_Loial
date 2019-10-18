package controller;

import model.QuestionBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( urlPatterns = {"/question"})
public class QuestionController extends HttpServlet {

	private static final String PAGE_QUEST_JSP = "/WEB-INF/jsp/question.jsp";
	private static final String PAGE_HOME = "/home";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response ) throws ServletException, IOException {
		QuestionBean model = QuestionBean.getInstence( req );

		model.initPartie();

		if ( model.getCalculCount() < 10 ) {
			model.initCalcul();
			req.getRequestDispatcher(PAGE_QUEST_JSP).forward(req, response);
		} else
			response.sendRedirect( req.getContextPath() + PAGE_HOME );
	}

	@Override
	protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
		QuestionBean model = QuestionBean.getInstence( req );

		model.processForm( req );

		doGet( req, resp );
	}
}
