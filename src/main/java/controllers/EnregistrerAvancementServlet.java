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

public class EnregistrerAvancementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ProjectManager manager=new ProjectManager();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnregistrerAvancementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		 String nomComplet = request.getParameter("nomcomplet");
		ArrayList<Projet> projets=manager.getProject(nomComplet);
		System.out.println("--------------------------------777-----------------------"+projets.size());
		request.setAttribute("projets", projets);
		request.getRequestDispatcher("/views/avancement.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}