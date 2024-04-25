package businessLayer;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import models.*;

public interface InterfaceProjetManager {
	
	public boolean AddProject(Projet P);
	public void AddProject(String nom,String description,Client client, Date dateDeDebut,Date dateDeLivraison, 
			int joursDeDeveloppement,ChefProjet chefProjet) ;
	public void addClient(Client c);
	public ArrayList<Projet> getAllProjects();
	public  ArrayList<User> getChef();
	public  ArrayList<Client> getclients();
	public boolean deleteProject(int id);
	public boolean modifyProject(Projet p);
	
	public List<Projet> GetProjets(int chefProjet);
	public Projet ProjetSelected(int idPrj);
	public List<Methodologie> getMeth();
	public List<Technologie> getTech();
	public void UpDateMeth(int prj , int idMeth);
	public void AddTech(int prj , int idTech);
	public List<Developpeur> FormerEquipe(int prj);
	public List<Competance> Competance(int prj,int  dev );
	public void AddEquipe(int prj , ArrayList<Integer> listeDev);
	public ArrayList<Developpeur> EquipeMember( int p );
	public void AddDateRn(java.sql.Date Date, int prj );
	public ArrayList<Projet> ListProjet(int chefprj);
	public ArrayList<Technologie> ListeTech(int projetId);
	public ArrayList<Developpeur> GetEquipe(int projetId);
	public int AjouterService(String desc , int duree , int ProjetID);
	public void AjouterTache(String Nom , String Desc , int DevId , int ServiceId);
	
	public ArrayList<Projet> getProject(String nom);
	
	public User getInfoDev(String nom);
	
	public ArrayList<Competance> getCompetance(User dev);
	
    public ArrayList<Service> getServices(int projetid);
	
	public ArrayList<Tache>  getTaches (int serviceID);
	
	public ArrayList<Technologie> getTechnologies(int idProjet);
	
	public Methodologie getMethodologie(int idProjet);
	
	public ArrayList<Integer> chefAndClinetIDs(int projetid);
	
	public Client getClient (String idClient);
	
	public ChefProjet getChefProjet(int id);
	
	public int insertTechnologie(String nom);
	
	public void insertCompetance(int idTech,int idDev,String niveau);
	
	public int getDeveloppeur (String nom);
	
	public void updateUser(String nom,String email,String tel,int idDev);
	
	public void updateCompetance(int idDev,String niveau,String competance);
	
	public Tache getTacheDetails(int tacheId);

	public boolean updateTache(int tacheId, String nouvelleDescription, int nouvelAvancement);
}
