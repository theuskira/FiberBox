package fiberbox;

import fiberbox.model.Caixa;
import fiberbox.model.CaixaDAO;
import fiberbox.model.Estatico;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Matheus - DELL
 */
public class FXMLExcluirCaixaController implements Initializable {

    
    @FXML
    private TextField txtCodigo;
    
    @FXML
    private TextField txtPpoe1;
    
    @FXML
    private TextField txtPpoe2;
    
    @FXML
    private TextField txtPpoe3;
    
    @FXML
    private TextField txtPpoe4;
    
    @FXML
    private TextField txtPpoe5;
    
    @FXML
    private TextField txtPpoe6;
    
    @FXML
    private TextField txtPpoe7;
    
    @FXML
    private TextField txtPpoe8;
    
    @FXML
    private TextArea txtLocalizacao;
    
    @FXML
    private TextField txtX;
    
    @FXML
    private TextField txtY;
    
    @FXML
    private Label txtExcluir;
    
    @FXML
    private Button btnExcluir;
    
    @FXML
    private Button btnCancelar;
    
    private Caixa c;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        c = Estatico.getCaixa();
        
        inserirDados();
        
        btnExcluir.setOnAction((event) -> {
            
            if(new CaixaDAO().deletar(c)){
                
                btnExcluir.getScene().getWindow().hide();
                Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                dialogoInfo.setTitle("Informação");
                dialogoInfo.setHeaderText("Sucesso!");
                dialogoInfo.setContentText("Caixa " + c.getCodigo() + " apagada!");
                dialogoInfo.initOwner(btnExcluir.getScene().getWindow());
                dialogoInfo.showAndWait();
                
            }else{
                
                Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
                dialogoInfo.setTitle("Informação");
                dialogoInfo.setHeaderText("Erro!");
                dialogoInfo.setContentText("Erro ao apagar a caixa: " + c.getCodigo());
                dialogoInfo.initOwner(btnExcluir.getScene().getWindow());
                dialogoInfo.showAndWait();
                
            }
            
        });
        
        btnCancelar.setOnAction((event) -> {
            btnCancelar.getScene().getWindow().hide();
        });
        
    }   
    
    private void inserirDados(){
        
        txtCodigo.setText(c.getCodigo());
        txtExcluir.setText("EXCLUIR " + c.getCodigo());
        txtLocalizacao.setText(c.getEndereco());
        txtX.setText(String.valueOf(c.getX()));
        txtY.setText(String.valueOf(c.getY()));
        
        if(c.getUsuario1() != null){
            txtPpoe1.setText(c.getUsuario1());
        }
        
        if(c.getUsuario2() != null){
            txtPpoe2.setText(c.getUsuario2());
        }
        
        if(c.getUsuario3() != null){
            txtPpoe3.setText(c.getUsuario3());
        }
        
        if(c.getUsuario4() != null){
            txtPpoe4.setText(c.getUsuario4());
        }
        
        if(c.getUsuario5() != null){
            txtPpoe5.setText(c.getUsuario5());
        }
        
        if(c.getUsuario6() != null){
            txtPpoe6.setText(c.getUsuario6());
        }
        
        if(c.getUsuario7() != null){
            txtPpoe7.setText(c.getUsuario7());
        }
        
        if(c.getUsuario8() != null){
            txtPpoe8.setText(c.getUsuario8());
        }
        
    }
    
}
