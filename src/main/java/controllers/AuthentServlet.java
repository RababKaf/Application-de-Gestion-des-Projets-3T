package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import businessLayer.UserManager;


/**
 * Servlet implementation class LoginServlet
 */
public class AuthentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    UserManager manager=new UserManager();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthentServlet() {
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
		 
		        String userEmail = request.getParameter("email");
		        String password = request.getParameter("password");
		        int id=manager.findId(userEmail, password);
		        String image=manager.findPhoto(userEmail, password);
                String name=manager.findName(userEmail, password);
		        if (manager.authenticateUser(userEmail, password)) {
		        	   HttpSession session = request.getSession(true);
			              
		               session.setAttribute("authenticatedUser", userEmail);
		               session.setAttribute("nomcomplet",name );
		               session.setAttribute("id",id);
		               session.setAttribute("image",image);
		         
		            if(manager.findRole(userEmail, password)==1)
		            request.getRequestDispatcher("ConsulterProjet").forward(request, response);
			        if(manager.findRole(userEmail, password)==2)
		            	 request.getRequestDispatcher("ProjectServlet?id="+id).forward(request, response);
                    if(manager.findRole(userEmail, password)==3)
		              request.getRequestDispatcher("EnregistrerAvancementServlet?nomcomplet="+name).forward(request, response);
			     
		        } else {
		            // If authentication fails, you might want to redirect to a login error page
		            response.sendRedirect("/TPl/Error404.jsp");
		        }
		    }

}
