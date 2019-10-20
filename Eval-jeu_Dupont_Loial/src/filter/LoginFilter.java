package filter;

import controller.PageFactory;
import model.LoginBean;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/home", "/question"})
public class LoginFilter implements Filter {
	
	@Override
	public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain ) throws IOException, ServletException {

		LoginBean model = LoginBean.getInstence( (HttpServletRequest) servletRequest );

		if ( model.isConnected() )
			filterChain.doFilter( servletRequest, servletResponse );
		else
			servletRequest.getRequestDispatcher(PageFactory.getLoginJsp()).forward( servletRequest, servletResponse );
	}
}
