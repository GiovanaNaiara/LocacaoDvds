package locacaodvds.servicos;

import locacaodvds.entidades.DVD;
import locacaodvds.dao.DvdDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class DvdServices {
    
    public List<DVD> getTodos(){
    
        List<DVD> lista = new ArrayList<>();
        DvdDAO dao = null;
        
        try{
            
            dao = new DvdDAO();
            lista = dao.listarTodos();
            
        }catch( SQLException exc ){
            exc.printStackTrace();
        }finally{
            
            if( dao != null ){
                
                try{
                    dao.fecharConexao();
                }catch( SQLException exc ){
                    System.out.println( "Erro ao fechar conexão!" );
                    exc.printStackTrace();
                }
                
            }
            
        }    
        
        return lista;
    }
    
}
