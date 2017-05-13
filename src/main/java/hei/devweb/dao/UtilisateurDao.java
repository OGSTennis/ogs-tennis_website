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

import hei.devweb.dao.User;

public class UtilisateurDao {
	
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
	
	public List<User> listUser() throws DAOException {
    	List<User> listUtilisateurs = new ArrayList<>();
    	
    	try (
				Connection connection = getDataSource().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resulset = statement.executeQuery("SELECT * FROM utilisateur")){

			while (resulset.next()) {
				listUtilisateurs.add(new User(
						resulset.getInt("id"),
						resulset.getString("email"), 
						resulset.getString("mdp"), 
						resulset.getString("nom"), 
						resulset.getString("prenom")));
			}
			resulset.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listUtilisateurs;
    }
	
    public User creer( User utilisateur ) throws DAOException {
    	
    	try (
    			Connection connection = getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO utilisateur( email, mdp, nom, prenom) VALUES(?,?,?,?)"))
    	{
			statement.setString(1, utilisateur.getEmail());
			statement.setString(2, utilisateur.getMotDePasse());
			statement.setString(3, utilisateur.getNom());
			statement.setString(4, utilisateur.getPrenom());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateur;
    	
	}

    User trouver( long id ) throws DAOException {
		return null;
	}


    public void supprimer( Integer IdUtilisateur ) throws DAOException {
	
    	try (Connection connection = getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement("UPDATE utilisateur SET deleted=true WHERE id=?")){
			statement.setInt(1, IdUtilisateur);			
			statement.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public User getUser(String email){
    	User utilisateur = null;
    	try {
			Connection connection = getDataSource().getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM utilisateur where email= ? ");
			statement.setString(1, email);
			ResultSet resulset = statement.executeQuery();
			
			if(resulset.next()){
				utilisateur = new User(
						resulset.getInt("id"),
						resulset.getString("email"), 
						resulset.getString("mdp"), 
						resulset.getString("nom"), 
						resulset.getString("prenom"));
			}
			
			statement.close();
			connection.close();
			resulset.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return utilisateur;
    }
    
    public User getUserAdmin(String email){
    	User utilisateur = null;
    	try {
			Connection connection = getDataSource().getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM admin where email= ? ");
			statement.setString(1, email);
			ResultSet resulset = statement.executeQuery();
			
			if(resulset.next()){
				utilisateur = new User(
						resulset.getInt("id"),
						resulset.getString("email"), 
						resulset.getString("mdp"), 
						resulset.getString("nom"), 
						resulset.getString("prenom"));
			}
			
			statement.close();
			connection.close();
			resulset.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return utilisateur;
    }
    
    public String getPassword(String mail) {
		String motdepasse = null;
		try (Connection connection = getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT mdp FROM utilisateur WHERE email=?")){
				statement.setString(1, mail);
				ResultSet resultSet = statement.executeQuery();
				if (resultSet.next()) {
					motdepasse = resultSet.getString("mdp");
				}
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new OGSSQLException(e);
			}

		return motdepasse;
	}
    
    public String getPasswordAdmin(String mail) {
		String motdepasse = null;
		try (Connection connection = getDataSource().getConnection()){
			try(PreparedStatement statement = connection.prepareStatement("SELECT mdp FROM admin WHERE email=?")){
				statement.setString(1, mail);
				ResultSet resultSet = statement.executeQuery();
				if (resultSet.next()) {
					motdepasse = resultSet.getString("mdp");
				}
				statement.close();
				connection.close();
			}} catch (SQLException e) {
				throw new OGSSQLException(e);
			}

		return motdepasse;
	}

	public User getUser(Long id) {
		User utilisateur = null;
    	try {
			Connection connection = getDataSource().getConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM utilisateur where id="+id+";");
			ResultSet resulset = statement.executeQuery();
			
			if(resulset.next()){
				utilisateur = new User(
						resulset.getInt("id"),
						resulset.getString("email"), 
						resulset.getString("mdp"), 
						resulset.getString("nom"), 
						resulset.getString("prenom"));
			}
			
			statement.close();
			connection.close();
			resulset.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return utilisateur;
	}
}
