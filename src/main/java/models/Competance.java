package models;

public class Competance {
	
	    private int id;
		private String niveau;
		public Technologie technologie;
		public Developpeur developpeur;
		
		
		public Competance(){

		}
		
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getNiveau() {
			return niveau;
		}
		public void setNiveau(String niveau) {
			this.niveau = niveau;
		}
		public Technologie getTechnologie() {
			return technologie;
		}
		public void setTechnologie(Technologie technologie) {
			this.technologie = technologie;
		}
		public Developpeur getDeveloppeur() {
			return developpeur;
		}
		public void setDeveloppeur(Developpeur developpeur) {
			this.developpeur = developpeur;
		}

		

}
