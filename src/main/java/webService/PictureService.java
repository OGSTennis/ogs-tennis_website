package webService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.Part;

import hei.devweb.dao.Picture;
import hei.devweb.dao.PictureDAO;
import hei.devweb.dao.PictureExplorerRuntimeException;

public class PictureService {
	
	private PictureDAO pictureDao = new PictureDAO();
	private static final String PICTURE_MAIN_DIRECTORY = "C:/dataPicture";
	
	private static class PictureServiceHolder {
		private static PictureService instance = new PictureService();
	}
	
	public static PictureService getInstance() {
		return PictureServiceHolder.instance;
	}

	private PictureService() {
	}
	
	public List<Picture> listePicture(){
		return pictureDao.listPicture();
	}
	
	public void addPicture(Picture newPicture, Part path) throws IOException, PictureExplorerRuntimeException {
		if(newPicture == null){
			throw new IllegalArgumentException("A picture must be provided.");
		}
		if(newPicture.getName() == null || "".equals(newPicture.getName())) {
			throw new IllegalArgumentException("A picture must have a name.");
		}
		if(newPicture.getSummary() == null || "".equals(newPicture.getSummary())) {
			throw new IllegalArgumentException("A picture must have a summary.");
		}
		if(path == null) {
			throw new IllegalArgumentException("A picture must have a picture.");
		}
		
		Path picturePath = Paths.get(PICTURE_MAIN_DIRECTORY, path.getSubmittedFileName());
		
		pictureDao.addPicture(newPicture, picturePath.toString());
		
		Files.copy(path.getInputStream(), picturePath);
	}
	
	public Path getPicturePatch(Integer pictureId) throws PictureExplorerRuntimeException {
		String picturePathString = pictureDao.getPicturePath(pictureId);
		if(picturePathString == null) {
			return getDefaultPicturePath();
		} else {
			Path picturePath = Paths.get(pictureDao.getPicturePath(pictureId));
			if(Files.exists(picturePath)) {
				return picturePath;
			} else {
				return getDefaultPicturePath();
			}
		}
		
	}
	
	private Path getDefaultPicturePath() {
		try {
			return Paths.get(this.getClass().getClassLoader().getResource("picture-no-photo.png").toURI());
		} catch (URISyntaxException e) {
			return null;
		}
	}
	
	public void deletePicture(Integer pictureId) throws PictureExplorerRuntimeException{
		pictureDao.deletePicture(pictureId);
	}

	public Picture getPicture(String namePicture) throws PictureExplorerRuntimeException {
		return pictureDao.getPicture(namePicture);
	}
	
	public Picture getPicture(Integer idPicture) throws PictureExplorerRuntimeException {
		return pictureDao.getPicture(idPicture);
	}
}
