package models;

import java.util.Date;

/**
 * @author rabab
 * @version 1.0
 * @created 01-d�c.-2023 13:01:04
 */
public class User_Notifications {
    private int id;
	private Date dateNot;
	private boolean is_read = false;
	private String notification;

	public User_Notifications(){

	}

	public void finalize() throws Throwable {

	}
	
	

	public int getId() {
		return id;
	}

	public Date getDateNot() {
		return dateNot;
	}

	public boolean isIs_read() {
		return is_read;
	}

	public String getNotification() {
		return notification;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDateNot(Date dateNot) {
		this.dateNot = dateNot;
	}

	public void setIs_read(boolean is_read) {
		this.is_read = is_read;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}
}//end User_Notifications