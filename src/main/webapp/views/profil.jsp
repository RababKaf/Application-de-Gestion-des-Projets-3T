<%@ include file="navbarDev.jsp" %>
<section>
 
     
<%
			ArrayList<Competance> competances = (ArrayList<Competance>) request.getAttribute("competance");
    		User developpeur=(User)request.getAttribute("developpeur");
    
%>
<div class="container">
  <div class="row">
    <div class="col-lg-6 mx-auto mt-4">
      <div class="card shadow">
        <div class="card-body p-4">
<form id="monFormulaire" action="EnregistrerProfilServlet" method="post"  class="container mt-5">
      <b>
    <label style=" color: #454EA38F; for="nomComplet">NomComplet :</label>
    <input type="text" id="nomComplet" name="nomComplet" required value="<%= developpeur.getNom_complet() %>"  class="form-control mb-3">
    
    <label style=" color: #454EA38F; for="telephone">T�l�phone :</label>
    <input type="tel" id="telephone" name="telephone" required value="<%= developpeur.getTel() %>"  class="form-control mb-3">
    
    <label style=" color: #454EA38F; for="email">Email :</label>
    <input type="email" id="email" name="email" required value="<%= developpeur.getEmail() %>"  class="form-control mb-3">

   <label style=" color: #454EA38F;">Les Competances :</label>
 <div id="competences" name="competences">
    <% for (Competance competence : competances) { %>
        <div class="competence">
            <label style=" color: #454EA38F; for="<%= competence.getTechnologie().getNom() %>"></label>
            <input type="text" name="competence" value="<%= competence.getTechnologie().getNom() %>" required class="form-control mb-3">
       </b>
       
        <select name="niveau" required class="form-control mb-3">
            <option value="D�butant" <%= competence.getNiveau().equals("D�butant") ? "selected" : "" %>>D�butant</option>
            <option value="Interm�diaire" <%= competence.getNiveau().equals("Interm�diaire") ? "selected" : "" %>>Interm�diaire</option>
            <option value="Avanc�" <%= competence.getNiveau().equals("Avanc�") ? "selected" : "" %>>Avanc�</option>
        </select>
        </div>
    <% } %>
</div>

    <button style=" background-color: #454EA38F; type="button" onclick="ajouterCompetence()" class="btn btn-primary mb-3">Ajouter Competence</button>
    <br>
    <input style=" background-color: #454EA38F; type="submit" value="Valider" class="btn btn-primary mb-3">
</form></div></div></div></div></div>

<script>
    function ajouterCompetence() {
        let divCompetence = document.createElement("div");
        
        let labelCompetence = document.createElement("label");
        labelCompetence.innerHTML = "Comp�tence :";
        divCompetence.appendChild(labelCompetence);
        
        let inputCompetence = document.createElement("input");
        inputCompetence.setAttribute("type", "text");
        inputCompetence.setAttribute("name", "nouvelleCompetence");
        inputCompetence.setAttribute("placeholder", "Nom de la comp�tence");
        divCompetence.appendChild(inputCompetence);
        
        let labelNiveau = document.createElement("label");
        labelNiveau.innerHTML = "Niveau :";
        divCompetence.appendChild(labelNiveau);
        
        let selectNiveau = document.createElement("select");
        selectNiveau.setAttribute("name", "nouveauNiveau");
        
        let optionDebutant = document.createElement("option");
        optionDebutant.setAttribute("value", "D�butant");
        optionDebutant.text = "D�butant";
        let optionIntermediaire = document.createElement("option");
        optionIntermediaire.setAttribute("value", "Interm�diaire");
        optionIntermediaire.text = "Interm�diaire";
        let optionAvance = document.createElement("option");
        optionAvance.setAttribute("value", "Avanc�");
        optionAvance.text = "Avanc�";
        
        selectNiveau.appendChild(optionDebutant);
        selectNiveau.appendChild(optionIntermediaire);
        selectNiveau.appendChild(optionAvance);
        
        divCompetence.appendChild(selectNiveau);
        
        document.getElementById("competences").appendChild(divCompetence);
        
    }
</script>

</section>