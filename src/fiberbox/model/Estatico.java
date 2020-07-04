package fiberbox.model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Circle;
import me.legrange.mikrotik.ApiConnection;

/**
 *
 * @author Matheus - DELL
 */
public class Estatico {
    
    private static ApiConnection connnection;
    
    private static Caixa caixa;
    private static Boolean editarCaixa;
    private static String ramalSelecionado;
    private static List<Caixa> listaCaixas = new ArrayList<>();
    private static int caixasOn;
    private static int caixasOff;
    private static int caixasEncontradas;
    private static int totalUsuarios;
    private static List<Caixa> listaCaixasOff = new ArrayList<>();
    private static List<Caixa> listaCaixasOn = new ArrayList<>();
    
    private static List<Circle> listaCaixasCircle = new ArrayList<>();
    
    private static String txtStatusSistema;
    private static Boolean statusSistema;
    private static Boolean novoRamal;
    private static String usuario;
    private static String senha;
    private static String ip;
    private static int porta;
    
    private static Configuracao configuracao;

    public static Caixa getCaixa() {
        return caixa;
    }

    public static void setCaixa(Caixa caixa) {
        Estatico.caixa = caixa;
    }

    public static Boolean getEditarCaixa() {
        return editarCaixa;
    }

    public static void setEditarCaixa(Boolean editarCaixa) {
        Estatico.editarCaixa = editarCaixa;
    }

    public static List<Caixa> getListaCaixas() {
        return listaCaixas;
    }

    public static void setListaCaixas(List<Caixa> listaCaixas) {
        Estatico.listaCaixas = listaCaixas;
    }

    public static int getCaixasOn() {
        return caixasOn;
    }

    public static void setCaixasOn(int caixasOn) {
        Estatico.caixasOn = caixasOn;
    }

    public static int getCaixasOff() {
        return caixasOff;
    }

    public static void setCaixasOff(int caixasOff) {
        Estatico.caixasOff = caixasOff;
    }

    public static int getTotalUsuarios() {
        return totalUsuarios;
    }

    public static void setTotalUsuarios(int totalUsuarios) {
        Estatico.totalUsuarios = totalUsuarios;
    }

    public static List<Caixa> getListaCaixasOff() {
        return listaCaixasOff;
    }

    public static void setListaCaixasOff(Caixa caixa) {
        Estatico.listaCaixasOff.add(caixa);
    }

    public static List<Caixa> getListaCaixasOn() {
        return listaCaixasOn;
    }

    public static void setListaCaixasOn(Caixa caixa) {
        Estatico.listaCaixasOn.add(caixa);
    }

    public static int getCaixasEncontradas() {
        return caixasEncontradas;
    }

    public static void setCaixasEncontradas(int caixasEncontradas) {
        Estatico.caixasEncontradas = caixasEncontradas;
    }
    
        public static List<Circle> getListaCaixasCircle() {
        return listaCaixasCircle;
    }

    public static void setListaCaixasCircle(Circle listaCaixasCircle) {
        Estatico.listaCaixasCircle.add(listaCaixasCircle);
    }

    public static String getTxtStatusSistema() {
        return txtStatusSistema;
    }

    public static void setTxtStatusSistema(String txtStatusSistema) {
        Estatico.txtStatusSistema = txtStatusSistema;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static void setUsuario(String usuario) {
        Estatico.usuario = usuario;
    }

    public static String getIp() {
        return ip;
    }

    public static void setIp(String ip) {
        Estatico.ip = ip;
    }

    public static int getPorta() {
        return porta;
    }

    public static void setPorta(int porta) {
        Estatico.porta = porta;
    }

    public static Boolean getStatusSistema() {
        return statusSistema;
    }

    public static void setStatusSistema(Boolean statusSistema) {
        Estatico.statusSistema = statusSistema;
    }

    public static String getSenha() {
        return senha;
    }

    public static void setSenha(String senha) {
        Estatico.senha = senha;
    }

    public static ApiConnection getConnnection() {
        return connnection;
    }

    public static void setConnnection(ApiConnection connnection) {
        Estatico.connnection = connnection;
    }

    public static Configuracao getConfiguracao() {
        return configuracao;
    }

    public static void setConfiguracao(Configuracao configuracao) {
        Estatico.configuracao = configuracao;
    }

    public static Boolean getNovoRamal() {
        return novoRamal;
    }

    public static void setNovoRamal(Boolean novoRamal) {
        Estatico.novoRamal = novoRamal;
    }

    public static String getRamalSelecionado() {
        return ramalSelecionado;
    }

    public static void setRamalSelecionado(String ramalSelecionado) {
        Estatico.ramalSelecionado = ramalSelecionado;
    }
    
}
