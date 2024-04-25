package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import businessLayer.*;
import businessLayer.ProjectManager;
import businessLayer.InterfaceProjetManager;
import models.*;
/**
 * Servlet implementation class GestionProjet
 */
public class testServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectManager PG= new ProjectManager();
       
	static int id;
    public testServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id")!=null)
		id =Integer.parseInt(request.getParameter("id"));
		ArrayList<Projet> AllProjets = (ArrayList<Projet>) PG.ListProjet(id);
		//ArrayList<Technologie> listTech =(ArrayList<Technologie>) PG.getTech();
		
		String successMessage = (String) request.getAttribute("successMessage");	
		if (successMessage != null) {
	        
	       
	        request.setAttribute("successMessage", successMessage);
	    }
		
		for(Projet p : AllProjets)
		{
			ArrayList<Technologie> listTech =(ArrayList<Technologie>) PG.ListeTech(p.getId()) ;
			ArrayList<Developpeur> listDev = (ArrayList<Developpeur>)PG.GetEquipe(p.getId());
			
			request.setAttribute("listTech"+p.getId(), listTech);
			request.setAttribute("listDev"+p.getId(), listDev);
		}
		
		
		
		
		
		    request.setAttribute("AllPrj", AllProjets);
		
		request.getRequestDispatcher("/views/ConsulterProjets.jsp").forward(request, response);
		
	}

	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String idProjet = request.getParameter("ProjetId");
		// recuperer service : 
		
	   String descriptionService= request.getParameter("descriptionS");
	   String durre= request.getParameter("duree");
	   
	   // recuperer tache :
	   String nomTach= request.getParameter("nomTache");
	   String descTache= request.getParameter("descriptionTache");
	   String devTach= request.getParameter("devloppeurs");
	   // la tache 2 : 
	   String nomTach2= request.getParameter("nomTache2");
	   String descTache2= request.getParameter("descriptionTache2");
	   String devTach2= request.getParameter("devloppeurs2");
	   
	   int idService= PG.AjouterService(descriptionService, Integer.parseInt(durre), Integer.parseInt(idProjet));

	   PG.AjouterTache(nomTach, descTache, Integer.parseInt(devTach) ,idService);
	   
	   if((nomTach2 != null) && ( descTache2 != null) &&  (devTach2 != null))
	   {
		   System.out.println(devTach2);
		   PG.AjouterTache(nomTach2, descTache2, Integer.parseInt(devTach2) ,idService);
	   }
	   
	   request.setAttribute("successMessage", "Le Service a éte Ajouter avec succès");
	   
	   doGet(request, response);
	   
	   
		
		
	}

}
