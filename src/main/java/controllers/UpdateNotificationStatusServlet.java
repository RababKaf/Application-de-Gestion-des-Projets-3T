package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import businessLayer.UserNotificationManager;

/**
 * Servlet implementation class UpdateNotificationStatusServlet
 */
public class UpdateNotificationStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateNotificationStatusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    PrintWriter out = response.getWriter();

        UserNotificationManager um = new UserNotificationManager();

        String notificationId = request.getParameter("notificationId");

        if (notificationId != null && !notificationId.isEmpty()) {
            try {
                int parsedNotificationId = Integer.parseInt(notificationId);
                int rowsAffected = um.Update(parsedNotificationId);

                // You can send a response back to the client if needed
                out.println("Rows affected: " + rowsAffected);
            } catch (NumberFormatException e) {
                out.println("Invalid notificationId format");
                e.printStackTrace(); // Log the exception for debugging purposes
            }
        } else {
            out.println("Missing or empty notificationId parameter");
        }
    }

}
