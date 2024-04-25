package dataLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.management.Notification;

import models.User_Notifications;

public class DataUserNotificationManager  implements InterfaceDataUserNotifficationManager {
	private Connection cnx;
	public DataUserNotificationManager() {
		super();
		 Connexion connexion = new Connexion();
	      this.cnx = connexion.myCnx;

		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<User_Notifications> getNot(int id) {
		ResultSet resultat=null;
		ArrayList<User_Notifications> listNot=new ArrayList<User_Notifications>();
		  String requette = "SELECT User_NotificationsID , Notification FROM user_notifications WHERE is_read = false and UserID="+id+" order by Datenot";
		    try {
		    	
					Statement st=cnx.createStatement();	
					resultat=st.executeQuery(requette);
					    while(resultat.next())
					    { 
					    User_Notifications Not=new User_Notifications();
					    Not.setId(resultat.getInt(1));
					    Not.setNotification(resultat.getString(2));
					    listNot.add(Not);
					 
					       }
		    }
		catch (SQLException excpt) {
			// TODO Auto-generated catch block
			excpt.printStackTrace();}
		
		return listNot;

}
	public int Update(int a) {
		
		   String query = "UPDATE user_notifications SET is_read=true WHERE User_NotificationsID="+a;
		   
		   
		   System.out.println(query);
			Statement stm;
			try {
				stm = cnx.createStatement();
				return stm.executeUpdate(query);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return 0;
		}

	@Override
	public ArrayList<User_Notifications>  getNotReaded(int id) {
		ResultSet resultat=null;
		ArrayList<User_Notifications> listNot=new ArrayList<User_Notifications>();
		  String requette = "SELECT User_NotificationsID , Notification FROM user_notifications WHERE is_read = true and UserID="+id+" order by Datenot";
		 
		  try {
		    	
					Statement st=cnx.createStatement();	
					resultat=st.executeQuery(requette);	
					 while(resultat.next())
					    { 
						 User_Notifications Not=new User_Notifications();
						    Not.setId(resultat.getInt(1));
						    Not.setNotification(resultat.getString(2));
						    listNot.add(Not);
					 
					       }
		    }
		catch (SQLException excpt) {
			// TODO Auto-generated catch block
			excpt.printStackTrace();}
		
		return listNot;
	
	}
	
	
	

}


