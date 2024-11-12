package locacaodvds.servicos;

import locacaodvds.dao.ClassificacaoDAO;
import locacaodvds.entidades.ClassificacaoEtaria;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class ClassificacaoEtariaServices {
    
    public List<ClassificacaoEtaria> getTodos(){
    
        List<ClassificacaoEtaria> lista = new ArrayList<>();
        ClassificacaoDAO dao = null;
        
        try{
        
            dao = new ClassificacaoDAO();
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
