<%@ include file="navbarDev.jsp" %>
  
        
 <script src="scpt/notif.js"></script>
<section>
<div class="container">
  <div class="row">
    <div class="col-lg-6 mx-auto mt-4">
        <div class="card-body p-4">
<form action="SaveUpdateAvancementServlet" method="post">
<%
			ArrayList<Projet> projets = (ArrayList<Projet>) request.getAttribute("projets");
    
%>
	 <div class="form-group">
		<label style=" color: #454EA38F; for="projetSelect">Le projet :</label>
		<select id="projetSelect" name="projetSelect">
		    <option value="">Sélectionnez un projet</option>
		    <% for (Projet projet : projets) { %>
		        <option value="<%= projet.getId() %>"><%= projet.getNom() %></option>
		    <% } %>
		</select>
	</div>

	<div class="form-group">
		<label style=" color: #454EA38F;for="serviceSelect">Le service :</label>
		<select id="serviceSelect" name="serviceSelect" disabled>
		    <option value="">Sélectionnez un service</option>
		  
		</select>
	</div>


	<div class="form-group">
		<label style=" color: #454EA38F; for="tacheSelect">La tache :</label>
		<select id="tacheSelect" name="tacheSelect" disabled>
		    <option value="">Sélectionnez une tache</option>
		  
		</select>
	</div>



	<div class="form-group">
		<label style=" color: #454EA38F; for="description">La Description de la tache :</label>
		<input type="text" id="description" name="description" >
	</div>


	<div class="form-group">
		<label style=" color: #454EA38F; for="avancement">L'Avancement :</label>
		<input type="text" id="avancement" name="avancement" >
	</div>
	
	
	<button style=" background-color: #454EA38F;  id="annulerButton" class="btn btn-primary mb-3" >Annuler</button>
	<button style=" background-color: #454EA38F;  id="enregistrerButton" class="btn btn-primary mb-3" onclick="fct()">Valider</button>
</form>


</div></div></div></div>

   <script>
 document.getElementById('projetSelect').addEventListener('change', function() {
	    var selectedProjetId = this.value;

	    var xhrServices = new XMLHttpRequest();
	    xhrServices.onreadystatechange = function() {
	        if (xhrServices.readyState === 4 && xhrServices.status === 200) {
	            var services = JSON.parse(xhrServices.responseText);
	            var serviceSelect = document.getElementById('serviceSelect');
	            serviceSelect.innerHTML = '<option value="">Sélectionnez un service</option>';
	            serviceSelect.disabled = false;

	            services.forEach(function(service) {
	                var option = document.createElement('option');
	                option.value = service.id;
	                option.textContent = service.description;
	                serviceSelect.appendChild(option);
	            });
	        }
	    };
	    
	    xhrServices.open('GET', '/TPl/AjaxService.jsp?projetId='+selectedProjetId,true);
	    xhrServices.send();
	});

 document.getElementById('serviceSelect').addEventListener('change', function() {
	    var selectedServiceId = this.value;

	    var xhrTaches = new XMLHttpRequest();
	    xhrTaches.onreadystatechange = function() {
	        if (xhrTaches.readyState === 4 && xhrTaches.status === 200) {
	            var taches = JSON.parse(xhrTaches.responseText);
	            var tacheSelect = document.getElementById('tacheSelect');
	            tacheSelect.innerHTML = '<option value="">Sélectionnez une tâche</option>';
	            tacheSelect.disabled = false;

	            taches.forEach(function(tache) {
	                var option = document.createElement('option');
	                option.value = tache.id;
	                option.textContent = tache.nom;
	                tacheSelect.appendChild(option);
	            });
	        }
	    };
	    xhrTaches.open('GET', '/TPl/AjaxTache.jsp?serviceId=' + selectedServiceId, true);
	    xhrTaches.send();
	});

 document.getElementById('tacheSelect').addEventListener('change', function() {
	    var selectedTacheId = this.value;

	    var xhrTacheDetails = new XMLHttpRequest();
	    xhrTacheDetails.onreadystatechange = function() {
	        if (xhrTacheDetails.readyState === 4 && xhrTacheDetails.status === 200) {
	            var tacheDetails = JSON.parse(xhrTacheDetails.responseText);
	            document.getElementById('description').value = tacheDetails.description;
	            document.getElementById('avancement').value = tacheDetails.avancement;
	        }
	    };
	    xhrTacheDetails.open('GET', '/TPl/AjaxDetails.jsp?tacheId=' + selectedTacheId, true);
	    xhrTacheDetails.send();
	});

 
 document.querySelector('form').addEventListener('submit', function(event) {
	    event.preventDefault(); // Empêcher le comportement par défaut du formulaire

	    // Réinitialiser les champs à leur état initial ou vide, par exemple :
	    document.getElementById('description').value = '';
	    document.getElementById('avancement').value = '';

	    // Désélectionner les options dans les sélecteurs si nécessaire
	    document.getElementById('projetSelect').selectedIndex = 0;
	    document.getElementById('serviceSelect').selectedIndex = 0;
	    document.getElementById('tacheSelect').selectedIndex = 0;

	    // Désactiver les champs qui sont censés l'être
	    document.getElementById('serviceSelect').disabled = true;
	    document.getElementById('tacheSelect').disabled = true;

	    // Autres actions pour annuler les modifications...
	});
 
 document.getElementById('enregistrerButton').addEventListener('click', function() {
     // Récupérer les valeurs des champs modifiés
     var descriptionValue = document.getElementById('description').value;
     var avancementValue = document.getElementById('avancement').value;

     // Récupérer l'ID de la tâche sélectionnée
     var selectedTacheId = document.getElementById('tacheSelect').value;

     // Effectuer une requête AJAX pour envoyer ces données à la servlet
     var xhr = new XMLHttpRequest();
     xhr.onreadystatechange = function() {
         if (xhr.readyState === 4) {
             // Traiter la réponse de la servlet si nécessaire
             if (xhr.status === 200) {
                 // Action après l'enregistrement réussi, si nécessaire
             } else {
                 // Gérer une erreur en cas d'échec de l'enregistrement
             }
         }
     };

     // Envoyer les données modifiées à la servlet via POST
     xhr.open('POST', 'SaveUpdateAvancementServlet', true);
     xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
     xhr.send('tacheId=' + encodeURIComponent(selectedTacheId) + '&description=' + encodeURIComponent(descriptionValue) + '&avancement=' + encodeURIComponent(avancementValue));

    
 });
 

</script>
</section>