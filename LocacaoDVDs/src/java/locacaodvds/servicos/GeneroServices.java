package locacaodvds.servicos;

import locacaodvds.dao.GeneroDAO;
import locacaodvds.entidades.Genero;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class GeneroServices {
    
    public List<Genero> getTodos(){
    
        List<Genero> lista = new ArrayList<>();
        GeneroDAO dao = null;
        
        try{
            
            dao = new GeneroDAO();
            lista = dao.listarTodos();
        
        }catch( SQLException exc ){
            exc.printStackTrace();
        }finally{
            
            if( dao != null ){
                
                try{
                    dao.fecharConexao();
                }catch( SQLException exc ){
                    System.out.println("Erro ao fechar conexão!");
                    exc.printStackTrace();
                }
            }
            
        }
        return lista;
    }
    
}
