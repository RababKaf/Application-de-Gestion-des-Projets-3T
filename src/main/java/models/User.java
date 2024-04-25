package models;

import java.util.ArrayList;

public class User {

	private int id;
	protected String email;
	protected String mot_de_Passe;
	protected String nom_complet;
	protected int role;
	protected String tel;
	protected ArrayList<User_Notifications>  listeNotifications;
	
	public User(){
	     listeNotifications=new  ArrayList<User_Notifications>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMot_de_Passe() {
		return mot_de_Passe;
	}

	public void setMot_de_Passe(String mot_de_Passe) {
		this.mot_de_Passe = mot_de_Passe;
	}

	public String getNom_complet() {
		return nom_complet;
	}

	public void setNom_complet(String nom_complet) {
		this.nom_complet = nom_complet;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public ArrayList<User_Notifications> getListeNotifications() {
		return listeNotifications;
	}

	public void setListeNotifications(ArrayList<User_Notifications> listeNotifications) {
		this.listeNotifications = listeNotifications;
	}
	
	

}