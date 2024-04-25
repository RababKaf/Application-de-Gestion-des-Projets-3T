<%@ page language="java" contentType="application/json; charset=UTF-8"%>
<%@ page import="models.*" %>
<%@ page import="businessLayer.*" %>
<%
    String tacheIdString = request.getParameter("tacheId");
    int tacheId = 0;

    if (tacheIdString != null && !tacheIdString.isEmpty()) {
        tacheId = Integer.parseInt(tacheIdString);
        ProjectManager tacheService = new ProjectManager();
        Tache tache = tacheService.getTacheDetails(tacheId);

        // Convert tache details to JSON
        String jsonTacheDetails = "{";
        jsonTacheDetails += "\"description\":\"" + tache.getDescrip() + "\",";
        jsonTacheDetails += "\"avancement\":\"" + tache.getAvancement() + "\"}";
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonTacheDetails);
    }
%>