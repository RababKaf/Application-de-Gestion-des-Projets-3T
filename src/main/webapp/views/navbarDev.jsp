<%@ include file="/TraitementJSP/session.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Page Title</title> 
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/cssJEE/NavBar.css">
    <script src="scpt/notif.js"></script>
           <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css">
       <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
       <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
       
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.15.5/dist/sweetalert2.min.css">
      <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.15.5/dist/sweetalert2.min.js"></script>
  
 </head>
<body>

    <header>
        <img src="data:image/png;base64, <%= image %>" alt="Picture" id="profile-pic">
        <h2><%= nomcomplet %></h2>
          <div class="notification-container">
        <i class="fas fa-bell" style="font-size: 24px;" onclick="toggleNotifications()"></i>
        <span class="notification-badge" id="notificationCount"><%= unreadCount %></span>
           <div class="notification-messages" id="notificationMessages">
            <%    Enumeration<Integer> keys = notifications.keys();
            while (keys.hasMoreElements()) {Integer key = keys.nextElement();
           String value = notifications.get(key);
      %>
                <div class="notification-card" id="<%= key %>" onclick="markAsRead('<%=key%>')">
                    <p class="short-message"><%=value.substring(0,20) + "...."%></p>
                    <p class="full-message" style="display: none;"><%= value %></p>
                </div>
            <% } %>
             <%    keys=null;
               keys = notreaded.keys();
            while (keys.hasMoreElements()) {Integer key = keys.nextElement();
           String value = notreaded.get(key);
        %>
                <div class="notification-card" style="background-color:#fff;" id="<%= key %>" onclick="markAsRead('<%=key%>')" >
                    <p><%= value %></p>
                </div>
            <% } %>
        </div>
        
         </div>
         
               <a href="/TPl/DeconnecterServlet" class="logout-link">
            <i class="fas fa-sign-out-alt" style="font-size: 24px;"></i>
        </a>
         
    </header>
    

    <nav>
        <ul>
            <li class="menu-item">
                <a href="/TPl/ConsulterProjetS?nomcomplet=<%= nomcomplet%>">
                    <i class="fas fa-home"></i> Page Accueil
                </a>
            </li>
          
            <li class="menu-item">
                <a href="/TPl/ModifierProfilServlet?nomcomplet=<%= nomcomplet%>">
                    <i class="fas fa-user"></i> Profil
                </a>
            </li>
              <li class="menu-item">
                <a href="/TPl/ConsulterProjetS?nomcomplet=<%= nomcomplet%>">
                    <i class="fas fa-folder-open"></i> Consulter Les Projets
                </a>
            </li>
            <li class="menu-item">
                <a href="/TPl/EnregistrerAvancementServlet?nomcomplet=<%= nomcomplet%>">
                    <i class="fas fa-edit"></i> Enregistrer Votre Avancement
                </a>
            </li>
        </ul>
    </nav>


</body>
</html>

