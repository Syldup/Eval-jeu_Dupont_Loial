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
	
	@Override
	protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
		LoginBean model = LoginBean.getInstence( req );

		if ( model.isConnected() )
			resp.sendRedirect( PageFactory.getHomePath( req ) );
		else
			req.getRequestDispatcher( PageFactory.getLoginJsp() ).forward( req, resp );
	}
	
	@Override
	protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
		LoginBean model = LoginBean.getInstence( req );

	 	model.processForm( req );
		doGet( req, resp );
	}
}
