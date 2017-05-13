package webService;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import hei.devweb.dao.User;

import hei.devweb.dao.UtilisateurDao;


public class UserService {
	
    private static class UserServiceHolder {
        private static UserService instance = new UserService();
    }

    public static UserService getInstance() {
        return UserServiceHolder.instance;
    }

	private static UtilisateurDao UserDao = new UtilisateurDao();
	private MotDePasseManager motDePasseManager = new MotDePasseManager();

	private UserService() {
	}

	public List<User> listerUsers() {
		return UserDao.listUser();
	}

	public User getUser(String mail) {
		return UserDao.getUser(mail);
	}
	
	public User getUserAdmin(String mail) {
		return UserDao.getUserAdmin(mail);
	}
	
	public User getUser(Long idUsers) {
		if (idUsers == null || "".equals(idUsers)) {
			throw new IllegalArgumentException("L'identifiant doit être renseigné.");
		}
		return UserDao.getUser(idUsers);
	}

	public boolean validerMotDePasse(String email, String motDePasseAVerifier) throws OGSSecuriteException {
		if (email == null || "".equals(email)) {
			throw new IllegalArgumentException("L'identifiant doit être renseigné.");
		}
		if (motDePasseAVerifier == null || "".equals(motDePasseAVerifier)) {
			throw new IllegalArgumentException("Le mot de passe doit être renseigné.");
		}
		String motDePasseHashe = UserDao.getPassword(email);
		if (motDePasseHashe == null) {
			throw new IllegalArgumentException("L'identifiant n'est pas connu.");
		}
		try {
			return motDePasseManager.validerMotDePasse(motDePasseAVerifier, motDePasseHashe);
		} catch (GeneralSecurityException e) {
			throw new OGSSecuriteException("Problème dans la vérification du mot de passe.", e);
		}

	}
	
	public boolean validerMotDePasseAdmin(String email, String motDePasseAVerifier) throws OGSSecuriteException {
		if (email == null || "".equals(email)) {
			throw new IllegalArgumentException("L'identifiant doit être renseigné.");
		}
		if (motDePasseAVerifier == null || "".equals(motDePasseAVerifier)) {
			throw new IllegalArgumentException("Le mot de passe doit être renseigné.");
		}
		String motDePasseHashe = UserDao.getPasswordAdmin(email);
		if (motDePasseHashe == null) {
			throw new IllegalArgumentException("L'identifiant n'est pas connu.");
		}
		try {
			return motDePasseManager.validerMotDePasse(motDePasseAVerifier, motDePasseHashe);
		} catch (GeneralSecurityException e) {
			throw new OGSSecuriteException("Problème dans la vérification du mot de passe.", e);
		}

	}
	
	public String genererMotDePasse(String motDePasse) throws NoSuchAlgorithmException, InvalidKeySpecException{
		return motDePasseManager.genererMotDePasse(motDePasse);
	}
	
	
	public static void addUser (User newUser) {
		UserDao.creer(newUser);
	}

	public void deleteUser(Integer idUsers) {
		UserDao.supprimer(idUsers);;
	}

}