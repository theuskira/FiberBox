package fiberbox.model;

public class Usuario {

    private String usuario, caixa;
    private Boolean status;

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
    
    
    
}
