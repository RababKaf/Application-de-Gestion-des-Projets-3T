package controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Client;
import models.Projet;
import models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import businessLayer.*;
import dataLayer.DataProjet;

/**
 * Servlet implementation class ConsulterProjet
 */
public class ConsulterProjet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ConsulterProjet() {
        super();
        // TODO Auto-generated constructor stub
    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 DataProjet db = new DataProjet();
			 ProjectManager manager = new ProjectManager();

	        ArrayList<Projet> projects = manager.getAllProjects();
	        ArrayList<User> listChef = manager.getChef();
	        String msg=(String) request.getAttribute("messageSP");
	        request.setAttribute("SP",msg);
	        request.setAttribute("listChef",listChef);
	        request.setAttribute("projects", projects);
	        request.getRequestDispatcher("/views/ConsulterProjet.jsp").forward(request, response);


    }
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 DataProjet db = new DataProjet();
			 ProjectManager manager = new ProjectManager();

	        ArrayList<Projet> projects = manager.getAllProjects();
	        ArrayList<User> listChef = manager.getChef();
	        String msg=(String) request.getAttribute("messageAPS");
            request.setAttribute("SP",msg);
	        request.setAttribute("listChef",listChef);
	        request.setAttribute("projects", projects);
	        request.getRequestDispatcher("/views/ConsulterProjet.jsp").forward(request, response);


   }
	
	
	
}

	


