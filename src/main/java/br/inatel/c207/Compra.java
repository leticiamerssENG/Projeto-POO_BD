package br.inatel.c207;

import java.sql.SQLException;

public class Compra extends Database{
    private int idCompra;//ID da compra - não auto-increment
    private int pagamento;//forma de pagamento da compra
    private int idUsuario_Usuario;//ID do usuario que realizou a compra
    private double totalCompra;//total da compra

    //Construtor
    public Compra(int idCompra, int pagamento, int idUsuario_Usuario, double totalCompra) {
        this.idCompra = idCompra;
        this.pagamento = pagamento;
        this.idUsuario_Usuario = idUsuario_Usuario;
        this.totalCompra = totalCompra;
    }

    //Getter e Setter
    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getPagamento() {
        return pagamento;
    }

    public void setPagamento(int pagamento) {
        this.pagamento = pagamento;
    }

    public int getIdUsuario_Usuario() {
        return idUsuario_Usuario;
    }

    public void setIdUsuario_Usuario(int idUsuario_Usuario) {
        this.idUsuario_Usuario = idUsuario_Usuario;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }

    //Inserção da compra no BD - INSERT
    public boolean insereCompra(Compra compra){
        connect();
        String sql = "INSERT INTO compra(pagamento, totalCompra, idUsuario_Usuario) VALUES(?, ? ,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, compra.getPagamento());
            pst.setDouble(2, compra.getTotalCompra());
            pst.setInt(3, compra.idUsuario_Usuario);
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
}