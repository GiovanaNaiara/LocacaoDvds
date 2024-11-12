<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>

<html>
    <head>
        <title>Novo DVD</title>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet"
              href="${cp}/css/estilos.css"/>
    </head>
    
    <body>
        
        <h1>Novo DVD</h1>
        
        <form method="post" action="${cp}/processaDvds">

            <input name="acao" type="hidden" value="inserir"/>

            <table>
              <tr>
                <td class="alinharDireita">Título:</td>
                <td>
                  <input name="titulo"
                         type="text"
                         size="20"
                         maxlength="100"
                         required/>
                </td>
              </tr>
              <tr>
                  <td>Ano Lançamento:</td>
                  <td>
                      <input name="anoLancamento"
                             type="number"
                             size="3"
                             maxlength="11"
                             required/>
                  </td>
              </tr>
              <jsp:useBean id="atorPrincipal"
                    scope="page"
                    class="locacaodvds.servicos.AtorServices"/>
              <tr>
                <td class="alinharDireita">Ator Principal:</td>
                
                <td>
                   <select name="atorPrin-selecao" required> 
                        <c:forEach items="${atorPrincipal.todos}" var="ator">
                                <option value="${ator.id}">${ator.nome} ${ator.sobrenome}</option>
                        </c:forEach>
                   </select>
                </td>
              
              </tr>
              
              <jsp:useBean id="atorCoadjuvante"
                    scope="page"
                    class="locacaodvds.servicos.AtorServices"/>
              
              <tr>
                <td class="alinharDireita">Ator Coadjuvante:</td>
                <td>
                   <select name="atorCdj-selecao" required> 
                        <c:forEach items="${atorCoadjuvante.todos}" var="ator">
                                <option value="${ator.id}">${ator.nome} ${ator.sobrenome}</option>
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
                             required/>
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
                             required/>
                  </td>
              </tr>
              
              <jsp:useBean id="servicosClassificacao"
                           scope="page"
                           class="locacaodvds.servicos.ClassificacaoEtariaServices"/>
              
              <tr>
                  <td>Classificação Etária:</td>
                  <td>
                      <select name="classificacao-selecao" required>
                          
                          <c:forEach items="${servicosClassificacao.todos}" var="classificacao">
                              <option value="${classificacao.id}">${classificacao.descricao}</option>
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
                          
                          <c:forEach items="${servicosGenero.todos}" var="genero">
                              <option value="${genero.id}">${genero.descricao}</option>
                          </c:forEach>
                          
                      </select>
                      
                  </td>
              </tr>
              <tr>
                <td>
                  <a href="${cp}/formularios/dvd/listagem.jsp">Voltar</a>
                </td>
                <td class="alinharDireita">
                  <input type="submit" value="Salvar"/>
                </td>
              </tr>
            </table>

         </form>

    
    </body>
    
</html>
