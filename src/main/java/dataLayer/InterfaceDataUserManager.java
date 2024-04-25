package dataLayer;

import java.sql.ResultSet;

public interface InterfaceDataUserManager {
	public boolean authenticateUser(String userEmail, String password);
	public boolean userExists(String userEmail);
	public String getPassword(String userEmail);
	public int findRole(String userEmail, String password);
	public String findName(String userEmail, String password);
	public int findId(String userEmail, String password);
	public String findPhoto(String userEmail, String password);
	


}
