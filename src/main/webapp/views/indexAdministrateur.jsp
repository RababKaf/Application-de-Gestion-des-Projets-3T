<%@ include file="navbarAdm.jsp" %>
<section> <% if (request.getAttribute("messageAPS") != null) { %>
<script>
  
    Swal.fire({
      title: 'Succès !',
      text: 'Le traitement est effectué avec succès.',
      icon: 'success',
      confirmButtonText: 'OK'
    });
 
</script>
 <% } %> Bienvenue mr l'administrateur </section>