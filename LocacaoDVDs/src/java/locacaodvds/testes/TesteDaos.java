package locacaodvds.testes;

import java.sql.SQLException;
import java.sql.Date;
import locacaodvds.dao.AtorDAO;
import locacaodvds.entidades.Ator;
import locacaodvds.dao.ClassificacaoDAO;
import locacaodvds.entidades.ClassificacaoEtaria;
import locacaodvds.dao.DvdDAO;
import locacaodvds.entidades.DVD;
import locacaodvds.dao.GeneroDAO;
import locacaodvds.entidades.Genero;

public class TesteDaos {
    
    public static void main( String [] args ) throws SQLException{
        
        
        //Salvar -  ator
        Ator ap = new Ator();
        ap.setNome("Alexis");
        ap.setSobrenome("Bladel");
        ap.setDataEstreia(Date.valueOf("2002-01-12"));
        Ator ac = new Ator();
        ac.setNome("Jared");
        ac.setSobrenome("Padalecki");
        ac.setDataEstreia(Date.valueOf("2003-09-31"));
            
        AtorDAO dao1 = null;
        AtorDAO dao5 = null;
        
        
        try{
        
            dao1 = new AtorDAO();
            dao1.salvar(ap);
            dao5 = new AtorDAO();
            dao5.salvar(ac);
            
        }catch( SQLException exc){
            exc.printStackTrace();
        }
        
        //Salvar - Classificacao
        ClassificacaoEtaria ce = new ClassificacaoEtaria();
        ce.setDescricao("Proibido para menores de 12 anos");
        ClassificacaoDAO dao2 = null;
        
        try{
            dao2 = new ClassificacaoDAO();
            dao2.salvar(ce);
        }catch( SQLException exc ){
            exc.printStackTrace();
        }
        
        //Salvar - Genero
        Genero g = new Genero();
        g.setDescricao("romance");
        GeneroDAO dao3 = null;
        
        try{
            dao3 = new GeneroDAO();
            dao3.salvar(g);
        }catch( SQLException exc ){
            exc.printStackTrace();
        }
        
        //Salvar - DVD
        
        DVD dvd = new DVD();
        dvd.setTitulo("Gilmore Girls");
        dvd.setAnoLancamento(2005);
        dvd.setAtorPrincipal(dao1.obterPorId(1));
        dvd.setAtorCoadjuvante(dao5.obterPorId(2));
        dvd.setDataLancamento(Date.valueOf("2005-03-14"));
        dvd.setDuracaoMinutos(160);
        dvd.setClassificacao(dao2.obterPorId(1));
        dvd.setGenero(dao3.obterPorId(1));
        
        DvdDAO dao4 = null;
        
        try{
            
            dao4 = new DvdDAO();
            dao4.salvar(dvd);
        }catch( SQLException exc ){
            exc.printStackTrace();
        }
        
        
        //atualizar - DVD
        DvdDAO dao9 = null;
        DVD dvd2 = new DVD();
        dvd2.setId(1);
        dvd2.setTitulo("Como eu era antes de você");
        dvd2.setAnoLancamento(2016);
        dvd2.setAtorCoadjuvante(dao5.obterPorId(2));
        dvd2.setAtorPrincipal(dao1.obterPorId(1));
        dvd2.setClassificacao(dao2.obterPorId(1));
        dvd2.setDataLancamento(Date.valueOf("2000-10-08"));
        dvd2.setDuracaoMinutos(180);
        dvd2.setGenero(dao3.obterPorId(1));
       
        try{
            dao9 = new DvdDAO();
            dao9.atualizar(dvd2);
        }catch( SQLException exc ){
            exc.printStackTrace();
        }
        
        //atualizar - ator 
        AtorDAO dao6 = null;
        Ator a2 = new Ator();
        a2.setId(dao1.obterPorId(1).getId());
        a2.setNome("Emilia");
        a2.setSobrenome("Clarke");
        a2.setDataEstreia(Date.valueOf("2000-10-09"));
        
        try{
            
            dao6 = new AtorDAO();
            dao6.atualizar(a2);
            
        }catch( SQLException exc ){
            exc.printStackTrace();
        }
        
        //atualizar - genero
        GeneroDAO dao7 = null;
        Genero g2 = new Genero();
        g2.setId(dao3.obterPorId(1).getId());
        g2.setDescricao("comédia romântica");
        
        try{
        
            dao7 = new GeneroDAO();
            dao7.atualizar(g2);
        }catch( SQLException exc ){
            exc.printStackTrace();
        }
        
        //atualizar Classificação Etária
       
        ClassificacaoDAO dao8 = null;
        ClassificacaoEtaria ce2 = new ClassificacaoEtaria();
        ce2.setId(dao2.obterPorId(1).getId());
        ce2.setDescricao("proibido para menores de 16 anos");
        
        try{
            
            dao8 = new ClassificacaoDAO();
            dao8.atualizar(ce2);
        }catch( SQLException exc ){
            exc.printStackTrace();
        }
        
        
        
        
        //excluir dvd
        DvdDAO dao13 = null;
        
        try{
            
            dao13 = new DvdDAO();
            dao13.excluir(dvd2);
            
        }catch( SQLException exc ){
            exc.printStackTrace();
        }
        
        //excluir - ator
        AtorDAO dao10 = null;
        
        try{
            
            dao10 = new AtorDAO();
            dao10.excluir(a2);
        }catch( SQLException exc ){
            exc.printStackTrace();
        }
        
        
        //excluir genero
        GeneroDAO dao11 = null;
        
        try{
            dao11 = new GeneroDAO();
            dao11.excluir(g2);
        }catch( SQLException exc ){
            exc.printStackTrace();
        }
        
        //excluir classificacao
        ClassificacaoDAO dao12 = null;
        
        try{
        
            dao12 = new ClassificacaoDAO();
            dao12.excluir( ce2 );
        }catch( SQLException exc ){
            exc.printStackTrace();
        }finally{
            
            if( dao12 != null){
                try{
                    dao12.fecharConexao();
                }catch( SQLException exc ){
                    System.out.println("Erro ao fechar conexão!");
                    exc.printStackTrace();
                }
            }
        }
      
    } 
}
