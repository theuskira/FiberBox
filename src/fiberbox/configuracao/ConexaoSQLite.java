package fiberbox.configuracao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoSQLite {

    public static Connection getConnection(){
        
        try {
            
            return DriverManager.getConnection("jdbc:sqlite:firemap.db");
            
        } catch (SQLException e) {
            
            System.err.println("Erro SQLite: " + e.getMessage());
            return null;
            
        }
        
    }
    
    public static void closeConnection(Connection con){
        
        if(con != null){
            
            try {
                
                con.close();
                
            } catch (SQLException e) {
                
                System.err.println("Erro SQLite: " + e.getMessage());
                
            }
            
        }
        
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt){
        
        closeConnection(con);
            
        if(stmt != null){
            
            try {
                
                stmt.close();
                
            } catch (SQLException e) {
                
                System.err.println("Erro SQLite: " + e.getMessage());
                
            }
            
        }
        
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        
        closeConnection(con, stmt);
            
        if(rs != null){
            
            try {
                
                rs.close();
                
            } catch (SQLException e) {
                
                System.err.println("Erro SQLite: " + e.getMessage());
                
            }
            
        }
        
    }
    
}
