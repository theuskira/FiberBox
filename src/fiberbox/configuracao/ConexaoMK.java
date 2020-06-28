package fiberbox.configuracao;

import fiberbox.model.Estatico;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLSocketFactory;
import me.legrange.mikrotik.ApiConnection;
import me.legrange.mikrotik.ApiConnectionException;
import me.legrange.mikrotik.MikrotikApiException;

/**
 *
 * @author Matheus - DELL
 */
public class ConexaoMK {
    
    public List<Map<String, String>> lista(String ip, int porta, String usuario, String senha) throws ApiConnectionException{
        
        List<Map<String, String>> rs = null;
        
        // TODO - Teste de Conexao
        try {
            
            try (ApiConnection con = ApiConnection.connect(ip)    // connect to router
            //try (ApiConnection con = ApiConnection.connect(SSLSocketFactory.getDefault(), ip, porta, 5 * 1000)    // connect to router
            //ApiConnection con = ApiConnection.connect("10.10.11.1");
            ) {
                
                con.setTimeout(5 * 1000);
                
                con.login(usuario, senha);   // login to router
                
                if(con.isConnected()){
                    
                    System.out.println("** CONEXAO MIKROTIK: CONECTADO");
                    
                    //Estatico.setStatusSistema(true);
                    
                }else{
                    
                    System.err.println("** CONEXAO MIKROTIK: DESCONECTADO");
                    
                    //Estatico.setStatusSistema(false);
                    
                }   
                
                rs = con.execute("/interface/pppoe-server/print");
                //rs = con.execute("/ppp/active/print");
                //for (Map<String,String> r : rs) {
                //System.out.println(r);
                //}
                
                con.close();    //disconnect from router

                return rs;
                
            }
            
        } catch (MikrotikApiException e) {
            
            System.err.println("ERRO: " + e.getMessage());
            
            //Estatico.setStatusSistema(false);
            
        }
        
        return rs;
        
    }
    
    public Boolean conectar(String ip, int porta, String usuario, String senha){
        
        try (ApiConnection con = ApiConnection.connect(ip)) {
                
                con.setTimeout(5 * 1000);
                
                con.login(usuario, senha);   // login to router
                
                if(con.isConnected()){
                    
                    System.out.println("** CONEXAO MIKROTIK: CONECTADO");
                    
                    Estatico.setStatusSistema(true);
                    
                }else{
                    
                    System.err.println("** CONEXAO MIKROTIK: DESCONECTADO");
                    
                    Estatico.setStatusSistema(false);
                    
                }
                
                con.close();
        
            }catch(MikrotikApiException e){
                System.err.println(e.getMessage());
            }
        
        return Estatico.getStatusSistema();
        
    }
    
}
