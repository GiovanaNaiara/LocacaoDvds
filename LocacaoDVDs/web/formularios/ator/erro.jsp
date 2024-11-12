<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
    <head>
        <title>Erros ao Criar ou Alterar Atores</title>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>
    </head>
    
    <body>
        
        <h1>Erro!</h1>
        
        <ul>
            
            <c:if test="${fn:length(param.nome) > 45}">
                <li>O nome "${param.nome}" tem mais de 45 caracteres.</li>
            </c:if>
            
            <c:if test="${fn:length(param.nome) eq 0}">
                <li>O campo nome está vazio. Preencha-o.</li>
            </c:if>
                
            <c:if test="${fn:length(param.sobrenome) > 45}">
                <li>O nome "${param.sobrenome}" tem mais de 45 caracteres.</li>
            </c:if>
            
            <c:if test="${fn:length(param.sobrenome) eq 0}">
                <li>O campo sobrenome está vazio. Preencha-o.</li>
            </c:if>
            
        </ul>
        
        <a class=alinharDireita href="${cp}/formularios/ator/listagem.jsp">Voltar</a>
        
    </body>
</html>
