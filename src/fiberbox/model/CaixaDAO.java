package fiberbox.model;

import fiberbox.configuracao.ConexaoSQLite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CaixaDAO {
    
    private Connection con = ConexaoSQLite.getConnection();
    private PreparedStatement stmt = null;
    
    public boolean droparTabela(){
        
        System.out.println("** DROPAR TABELA CAIXA **");
        
        boolean retorno = false;
        
        try {
            
            stmt = con.prepareStatement("DROP TABLE IF EXISTS caixa");
            
            stmt.execute();
            
            retorno = true;
            
            System.out.println("* TABELA CAIXA DROPADA!");
            
        } catch (SQLException e) {
            
            System.err.println("Erro ao dropar a tabela caixa: " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt);
            
        }
        
        return retorno;
        
    }    
    
    public boolean verificarTabela(){
        
        System.out.println("** VERIFICAR TABELA **");
        
        boolean retorno = false;
        
        try {
            
            stmt = con.prepareStatement("CREATE TABLE IF NOT EXISTS "
                    + "caixa("
                    + "codigo VARCHAR(8) PRIMARY KEY,"
                    + "endereco VARCHAR(150),"
                    + "usuario1 VARCHAR(50),"
                    + "usuario2 VARCHAR(50),"
                    + "usuario3 VARCHAR(50),"
                    + "usuario4 VARCHAR(50),"
                    + "usuario5 VARCHAR(50),"
                    + "usuario6 VARCHAR(50),"
                    + "usuario7 VARCHAR(50),"
                    + "usuario8 VARCHAR(50),"
                    + "ramal VARCHAR,"
                    + "x DOUBLE,"
                    + "y DOUBLE"
                    + ")");
            
            stmt.execute();
            
            retorno = true;
            
        } catch (SQLException e) {
            
            System.err.println("Erro ao verificar a tabela: " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt);
            
        }
        
        return retorno;
        
    }  
    
    public boolean alterarTabela(){
        
        System.out.println("** ALTERAR TABELA **");
        
        boolean retorno = false;
        
        try {
            
            stmt = con.prepareStatement("UPDATE caixa SET ramal = ?;");
            stmt.setString(1, "Cândido Mendes - MA");
            
            stmt.execute();
            
            retorno = true;
            
        } catch (SQLException e) {
            
            System.err.println("Erro ao alterar a tabela: " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt);
            
        }
        
        return retorno;
        
    }  
    
    public boolean inserir(Caixa caixa){
        
        System.out.println("** CRIAR CAIXA **");
        
        boolean retorno = false;
        
        try {
            
            stmt = con.prepareStatement("INSERT INTO"
                    + " caixa "
                    + "(codigo, endereco, usuario1, usuario2, usuario3,"
                    + " usuario4, usuario5, usuario6, usuario7, usuario8, ramal, x, y) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            
            stmt.setString(1, caixa.getCodigo());
            stmt.setString(2, caixa.getEndereco());
            stmt.setString(3, caixa.getUsuario1());
            stmt.setString(4, caixa.getUsuario2());
            stmt.setString(5, caixa.getUsuario3());
            stmt.setString(6, caixa.getUsuario4());
            stmt.setString(7, caixa.getUsuario5());
            stmt.setString(8, caixa.getUsuario6());
            stmt.setString(9, caixa.getUsuario7());
            stmt.setString(10, caixa.getUsuario8());
            stmt.setString(11, caixa.getRamal());
            stmt.setDouble(12, caixa.getX());
            stmt.setDouble(13, caixa.getY());
            
            stmt.executeUpdate();
            
            System.out.println("Caixa " + caixa.getCodigo() + " cadastrada!");
            
            retorno = true;
            
        } catch (SQLException e) {
            
            System.err.println("Erro ao inserir a caixa " + caixa.getCodigo() + ": " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt);
            
        }
        
        return retorno;
        
    }
    
    public boolean atualizar(Caixa caixa, String codigoAnterior){
        
        System.out.println("** ATUALIZAR CAIXA **");
        
        boolean retorno = false;
        
        try {
            
            stmt = con.prepareStatement("UPDATE "
                    + "caixa "
                    + " SET "
                    + "codigo = ?, endereco = ?, usuario1 = ?, usuario2 = ?, "
                    + "usuario3 = ?, usuario4 = ?, usuario5 = ?, usuario6 = ?,"
                    + "usuario7 = ?, usuario8 = ?, ramal = ?, x = ?, y = ? "
                    + "WHERE codigo = ?");
            
             stmt.setString(1, caixa.getCodigo());
            stmt.setString(2, caixa.getEndereco());
            stmt.setString(3, caixa.getUsuario1());
            stmt.setString(4, caixa.getUsuario2());
            stmt.setString(5, caixa.getUsuario3());
            stmt.setString(6, caixa.getUsuario4());
            stmt.setString(7, caixa.getUsuario5());
            stmt.setString(8, caixa.getUsuario6());
            stmt.setString(9, caixa.getUsuario7());
            stmt.setString(10, caixa.getUsuario8());
            stmt.setString(11, caixa.getRamal());
            stmt.setDouble(12, caixa.getX());
            stmt.setDouble(13, caixa.getY());
            stmt.setString(14, codigoAnterior);
            
            stmt.executeUpdate();
            
            System.out.println(codigoAnterior + " atualizada para " +caixa.getCodigo() + "!");
            
            retorno = true;
            
        } catch (SQLException e) {
            
            System.err.println("Erro ao atualizar " + codigoAnterior + ": " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt);
            
        }
        
        return retorno;
        
    }
    
    public List<Caixa> listar(){
        
        System.out.println("** LISTAR CAIXAS **");
        
        ResultSet rs = null;
        
        List<Caixa> listaCaixas = new ArrayList<>();
        
        try {
            
            stmt = con.prepareStatement("SELECT * FROM caixa");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Caixa c = new Caixa(
                        rs.getString("codigo"),
                        rs.getString("endereco"),
                        rs.getString("usuario1"),
                        rs.getString("usuario2"),
                        rs.getString("usuario3"),
                        rs.getString("usuario4"),
                        rs.getString("usuario5"),
                        rs.getString("usuario6"),
                        rs.getString("usuario7"),
                        rs.getString("usuario8"),
                        rs.getString("ramal"),
                        rs.getDouble(("x")),
                        rs.getDouble(("y"))
                    );
                
                //System.out.println("* Caixa encontrada: " + c.getCodigo());
                
                listaCaixas.add(c);
                
            }
            
        } catch (SQLException e) {
            
            System.err.println("Erro ao Listar Caixas: " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt, rs);
            
        }
        
        //System.out.println("* " + listaCaixas.size() + " Caixas encontradas!");
        
        return listaCaixas;
        
    }
    
    public Caixa localizar(String codigo){
        
        System.out.println("** LOCALIZAR CAIXA **");
        
        ResultSet rs = null;
        
        Caixa c = new Caixa();
        
        try {
            
            stmt = con.prepareStatement("SELECT * FROM caixa WHERE codigo = ?");
            stmt.setString(1, codigo);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                c.setCodigo(rs.getString("codigo"));
                c.setEndereco(rs.getString("endereco"));
                c.setUsuario1(rs.getString("usuario1"));
                c.setUsuario2(rs.getString("usuario2"));
                c.setUsuario3(rs.getString("usuario3"));
                c.setUsuario4(rs.getString("usuario4"));
                c.setUsuario5(rs.getString("usuario5"));
                c.setUsuario6(rs.getString("usuario6"));
                c.setUsuario7(rs.getString("usuario7"));
                c.setUsuario8(rs.getString("usuario8"));
                c.setUsuario8(rs.getString("ramal"));
                c.setX(rs.getDouble("x"));
                c.setY(rs.getDouble("y"));
                
                System.out.println("* Caixa localizada: " + c.getCodigo());
                
            }

        } catch (SQLException e) {
            
            System.err.println("Erro ao Localizar a Caixa: " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt, rs);
            
        }
        
        return c;
        
    }
    
    public Caixa localizarPosicao(Double x, Double y){
        
        System.out.println("** LOCALIZAR CAIXA **");
        
        ResultSet rs = null;
        
        Caixa c = new Caixa();
        
        try {
            
            stmt = con.prepareStatement("SELECT * FROM caixa WHERE x = ? and y = ?");
            stmt.setDouble(1, x);
            stmt.setDouble(2, y);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                c.setCodigo(rs.getString("codigo"));
                c.setEndereco(rs.getString("endereco"));
                c.setUsuario1(rs.getString("usuario1"));
                c.setUsuario2(rs.getString("usuario2"));
                c.setUsuario3(rs.getString("usuario3"));
                c.setUsuario4(rs.getString("usuario4"));
                c.setUsuario5(rs.getString("usuario5"));
                c.setUsuario6(rs.getString("usuario6"));
                c.setUsuario7(rs.getString("usuario7"));
                c.setUsuario8(rs.getString("usuario8"));
                c.setUsuario8(rs.getString("ramal"));
                c.setX(rs.getDouble("x"));
                c.setY(rs.getDouble("y"));
                
                System.out.println("* Caixa localizada: " + c.getCodigo());
                
            }

        } catch (SQLException e) {
            
            System.err.println("Erro ao Localizar a Caixa: " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt, rs);
            
        }
        
        return c;
        
    }
    
    public List<Caixa> pesquisar(String search){
        
       ResultSet rs = null;
        
       List<Caixa> listaCaixa = new ArrayList<>();
        
        try {
            
            stmt = con.prepareStatement("SELECT * FROM caixa WHERE codigo LIKE '%"
                    + search 
                    +"%' OR endereco LIKE '%"
                    + search 
                    +"%' OR usuario1 LIKE '%"
                    + search 
                    +"%' OR usuario2 LIKE '%"
                    + search 
                    +"%' OR usuario3 LIKE '%"
                    + search 
                    +"%' OR usuario4 LIKE '%"
                    + search 
                    +"%' OR usuario5 LIKE '%"
                    + search 
                    +"%' OR usuario6 LIKE '%"
                    + search 
                    +"%' OR usuario7 LIKE '%"
                    + search 
                    +"%' OR usuario8 LIKE '%"
                    + search 
                    +"%' OR ramal LIKE '%"
                    + search 
                    +"%' OR x LIKE '%"
                    + search 
                    +"%' OR y LIKE '%"
                    + search 
                    +"%' ORDER BY codigo");
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Caixa cliente = new Caixa(
                        rs.getString("codigo"),
                        rs.getString("endereco"),
                        rs.getString("usuario1"),
                        rs.getString("usuario2"),
                        rs.getString("usuario3"),
                        rs.getString("usuario4"),
                        rs.getString("usuario5"),
                        rs.getString("usuario6"),
                        rs.getString("usuario7"),
                        rs.getString("usuario8"),
                        rs.getString("ramal"),
                        rs.getDouble(("x")),
                        rs.getDouble(("y"))
                );
                
                System.out.println("* Caixa encontrada: " + cliente.getCodigo());
                
                listaCaixa.add(cliente);
                
            }
            
        } catch (SQLException ex) {
            
            System.err.println("Erro ao pesquisar " + search.toUpperCase() + "! \n" + ex.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt, rs);
            
        }
        
        System.out.println(listaCaixa.size() + " caixa(s) encontrada(s)!");
        
        return listaCaixa;
        
    }
    
    public Boolean deletar(Caixa c){
        
        System.out.println("** DELETAR CAIXA **");
        
        ResultSet rs = null;
        
        Boolean resultado = false;
        
        try {
            
            stmt = con.prepareStatement("DELETE FROM caixa WHERE codigo = ?");
            stmt.setString(1, c.getCodigo());
            stmt.execute();
            
            resultado = true;

        } catch (SQLException e) {
            
            System.err.println("Erro ao Localizar a Caixa: " + e.getMessage());
            
        }finally{
            
            ConexaoSQLite.closeConnection(con, stmt, rs);
            
        }
        
        return resultado;
        
    }
    
}
