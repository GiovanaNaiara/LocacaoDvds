package locacaodvds.controladores;

import jakarta.servlet.RequestDispatcher;
import locacaodvds.dao.ClassificacaoDAO;
import locacaodvds.entidades.ClassificacaoEtaria;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ClassificacaoServlet", urlPatterns = {"/processaClassificacao"})
public class ClassificacaoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            String acao = request.getParameter("acao");
            ClassificacaoDAO dao = null;
            RequestDispatcher disp = null;
            
            try{
            
                dao = new ClassificacaoDAO();

                if( acao.equals("inserir") ){
                    
                    String descricao = request.getParameter("descricao");
                    
                    if( descricao.isEmpty() || descricao.length() > 40 ){
                        disp = request.getRequestDispatcher("/formularios/classificacao/erro.jsp");
                    }else{

                        ClassificacaoEtaria c = new ClassificacaoEtaria();
                        c.setDescricao(descricao);
                        dao.salvar(c);
                        disp = request.getRequestDispatcher("/formularios/classificacao/listagem.jsp");
                    }   
                    
                }else if( acao.equals("alterar") ){
                    
                    String descricao = request.getParameter("descricao");
                    
                    if( descricao.isEmpty() || descricao.length() > 40 ){
                        disp = request.getRequestDispatcher("/formularios/classificacao/erro.jsp");
                    }else{

                        ClassificacaoEtaria c = new ClassificacaoEtaria();
                        int id = Integer.parseInt(request.getParameter("id"));
                        c.setId(id);
                        c.setDescricao(descricao);
                        dao.atualizar(c);
                        disp = request.getRequestDispatcher("/formularios/classificacao/listagem.jsp");
                    }    
                        
                }else if( acao.equals("excluir") ){
                    
                    ClassificacaoEtaria c = new ClassificacaoEtaria();
                    int id = Integer.parseInt(request.getParameter("id"));
                    dao.excluir(dao.obterPorId(id));
                    disp = request.getRequestDispatcher("/formularios/classificacao/listagem.jsp");
                
                }else {
                
                    int id = Integer.parseInt(request.getParameter( "id" ));
                    ClassificacaoEtaria c = dao.obterPorId( id );
                    request.setAttribute( "classificacao", c );

                    if ( acao.equals( "prepararAlteracao" ) ) {
                        disp = request.getRequestDispatcher( 
                                "/formularios/classificacao/alterar.jsp" );
                    } else if ( acao.equals( "prepararExclusao" ) ) {
                        disp = request.getRequestDispatcher( 
                                "/formularios/classificacao/excluir.jsp" );
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
        return "ClassificacaoServlet";
    }
}


   