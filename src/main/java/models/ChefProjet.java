package models;

public class ChefProjet extends User {
		int id;
	public ChefProjet(){

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ChefProjet(int id) {
		super();
		this.id = id;
	}

	
}