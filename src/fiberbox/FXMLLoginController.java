package fiberbox;

import fiberbox.configuracao.ConexaoMK;
import fiberbox.model.Estatico;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        btnEntrar.setOnAction((event) -> {
            verificar();
        });
        
    }

    private void verificar(){
        
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
            
            conectar();
            
        }
        
    }
    
    private void conectar() {

        System.out.println("** INICIANDO CONEXAO");

        try {

            new ConexaoMK().lista(txtServidor.getText(), txtUsuario.getText(), txtSenha.getText());

        } catch (Exception e) {

            System.err.println("Erro de conexão: " + e.getMessage());

            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("Informação");
            dialogoInfo.setHeaderText("Erro de conexão!");
            dialogoInfo.setContentText(e.getMessage());
            dialogoInfo.initOwner(btnEntrar.getScene().getWindow());
            dialogoInfo.showAndWait();

        } finally {

            if (Estatico.getStatusSistema()) {

                Estatico.setIp(txtServidor.getText());
                Estatico.setPorta(txtPorta.getText());
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
    
    private void iniciarPrincipal(){
        
        try {
            FXMLLoader fxmlPrincipal = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent r = (Parent) fxmlPrincipal.load();
            Stage stage = new Stage();

            stage.getIcons().add(new Image("fiberbox/img/FiberBox.png"));
            stage.setScene(new Scene(r));
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
