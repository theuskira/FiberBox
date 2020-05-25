package fiberbox.model;

import fiberbox.configuracao.ConexaoSQLite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsuarioDAO {
    
    private Connection con = ConexaoSQLite.getConnection();
    private PreparedStatement stmt = null;
    
    public boolean droparTabela(){
        
        System.out.println("** DROPAR TABELA USUARIO **");
        
        boolean retorno = false;
        
        try {
            
            stmt = con.prepareStatement("DROP TABLE IF EXISTS usuario");
            
            stmt.execute();
            
            retorno = true;
            
            System.out.println("* TABELA USUARIO DROPADA!");
            
        } catch (SQLException e) {
            
            System.err.println("Erro ao dropar a tabela usuario: " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt);
            
        }
        
        return retorno;
        
    }
    
    public boolean droparTudo(){
        
        System.out.println("** DROPAR TUDO **");
        
        boolean retorno = false;
        
        try {
            
            stmt = con.prepareStatement("DROP DATABASE firemap;");
            
            stmt.execute();
            
            retorno = true;
            
            System.out.println("* DATABASE DROPADA!");
            
        } catch (SQLException e) {
            
            System.err.println("Erro ao dropar a database: " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt);
            
        }
        
        return retorno;
        
    }
    
    public boolean verificarDados(){
        
        System.out.println("** VERIFICAR DADOS **");
        
        boolean retorno = false;
        
        try {
            
            stmt = con.prepareStatement("CREATE TABLE IF NOT EXISTS "
                    + "usuario("
                    + "usuario VARCHAR(50) PRIMARY KEY,"
                    + "caixa VARCHAR(8),"
                    + "status BOOL,"
                    + "FOREIGN KEY (caixa) REFERENCES caixa(codigo)"
                    + ")");
            
            stmt.execute();
            
            retorno = true;
            
        } catch (SQLException e) {
            
            System.err.println("Erro ao verificar os dados: " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt);
            
        }
        
        return retorno;
        
    }
    
    public boolean inserir(Usuario usuario){
        
        System.out.println("** CRIAR USUARIO **");
        
        boolean retorno = false;
        
        try {
            
            stmt = con.prepareStatement("INSERT INTO"
                    + " usuario "
                    + "(usuario, caixa, status) "
                    + "VALUES "
                    + "(?, ?, ?);");
            
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getCaixa());
            stmt.setBoolean(3, usuario.getStatus());
            
            stmt.executeUpdate();
            
            System.out.println("Usuario " + usuario.getUsuario()+ " cadastrado!");
            
            retorno = true;
            
        } catch (SQLException e) {
            
            System.err.println("Erro ao cadastrar " + usuario.getUsuario() + ": " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt);
            
        }
        
        return retorno;
        
    }
    
    public Usuario localizar(String usuario){
        
        System.out.println("** LOCALIZAR CAIXA **");
        
        ResultSet rs = null;
        
        Usuario u = new Usuario();
        
        try {
            
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE usuario = ?");
            stmt.setString(1, usuario);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                u.setUsuario(rs.getString("usuario"));
                u.setCaixa(rs.getString("caixa"));
                u.setStatus(rs.getBoolean("status"));
                
                System.out.println("* Usuario localizado: " + u.getUsuario());
                
            }

        } catch (SQLException e) {
            
            System.err.println("Erro ao Localizar o Usuario: " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt, rs);
            
        }
        
        return u;
        
    }
    
    public boolean atualizar(Usuario usuario, String usuarioAnterior){
        
        System.out.println("** ATUALIZAR USUARIO **");
        
        boolean retorno = false;
        
        try {
            
            stmt = con.prepareStatement("UPDATE "
                    + "usuario "
                    + " SET "
                    + "usuario = ?, caixa = ?, status = ? "
                    + "WHERE usuario = ?");
            
             stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getCaixa());
            stmt.setBoolean(3, usuario.getStatus());
            stmt.setString(4, usuarioAnterior);
            
            stmt.executeUpdate();
            
            System.out.println(usuarioAnterior + " atualizada para " + usuario.getUsuario()+ "!");
            
            retorno = true;
            
        } catch (SQLException e) {
            
            System.err.println("Erro ao atualizar " + usuarioAnterior + ": " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt);
            
        }
        
        return retorno;
        
    }
    
}
