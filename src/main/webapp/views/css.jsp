<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<style>
 .modal {
    display: none; /* Masquer le modal par défaut */
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgba(0,0,0,0.4);
  }

  .modal-content {
    background-color: #fefefe;
    margin: 15% auto;
    padding: 20px;
    border: 1px solid #888;
    width: 80%;
  }
  
   #projectTable {
            width: 85%; /* Largeur de 60% de la page */
            margin: 30px auto 0; /* Décalage vers le bas et centrage horizontal */
        }
        
        
   .btn-primary {
            background-color: #007bff; /* Bleu Bootstrap */
            color: #ffd700; /* Gold pour le texte */
            border-color: #007bff;
        }

        .btn-primary:hover {
            background-color: #555; /* Gris au survol */
        }
</style> 
</body>
</html>