<%@ include file="navbarAdm.jsp" %>
<section> <% if (request.getAttribute("messageAPS") != null) { %>
<script>
  
    Swal.fire({
      title: 'Succ�s !',
      text: 'Le traitement est effectu� avec succ�s.',
      icon: 'success',
      confirmButtonText: 'OK'
    });
 
</script>
 <% } %> Bienvenue mr l'administrateur </section>