package br.inatel.c207;

import java.sql.SQLException;

public class UsuarioDB extends Database {

    //Inserção do usuario e do login - INSERT
    public boolean insereUsuario(Usuario usuario){
        connect();
        String sql = "INSERT INTO usuario(nome, dataNasc, endereco, email) VALUES(?, ?, ?, ?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, usuario.getNome());
            pst.setString(2, usuario.getDataNasc());
            pst.setString(3, usuario.getEndereco());
            pst.setString(4, usuario.getEmail());
            pst.execute();
            check = true;
        }catch (SQLException e){
            System.out.println("Falha de operação: " + e.getMessage());
            check = false;
        }finally {
            try {
                connection.close();
                pst.close();
            }catch (SQLException e){
                System.out.println("Falha ao fechar conexão: " + e.getMessage());
            }
        }
        return check;
    }

    //Inserção do login - INSERT
    public boolean insereLogin(Login login){
        connect();
        String sql = "INSERT INTO login(cpf, senha, idUsuario_Usuario) VALUES(?, ?, ?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, login.getCpf());
            pst.setString(2, login.getSenha());
            pst.setInt(3, login.getIdUsuario_Usuario());
            pst.execute();
            check = true;
        }catch (SQLException e){
            System.out.println("Falha de operação: " + e.getMessage());
            check = false;
        }finally {
            try {
                connection.close();
                pst.close();
            }catch (SQLException e){
                System.out.println("Falha ao fechar conexão: " + e.getMessage());
            }
        }
        return check;
    }

    // Validação do login - SELECT
    public boolean buscaCpf(String cpf, String senha){
        connect();
        boolean res = false;//resposta retorno do metodo
        String senhaUser;//variavel auxiliar
        String sql = "SELECT senha, nome as Nome FROM login, usuario WHERE id = idLogin AND cpf = '" + cpf + "'";
        try{ statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while(result.next())
            {
                senhaUser = result.getString("senha");
                if(senhaUser.equalsIgnoreCase(senha))
                {
                    res = true;
                    System.out.println("Senha igual!");
                    System.out.println("Bem-vindo(a) " + result.getString("Nome"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro de operação: " + e.getMessage());
            res = false;
        }finally {
            try
            {
                connection.close();
                statement.close();
                result.close();
            }catch (SQLException e){
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
        return res;
    }

    //Atualização da senha do usuário - UPDATE
    public boolean atualizaUsuario(String cpf, String senha){
        connect();
        String sql = "UPDATE login SET senha=? WHERE cpf = '" + cpf + "'";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1, senha);
            pst.execute();
            check = true;
        }catch (SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
            check = false;
        }finally {
            try {
                connection.close();
                pst.close();
            }catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
        return check;
    }

    //Exclusão da conta do usuário e seu login- DELETE
    public boolean deleteUsuario(String cpf){
        connect();
        String sql = "DELETE usuario, login from usuario INNER JOIN login WHERE usuario.id = login.idLogin AND login.cpf = '" + cpf + "'";
        try {
            pst = connection.prepareStatement(sql);
            pst.execute();
            System.out.println("Login excluido");
            check = true;
        }catch (SQLException e){
            System.out.println("Falha de operação: " + e.getMessage());
            check = false;
        }finally {
            try {
                connection.close();
                pst.close();
            }catch (SQLException e){
                System.out.println("Falha ao fechar conexão: " + e.getMessage());
            }
        }
        return check;
    }

    //Busca do ID do Usuario - SELECT
    public int buscaUsuario(String cpf){
        connect();
        int idUser = 0;//variavel auxiliar
        String sql = "SELECT id FROM usuario, login WHERE cpf = '" + cpf + "'";
        try{ statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while(result.next())
            {
                idUser = result.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Erro de operação: " + e.getMessage());
        }finally {
            try
            {
                connection.close();
                statement.close();
                result.close();
            }catch (SQLException e){
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
        return idUser;
    }
}