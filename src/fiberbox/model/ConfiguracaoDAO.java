package fiberbox.model;

import fiberbox.configuracao.ConexaoSQLite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Matheus - DELL
 */
public class ConfiguracaoDAO {
    
    private Connection con = ConexaoSQLite.getConnection();
    private PreparedStatement stmt = null;
    
    public boolean droparTabela(){
        
        System.out.println("** DROPAR TABELA CONFIGURACAO **");
        
        boolean retorno = false;
        
        try {
            
            stmt = con.prepareStatement("DROP TABLE IF EXISTS configuracao");
            
            stmt.execute();
            
            retorno = true;
            
            System.out.println("* TABELA CONFIGURACAO DROPADA!");
            
        } catch (SQLException e) {
            
            System.err.println("Erro ao dropar a tabela configuracao: " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt);
            
        }
        
        return retorno;
        
    }
    
    public boolean verificarTabela(){
        
        System.out.println("** VERIFICAR TABELA CONFIGURACAO **");
        
        boolean retorno = false;
        
        try {
            
            stmt = con.prepareStatement("CREATE TABLE IF NOT EXISTS "
                    + "configuracao("
                    + "usuario VARCHAR(50) PRIMARY KEY,"
                    + "som INTEGER,"
                    + "caixaOn VARCHAR(10),"
                    + "caixaOff VARCHAR(10),"
                    + "caixaTam DOUBLE,"
                    + "atualizacao INTEGER,"
                    + "conexaoAutomatica INTEGER);");
            
            stmt.execute();
            
            retorno = true;
            
            System.out.println("* TABELA CONFIGURACAO VERIFICADA!");
            
        } catch (SQLException e) {
            
            System.err.println("Erro ao verificar a tabela configuracao: " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt);
            
        }
        
        return retorno;
        
    }
    
    public boolean inserir(Configuracao configuracao){
        
        System.out.println("** NOVA CONFIGURAÇÃO **");
        
        boolean retorno = false;
        
        try {
            
            stmt = con.prepareStatement("INSERT INTO "
                    + "configuracao "
                    + "(usuario, som, caixaOn, caixaOff, caixaTam, atualizacao, conexaoAutomatica) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?, ?, ?);");
            
            stmt.setString(1, configuracao.getUsuario());
            stmt.setInt(2, configuracao.getSom());
            stmt.setString(3, configuracao.getCaixaOn());
            stmt.setString(4, configuracao.getCaixaOff());
            stmt.setDouble(5, configuracao.getCaixaTam());
            stmt.setInt(6, configuracao.getAtualizacao());
            stmt.setInt(7, configuracao.getConexaoAutomatica());
            
            stmt.executeUpdate();
            
            System.out.println("Configuracão de " + configuracao.getUsuario()+ " cadastrada!");
            
            retorno = true;
            
        } catch (SQLException e) {
            
            System.err.println("Erro ao cadastrar a configuracao de " + configuracao.getUsuario() + ": " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt);
            
        }
        
        return retorno;
        
    }
    
    public Configuracao localizar(String usuario){
        
        System.out.println("** LOCALIZAR CONFIGURACAO **");
        
        ResultSet rs = null;
        
        Configuracao u = new Configuracao();
        
        try {
            
            stmt = con.prepareStatement("SELECT * FROM configuracao WHERE usuario = ?");
            stmt.setString(1, usuario);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                u.setUsuario(rs.getString("usuario"));
                u.setSom(rs.getInt("som"));
                u.setCaixaOn(rs.getString("caixaOn"));
                u.setCaixaOff(rs.getString("caixaOff"));
                u.setCaixaTam(rs.getDouble("caixaTam"));
                u.setAtualizacao(rs.getInt("atualizacao"));
                u.setConexaoAutomatica(rs.getInt("conexaoAutomatica"));
                
                System.out.println("* Configuracao localizada: " + u.getUsuario());
                
            }

        } catch (SQLException e) {
            
            System.err.println("Erro ao Localizar a Configuracao de " + usuario + ": " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt, rs);
            
        }
        
        return u;
        
    }
    
    public boolean atualizar(Configuracao configuracao, String usuarioAnterior){
        
        System.out.println("** ATUALIZAR CONFIGURACAO **");
        
        boolean retorno = false;
        
        try {
            
            stmt = con.prepareStatement("UPDATE "
                    + "configuracao "
                    + " SET "
                    + "usuario = ?,"
                    + " som = ?,"
                    + " caixaOn = ?,"
                    + " caixaOff = ?,"
                    + " caixaTam = ?,"
                    + " atualizacao = ?,"
                    + " conexaoAutomatica = ? "
                    + "WHERE usuario = ?");
            
            stmt.setString(1, configuracao.getUsuario());
            stmt.setInt(2, configuracao.getSom());
            stmt.setString(3, configuracao.getCaixaOn());
            stmt.setString(4, configuracao.getCaixaOff());
            stmt.setDouble(5, configuracao.getCaixaTam());
            stmt.setInt(6, configuracao.getAtualizacao());
            stmt.setInt(7, configuracao.getConexaoAutomatica());
            stmt.setString(8, usuarioAnterior);
            
            stmt.executeUpdate();
            
            System.out.println(usuarioAnterior + " atualizada para " + configuracao.getUsuario()+ "!");
            
            retorno = true;
            
        } catch (SQLException e) {
            
            System.err.println("Erro ao atualizar " + usuarioAnterior + ": " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt);
            
        }
        
        return retorno;
        
    }
    
}
