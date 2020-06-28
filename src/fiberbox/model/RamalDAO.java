package fiberbox.model;

import fiberbox.configuracao.ConexaoSQLite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus - DELL
 */
public class RamalDAO {
    
    private Connection con = ConexaoSQLite.getConnection();
    private PreparedStatement stmt = null;
    
    public boolean droparTabela(){
        
        System.out.println("** DROPAR TABELA RAMAL **");
        
        boolean retorno = false;
        
        try {
            
            stmt = con.prepareStatement("DROP TABLE IF EXISTS ramal");
            
            stmt.execute();
            
            retorno = true;
            
            System.out.println("* TABELA CONFIGURACAO RAMAL!");
            
        } catch (SQLException e) {
            
            System.err.println("Erro ao dropar a tabela ramal: " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt);
            
        }
        
        return retorno;
        
    }
    
    public boolean verificarTabela(){
        
        System.out.println("** VERIFICAR TABELA RAMAL **");
        
        boolean retorno = false;
        
        try {
            
            stmt = con.prepareStatement("CREATE TABLE IF NOT EXISTS "
                    + "ramal("
                    + "usuario VARCHAR(50) PRIMARY KEY,"
                    + "nome VARCHAR(50),"
                    + "ip VARCHAR(19),"
                    + "senha VARCHAR(50),"
                    + "porta Integer);");
            
            stmt.execute();
            
            retorno = true;
            
            System.out.println("* TABELA RAMAL VERIFICADO!");
            
        } catch (SQLException e) {
            
            System.err.println("Erro ao verificar a tabela ramal: " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt);
            
        }
        
        return retorno;
        
    }
    
    public boolean inserir(Ramal ramal){
        
        System.out.println("** CRIAR RAMAL **");
        
        boolean retorno = false;
        
        try {
            
            stmt = con.prepareStatement("INSERT INTO "
                    + "ramal "
                    + "(usuario, nome, ip, senha, porta) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?);");
            
            stmt.setString(1, Estatico.getUsuario());
            stmt.setString(2, ramal.getNome());
            stmt.setString(3, ramal.getIp());
            stmt.setString(4, ramal.getSenha());
            stmt.setInt(5, ramal.getPorta());
            
            stmt.executeUpdate();
            
            System.out.println("Ramal " + ramal.getNome() + " cadastrado!");
            
            retorno = true;
            
        } catch (SQLException e) {
            
            System.err.println("Erro ao cadastrar o ramal " + Estatico.getUsuario() + ": " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt);
            
        }
        
        return retorno;
        
    }
    
    public Ramal localizar(String usuario){
        
        System.out.println("** LOCALIZAR RAMAL **");
        
        ResultSet rs = null;
        
        Ramal u = new Ramal();
        
        try {
            
            stmt = con.prepareStatement("SELECT * FROM ramal WHERE usuario = ?");
            stmt.setString(1, usuario);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                u.setNome(rs.getString("nome"));
                u.setIp(rs.getString("ip"));
                u.setSenha(rs.getString("senha"));
                u.setPorta(rs.getInt("porta"));
                
                System.out.println("* Ramal localizado: " + u.getNome());
                
            }

        } catch (SQLException e) {
            
            System.err.println("Erro ao localizar o ramal " + Estatico.getUsuario() + ": " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt, rs);
            
        }
        
        return u;
        
    }
    
    public boolean atualizar(Ramal ramal, String usuarioAnterior){
        
        System.out.println("** ATUALIZAR RAMAL **");
        
        boolean retorno = false;
        
        try {
            
            stmt = con.prepareStatement("UPDATE "
                    + "ramal "
                    + " SET "
                    + "usuario = ?, nome = ?, ip = ?, senha = ?, porta = ? "
                    + "WHERE usuario = ?");
            
            stmt.setString(1, Estatico.getUsuario());
            stmt.setString(2, ramal.getNome());
            stmt.setString(3, ramal.getIp());
            stmt.setString(4, ramal.getSenha());
            stmt.setInt(5, ramal.getPorta());
            stmt.setString(6, usuarioAnterior);
            
            stmt.executeUpdate();
            
            System.out.println("Ramal atualizado!");
            
            retorno = true;
            
        } catch (SQLException e) {
            
            System.err.println("Erro ao atualizar o ramal: " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt);
            
        }
        
        return retorno;
        
    }
    
    public List<Ramal> listar(){
        
        System.out.println("** LISTAR RAMAIS **");
        
        ResultSet rs = null;
        
        List<Ramal> listaRamal = new ArrayList<>();
        
        try {
            
            stmt = con.prepareStatement("SELECT * FROM ramal");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Ramal c = new Ramal(
                        rs.getString("nome"),
                        rs.getString("ip"),
                        rs.getString("senha"),
                        rs.getInt(("porta"))
                    );
                
                System.out.println("* Ramal encontrado: " + c.getNome());
                
                listaRamal.add(c);
                
            }
            
        } catch (SQLException e) {
            
            System.err.println("Erro ao Listar Ramais: " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt, rs);
            
        }
        
        System.out.println("* " + listaRamal.size() + " Ramais encontradas!");
        
        return listaRamal;
        
    }
    
}
