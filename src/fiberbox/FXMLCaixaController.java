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
public class FXMLCaixaController implements Initializable {

    
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
    private Label txtTitulo;
    
    @FXML
    private Button btnSalvar;
    
    /**
     * Initializes the controller class.
     */
    
    private Boolean editar;
    private Caixa c;
    private String codigoAnterior = "";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        editar = Estatico.getEditarCaixa();
        c = Estatico.getCaixa();
        
        if(editar){
            
            inserirDados();
            codigoAnterior = c.getCodigo();
            txtTitulo.setText("EDITAR CAIXA: " + codigoAnterior);
            
        }else{
            
            txtTitulo.setText("CADASTRAR NOVA CAIXA");
            
            txtX.setText(String.valueOf(Estatico.getCaixa().getX()));
            txtY.setText(String.valueOf(Estatico.getCaixa().getY()));
        }
        
        btnSalvar.setOnAction((event) -> {
            
            verificar();
            
        });
        
    }    
    
    private void inserirDados(){
        
        txtCodigo.setText(c.getCodigo());
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
    
    private void verificar(){
        
        int erro = 0;
        String erro1 = "";
        
        Caixa caixa = new Caixa();
        
        if(txtCodigo.getText().length() > 8 || txtCodigo.getText().isEmpty()){
            
            erro ++;
            txtCodigo.requestFocus();
            
            System.err.println("Erro Codigo");
            erro1 += "Erro Codigo\n";
            
        }else{
            
            caixa.setCodigo(txtCodigo.getText());
            
        }
        
        if(txtLocalizacao.getText().length() > 150 || txtLocalizacao.getText().isEmpty()){
            
            erro ++;
            txtLocalizacao.requestFocus();
            
            System.err.println("Erro Localização");
            erro1 += "Erro Localização\n";
            
        }else{
            
            caixa.setEndereco(txtLocalizacao.getText());
            
        }
        
        if(txtX.getText().isEmpty()){
            
            erro ++;
            txtX.requestFocus();
            
            System.err.println("Erro X");
            erro1 += "Erro X\n";
            
        }else{
            
            caixa.setX(Double.parseDouble(txtX.getText()));
            
        }
        
        if(txtY.getText().isEmpty()){
            
            erro ++;
            txtY.requestFocus();
            
            System.err.println("Erro Y");
            erro1 += "Erro Y\n";
            
        }else{
            
            caixa.setY(Double.parseDouble(txtY.getText()));
            
        }
        
        if(txtPpoe1.getText().isEmpty() &&
            txtPpoe2.getText().isEmpty() &&
            txtPpoe3.getText().isEmpty() &&
            txtPpoe4.getText().isEmpty() &&
            txtPpoe4.getText().isEmpty() &&
            txtPpoe6.getText().isEmpty() &&
            txtPpoe7.getText().isEmpty() &&
            txtPpoe8.getText().isEmpty()){
            
            erro ++;
            txtPpoe1.requestFocus();
            
            System.err.println("Erro PPoE");
            erro1 += "Erro PPoE\n";
            
        }
        
        caixa.setUsuario1(txtPpoe1.getText());
        caixa.setUsuario2(txtPpoe2.getText());
        caixa.setUsuario3(txtPpoe3.getText());
        caixa.setUsuario4(txtPpoe4.getText());
        caixa.setUsuario5(txtPpoe5.getText());
        caixa.setUsuario6(txtPpoe6.getText());
        caixa.setUsuario7(txtPpoe7.getText());
        caixa.setUsuario8(txtPpoe8.getText());
        
        if(erro > 0){
            
            System.err.println(erro + " erro(s) encontrado(s)!");
            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("Informação");
            dialogoInfo.setHeaderText(erro + " erro(s) encontrado(s)!");
            dialogoInfo.setContentText(erro1);
            dialogoInfo.initOwner(btnSalvar.getScene().getWindow());
            dialogoInfo.showAndWait();
            
        }else{
            
            if(editar){
                
                if(new CaixaDAO().atualizar(caixa, codigoAnterior)){
                    btnSalvar.getScene().getWindow().hide();
                    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                    dialogoInfo.setTitle("Informação");
                    dialogoInfo.setHeaderText("Sucesso!");
                    dialogoInfo.setContentText(codigoAnterior + " atualizada!");
                    dialogoInfo.showAndWait();
                }else{
                    Alert dialogoInfo = new Alert(Alert.AlertType.WARNING);
                    dialogoInfo.setTitle("Informação");
                    dialogoInfo.setHeaderText("Falha!");
                    dialogoInfo.setContentText(codigoAnterior + " não atualizada!");
                    dialogoInfo.showAndWait();
                }
                
            }else{
                
                if(new CaixaDAO().inserir(caixa)){
                    btnSalvar.getScene().getWindow().hide();
                    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                    dialogoInfo.setTitle("Informação");
                    dialogoInfo.setHeaderText("Sucesso!");
                    dialogoInfo.setContentText(caixa.getCodigo() + " cadastrada!");
                    dialogoInfo.showAndWait();
                }else{
                    Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
                    dialogoInfo.setTitle("Informação");
                    dialogoInfo.setHeaderText("Erro!");
                    dialogoInfo.setContentText(caixa.getCodigo() + " não cadastrada!");
                    dialogoInfo.showAndWait();
                }
                
            }
            
        }
        
    }
    
}
