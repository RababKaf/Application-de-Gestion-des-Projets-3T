<%@ page language="java" contentType="application/json; charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="models.*" %>
<%@ page import="businessLayer.*" %>
<%
    String serviceIdString = request.getParameter("serviceId");
    int serviceId = 0;

    if (serviceIdString != null && !serviceIdString.isEmpty()) {
        serviceId = Integer.parseInt(serviceIdString);
        ProjectManager tacheService = new ProjectManager();
        ArrayList<Tache> taches = tacheService.getTaches(serviceId);

        // Convert taches to JSON
        StringBuilder jsonTaches = new StringBuilder("[");
        for (Tache tache : taches) {
            jsonTaches.append("{\"id\":").append(tache.getId())
                       .append(",\"nom\":\"").append(tache.getNom()).append("\"},");
        }
        if (jsonTaches.length() > 1) {
            jsonTaches.deleteCharAt(jsonTaches.length() - 1);
        }
        jsonTaches.append("]");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonTaches.toString());
    }
%>