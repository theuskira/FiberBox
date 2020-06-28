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
                    + "som BOOL,"
                    + "atualizacao BOOL,"
                    + "conexaoAutomatica BOOL);");
            
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
        
        System.out.println("** CRIAR USUARIO **");
        
        boolean retorno = false;
        
        try {
            
            stmt = con.prepareStatement("INSERT INTO "
                    + "configuracao "
                    + "(usuario, som, atualizar, conexaoAutomatica) "
                    + "VALUES "
                    + "(?, ?, ?, ?);");
            
            stmt.setString(1, configuracao.getUsuario());
            stmt.setBoolean(2, configuracao.getSom());
            stmt.setBoolean(3, configuracao.getAtualizacao());
            stmt.setBoolean(4, configuracao.getConexaoAutomatica());
            
            stmt.executeUpdate();
            
            System.out.println("Configuracao de " + configuracao.getUsuario()+ " cadastrada!");
            
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
                u.setSom(rs.getBoolean("som"));
                u.setAtualizacao(rs.getBoolean("atualizacao"));
                u.setConexaoAutomatica(rs.getBoolean("conexaoAutomatica"));
                
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
                    + "usuario = ?, som = ?, atualizacao = ?, conexaoAutomatica = ? "
                    + "WHERE usuario = ?");
            
            stmt.setString(1, configuracao.getUsuario());
            stmt.setBoolean(2, configuracao.getSom());
            stmt.setBoolean(3, configuracao.getAtualizacao());
            stmt.setBoolean(4, configuracao.getConexaoAutomatica());
            stmt.setString(5, usuarioAnterior);
            
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
