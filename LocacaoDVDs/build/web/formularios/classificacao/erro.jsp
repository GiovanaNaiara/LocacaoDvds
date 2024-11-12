<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <title>Erros ao Criar ou Alterar Classificações Etárias</title>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>
    </head>
    
    <body>
        <h1>Erro!</h1>
        
        <ul>
            
            <c:if test="${fn:length(param.descricao) > 40}">
                <li>A descrição "${param.descricao}" tem mais de 40 caracteres.</li>
            </c:if>
            
            <c:if test="${fn:length(param.descricao) eq 0}">
                <li>O campo descrição está vazio. Preencha-o.</li>
            </c:if>
           
        </ul>
        
        <a class=alinharDireita href="${cp}/formularios/classificacao/listagem.jsp">Voltar</a>
        
    </body>
</html>
