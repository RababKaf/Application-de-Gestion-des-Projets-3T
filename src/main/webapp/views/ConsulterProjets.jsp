<%@ include file="/views/navbarChp.jsp" %>
<section>

<% if (request.getAttribute("successMessage") != null) { %>
<script>
  
  
    Swal.fire({
      title: 'Succès !',
      text: '<%= request.getAttribute("successMessage") %>',
      icon: 'success',
      confirmButtonText: 'OK'
    });
 
</script>
 <% } %>
 
<div class="container mt-5">
 <div class="table-responsive">
    <table class="table text-center my-3" id="myTable">
    <thead>
     <tr style="background-color:#454EA38F;color:white;">
    
            <th  class="sous-titre">Nom du projet</th>
            <th  class="sous-titre">Date de Démarrage</th>
            <th  class="sous-titre ">Date de Livraison</th>
            <th  class="sous-titre masquer">Action</th>
            
    </tr>
</thead>
<tbody>
<% 
  ArrayList<Projet> AllProjets = (ArrayList<Projet>)request.getAttribute("AllPrj");
for(Projet p : AllProjets)
{
%>
 <tr>
            <td><%= p.getNom() %></td>
            <td><%= p.getDateDeDebut() %></td>
            <td><%= p.getDateDeLivraison() %></td>
            <td>
            
            	
      <a class="ms-5" href="#" data-bs-toggle="modal" data-bs-target="#modalConsulter<%=p.getId() %>" >
          <i class="bi bi-file-earmark-text-fill"   style="color:#454EA38F;font-size:25px;" data-bs-toggle="tooltip" title="Consulter projet" data-bs-placement="top"></i>
      </a>

      <a  class="ms-5" href="#" data-bs-toggle="modal" data-bs-target="#GestionModal<%=p.getId() %>" >
        <i class="bi bi-pencil-square"   style= "color:#454EA38F;font-size:25px;" data-bs-toggle="tooltip" title="Gérer projet" data-bs-placement="top"></i>
      </a>

   
            </td>
        </tr>
   <%
}
   %>
        
</tbody>
</table></div>
</div>

<% 

//******************* modal consulter*********************************// 
for(Projet p : AllProjets)
{
%>

<div class="modal modalStyle" id="modalConsulter<%=p.getId() %>" tabindex="-1">
  <div class="modal-dialog" style="max-width: 50%;">
    <div class="modal-content">
      <div class="modal-header">
       <h3 class="modal-title" id="exampleModalLabel" style="color:#0f63b8f1">Consulter Projet :</h3>
      </div>
      <div class="modal-body">
      <div class="data-panel myBody">
        
    <div class="row ">
      <div class="">
           <h6>Informations de base :</h6>
          <p><strong>Nom :</strong> <span id="projectName"><%=p.getNom() %></span></p>
          <p><strong>Jours de Développement :</strong> <span id="developmentDays"><%=p.getJoursDeDeveloppement() %></span></p>
          <p><strong>Client :</strong> <span id="developmentDays"><%= p.getClient().getNom_complet() %></span></p>
        
      </div>
   
      <div class="">
        <h6 >Dates :</h6>
        <p><strong>Date de Début :</strong> <span id="startDate"><%= p.getDateDeDebut() %></span></p>
        <p><strong>Date de Livraison :</strong> <span id="deliveryDate"><%= p.getDateDeLivraison() %></span></p>
        <p><strong>Date de Réunion  :</strong> <span id="deliveryDate"><%= p.getDateDeReunion() %></span></p>
      
      </div>
    </div>

    <div class="mt-4">
      <h6 >Description :</h6>
      <p><span id="projectDescription"><%= p.getDescription() %></span></p>
    </div>
    
     <div class="mt-4">
      <h6 >Methodologie & technologies:</h6>
       <p><strong>Methododlogie:</strong> <span id="projectName"><%= p.getMethodologie().getNom() %></span></p>
        <p><strong>Technologies :</p>
       <%
       ArrayList<Technologie> listTech = (ArrayList<Technologie>)request.getAttribute("listTech"+p.getId()) ;
       for(Technologie tech :listTech )
       {
    	   
       %>
       <span class="Tech" style="color: black; font-family: 'Times New Roman', serif; font-size:19px;"><%= tech.getNom() %> <b> ,</b></span>
        <%} %>
    </div>
    
     <div class="mt-4">
      <h6 >Equipe :</h6>
      
      <%
       ArrayList<Developpeur> listDev = (ArrayList<Developpeur>)request.getAttribute("listDev"+p.getId()) ;
       for(Developpeur dev :listDev )
       {
    	   
       %>
        <p><span style="color: black; font-family: 'Times New Roman', serif; font-size:19px;"><%= dev.getNom_complet() %></span></p>
        <%} %>
    </div>
      </div>
       <div class="modal-footer">
        <button style="background-color:#454EA38F;" type="button" class="btn btn-secondary" data-bs-dismiss="modal">OK</button>
      </div>
        
      </div>
    </div>
  </div>
</div>

   <%
   }
//******************* end modal consulter*********************************// 
   %>


 
<% 

//******************* modal gestion projet*********************************// 
for(Projet p : AllProjets)
{
%>

<div class="modal modalStyle" id="GestionModal<%=p.getId() %>" tabindex="-1">
  <div class="modal-dialog" style="max-width: 50%;text-align:center">
    <div class="modal-content">
      <div class="modal-header">
     
      </div>
      <div class="modal-body">
      <form action="/TPl/testServlet" method="post">
      <input type="hidden" name="ProjetId" value="<%=p.getId() %>">
       <h4 style="color:#454EA38F">Ajouter Service :</h4>
       <label  style="color:#454EA38F">Description:</label>
      <div class="input-group mb-3">
        <textarea type="text" name="descriptionS" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default"  placeholder="Saisir une description sur le service"></textarea>
      </div>
      
      <label style="color:#454EA38F">Durée(jours):</label>
      <div class="input-group mb-3">
        <input type="number" name="duree" class="form-control" >
      </div>
      <div class="modal-footer">
        <button style="background-color:#454EA38F;" type="button" class="btn btn-primary" id="btnSuivant<%=p.getId() %>"  >Ajouter Les Taches</button>
     </div>
     
     <div class="tache" id="tacheId<%=p.getId() %>" style="display: none;">
     
     <h4 style="color:#454EA38F">Ajouter Les Taches:</h4>
       <label style="color:#454EA38F;">Label:</label>
      <div class="input-group mb-3">
        <input type="text" name="nomTache" class="form-control"  placeholder="Saisir nom de la tache">
      </div>
      
      
      <label style="color:#454EA38F">La Description:</label>
      <div class="input-group mb-3">
        <textarea type="text" name="descriptionTache" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default"  placeholder="Saisir une description sur la tache"></textarea>
      </div>
      
      <label style="color:#454EA38F">Développeur:</label>
      <select name="devloppeurs" class="form-select" aria-label="Default select example">
      <%
       ArrayList<Developpeur> listDev2 = (ArrayList<Developpeur>)request.getAttribute("listDev"+p.getId()) ;
       for(Developpeur dev :listDev2 )
       {
    	   
       %>
        <option value="<%= dev.getId()%>"><%= dev.getNom_complet()%></option>
        
        <%} %>
      </select>
      
      
       <div class="text-end mt-3">
                        <button style="background-color:#454EA38F;" type="button" class="btn btn-primary " id="AddTache1">
                           Ajouter Autre Tache
                        </button>
                    </div><br>
      
</div>

<!--  tache 2 -->

<div class="tache" id="tacheAdd2" style="display: none;">
     
     
       <label  style="color:#454EA38F;">Label:</label>
      <div class="input-group mb-3">
        <input type="text" name="nomTache2" class="form-control"  placeholder="Saisir nom de la tache">
      </div>
      
      
      <label style="color:#454EA38F;">La Description:</label>
      <div class="input-group mb-3">
        <textarea type="text" name="descriptionTache2" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default"  placeholder="Saisir une description sur la tache"></textarea>
      </div>
      
      <label style="color:#454EA38F;">Développeur:</label>
      <select name="devloppeurs2" class="form-select" aria-label="Default select example">
      <%
      
       for(Developpeur dev :listDev2 )
       {
    	   
       %>
        <option value="<%= dev.getId()%>"><%= dev.getNom_complet()%></option>
        
        <%} %>
      </select>
      
</div>




       <div class="modal-footer">
        <button style="background-color:#454EA38F;" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
         <input style="background-color:#454EA38F;" type="submit" class="btn btn-primary" value="Valider" />
      </div>
        </form>
      </div>
    </div>
  </div>
</div>

   <%
   }

   %>


 <script>
  $(document).ready(function() {
    <% 
    for (Projet p : AllProjets) {
    %>
    $("#btnSuivant<%=p.getId() %>").on("click", function() {
      $("#tacheId<%=p.getId() %>").show();
    });
    <% } %>
  });
</script>

<script>

  $(document).ready(function() {
    $("#AddTache1").on("click", function() {
      $("#tacheAdd2").show();
    });
  });
</script>

</section>