package br.inatel.c207;

public class Usuario{
    public int id;//ID do usuário - auto-increment
    private String nome;//nome completo
    private String dataNasc;//data de nascimento (dd/mm/aaaa)
    private String endereco;//endereço completo
    private String email;//email

    //Construtor
    public Usuario(String nome, String dataNasc, String endereco, String email) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.endereco = endereco;
        this.email = email;
    }

    //Getter e Setter
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
