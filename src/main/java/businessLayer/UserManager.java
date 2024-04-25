package businessLayer;

import dataLayer.DataUserManager;

public class UserManager implements InterfaceUserManager {
 DataUserManager dm=new DataUserManager();
	@Override
	public boolean authenticateUser(String userEmail, String password) {
		// TODO Auto-generated method stub
		return dm.authenticateUser(userEmail, password);
	}

	@Override
	public boolean userExists(String userEmail) {
		// TODO Auto-generated method stub
		return dm.userExists(userEmail);
	}

	@Override
	public String getPassword(String userEmail) {
		// TODO Auto-generated method stub
		return dm.getPassword(userEmail);
	}

	@Override
	public int findRole(String userEmail, String password) {
		// TODO Auto-generated method stub
		return dm.findRole(userEmail, password);
	}

	@Override
	public String findName(String userEmail, String password) {
		// TODO Auto-generated method stub
		return dm.findName(userEmail, password);
	}

	@Override
	public int findId(String userEmail, String password) {
		// TODO Auto-generated method stub
		return dm.findId(userEmail, password);
	}

	@Override
	public String findPhoto(String userEmail, String password) {
		// TODO Auto-generated method stub
		return dm.findPhoto(userEmail, password);
	}

}
