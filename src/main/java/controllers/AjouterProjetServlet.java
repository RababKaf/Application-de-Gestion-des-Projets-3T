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
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import businessLayer.*;
import dataLayer.DataProjet;


public class AjouterProjetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ProjectManager manager = new ProjectManager();
       
  
    public AjouterProjetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		DataProjet db = new DataProjet();
		ProjectManager manager = new ProjectManager();

        ArrayList<User> listChef = manager.getChef();
        
        ArrayList<Client> listClient=manager.getclients();
   
        System.out.println(listClient.size());
        
        
        request.setAttribute("listClient",listClient);

        request.setAttribute("listChef",listChef);
        request.getRequestDispatcher("/views/ajouterProjet.jsp").forward(request, response);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataProjet db = new DataProjet();
		ProjectManager manager = new ProjectManager();
        ArrayList<User> listChef = manager.getChef();
        ArrayList<Client> listClient=manager.getclients();
        request.setAttribute("listClient",listClient);
        request.setAttribute("listChef",listChef);
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        
        try {
		    
		    java.util.Date parsedema = dateFormat.parse(dema);
		    java.util.Date parseLivraison = dateFormat.parse(dateLivraison);
		    java.sql.Date sqlDate1 = new java.sql.Date(parsedema.getTime());
		    java.sql.Date sqlDate2 = new java.sql.Date(parseLivraison.getTime());
		    Client client = new Client(adress,cinclient,nomclient,tele);
			ChefProjet chef=new ChefProjet(idchef);
			Projet projet=new Projet(sqlDate1,sqlDate2,description,nbrJ,nomProjet,client,chef);
			System.out.println(sqlDate1+"     "+sqlDate2);
			 manager.addClient(client);
			
			
		boolean existP=manager.AddProject(projet);
					
					if (!existP) {
					
						request.getRequestDispatcher("/views/ajouterProjet.jsp").forward(request, response);
					}
					
					else {
				
						request.getRequestDispatcher("ConsulterProjet").forward(request, response);
					}
					
		} catch (ParseException e) {
		   
		    e.printStackTrace(); 
		}
		
		

		
		

				
		
	}

}
