package models;

import java.sql.Date;
import java.util.ArrayList;

public class Projet  {
    private int id;
	private Date dateDeDebut;
	private Date dateDeLivraison;
	private Date dateDeReunion;
	private String description;
	private int joursDeDeveloppement;
	private String nom;
	public ChefProjet chefProjet;
	public Methodologie methodologie;
	public ArrayList<Technologie> technologie;
	public Equipe equipe;
	public ArrayList<Service> service;
	public Client client;
	
	public Projet (){
		chefProjet=new ChefProjet();
		methodologie=new Methodologie();
		technologie=new ArrayList<>();
		service=new ArrayList<>();
		client=new Client();
	}
	
	

	


	

	public Methodologie getMethodologie() {
		return methodologie;
	}






	public ArrayList<Technologie> getTechnologie() {
		return technologie;
	}






	public Equipe getEquipe() {
		return equipe;
	}






	public ArrayList<Service> getService() {
		return service;
	}






	public void setMethodologie(Methodologie methodologie) {
		this.methodologie = methodologie;
	}






	public void setTechnologie(ArrayList<Technologie> technologie) {
		this.technologie = technologie;
	}






	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}






	public void setService(ArrayList<Service> service) {
		this.service = service;
	}






	public Projet(Date dateDeDebut, Date dateDeLivraison, String description, int joursDeDeveloppement, String nom,
			Client client,ChefProjet chefProjet) {
		super();
		this.dateDeDebut = dateDeDebut;
		this.dateDeLivraison = dateDeLivraison;
		this.description = description;
		this.joursDeDeveloppement = joursDeDeveloppement;
		this.nom = nom;
		this.client = client;
		this.chefProjet=chefProjet;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public Date getDateDeDebut() {
		return dateDeDebut;
	}



	public void setDateDeDebut(Date dateDeDebut) {
		this.dateDeDebut = dateDeDebut;
	}



	public Date getDateDeLivraison() {
		return dateDeLivraison;
	}



	public void setDateDeLivraison(Date dateDeLivraison) {
		this.dateDeLivraison = dateDeLivraison;
	}



	public Date getDateDeReunion() {
		return dateDeReunion;
	}

	public void setDateDeReunion(Date dateDeReunion) {
		this.dateDeReunion = dateDeReunion;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getJoursDeDeveloppement() {
		return joursDeDeveloppement;
	}

	public void setJoursDeDeveloppement(int joursDeDeveloppement) {
		this.joursDeDeveloppement = joursDeDeveloppement;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ChefProjet getChefProjet() {
		return chefProjet;
	}

	public void setChefProjet(ChefProjet chefProjet) {
		this.chefProjet = chefProjet;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	
}