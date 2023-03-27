package model;

public class Cliente {
    int codigo;
    String nome;
    String estado;
    String cidade;
    String bairro;
    String logradouro;
    String complemento;

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Codigo: "+this.codigo+"\nNome: "+ this.nome+"\nEstado:"+this.estado+"\nCidade: "+this.cidade+"\nBairro: "+this.bairro+"\nLogradouro: "+this.logradouro+"\nComplemento: "+this.logradouro;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getLogradouro() {
        return logradouro;
    }
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    

    
}
