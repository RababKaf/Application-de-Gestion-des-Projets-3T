package dataLayer;

import java.sql.ResultSet;
import java.util.ArrayList;

import models.User_Notifications;

public interface InterfaceDataUserNotifficationManager {
	public ArrayList<User_Notifications> getNot(int id);
	public int Update(int a);
	public ArrayList<User_Notifications> getNotReaded(int id);
}
