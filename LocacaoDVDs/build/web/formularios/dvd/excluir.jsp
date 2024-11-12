<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>

<html>
    <head>
        <title>Excluir DVD</title>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>
    </head>
    
    <body>
        
        <h1>Excluir DVD</h1>
        
        <form method="post" action="${cp}/processaDvds">

          <input name="acao" type="hidden" value="excluir"/>
          <input name="id" type="hidden" value="${requestScope.dvd.id}"/>

          <table>
            <tr>
              <td class="alinharDireita">Título:</td>
              <td>${requestScope.dvd.titulo}</td>
            </tr>
            <tr>
                <td class="alinharDireita">Ano de Lançamento:</td>
                <td>${requestScope.dvd.anoLancamento}</td>
            </tr>
            <tr>
              <td class="alinharDireita">Ator Principal:</td>
              <td>${requestScope.dvd.atorPrincipal.nome} ${requestScope.dvd.atorPrincipal.sobrenome}</td>
            </tr>
            <tr>
              <td class="alinharDireita">Ator Coadjuvante:</td>
              <td>${requestScope.dvd.atorCoadjuvante.nome} ${requestScope.dvd.atorCoadjuvante.sobrenome}</td>
            </tr>
            <tr>
              <td class="alinharDireita">Data de Lançamento:</td>
              <td><fmt:formatDate value="${requestScope.dvd.dataLancamento}" pattern="dd/MM/yyyy"/></td>
            </tr>
            <tr>
              <td class="alinharDireita">Duração em Minutos:</td>
              <td>${requestScope.dvd.duracaoMinutos}</td>
            </tr>
            
            <tr>
              <td class="alinharDireita">Classificação Etária:</td>
              <td>${requestScope.dvd.classificacao.descricao}</td>
            </tr>
            
            <tr>
              <td class="alinharDireita">Gênero:</td>
              <td>${requestScope.dvd.genero.descricao}</td>
            </tr>
            <tr>
              <td>
                <a href="${cp}/formularios/dvd/listagem.jsp">Voltar</a>
              </td>
              <td class="alinharDireita">
                <input type="submit" value="Excluir"/>
              </td>
            </tr>
          </table>

        </form>

        
        
    </body>
</html>
