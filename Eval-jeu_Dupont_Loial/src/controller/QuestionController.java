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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response ) throws ServletException, IOException {
		QuestionBean model = QuestionBean.getInstence( req );

		model.initPartie();

		if ( model.getCalculCount() < 10 ) {
			model.initCalcul();
			req.getRequestDispatcher( PageFactory.getQuestJsp() ).forward(req, response);
		} else {
			response.sendRedirect( PageFactory.getHomePath(req) );
		}
	}

	@Override
	protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
		QuestionBean model = QuestionBean.getInstence( req );

		model.processForm( req );

		doGet( req, resp );
	}
}
