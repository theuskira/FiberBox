package fiberbox.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Matheus - DELL
 */
public class Estatico {
    
    private static Caixa caixa;
    private static Boolean editarCaixa;
    private static List<Caixa> listaCaixas = new ArrayList<>();
    private static int caixasOn;
    private static int caixasOff;
    private static int caixasEncontradas;
    private static int totalUsuarios;
    private static List<Caixa> listaCaixasOff = new ArrayList<>();
    private static List<Caixa> listaCaixasOn = new ArrayList<>();

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
    
    
    
}
