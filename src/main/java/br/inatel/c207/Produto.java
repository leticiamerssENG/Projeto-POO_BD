package br.inatel.c207;

public class Produto {
    public int idProduto;//ID do livro - auto-increment
    private String nome;//nome do livro
    private String categoria;//categoria do livro
    private String autor;//autor do livro
    private int volume;//volume do livro
    private double preco;//preço do livro
    private int qtdEstoque;//quantidade disponível no estoque do livro

    //Construtor
    public Produto(String nome, String categoria, String autor, int volume, double preco, int qtdEstoque) {
        this.nome = nome;
        this.categoria = categoria;
        this.autor = autor;
        this.volume = volume;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
    }

    //Getter e Setter
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

}
