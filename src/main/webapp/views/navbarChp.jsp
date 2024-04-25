<%@ include file="/TraitementJSP/session.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Page Title</title>
        <link rel="stylesheet" type="text/css" href="css/cssJEE/NavBar.css">
        <link rel="stylesheet" type="text/css" href="css/cssJEE/modal.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">     
       <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css">
       <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
       <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
      
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.15.5/dist/sweetalert2.min.css">
      <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.15.5/dist/sweetalert2.min.js"></script>


  
        
 <script src="scpt/notif.js"></script>

</head>
<body>

    <header>
        <img src="data:image/png;base64, <%= image %>" alt="Picture" id="profile-pic">
        <h1><%= nomcomplet %></h1>
              <div class="notification-container">
        <i class="fas fa-bell" style="font-size: 24px;" onclick="toggleNotifications()"></i>
        <span class="notification-badge" id="notificationCount"><%= unreadCount %></span>
           <div class="notification-messages" id="notificationMessages">
            <%    Enumeration<Integer> keys = notifications.keys();
            while (keys.hasMoreElements()) {Integer key = keys.nextElement();
           String value = notifications.get(key);
            System.out.println("Key: " + key + ", Value: " + value); %>
                <div class="notification-card" id="<%= key %>" onclick="markAsRead('<%=key%>')">
                    <p class="short-message"><%=value.substring(0,20) + "...."%></p>
                    <p class="full-message" style="display: none;"><%= value %></p>
                </div>
            <% } %>
             <%    keys=null;
               keys = notreaded.keys();
            while (keys.hasMoreElements()) {Integer key = keys.nextElement();
           String value = notreaded.get(key);
            System.out.println("Key: " + key + ", Value: " + value); %>
                <div class="notification-card" style="background-color:#fff;" id="<%= key %>" >
                    
                    <p><%= value %></p>
                </div>
            <% } %>
        </div>
        
         </div>
  
         
             <a href="/TPl/DeconnecterServlet" class="logout-link">
            <i class="fas fa-sign-out-alt" style="font-size: 24px;"></i>
            <span>Déconnexion</span>
        </a>
         
    </header>
    

    <nav>
          <ul>
            <li class="menu-item">
                <a href="/TPl/ProjectServlet?id=<%=id%>">
                    <i class="fas fa-home"></i> Accueil
                </a>
            </li>
            <li class="menu-item">
                <a href="/TPl/ProjectServlet?id=<%=id%>">
                    <i class="fas fa-folder-open"></i> Consulter Projet
                </a>
            </li>
            <li class="menu-item">
                <a href="/TPl/testServlet?id=<%=id%>">
                    <i class="fas fa-cogs"></i> Affecter Les Services
                </a>
            </li>
        </ul>
    </nav>


</body>
</html>

