package filter;

import model.LoginBean;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/persons", "/persons/*"})
public class LoginFilter implements Filter {
	
	@Override
	public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain ) throws IOException, ServletException {
		
		HttpSession session = ((HttpServletRequest)servletRequest).getSession(  );
		if ( null == session || null == session.getAttribute( LoginBean.ATT_AUTH_SESSION )) {
			((HttpServletRequest)servletRequest).getRequestDispatcher( "login" ).forward( servletRequest, servletResponse );
		} else {
			filterChain.doFilter( servletRequest, servletResponse );
		}
	}
}
