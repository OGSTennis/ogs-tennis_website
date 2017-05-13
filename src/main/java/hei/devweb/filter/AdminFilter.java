package hei.devweb.filter;

import java.io.IOException;
import hei.devweb.dao.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class AdminFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		User user = (User) httpRequest.getSession().getAttribute("user");
		if (user == null) {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect("../home");
			return;
		}
		chain.doFilter(request, response);
	}

	public void destroy() {
	}

}
