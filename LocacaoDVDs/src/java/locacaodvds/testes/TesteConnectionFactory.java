package locacaodvds.testes;

import java.sql.Connection;
import java.sql.SQLException;
import locacaodvds.jdbc.ConnectionFactory;

public class TesteConnectionFactory {
  
    public static void main ( String [] args ){
        
        try{
            
            Connection conexao = ConnectionFactory.getConnection();
            System.out.println( "Conexão criada com sucesso!" );
        
        }catch( SQLException exc ){
            System.err.print( "Erro ao criar conexão!" );
            exc.printStackTrace();
        }
    }
}
