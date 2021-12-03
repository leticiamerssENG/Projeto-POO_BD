package br.inatel.c207;

public class Login {
    public int idLogin;//ID do login - auto-increment
    private String cpf;//cpf do usuario para login xxx.xxx.xxx-xx
    private String senha;//senha do usuario para login - maximo de 15 caracteres
    private int idUsuario_Usuario;//ID do usuario relacionado ao login

    //Construtor
    public Login(String cpf, String senha, int idUsuario_Usuario) {
        this.cpf = cpf;
        this.senha = senha;
        this.idUsuario_Usuario = idUsuario_Usuario;
    }

    //Getter e Setter
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdUsuario_Usuario() {
        return idUsuario_Usuario;
    }

    public void setIdUsuario_Usuario(int idUsuario_Usuario) {
        this.idUsuario_Usuario = idUsuario_Usuario;
    }


}
