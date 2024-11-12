
package locacaodvds.dao;

import locacaodvds.entidades.Genero;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GeneroDAO extends DAO<Genero>{

    public GeneroDAO() throws SQLException{
    }
    
    @Override
    public void salvar(Genero obj) throws SQLException {
        
        String sql = """
                     INSERT INTO genero
                     ( descricao )
                     VALUES ( ? );
                     """;
        
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setString(1, obj.getDescricao());
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void atualizar(Genero obj) throws SQLException {
        
        String sql = """
                     UPDATE genero
                     SET
                        descricao = ?
                     WHERE id = ?;
                     """;
        
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setString(1, obj.getDescricao());
        stmt.setInt(2, obj.getId());
        stmt.executeUpdate();
        stmt.close();
        
    }

    @Override
    public void excluir(Genero obj) throws SQLException {
        
        String sql = """
                     DELETE FROM genero
                     WHERE id = ?;
                     """;
        
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, obj.getId());
        stmt.executeUpdate();
        stmt.close();
        
        
    }

    @Override
    public List<Genero> listarTodos() throws SQLException {
        
        List<Genero> lista = new ArrayList<>();
        
        String sql = """
                     SELECT * FROM genero
                     ORDER BY descricao;
                     """;    
        
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        while( rs.next() ){
        
            Genero g = new Genero();
            g.setId(rs.getInt("id"));
            g.setDescricao(rs.getString("descricao"));
            
            lista.add(g);
        
        }
        
        rs.close();
        stmt.close();
        
        
        return lista;
        
    }

    @Override
    public Genero obterPorId(int id) throws SQLException {
        
        Genero g = null;
        
        String sql = """
                     SELECT * FROM genero
                     WHERE id = ?;
                     """;
        
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        
        if( rs.next() ){
            
            g = new Genero();
            g.setId(rs.getInt("id"));
            g.setDescricao(rs.getString("descricao"));
        }
        
        rs.close();
        stmt.close();
        
        return g;
        
    }
    
}
