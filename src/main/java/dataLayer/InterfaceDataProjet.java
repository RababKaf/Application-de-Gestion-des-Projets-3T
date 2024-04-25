package dataLayer;

import java.util.ArrayList;
import java.util.List;

import models.*;

public interface InterfaceDataProjet {
	public void AddProject(Projet P);
	public ArrayList<Projet> getAllProjects();
	public  ArrayList<User> getChef();
	public ArrayList<Client> getclients();
	public boolean deleteProject(int id);
	public void addClient(Client c);
	public void modifyProject(Projet p);
	//public String getNomComplet(int userID);
	
	public List<Projet> GetProjets(int chefProjet);
	public Projet getProjetSelected(int idProjet) ;
	public List<Methodologie> getMeth();
	public List<Technologie> getTech();
	public void AddMeth(Projet prj );
	
	public ArrayList<Projet> GetAllProjet(int chefprj);
	public ArrayList<Technologie> GetTechnologie(Projet p);
    public 	ArrayList<Developpeur> EquipeMemebre(Projet p) ;
	public int AjouterService(Service  service , Projet p) ; 
	public void AjouterTache(Tache tache) ;
	public void Addtech(Projet prj ,Technologie tech );
	public List<Developpeur> FormerEquipe(Projet prj);
	public List<Competance> Competance(Projet prj,Developpeur dev );
	public void AddEquipe(Projet prj , Developpeur dev);
    public ArrayList<Developpeur> EquipeMember(Projet p);
    public void AddDateRn(Projet p);
    public String getNomComplet(int userID) ;
    public String GetNomProjet(int idProjet);
	public int getDeveloppeur (String nom);
	
	public ArrayList<Integer>  getListeidProjet (int idDevelopeur);
	
	public ArrayList<Projet> getProject(String nom);
	
	public Client getClient (String idClient);
	
	public ChefProjet getChefProjet(int id);
	
	public User getInfoDev(String nom);
	
	public  ArrayList<Competance> getCompetance(User dev);
	
	public Technologie getTechnologie(int id);
	
	public ArrayList<Service> getServices(int projetid);
	
	public ArrayList<Tache>  getTaches (int serviceID);
	
	public ArrayList<Technologie> getTechnologies(int idProjet);
	
	public Methodologie getMethodologie(int idProjet);
	
	public ArrayList<Integer> chefAndClinetIDs(int projetid);
	
	public User getInfoDev(int idDve);

	public int insertTechnologie(String nom);
	
	public void insertCompetance(int idTech,int idDev,String niveau);
	
	public void updateUser(String nom,String email,String tel,int idDve);
	
	public void updateCompetance(int idDev,String niveau,String competance);
	
	public Tache getTacheDetails(int tacheId);
	
	public boolean updateTache(int tacheId, String nouvelleDescription, int nouvelAvancement);
}
