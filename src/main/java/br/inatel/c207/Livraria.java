package br.inatel.c207;

import java.util.ArrayList;
import java.util.Scanner;

public class Livraria {

    public void menuSecundario(UsuarioDB usuarioDB, String cpf, String senha){

        //Declaração das variáveis
        int escolha;//escolha do menu secundario
        int categoria;//escolha da categoria do livro para busca
        int livro;//ID do livro a ser comprado
        int qtdLivro;//quantidade de livros que o usuario deseja comprar
        double totalCompra = 0;//total da compra dos livros
        int confCompra = 0;//confirmação da compra dos livros
        int c = 1;//ID da compra - não será auto-increment
        int idUser;//ID do usuario do login ativo
        int formaPag = 0;//escolha da forma do pagamento da compra
        boolean menu = true;

        //Criando objeto de manipulação no BD do produto
        ProdutoDB produtoDB = new ProdutoDB();
        //Criando objeto para leitura das entradas das variáveis
        Scanner sc = new Scanner(System.in);
        //Criando objeto para a lista dos livros da livraria
        ArrayList<Produto> listaLivro = new ArrayList<>();
        //Criando objeto para a lista dos IDs dos livros a serem comprados
        ArrayList<Integer> listaCompra = new ArrayList<>();

        //Criando os objetos dos livros
        //Inserindo os livros no Banco de Dados do Produto
        /*
        Produto produto = new Produto("Crespusculo","Ficçao","Meyer",1,56.00,2);
        produtoDB.insereProduto(produto);
        Produto produto2 = new Produto("Lua Nova","Fantasia","Meyer",1,62.00,1);
        produtoDB.insereProduto(produto2);
        Produto produto3 = new Produto("Eclipse","Ficçao","Meyer",1,60.00,2);
        produtoDB.insereProduto(produto3);
        */

        /*===============================MENU SECUNDARIO============================*/
        while(menu){
            System.out.println("*===Livraria LM===*");
            System.out.println(" ");
            System.out.println("Escolha uma opção:");
            System.out.println(" ");
            System.out.println("1 - Listar Livros.");
            System.out.println("2 - Pesquisar livro por categoria.");
            System.out.println("3 - Comprar livros.");
            System.out.println("4 - Alterar a senha.");
            System.out.println("5 - Deletar conta.");
            System.out.println("6 - Sair.");

            //Leitura da escolha do usuário
            escolha = sc.nextInt();
            sc.nextLine();

            switch(escolha)
            {
                case 1:
                    //Apresenta todos os livros da livraria
                    System.out.println("*===LIVROS===*");
                    listaLivro = produtoDB.listaProduto();
                    try{
                        for(int i = 0; i < listaLivro.size();i++)
                        {
                            System.out.println("Livro " + (i+1));
                            System.out.println("ID " + listaLivro.get(i).idProduto);
                            System.out.println("Nome = " + listaLivro.get(i).getNome());
                            System.out.println("Categoria = " + listaLivro.get(i).getCategoria());
                            System.out.println("Autor = " + listaLivro.get(i).getAutor());
                            System.out.println("Volume = " + listaLivro.get(i).getVolume());
                            System.out.println("Preço = " + listaLivro.get(i).getPreco());
                            if(listaLivro.get(i).getQtdEstoque() > 0)
                            {
                                System.out.println("Disponível em estoque!");
                            }
                            else{
                                System.out.println("Indisponível!");
                            }
                            System.out.println("------------------------------");
                        }
                        listaLivro.clear();//esvaziar a lista
                    } catch (Exception e) {
                        System.out.println("Sitema fora do ar!");
                    }
                    break;
                case 2:
                    //Apresenta os livros da livraria de acordo com a categoria escolhida
                    System.out.println("*===PESQUISA===*");
                    System.out.println("Indique a categoria:");
                    System.out.println("1- Ficçao");
                    System.out.println("2- Romance");
                    System.out.println("3- Fantasia");
                    categoria = sc.nextInt();

                    if(categoria == 1)
                        listaLivro = produtoDB.buscaProduto("Ficçao");
                    else if(categoria == 2)
                        listaLivro = produtoDB.buscaProduto("Romance");
                    else if(categoria == 3)
                        listaLivro = produtoDB.buscaProduto("Fantasia");
                    else
                        System.out.println("Categoria não encontrada!");
                    try{
                        for(int i = 0; i < listaLivro.size();i++)
                        {
                            System.out.println("Livro " + (i+1));
                            System.out.println("ID " + listaLivro.get(i).idProduto);
                            System.out.println("Nome = " + listaLivro.get(i).getNome());
                            System.out.println("Categoria = " + listaLivro.get(i).getCategoria());
                            System.out.println("Autor = " + listaLivro.get(i).getAutor());
                            System.out.println("Volume = " + listaLivro.get(i).getVolume());
                            System.out.println("Preço = " + listaLivro.get(i).getPreco());
                            if(listaLivro.get(i).getQtdEstoque() > 0)
                            {
                                System.out.println("Disponível em estoque!");
                            }
                            else{
                                System.out.println("Indisponível!");
                            }

                            System.out.println("------------------------------");
                        }
                        listaLivro.clear();//esvaziar a lista
                    } catch (Exception e) {
                        System.out.println("Sistema fora do ar!");
                    }
                    break;
                case 3:
                    //Realizar a compra dos livros
                    System.out.println("Informe quantos livros irá comprar: ");
                    qtdLivro = sc.nextInt();

                    System.out.println("Indique o(s) ID(s) do(s) livro(s):");
                    //Leitura dos IDs do livro
                    //Cálculo do total da Compra
                    //Adiciona o ID do livro em uma lista de compra
                    for(int i = 0; i < qtdLivro;i++)
                    {
                        livro = sc.nextInt();
                        totalCompra = totalCompra + produtoDB.precoProduto(livro);
                        listaCompra.add(livro);
                    }
                    //Apresentação do total da compra
                    System.out.println("Total da Compra: R$ " + totalCompra);
                    //Confirmação da compra
                    System.out.println("Confirmar compra (S-1, N-2)?");
                    confCompra = sc.nextInt();
                    if(confCompra == 1)
                    {
                        idUser = usuarioDB.buscaUsuario(cpf);
                        System.out.println("Forma de Pagamento: ");
                        System.out.println("1- Cartão bancário");
                        System.out.println("2- Boleto");
                        formaPag = sc.nextInt();
                        //Compra
                        Compra compra = new Compra(c,formaPag,idUser, totalCompra);
                        compra.insereCompra(compra);
                        //CompraHasProduto
                        CompraHasProduto compraHasProduto = new CompraHasProduto();
                        compraHasProduto.comprarLivro(listaCompra,c);
                        //atualizar o total do estoque do livro
                        c++;//ID da compra
                    }
                    else
                        System.out.println("Compra Cancelada!");
                    break;
                case 4:
                    //Altera a senha do usuario do login
                    System.out.println("*===PERFIL===*");
                    System.out.println("Confirme seu CPF:");
                    cpf = sc.nextLine();
                    System.out.println("Entre com a nova senha:");
                    senha = sc.nextLine();
                    if(usuarioDB.atualizaUsuario(cpf, senha))
                        System.out.println("Senha atualizada com sucesso!");
                    else
                        System.out.println("Erro na atualização");
                    break;
                case 5:
                    //Deleta a conta do usuário e seu login
                    System.out.println("*===DELETAR CONTA===*");
                    System.out.println("Confirme o seu CPF:");
                    cpf = sc.nextLine();
                    if(usuarioDB.deleteUsuario(cpf))
                        System.out.println("Conta excluída com sucesso!");
                    else
                        System.out.println("Erro ao excluir a conta!");
                    break;
                case 6:
                    //Sair da livraria
                    System.out.println("Você saiu!");
                    menu = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }

    }
}