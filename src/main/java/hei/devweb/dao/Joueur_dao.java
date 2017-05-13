package hei.devweb.dao;

import java.util.List;

import hei.devweb.model.Joueur;

public interface Joueur_dao {
	
	public List<Joueur> getJoueurByNom(String nom);
	
	public List<Joueur> getJoueurByPrenom(String prenom);
	
	public List<Joueur> getJoueurByNumEquipe(String num_equipe);
	
	public List<Joueur> getJoueurByNumero(int numero);
	
	
	
	
	
	
	
}
