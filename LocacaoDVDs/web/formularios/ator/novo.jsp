<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>

<html>
    <head>
        <title>Novo Ator</title>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>
    </head>
    
    <body>
        
        <h1>Novo Ator</h1>
        
        <form method="post" action="${cp}/processaAtores">

            <input name="acao" type="hidden" value="inserir"/>

            <table>
              <tr>
                <td class="alinharDireita">Nome:</td>
                <td>
                  <input name="nome"
                         type="text"
                         size="20"
                         maxlength="45"
                         required/>
                </td>
              </tr>
              <tr>
                <td class="alinharDireita">Sobrenome:</td>
                <td>
                    <input type="text"
                           name="sobrenome"
                           size="20"
                           maxlength="45"
                           required/>
                </td>
              </tr>
              <tr>
                <td class="alinharDireita">Data de Estreia:</td>
                <td>
                    
                    <input type="date"
                           name="dataEstreia"
                           size="10"
                           required/>
                    
                    <fmt:formatDate value="${dataEstreia}" pattern="dd/MM/yyyy"/>
                    
                </td>
              </tr>
              <tr>
                <td>
                  <a href="${cp}/formularios/ator/listagem.jsp">Voltar</a>
                </td>
                <td class="alinharDireita">
                  <input type="submit" value="Salvar"/>
                </td>
              </tr>
            </table>

         </form>

    
    </body>
    
</html>
