package fiberbox.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String usuario, caixa;
    private Boolean status;
    private List<Ramal> ramal = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String usuario, String caixa, Boolean status) {
        this.usuario = usuario;
        this.caixa = caixa;
        this.status = status;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCaixa() {
        return caixa;
    }

    public void setCaixa(String caixa) {
        this.caixa = caixa;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Ramal> getRamal() {
        return ramal;
    }

    public void setRamal(List<Ramal> ramal) {
        this.ramal = ramal;
    }
    
}
