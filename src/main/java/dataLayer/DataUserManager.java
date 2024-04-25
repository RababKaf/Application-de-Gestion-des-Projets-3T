package dataLayer;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

import dataLayer.Connexion;

public class DataUserManager implements InterfaceDataUserManager  {
	private Connection cnx;
	public DataUserManager() {
		super();
		 Connexion connexion = new Connexion();
	      this.cnx = connexion.myCnx;

	}
	
	   public boolean authenticateUser(String userEmail, String password) {
	        if (userExists(userEmail)) {
	          
	            String storedPassword = getPassword(userEmail);
	            return password.equals(storedPassword);
	        }

	        return false;
	    }

	    // Helper method to check if the user with the provided email exists
	    public boolean userExists(String userEmail) {
	       
	        ResultSet resultat=null;
			  String requette = "SELECT COUNT(*) as user_count FROM user WHERE Email='"+userEmail+"'";
			    System.out.println(requette);
			
			    	
						Statement st;
						try {
							st = cnx.createStatement();
							resultat=st.executeQuery(requette);	
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
					
			    
	            try (ResultSet resultSet = resultat) {
	                if (resultSet.next()) {
	                    int userCount = resultSet.getInt("user_count");
	                    return userCount > 0;
	                }
	            }
	         catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return false;
}

	    // Helper method to get the stored password for the user with the provided email
	    public String getPassword(String userEmail) {
	    
	        ResultSet resultat=null;
			  String requette = "SELECT mot_de_passe FROM user WHERE Email='"+userEmail+"'";
			    
			    	
						Statement st;
						try {
							st = cnx.createStatement();
							resultat=st.executeQuery(requette);	
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}	
	            
	            try (ResultSet resultSet =resultat) {
	                if (resultSet.next()) {
	                    return resultSet.getString("mot_de_passe");
	                }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return null;
	    }

		@Override
		public int findRole(String userEmail, String password) {
			ResultSet resultat=null;
			int nb=0;
			  String requette = "SELECT Role FROM user WHERE Email='"+userEmail+"'"+" and mot_de_passe='"+password+"'";
			  
			
			    	
						Statement st;
						try {
							st = cnx.createStatement();
							resultat=st.executeQuery(requette);	
						    int i;
						    while(resultat.next())
						    { 
						    i=resultat.getInt(1);
						    nb=i;
						       }
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
					
						System.out.println(nb);		
return nb;

	     
}
		@Override
		public int findId(String userEmail, String password) {
			ResultSet resultat=null;
			int nb=0;
			  String requette = "SELECT UserID FROM user WHERE Email='"+userEmail+"'"+" and mot_de_passe='"+password+"'";
			  
			
			    	
						Statement st;
						try {
							st = cnx.createStatement();
							resultat=st.executeQuery(requette);	
						    int i;
						    while(resultat.next())
						    { 
						    i=resultat.getInt(1);
						    nb=i;
						       }
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
					
						System.out.println(nb);		
return nb;

	     
}
		@Override
		public String findName(String userEmail, String password) {
			// TODO Auto-generated method stub
			ResultSet resultat=null;
			String nom=null;
			  String requette = "SELECT Nom_complet FROM user WHERE Email='"+userEmail+"'"+" and mot_de_passe='"+password+"'";
			  
			
			    	
						Statement st;
						try {
							st = cnx.createStatement();
							resultat=st.executeQuery(requette);	
						    String i;
						    while(resultat.next())
						    { 
						    i=resultat.getString(1);
						    nom=i;
						       }
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
					
						System.out.println(nom);		
return nom;
		}
		
		public String findPhoto(String userEmail, String password) {
			// TODO Auto-generated method stub
			ResultSet resultat=null;
			Blob nom=null;
		    String base64Image = null;
			  String requette = "SELECT image FROM user WHERE Email='"+userEmail+"'"+" and mot_de_passe='"+password+"'";
			  
			
			    	
						Statement st;
						try {
							st = cnx.createStatement();
							resultat=st.executeQuery(requette);	
						    Blob i;
						    while(resultat.next())
						    { 
						    i=resultat.getBlob(1);
						    nom=i;

			                // Convert Blob to base64
			                base64Image = convertBlobToBase64(nom);
			            }
			        } catch (SQLException e1) {
			            e1.printStackTrace();
			        } finally {
			            try {
			                if (resultat != null) {
			                    resultat.close();
			                }
			            } catch (SQLException e) {
			                e.printStackTrace();
			            }
			        }

			       
			        return base64Image;
			    }

			    private String convertBlobToBase64(Blob blob) {
			        try {
			            byte[] bytes = blob.getBytes(1, (int) blob.length());
			            return Base64.getEncoder().encodeToString(bytes);
			        } catch (SQLException e) {
			            e.printStackTrace();
			            return null;
			        }
			    }
		
		
		
	}
