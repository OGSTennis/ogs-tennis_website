package hei.devweb.model;

public class Joueur {
	
	private long id;
	
	private String nom;
	
	private String prenom; 
	
	private int numero_adherant; 
	
	private String login ; 
	
	private String pswd ;
	
	private int equipe_id;
	
	private String information;
	
	public Joueur(long id, String nom, String prenom, int numero_adherant){
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.numero_adherant = numero_adherant;
	}
	
	public Joueur(long id, String nom, String prenom, int numero_adherant, int equipe_id){
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.numero_adherant = numero_adherant;
		this.setEquipe_id(equipe_id);
	}

	public Joueur() {
		super();
		// TODO Auto-generated constructor stub
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getNumero_adherant() {
		return numero_adherant;
	}

	public void setNumero_adherant(int numero_adherant) {
		this.numero_adherant = numero_adherant;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public int getEquipe_id() {
		return equipe_id;
	}

	public void setEquipe_id(int equipe_id) {
		this.equipe_id = equipe_id;
	} 
	
	

}
