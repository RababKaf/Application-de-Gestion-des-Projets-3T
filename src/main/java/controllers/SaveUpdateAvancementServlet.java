package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import businessLayer.*;

/**
 * Servlet implementation class SaveUpdateAvancementServlet
 */
public class SaveUpdateAvancementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	ProjectManager projetService = new ProjectManager();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveUpdateAvancementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		 request.setAttribute("successMessage", "Modification enregistrée avec succès");
    	 request.getRequestDispatcher("EnregistrerAvancementServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Récupérer les données modifiées depuis la requête
		doGet(request, response);
		
	    String tacheIdString = request.getParameter("tacheId");
	    String nouvelleDescription = request.getParameter("description");
	    String avancementString = request.getParameter("avancement");

	    // Vérifier si les paramètres ne sont pas null ou vides avant de les convertir en entiers
	    if (tacheIdString != null && !tacheIdString.isEmpty() &&
	        nouvelleDescription != null && !nouvelleDescription.isEmpty() &&
	        avancementString != null && !avancementString.isEmpty()) {
    try {
	            int tacheId = Integer.parseInt(tacheIdString);
	            int nouvelAvancement = Integer.parseInt(avancementString);

	            // Appel à une classe de service  pour effectuer la mise à jour dans la base de données
	            boolean modificationEffectuee = projetService.updateTache(tacheId, nouvelleDescription, nouvelAvancement);

	          
	            request.setAttribute("successMessage", "Modification enregistrée avec succès"); 
	            request.getRequestDispatcher("EnregistrerAvancementServlet").forward(request, response); 
	        
	        } catch (NumberFormatException e) {
	            // Gérer une exception si la conversion en entier échoue
	           // response.setContentType("text/plain");
	          //  response.getWriter().write("Erreur de format pour les valeurs numériques.");
	        }
	    } 
	}

     
	

}