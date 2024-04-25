<%@ include file="navbarChp.jsp" %>

<section>

    <br>
    <div class="container detail">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div>
                    <%
                    Projet prj = (Projet)request.getAttribute("projet");
                    ArrayList<Technologie> listTech = prj.getTechnologie();
                    ArrayList<Developpeur> listDev = (ArrayList<Developpeur>) request.getAttribute("Equipe");
                    %>

                    <h1  style="color:#454EA38F; text-align:center;">Information Sur Votre Projet</h1><br>
                    <div style="text-align:center;">
                    <b style="color:#454EA38F">Nom du projet :</b><b><%=prj.getNom() %></b><br>
                    <b style="color:#454EA38F">La Methodologie :</b> <b><%= prj.getMethodologie().getNom() %></b><br>
                    <b style="color:#454EA38F">Les Technologies :</b>
                    <br>
                    <ul>
                   <b>
                   
   
                    <% 
                    for(Technologie tech : listTech) {
                    %>
                     
                    <p class="ms-3"><%=tech.getNom() %></p>
                    
                    <% } %></ul>
                    <br>
                    <b style="color:#454EA38F">Membre de l'Equipe:</b>
                    <%
                    for(Developpeur dev :listDev ) {
                    %>
                    <p class="ms-3"><%= dev.getNom_complet() %></p>
                    <% } %>
                    </b>
                    <br>
                    <form action="FormerEquipeServlet" method="get">
                        <b style="color:#454EA38F">Attribuer La date du Réunion : <input type="Date" name="dateRn"></b><br>
                        <br>
                        <input type="hidden" name="projetID" value="<%= prj.getId() %>" />
                        <input type="hidden" name="projetName" value="<%= prj.getNom() %>" />
                        <input type="hidden" name="action" value="AddDate">
                        <div class="text-md-centre">
                            <input type="submit" class="btn btn-primary" value="Enregistrer" />
                        </div>
                        
                    </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    

</section>
