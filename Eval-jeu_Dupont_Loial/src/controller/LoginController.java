package controller;

import model.LoginBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( urlPatterns = {"/login"} )
public class LoginController extends HttpServlet {
	
	private static final String PAGE_LOGIN_JSP = "/WEB-INF/jsp/login.jsp";
	private static final String PAGE_HOME = "/home";
	
	@Override
	protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

		LoginBean model = (LoginBean) req.getAttribute( "loginBean" );
		if (model == null)
			model = new LoginBean();
		req.setAttribute("loginBean", model);
		if ( model.isConnected( req ) ) {
			resp.sendRedirect( req.getContextPath() + PAGE_HOME );
		} else {
			req.getRequestDispatcher( PAGE_LOGIN_JSP ).forward( req, resp );
		}
	}
	
	@Override
	protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

		LoginBean model = new LoginBean();
	 	model.authenticate( req );
		req.setAttribute( "loginBean", model );


		model.suscribe(req);
		req.setAttribute("loginBean",model);
		doGet( req, resp );
	}
}
