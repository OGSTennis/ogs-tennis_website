package hei.devweb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import hei.devweb.dao.AbstractGenericServlet;
import hei.devweb.dao.Picture;
import hei.devweb.dao.PictureExplorerRuntimeException;
import webService.PictureService;


@WebServlet("/PictureAddServlet")
@MultipartConfig
public class PictureAddServlet extends AbstractGenericServlet{

	private static final long serialVersionUID = -3497793006266174453L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		
		WebContext context = new WebContext(req, resp, getServletContext());
		if(req.getSession().getAttribute("pictureCreationError") != null) {
			context.setVariable("errorMessage", req.getSession().getAttribute("pictureCreationError"));
			context.setVariable("picture", (Picture) req.getSession().getAttribute("pictureCreationData"));

			req.getSession().removeAttribute("pictureCreationError");
			req.getSession().removeAttribute("pictureCreationData");
		} else {
			context.setVariable("picture", new Picture());
		}
		templateEngine.process("galery", context, resp.getWriter());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String summary = req.getParameter("summary");
		
		Part picturePath = req.getPart("picture");
		Picture newPicture = new Picture(null, name, summary);
		
		
		try {
			PictureService.getInstance().addPicture(newPicture, picturePath);
			resp.sendRedirect("galery");
		} catch (IllegalArgumentException|IOException | PictureExplorerRuntimeException e) {
			req.getSession().setAttribute("pictureCreationError", e.getMessage());
			req.getSession().setAttribute("pictureCreationData", newPicture);
			resp.sendRedirect("galery");
		} 

	}
	
}
