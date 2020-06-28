package fiberbox.model;

/**
 *
 * @author Matheus - DELL
 */
public class Configuracao {
    
    private Boolean som, atualizacao, conexaoAutomatica;
    private String usuario;

    public Boolean getSom() {
        return som;
    }

    public void setSom(Boolean som) {
        this.som = som;
    }

    public Boolean getAtualizacao() {
        return atualizacao;
    }

    public void setAtualizacao(Boolean atualizacao) {
        this.atualizacao = atualizacao;
    }

    public Boolean getConexaoAutomatica() {
        return conexaoAutomatica;
    }

    public void setConexaoAutomatica(Boolean conexaoAutomatica) {
        this.conexaoAutomatica = conexaoAutomatica;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
    
}
