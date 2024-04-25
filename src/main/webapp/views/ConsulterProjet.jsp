<%@ include file="navbarAdm.jsp" %>
<section>

  <div class="table-responsive">
    <table class="table text-center my-3" id="myTable">
        <tr>
 <th class="sous-titre highlighted-header " style=" background-color: #454EA38F; color:white;">Nom</th>
<th class="sous-titre " style=" background-color: #454EA38F; color:white;">Description</th>
<th class="sous-titre " style=" background-color: #454EA38F; color:white;">Date de démarrage</th>
<th class="sous-titre " style=" background-color: #454EA38F; color:white;">Date de livraison</th>
<th class="sous-titre " style=" background-color: #454EA38F; color:white;">Client</th>
<th class="sous-titre " style=" background-color: #454EA38F; color:white;">Chef de projet</th>
<th class="sous-titre masquer " style=" background-color: #454EA38F; color:white;">Actions</th>
   </tr> <%
             ArrayList<Projet> listprojects=(ArrayList<Projet>)request.getAttribute("projects");
             for(Projet p : listprojects)
             {%>
                    <tr>
                <td ><%= p.getNom() %></td>
                <td><%= p.getDescription() %></td>
                <td><%= p.getDateDeDebut() %></td>
                <td><%= p.getDateDeLivraison() %></td>
                <td><%= p.getClient().getNom_complet() %></td>
                <td><%= p.getChefProjet().getNom_complet()%></td>
                
               <td>
    <a class="ms-5" href="#" data-bs-toggle="modal" data-bs-target="#modalConsulter<%=p.getId() %>">
        <i class="fas fa-edit fa-lg mx-1" style="color:green;"></i></a>

    <a class="ms-5" href="#" data-bs-toggle="modal" data-bs-target="#modalSupprimer<%=p.getId() %>">
           <i class="fas fa-trash fa-lg mx-1" style="color:red;"></i>
    </a>
</td>
               
               </tr>
                   <%}%>
  
    </table>
    </div>
<% 

  ArrayList<Projet> listprojects2=(ArrayList<Projet>)request.getAttribute("projects");
             for(Projet p : listprojects2)
             {
            	
            	// System.out.println("mon chef"+p.getChefProjet().getNom_complet());
%>

<div class="modal modalStyle" id="modalConsulter<%=p.getId() %>" tabindex="-1">
    <div class="modal-dialog" style="max-width: 50%;">
        <div class="modal-content">
            <div class="modal-header">
         
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">
                <div class="data-panel">
                    <h5 class="mb-4" style="text-align:center;color:#545784A6;">Informations du Projet</h5>

                    <form method="post" action="modifierProjetServlet">
                        <div class="row">
                            <div class="col">
                                <fieldset><b>
                                    <legend style="color:rgb(18, 46, 108);"></legend>
                                    <input type="hidden" name="idProjet" value="<%=p.getId() %>">

                                    <div class="mb-3">
                                        <label style="color:#545784A6;" for="nomProjet" class="form-label">Le Nom du Projet:</label>
                                        <input type="text" class="form-control" name="nomProjet" value="<%=p.getNom() %>" required>
                                    </div>

                                    <div class="mb-3">
                                        <label style="color:#545784A6;" for="nomclient" class="form-label">Le NomComplet du Client :</label>
                                         <input type="text" class="form-control" name="nomclient" value="<%= p.getClient().getNom_complet() %>" readonly>
                                       
                                        <input type="hidden" name="nomclient" value="<%= p.getClient().getNom_complet() %>">
                                        <input type="hidden" name="cin" value="<%= p.getClient().getCin() %>">
                                        <input type="hidden" name="adress" value="<%= p.getClient().getAdresse() %>">
                                        <input type="hidden" name="tele" value="<%= p.getClient().getTel() %>">
                                    </div>

                                    <div class="mb-3">
                                        <label style="color:#545784A6;"for="description" class="form-label">La Description :</label>
                                        <textarea class="form-control" name="description" rows="2" cols="20" required><%= p.getDescription() %></textarea>
                                    </div>

                                    <div class="mb-3">
                                        <label for="nbrJ" class="form-label">Les Jours de Développement :</label>
                                        <input type="number" class="form-control" value="<%=p.getJoursDeDeveloppement() %>" name="nbrJ" required>
                                    </div>
                                </fieldset>
                            </div>

                            <div class="col">
                                <div class="mb-3">
                                    <label style="color:#545784A6;" for="dema" class="form-label">La Date de démarrage:</label>
                                    <input type="date" class="form-control" value="<%= p.getDateDeDebut() %>" name="dema" required>
                                </div>

                                <div class="mb-3">
                                    <label style="color:#545784A6;" for="dateLivraison" class="form-label">La Date de Livraison :</label>
                                    <input type="date" class="form-control" value="<%= p.getDateDeLivraison() %>" name="dateLivraison" required>
                                </div></b>

                                <div class="mb-3">
                                    <h5 style="color:#545784A6;" class="form-label">Affecter Le Projet :</h5>
                                    <select class="form-select" name="chef">
                                        <option value="<%= p.getChefProjet().getId() %>" selected><%= p.getChefProjet().getNom_complet() %></option>
                                        <% 
                                        //System.out.println("mon chef"+p.getChefProjet().getId());
                                        
                                        ArrayList<User> listchef = (ArrayList<User>) request.getAttribute("listChef");
                                        for(User chef : listchef)
                                        {
                                        	//System.out.println(p.getChefProjet().getId()+"      "+chef.getId());
                                            if(p.getChefProjet().getId() == chef.getId())
                                                continue;	
                                        %>
                                        <option value="<%= chef.getId() %>"><%= chef.getNom_complet() %></option>
                                        <% } %>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="modal-footer">
                             <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                            <input style="background-color:#545784A6;" type="submit" class="btn btn-primary" data-bs-dismiss="modal" value="Modifier">
                       
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>



     <%
						}
   				%>
   
<% 

  ArrayList<Projet> listprojects3=(ArrayList<Projet>)request.getAttribute("projects");
             for(Projet p : listprojects3)
             {
%>

<div class="modal modalStyle" id="modalSupprimer<%=p.getId() %>" tabindex="-1">
    <div class="modal-dialog" >
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel"  font-family:Lucida Bright;"><small class="font-weight-bold" style="color:#cda01b;">
            <i class="bi bi-exclamation-triangle-fill" style="color:#e82311;"></i>
          </small></h5><b>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body delete-modal">
         
          <p style="color:red;text-align:center;">Souhaitez-vous réellement supprimer ce projet ?</p>
          </b>
       
           <div style="text-align:center;">
              <div style="text-align:center;"class="modal-footer mt-2 ms-2 " >
           <button style="background-color:#545784A6;" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Non</button>
            <form  method="post" action="/TPl/SupprimerProjetServlet">
             <input type="hidden" name="idProjet" value="<%=p.getId() %>">
              <input type="submit"  data-bs-dismiss="modal" value="Oui" class="btn btn-danger" style="font-size: 18px;">
          
            </form>
             </div>
          </div>
  
      </div>
    </div>
  </div> 
  </div>
     <%
}
   %>
  
</section>
