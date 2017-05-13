package test.impl;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import hei.devweb.dao.User;
import hei.devweb.dao.UtilisateurDao;

public class UtilisateurDaoTestCase {
	
	private UtilisateurDao utilisateurDao = new UtilisateurDao();

	@Before
	//Test pour initialisation de la base de donnée
	public void initDataBase() throws Exception{
		try(Connection connection = utilisateurDao.getDataSource().getConnection();
		Statement stmt = connection.createStatement()){
			stmt.executeUpdate("DELETE FROM utilisateur");
			stmt.executeUpdate("INSERT INTO utilisateur(id,email, mdp, nom, prenom) VALUES(1,'test1@test.test','1','nom1','prenom1')");
			stmt.executeUpdate("INSERT INTO utilisateur(id,email, mdp, nom, prenom) VALUES(2,'test2@test.test','12','nom2','prenom2')");
			stmt.executeUpdate("INSERT INTO utilisateur(id,email, mdp, nom, prenom) VALUES(3,'test3@test.test','123','nom3','prenom3')");
			stmt.executeUpdate("INSERT INTO utilisateur(id,email, mdp, nom, prenom) VALUES(4,'test4@test.test','1234','nom4','prenom4')");
		}
	}
	
	@Test
	//Test pour initialisation de la base de donnée
	public void shouldListJeu() throws Exception{
		//WHEN
		List<User> listUtilisateur = utilisateurDao.listUser();
		//THEN
		Assertions.assertThat(listUtilisateur).hasSize(4);
		Assertions.assertThat(listUtilisateur).extracting("id","email","mdp","nom","prenom").containsOnly(
				Assertions.tuple(1,"test1@test.test","1","nom1","prenom1"),
				Assertions.tuple(2,"test2@test.test","12","nom2","prenom2"),
				Assertions.tuple(3,"test3@test.test","123","nom3","prenom3"),
				Assertions.tuple(4,"test4@test.test","1234","nom4","prenom4"));
	}
	
}



