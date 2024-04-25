package businessLayer;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import dataLayer.*;
import models.*;


public class ProjectManager implements InterfaceProjetManager {
	private DataProjet dataP= new DataProjet();

	
	
	
	@Override
	public List<Projet> GetProjets(int chefProjet) {
		return dataP.GetProjets( chefProjet);
	}

	@Override
	public Projet ProjetSelected(int idPrj) {
		
		return dataP.getProjetSelected(idPrj);
	}

	@Override
	public List<Methodologie> getMeth() {
		
		return dataP.getMeth();
	}

	@Override
	public List<Technologie> getTech() {
		
		return dataP.getTech();
	}

	@Override
	public void UpDateMeth(int prj, int idMeth) {
		Projet projet = new Projet();
		Methodologie m = new Methodologie();
		projet.setId(prj);
		m.setId(idMeth);
		projet.setMethodologie(m);
		dataP.AddMeth(projet);
		
	}

	@Override
	public void AddTech(int prj, int idTech) {
		Projet projet = new Projet();
		Technologie tech = new Technologie();
		projet.setId(prj);
		tech.setId(idTech);
		dataP.Addtech(projet, tech);
		
	}
	

	@Override
	public ArrayList<Projet> ListProjet(int chefprj) {
		
		return dataP.GetAllProjet( chefprj);
	}

	@Override
	public ArrayList<Technologie> ListeTech(int projetId) {
		Projet projet = new Projet();
		projet.setId(projetId);
		return dataP.GetTechnologie(projet);
	}

	@Override
	public ArrayList<Developpeur> GetEquipe(int projetId) {
		Projet projet = new Projet();
		projet.setId(projetId);
		return dataP.EquipeMemebre(projet);
	}

	@Override
	public int AjouterService(String desc, int duree, int ProjetID) {
		Service service = new Service();
		Projet projet = new Projet();
		service.setDescrip(desc);
		service.setDurreeEnjours(duree);
		projet.setId(ProjetID);
		return dataP.AjouterService(service, projet);
	}

	@Override
	public void AjouterTache(String Nom, String Desc, int DevId, int ServiceId) {
		Tache tache= new Tache() ;
		Service ser = new Service();
		User user = new User();
		user.setId(DevId);
		ser.setId(ServiceId);
		tache.setDescrip(Desc);
		tache.setNom(Nom);
		tache.setUser(user);
		tache.setService(ser);
		dataP.AjouterTache(tache);
		
	}
	
	
	@Override
	public List<Developpeur> FormerEquipe(int prj) {
		Projet pr = new Projet() ;
				pr.setId(prj);
				
		return dataP.FormerEquipe(pr);
	}

	@Override
	public List<Competance> Competance(int prj,int  dev ) {
		Projet pr = new Projet() ;
		pr.setId(prj);
		Developpeur devp = new Developpeur();
		devp.setId(dev);
		return dataP.Competance(pr, devp);
	}

	@Override
	public void AddEquipe(int prj, ArrayList<Integer> listeDev) {
		Projet projet = new Projet();
		projet.setId(prj);
		
		for(int dev : listeDev)
		{
			Developpeur Devlop = new Developpeur();
			Devlop.setId(dev);
			dataP.AddEquipe(projet, Devlop);
		}
		
	}

	@Override
	public ArrayList<Developpeur> EquipeMember(int p) {
		Projet projet = new Projet();
		projet.setId(p);
		return dataP.EquipeMember(projet);
	}

	
	@Override
	
	public void AddDateRn(java.sql.Date Date, int prj ) {
		Projet projet = new Projet();
		projet.setId(prj);
		projet.setDateDeReunion(Date);
		dataP.AddDateRn(projet);
	}

	@Override
	public ArrayList<Projet> getProject(String nom) {
		// TODO Auto-generated method stub
		return dataP.getProject(nom);
	}


	@Override
	public User getInfoDev(String nom) {
		// TODO Auto-generated method stub
		return dataP.getInfoDev(nom);
	}


	@Override
	public ArrayList<Competance> getCompetance(User dev) {
		// TODO Auto-generated method stub
		return dataP.getCompetance(dev);
	}


	@Override
	public ArrayList<Service> getServices(int projetid) {
		// TODO Auto-generated method stub
		return dataP.getServices(projetid);
	}


	@Override
	public ArrayList<Tache> getTaches(int serviceID) {
		// TODO Auto-generated method stub
		return dataP.getTaches(serviceID);
	}


	@Override
	public ArrayList<Technologie> getTechnologies(int idProjet) {
		// TODO Auto-generated method stub
		return dataP.getTechnologies(idProjet);
	}


	@Override
	public Methodologie getMethodologie(int idProjet) {
		// TODO Auto-generated method stub
		return dataP.getMethodologie(idProjet);
	}


	@Override
	public ArrayList<Integer> chefAndClinetIDs(int projetid) {
		// TODO Auto-generated method stub
		return dataP.chefAndClinetIDs(projetid);
	}


	@Override
	public Client getClient(String idClient) {
		// TODO Auto-generated method stub
		return dataP.getClient(idClient);
	}


	@Override
	public ChefProjet getChefProjet(int id) {
		// TODO Auto-generated method stub
		return dataP.getChefProjet(id);
	}


	@Override
	public int insertTechnologie(String nom) {
		// TODO Auto-generated method stub
		return dataP.insertTechnologie(nom);
	}


	@Override
	public void insertCompetance(int idTech, int idDev, String niveau) {
		// TODO Auto-generated method stub
		dataP.insertCompetance(idTech, idDev, niveau);
	}


	@Override
	public int getDeveloppeur(String nom) {
		// TODO Auto-generated method stub
		return dataP.getDeveloppeur(nom);
	}


	@Override
	public void updateUser(String nom, String email, String tel,int idDev) {
		// TODO Auto-generated method stub
		dataP.updateUser(nom, email, tel,idDev);
		
	}


	@Override
	public void updateCompetance( int idDev, String niveau,String competance) {
		// TODO Auto-generated method stub
		System.out.println("update competance business ");
		dataP.updateCompetance( idDev, niveau,competance);
	}


	@Override
	public Tache getTacheDetails(int tacheId) {
		// TODO Auto-generated method stub
		return dataP.getTacheDetails(tacheId);
	}


	@Override
	public boolean updateTache(int tacheId, String nouvelleDescription, int nouvelAvancement) {
		// TODO Auto-generated method stub
		return dataP.updateTache(tacheId, nouvelleDescription, nouvelAvancement);
	}
	
	
	public boolean AddProject(Projet P) {
	    ArrayList<Projet> listProjets = dataP.getAllProjects();
	    for (Projet projet : listProjets) {
	        if (projet.getNom().equals(P.getNom()))

	        	return false;
	  }
	    dataP.AddProject(P);
        return true;

	    
	}

	public void addClient(Client c) {
		 ArrayList<Client> listClients = dataP.getclients();
		 boolean exist=false;
		    for (Client client : listClients) {
		        if (client.getCin().equals(c.getCin()))
		        	exist=true;
		  }
		    if(!exist)
		    	dataP.addClient(c);
		
	}
	@Override
	public void AddProject(String nom,String description,Client client, Date dateDeDebut,Date dateDeLivraison, 
			int joursDeDeveloppement,ChefProjet chefProjet) {
		Projet p =new Projet(dateDeDebut,dateDeLivraison,description,joursDeDeveloppement,nom,client,chefProjet);
		dataP.AddProject(p);
	}
	
	    public ArrayList<Projet> getAllProjects() {
	        return dataP.getAllProjects();
	    }
	    public  ArrayList<User> getChef() {
	    	
	    	return dataP.getChef();
	    }

	    public  ArrayList<Client> getclients() {
	    	return dataP.getclients();
	    }
	    
	    public boolean deleteProject(int id) {
	    	return dataP.deleteProject(id);
	    }
	    
	    public boolean modifyProject(Projet p) {
	    	
    		 dataP.modifyProject(p);
    		 return true;
    	
	
	    }
	
	

}
