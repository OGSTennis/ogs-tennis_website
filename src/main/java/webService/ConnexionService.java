package webService;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.dao.AbstractGenericServlet;

@WebServlet("/connexionUtil")

public class ConnexionService extends AbstractGenericServlet {
	private static final long serialVersionUID = 3038302649713866775L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null) {
			TemplateEngine engine = this.createTemplateEngine(request);
			WebContext context = new WebContext(request, response, request.getServletContext());
			
			engine.process("connexionUtil", context, response.getWriter());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String identifiant = request.getParameter("email");
		String motDePasse = request.getParameter("mdp");
		try {
			if (UserService.getInstance().getUserAdmin(identifiant)!=null && UserService.getInstance().validerMotDePasseAdmin(identifiant, motDePasse)){
				request.getSession().setAttribute("user", UserService.getInstance().getUserAdmin(identifiant));

			
				response.sendRedirect("HomeServlet");
			}

			else{

				response.sendRedirect("connexionUtil");
			}
			
		} catch (IllegalArgumentException e) {
		} catch (OGSSecuriteException e) {
		}

	}

}
