package locacaodvds.dao;

import locacaodvds.entidades.ClassificacaoEtaria;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class ClassificacaoDAO extends DAO<ClassificacaoEtaria>{

    public ClassificacaoDAO() throws SQLException{
    }
    
    @Override
    public void salvar(ClassificacaoEtaria obj) throws SQLException {
        
        String sql = """
                     INSERT INTO classificacao_etaria
                     ( descricao )
                     VALUES ( ? );
                     """;
        
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setString(1, obj.getDescricao());
        stmt.executeUpdate();
        stmt.close();
        
    }

    @Override
    public void atualizar(ClassificacaoEtaria obj) throws SQLException {
        
        String sql = """
                     UPDATE classificacao_etaria
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
    public void excluir(ClassificacaoEtaria obj) throws SQLException {
        
        String sql = """
                     DELETE FROM classificacao_etaria
                     WHERE id = ?;
                     """;
        
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, obj.getId());
        stmt.executeUpdate();
        stmt.close();
        
    }

    @Override
    public List<ClassificacaoEtaria> listarTodos() throws SQLException {
    
        List<ClassificacaoEtaria> lista = new ArrayList<>();
        
        String sql = """
                     SELECT * FROM classificacao_etaria
                     ORDER BY id;
                     """;    
        
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        while( rs.next() ){
        
            ClassificacaoEtaria ce = new ClassificacaoEtaria();
            ce.setId(rs.getInt("id"));
            ce.setDescricao(rs.getString("descricao"));
            
            lista.add(ce);
        
        }
        
        rs.close();
        stmt.close();
        
        
        return lista;
    }

    @Override
    public ClassificacaoEtaria obterPorId(int id) throws SQLException {
        
        ClassificacaoEtaria ce = null;
        
        String sql = """
                     SELECT * FROM classificacao_etaria
                     WHERE id = ?;
                     """;
        
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        
        if( rs.next() ){
            
            ce = new ClassificacaoEtaria();
            ce.setId(rs.getInt("id"));
            ce.setDescricao(rs.getString("descricao"));
        }
        
        rs.close();
        stmt.close();
        
        return ce;
    }
    
}
