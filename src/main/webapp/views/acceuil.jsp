<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menu</title>
    
    
   
    
     
</head>
<body>

<% if (request.getAttribute("messageAPS") != null) { %>
<script>
  
    Swal.fire({
      title: 'Succès !',
      text: 'Le traitement est effectué avec succès.',
      icon: 'success',
      confirmButtonText: 'OK'
    });
 
</script>
 <% } %>
 
 
    <h2>Menu</h2>
    <ul>
        <li><a href="/app/addProject" >Ajouter Projet</a></li>
        <li><a href="/app/ConsulterProjet">Consulter Projet</a></li>
    </ul>
    <script>
  
    
</body>

</html>
