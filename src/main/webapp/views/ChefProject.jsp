<%@ include file="navbarChp.jsp" %>
<section>

<% if (request.getAttribute("successMessage") != null) { %>
<script>
  
  
    Swal.fire({
      title: 'Well Done',
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
     <tr style="background-color:#454EA38F; color:white">
     
            <th  class="sous-titre">Nom du projet</th>
            <th  class="sous-titre">Date de demarage</th>
            <th  class="sous-titre ">Date de fin</th>
            <th  class="sous-titre masquer">Action</th>
            
    </tr>
</thead>
<tbody>
<% 
  ArrayList<Projet> NewProjets = (ArrayList<Projet>)request.getAttribute("NewPrj");
for(Projet p : NewProjets)
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

      <a  class="ms-5" href="#" data-bs-toggle="modal" data-bs-target="#modalMeth<%=p.getId() %>" >
        <i class=" bi bi-plus-square-fill"   style= "color:#454EA38F;font-size:25px;" data-bs-toggle="tooltip" title="Gï¿½rer projet" data-bs-placement="top"></i>
      </a>

     <!--  <a class="ms-5" href="ProjectServlet?projet=<%=p.getId() %>" data-bs-toggle="modal" data-bs-target="#modalMethod<%=p.getId() %>">
        <i class="bi bi-pencil-square toggle-icon"   style= "color:rgba(17, 150, 183, 0.926);font-size:25px;" data-bs-toggle="tooltip" title="Gï¿½rer projet" data-bs-placement="top"></i>
      </a>-->
            </td>
        </tr>
   <%
}
   %>
        
</tbody>
</table>
</div>
</div>
<% 

//******************* modal consulter*********************************// 
  ArrayList<Projet> NewProjets2 = (ArrayList<Projet>)request.getAttribute("NewPrj");
for(Projet p : NewProjets2)
{
%>

<div class="modal modalStyle" id="modalConsulter<%=p.getId() %>" tabindex="-1">
  <div class="modal-dialog" style="max-width: 50%;">
    <div class="modal-content">
      <div class="modal-header">
       <h5 class="modal-title" id="exampleModalLabel" style="color:#454EA38F;text-align:center;">
            Informations Sur le Projet :</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body"  style="text-align:center;">
      
      <div class="data-panel myBody">
        
    <div class="row mt-4">
      <div class="">
           
        <p><strong>Nom Du Projet:</strong> <span id="projectName"><%=p.getNom() %></span></p>
        <p><strong>Jours de Développement :</strong> <span id="developmentDays"><%=p.getJoursDeDeveloppement() %></span></p>
         <p><strong>Client :</strong> <span id="developmentDays"><%= p.getClient().getNom_complet() %></span></p>
        
      </div>
   
      <div class="">
        
        <p><strong>Date de Début :</strong> <span id="startDate"><%= p.getDateDeDebut() %></span></p>
        <p><strong>Date de Livraison :</strong> <span id="deliveryDate"><%= p.getDateDeLivraison() %></span></p>
        <p><strong>Date de Réunion  :</strong> <span id="deliveryDate">Non encore Integré.</span></p>
      
      </div>
    </div>

    <div class="mt-4">
      <p><strong>Description :</strong>
      <p><span id="projectDescription"><%= p.getDescription() %></span></p>
    </div>
    
     <div class="mt-4">
    
       <p><strong>Methododlogie:</strong> <span id="projectName">Non encore Integré</span></p>
     
        <p><strong>Technologies :</strong> <span id="projectName">Non encore Integré</span></p>
    </div>
      </div>
       <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" style="text-align:center;background-color:blue">OK</button>
      </div>
        
      </div>
    </div>
  </div>
</div>

   <%
   }
//******************* end modal consulter*********************************// 
   %>



<% // ************* modal Afficher Meth***************** %>




		<% 
		
		//******************* modal consulter*********************************// 
		  ArrayList<Projet> NewProjets3 = (ArrayList<Projet>)request.getAttribute("NewPrj");
		for(Projet p : NewProjets3)
		{
		%>
<div class="modal modalStyle  modalMeth" id="modalMeth<%=p.getId() %>" tabindex="-1">
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" style="color:#0f63b8f1;"></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="data-panel">
                     <h3 class="fst-italic  text-secondary">Ajouter la methodologie :</h3>
         <form  method="POST" action="EquipeServlet">
                
             <input type="hidden" name="ProjetId" value="<%=p.getId() %>">
             
          <select name="Method" class="form-select" aria-label="Default select example">
						          <%
						            ArrayList<Methodologie> listMeth = (ArrayList<Methodologie>)request.getAttribute("listMeth");
						             for(Methodologie meth : listMeth)
						             {
						            	
						          %>
				       <option value="<%= meth.getId() %>"><%= meth.getNom() %></option>
										<%} %>
              </select>
       <br>
                                   <!--  Ajouter les donner de technologie ajouter :  --> 
       
		         <h3 class="fst-italic  text-secondary">Ajouter la Technologies:</h3>
		       
		                
						                <%
									             ArrayList<Technologie> listTech = (ArrayList<Technologie>) request.getAttribute("listTech");
									             for(Technologie tech : listTech)
									             {
									            	
									          %>
		                            <div class="form-check" style="font-size:19px;">
										  <input class="form-check-input" type="checkbox" value="<%= tech.getId() %>" name="nomDuChampCheckbox" id="flexCheckChecked">
										  <label class="form-check-label" for="flexCheckChecked">
										   <%= tech.getNom() %>
										  </label>
										</div>
						         
						                           <%} %>
						       
						       
						       
				    <div class="modal-footer">
				             <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
				             <input type="submit" class="btn btn-primary"  value="Suivant" />
				   </div>

      </form>
                </div>
                
              
            </div>
          
        </div>
    </div>
</div>

<% } %>

<script>
  
  <% if (request.getAttribute("successMessage") != null) { %>
    Swal.fire({
      title: 'Success !',
      text: '<%= request.getAttribute("successMessage") %>',
      icon: 'success',
      confirmButtonText: 'OK'
    });
  <% } %>
</script>

</section>