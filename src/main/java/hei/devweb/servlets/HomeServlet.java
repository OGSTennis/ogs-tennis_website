package hei.devweb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.dao.AbstractGenericServlet;
import hei.devweb.dao.User;

@WebServlet("/index")
public class HomeServlet extends AbstractGenericServlet {
	
	private static final long serialVersionUID = -296855808757153937L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TemplateEngine templateEngine = this.createTemplateEngine(request);
		
		WebContext context = new WebContext(request, response, request.getServletContext());
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if((User) httpRequest.getSession().getAttribute("user") != null){
			context.setVariable("admin", true);
		}else{
			context.setVariable("admin", false);
		}
		
		templateEngine.process("index2", context, response.getWriter());
	}
}
