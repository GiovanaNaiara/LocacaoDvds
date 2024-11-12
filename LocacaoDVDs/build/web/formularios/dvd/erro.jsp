<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <title>Erros ao Criar ou Alterar DVD</title>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>
    </head>
    
    <body>
        <h1>Erro!</h1>
        
        <ul>
            
            <c:if test="${fn:length(param.titulo) > 100}">
                <li>O título "${param.titulo}" tem mais de 100 caracteres.</li>
            </c:if>
            
            <c:if test="${fn:length(param.titulo) eq 0}">
                <li>O campo título está vazio. Preencha-o.</li>
            </c:if>
            
        </ul>
        
        <a class=alinharDireita href="${cp}/formularios/dvd/listagem.jsp">Voltar</a>
    </body>
</html>
