<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>

<html>
    <head>
        <title>Alterar DVD</title>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>
    </head>
    
    <body>
        <h1>Alterar DVD</h1>
    
        <form method="post"  action="${cp}/processaDvds">

          <input name="acao" type="hidden" value="alterar"/>
          <input name="id" type="hidden" value="${requestScope.dvd.id}"/>

          <table>
            <tr>
              <td class="alinharDireita">Título:</td>
              <td>
                <input name="titulo"
                       type="text"
                       size="20"
                       maxlength="100"
                       required
                       value="${requestScope.dvd.titulo}"/>
              </td>
            </tr>
            
            <tr>
                  <td>Ano Lançamento:</td>
                  <td>
                      <input name="anoLancamento"
                             type="number"
                             size="3"
                             maxlength="11"
                             required
                             value="${requestScope.dvd.anoLancamento}"/>
                  </td>
              </tr>
              
            <jsp:useBean id="servicos"
                    scope="page"
                    class="locacaodvds.servicos.AtorServices"/>
              <tr>
                <td class="alinharDireita">Ator Principal:</td>
                
                <td>
                   <select name="atorPrin-selecao" required>
                       <option value="${requestScope.dvd.atorPrincipal.id}" selected>${requestScope.dvd.atorPrincipal.nome} ${requestScope.dvd.atorPrincipal.sobrenome}</option>
                        <c:forEach items="${servicos.todos}" var="ator">
                            <c:if test="${requestScope.dvd.atorPrincipal.id != ator.id}">
                                <option value="${ator.id}">${ator.nome} ${ator.sobrenome}</option>
                            </c:if>    
                            
                        </c:forEach>
                   </select>
                </td>
              
              </tr>
              
              <tr>
                <td class="alinharDireita">Ator Coadjuvante:</td>
                
                <td>
                   <select name="atorCdj-selecao" required>
                       <option value="${requestScope.dvd.atorCoadjuvante.id}" selected>${requestScope.dvd.atorCoadjuvante.nome} ${requestScope.dvd.atorPrincipal.sobrenome}</option>
                        <c:forEach items="${servicos.todos}" var="ator">
                            <c:if test="${requestScope.dvd.atorCoadjuvante.id != ator.id}">
                                <option value="${ator.id}">${ator.nome} ${ator.sobrenome}</option>
                            </c:if>    
                            
                        </c:forEach>
                   </select>
                </td>
              
              </tr>
              
              <tr>
                  <td>Data de Lançamento:</td>
                  <td>
                      <input name="dataLancamento" 
                             type="Date"
                             size="5"
                             maxlength="8"
                             required
                             value="${requestScope.dvd.dataLancamento}"/>
                      <fmt:formatDate value="${dataLancamento}" pattern="dd/MM/yyyy"/>
                      
                  </td>
              </tr>
              
              <tr>
                  <td>Duração em Minutos:</td>
                  <td>
                      <input name="duracao"
                             type="number"
                             size="3"
                             maxlength="11"
                             required
                             value="${requestScope.dvd.duracaoMinutos}"/>
                  </td>
              </tr>
              
              <jsp:useBean id="servicosClassificacao"
                           scope="page"
                           class="locacaodvds.servicos.ClassificacaoEtariaServices"/>
              
              <tr>
                  <td>Classificação Etária:</td>
                  <td>
                      <select name="classificacao-selecao" required>
                          <option value="${requestScope.dvd.classificacao.id}" selected>${requestScope.dvd.classificacao.descricao}</option>
                          <c:forEach items="${servicosClassificacao.todos}" var="classificacao">
                              <c:if test="${requestScope.dvd.classificacao.id != classificacao.id}">
                                  <option value="${classificacao.id}">${classificacao.descricao}</option>
                              </c:if>
                          </c:forEach>
                          
                      </select>
                      
                  </td>
              </tr>
              
              <jsp:useBean id="servicosGenero"
                           scope="page"
                           class="locacaodvds.servicos.GeneroServices"/>
              
              <tr>
                  <td>Gênero:</td>
                  <td>
                      <select name="genero-selecao">
                          <option value="${requestScope.dvd.genero.id}" selected>${requestScope.dvd.genero.descricao}</option>
                          <c:forEach items="${servicosGenero.todos}" var="genero">
                              <c:if test="${requestScope.dvd.genero.id != genero.id}">
                                <option value="${genero.id}">${genero.descricao}</option>
                              </c:if>
                          </c:forEach>
                          
                      </select>
                      
                  </td>
              </tr>
             
         
            <tr>
              <td>
                <a href="${cp}/formularios/dvd/listagem.jsp">Voltar</a>
              </td>
              <td class="alinharDireita">
                <input type="submit" value="Alterar"/>
              </td>
            </tr>
          </table>

        </form>

        
    </body>

</html>
