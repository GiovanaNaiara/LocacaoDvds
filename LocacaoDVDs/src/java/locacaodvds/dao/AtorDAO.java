package locacaodvds.dao;

import java.sql.ResultSet;
import locacaodvds.entidades.Ator;
import java.sql.SQLException;
import java.util.List;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class AtorDAO extends DAO<Ator> {

    public AtorDAO() throws SQLException{
    }
    
    @Override
    public void salvar(Ator obj) throws SQLException {
        
        String sql = """
                     INSERT INTO ator ( nome, sobrenome, data_estreia )
                     VALUES ( ?, ?, ? );
                     """;
        
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getSobrenome());
        stmt.setDate(3, obj.getDataEstreia());
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void atualizar(Ator obj) throws SQLException {
        
        String sql = """
                     UPDATE ator 
                     SET
                        nome = ?,
                        sobrenome = ?,
                        data_estreia = ?
                     WHERE id = ?;
                     """;
        
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setString(1, obj.getNome());
        stmt.setString(2, obj.getSobrenome());
        stmt.setDate(3, obj.getDataEstreia());
        stmt.setInt(4, obj.getId());
        stmt.executeUpdate();
        stmt.close();
        
    }

    @Override
    public void excluir(Ator obj) throws SQLException {
        
        String sql = """
                     DELETE FROM ator 
                     WHERE id = ?;
                     """;
        
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, obj.getId());
        stmt.executeUpdate();
        stmt.close();
        
    }

    @Override
    public List<Ator> listarTodos() throws SQLException {
        
        List<Ator> lista = new ArrayList<>();
        
        String sql = """
                     SELECT * FROM ator
                     ORDER BY nome, data_estreia;""";
        
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        while( rs.next() ){
         
            Ator a = new Ator();
            a.setId(rs.getInt("id"));
            a.setNome(rs.getString("nome"));
            a.setSobrenome(rs.getString("sobrenome"));
            a.setDataEstreia(rs.getDate("data_estreia"));
            
            lista.add(a);
        }
        
        rs.close();
        stmt.close();
        
        return lista;
    }

    @Override
    public Ator obterPorId(int id) throws SQLException {
        
        Ator ator = null;
        
        String sql = """
                     SELECT * FROM ator 
                     WHERE id = ?;
                     """;
        
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        
        if( rs.next() ){
            
            ator = new Ator();
            ator.setId(rs.getInt("id"));
            ator.setNome(rs.getString("nome"));
            ator.setSobrenome(rs.getString("sobrenome"));
            ator.setDataEstreia(rs.getDate("data_estreia"));

        }
        
        rs.close();
        stmt.close();
        
        return ator;
    }


    
}
