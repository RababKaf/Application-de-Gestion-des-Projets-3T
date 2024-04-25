package models;

import java.util.ArrayList;

public class Developpeur extends User {
	public ArrayList<Service> listeService;
    public ArrayList<Tache> listeTache;
	public Developpeur(){
		listeService = new ArrayList<>();
		listeTache = new ArrayList<>();
	
	}

	public void finalize() throws Throwable {
		super.finalize();
	}
}
