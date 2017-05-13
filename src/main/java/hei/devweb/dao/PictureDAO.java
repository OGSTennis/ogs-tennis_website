package hei.devweb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class PictureDAO {
	
	public MysqlDataSource dataSource;

	public DataSource getDataSource() {
		if (dataSource == null) {
			dataSource = new MysqlDataSource();
			dataSource.setServerName("xxx");
			dataSource.setPort(0000);
			dataSource.setDatabaseName("xxx");
			dataSource.setUser("xxx");
			dataSource.setPassword("xxx");
		}
		return dataSource;
	}
	
	public void addPicture(Picture newPicture, String picturePath) throws PictureExplorerRuntimeException {
		try (
				Connection connection = getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO picture(name, summary, path) VALUES (?, ?, ?)")) {
			statement.setString(1, newPicture.getName());
			statement.setString(2, newPicture.getSummary());
			statement.setString(3, picturePath);

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PictureExplorerRuntimeException("Error when getting pictures", e);
		}
	}
	
	public String getPicturePath(Integer id) throws PictureExplorerRuntimeException{
		try (
				Connection connection = getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT path FROM picture WHERE id = ?")) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getString("path");
				}
			}
		} catch (SQLException e) {
			throw new PictureExplorerRuntimeException("Error when getting pictures", e);
		}
		return null;
	}
	
	public void deletePicture(Integer pictureID) throws PictureExplorerRuntimeException{
		try (
				Connection connection = getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement("UPDATE picture SET deleted=true WHERE id=?")) {
			statement.setInt(1, pictureID);
			statement.executeUpdate();	
		} catch (SQLException e) {
			throw new PictureExplorerRuntimeException("Error when deleting pictures", e);
		}
	}

	public List<Picture> listPicture() throws DAOException{
		List<Picture> pictures = new ArrayList<>();
		
		try (
				Connection connection = getDataSource().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resulset = statement.executeQuery("SELECT * FROM picture WHERE deleted=false")){

			while (resulset.next()) {
				pictures.add(new Picture(
						resulset.getInt("id"),
						resulset.getString("name"),
						resulset.getString("summary")));
			}
			resulset.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pictures;
	}

	public Picture getPicture(String namePicture) throws PictureExplorerRuntimeException {
		try (Connection connection = getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM picture WHERE name = ? AND deleted=false")) {
			statement.setString(1, namePicture);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return new Picture(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("summary"));
				}
			}
		} catch (SQLException e) {
			throw new PictureExplorerRuntimeException("Error when getting cities", e);
		}
		
		return null;
	}
	
	public Picture getPicture(Integer idPicture) throws PictureExplorerRuntimeException {
		try (Connection connection = getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM picture WHERE id = ? AND deleted=false")) {
			statement.setInt(1, idPicture);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return new Picture(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("summary"));
				}
			}
		} catch (SQLException e) {
			throw new PictureExplorerRuntimeException("Error when getting cities", e);
		}
		
		return null;
	}

}
