package businessLayer;

import java.sql.ResultSet;
import java.util.ArrayList;

import dataLayer.DataUserNotificationManager;
import models.User_Notifications;

public class UserNotificationManager implements  InterfaceUserNotifficationManager{
	DataUserNotificationManager notM=new DataUserNotificationManager();

	@Override
	public ArrayList<User_Notifications>  getNot(int id) {
	
		return notM.getNot(id);
	}

	@Override
	public int Update(int a) {
		// TODO Auto-generated method stub
		return notM.Update(a);
	}

	@Override
	public ArrayList<User_Notifications>  getNotReaded(int id) {
		// TODO Auto-generated method stub
		return notM.getNotReaded(id);
	}
}
