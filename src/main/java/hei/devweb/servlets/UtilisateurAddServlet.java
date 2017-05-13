package hei.devweb.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import hei.devweb.dao.AbstractGenericServlet;
import hei.devweb.dao.User;
import webService.UserService;
import webService.UtilisateurService;

@WebServlet("/UtilisateurAddServlet")
@MultipartConfig
public class UtilisateurAddServlet extends AbstractGenericServlet{

	private static final long serialVersionUID = 1351873015540134167L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		
		WebContext context = new WebContext(req, resp, getServletContext());
		if(req.getSession().getAttribute("utilisateurCreationError") != null) {
			context.setVariable("errorMessage", req.getSession().getAttribute("utilisateurCreationError"));
			context.setVariable("utilisateur", (User) req.getSession().getAttribute("utilisateurCreationData"));

			req.getSession().removeAttribute("utilisateurCreationError");
			req.getSession().removeAttribute("utilisateurCreationData");
		} else {
			context.setVariable("utilisateur", new User());
		}
		templateEngine.process("inscriptionUtil", context, resp.getWriter());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String mdp = req.getParameter("mdp");
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		
		try {
			mdp = UserService.getInstance().genererMotDePasse(mdp);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (InvalidKeySpecException e1) {
			e1.printStackTrace();
		}
		
		User newUtilisateur = new User(null, email, mdp, nom, prenom);
			
		try {
			UtilisateurService.getInstance().creerUser(newUtilisateur);
			resp.sendRedirect("inscriptionOk");
		} catch (IllegalArgumentException|IOException e) {
			req.getSession().setAttribute("utilisateurCreationError", e.getMessage());
			resp.sendRedirect("UtilisateurAddServlet");
		}
	}
}
