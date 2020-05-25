package fiberbox.configuracao;

import java.util.List;
import java.util.Map;
import me.legrange.mikrotik.ApiConnection;
import me.legrange.mikrotik.MikrotikApiException;

/**
 *
 * @author Matheus - DELL
 */
public class ConexaoMK {
    
    public List<Map<String, String>> lista(String ip, String usuario, String senha){
        
        List<Map<String, String>> rs = null;
        
        // TODO - Teste de Conexao
        try {
            
            ApiConnection con = ApiConnection.connect(ip); // connect to router
            //ApiConnection con = ApiConnection.connect("10.10.11.1");
           
            con.setTimeout(5000);
            
            con.login(usuario,senha); // log in to router
            
            if(con.isConnected()){
                
                System.out.println("** CONEXAO MIKROTIK: CONECTADO");
                
            }else{
                
                System.err.println("** CONEXAO MIKROTIK: DESCONECTADO");
                
            }
            
            rs = con.execute("/interface/pppoe-server/print");
            //rs = con.execute("/ppp/active/print");
            for (Map<String,String> r : rs) {
              //System.out.println(r);
            }
         
            con.close(); // disconnect from router
            
        } catch (MikrotikApiException e) {
            
            System.err.println("ERRO: " + e.getMessage());
            
        }
        
        return rs;
        
    }
    
}
