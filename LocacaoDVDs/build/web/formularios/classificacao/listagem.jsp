<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<c:set var="prefixo" value="processaClassificacao?acao=preparar"/>
<!DOCTYPE html>

<html>
    <head>
        <title>Classificações Etárias Cadastradas</title>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>
    </head>
    
    <body>
        <h1>Classificações Etárias Cadastradas</h1>
        
        <p>
            <a href="${cp}/formularios/classificacao/novo.jsp">
              Nova Classificação
            </a>
        </p>
        
        <table class="tabelaListagem">
          <thead>
            <tr>
              <th>Id</th>
              <th>Descrição</th>
              <th>Alterar</th>
              <th>Excluir</th>
            </tr>
            </thead>
            <tbody>

                <jsp:useBean
                    id="servicos"
                    scope="page"
                    class="locacaodvds.servicos.ClassificacaoEtariaServices"/>

                <c:forEach items="${servicos.todos}" var="classificacao">
                  <tr>
                    <td>${classificacao.id}</td>
                    <td>${classificacao.descricao}</td>
                    <td>
                      <a href="${cp}/${prefixo}Alteracao&id=${classificacao.id}">
                        Alterar
                      </a>
                    </td>
                    <td>
                      <a href="${cp}/${prefixo}Exclusao&id=${classificacao.id}">
                        Excluir
                      </a>
                    </td>
                  </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <p>
            <a href="${cp}/formularios/classificacao/novo.jsp">
              Nova Classificacao
            </a>
        </p>

        <p><a href="${cp}/index.jsp">Tela Principal</a></p>
        
    </body>
    
</html>
