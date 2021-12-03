package br.inatel.c207;

import java.sql.SQLException;
import java.util.ArrayList;

public class CompraHasProduto extends Database{
    public int idCompra_Compra;//ID da compra
    public int idProduto_Produto;//ID do produto

    public boolean comprarLivro(ArrayList<Integer> listaCompra, int idC) {

        //Atribuição do valor do ID da compra
        this.idCompra_Compra = idC;
        //Inserção da ação da compra no BD
        //Cada indice da lista armazena um ID do produto
        for (int i = 0; i < listaCompra.size(); i++) {
            connect();
            this.idProduto_Produto = listaCompra.get(i);
            String sql = "INSERT INTO compra_has_produto(idCompra_Compra, idProduto_Produto) VALUES(?, ?)";
            try {
                pst = connection.prepareStatement(sql);
                pst.setInt(1, this.idCompra_Compra);
                pst.setInt(2, this.idProduto_Produto);
                pst.execute();
                check = true;
            } catch (SQLException e) {
                System.out.println("Falha de operação: " + e.getMessage());
                check = false;
            } finally {
                try {
                    connection.close();
                    pst.close();
                } catch (SQLException e) {
                    System.out.println("Falha ao fechar conexão: " + e.getMessage());
                }
            }
        }
        return check;
    }
}
