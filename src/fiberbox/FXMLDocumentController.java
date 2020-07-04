package fiberbox;

import fiberbox.configuracao.ConexaoMK;
import fiberbox.configuracao.FiberBoxBot;
import fiberbox.model.Caixa;
import fiberbox.model.CaixaDAO;
import fiberbox.model.Estatico;
import fiberbox.model.Ramal;
import fiberbox.model.RamalDAO;
import fiberbox.model.UsuarioDAO;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import me.legrange.mikrotik.MikrotikApiException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 *
 * @author Matheus - DELL
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private BorderPane bpPrincipal;
    
    @FXML
    private AnchorPane apMap1;
    
    @FXML
    private TabPane tPanePrincipal;
    
    @FXML
    private ComboBox<String> cBoxRamal;
    
//
//    @FXML
//    private ImageView background1;

    @FXML
    private Label txtTotalUsuarios;

    @FXML
    private Label txtCaixasOn;
    
    @FXML
    private Label txtUsuario;
    
    @FXML
    private Label txtStatusSistema;

    @FXML
    private Label txtCaixasOff;

    @FXML
    private Label txtSistema;

    @FXML
    private Button btnSincronizar;

    @FXML
    private Button btnMusica;

    private List<Map<String, String>> usuarios;

    private Boolean atualizar = true;

    private Boolean musicaOn = true;

    private MediaPlayer player;

    // CAIXAS
    @FXML
    private TableView<Caixa> tvCaixas;
    @FXML
    private TableColumn<Caixa, String> tcCaixaId;
    @FXML
    private TableColumn<Caixa, String> tcCaixaPpoe1;
    @FXML
    private TableColumn<Caixa, String> tcCaixaPpoe2;
    @FXML
    private TableColumn<Caixa, String> tcCaixaPpoe3;
    @FXML
    private TableColumn<Caixa, String> tcCaixaPpoe4;
    @FXML
    private TableColumn<Caixa, String> tcCaixaPpoe5;
    @FXML
    private TableColumn<Caixa, String> tcCaixaPpoe6;
    @FXML
    private TableColumn<Caixa, String> tcCaixaPpoe7;
    @FXML
    private TableColumn<Caixa, String> tcCaixaPpoe8;
    @FXML
    private TableColumn<Caixa, String> tcCaixaLocalizacao;
    @FXML
    private TableColumn<Caixa, Double> tcCaixaX;
    @FXML
    private TableColumn<Caixa, Double> tcCaixaY;

    @FXML
    private TextField txtCaixasPesquisarCx;

    @FXML
    private Label txtCaixasCxSelecionadaID;

    @FXML
    private Button btnCaixasPesquisarCx;

    @FXML
    private Button btnCaixasPesquisarCxCancelar;
    
    private List<AnchorPane> apList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // BOT - TELEGRAM
        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();
        
        try {
            
            FiberBoxBot fiberBoxBot = new FiberBoxBot();
            
            botsApi.registerBot(fiberBoxBot);
            
            System.out.println("BOT ////////////");
            
            System.out.println("BOT User Name: " + fiberBoxBot.getBotUsername());
            
            System.out.println("NOME MAQUINA: " + InetAddress.getLocalHost().getHostName());
            System.out.println("IP MAQUINA: " + InetAddress.getLocalHost().getHostAddress());
            
            //System.out.println("BOT User Name: " + fiberBoxBot.se);
            
            Message message = new Message();
            
            message.setForwardSenderName("");
            
        } catch (TelegramApiException e) {
            System.err.println("Erro: " + e.getMessage());
        } catch (UnknownHostException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // TODO
        //background1.fitWidthProperty().bind(apMap1.widthProperty());
        //background1.fitHeightProperty().bind(apMap1.heightProperty());
        
        // TODO - Verificar Ramais e Inserilos na Lista!
        
        List<Ramal> ramais = new RamalDAO().listar();
        
        for(Ramal r : ramais ){
            
            AnchorPane a = new AnchorPane();
            
            a.setStyle(""
                + "-fx-background-image: url(" + r.getCaminhoFoto() + ");"
                + "-fx-background-size: 100% 100%;");
            
            apList.add(a);
            
        }
        
        apMap1.setStyle(""
                + "-fx-background-image: url(\"fiberbox/img/background1.png\");"
                + "-fx-background-size: 100% 100%;");

        try {

            final URL resource = getClass().getResource("song/song1.mp3");
            final Media media = new Media(resource.toString());

            player = new MediaPlayer(media);

        } catch (Exception e) {

            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("Informação");
            dialogoInfo.setHeaderText("Erro de som!");
            dialogoInfo.setContentText("Erro: " + e.getMessage());
            dialogoInfo.showAndWait();

        }

        btnCaixasPesquisarCx.setOnAction((event) -> {
            pesquisarTabelaCaixas(txtCaixasPesquisarCx.getText());
        });

        txtCaixasPesquisarCx.setOnKeyPressed((event) -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                pesquisarTabelaCaixas(txtCaixasPesquisarCx.getText());
            }
        });

        btnCaixasPesquisarCxCancelar.setOnAction((event) -> {
            iniciarTabelaCaixas();
            txtCaixasPesquisarCx.setText("");
        });

        apMap1.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {

                if (event.getButton().equals(MouseButton.PRIMARY)) {

                    if (event.getClickCount() == 2) {
                        Estatico.setEditarCaixa(false);

                        System.out.println("CRIAR/ALTERAR CAIXA");
                        Estatico.setCaixa(
                                new Caixa("", "", "", "", "", "", "", "", "", "", "", event.getX(), event.getY())
                        );

                        Estatico.setEditarCaixa(false);
                        cadastrarCaixa();
                    }

                }

                System.out.println("X: " + event.getX());
                System.out.println("Y: " + event.getY());

            }
        });

        tcCaixaId.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tcCaixaPpoe1.setCellValueFactory(new PropertyValueFactory<>("usuario1"));
        tcCaixaPpoe2.setCellValueFactory(new PropertyValueFactory<>("usuario2"));
        tcCaixaPpoe3.setCellValueFactory(new PropertyValueFactory<>("usuario3"));
        tcCaixaPpoe4.setCellValueFactory(new PropertyValueFactory<>("usuario4"));
        tcCaixaPpoe5.setCellValueFactory(new PropertyValueFactory<>("usuario5"));
        tcCaixaPpoe6.setCellValueFactory(new PropertyValueFactory<>("usuario6"));
        tcCaixaPpoe7.setCellValueFactory(new PropertyValueFactory<>("usuario7"));
        tcCaixaPpoe8.setCellValueFactory(new PropertyValueFactory<>("usuario8"));
        tcCaixaLocalizacao.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        tcCaixaX.setCellValueFactory(new PropertyValueFactory<>("x"));
        tcCaixaY.setCellValueFactory(new PropertyValueFactory<>("y"));

        //conectar();
        
        //new CaixaDAO().droparTabela();
        //new UsuarioDAO().droparTabela();
        new CaixaDAO().verificarTabela();
        new UsuarioDAO().verificarDados();
        
        iniciarRamais();
        
        iniciarTabelaCaixas();
        
        Thread t1 = new Thread() {

            @Override
            public void run() {

                animationTimer.start();

            }

        };

        t1.start();
        
        try {
            Thread t = new Thread() {

                @Override
                public void run() {

                    while (true) {

                        try {
                            
                            conectar();
                            
                            Thread.sleep(5000);
                            
                        } catch (InterruptedException ex) {
                            
                            System.err.println("Erro: " + ex.getMessage());
                            
                        }

                    }

                }

            };

            t.start();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

    }

    private void iniciarTabelaCaixas() {
        Thread t = new Thread() {

            @Override
            public void run() {

                tvCaixas.setItems(listaCaixas());

            }

        };

        t.start();
    }

    private void pesquisarTabelaCaixas(String pesquisa) {
        Thread t = new Thread() {

            @Override
            public void run() {

                tvCaixas.setItems(pesquisarCaixas(pesquisa));

            }

        };

        t.start();
    }

    // LISTA DE CAIXAS
    private ObservableList<Caixa> listaCaixas() {

        tvCaixas.getItems().clear();
        Estatico.getListaCaixas().clear();

        for (Caixa cx : new CaixaDAO().listar()) {
            Estatico.getListaCaixas().add(cx);
        }

        return FXCollections.observableArrayList(
                Estatico.getListaCaixas()
        );

    }

    // PESQUISAR CAIXAS
    private ObservableList<Caixa> pesquisarCaixas(String pesquisa) {

        tvCaixas.getItems().clear();
        Estatico.getListaCaixas().clear();

        for (Caixa cx : new CaixaDAO().pesquisar(pesquisa)) {
            Estatico.getListaCaixas().add(cx);
        }

        return FXCollections.observableArrayList(
                Estatico.getListaCaixas()
        );

    }

    private void conectar() {

        System.out.println("** INICIANDO CONEXAO");

        try {
            
            new ConexaoMK().conectar(Estatico.getIp(), Estatico.getPorta(), Estatico.getUsuario(), Estatico.getSenha());
            
            if(Estatico.getStatusSistema()){
                
                usuarios = new ConexaoMK().lista(Estatico.getIp(), Estatico.getPorta(), Estatico.getUsuario(), Estatico.getSenha());
            
            }else{
                
                apMap1.getChildren().clear();
                
            }

        } catch (MikrotikApiException e) {

            System.err.println("Erro: " + e.getMessage());

            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("Informação");
            dialogoInfo.setHeaderText("Erro de conexão!");
            dialogoInfo.setContentText("Erro: " + e.getMessage());
            dialogoInfo.showAndWait();

        } finally {

            if (usuarios != null && Estatico.getStatusSistema()) {

                System.out.println("Usuário(s): " + usuarios.size());
                Estatico.setTotalUsuarios(usuarios.size());

                try {

                    localizarCaixas();

                } catch (Exception e) {

                    System.err.println("Erro ao localizar as Caixas: " + e.getMessage());

                } finally {

                    //animationTimer.start(); ATIVAR SE NAO FUNCIONAR
                }

            } else {

                System.err.println("Erro: Sistema Off-Line ou Nullo");

//                Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
//                dialogoInfo.setTitle("Informação");
//                dialogoInfo.setHeaderText("Erro de conexão!");
//                dialogoInfo.setContentText("Erro: Sistema Off-Line ou Nullo");
//                dialogoInfo.showAndWait();
            }

        }

    }

    private void localizarCaixas() {

        List<Caixa> caixa = new CaixaDAO().listar();

        int caixasEncontradas = 0;
        int caixasOnline = 0;
        Estatico.getListaCaixasOn().clear();
        int caixasOff = 0;
        Estatico.getListaCaixasOff().clear();
        
        Estatico.getListaCaixasCircle().clear();

        for (Caixa c : caixa) {

            Circle circle = new Circle();
            boolean caixaOn = false;

            for (Map<String, String> entry : usuarios) {

                if (entry.containsValue("<pppoe-" + c.getUsuario1() + ">")) {
                    caixaOn = true;
                }
                if (entry.containsValue("<pppoe-" + c.getUsuario2() + ">")) {
                    caixaOn = true;
                }
                if (entry.containsValue("<pppoe-" + c.getUsuario3() + ">")) {
                    caixaOn = true;
                }
                if (entry.containsValue("<pppoe-" + c.getUsuario4() + ">")) {
                    caixaOn = true;
                }
                if (entry.containsValue("<pppoe-" + c.getUsuario5() + ">")) {
                    caixaOn = true;
                }
                if (entry.containsValue("<pppoe-" + c.getUsuario6() + ">")) {
                    caixaOn = true;
                }
                if (entry.containsValue("<pppoe-" + c.getUsuario7() + ">")) {
                    caixaOn = true;
                }
                if (entry.containsValue("<pppoe-" + c.getUsuario8() + ">")) {
                    caixaOn = true;
                }

            }

            if (caixaOn) {
                caixasOnline++;
                c.setOnline(true);
                Estatico.setListaCaixasOn(c);
                if(Estatico.getConfiguracao().getCaixaOn() != null){
                    circle.setFill(Paint.valueOf(Estatico.getConfiguracao().getCaixaOn()));
                }else{
                    circle.setFill(Paint.valueOf("DODGERBLUE"));
                }
                
            } else {
                c.setOnline(false);
                Estatico.setListaCaixasOff(c);
                caixasOff++;
                if(Estatico.getConfiguracao().getCaixaOff() != null){
                    circle.setFill(Paint.valueOf(Estatico.getConfiguracao().getCaixaOff()));
                }else{
                    circle.setFill(Paint.valueOf("RED"));
                }
            }
            
            if (caixaOn) {

                //c.setOnline(true);
                //Estatico.setListaCaixasOn(c);
            } else {
                //c.setOnline(false);
                //Estatico.setListaCaixasOff(c);
            }

            circle.setCenterX(c.getX());
            circle.setCenterY(c.getY());
            
            if(Estatico.getConfiguracao().getCaixaTam() != null){
                circle.setRadius(Estatico.getConfiguracao().getCaixaTam());
            }else{
                circle.setRadius(15.0);
            }
            
            circle.setCursor(Cursor.HAND);
            circle.setStroke(Paint.valueOf("BLACK"));
            
            circle.setOnMouseEntered((event) -> {
                
                animationTimer.stop();
                
                circle.setOnMousePressed((e) -> {
                        
                    circle.setCursor(Cursor.MOVE);
                    
                    if(e.getEventType().equals(MouseEvent.MOUSE_MOVED)){
                        
                        circle.setCenterX(e.getSceneX());
                        circle.setCenterY(e.getSceneY() - 52);
                        
                        
                        System.out.println("MOVENDO CAIXA");
                        
                        txtSistema.setText("Movendo");
                        
                    }
                
                });
                
                String usuarios1 = "";
                if (c.getUsuario1() != null && !c.getUsuario1().isEmpty()) {
                    usuarios1 += c.getUsuario1() + "\n";
                }
                if (c.getUsuario2() != null && !c.getUsuario2().isEmpty()) {
                    usuarios1 += c.getUsuario2() + "\n";
                }
                if (c.getUsuario3() != null && !c.getUsuario3().isEmpty()) {
                    usuarios1 += c.getUsuario3() + "\n";
                }
                if (c.getUsuario4() != null && !c.getUsuario4().isEmpty()) {
                    usuarios1 += c.getUsuario4() + "\n";
                }
                if (c.getUsuario5() != null && !c.getUsuario5().isEmpty()) {
                    usuarios1 += c.getUsuario5() + "\n";
                }
                if (c.getUsuario6() != null && !c.getUsuario6().isEmpty()) {
                    usuarios1 += c.getUsuario6() + "\n";
                }
                if (c.getUsuario7() != null && !c.getUsuario7().isEmpty()) {
                    usuarios1 += c.getUsuario7() + "\n";
                }
                if (c.getUsuario8() != null && !c.getUsuario8().isEmpty()) {
                    usuarios1 += c.getUsuario8() + "\n";
                }

                txtSistema.setText("CAIXA\n"
                        + c.getCodigo()
                        + "\n" + usuarios1
                        + "\n" + "LOCALIZAÇÃO\n"
                        + c.getEndereco() + "\n"
                                + "X: " + c.getX()
                        + " | Y: " + c.getY());
                
            });
            
            circle.setOnMouseExited((event) -> {
                if(atualizar){
                    animationTimer.start();
                }
            });

            circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent event) {
                    if (event.getButton().equals(MouseButton.PRIMARY)) {
                        
                        System.out.println("EDITAR CAIXA");
                        
                        Estatico.setEditarCaixa(true);
                        Estatico.setCaixa(c);
                        
                        cadastrarCaixa();
                        
                    }
                }
            });

            caixasEncontradas++;

            Estatico.getListaCaixas().add(c);
            
            Estatico.setListaCaixasCircle(circle);

        }

        Estatico.setCaixasOn(caixasOnline);
        Estatico.setCaixasOff(caixasOff);
        Estatico.setCaixasEncontradas(caixasEncontradas);
        
        //animationTimer.start();
        //animationTimer.stop();

        //atualizacao(null);
    }

    private void cadastrarCaixa() {

        try {

            FXMLLoader fxmlCaixa = new FXMLLoader(getClass().getResource("FXMLCaixa.fxml"));
            Parent r = (Parent) fxmlCaixa.load();
            Stage stage = new Stage();

            stage.getIcons().add(new Image("fiberbox/img/logo1.png"));
            stage.setScene(new Scene(r));
            stage.setResizable(false);
            stage.setAlwaysOnTop(true);
            stage.show();

        } catch (IOException e) {

            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("Informação");
            dialogoInfo.setHeaderText("Erro!");
            dialogoInfo.setContentText("Erro: " + e.getMessage());
            dialogoInfo.showAndWait();

        }

    }

    private void apagarCaixa() {

        try {

            FXMLLoader fxmlCaixa = new FXMLLoader(getClass().getResource("FXMLExcluirCaixa.fxml"));
            Parent r = (Parent) fxmlCaixa.load();
            Stage stage = new Stage();

            stage.getIcons().add(new Image("fiberbox/img/logo1.png"));
            stage.setScene(new Scene(r));
            stage.setResizable(false);
            stage.setAlwaysOnTop(true);
            stage.show();

        } catch (IOException e) {

            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("Informação");
            dialogoInfo.setHeaderText("Erro!");
            dialogoInfo.setContentText("Erro: " + e.getMessage());
            dialogoInfo.showAndWait();

        }

    }

    AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long currentNanoTime) {
            
            //if (currentNanoTime - 500 > 1_000_000_000) {
                
                try {

                    //System.out.println("** ATUALIZANDO GUI");
                    
                    if(Estatico.getStatusSistema() != null){
                        
                        txtUsuario.setText(Estatico.getUsuario() + "@" + Estatico.getIp() + ":" + Estatico.getPorta());
                        
                        if(Estatico.getStatusSistema()){
                            
                            txtStatusSistema.setText("Status do Sistema: On-Line!");
                            txtStatusSistema.setStyle("-fx-background-color: black;"
                                    + " -fx-text-fill: white;"
                                    + " -fx-background-radius: 5;");
                            
                        }else{
                            
                            txtStatusSistema.setText("Status do Sistema: Off-Line!");
                            txtStatusSistema.setStyle("-fx-background-color: black;"
                                    + " -fx-text-fill: red;"
                                    + " -fx-background-radius: 5;");
                            
                        }
                        
                    }
                    
                    if(usuarios != null){

                        //System.out.println("USUARIOS NAO NULOS");
                        
                        apMap1.getChildren().clear();

                        txtTotalUsuarios.setText(String.valueOf(usuarios.size()));

                        txtCaixasOn.setText(String.valueOf(Estatico.getCaixasOn()));

                        //System.out.println("CAIXAS ON: " + Estatico.getCaixasOn());

                        txtCaixasOff.setText(String.valueOf(Estatico.getCaixasOff()));
                        //System.out.println("CAIXAS OFF: " + Estatico.getCaixasOff());

                        txtSistema.setText(Estatico.getCaixasEncontradas() + " caixa(s) encontradas!");
                        txtSistema.setStyle("-fx-background-color: white;"
                                + " -fx-text-fill: black;"
                                + " -fx-font-style: regular;"
                                + " -fx-text-alignment: left;"
                                + " -fx-background-radius: 5;");

                        if (Estatico.getCaixasOff() > 0) {

                            txtCaixasOff.setStyle("-fx-background-color: black;"
                                    + " -fx-text-fill: red;"
                                    + " -fx-background-radius: 5");

                            txtSistema.setText("CAIXA(S) OFF\n" + String.valueOf(Estatico.getCaixasOff()));

                                txtSistema.setStyle("-fx-background-color: white;"
                                        + " -fx-text-fill: red;"
                                        + " -fx-font-style: bold;"
                                        + " -fx-text-alignment: center;"
                                        + " -fx-background-radius: 5;");

                                player.play();

                        } else {

                            txtCaixasOff.setStyle("-fx-background-color: black;"
                                    + " -fx-text-fill: white;"
                                    + " -fx-background-radius: 5");

                            player.stop();

                        }

                        Estatico.getListaCaixasCircle().forEach((c) -> {
                            
                            //c.setCenterX((apMap1.getHeight() * 0.5) - c.getCenterX());
                            
//                            System.out.println(apMap1.getHeight()); //  ALTURA
//                            
//                            double x = c.getCenterX();
//                            double y = c.getCenterY();
//                            
//                            c.setCenterX(apMap1.getWidth() / x * 2);
//                            c.setCenterY((apMap1.getWidth() - y) * 0.5);
//                            
//                            System.out.println(apMap1.getWidth()); // LARGURA
                            
                            apMap1.getChildren().add(c);
                            
                                
                        });
                    }

                    //conectar();
                    //localizarCaixas();
                    //Thread.sleep(500);

                } catch (Exception e) {
                    
                    System.err.println("ERRO ANIMATION TIMER: " + e.getMessage());
//                    Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
//                    dialogoInfo.setTitle("Informação");
//                    dialogoInfo.setHeaderText("Erro!");
//                    dialogoInfo.setContentText("Erro: " + e.getMessage());
//                    dialogoInfo.showAndWait();
                }
                
            //}

        }
    };

    @FXML
    private void atualizacao(ActionEvent event) {

        atualizar = !atualizar;

        System.out.println("SINCRONIZACAO: " + atualizar);

        if (atualizar) {

            btnSincronizar.setText("SINCRONIZAÇÃO ON");
            btnSincronizar.setStyle("-fx-background-color: green;"
                    + " -fx-text-fill: white;"
                    + " -fx-background-radius: 5");

            animationTimer.start();

        } else {

            animationTimer.stop();

            btnSincronizar.setText("SINCRONIZAÇÃO OFF");
            btnSincronizar.setStyle("-fx-background-color: black;"
                    + " -fx-text-fill: white;"
                    + " -fx-background-radius: 5");

        }

    }

    @FXML
    private void mutarSom(ActionEvent event) {

        musicaOn = !musicaOn;

        if (musicaOn) {
            player.setMute(false);
            btnMusica.setText("SOM ON");
            btnMusica.setStyle("-fx-background-color: green;"
                    + " -fx-text-fill: white;"
                    + " -fx-background-radius: 5");
        } else {
            player.setMute(true);
            btnMusica.setText("SOM OFF");
            btnMusica.setStyle("-fx-background-color: black;"
                    + " -fx-text-fill: white;"
                    + " -fx-background-radius: 5");
        }

    }

    @FXML
    private void clickTableaCaixas(MouseEvent event) {

        Caixa cx = tvCaixas.getSelectionModel().getSelectedItem();

        System.out.println("CLICK TABELA: " + cx.getCodigo());

        txtCaixasCxSelecionadaID.setText(cx.getCodigo());

        Estatico.setCaixa(cx);

        if (event.getButton().equals(MouseButton.PRIMARY)) {

            if (event.getClickCount() == 2) {
                
                System.out.println("EDITAR CAIXA");

                Estatico.setEditarCaixa(true);
                Estatico.setCaixa(cx);

                cadastrarCaixa();
                
            }
            
        }

    }

    @FXML
    private void clickTableaCaixasEditar(ActionEvent event) {

        System.out.println("EDITAR CAIXA");

        Estatico.setEditarCaixa(true);

        cadastrarCaixa();

    }

    @FXML
    private void clickTableaCaixasExcluir(ActionEvent event) {

        System.out.println("EXCLUIR CAIXA");

        apagarCaixa();

    }
    
    // RETORNA A DATA DO SISTEMA
    public static final String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
    
    public void abrirConfiguracoes(){
        
        try {

            FXMLLoader fxmlCaixa = new FXMLLoader(getClass().getResource("FXMLConfiguracoes.fxml"));
            Parent r = (Parent) fxmlCaixa.load();
            Stage stage = new Stage();

            stage.getIcons().add(new Image("fiberbox/img/FiberBox1.png"));
            stage.setScene(new Scene(r));
            stage.setTitle("FiberBox - Configurações | " + Estatico.getConfiguracao().getUsuario());
            //stage.setResizable(false);
            //stage.setAlwaysOnTop(true);
            stage.show();

        } catch (IOException e) {

            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("Informação");
            dialogoInfo.setHeaderText("Erro!");
            dialogoInfo.setContentText("Erro: " + e.getMessage());
            dialogoInfo.showAndWait();

        }
        
    }
    
    public void novoRamal(){
        
        try {

            FXMLLoader fxmlCaixa = new FXMLLoader(getClass().getResource("FXMLNovoRamal.fxml"));
            Parent r = (Parent) fxmlCaixa.load();
            Stage stage = new Stage();

            stage.getIcons().add(new Image("fiberbox/img/FiberBox1.png"));
            stage.setScene(new Scene(r));
            stage.setTitle("FiberBox - Novo Ramal | " + Estatico.getConfiguracao().getUsuario());
            Estatico.setNovoRamal(true);
            //stage.setResizable(false);
            //stage.setAlwaysOnTop(true);
            stage.show();

        } catch (IOException e) {

            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("Informação");
            dialogoInfo.setHeaderText("Erro!");
            dialogoInfo.setContentText(e.getMessage());
            dialogoInfo.showAndWait();

        }
        
    }
    
    private List<String> ramais = new ArrayList<>();
    
    private ObservableList<String>  obsCaixa;
    
    private void iniciarRamais(){
        
        try {
            
            String r = "";
        
            int i = new RamalDAO().listar().size();

            for(int j = 0 ; j < i; j++){
                
                r = new RamalDAO().listar().get(j).getNome();
                
                if(!ramais.equals(r)){
                    
                    ramais.add(new RamalDAO().listar().get(j).getNome());
                    
                    Tab t = new Tab(ramais.get(j));
                    t.setContent(apList.get(j));
                    
                    tPanePrincipal.getTabs().add(t);
                    
                }
                
                obsCaixa = FXCollections.observableArrayList(ramais);

                cBoxRamal.setItems(obsCaixa);

                
                
            }
            
        } catch (Exception e) {
            
            System.err.println(e.getMessage());
            
        }
        
    }

}
