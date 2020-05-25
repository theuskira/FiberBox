package fiberbox.model;

public class Caixa {
    
    private String codigo, endereco, usuario1, usuario2, usuario3,
            usuario4, usuario5, usuario6, usuario7, usuario8;
    
    private Double x, y;
    
    private Boolean online;

    public Caixa(
            String codigo,
            String endereco,
            String usuario1,
            String usuario2,
            String usuario3,
            String usuario4,
            String usuario5,
            String usuario6,
            String usuario7,
            String usuario8, 
            Double x,
            Double y) {
        
        this.codigo = codigo;
        this.endereco = endereco;
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
        this.usuario3 = usuario3;
        this.usuario4 = usuario4;
        this.usuario5 = usuario5;
        this.usuario6 = usuario6;
        this.usuario7 = usuario7;
        this.usuario8 = usuario8;
        this.x = x;
        this.y = y;
        
    }
    
    public Caixa() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(String usuario1) {
        this.usuario1 = usuario1;
    }

    public String getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(String usuario2) {
        this.usuario2 = usuario2;
    }

    public String getUsuario3() {
        return usuario3;
    }

    public void setUsuario3(String usuario3) {
        this.usuario3 = usuario3;
    }

    public String getUsuario4() {
        return usuario4;
    }

    public void setUsuario4(String usuario4) {
        this.usuario4 = usuario4;
    }

    public String getUsuario5() {
        return usuario5;
    }

    public void setUsuario5(String usuario5) {
        this.usuario5 = usuario5;
    }

    public String getUsuario6() {
        return usuario6;
    }

    public void setUsuario6(String usuario6) {
        this.usuario6 = usuario6;
    }

    public String getUsuario7() {
        return usuario7;
    }

    public void setUsuario7(String usuario7) {
        this.usuario7 = usuario7;
    }

    public String getUsuario8() {
        return usuario8;
    }

    public void setUsuario8(String usuario8) {
        this.usuario8 = usuario8;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }
    
    
    
}
