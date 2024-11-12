
package locacaodvds.dao;

import locacaodvds.entidades.DVD;
import locacaodvds.entidades.Ator;
import locacaodvds.entidades.ClassificacaoEtaria;
import locacaodvds.entidades.Genero;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DvdDAO extends DAO<DVD> {

    public DvdDAO() throws SQLException{
    }
    
    @Override
    public void salvar(DVD obj) throws SQLException {
    
        String sql = """
                     INSERT INTO dvd
                     ( titulo, ano_lancamento, ator_principal_id, 
                        ator_coadjuvante_id, data_lancamento,
                        duracao_minutos, classificacao_etaria_id,
                        genero_id)
                     VALUES ( ?, ?, ?, ? , ?, ?, ?, ? );
                     """;
       
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setString(1, obj.getTitulo());
        stmt.setInt(2, obj.getAnoLancamento());
        stmt.setInt(3, obj.getAtorPrincipal().getId());
        stmt.setInt(4, obj.getAtorCoadjuvante().getId());
        stmt.setDate(5, obj.getDataLancamento());
        stmt.setInt(6, obj.getDuracaoMinutos());
        stmt.setInt(7, obj.getClassificacao().getId());
        stmt.setInt(8, obj.getGenero().getId());
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void atualizar(DVD obj) throws SQLException {
        
       String sql = """
                     UPDATE dvd
                     SET
                        titulo = ?,
                        ano_lancamento = ?,
                        ator_principal_id = ?, 
                        ator_coadjuvante_id = ?,
                        data_lancamento = ?,
                        duracao_minutos = ?, 
                        classificacao_etaria_id = ?,
                        genero_id = ?
                    WHERE id = ?; 
                    """;
       
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setString(1, obj.getTitulo());
        stmt.setInt(2, obj.getAnoLancamento());
        stmt.setInt(3, obj.getAtorPrincipal().getId());
        stmt.setInt(4, obj.getAtorCoadjuvante().getId());
        stmt.setDate(5, obj.getDataLancamento());
        stmt.setInt(6, obj.getDuracaoMinutos());
        stmt.setInt(7, obj.getClassificacao().getId());
        stmt.setInt(8, obj.getGenero().getId());
        stmt.setInt(9, obj.getId());
        stmt.executeUpdate();
        stmt.close();
        
    }

    @Override
    public void excluir(DVD obj) throws SQLException {
        
        String sql = """
                     DELETE FROM dvd WHERE id = ?;
                     """;
        
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, obj.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public List<DVD> listarTodos() throws SQLException {
        
        List<DVD> lista = new ArrayList<>();
       
        String sql = """   
                     SELECT
                        dvd.id,
                        dvd.titulo AS titulo,
                        dvd.ano_lancamento AS anoLancamento,
                        ap.id,
                        ap.nome AS nomePrincipal,
                        ap.sobrenome AS sobrenomePrincipal,
                        ap.data_estreia AS dataPrincipal,
                        ac.id,
                        ac.nome AS nomeCoadjuvante,
                        ac.sobrenome AS sobrenomeCoadjuvante,
                        ac.data_estreia AS dataCoadjuvante,
                        dvd.data_lancamento AS dataLancamento,
                        dvd.duracao_minutos AS duracaoMinutos,
                        ce.id,
                        ce.descricao AS ceDescricao,
                        g.id,
                        g.descricao AS generoDescricao
                     FROM
                         dvd
                     INNER JOIN ator ap ON dvd.ator_principal_id = ap.id
                     INNER JOIN ator ac ON dvd.ator_coadjuvante_id = ac.id
                     INNER JOIN classificacao_etaria ce ON dvd.classificacao_etaria_id = ce.id
                     INNER JOIN genero g ON dvd.genero_id = g.id
                     ORDER BY titulo, ano_lancamento DESC, data_lancamento DESC;
                     """;
        
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        while( rs.next() ){
        
            DVD dvd = new DVD();
            Ator atorP = new Ator();
            Ator atorC = new Ator();
            Genero g = new Genero();
            ClassificacaoEtaria ce = new ClassificacaoEtaria();
            
            dvd.setId(rs.getInt("dvd.id"));
            dvd.setTitulo(rs.getString("titulo"));
            dvd.setAnoLancamento(rs.getInt("anoLancamento"));
            
            atorP.setId(rs.getInt("ap.id"));
            atorP.setNome(rs.getString("nomePrincipal"));
            atorP.setSobrenome(rs.getString("sobrenomePrincipal"));
            atorP.setDataEstreia(rs.getDate("dataPrincipal"));
            
            atorC.setId(rs.getInt("ac.id"));
            atorC.setNome(rs.getString("nomeCoadjuvante"));
            atorC.setSobrenome(rs.getString("sobrenomeCoadjuvante"));
            atorC.setDataEstreia(rs.getDate("dataCoadjuvante"));
            
            g.setId(rs.getInt("g.id"));
            g.setDescricao(rs.getString("generoDescricao"));
            
            ce.setId(rs.getInt("ce.id"));
            ce.setDescricao(rs.getString("ceDescricao"));
            
            dvd.setAtorPrincipal(atorP);
            dvd.setAtorCoadjuvante(atorC);
            dvd.setDataLancamento(rs.getDate("dataLancamento"));
            dvd.setDuracaoMinutos(rs.getInt("duracaoMinutos"));
            dvd.setClassificacao(ce);
            dvd.setGenero(g);
            
            lista.add(dvd);
            
        }
        
        rs.close();
        stmt.close();
        
        return lista;
    }

    @Override
    public DVD obterPorId(int id) throws SQLException {
        
        DVD dvd = null;
        
        String sql = """   
                     SELECT
                         dvd.id,
                         dvd.titulo AS titulo,
                         dvd.ano_lancamento AS anoLancamento,
                         ap.id,
                         ap.nome AS nomePrincipal,
                         ap.sobrenome AS sobrenomePrincipal,
                         ap.data_estreia AS dataPrincipal,
                         ac.id,
                         ac.nome AS nomeCoadjuvante,
                         ac.sobrenome AS sobrenomeCoadjuvante,
                         ac.data_estreia AS dataCoadjuvante,
                         dvd.data_lancamento AS dataLancamento,
                         dvd.duracao_minutos AS duracaoMinutos,
                         ce.id,
                         ce.descricao AS ceDescricao,
                         g.id,
                         g.descricao AS generoDescricao
                     FROM
                         dvd
                     INNER JOIN ator ap ON dvd.ator_principal_id = ap.id
                     INNER JOIN ator ac ON dvd.ator_coadjuvante_id = ac.id
                     INNER JOIN classificacao_etaria ce ON dvd.classificacao_etaria_id = ce.id
                     INNER JOIN genero g ON dvd.genero_id = g.id
                     WHERE
                         dvd.id = ?
                     ORDER BY titulo, ano_lancamento DESC, data_lancamento DESC;
                     """;
        
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        
        if( rs.next() ){
            
            dvd = new DVD();
            
            Ator atorP = new Ator();
            Ator atorC = new Ator();
            Genero g = new Genero();
            ClassificacaoEtaria ce = new ClassificacaoEtaria();
            
            dvd.setId(rs.getInt("dvd.id"));
            dvd.setTitulo(rs.getString("titulo"));
            dvd.setAnoLancamento(rs.getInt("anoLancamento"));
         
            atorP.setId(rs.getInt("ap.id"));
            atorP.setNome(rs.getString("nomePrincipal"));
            atorP.setSobrenome(rs.getString("sobrenomePrincipal"));
            atorP.setDataEstreia(rs.getDate("dataPrincipal"));
            
            atorC.setId(rs.getInt("ac.id"));
            atorC.setNome(rs.getString("nomeCoadjuvante"));
            atorC.setSobrenome(rs.getString("sobrenomeCoadjuvante"));
            atorC.setDataEstreia(rs.getDate("dataCoadjuvante"));
         
            dvd.setAtorPrincipal(atorP);
            dvd.setAtorCoadjuvante(atorC);
            
            dvd.setDataLancamento(rs.getDate("dataLancamento"));
            dvd.setDuracaoMinutos(rs.getInt("duracaoMinutos"));
            
            ce.setId(rs.getInt("ce.id"));
            ce.setDescricao(rs.getString("ceDescricao"));
            
            g.setId(rs.getInt("g.id"));
            g.setDescricao(rs.getString("generoDescricao"));
            
            dvd.setClassificacao(ce);
            dvd.setGenero(g);
            
        }
        
        rs.close();
        stmt.close();
        
        return dvd;
    }
    
    
    
}
