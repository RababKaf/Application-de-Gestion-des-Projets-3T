<%@ include file="navbarAdm.jsp" %>
<section>
<style>

.select-wrapper {
    position: relative;
    display: inline-block;
}

.form-select {
    padding: 10px; 
    font-size: 16px;
    border: 1px solid #ccc;
    border-radius: 5px;
    appearance: none; 
    background-color: #fff;
    width: 150%; 
}
.select-wrapper::after {
    content: '\25BC'; 
    font-size: 12px; 
    color: #545784A6;
    position: absolute;
    top: 50%;
    right: -50px; 
    transform: translateY(-50%);
    pointer-events: none; 
}


</style>
 <script>   
 function verifyClient() {
     var cin = $("#cin").val();
     var client = listClient.find(obj => obj.cin === cin);

    
     if (client) {
         
         $("#clientEx").show();

        
         $("#nomclient").val(client.nomComplet);
         $("#tele").val(client.telephone);
         $("#adress").val(client.adresse);
     } 
     else {
      
         $("#clientEx").show();
         $("#nomclient").val("");
         $("#adress").val("");
         $("#tele").val("");
     }
 
}
 
 </script>

   <div class="container">
    <div class="row">
        <div class="col-md-6 mx-auto mt-3">
          <div class="card shadow" >
       <div style="background-color:#545784A6; text-align:center;color:#545784A6;class="card-header bg-primary" style=" text-align:center;">
    <h3 class="text-light">Ajouter Nouveau Projet</h3>
</div>
       
        <div class="card-body p-4">
          
            <form action="/TPl/AjouterProjetServlet" method="post">
            

                <div class="mb-3">
                    <label for="nomProjet" class="form-label">Nom du Projet:</label>
                    <input type="text" class="form-control" name="nomProjet" required>
                </div>

                <div class="mb-3">
                    <label for="description" class="form-label">Description du projet:</label>
                    <textarea class="form-control" name="description" rows="2" required></textarea>
                </div>
                  <div class="mb-3">
                    <label for="dema" class="form-label">Date de démarrage:</label>
                    <input type="date" class="form-control" name="dema" required>
                </div>

                <div class="mb-3">
                    <label for="dateLivraison" class="form-label">Date de Livraison:</label>
                    <input type="date" class="form-control" name="dateLivraison" required>
                </div>

                <div class="mb-3">
                    <label for="nbrJ" class="form-label">Nombre de Jours de Développement:</label>
                    <input type="number" class="form-control" name="nbrJ" required>
                </div>
                 <h4 style="text-align:center;color:#545784A6;">Information sur Le Client du Projet</h4>

                <div class="mb-3">
                   
                    <label for="cin" class="form-label">CIN du client:</label>
                    <input type="text" class="form-control" id="cin" name="cin" required>
                </div>

                <div class="mb-3" id="clientEx">
                    <label for="nomclient" class="form-label">Nom du client:</label>
                    <input type="text" class="form-control" id="nomclient" name="nomclient" required>

                    <label for="tele" class="form-label">Téléphone du client:</label>
                    <input type="text" class="form-control" id="tele" name="tele" required>

                    <label for="adress" class="form-label">Adresse du client:</label>
                    <input type="text" class="form-control" id="adress" name="adress" required>
                </div>

            <h4 style="text-align:center;color:#545784A6;">Affecteur le Projet à</h4>
                <div class="mb-3">
                    <label class="form-label">Chef de Projet :</label>
                    <br>
                    <div class="select-wrapper">
    <select class="form-select" name="chef">
        <%
            ArrayList<User> listchef = (ArrayList<User>) request.getAttribute("listChef");
            for(User chef : listchef) {
        %>
        <option value="<%= chef.getId() %>"><%= chef.getNom_complet() %></option>
        <% } %>
    </select>
    </div>
                </div>

             
               <div class="d-grid">
              <input style="background-color:#545784A6;" type="submit" name="submit" value="Ajouter" class="btn btn-primary btn-lg" />
            </div>
             

            </form>
        </div>
    </div>
</div>
</div></div>

        <script>
   
   
    $("#clientEx").show();

  
    var listClient = [];
    <% 
    ArrayList<Client> listC = (ArrayList<Client>) request.getAttribute("listClient");
    for (Client client : listC) {
    	//System.out.println(client.getCin());
    %>
        var client = {
            id: "<%= client.getId() %>",
            nomComplet: "<%= client.getNom_complet() %>",
            telephone: "<%= client.getTel() %>",
            adresse: "<%= client.getAdresse() %>",
            cin: "<%= client.getCin() %>"
        };
        listClient.push(client);
    <% } %>

   
    $("#cin").on("input", verifyClient);
   
    
    </script>
    
    
    
    
</section>
