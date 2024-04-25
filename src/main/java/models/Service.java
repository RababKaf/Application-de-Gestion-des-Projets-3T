package models;

import java.util.*;

public class Service {
	   private int id;
			private String descrip;
			private int DurreeEnjours;
			public ArrayList<Developpeur> listeDev=new ArrayList<>();
			public ArrayList<Tache> taches=new ArrayList<>() ;
			
			public Service() {
				
				listeDev = new ArrayList<>();
				taches=  new ArrayList<>();
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
	public int getDurreeEnjours() {
		return DurreeEnjours;
	}
	public void setDurreeEnjours(int durreeEnjours) {
		DurreeEnjours = durreeEnjours;
	}
	public ArrayList<Developpeur> getListeDev() {
		return listeDev;
	}
	public void setListeDev(ArrayList<Developpeur> listeDev) {
		this.listeDev = listeDev;
	}
	public ArrayList<Tache> getTaches() {
		return taches;
	}
	public void setTaches(ArrayList<Tache> taches) {
		this.taches = taches;
	}

	
}
