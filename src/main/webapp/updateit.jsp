<%@ page import="java.sql.*" %>

<%@ page import="businessLayer.UserNotificationManager "%>

<%

System.err.println("hahahhazhfkjazehfkjhaekjfhkjezhfkjhezkjrhhezjhrkjzehjkahah");
UserNotificationManager um=new UserNotificationManager();

 String notificationId = request.getParameter("notificationId");
      
        if (notificationId != null && !notificationId.isEmpty()) {
            int parsedNotificationId = Integer.parseInt(notificationId);
            int rowsAffected = um.Update(parsedNotificationId);}

  
%>
