package fiberbox.configuracao;

import java.util.Map;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import fiberbox.model.Caixa;
import fiberbox.model.Usuario;
import fiberbox.model.UsuarioDAO;

public class VerificarCaixas {
    
    public Boolean verificarCaixa(
            Map<String, String> entry,
            Circle caixa,
            String usuario1,
            String usuario2,
            String usuario3,
            String usuario4,
            String usuario5,
            String usuario6,
            String usuario7,
            String usuario8,
            Boolean status) {

        if (
                entry.containsValue("<pppoe-" + usuario1 + ">") ||
                entry.containsValue("<pppoe-" + usuario2 + ">") ||
                entry.containsValue("<pppoe-" + usuario3 + ">") ||
                entry.containsValue("<pppoe-" + usuario4 + ">") ||
                entry.containsValue("<pppoe-" + usuario5 + ">") ||
                entry.containsValue("<pppoe-" + usuario6 + ">") ||
                entry.containsValue("<pppoe-" + usuario7 + ">") ||
                entry.containsValue("<pppoe-" + usuario8 + ">")
                ) {
            
            System.out.println("ON-LINE");
            caixa.setFill(Paint.valueOf("GREEN"));
            
            status = true;
            
        }else{
            System.out.println("OFF-LINE");
            caixa.setFill(Paint.valueOf("RED"));
            
            status = false;
        }
        
        return status;

    }
    
    
    // NOVO METODO !!!
    
    public Boolean verificarUsuario(
            Map<String, String> entry,
            Caixa caixa) {
        
        Boolean status = false;

        // USUARIO 1
        if(caixa.getUsuario1() != null){
            
            if (entry.containsValue("<pppoe-" + caixa.getUsuario1() + ">")) {

                //circle.setFill(Paint.valueOf("GREEN"));
                
                Usuario u = new UsuarioDAO().localizar(caixa.getUsuario1());
                u.setStatus(true);
                
                new UsuarioDAO().atualizar(
                        u,
                        caixa.getUsuario1());
                
                status = true;

            }else{

                //circle.setFill(Paint.valueOf("RED"));
                
                Usuario u = new UsuarioDAO().localizar(caixa.getUsuario1());
                u.setStatus(false);
                
                new UsuarioDAO().atualizar(
                        u,
                        caixa.getUsuario1());

            }
            
        }
        
        // USUARIO 2
        if(caixa.getUsuario2() != null){
            
            if (entry.containsValue("<pppoe-" + caixa.getUsuario2() + ">")) {

                //circle.setFill(Paint.valueOf("GREEN"));
                
                Usuario u = new UsuarioDAO().localizar(caixa.getUsuario2());
                u.setStatus(true);
                
                new UsuarioDAO().atualizar(
                        u,
                        caixa.getUsuario2());
                
                status = true;

            }else{

                //circle.setFill(Paint.valueOf("RED"));
                
                Usuario u = new UsuarioDAO().localizar(caixa.getUsuario2());
                u.setStatus(false);
                
                new UsuarioDAO().atualizar(
                        u,
                        caixa.getUsuario2());

            }
            
        }
        
        // USUARIO 3
        if(caixa.getUsuario3() != null && !caixa.getUsuario3().isEmpty()){
            
            if (entry.containsValue("<pppoe-" + caixa.getUsuario3() + ">")) {

                //circle.setFill(Paint.valueOf("GREEN"));
                
                Usuario u = new UsuarioDAO().localizar(caixa.getUsuario3());
                u.setStatus(true);
                
                new UsuarioDAO().atualizar(
                        u,
                        caixa.getUsuario3());
                
                status = true;

            }else{

                //circle.setFill(Paint.valueOf("RED"));
                
                Usuario u = new UsuarioDAO().localizar(caixa.getUsuario3());
                u.setStatus(false);
                
                new UsuarioDAO().atualizar(
                        u,
                        caixa.getUsuario3());

            }
            
        }

        // USUARIO 4
        if(caixa.getUsuario4() != null && !caixa.getUsuario4().isEmpty()){
            
            if (entry.containsValue("<pppoe-" + caixa.getUsuario4() + ">")) {

                //circle.setFill(Paint.valueOf("GREEN"));
                
                Usuario u = new UsuarioDAO().localizar(caixa.getUsuario4());
                u.setStatus(true);
                
                new UsuarioDAO().atualizar(
                        u,
                        caixa.getUsuario4());
                
                status = true;

            }else{

                //circle.setFill(Paint.valueOf("RED"));
                
                Usuario u = new UsuarioDAO().localizar(caixa.getUsuario4());
                u.setStatus(false);
                
                new UsuarioDAO().atualizar(
                        u,
                        caixa.getUsuario4());

            }
            
        }

        // USUARIO 5
        if(caixa.getUsuario5() != null && !caixa.getUsuario5().isEmpty()){
            
            if (entry.containsValue("<pppoe-" + caixa.getUsuario5() + ">")) {

                //circle.setFill(Paint.valueOf("GREEN"));
                
                Usuario u = new UsuarioDAO().localizar(caixa.getUsuario5());
                u.setStatus(true);
                
                new UsuarioDAO().atualizar(
                        u,
                        caixa.getUsuario5());
                
                status = true;

            }else{

                //circle.setFill(Paint.valueOf("RED"));
                
                Usuario u = new UsuarioDAO().localizar(caixa.getUsuario5());
                u.setStatus(false);
                
                new UsuarioDAO().atualizar(
                        u,
                        caixa.getUsuario5());

            }
            
        }

        // USUARIO 6
        if(caixa.getUsuario6() != null && !caixa.getUsuario6().isEmpty()){
            
            if (entry.containsValue("<pppoe-" + caixa.getUsuario6() + ">")) {

                //circle.setFill(Paint.valueOf("GREEN"));
                
                Usuario u = new UsuarioDAO().localizar(caixa.getUsuario6());
                u.setStatus(true);
                
                new UsuarioDAO().atualizar(
                        u,
                        caixa.getUsuario6());
                
                status = true;

            }else{

                //circle.setFill(Paint.valueOf("RED"));
                
                Usuario u = new UsuarioDAO().localizar(caixa.getUsuario6());
                u.setStatus(false);
                
                new UsuarioDAO().atualizar(
                        u,
                        caixa.getUsuario6());

            }
            
        }

        // USUARIO 7
        if(caixa.getUsuario7() != null && !caixa.getUsuario7().isEmpty()){
            
            if (entry.containsValue("<pppoe-" + caixa.getUsuario7() + ">")) {

                //circle.setFill(Paint.valueOf("GREEN"));
                
                Usuario u = new UsuarioDAO().localizar(caixa.getUsuario7());
                u.setStatus(true);
                
                new UsuarioDAO().atualizar(
                        u,
                        caixa.getUsuario7());
                
                status = true;

            }else{

                //circle.setFill(Paint.valueOf("RED"));
                
                Usuario u = new UsuarioDAO().localizar(caixa.getUsuario7());
                u.setStatus(false);
                
                new UsuarioDAO().atualizar(
                        u,
                        caixa.getUsuario7());

            }
            
        }

        // USUARIO 8
        if(caixa.getUsuario8() != null && !caixa.getUsuario8().isEmpty()){
            
            if (entry.containsValue("<pppoe-" + caixa.getUsuario8() + ">")) {

                //circle.setFill(Paint.valueOf("GREEN"));
                
                Usuario u = new UsuarioDAO().localizar(caixa.getUsuario8());
                u.setStatus(true);
                
                new UsuarioDAO().atualizar(
                        u,
                        caixa.getUsuario8());
                
                status = true;

            }else{

                //circle.setFill(Paint.valueOf("RED"));
                
                Usuario u = new UsuarioDAO().localizar(caixa.getUsuario8());
                u.setStatus(false);
                
                new UsuarioDAO().atualizar(
                        u,
                        caixa.getUsuario8());

            }
            
        }
        
        System.out.println("************** RETORNO: " + status);
        
        return status;
        
    }
    
}
