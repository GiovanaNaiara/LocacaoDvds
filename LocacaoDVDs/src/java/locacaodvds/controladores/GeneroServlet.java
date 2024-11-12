package locacaodvds.controladores;

import jakarta.servlet.RequestDispatcher;
import locacaodvds.dao.GeneroDAO;
import locacaodvds.entidades.Genero;
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

@WebServlet(name = "GeneroServlet", urlPatterns = {"/processaGenero"})
public class GeneroServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            String acao = request.getParameter("acao");
            GeneroDAO dao = null;
            RequestDispatcher disp = null;
            
            try{
            
                dao = new GeneroDAO();

                if( acao.equals("inserir") ){
                    
                    String descricao = request.getParameter("descricao");
                    
                    if( descricao.isEmpty() || descricao.length() > 40 ){
                        disp = request.getRequestDispatcher("/formularios/genero/erro.jsp");
                    }else{

                        Genero g = new Genero();
                        g.setDescricao(descricao);
                        dao.salvar(g);
                        disp = request.getRequestDispatcher("/formularios/genero/listagem.jsp");
                    }
                }else if( acao.equals("alterar") ){
                    
                    String descricao = request.getParameter("descricao");
                    
                    if( descricao.isEmpty() || descricao.length() > 40 ){
                        disp = request.getRequestDispatcher("/formularios/genero/erro.jsp");
                    }else{
                    
                        Genero g = new Genero();
                        int id = Integer.parseInt(request.getParameter("id"));
                        g.setId(id);
                        g.setDescricao(descricao);
                        dao.atualizar(g);
                        disp = request.getRequestDispatcher("/formularios/genero/listagem.jsp");
                    }
                }else if( acao.equals("excluir") ){
                    
                    Genero g = new Genero();
                    int id = Integer.parseInt(request.getParameter("id"));
                    dao.excluir(dao.obterPorId(id));
                    disp = request.getRequestDispatcher("/formularios/genero/listagem.jsp");
                
                }else {
                
                    int id = Integer.parseInt(request.getParameter( "id" ));
                    Genero g = dao.obterPorId( id );
                    request.setAttribute( "genero", g );

                    if ( acao.equals( "prepararAlteracao" ) ) {
                        disp = request.getRequestDispatcher( 
                                "/formularios/genero/alterar.jsp" );
                    } else if ( acao.equals( "prepararExclusao" ) ) {
                        disp = request.getRequestDispatcher( 
                                "/formularios/genero/excluir.jsp" );
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
        return "GeneroServlet";
    }
}


   