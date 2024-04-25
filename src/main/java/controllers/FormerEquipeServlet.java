package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import businessLayer.*;
import businessLayer.ProjectManager;
import businessLayer.InterfaceProjetManager;
import models.Developpeur;
import models.Projet;

/**
 * Servlet implementation class FormerEquipeServlet
 */
public class FormerEquipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectManager  EM= new ProjectManager();
	private ProjectManager PM= new ProjectManager();
       
   
    public FormerEquipeServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ProjetSelected= request.getParameter("projetID") ;
		String Date = request.getParameter("dateRn");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 

		try {
		    
		    java.util.Date parsedDate = dateFormat.parse(Date);
		    java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
		    EM.AddDateRn(sqlDate, Integer.parseInt(ProjetSelected));
		} catch (ParseException e) {
		   
		    e.printStackTrace(); 
		}
		
		request.setAttribute("successMessage", "Modifications enregistrées avec succès");
		request.getRequestDispatcher("ProjectServlet").forward(request, response);		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String ProjetSelected= request.getParameter("ProjetID") ;
	//	String ProjetNom= request.getParameter("projetName") ;
		
		System.out.println(ProjetSelected);
		String[] DevCheked = request.getParameterValues("DevloppeurSelected");
		ArrayList<Integer> listeDev = new ArrayList<>();
		
		if (DevCheked != null) {
		    for (String dev : DevCheked) {
		       
		    	System.out.println(dev);
		    	listeDev.add(Integer.parseInt(dev));
		    }
		} else {
		   
		    System.out.println("Aucune case à cocher sélectionnée.");
		}
		  EM.AddEquipe(Integer.parseInt(ProjetSelected), listeDev);
		  Projet projet = (Projet)PM.ProjetSelected(Integer.parseInt(ProjetSelected));
		  ArrayList<Developpeur> listDev = (ArrayList<Developpeur>) EM.EquipeMember(Integer.parseInt(ProjetSelected));
		  
		  for(  Developpeur dev :listDev)	
		  {
			  System.out.println("ha dev "+ dev.getNom_complet()); 
		  }
		  
		  request.setAttribute("projet", projet);
		  System.out.println(projet.getNom());
		  request.setAttribute("Equipe", listDev);
		request.getRequestDispatcher("/views/ValiderEquipe.jsp").forward(request, response);
		
	}

}
