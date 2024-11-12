<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
    <head>
        
        <title>Locação de Dvds</title>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>
    </head>
    <body>
        <h1>Locação de Dvds</h1>
        <a href="${cp}/formularios/ator/listagem.jsp">Atores</a>
        <a href="${cp}/formularios/dvd/listagem.jsp">DVDs</a>
        <a href="${cp}/formularios/classificacao/listagem.jsp">Classificações</a>
        <a href="${cp}/formularios/genero/listagem.jsp">Gêneros</a>
    </body>
</html>
