package fiberbox;

import fiberbox.model.Configuracao;
import fiberbox.model.ConfiguracaoDAO;
import fiberbox.model.Estatico;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Matheus - DELL
 */
public class FXMLConfiguracoesController implements Initializable {

    @FXML
    private Circle cExemploOn;

    @FXML
    private Circle cExemploOff;
    
    @FXML
    private ColorPicker cpCaixaOn;
    
    @FXML
    private ColorPicker cpCaixaOff;
    
    @FXML
    private Slider sCaixaRaio;
    
    @FXML
    private Label txtRaio;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        cExemploOn.setStroke(Paint.valueOf("BLACK"));
        caixaOnAnteriorCor();
        
        cExemploOff.setStroke(Paint.valueOf("BLACK"));
        caixaOffAnteriorCor();
        
        
        sCaixaRaio.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                txtRaio.setText("Raio: " + (int) sCaixaRaio.getValue());
                cExemploOn.setRadius(sCaixaRaio.getValue());
                cExemploOff.setRadius(sCaixaRaio.getValue());
            }
        });
        
    }
    
    @FXML
    private void changeCaixaOn(ActionEvent event){
        cExemploOn.setFill(cpCaixaOn.getValue());
    }
    
    @FXML
    private void changeCaixaOff(ActionEvent event){
        cExemploOff.setFill(cpCaixaOff.getValue());
    }
    
    private void caixaOnAnteriorCor(){
        
        String cor = "DODGERBLUE";
        Double raio = 15.0;
        
        if(Estatico.getConfiguracao().getCaixaOn() != null){

            cor = Estatico.getConfiguracao().getCaixaOn();
            raio = Estatico.getConfiguracao().getCaixaTam();

        }else{
            System.err.println("Caixa ONLINE ANTERIOR NULA!");
        }
        
        cExemploOn.setFill(Paint.valueOf(cor));
        cExemploOn.setRadius(raio);
        cpCaixaOn.setValue(Color.valueOf(cor));
        sCaixaRaio.setValue(raio);
        txtRaio.setText("Raio: " + (int) sCaixaRaio.getValue());
        
    }
    
    private void caixaOffAnteriorCor(){
        
        String cor = "RED";
        Double raio = 15.0;
        
        if(Estatico.getConfiguracao().getCaixaOff() != null){

            cor = Estatico.getConfiguracao().getCaixaOff();
            raio = Estatico.getConfiguracao().getCaixaTam();
            
        }else{
            System.err.println("Caixa OFFLINE ANTERIOR NULA!");
        }
        
        cExemploOff.setFill(Paint.valueOf(cor));
        cExemploOff.setRadius(raio);
        cpCaixaOff.setValue(Color.valueOf(cor));
        sCaixaRaio.setValue(raio);
        txtRaio.setText("Raio: " + (int) sCaixaRaio.getValue());
        
    }
    
    @FXML
    private void aplicarCaixas(ActionEvent event){

        System.out.println("* Salvar Configuração das Caixas");
        
        System.out.println("Cor Online: " + cpCaixaOn.getValue().toString());
        System.out.println("Cor Offline: " + cpCaixaOn.getValue().toString());
        System.out.println("Tamanho/Raio: " + sCaixaRaio.getValue());
        
        Configuracao config = Estatico.getConfiguracao();
        
        config.setCaixaOn(cpCaixaOn.getValue().toString());
        config.setCaixaOff(cpCaixaOff.getValue().toString());
        config.setCaixaTam(sCaixaRaio.getValue());
        
        if(new ConfiguracaoDAO().atualizar(config, Estatico.getConfiguracao().getUsuario())){
            System.out.println("Configuracao Atualizada com Sucesso!");
            
            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Informação");
            dialogoInfo.setHeaderText("Salvo!");
            dialogoInfo.setContentText("Configuracao Atualizada com Sucesso!");
            dialogoInfo.initOwner(cExemploOff.getScene().getWindow());
            dialogoInfo.showAndWait();
            
        }
        
    }
    
}
