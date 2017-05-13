package hei.devweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hei.devweb.dao.Joueur_dao;
import hei.devweb.model.Joueur;

public class Joueur_dao_impl implements Joueur_dao {

	@Override
	public List<Joueur> getJoueurByNom(String nom) {
		List<Joueur> listejoueur = new ArrayList<Joueur>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM `joueur` WHERE nom=`"+nom+";");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Joueur joueur = new Joueur((Long) rs.getLong("joueur_id"),rs.getString("nom"), rs.getString("prenom"), rs.getInt("numero_adherant"));
				
				listejoueur.add(joueur);
			}
			
			rs.close();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listejoueur;
	}

	@Override
	public List<Joueur> getJoueurByPrenom(String prenom) {
		
		List<Joueur> listejoueur = new ArrayList<Joueur>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM `joueur` WHERE prenom=`"+prenom+";");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Joueur joueur = new Joueur((Long) rs.getLong("joueur_id"),rs.getString("nom"), rs.getString("prenom"), rs.getInt("numero_adherant"));
				
				listejoueur.add(joueur);
			}
			
			rs.close();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listejoueur;
	}

	@Override
	public List<Joueur> getJoueurByNumEquipe(String num_equipe) {
		
		List<Joueur> listejoueur = new ArrayList<Joueur>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM `joueur` WHERE equipe_id=`"+num_equipe+";");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Joueur joueur = new Joueur((Long) rs.getLong("joueur_id"),rs.getString("nom"), rs.getString("prenom"), rs.getInt("numero_adherant"));
				
				listejoueur.add(joueur);
			}
			
			rs.close();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listejoueur;
	}

	@Override
	public List<Joueur> getJoueurByNumero(int numero) {
		List<Joueur> listejoueur = new ArrayList<Joueur>();
		try {
			Connection connection = DataSourceProvider.getDataSource().getConnection();
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM `joueur` WHERE numero_adherant=`"+numero+";");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Joueur joueur = new Joueur((Long) rs.getLong("joueur_id"),rs.getString("nom"), rs.getString("prenom"), rs.getInt("numero_adherant"));
				
				listejoueur.add(joueur);
			}
			
			rs.close();
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listejoueur;
	}

}
