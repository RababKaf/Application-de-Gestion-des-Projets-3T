<%@ include file="navbarDev.jsp" %>

<section>
<div class="container mt-5">
<div class="text-center">
<b><label style="color: #454EA38F;" for="searchProject">Rechercher Un Projet par Nom :</label></b><br>
<input style="color: #454EA38F;" type="text" id="searchProject" name="searchProject" onkeyup="searchProjects()" >
<br><br><br>
<table class="table  table-bordered  b -5" id="projectTable">
<thead  >
  <tr style="background-color: #454EA38F; color:white;">
                <th>Nom du Projet</th>
                <th>Description</th>
                <th>Date de Démarage</th>
                <th>Date de Livraison</th>
                <th>Chef du Projet</th>
                <th>Client</th>
                <th>Consulter Le Projet</th>
  </tr>
  </thead>

 <% 
    ArrayList<Projet> projets=(ArrayList<Projet>)request.getAttribute("projets");
  
    for(Projet p : projets){ %>
    <tr data-projetid="<%= p.getId() %>">
       <td><%=p.getNom() %></td>
       <td><%=p.getDescription() %></td>
       <td><%=p.getDateDeDebut() %></td>
       <td><%=p.getDateDeLivraison() %></td>
       <td><%=p.getChefProjet().getNom_complet() %></td>
       <td><%=p.getClient().getNom_complet() %></td>
       <td>
  <a class="ms-5" href="#" onclick="openModal('<%=p.getId() %>')">
            <i class="bi bi-file-earmark-text-fill" style="color:#454EA38F;font-size:25px;" data-bs-toggle="tooltip" title="Consulter projet" data-bs-placement="top"></i>
         
         
          </a></td>
 </tr>
  <% } %> 
</table>
 </div>
    </div>

<%
//******* modal consulter*************// 
ArrayList<Projet> projets2=(ArrayList<Projet>)request.getAttribute("projets");
for(Projet p : projets2)
{
%>
<div class="modal modalStyle" id="modalConsulter<%=p.getId() %>" tabindex="-1">
  <div class="modal-dialog" style="max-width: 50%;">
    <div class="modal-content">
      <div class="modal-header">
   
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      
      <div class="data-panel">
  
      <div class="card shadow">
      
        <div class="card-body p-4">
      <div class=""><b>
          
        <p><strong style="color: #454EA38F;">Nom :</strong> <span id="projectName"><%=p.getNom() %></span></p>
         <p><strong style="color: #454EA38F;">Client :</strong> <span id="developmentDays"><%= p.getClient().getNom_complet() %></span></p>
        
      </div>
   
      <div class="">
        <label style="color: #454EA38F;" class="text-secondary">Les Dates :</label>
        <p ><strong style="color: #454EA38F;">Date de Démarage :</strong> <span id="startDate"><%= p.getDateDeDebut() %></span></p>
        <p><strong style="color: #454EA38F;">Date de Livraison :</strong> <span id="deliveryDate"><%= p.getDateDeLivraison() %></span></p>
        <p><strong style="color: #454EA38F;">Date de Réunion  :</strong> <span id="deliveryDate"><%= p.getDateDeReunion() %></span></p>
      
      </div>
    </div>

    <div class="mt-4">
      <label style="color: #454EA38F;" class="text-secondary">La Description :</label>
      <span id="projectDescription"><%= p.getDescription() %></span>
    </div>
    
    <div class="mt-4">
    <label style="color: #454EA38F;" class="text-secondary">Methodologie et technologies :</label>
    <p><strong style="color: #454EA38F;">Methododlogie:</strong> <span id="methodologieName<%= p.getId() %>"><%= p.getMethodologie().getNom() %></span></p>
   <strong style="color: #454EA38F;">Les Technologies :</strong>
    <% int count = 0; %>
    <% for(Technologie tech :p.getTechnologie() ){ %>
        <p> <span id="technologyName<%= p.getId() %><%= count %>"><%= tech.getNom() %></span></p>
        <% count++; %>
    <% } %>
   </div>
   
    <div class="mt-4">
    <label style="color: #454EA38F;" class="text-secondary">Les Services et Les taches:</label>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Service</th>
                <th>Tâche</th>
                <th>Développeur</th>
            </tr>
        </thead>
        <tbody>
            <% for (Service service : p.getService()) { %>
                <% for (Tache tache : service.getTaches()) { %>
                    <tr>
                        <td><%= service.getDescrip() %></td>
                        <td><%= tache.getNom() %></td>
                        <td><%= tache.getUser().getNom_complet() %></td>
                    </tr>
                <% } %>
            <% } %>
        </tbody></b>
    </table>
   </div>

      </div>
      </div></div></div></div>
        
      </div>
 


   <%
   }

%>

<script>
function searchProjects() {
    // Récupérer la valeur saisie dans le champ de recherche
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("searchProject");
    filter = input.value.toUpperCase();
    table = document.getElementById("projectTable");
    tr = table.getElementsByTagName("tr");

    // Parcourir toutes les lignes du tableau et masquer celles qui ne correspondent pas à la recherche
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0]; // Recherche dans la première colonne (Nom du Projet)
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function openModal(projectId) {
	  var modal = document.getElementById("modalConsulter" + projectId);
	  if (modal) {
	    modal.style.display = "block";
	  }
	}
	
window.onclick = function(event) {
	  var modals = document.getElementsByClassName("modal");
	  for (var i = 0; i < modals.length; i++) {
	    var modal = modals[i];
	    if (event.target == modal) {
	      modal.style.display = "none";
	    }
	  }
	}

</script>
</section>