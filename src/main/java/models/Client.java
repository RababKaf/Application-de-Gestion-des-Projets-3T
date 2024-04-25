package models;

import java.util.ArrayList;

public class Client {
	private int id;
	private String adresse;
	private String cin;
	private String nom_complet;
	private String tel;
	public ArrayList<Projet> projets ;

	public Client(){

	}

	public Client(String adresse, String cin, String nom_complet, String tel) {
		super();
		this.adresse = adresse;
		this.cin = cin;
		this.nom_complet = nom_complet;
		this.tel = tel;
	}

	public void finalize() throws Throwable {

	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getNom_complet() {
		return nom_complet;
	}

	public void setNom_complet(String nom_complet) {
		this.nom_complet = nom_complet;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
