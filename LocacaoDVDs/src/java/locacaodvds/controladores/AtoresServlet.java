package locacaodvds.controladores;

import jakarta.servlet.RequestDispatcher;
import locacaodvds.dao.AtorDAO;
import locacaodvds.entidades.Ator;
import java.sql.SQLException;
import java.sql.Date;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AtoresServlet", urlPatterns = {"/processaAtores"})
public class AtoresServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
            String acao = request.getParameter("acao");
            AtorDAO dao = null;
            RequestDispatcher disp = null;
            
            try{
            
                dao = new AtorDAO();
                
                if( acao.equals("inserir") ){
                    
                    String nome = request.getParameter("nome");
                    String sobrenome = request.getParameter("sobrenome");
                    Date dataEstreia = Date.valueOf(request.getParameter("dataEstreia"));
                    String data = dataEstreia.toString();
                
                    if( nome.length() > 45 || nome.isEmpty() 
                            || sobrenome.length() > 45 || sobrenome.isEmpty()){

                        disp = request.getRequestDispatcher("/formularios/ator/erro.jsp");
                    }else{
                    
                    Ator a = new Ator();
                    a.setNome(nome);
                    a.setSobrenome(sobrenome);
                    a.setDataEstreia(dataEstreia);
                    dao.salvar(a);
                    disp = request.getRequestDispatcher("/formularios/ator/listagem.jsp");
                    }
                    
                }else if( acao.equals("alterar") ){
                    
                    String nome = request.getParameter("nome");
                    String sobrenome = request.getParameter("sobrenome");
                    Date dataEstreia = Date.valueOf(request.getParameter("dataEstreia"));
                    String data = dataEstreia.toString();
                
                    if( nome.length() > 45 || nome.isEmpty() 
                            || sobrenome.length() > 45 || sobrenome.isEmpty()){

                        disp = request.getRequestDispatcher("/formularios/ator/erro.jsp");
                    }else{
                        Ator a = new Ator();
                        int id = Integer.parseInt(request.getParameter("id"));
                        a.setId(id);
                        a.setNome(nome);
                        a.setSobrenome(sobrenome);
                        a.setDataEstreia(dataEstreia);
                        dao.atualizar(a);
                        disp = request.getRequestDispatcher("/formularios/ator/listagem.jsp");
                    }
                }else if( acao.equals("excluir") ){
                    
                    Ator a = new Ator();
                    int id = Integer.parseInt(request.getParameter("id"));
                    dao.excluir(dao.obterPorId(id));
                    disp = request.getRequestDispatcher("/formularios/ator/listagem.jsp");
                
                }else {
                
                    int id = Integer.parseInt(request.getParameter( "id" ));
                    Ator a = dao.obterPorId( id );
                    request.setAttribute( "ator", a );

                    if ( acao.equals( "prepararAlteracao" ) ) {
                        disp = request.getRequestDispatcher( 
                                "/formularios/ator/alterar.jsp" );
                    } else if ( acao.equals( "prepararExclusao" ) ) {
                        disp = request.getRequestDispatcher( 
                                "/formularios/ator/excluir.jsp" );
                    }

                }
                
            }catch( SQLException exc ){
                exc.printStackTrace();
            }finally{
            
                if( dao != null ){
                    
                    try{
                        dao.fecharConexao();
                    }catch( SQLException exc ){
                        exc.printStackTrace();
                    }
                }
                
            }   
            
            if( disp != null ){
                disp.forward(request, response);
            }
            
        }
    
    @Override
    protected void doGet( 
            HttpServletRequest request, 
            HttpServletResponse response )
            throws ServletException, IOException {
        processRequest( request, response );
    }

    @Override
    protected void doPost( 
            HttpServletRequest request, 
            HttpServletResponse response )
            throws ServletException, IOException {
        processRequest( request, response );
    }

    @Override
    public String getServletInfo() {
        return "AtoresServlet";
    }
}


   