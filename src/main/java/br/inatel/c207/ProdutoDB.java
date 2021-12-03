package br.inatel.c207;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDB extends Database {

    //Inserção de novos livros - INSERT
    public boolean insereProduto(Produto produto){
        connect();
        String sql = "INSERT INTO produto(nome, categoria, autor, volume, preco, qtdEstoque) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, produto.getNome());
            pst.setString(2, produto.getCategoria());
            pst.setString(3, produto.getAutor());
            pst.setInt(4, produto.getVolume());
            pst.setDouble(5, produto.getPreco());
            pst.setInt(6, produto.getQtdEstoque());
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

    //Listar todos os livros cadastrados - SELECT
    public ArrayList<Produto> listaProduto(){
        ArrayList<Produto> livros = new ArrayList<>();
        connect();
        String sql = "SELECT * FROM produto";
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while (result.next()){
                Produto produtoTemp = new Produto(result.getString("nome"),result.getString("categoria"), result.getString("autor"), result.getInt("volume"), result.getDouble("preco"), result.getInt("qtdEstoque"));
                produtoTemp.idProduto = result.getInt("idProduto");
                livros.add(produtoTemp);
            }
        }catch (SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }finally {
            try {
                connection.close();
                statement.close();
                result.close();
            }catch (SQLException e){
                System.out.println("Falha ao fechar conexão: " + e.getMessage());
            }
        }
        return  livros;
    }

    //Listar todos os livros cadastrados de acordo com a categoria escolhida pelo usuario - SELECT
    public ArrayList<Produto> buscaProduto(String categoria){
        ArrayList<Produto> livrosCategoria = new ArrayList<>();
        connect();
        String sql = "SELECT * FROM produto WHERE categoria = '" + categoria + "'";
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while (result.next()){
                Produto produtoTemp = new Produto(result.getString("nome"),result.getString("categoria"), result.getString("autor"), result.getInt("volume"), result.getDouble("preco"), result.getInt("qtdEstoque"));
                produtoTemp.idProduto = result.getInt("idProduto");
                livrosCategoria.add(produtoTemp);
            }
        }catch (SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }finally {
            try {
                connection.close();
                statement.close();
                result.close();
            }catch (SQLException e){
                System.out.println("Falha ao fechar conexão: " + e.getMessage());
            }
        }
        return  livrosCategoria;
    }

    //Busca o preço do livro - SELECT
    public double precoProduto(int idProd){
        connect();
        String idP;//variavel auxiliar
        idP = String.valueOf(idProd);//transformando de int para String
        double precoLivro = 0;//retorno do preço do livro
        String sql = "SELECT preco FROM produto WHERE idProduto = '" + idP + "'";
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while (result.next()){
                precoLivro = result.getDouble("preco");
            }
        }catch (SQLException e){
            System.out.println("Erro de operação: " + e.getMessage());
        }finally {
            try {
                connection.close();
                statement.close();
                result.close();
            }catch (SQLException e){
                System.out.println("Falha ao fechar conexão: " + e.getMessage());
            }
        }
        return precoLivro;
    }
}