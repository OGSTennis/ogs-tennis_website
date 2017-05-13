package hei.devweb.dao;

public class User {

	private Integer id;
	private String email;
    private String mdp;
    private String nom;
    private String prenom;
    
    public User(Integer id, String email, String mdp, String nom, String prenom ){
    	super();
    	this.id = id;
    	this.email=email;
    	this.mdp=mdp;
    	this.nom=nom;
    	this.prenom=prenom;
    	
    }
    
    public User(String email, String mdp, String nom, String prenom ){
    	super();
    	this.email=email;
    	this.mdp=mdp;
    	this.nom=nom;
    	this.prenom=prenom;
    	
    }
    
	public User() {
	}

	public void setId( Integer id ) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setEmail(String email) {
    	this.email = email;
    }
    public String getEmail() {
    	return email;
    }

    public void setMotDePasse(String mdp) {
    	this.mdp = mdp;
    }
    public String getMotDePasse() {
    	return mdp;
    }

    public void setNom(String nom) {
    	this.nom = nom;
    }
    public String getNom() {
    	return nom;
    }
    public void setPrenom(String prenom) {
    	this.prenom = prenom;
    }
    public String getPrenom() {
    	return prenom;
    }
    
}
