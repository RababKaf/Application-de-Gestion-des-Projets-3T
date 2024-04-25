package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import businessLayer.*;
import businessLayer.ProjectManager;
import businessLayer.InterfaceProjetManager;
import models.Competance;
import models.Developpeur;

/**
 * Servlet implementation class EquipeServlet
 */
public class EquipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProjectManager PM= new ProjectManager();
	private ProjectManager  EM= new ProjectManager();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EquipeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ProjetSelected= request.getParameter("ProjetId") ;
		String MethdSelected = request.getParameter("Method");
		
	
	 	PM.UpDateMeth(Integer.parseInt(ProjetSelected),Integer.parseInt(MethdSelected));
		
		String[] techCheked = request.getParameterValues("nomDuChampCheckbox");
		if (techCheked != null) {
		    for (String tech : techCheked) {
		       
		       PM.AddTech(Integer.parseInt(ProjetSelected), Integer.parseInt(tech));
		       
		    }
		} else {
		   
		    System.out.println("Aucune case à cocher sélectionnée.");
		}
		 
		ArrayList<Developpeur> listeDev= (ArrayList<Developpeur>) EM.FormerEquipe(Integer.parseInt(ProjetSelected));
		for(Developpeur dev : listeDev )
		{
			ArrayList<Competance> listeComp = (ArrayList<Competance>) EM.Competance(Integer.parseInt(ProjetSelected), dev.getId());
			request.setAttribute("Comp"+dev.getId(),listeComp);
		}
		request.setAttribute("listeDev", listeDev);
		request.setAttribute("ProjetId", Integer.parseInt(ProjetSelected));
		
		request.getRequestDispatcher("/views/Equipe.jsp").forward(request, response);
		
	}

}
