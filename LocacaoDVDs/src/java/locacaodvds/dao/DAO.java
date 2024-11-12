package locacaodvds.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import locacaodvds.jdbc.ConnectionFactory;

public abstract class DAO<Tipo>{
    
    //Conexão DAO
    private Connection conexao;
    
    //construtor para criação de conexão
    public DAO() throws SQLException {
    
        conexao = ConnectionFactory.getConnection();
    }
    
    public Connection getConnection(){
        return conexao;
    }
    
    public void fecharConexao() throws SQLException {
        conexao.close();
    }
    
    //C do CRUD (Create)
    public abstract void salvar ( Tipo obj ) throws SQLException;
    
    //U do CRUD (Update)
    public abstract void atualizar ( Tipo obj ) throws SQLException;
    
    //D do CRUD (Delete)
    public abstract void excluir ( Tipo obj ) throws SQLException;
    
    //R do CRUD (Read)
    //Lista todas as instâncias existentes
    public abstract List<Tipo> listarTodos() throws SQLException;
    
    //R do CRUD (Read)
    //Traz uma instância específica
    public abstract Tipo obterPorId( int id ) throws SQLException;
  
}
