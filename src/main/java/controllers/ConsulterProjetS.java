package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Methodologie;
import models.Projet;
import models.Technologie;

import java.io.IOException;
import java.util.ArrayList;

import businessLayer.*;

/**
 * Servlet implementation class ConsulterProjetServlet
 */
public class ConsulterProjetS extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ProjectManager manager=new ProjectManager();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsulterProjetS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String nomComplet = request.getParameter("nomcomplet");
		ArrayList<Projet> projets=manager.getProject(nomComplet);
		
		
		
		request.setAttribute("projets", projets);
		request.getRequestDispatcher("/views/devloppeur.jsp").forward(request, response);
		
	}
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nom =(String) request.getAttribute("name");
		String prenom=(String) request.getAttribute("prenom");
		request.getRequestDispatcher("/views/devloppeur.jsp");
		
	}

}