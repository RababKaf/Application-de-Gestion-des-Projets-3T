package dataLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.*;

public class DataProjet implements InterfaceDataProjet {

	private Connection cnx;
	
		public DataProjet() {
				Connexion connexion=new Connexion();
				this.cnx=connexion.myCnx;
			} 
		
		
		
		@Override
		// cree une fonction qui recupere  les projet initial
		public List<Projet> GetProjets(int chefProjet) {
			
			List<Projet> NewProjets = new ArrayList<>();  // Declarer la liste a recuperer  :
			 
			 String myReq ="SELECT projet.*, client.NomComplet\r\n"
			 		+ "FROM projet, client, chefdeprojet\r\n"
			 		+ "WHERE projet.ChefDeProjetID = chefdeprojet.ChefDeProjetID\r\n"
			 		+ "  AND client.CIN= projet.CINClient\r\n"
			 		+ "  AND projet.MethodologieID IS NULL\r\n"
			 		+ "  AND projet.ChefDeProjetID = ?";  // definir la requte :
			 
			 try(PreparedStatement st=cnx.prepareStatement(myReq))
				{
				      st.setInt(1, chefProjet); 
				      ResultSet rs =st.executeQuery();

						while(rs.next())
						{
							Projet prj = new Projet();
							Client client = new Client();
							prj.setDateDeDebut(rs.getDate("Datededebut"));
							prj.setDateDeLivraison(rs.getDate("Datedelivraison"));
							prj.setNom(rs.getNString("nom"));
							prj.setId(rs.getInt("ProjetID"));
							prj.setDescription(rs.getString("Description"));
							prj.setJoursDeDeveloppement(rs.getInt("Joursdedeveloppement"));
							client.setNom_complet(rs.getString("NomComplet"));
							prj.setClient(client);
							
							NewProjets .add(prj);	
						}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 

			return NewProjets;
		}


		@Override
		public Projet getProjetSelected(int idProjet) {
			
			Projet prj = new Projet();
			ArrayList<Technologie> listeTech = new ArrayList<>();
			
		
			
			 String myReq ="select projet.* , client.NomComplet ,methodologie.Nom , technologie.Nom \r\n"
			 		+ "from projet , client , methodologie , technologie , techprojet \r\n"
			 		+ "where projet.CINClient = client.CIN \r\n"
			 		+ "and projet.MethodologieID=methodologie.MethodologieID \r\n"
			 		+ "and techprojet.ProjetID=projet.ProjetID\r\n"
			 		+ "and techprojet.TechnologieID=technologie.TechnologieID \r\n"
			 		+ "and projet.ProjetID=?";  
				 
				 try(PreparedStatement st=cnx.prepareStatement(myReq))
					{
					      st.setInt(1, idProjet); 
					      ResultSet rs =st.executeQuery();
					    

							while(rs.next())
							{
								
								Client client = new Client();
								Technologie tec = new Technologie();
								Methodologie meth = new Methodologie();
								prj.setDateDeDebut(rs.getDate("Datededebut"));
								prj.setDateDeLivraison(rs.getDate("Datedelivraison"));
								prj.setNom(rs.getString("nom"));
								prj.setId(rs.getInt("ProjetID"));
								prj.setDescription(rs.getString("Description"));
								prj.setJoursDeDeveloppement(rs.getInt("Joursdedeveloppement"));
								client.setNom_complet(rs.getString("NomComplet"));;
								prj.setClient(client);
								meth.setNom(rs.getString("methodologie.Nom"));
								if(meth != null)
								{
									prj.setMethodologie(meth);
									
								}
								
								tec.setNom(rs.getString("technologie.Nom"));
								if(tec != null) {
									
									listeTech.add(tec);
								}
								
								
								
							}
							if(listeTech != null)
							{
								prj.setTechnologie(listeTech);
							}
							
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
			
			
			return  prj;
		}


		@Override
		public List<Methodologie> getMeth() {
			
			List<Methodologie> listMeth = new ArrayList<>(); 
			
			String Req ="select * from methodologie" ;
			try(PreparedStatement st=cnx.prepareStatement(Req))
			{
			     
			      ResultSet rs =st.executeQuery();

					while(rs.next())
					{
						Methodologie meth = new Methodologie();
						meth.setId(rs.getInt("MethodologieID"));
						meth.setNom(rs.getString("Nom"));
						listMeth.add(meth);
						
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			
			return listMeth;
		}


		@Override
		public List<Technologie> getTech() {
			
			List<Technologie> listTech = new ArrayList<>();  
			
			
			String Req ="select * from technologie" ;
			try(PreparedStatement st=cnx.prepareStatement(Req))
			{
			     
			      ResultSet rs =st.executeQuery();

					while(rs.next())
					{
						Technologie tech = new Technologie();
						tech.setId(rs.getInt("TechnologieID"));
						tech.setNom(rs.getString("Nom"));
						listTech.add(tech);
						
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			
			return listTech;
		}


		@Override
		public void AddMeth(Projet prj) {
		
		    int IdProjet = prj.getId();
		    int idMeth=prj.getMethodologie().getId();
		    
			String Req ="UPDATE projet set MethodologieID = ? WHERE ProjetID =? ;" ;
			try(PreparedStatement st=cnx.prepareStatement(Req))
			{
					     st.setInt(1, idMeth);
					     st.setInt(2, IdProjet);
			            st.executeUpdate();
		
			} catch (SQLException e) {
				
				e.printStackTrace();
			} 
			
			
		}


		@Override
		public void Addtech(Projet prj, Technologie tech) {
			
			int IdProjet = prj.getId();
		    int idtech=tech.getId();
		    
		    
			String Req ="INSERT into techprojet(ProjetID , TechnologieID) VALUES (?,?);" ;
			try(PreparedStatement st=cnx.prepareStatement(Req))
			{
					     st.setInt(1, IdProjet);
					     st.setInt(2, idtech);
			            st.executeUpdate();
		
			} catch (SQLException e) {
				
				e.printStackTrace();
			} 
			
		}


		@Override
		public List<Competance> Competance(Projet prj,Developpeur dev ) {
			
			
	        int IdProjet = prj.getId();
	        int IdDev = dev.getId();
			List<Competance> ListComp = new ArrayList<>();  // Declarer la liste a recuperer  :
			 
			 String myReq ="select  DISTINCT  technologie.Nom , competance.Niveau , technologie.TechnologieID\r\n"
			 		+ "from   technologie , developpeur , competance , projet , techprojet\r\n"
			 		+ "where technologie.TechnologieID=competance.TechnologieID \r\n"
			 		+ "and competance.DeveloppeurID=developpeur.DeveloppeurID\r\n"
			 		+ "and projet.ProjetID=techprojet.ProjetID\r\n"
			 		+ "and technologie.TechnologieID=techprojet.TechnologieID\r\n"
			 		+ "and developpeur.DeveloppeurID=?\r\n"
			 		+ "and projet.ProjetID=?"; 
			 
			 try(PreparedStatement st=cnx.prepareStatement(myReq))
				{
				      st.setInt(1, IdDev); 
				      st.setInt(2, IdProjet); 
				      ResultSet rs =st.executeQuery();

						while(rs.next())
						{
							Competance cmp = new Competance();
							
							Technologie tech = new Technologie();
							//recuperer la tech:
							tech.setNom(rs.getString("technologie.Nom"));
							tech.setId(rs.getInt("technologie.TechnologieID"));
							cmp.setNiveau(rs.getString("competance.Niveau"));
							cmp.setTechnologie(tech);
							ListComp.add(cmp);
							
						}
				} catch (SQLException e) {
					
					e.printStackTrace();
				} 
			
			return ListComp;
		}
		
	public List<Developpeur> FormerEquipe(Projet prj) {
			
			
	        int IdProjet = prj.getId();
			List<Developpeur> ListDev = new ArrayList<>();  // Declarer la liste a recuperer  :
			 
			 String myReq ="select  DISTINCT  user.Nom_complet , user.UserID  \r\n"
			 		+ "from user , technologie , developpeur , competance , projet , techprojet\r\n"
			 		+ "where user.UserID=developpeur.DeveloppeurID \r\n"
			 		+ "and technologie.TechnologieID=competance.TechnologieID \r\n"
			 		+ "and competance.DeveloppeurID=developpeur.DeveloppeurID\r\n"
			 		+ "and projet.ProjetID=techprojet.ProjetID\r\n"
			 		+ "and technologie.TechnologieID=techprojet.TechnologieID\r\n"
			 		+ "and projet.ProjetID=?"; 
			 
			 try(PreparedStatement st=cnx.prepareStatement(myReq))
				{
				      st.setInt(1, IdProjet); 
				      ResultSet rs =st.executeQuery();

						while(rs.next())
						{
							
							Developpeur dev = new Developpeur();
							dev.setNom_complet(rs.getString("user.Nom_complet"));
							dev.setId(rs.getInt("user.UserID"));
							ListDev.add(dev);
							
							
						}
				} catch (SQLException e) {
					
					e.printStackTrace();
				} 
			
			return ListDev;
		}




	@Override
	public void AddEquipe(Projet prj, Developpeur dev) {
		int IdProjet = prj.getId();
	    int iddev=dev.getId();
	   
		String Req ="INSERT INTO equipe (DeveloppeurID, ProjetID) VALUES (?,?)" ;
		try(PreparedStatement st=cnx.prepareStatement(Req))
		{
				     st.setInt(1,iddev);
				     st.setInt(2,IdProjet );
		            st.executeUpdate();
		            

		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		
		String  nom=getNomComplet(iddev);
		String NomProjet = GetNomProjet(IdProjet);
		String sql1 = "INSERT INTO user_notifications (UserID,Notification) VALUES (?,?)";
		String text="Bonjour "+nom+" avez ete affecte a le projet " + NomProjet;
		
		
				try(PreparedStatement st3=cnx.prepareStatement(sql1))
	{
					
			    st3.setInt(1,iddev);
			    st3.setString(2,text);
			  
			     
				   st3.executeUpdate();
		         

	}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Erreur de catch : " + e.getMessage());
			         
				}

	}





	@Override
	public ArrayList<Developpeur> EquipeMember(Projet p) {
		
		
		
		int IdProjet = p.getId();
		
		ArrayList<Developpeur> ListDev = new ArrayList<>();  
		 
		 String myReq ="select user.Nom_complet from user , developpeur , equipe , projet\r\n"
		 		+ "where user.UserID=developpeur.DeveloppeurID\r\n"
		 		+ "and equipe.DeveloppeurID=developpeur.DeveloppeurID\r\n"
		 		+ "and equipe.ProjetID=projet.ProjetID\r\n"
		 		+ "and projet.ProjetID=?"; 
		 
		 try(PreparedStatement st=cnx.prepareStatement(myReq))
			{
			      st.setInt(1, IdProjet); 
			      ResultSet rs =st.executeQuery();

					while(rs.next())
					{
						
						Developpeur dev = new Developpeur();
						dev.setNom_complet(rs.getString("user.Nom_complet"));
						
						ListDev.add(dev);
						
						
					}
			} catch (SQLException e) {
				
				e.printStackTrace();
			} 
		
		 
		  
		return  ListDev;
	}



	@Override
	public void AddDateRn(Projet p) {

		int IdProjet = p.getId();
	    java.sql.Date date=p.getDateDeReunion();
	    
		String Req ="UPDATE projet set Datedereunion= ? WHERE ProjetID =? ;" ;
		try(PreparedStatement st=cnx.prepareStatement(Req))
		{
				     st.setDate(1, date);
				     st.setInt(2, IdProjet);
		            st.executeUpdate();

		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		
	}


	@Override
	public String getNomComplet(int userID) {
	       String nomComplet = null;
	      String sql = "SELECT Nom_complet FROM user WHERE UserID = ?";
	     try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
	                     preparedStatement.setInt(1, userID);

	 
	 try (ResultSet resultSet = preparedStatement.executeQuery()) {
	   
	     if (resultSet.next()) {
	         nomComplet = resultSet.getString("Nom_complet");
	     }
	 }
	}

	catch (SQLException e) {
	 	e.printStackTrace();
			}

	return nomComplet ;
	}


	@Override
	public String GetNomProjet(int idProjet) {
		 String nomProjet= null;
	     String sql = "SELECT Nom from Projet WHERE ProjetID = ?";
	    try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
	                    preparedStatement.setInt(1, idProjet);


	try (ResultSet resultSet = preparedStatement.executeQuery()) {
	  
	    if (resultSet.next()) {
	    	nomProjet = resultSet.getString("Nom");
	    }
	}
	}

	catch (SQLException e) {
		e.printStackTrace();
			}


		return nomProjet;
	}

		
	@Override
	public int getDeveloppeur(String nom) {
		int idDevelopeur=0 ;
		String requete = "SELECT UserID FROM user WHERE Nom_complet= ?";
	    try {
	        PreparedStatement st = cnx.prepareStatement(requete);
	        st.setString(1, nom); 

	        ResultSet resultat = st.executeQuery();
	        if(resultat.next()) {
	        	idDevelopeur=resultat.getInt("UserID");
	        }
	        
//	        getListeidProjet(idDevelopeur);
	        return idDevelopeur;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return idDevelopeur;
	}


	@Override
	public ArrayList<Integer>  getListeidProjet(int idDevelopeur) {
		// TODO Auto-generated method stub
		 String requete = "SELECT ProjetID  FROM equipe WHERE DeveloppeurID = ?";
		 ArrayList<Integer> listeIDProject=new ArrayList<>();
		 try {
		        PreparedStatement st = cnx.prepareStatement(requete);
		        st.setInt(1, idDevelopeur); 

		        ResultSet resultat = st.executeQuery();
		        while(resultat.next()) {
		        	listeIDProject.add(resultat.getInt("ProjetID"));
		        }
		        return listeIDProject;
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		return listeIDProject;
	}

	@Override
	public ArrayList<Projet> getProject(String nom) {
		System.out.println("appel getProjet de la classe data");
	    ArrayList<Projet> listeProjet=new ArrayList<>();
	    int idDeveloppeur=getDeveloppeur(nom);
	    System.err.println("eztgert erz yez yer yu"+ idDeveloppeur);
	    ArrayList<Integer> listeIDProjets=getListeidProjet(idDeveloppeur);
	    for (int idProjet : listeIDProjets) {
	    	String requete = "SELECT * FROM projet WHERE ProjetID = ?";
		    try {
		        PreparedStatement st = cnx.prepareStatement(requete);
		        st.setInt(1, idProjet); 
		        ResultSet resultat = st.executeQuery();
		        if(resultat.next()) {
		        	Projet p=new Projet();
		        	p.setId(idProjet);
		        	p.setNom(resultat.getString("Nom"));
		        	p.setDescription(resultat.getString("Description"));
		        	p.setDateDeDebut(resultat.getDate("Datededebut"));
		        	p.setDateDeLivraison(resultat.getDate("Datedelivraison"));
		        	p.setDateDeReunion(resultat.getDate("Datedereunion"));
		        	Client client= getClient(resultat.getString("CINClient"));
		        	p.setClient(client);
		        	ChefProjet chef_projet=getChefProjet(resultat.getInt("ChefDeProjetID"));
		        	p.setChefProjet(chef_projet);
		        	Methodologie methodologie=getMethodologie(idProjet);
		        	p.setMethodologie(methodologie);
		        	ArrayList<Technologie> technologies=getTechnologies(idProjet);
		        	p.setTechnologie(technologies);
		        	
		        	ArrayList<Service> services=getServices(idProjet);
		        	for(Service service : services) {
		        		service.setTaches(getTaches(service.getId()));
		        	}
		        	p.setService(services);

		        	listeProjet.add(p);
		        	
		        }
		       
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	    }
	    
	    return listeProjet;
	}





	@Override
	public Client getClient(String string) {
		// TODO Auto-generated method stub
		Client client=new Client();
		String requete = "SELECT * FROM client WHERE CIN = ?";
		 try {
		        PreparedStatement st = cnx.prepareStatement(requete);
		        st.setString(1, string); 
		        ResultSet resultat = st.executeQuery();
		        if(resultat.next()) {
		        	client.setNom_complet(resultat.getString("NomComplet"));
		        	client.setAdresse(resultat.getString("Adresse"));
		        	client.setTel(resultat.getString("Tel"));
		        }
		        return client;
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		return client;
	}

	@Override
	public ChefProjet getChefProjet(int id) {
		// TODO Auto-generated method stub
		ChefProjet chefProjet=new ChefProjet();
		String requete = "SELECT Nom_complet FROM user WHERE UserID=?";
		 try {
		        PreparedStatement st = cnx.prepareStatement(requete);
		        st.setInt(1, id);
		        ResultSet resultat = st.executeQuery();
		        
		        	if(resultat.next()) {
		        		chefProjet.setNom_complet(resultat.getString("Nom_complet"));
		        	}
		        	
		        return chefProjet;
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		return chefProjet;
	}

	@Override
	public User getInfoDev(String nom) {
		// TODO Auto-generated method stub
		User user=new User();
		String requete = "SELECT * FROM user WHERE Nom_complet= ?";
	    try {
	        PreparedStatement st = cnx.prepareStatement(requete);
	        st.setString(1, nom); 

	        ResultSet resultat = st.executeQuery();
	       if(resultat.next()) {
	    	   user.setId(resultat.getInt("UserID"));
		        user.setNom_complet(nom);
		        user.setEmail(resultat.getString("Email"));
		        user.setTel(resultat.getString("Tel"));
	       }
	        return user;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return user;
	}

	@Override
	public ArrayList<Competance> getCompetance(User dev) {
		// TODO Auto-generated method stub
		
		ArrayList<Competance> competances=new ArrayList<>();
		String requete = "SELECT * FROM competance WHERE DeveloppeurID= ?";
	    try {
	        PreparedStatement st = cnx.prepareStatement(requete);
	        st.setInt(1, dev.getId()); 
	        ResultSet resultat = st.executeQuery();
	        while(resultat.next()) {
	        	Competance competance=new Competance();
	        	 competance.setId(resultat.getInt("CompetanceID"));
	 	        competance.setNiveau(resultat.getString("Niveau"));
	 	        competance.setTechnologie(this.getTechnologie(resultat.getInt("TechnologieID")));
	 	        competance.setDeveloppeur(null);
	 	        competances.add(competance);
	        }
	       
	        return competances;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return competances;
	}

	@Override
	public Technologie getTechnologie(int id) {
		// TODO Auto-generated method stub
		Technologie technologie=new Technologie();
		String requete = "SELECT * FROM technologie WHERE TechnologieID= ?";
	    try {
	        PreparedStatement st = cnx.prepareStatement(requete);
	        st.setInt(1, id); 
	        ResultSet resultat = st.executeQuery();
	       if(resultat.next()) {
	    	   technologie.setNom(resultat.getString("Nom"));
	       }
	        return technologie;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return technologie;
	}

	@Override
	public ArrayList<Service> getServices(int projetid) {
		// TODO Auto-generated method stub
		ArrayList<Service> services=new ArrayList<>();
		String requete = "SELECT * FROM service WHERE ProjetID= ?";
	    try {
	        PreparedStatement st = cnx.prepareStatement(requete);
	        st.setInt(1, projetid); 
	        ResultSet resultat = st.executeQuery();
	        while(resultat.next()) {
	        	Service service=new Service();
	        	service.setId(resultat.getInt("ServiceID"));
	        	service.setDescrip(resultat.getString("Descrip"));
	        	services.add(service);
	        }
	       
	        return services;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return services;
	}

	@Override
	public ArrayList<Tache> getTaches(int serviceID) {
		// TODO Auto-generated method stub
		ArrayList<Tache> taches=new ArrayList<>();
		String requete = "SELECT * FROM tache WHERE ServiceID= ?";
	    try {
	        PreparedStatement st = cnx.prepareStatement(requete);
	        st.setInt(1, serviceID); 
	        ResultSet resultat = st.executeQuery();
	        while(resultat.next()) {
	        	Tache tache=new Tache();
	        	tache.setId(resultat.getInt("TacheID"));
	        	tache.setNom(resultat.getString("Nom"));
	        	tache.setDescrip(resultat.getString("Descrip"));
	        	tache.setAvancement(resultat.getInt("Avancement"));
	        	tache.setUser(getInfoDev(resultat.getInt("DeveloppeurID")));;
	        	taches.add(tache);
	        }
	       
	        return taches;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return taches;
	}

	@Override
	public ArrayList<Technologie> getTechnologies(int idProjet) {
		// TODO Auto-generated method stub
		ArrayList<Technologie> technologies=new ArrayList<>();
		
		String requete = "SELECT t.* "
				+ "from techprojet tp,technologie t "
				+ "WHERE tp.ProjetID=? "
				+ "and tp.TechnologieID=t.TechnologieID;";
	    try {
	        PreparedStatement st = cnx.prepareStatement(requete);
	        st.setInt(1, idProjet); 
	        ResultSet resultat = st.executeQuery();
	       while(resultat.next()) {
	    	   Technologie technologie=new Technologie();
	    	   technologie.setNom(resultat.getString("Nom"));
	    	   technologies.add(technologie);
	       }
	        return technologies;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return technologies;
	}

	@Override
	public Methodologie getMethodologie(int idProjet) {
		// TODO Auto-generated method stub
		Methodologie methodologie=new Methodologie();
		String requete = "SELECT m.* "
				+ "FROM methodologie m , projet p "
				+ "WHERE p.ProjetID=? "
				+ "and p.MethodologieID=m.MethodologieID;";
	    try {
	        PreparedStatement st = cnx.prepareStatement(requete);
	        st.setInt(1, idProjet); 
	        ResultSet resultat = st.executeQuery();
	       if(resultat.next()) {
	    	   methodologie.setNom(resultat.getString("Nom"));
	       }
	        return methodologie;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return methodologie;
	}

	@Override
	public ArrayList<Integer> chefAndClinetIDs(int projetid) {
		// TODO Auto-generated method stub
		ArrayList<Integer> ids=new ArrayList<>();
		String requete = "SELECT ChefDeProjetID,CINClient FROM projet WHERE ProjetID = ?";

		try {
			PreparedStatement st=cnx.prepareStatement(requete);;
			st.setInt(1, projetid); 
	        ResultSet resultat = st.executeQuery();
			if(resultat.next()) {
				ids.add(resultat.getInt("ChefDeProjetID"));
				ids.add(resultat.getInt("CINClient"));
				
			}
			return ids;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
		
		return ids;
	}

	@Override
	public User getInfoDev(int idDve)  {
		// TODO Auto-generated method stub
		User user=new User();
		String requete = "SELECT * FROM user WHERE UserID= ?";
	    try {
	        PreparedStatement st = cnx.prepareStatement(requete);
	        st.setInt(1, idDve); 

	        ResultSet resultat = st.executeQuery();
	       if(resultat.next()) {
	    	   user.setId(resultat.getInt("UserID"));
		        user.setNom_complet(resultat.getString("Nom_complet"));
		        user.setEmail(resultat.getString("Email"));
		        user.setTel(resultat.getString("Tel"));
	       }
	        return user;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return user;
	}

	@Override
	public int insertTechnologie(String nom) {
		// TODO Auto-generated method stub
		String requete = "INSERT INTO technologie (Nom) VALUES (?) ";
		
	     try {
	    	 PreparedStatement st = cnx.prepareStatement(requete,Statement.RETURN_GENERATED_KEYS);
	    	 st.setString(1, nom);
	    	    int rowsAffected = st.executeUpdate();

	    	    if (rowsAffected > 0) {
	    	        ResultSet generatedKeys = st.getGeneratedKeys();
	    	        if (generatedKeys.next()) {
	    	            return generatedKeys.getInt(1); // Récupérer la clé générée (ici, l'ID)
	    	        }
	    	    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return -1;
	}

	@Override
	public void insertCompetance(int idTech, int idDev,String niveau) {
		// TODO Auto-generated method stub
		String requete = "INSERT INTO competance(TechnologieID,DeveloppeurID,Niveau)  VALUES (?,?,?) ";
		 try {
	    	 PreparedStatement st = cnx.prepareStatement(requete);
			 st.setInt(1, idTech);
			 st.setInt(2, idDev);
			 st.setString(3, niveau);
			 st.executeUpdate();
			  // Récupérer les clés générées
		        ResultSet generatedKeys = st.getGeneratedKeys();
		       
		  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@Override
	public void updateUser(String nom, String email, String tel,int idDev) {
		// TODO Auto-generated method stub
		 String requete = "UPDATE user " +
	             "SET Nom_complet=?, Email=?, Tel=? " +
	             "WHERE UserID=? ";

			try {
			    PreparedStatement st = cnx.prepareStatement(requete);
			    st.setString(1, nom);
			    st.setString(2, email);
			    st.setString(3, tel);
			    st.setInt(4, idDev);
			
			    int rowsAffected = st.executeUpdate();
			
			   
			} catch (SQLException e) {
			    e.printStackTrace();
			    // Gérer l'exception ou afficher un message d'erreur
			}
	}

	@Override
	public void updateCompetance( int idDev, String niveau,String competance) {
		// TODO Auto-generated method stub
		String requeteSelect = "SELECT TechnologieID FROM technologie WHERE Nom = ?";
		
		try {
		    PreparedStatement stSelect = cnx.prepareStatement(requeteSelect);
		    stSelect.setString(1, competance);
		    ResultSet rs = stSelect.executeQuery();

		    if (rs.next()) {
		        int idTechnologie = rs.getInt("TechnologieID");

		        // Mise à jour du niveau pour la compétence spécifique du développeur
		       
		        String requeteUpdate = "UPDATE competance SET Niveau = ? " +
		                               "WHERE TechnologieID = ? AND DeveloppeurID = ?";
		        PreparedStatement stUpdate = cnx.prepareStatement(requeteUpdate);
		        stUpdate.setString(1, niveau);
		        stUpdate.setInt(2, idTechnologie);
		        stUpdate.setInt(3, idDev); // Remplacez idDev par l'ID du développeur concerné

		        int rowsAffected = stUpdate.executeUpdate();

		       
		    } else {
		       // System.out.println("La compétence " + competance + " n'existe pas.");
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		    // Gérer l'exception ou afficher un message d'erreur
		}
	}

	@Override
	public Tache getTacheDetails(int tacheId) {
		// TODO Auto-generated method stub
		Tache tache=new Tache();
		String requete = "SELECT * FROM tache WHERE TacheID= ?";
	    try {
	        PreparedStatement st = cnx.prepareStatement(requete);
	        st.setInt(1, tacheId); 
	        ResultSet resultat = st.executeQuery();
	        if(resultat.next()) {
	        	tache.setId(resultat.getInt("TacheID"));
	        	tache.setNom(resultat.getString("Nom"));
	        	tache.setDescrip(resultat.getString("Descrip"));
	        	tache.setAvancement(resultat.getInt("Avancement"));
	        	tache.setUser(getInfoDev(resultat.getInt("DeveloppeurID")));;
	        	
	        }
	       
	        return tache;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return tache;
	}

	@Override
	public boolean updateTache(int tacheId, String nouvelleDescription, int nouvelAvancement) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				 String requete = "UPDATE tache " +
		                 "SET Avancement=?, Descrip=?" +
		                 "WHERE TacheID=? ";

					try {
					    PreparedStatement st = cnx.prepareStatement(requete);
					    st.setInt(1, nouvelAvancement);
					    st.setString(2, nouvelleDescription);
					    st.setInt(3, tacheId);
					  
					
					    int rowsAffected = st.executeUpdate();
					
					    if (rowsAffected > 0) {
					        System.out.println("Mise à jour de la tache effectuée pour " + tacheId);
					    } else {
					        System.out.println("Aucune mise à jour effectuée pour la tache " + tacheId);
					    }
					    return true;
					} catch (SQLException e) {
					    e.printStackTrace();
					    // Gérer l'exception ou afficher un message d'erreur
					}
		return false;
	}
	
	

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	    public void AddProject(Projet P) {
		String sql = "Insert into projet(Nom,Description,Datededebut,Datedelivraison,Joursdedeveloppement,CINClient,ChefDeProjetID) values (?,?,?,?,?,?,?)";
		try(PreparedStatement st2=cnx.prepareStatement(sql))
{
			st2.setString(1,P.getNom());
 		    st2.setString(2,P.getDescription());
 		    st2.setDate(3,P.getDateDeDebut());
 		    st2.setDate(4,P.getDateDeLivraison());
 		    st2.setInt(5,P.getJoursDeDeveloppement());
 		    st2.setString(6,P.getClient().getCin());
 		    st2.setInt(7,P.getChefProjet().getId());
 		    
 		    
		st2.executeUpdate();
//		
	}

		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String  nom=getNomComplet(P.getChefProjet().getId());
		String sql1 = "INSERT INTO user_notifications (UserID,Notification) VALUES (?,?)";
		String text="Bonjour "+nom+" avez ete affecte a le projet "+P.getNom();
		//System.out.println(text+nom);
				try(PreparedStatement st3=cnx.prepareStatement(sql1))
    {
			st3.setInt(1,P.getChefProjet().getId());
 		    st3.setString(2,text);
 		  
 	
		    st3.executeUpdate();
		   // System.out.println("hhhhhhhhhhhhhhh");
	}

		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		
	


//	    
	    
	    
	    public ArrayList<Projet> getAllProjects() {
	    	ArrayList<Projet> projects = new ArrayList<>();

	        String query = "SELECT ProjetID,Nom,Datededebut,Datedelivraison,Joursdedeveloppement,Description,CINClient,c.NomComplet ,u.Nom_complet as nn, u.UserID as idchef FROM projet p ,client c, user u where p.CINClient =c.CIN and p.ChefDeProjetID=u.UserID";

	        try (PreparedStatement statement = cnx.prepareStatement(query);
	             ResultSet resultSet = statement.executeQuery()) {

	            while (resultSet.next()) {
	                Projet projet = new Projet();
	               
	                projet.setId(resultSet.getInt("ProjetID"));
	                projet.setNom(resultSet.getString("Nom"));
	                projet.setDateDeDebut(resultSet.getDate("Datededebut"));
	                projet.setDateDeLivraison(resultSet.getDate("Datedelivraison"));
	                projet.setDescription(resultSet.getString("Description"));
	                String  nbrJS=resultSet.getString("Joursdedeveloppement");
	                int nbrJ = Integer.parseInt(nbrJS);
	                projet.setJoursDeDeveloppement(nbrJ);
	                Client client=new Client();
	                client.setCin(resultSet.getString("CINClient"));
	                client.setNom_complet(resultSet.getString("NomComplet"));
	                projet.setClient(client);
	                ChefProjet chef=new ChefProjet();
	                chef.setId(resultSet.getInt("idchef"));
	                chef.setNom_complet(resultSet.getString("nn"));
	                projet.setChefProjet(chef);
	                projects.add(projet);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return projects;
	    }
	    
	    
	    
		public  ArrayList<User> getChef() {
			
			 ArrayList<User> listChef = new ArrayList<>(); 
			
			String Req ="select Nom_complet ,UserID from user , chefdeprojet where user.UserID = chefdeprojet.ChefDeProjetID " ;
			try(PreparedStatement st=cnx.prepareStatement(Req))
			{
			     
			      ResultSet rs =st.executeQuery();

					while(rs.next())
					{
						
						User usr=new User();
						
						usr.setNom_complet(rs.getString("Nom_complet"));
						usr.setId(rs.getInt("UserID"));
						listChef.add(usr);
						
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		return listChef;
		}
		
		
		
		
		
		public ArrayList<Client> getclients() {
		    ArrayList<Client> listclients = new ArrayList<>();

		    String req = "SELECT NomComplet, CIN, Tel,Adresse FROM Client";
		    Statement st;
		    try {  st = cnx.createStatement();
		    		
		         ResultSet rs = st.executeQuery(req);
		     	
		        while (rs.next()) {
		            Client client = new Client();
		            client.setNom_complet(rs.getString("NomComplet"));
		            client.setCin(rs.getString("CIN"));
		            client.setAdresse(rs.getString("Adresse"));
		            client.setTel(rs.getString("Tel"));
		            listclients.add(client);
		        }

		    } catch (SQLException e) {
		        // Log or handle the exception appropriately
		    	
		    }

		    return listclients;
		}
		
		
		
		public boolean deleteProject(int id) {
			String Req ="DELETE FROM projet WHERE ProjetID=?" ;
			try(PreparedStatement st=cnx.prepareStatement(Req))
			{
				st.setInt(1,id);
				int rowsAffected = st.executeUpdate();

                return rowsAffected > 0;
				
			} catch (SQLException e) {
				System.out.println("problem de statement:" + e.getMessage());
				 return false;
			}
		}
		
		
		public void addClient(Client c) {
			String req = "Insert into client(Adresse,CIN,NomComplet,Tel) values (?,?,?,?)";
			try(PreparedStatement st=cnx.prepareStatement(req))
			{
				st.setString(1,c.getAdresse());
				st.setString(2,c.getCin());
				st.setString(3,c.getNom_complet());
				st.setString(4,c.getTel());
				
				
				
				st.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("problem de statement:" + e.getMessage());
			}
		}
		
	
		
		public void modifyProject(Projet p) {
			String Req ="update projet set Description=?,Datededebut=?,Datedelivraison=?,Joursdedeveloppement=?,ChefDeProjetID=? WHERE ProjetID=?" ;
			try(PreparedStatement st=cnx.prepareStatement(Req))
			{
				
				
	 		    st.setString(1,p.getDescription());
	 		    st.setDate(2,p.getDateDeDebut());
	 		    st.setDate(3,p.getDateDeLivraison());
	 		    st.setInt(4,p.getJoursDeDeveloppement());
	 		    st.setInt(5,p.getChefProjet().getId());
	 		    st.setInt(6,p.getId());
//	 		   System.out.println(p.getNom());
//	 		  System.out.println(p.getDescription());
//	 	     	 System.out.println(p.getDateDeDebut());
//	 		   System.out.println(p.getDateDeLivraison());
//	 		   System.out.println(p.getJoursDeDeveloppement());
//	 		   System.out.println(p.getChefProjet().getId());
//	 		   System.out.println(p.getId());
		        int rowsAffected = st.executeUpdate();

		          if (rowsAffected > 0) {
		                System.out.println("La mise à jour du projet a été effectuée avec succès.");
		            } else {
		                System.out.println("La mise à jour du projet a échoué.");
		            }
	       
			} catch (SQLException e) {
				System.out.println("problem de statement:" + e.getMessage());
				
			}
		}
		
//		 public String getNomComplet(int userID) {
//		        String nomComplet = null;
//		 String sql = "SELECT Nom_complet FROM user WHERE UserID = ?";
//         try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
//             preparedStatement.setInt(1, userID);
//
//             
//             try (ResultSet resultSet = preparedStatement.executeQuery()) {
//               
//                 if (resultSet.next()) {
//                     nomComplet = resultSet.getString("Nom_complet");
//                 }
//             }
//         }
//
//         catch (SQLException e) {
//        	 	e.printStackTrace();
//         		}
//
//     return nomComplet; 
//     }
		 @Override
			public ArrayList<Projet> GetAllProjet(int chefprj) {
				

				
				ArrayList<Projet> ListeProjets = new ArrayList<>();
				
				
				 String myReq ="select projet.* , client.NomComplet , methodologie.Nom\r\n"
				 		+ "from projet , client , methodologie \r\n"
				 		+ "WHERE projet.MethodologieID=methodologie.MethodologieID\r\n"
				 		+ "and client.CIN=projet.CINClient\r\n"
				 		+ "and projet.MethodologieID is not null "
				 		+ " AND projet.ChefDeProjetID = ?";  
					 
					 try(PreparedStatement st=cnx.prepareStatement(myReq))
						{
						      st.setInt(1, chefprj); 
						      ResultSet rs =st.executeQuery();
						    

								while(rs.next())
								{
									Projet prj = new Projet();
									Client client = new Client();
									Methodologie meth = new Methodologie();
									prj.setDateDeDebut(rs.getDate("Datededebut"));
									prj.setDateDeLivraison(rs.getDate("Datedelivraison"));
									prj.setDateDeReunion(rs.getDate("Datedereunion"));
									prj.setNom(rs.getString("nom"));
									prj.setId(rs.getInt("ProjetID"));
									prj.setDescription(rs.getString("Description"));
									prj.setJoursDeDeveloppement(rs.getInt("Joursdedeveloppement"));
									client.setNom_complet(rs.getString("NomComplet"));
									prj.setClient(client);
									meth.setNom(rs.getString("methodologie.Nom"));
								    prj.setMethodologie(meth);
								    
								    ListeProjets.add(prj);
								}
								    
								
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
				
				return ListeProjets;
			}

			@Override
			public ArrayList<Technologie> GetTechnologie(Projet p) {
				
				int IdProjet = p.getId();
				ArrayList<Technologie> ListeTech = new ArrayList<>();
				
				

				 String myReq ="select projet.ProjetID , technologie.Nom\r\n"
				 		+ "from projet , techprojet , technologie\r\n"
				 		+ "where projet.ProjetID= techprojet.ProjetID\r\n"
				 		+ "and technologie.TechnologieID=techprojet.TechnologieID\r\n"
				 		+ "and projet.ProjetID=?";  
					 
					 try(PreparedStatement st=cnx.prepareStatement(myReq))
						{
						      st.setInt(1, IdProjet);
						      ResultSet rs =st.executeQuery();
						    

								while(rs.next())
								{
									Technologie tech = new Technologie();
									tech.setNom(rs.getString("Nom"));
									ListeTech.add(tech);
									
								}
								
								    
								
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
				
				
				return ListeTech;
			}

			
			
			@Override
			public ArrayList<Developpeur> EquipeMemebre(Projet p) {
				
				int IdProjet = p.getId();
				ArrayList<Developpeur> ListeDev = new ArrayList<>();
				
				

				 String myReq ="Select user.Nom_complet , user.UserID\r\n"
				 		+ "from user , developpeur , equipe , projet \r\n"
				 		+ "where user.UserID=developpeur.DeveloppeurID\r\n"
				 		+ "and equipe.ProjetID=projet.ProjetID\r\n"
				 		+ "and developpeur.DeveloppeurID=equipe.DeveloppeurID\r\n"
				 		+ "and projet.ProjetID=?";  
					 
					 try(PreparedStatement st=cnx.prepareStatement(myReq))
						{
						      st.setInt(1, IdProjet);
						      ResultSet rs =st.executeQuery();
						    

								while(rs.next())
								{
									Developpeur dev = new Developpeur();
									dev.setId(rs.getInt("user.UserID"));
									dev.setNom_complet(rs.getString("user.Nom_complet"));
									ListeDev.add(dev);
									
								}	
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
				
				
				return ListeDev;
			}

			@Override
			public int AjouterService(Service service, Projet p) {
				int IdProjet = p.getId();
			    int durre = service.getDurreeEnjours() ; 
			    String desc = service.getDescrip();
			    int generatedId = -1;
			    
				String Req ="insert into service(Descrip , Durreenjours , ProjetID) values (? , ? , ?)" ;
				try(PreparedStatement st=cnx.prepareStatement(Req , Statement.RETURN_GENERATED_KEYS))
				{
						     st.setString(1, desc);
						     st.setInt(2, durre);
						     st.setInt(3, IdProjet);
				            st.executeUpdate();
			
				            ResultSet rs = st.getGeneratedKeys();
				            if (rs.next()) {
				                generatedId = rs.getInt(1);
				            }
				} catch (SQLException e) {
					
					e.printStackTrace();
				} 
				
				
				return  generatedId ;
			}

			@Override
			public void AjouterTache(Tache tache) {
				
			    Service service = tache.getService();
			    int idService =  service.getId() ;
			    String desc = tache.getDescrip() ;
			    String nom = tache.getNom();
			    int idDev =tache.getUser().getId();
			    
				String Req ="INSERT into tache(Nom , Descrip , DeveloppeurID , ServiceID) VALUES (? , ? , ? , ?)" ;
				try(PreparedStatement st=cnx.prepareStatement(Req ))
				{
						     st.setString(1, nom);
						     st.setString(2, desc);
						     st.setInt(3, idDev);
						     st.setInt(4, idService);
				              st.executeUpdate();
			
				           
				} catch (SQLException e) {
					
					e.printStackTrace();
				} 
				
				
				
			}
			
}
