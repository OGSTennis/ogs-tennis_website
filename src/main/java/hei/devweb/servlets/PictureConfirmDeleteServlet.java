package hei.devweb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import hei.devweb.dao.AbstractGenericServlet;
import hei.devweb.dao.PictureExplorerRuntimeException;
import hei.devweb.dao.User;
import webService.PictureService;

@WebServlet("/deletepicture")
public class PictureConfirmDeleteServlet extends AbstractGenericServlet{
	private static final long serialVersionUID = -5952202407799824000L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String confirm = request.getParameter("confirm");
		Integer idPicture = Integer.parseInt(request.getParameter("id"));
		
		if("true".equals(confirm)){
			try {
				PictureService.getInstance().deletePicture(idPicture);
			} catch (PictureExplorerRuntimeException e) {
				e.printStackTrace();
			}
			response.sendRedirect("galery");
		}else{
			TemplateEngine templateEngine = this.createTemplateEngine(request);
			
			WebContext context = new WebContext(request, response, request.getServletContext());
			
			try {
				context.setVariable("picture", PictureService.getInstance().getPicturePatch(idPicture));
			} catch (PictureExplorerRuntimeException e) {
				e.printStackTrace();
			}
			
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			if((User) httpRequest.getSession().getAttribute("user") != null){
				context.setVariable("admin", true);
			}else{
				context.setVariable("admin", false);
			}
			
			templateEngine.process("confirmdelete", context, response.getWriter());
		}
	}

}
