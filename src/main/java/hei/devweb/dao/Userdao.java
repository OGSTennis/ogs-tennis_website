package hei.devweb.dao;

import java.util.List;

import hei.devweb.dao.User;


public interface Userdao {
	List<User> listerUsers();

	User getUser(String mail);
	
	User getUser(long idusers);

	String getPassword(String mail);

	User addUser(User nouvelUser);

	void deleteUser(long idusers);

	void updateDescription(long idusers, String description);

	String getDescription(long idusers);

	void modifierMotDePasse(long idusers, String motdepasse);
	
	String generateRandomPassword();

	List<Long> listUsersIdFound(String identity, Long idusers);
	
	void updatePicture(long idusers, String picturePath);
	
	void blockUser(long idusers);
	
	List<User> usersBlockList();
	
	void unblockUser(long idusers);
	
	User addTemporaryUser(User newuser, String activationKey);
	
	User getTemporaryUser(Long idAccountNotActivated);
	
	void deleteTemporaryUser(long idAccountNotActivated);
	
	String generateActivationKey();
	
	User getTemporaryUser(String mail);
	
}
