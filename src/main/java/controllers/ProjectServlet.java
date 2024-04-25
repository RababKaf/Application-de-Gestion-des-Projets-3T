package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import businessLayer.*;
import models.*;


public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InterfaceProjetManager PM= new ProjectManager();
	static int id;
       
   
    public ProjectServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id")!=null) {
			 id =Integer.parseInt(request.getParameter("id"));}
		
		ArrayList<Projet> NewProjets = (ArrayList<Projet>) PM.GetProjets(id);
		//System.out.println("ytduvtryutrvyutrvytvr"+id);
		ArrayList<Methodologie> ListeMethododlogie =(ArrayList<Methodologie>) PM.getMeth();
		ArrayList<Technologie> listTech =(ArrayList<Technologie>) PM.getTech();
		
		String successMessage = (String) request.getAttribute("successMessage");	
		if (successMessage != null) {
	        
	       
	        request.setAttribute("successMessage", successMessage);
	    }
		    request.setAttribute("NewPrj", NewProjets);
			request.setAttribute("listTech", listTech);
			request.setAttribute("listMeth", ListeMethododlogie);
		request.getRequestDispatcher("/views/ChefProject.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
