package dataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

//import VIEW.ViewChercher;

public class Connexion {

		public static Connection myCnx;
		String url,pilote;
		//ViewChercher view=new ViewChercher(); 
		public Connexion()  {
		try {

			pilote=new String("com.mysql.cj.jdbc.Driver");
			Class.forName(pilote);
			url = new String("jdbc:mysql://127.0.0.1/projetjee?serverTimezone=UTC");
			 try {
				connect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			} 
		catch( ClassNotFoundException excpt) {
				System.err.println("Erreur du chargement du pilote : " + excpt.getMessage()); 
			                             }
		
		    }
		
		
		public void connect() throws SQLException {
			try {
				myCnx = DriverManager.getConnection(url,"root","");
				//JOptionPane.showMessageDialog(null, "CONNEXION ETABLIE","Connextion",JOptionPane.INFORMATION_MESSAGE);
				System.out.println("braaaaaaaaaaaaaaaaaaaaaaaaavooooo");  
		
			} catch (SQLException excpt) {
				System.err.println("Erreur produise :" + excpt);
			} 		
		}
		
		public void disconnect() 
		{
		
			try {
				myCnx.close();
				JOptionPane.showMessageDialog(null, "Deconexion avec succes","Deconnextion",JOptionPane.INFORMATION_MESSAGE);
		        } 
			catch (SQLException excpt) {
			     excpt.printStackTrace();
			                            }
		}
		
		public ResultSet getAllData() {
			ResultSet resultat=null;
			String requette="SELECT * FROM insc";
			//JOptionPane.showMessageDialog(null, requette);
			try {
				
				Statement st=myCnx.createStatement();	
				resultat=st.executeQuery(requette);	
			
			}

				catch (SQLException excpt) {
					// TODO Auto-generated catch block
					excpt.printStackTrace();}
				
				return resultat;
			
}
		public ResultSet getNot() {
			ResultSet resultat=null;
			  String requette = "SELECT * FROM user_notifications WHERE is_read = false";
			    try {
			    	
						Statement st=myCnx.createStatement();	
						resultat=st.executeQuery(requette);	
			    }
			catch (SQLException excpt) {
				// TODO Auto-generated catch block
				excpt.printStackTrace();}
			
			return resultat;

}
		
		public int Update(int a) {
			
		   String query = "UPDATE user_notifications SET is_read=true WHERE id="+a;
		   
		   System.out.println(query);
			Statement stm;
			try {
				stm = myCnx.createStatement();
				return stm.executeUpdate(query);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return 0;
		}






}