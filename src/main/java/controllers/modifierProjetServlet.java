package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.ChefProjet;
import models.Client;
import models.Projet;
import models.User;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import businessLayer.*;

/**
 * Servlet implementation class modifierProjetServlet
 */
public class modifierProjetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ProjectManager manager = new ProjectManager();
	
    public modifierProjetServlet() {
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

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProjectManager manager = new ProjectManager();
		ArrayList<Projet> projects = manager.getAllProjects();
        ArrayList<User> listChef = manager.getChef();
        
        request.setAttribute("listChef",listChef);
        request.setAttribute("projects", projects);
      
        
		String nomProjet= request.getParameter("nomProjet");
		String description= request.getParameter("description");
		String nomclient= request.getParameter("nomclient");
		String cinclient= request.getParameter("cin");
		String tele= request.getParameter("tele");
		String dema=request.getParameter("dema");
		String dateLivraison= request.getParameter("dateLivraison");
		String nbrJS= request.getParameter("nbrJ");
		int nbrJ = Integer.parseInt(nbrJS);
		String adress= request.getParameter("adress");
        String chefS=request.getParameter("chef");
        int idchef=Integer.parseInt(chefS);
        String idS=request.getParameter("idProjet");
        int id=Integer.parseInt(idS);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        try {
		    
		    java.util.Date parsedema = dateFormat.parse(dema);
		    java.util.Date parseLivraison = dateFormat.parse(dateLivraison);
		    java.sql.Date sqlDate1 = new java.sql.Date(parsedema.getTime());
		    java.sql.Date sqlDate2 = new java.sql.Date(parseLivraison.getTime());
		    Client client = new Client(adress,cinclient,nomclient,tele);
			ChefProjet chef=new ChefProjet(idchef);
			Projet projet=new Projet(sqlDate1,sqlDate2,description,nbrJ,nomProjet,client,chef);
		    projet.setId(id);
		    
		    boolean existP=manager.modifyProject(projet);
			
			if (!existP) {
				String message = "La modification n'est pas effectué avec succès.";
				request.setAttribute("message", message);
			request.getRequestDispatcher("/ConsulterProjet").forward(request, response);
			}
			String message = "La modification s'est effectué avec succès.";
			request.setAttribute("messageMP", message);
			request.getRequestDispatcher("/ConsulterProjet").forward(request, response);
		    
		    
		
		} catch (ParseException e) {
		   
		    e.printStackTrace(); 
		}
	
	}

}
