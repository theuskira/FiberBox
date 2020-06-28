package fiberbox;

import fiberbox.configuracao.ConexaoMK;
import fiberbox.model.Configuracao;
import fiberbox.model.ConfiguracaoDAO;
import fiberbox.model.Estatico;
import fiberbox.model.Ramal;
import fiberbox.model.RamalDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import me.legrange.mikrotik.MikrotikApiException;

/**
 * FXML Controller class
 *
 * @author Matheus - DELL
 */

public class FXMLLoginController implements Initializable {
    
    @FXML
    private TextField txtServidor;
    
    @FXML
    private TextField txtPorta;
    
    @FXML
    private TextField txtUsuario;
    
    @FXML
    private PasswordField txtSenha;
    
    @FXML
    private Button btnEntrar;
    
    @FXML
    private CheckBox cbConexaoAutomatica;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //new ConfiguracaoDAO().droparTabela();
        new ConfiguracaoDAO().verificarTabela();
        
        btnEntrar.setOnAction((event) -> {
            try {
                verificar();
            } catch (MikrotikApiException ex) {
                Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }

    private void verificar() throws MikrotikApiException{
        
        int erro = 0 ;
        String erro1 = "";
        
        if(txtServidor.getText().isEmpty() || txtServidor.getText().length() < 8){
            
            erro ++;
            erro1 += "Servidor Inválido!\n";
            txtServidor.requestFocus();
            
        }
        
        if(txtPorta.getText().isEmpty()){
            
            erro ++;
            erro1 += "Porta Inválida!\n";
            txtPorta.requestFocus();
            
        }
        
        if(txtUsuario.getText().isEmpty()){
            
            erro ++;
            erro1 += "Usuário Inválido!\n";
            txtUsuario.requestFocus();
            
        }
        
        if(txtSenha.getText().isEmpty()){
            
            erro ++;
            erro1 += "Senha Inválida!\n";
            txtSenha.requestFocus();
            
        }
        
        if(erro > 0){
            
            System.err.println(erro + " erro(s) encontrado(s)!");
            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("Informação");
            dialogoInfo.setHeaderText(erro + " erro(s) encontrado(s)!");
            dialogoInfo.setContentText(erro1);
            dialogoInfo.initOwner(btnEntrar.getScene().getWindow());
            dialogoInfo.showAndWait();
            
        }else{
            
            Configuracao c = new ConfiguracaoDAO().localizar(txtUsuario.getText());
            
            if(c.getUsuario() != null){
                
                Estatico.setConfiguracao(c);
                
                System.out.println("* CONFIGURAÇÃO CARREGADA!\n");
                
                System.out.println("* USUARIO: " + Estatico.getConfiguracao().getUsuario());
                System.out.println("* SOM: " + Estatico.getConfiguracao().getSom());
                System.out.println("* ATUALIZACAO AUTOMATICA: " + Estatico.getConfiguracao().getAtualizacao());
                System.out.println("* CONEXAOCAO AUTOMATICA: " + Estatico.getConfiguracao().getConexaoAutomatica());
                System.out.println("* COR CAIXA ON: " + Estatico.getConfiguracao().getCaixaOn());
                System.out.println("* COR CAIXA OFF: " + Estatico.getConfiguracao().getCaixaOff());
                System.out.println("* TAMANHO/RAIO CAIXA: " + Estatico.getConfiguracao().getCaixaTam());
                
            }else{
                
                Configuracao newC = new Configuracao();
                
                newC.setUsuario(txtUsuario.getText());
                newC.setSom(1);
                newC.setAtualizacao(1);
                newC.setCaixaOff("RED");
                newC.setCaixaOn("DODGERBLUE");
                newC.setCaixaTam(15.0);
                
                if(cbConexaoAutomatica.isArmed()){
                    
                    newC.setConexaoAutomatica(1);
                    System.out.println("* Conexao automatica: ON");
                    
                }else{
                    
                    newC.setConexaoAutomatica(0);
                    System.out.println("* Conexao automatica: OFF");
                    
                }
                
                new ConfiguracaoDAO().inserir(newC);
                
                Estatico.setConfiguracao(newC);
                
            }
            
            Estatico.setUsuario(txtUsuario.getText());
            Estatico.setSenha(txtSenha.getText());
            Estatico.setIp(txtServidor.getText());
            Estatico.setPorta(Integer.parseInt(txtPorta.getText()));
            
            conectar();
            
        }
        
    }
    
    /*
    private void conectar() {

        System.out.println("** INICIANDO CONEXAO");

        try {

            //new ConexaoMK().lista(txtServidor.getText(), Integer.parseInt(txtPorta.getText()), txtUsuario.getText(), txtSenha.getText());
            new ConexaoMK().lista(txtServidor.getText(), Integer.parseInt(txtPorta.getText()), txtUsuario.getText(), txtSenha.getText());

        } catch (ApiConnectionException e) {

            System.err.println("Erro de conexão: " + e.getMessage());

            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("Informação");
            dialogoInfo.setHeaderText("Erro de conexão!");
            dialogoInfo.setContentText(e.getMessage());
            dialogoInfo.initOwner(btnEntrar.getScene().getWindow());
            dialogoInfo.showAndWait();

        } finally {

            if (Estatico.getStatusSistema() != null && Estatico.getStatusSistema()) {

                Estatico.setIp(txtServidor.getText());
                Estatico.setPorta(Integer.parseInt(txtPorta.getText()));
                Estatico.setUsuario(txtUsuario.getText());
                Estatico.setSenha(txtSenha.getText());
                
                iniciarPrincipal();

            } else {

                System.err.println("Erro: Sistema Off-Line ou Nullo");

                Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
                dialogoInfo.setTitle("Informação");
                dialogoInfo.setHeaderText("Erro!");
                dialogoInfo.setContentText("Sistema Off-Line ou Nullo");
                dialogoInfo.initOwner(btnEntrar.getScene().getWindow());
                dialogoInfo.showAndWait();
                
            }

        }

    }
*/
    
    private void conectar() throws MikrotikApiException {

        System.out.println("** INICIANDO CONEXAO");

        new ConexaoMK().conectar(txtServidor.getText(), Integer.parseInt(txtPorta.getText()), txtUsuario.getText(), txtSenha.getText());
        
        if (Estatico.getStatusSistema() != null && Estatico.getStatusSistema()) {
            
            Estatico.setIp(txtServidor.getText());
            Estatico.setPorta(Integer.parseInt(txtPorta.getText()));
            Estatico.setUsuario(txtUsuario.getText());
            Estatico.setSenha(txtSenha.getText());
            
            iniciarPrincipal();
            
        } else {
            
            System.err.println("Erro: Sistema Off-Line ou Nullo");
            
            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("Informação");
            dialogoInfo.setHeaderText("Erro!");
            dialogoInfo.setContentText("Sistema Off-Line ou Nullo");
            dialogoInfo.initOwner(btnEntrar.getScene().getWindow());
            dialogoInfo.showAndWait();

        }

    }
    
    private void iniciarPrincipal(){
        
        try {
            FXMLLoader fxmlPrincipal = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent r = (Parent) fxmlPrincipal.load();
            Stage stage = new Stage();

            stage.getIcons().add(new Image("fiberbox/img/FiberBox1.png"));
            stage.setScene(new Scene(r));
            stage.setTitle("FiberBox | " + Estatico.getConfiguracao().getUsuario());
            stage.setResizable(false);
            stage.setMaximized(true);
            stage.show();
            btnEntrar.getScene().getWindow().hide();
            
        } catch (IOException e) {
            
            System.err.println("Erro: " + e.getMessage());

            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("Informação");
            dialogoInfo.setHeaderText("Erro!");
            dialogoInfo.setContentText(e.getMessage());
            dialogoInfo.initOwner(btnEntrar.getScene().getWindow());
            dialogoInfo.showAndWait();
            
        }
        
    }
    
}
