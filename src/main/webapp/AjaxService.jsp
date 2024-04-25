<%@ page language="java" contentType="application/json; charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="models.*" %>
<%@ page import="businessLayer.*" %>
<%
    String projetIdString = request.getParameter("projetId");
    int projetId = 0;
    System.err.println("----------------------------------------------------------------------------------- tinuer"+projetId);

    if (projetIdString != null && !projetIdString.isEmpty()) {
        projetId = Integer.parseInt(projetIdString);
        ProjectManager serviceService = new ProjectManager();
        ArrayList<Service> services = serviceService.getServices(projetId);
        
       

        // Convert services to JSON
        StringBuilder jsonServices = new StringBuilder("[");
        for (Service service : services) {
            jsonServices.append("{\"id\":").append(service.getId())
                        .append(",\"description\":\"").append(service.getDescrip()).append("\"},");
        }
        if (jsonServices.length() > 1) {
            jsonServices.deleteCharAt(jsonServices.length() - 1);
        }
        jsonServices.append("]");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonServices.toString());
    }
%>