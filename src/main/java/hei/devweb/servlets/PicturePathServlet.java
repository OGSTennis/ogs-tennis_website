package hei.devweb.servlets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hei.devweb.dao.AbstractGenericServlet;
import hei.devweb.dao.PictureExplorerRuntimeException;
import webService.PictureService;

@WebServlet("/picturepath")
public class PicturePathServlet extends AbstractGenericServlet{
	
	private static final long serialVersionUID = 5916054828075178116L;
	Path picturePath = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer pictureId = Integer.parseInt(req.getParameter("id"));
		try {
			picturePath = PictureService.getInstance().getPicturePatch(pictureId);
		} catch (PictureExplorerRuntimeException e) {
			e.printStackTrace();
		}
		
		
		Files.copy(picturePath, resp.getOutputStream());
	}
}
