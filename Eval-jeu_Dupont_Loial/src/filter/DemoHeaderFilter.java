package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class DemoHeaderFilter implements Filter {
	@Override
	public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain ) throws IOException, ServletException {
		
		((HttpServletResponse)servletResponse).setHeader( "x-demo", "salary" );
		filterChain.doFilter( servletRequest, servletResponse );
	}
}
