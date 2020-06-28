package fiberbox.model;

/**
 *
 * @author Matheus - DELL
 */
public class Ramal {
    
    private String nome, ip, senha;
    private int porta;
    private String usuario, caminhoFoto;

    public Ramal() {
    }

    public Ramal(String nome, String ip, String senha, int porta) {
        this.nome = nome;
        this.ip = ip;
        this.senha = senha;
        this.porta = porta;
    }
    public Ramal(String nome, String ip, String senha, int porta, String usuario, String caminhoFoto) {
        this.nome = nome;
        this.ip = ip;
        this.senha = senha;
        this.porta = porta;
        this.usuario = usuario;
        this.caminhoFoto = caminhoFoto;
        
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }
    
    
    
}
