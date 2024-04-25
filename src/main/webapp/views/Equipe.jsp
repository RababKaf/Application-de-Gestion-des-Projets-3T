<%@ include file="navbarChp.jsp" %>
<section>
<div class="container mt-5">
 <form action="FormerEquipeServlet" method="post"> 
 <div class="table-responsive">
    <table class="table text-center my-3" id="myTable">
    <thead>
     <tr style="background-color:#454EA38F; color:white">
            <th  class="sous-titre">NomComplet du développeur</th>
            <th  class="sous-titre">sa competance</th>
            <th  class="sous-titre ">son niveau</th>
            <th class="sous-titre" style="width:10%;"></th>
     </tr>
</thead>
<tbody>

<% 
  ArrayList<Developpeur> ListeDev= (ArrayList<Developpeur>)request.getAttribute("listeDev");
  int ProjetSelected = (int)request.getAttribute("ProjetId");
for(Developpeur dev : ListeDev)
{
%>

       <tr>
       
        
		
            <td><%= dev.getNom_complet()%></td>
            <%
            ArrayList<Competance> Listecomp= (ArrayList<Competance>)request.getAttribute("Comp"+dev.getId());
            %>
            
		            <td>
		            <% for(Competance comp : Listecomp)   { %>
		          
		            <ul style="list-style-type: none;">
		            <li><%= comp.getTechnologie().getNom() %></li>
		            </ul>
		            <%} %>
		            </td>
		            
		            <td>
		            <% for(Competance comp : Listecomp)   { %>
		            <ul style="list-style-type: none;">
		            <li><%= comp.getNiveau() %></li>
		            </ul>
		             <%} %>
		            </td>
		            <td>
		   <input class="form-check-input" type="checkbox" value="<%= dev.getId() %>" name="DevloppeurSelected" id="flexCheckChecked"  class="form-check" style="font-size:19px;">
		</td>
		          
        </tr>
        
   <%
}
   %>

</tbody>
</table></div>
				<div class="d-grid gap-2 d-md-flex justify-content-md-end">
				<input type="hidden" name="ProjetID" value="<%=ProjetSelected%>">
				<input type="submit" class="btn btn-primary text-left" value="Affecter" />
				
				</div>

 
</form>   
</div>
</section>