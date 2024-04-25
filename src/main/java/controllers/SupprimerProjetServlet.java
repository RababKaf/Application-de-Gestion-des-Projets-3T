package controllers;

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

import businessLayer.*;

/**
 * Servlet implementation class SupprimerProjetServlet
 */
public class SupprimerProjetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerProjetServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		ProjectManager manager = new ProjectManager();
		
        int idProjetASupprimer = Integer.parseInt(request.getParameter("idProjet"));
     
        
        boolean suppressionReussie = manager.deleteProject(idProjetASupprimer);
        String message;
        if (suppressionReussie) 
        	message = "La suppression est effectué avec succès.";
		
		message = "La suppression n'est pas effectué avec succès.";
		request.setAttribute("messageSP", message);
        request.getRequestDispatcher("/ConsulterProjet").forward(request, response);
    }
}
