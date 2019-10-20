package controller;


import model.HomeBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet( urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

	private static final String PAGE_HOME_JSP = "/WEB-INF/jsp/home.jsp";
	private static final String PAGE_QUEST = "/question";


	@Override
	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		HomeBean model=HomeBean.getInstence(request);

			 try {
				 model.dispListTopPlayer(request);
				 request.getRequestDispatcher( PAGE_HOME_JSP ).forward( request, response );
			 } catch (SQLException e) {
				 e.printStackTrace();
			 }

	}
}
