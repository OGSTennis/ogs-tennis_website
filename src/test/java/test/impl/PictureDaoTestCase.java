package test.impl;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import hei.devweb.dao.Picture;
import hei.devweb.dao.PictureDAO;

public class PictureDaoTestCase {
	//Ceci à surtout pour but de tester la connection Java-BDD
	private PictureDAO pictureDao = new PictureDAO ();
	
	@Before
	public void initDataBase() throws Exception{
		try(Connection connection = pictureDao.getDataSource().getConnection();
		Statement stmt = connection.createStatement()){
			stmt.executeUpdate("DELETE FROM picture");
			stmt.executeUpdate("INSERT INTO picture(id,name, summary, path) VALUES(1,'testName1','TestDesc1','/path/to/image1.png')");
			stmt.executeUpdate("INSERT INTO picture(id,name, summary, path) VALUES(2,'testName2','TestDesc2','/path/to/image2.png')");
			stmt.executeUpdate("INSERT INTO picture(id,name, summary, path) VALUES(3,'testName3','TestDesc3','/path/to/image3.png')");
		}
	}
	
	@Test
	public void shouldListPicture() throws Exception{
		//WHEN
		List<Picture> listPictures = pictureDao.listPicture();
		//THEN
		Assertions.assertThat(listPictures).hasSize(3);
		Assertions.assertThat(listPictures).extracting("id","name","summary").containsOnly(
				Assertions.tuple(1,"testName1","TestDesc1"),
				Assertions.tuple(2,"testName2","TestDesc2"),
				Assertions.tuple(3,"testName3","TestDesc3"));
	}
	
	@Test
	public void shouldGetPicturePath() throws Exception {
		// WHEN
		String picturePath = pictureDao.getPicturePath(1);
		// THEN
		Assertions.assertThat(picturePath).isEqualTo("/path/to/image1.png");
		
	}
	
}
