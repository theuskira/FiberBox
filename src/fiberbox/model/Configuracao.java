package fiberbox.model;

/**
 *
 * @author Matheus - DELL
 */
public class Configuracao {
    
    private int som, atualizacao, conexaoAutomatica;
    private String usuario, caixaOn, caixaOff;
    private Double caixaTam;

    public Configuracao() {
    }

    public Configuracao(int som, int atualizacao, int conexaoAutomatica, String usuario, String caixaOn, String caixaOff, Double caixaTam) {
        this.som = som;
        this.atualizacao = atualizacao;
        this.conexaoAutomatica = conexaoAutomatica;
        this.usuario = usuario;
        this.caixaOn = caixaOn;
        this.caixaOff = caixaOff;
        this.caixaTam = caixaTam;
    }
    
    

    public int getSom() {
        return som;
    }

    public void setSom(int som) {
        this.som = som;
    }

    public int getAtualizacao() {
        return atualizacao;
    }

    public void setAtualizacao(int atualizacao) {
        this.atualizacao = atualizacao;
    }

    public int getConexaoAutomatica() {
        return conexaoAutomatica;
    }

    public void setConexaoAutomatica(int conexaoAutomatica) {
        this.conexaoAutomatica = conexaoAutomatica;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCaixaOn() {
        return caixaOn;
    }

    public void setCaixaOn(String caixaOn) {
        this.caixaOn = caixaOn;
    }

    public String getCaixaOff() {
        return caixaOff;
    }

    public void setCaixaOff(String caixaOff) {
        this.caixaOff = caixaOff;
    }

    public Double getCaixaTam() {
        return caixaTam;
    }

    public void setCaixaTam(Double caixaTam) {
        this.caixaTam = caixaTam;
    }
    
}
