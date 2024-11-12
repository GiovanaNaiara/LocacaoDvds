package locacaodvds.controladores;

import locacaodvds.dao.DvdDAO;
import locacaodvds.entidades.DVD;
import locacaodvds.dao.AtorDAO;
import locacaodvds.dao.ClassificacaoDAO;
import locacaodvds.dao.GeneroDAO;
import java.util.List;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;

@WebServlet(name = "DVDServlet", urlPatterns = {"/processaDvds"})
public class DVDServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
            String acao = request.getParameter("acao");
            DvdDAO dao = null;
            RequestDispatcher disp = null;
            
            try{
            
                dao = new DvdDAO();

                if( acao.equals("inserir") ){
                    
                    String titulo = request.getParameter("titulo");
                    Integer anoLancamento = Integer.parseInt(request.getParameter("anoLancamento"));
                    Integer duracaoMinutos = Integer.parseInt(request.getParameter("duracao"));
                    
                    if( titulo.length() > 100 || titulo.isEmpty() ){
                        
                        disp = request.getRequestDispatcher("/formularios/dvd/erro.jsp");
                    }else{

                        DVD d = new DVD();

                        d.setTitulo(titulo);
                        d.setAnoLancamento(anoLancamento);

                        AtorDAO atorDao = new AtorDAO();
                        int apId = Integer.parseInt(request.getParameter("atorPrin-selecao"));
                        int acId = Integer.parseInt(request.getParameter("atorCdj-selecao"));

                        int ceId = Integer.parseInt(request.getParameter("classificacao-selecao"));
                        ClassificacaoDAO cDao = new ClassificacaoDAO();

                        int gId = Integer.parseInt(request.getParameter("genero-selecao"));
                        GeneroDAO gDao = new GeneroDAO();

                        d.setAtorPrincipal(atorDao.obterPorId(apId));
                        d.setAtorCoadjuvante(atorDao.obterPorId(acId));
                        d.setDataLancamento(Date.valueOf(request.getParameter("dataLancamento")));
                        d.setDuracaoMinutos(duracaoMinutos);
                        d.setClassificacao(cDao.obterPorId(ceId));
                        d.setGenero(gDao.obterPorId(gId));

                        dao.salvar(d);

                        disp = request.getRequestDispatcher("/formularios/dvd/listagem.jsp");
                    }
                }else if( acao.equals("alterar") ){
                    
                    String titulo = request.getParameter("titulo");
                    
                    if( titulo.length() > 100 || titulo.isEmpty() ){
                        
                        disp = request.getRequestDispatcher("/formularios/dvd/erro.jsp");
                    }else{

                        DVD d = new DVD();
                        int id = Integer.parseInt(request.getParameter("id"));
                        d.setId(id);
                        d.setTitulo(request.getParameter("titulo"));
                        d.setAnoLancamento(Integer.parseInt(request.getParameter("anoLancamento")));

                        AtorDAO atorDao = new AtorDAO();
                        int apId = Integer.parseInt(request.getParameter("atorPrin-selecao"));
                        int acId = Integer.parseInt(request.getParameter("atorCdj-selecao"));

                        int ceId = Integer.parseInt(request.getParameter("classificacao-selecao"));
                        ClassificacaoDAO cDao = new ClassificacaoDAO();

                        int gId = Integer.parseInt(request.getParameter("genero-selecao"));
                        GeneroDAO gDao = new GeneroDAO();

                        d.setAtorPrincipal(atorDao.obterPorId(apId));
                        d.setAtorCoadjuvante(atorDao.obterPorId(acId));
                        d.setDataLancamento(Date.valueOf(request.getParameter("dataLancamento")));
                        d.setDuracaoMinutos(Integer.parseInt(request.getParameter("duracao")));
                        d.setClassificacao(cDao.obterPorId(ceId));
                        d.setGenero(gDao.obterPorId(gId));

                        dao.atualizar(d);
                        disp = request.getRequestDispatcher("/formularios/dvd/listagem.jsp");
                    }
                }else if( acao.equals("excluir") ){
                    
                    
                    int id = Integer.parseInt(request.getParameter("id"));
                    dao.excluir(dao.obterPorId(id));
                    disp = request.getRequestDispatcher("/formularios/dvd/listagem.jsp");
                
                }else {
                
                    int id = Integer.parseInt(request.getParameter( "id" ));
                    DVD dvd = dao.obterPorId( id );
                    request.setAttribute( "dvd", dvd );

                    if ( acao.equals( "prepararAlteracao" ) ) {
                        disp = request.getRequestDispatcher( 
                                "/formularios/dvd/alterar.jsp" );
                    } else if ( acao.equals( "prepararExclusao" ) ) {
                        disp = request.getRequestDispatcher( 
                                "/formularios/dvd/excluir.jsp" );
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "DVDServlet";
    }// </editor-fold>

}
