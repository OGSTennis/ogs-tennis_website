package hei.devweb.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import hei.devweb.dao.impl.MotDePasseUtils;

@WebServlet("/Index")
public class Index_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

  
    public Index_servlet() {
       
    }
private Map<String, String> utilisateursAutorises;
	
	@Override
    public void init() throws ServletException {
		utilisateursAutorises = new HashMap<>();
		utilisateursAutorises.put("admin", "admin");
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(request.getServletContext());
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setPrefix("/webapp/");
		templateResolver.setSuffix(".html");
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		templateEngine.addDialect(new Java8TimeDialect());
		
		WebContext context = new WebContext(request, response,getServletContext());
		
		templateEngine.process("main", context,response.getWriter());
		
		String identifiantConnecte = (String) request.getSession().getAttribute("utilisateurConnecte");
		if (identifiantConnecte == null || "".equals(identifiantConnecte)) {
			System.out.println("truc bon");
	
		} else {
			System.out.println("truc mauvais");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String identifiantSaisi = request.getParameter("login");
		String motDePasseSaisi = request.getParameter("password");
		try {
			if(utilisateursAutorises.containsKey(identifiantSaisi) && MotDePasseUtils.validerMotDePasse(motDePasseSaisi, utilisateursAutorises.get(identifiantSaisi))) {
				request.getSession().setAttribute("utilisateurConnecte", identifiantSaisi);
				System.out.println("truc bon");
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		} 
		response.sendRedirect("connexion");
	}

}