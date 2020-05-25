package fiberbox;

import fiberbox.configuracao.ConexaoMK;
import fiberbox.configuracao.FiberBoxBot;
import fiberbox.model.Caixa;
import fiberbox.model.CaixaDAO;
import fiberbox.model.Estatico;
import fiberbox.model.UsuarioDAO;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 *
 * @author Matheus - DELL
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane apMap1;

    @FXML
    private ImageView background1;

    @FXML
    private Label txtTotalUsuarios;

    @FXML
    private Label txtCaixasOn;

    @FXML
    private Label txtCaixasOff;

    @FXML
    private Label txtSistema;

    @FXML
    private Button btnSincronizar;

    @FXML
    private Button btnMusica;

    private List<Map<String, String>> usuarios;

    private Boolean sistemaOn = false;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // BOT - TELEGRAM
        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new FiberBoxBot());
        } catch (TelegramApiException e) {
            System.err.println("Erro: " + e.getMessage());
        }

        // TODO
        background1.fitWidthProperty().bind(apMap1.widthProperty());
        background1.fitHeightProperty().bind(apMap1.heightProperty());

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

        background1.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {

                if (event.getButton().equals(MouseButton.PRIMARY)) {

                    if (event.getClickCount() == 2) {
                        Estatico.setEditarCaixa(false);

                        System.out.println("CRIAR/ALTERAR CAIXA");
                        Estatico.setCaixa(
                                new Caixa("", "", "", "", "", "", "", "", "", "", event.getX(), event.getY())
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

        iniciarTabelaCaixas();
        
        animationTimer.start();
        
        try {
            Thread t = new Thread() {

                @Override
                public void run() {

                    while (true) {

                        try {
                            
                            conectar();
                            Thread.sleep(3000);
                            
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

            usuarios = new ConexaoMK().lista("10.10.11.1", "fibermap", "pw123456");

            sistemaOn = true;

            //System.out.println("** SISTEMA CONECTADO");

        } catch (Exception e) {

            System.err.println("Erro: " + e.getMessage());

            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("Informação");
            dialogoInfo.setHeaderText("Erro de conexão!");
            dialogoInfo.setContentText("Erro: " + e.getMessage());
            dialogoInfo.showAndWait();

            sistemaOn = false;

        } finally {

            if (sistemaOn && usuarios != null) {

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

        for (Caixa c : caixa) {

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
            } else {
                c.setOnline(false);
                Estatico.setListaCaixasOff(c);
                caixasOff++;
            }

            caixasEncontradas++;

            Estatico.getListaCaixas().add(c);

        }

        Estatico.setCaixasOn(caixasOnline);
        Estatico.setCaixasOff(caixasOff);
        Estatico.setCaixasEncontradas(caixasEncontradas);

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

            try {

                //System.out.println("** ATUALIZANDO GUI");
                
                if(usuarios != null){
                    
                    System.out.println("USUARIOS NAO NULOS");
                    
                    txtTotalUsuarios.setText(String.valueOf(usuarios.size()));

                    txtCaixasOn.setText(String.valueOf(Estatico.getCaixasOn()));

                    System.out.println("CAIXAS ON: " + Estatico.getCaixasOn());

                    txtCaixasOff.setText(String.valueOf(Estatico.getCaixasOff()));
                    System.out.println("CAIXAS OFF: " + Estatico.getCaixasOff());

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

                    for (Caixa c : Estatico.getListaCaixas()) {

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
                            circle.setFill(Paint.valueOf("BLUE"));
                            c.setOnline(true);
                            Estatico.setListaCaixasOn(c);
                        } else {
                            circle.setFill(Paint.valueOf("RED"));
                            c.setOnline(false);
                            Estatico.setListaCaixasOff(c);
                        }

                        circle.setCenterX(c.getX());
                        circle.setCenterY(c.getY());
                        circle.setRadius(10.0f);

                        circle.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
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
                                    + "\n" + "LOCALIZACAO\n"
                                    + c.getEndereco() + "\n"
                                    + "X: " + c.getX()
                                    + " - Y: " + c.getY());

                            if (event.getButton().equals(MouseButton.PRIMARY)) {
                                if (event.getClickCount() == 2) {

                                    System.out.println("EDITAR CAIXA");

                                    Estatico.setEditarCaixa(true);
                                    Estatico.setCaixa(c);

                                    cadastrarCaixa();

                                }
                            }

                        });

                        apMap1.getChildren().add(
                                circle
                        );

                    }
                }

                //conectar();
                //localizarCaixas();
                Thread.sleep(60);

            } catch (InterruptedException e) {
                Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
                dialogoInfo.setTitle("Informação");
                dialogoInfo.setHeaderText("Erro!");
                dialogoInfo.setContentText("Erro: " + e.getMessage());
                dialogoInfo.showAndWait();
            }
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

}
