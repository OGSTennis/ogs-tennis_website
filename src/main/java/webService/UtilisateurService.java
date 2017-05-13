package webService;

import java.util.List;

import hei.devweb.dao.User;
import hei.devweb.dao.UtilisateurDao;

public class UtilisateurService {
	
	private UtilisateurDao utilisateurDao = new UtilisateurDao();
	
	private static class UtilisateurServiceHolder{
		private static UtilisateurService instance = new UtilisateurService();
	}

	public static UtilisateurService getInstance(){
		return UtilisateurServiceHolder.instance;
	}
	
	private UtilisateurService(){
		
	}
	
	public List<User> listeUtilisateur(){
		return utilisateurDao.listUser();
	}
	
	public User creerUser(User newUtilisateur){
		return utilisateurDao.creer(newUtilisateur);
	}
	
	public void suppUtilisateur(Integer idUtilisateur){
		utilisateurDao.supprimer(idUtilisateur);
	}
	
	
}

