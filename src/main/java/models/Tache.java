package models;


public class Tache {
	

	private int avancement;
	private int id;
	private String descrip;
	private String nom;
	public Service service ;
	public User user ;

	public Tache(){

	}
    


	public int getAvancement() {
		return avancement;
	}



	public void setAvancement(int avancement) {
		this.avancement = avancement;
	}




	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public int getId() {
		return id;
	}








	public void setId(int id) {
		this.id = id;
	}








	public String getDescrip() {
		return descrip;
	}








	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}








	public String getNom() {
		return nom;
	}








	public void setNom(String nom) {
		this.nom = nom;
	}








	public Service getService() {
		return service;
	}








	public void setService(Service service) {
		this.service = service;
	}








	public void finalize() throws Throwable {

	}
}
