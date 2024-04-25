package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import businessLayer.*;

/**
 * Servlet implementation class EnregistrerProfilServlet
 */
public class EnregistrerProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ProjectManager manager=new ProjectManager();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnregistrerProfilServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		 // Récupération des valeurs des champs du formulaire
        String nomComplet = request.getParameter("nomComplet");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");
        int idDev=manager.getDeveloppeur(nomComplet);
        
        manager.updateUser(nomComplet, email, telephone, idDev);
        
        // Récupération des compétences existantes
        String[] competencesExistantes = request.getParameterValues("competence");
        String[] niveauxExistantes = request.getParameterValues("niveau");

        // Récupération des nouvelles compétences et de leurs niveaux
        String[] nouvellesCompetences = request.getParameterValues("nouvelleCompetence");
        String[] nouveauxNiveaux = request.getParameterValues("nouveauNiveau");

        // Traitement des compétences existantes
        if (competencesExistantes != null && niveauxExistantes != null) {
            for (int i = 0; i < competencesExistantes.length; i++) {
                String competenceExistante = competencesExistantes[i];
                String niveauExistant = niveauxExistantes[i];
               
                manager.updateCompetance(idDev,niveauExistant,competenceExistante); // Updated the order of parameters
            }
        }

        // Traitement des nouvelles compétences ajoutées
        if (nouvellesCompetences != null && nouveauxNiveaux != null) {
            for (int i = 0; i < nouvellesCompetences.length; i++) {
                String nouvelleCompetence = nouvellesCompetences[i];
                String nouveauNiveau = nouveauxNiveaux[i];
               
                // Insérer ces nouvelles compétences dans la base de données
                int idTech=manager.insertTechnologie(nouvelleCompetence);
                
                manager.insertCompetance(idTech,idDev, nouveauNiveau);
            }
        }

      
        request.setAttribute("successMessage", "Profil enregistrer avec succés");
        request.getRequestDispatcher("/views/index.jsp").forward(request, response);
    
    }
        

}