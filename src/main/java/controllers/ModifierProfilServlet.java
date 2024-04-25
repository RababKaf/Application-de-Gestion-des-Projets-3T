package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.*;

import java.io.IOException;
import java.util.ArrayList;

import businessLayer.*;

/**
 * Servlet implementation class ModifierProfilServlet
 */
public class ModifierProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ProjectManager projet=new ProjectManager();
	static  String nomComplet;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierProfilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	 nomComplet = request.getParameter("nomcomplet");
		User developpeur= projet.getInfoDev(nomComplet);
		ArrayList<Competance> competance = projet.getCompetance(developpeur);
		
		request.setAttribute("competance", competance);
		request.setAttribute("developpeur", developpeur);
		request.getRequestDispatcher("/views/profil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}