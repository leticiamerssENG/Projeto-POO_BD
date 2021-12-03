package br.inatel.c207;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        //Declaração das variáveis
        int escolha; //escolha do menu principal
        boolean senhaV = false;//variavel auxiliar para verificação de senha
        int idUser;//variavel auxiliar ID do Usuario

        //Dados do usuário-login
        String nome;
        String cpf;
        String senha;
        String dataN;
        String endereco;
        String email;

        //Criando objeto de manipulação no BD do usuario
        UsuarioDB usuarioDB = new UsuarioDB();
        //Criando objeto do Menu Secundario
        Livraria livraria = new Livraria();
        //Criando objeto para leitura das entradas das variáveis
        Scanner sc = new Scanner(System.in);

        /*===============================MENU PRINCIPAL============================*/
        System.out.println("*===Livraria LM===*");
        System.out.println(" ");
        System.out.println("Escolha uma opção:");
        System.out.println(" ");
        System.out.println("1 - Cadastrar.");
        System.out.println("2 - Entrar.");
        System.out.println("3 - Sair.");

        //Leitura da escolha do usuário
        escolha = sc.nextInt();
        sc.nextLine();

        switch(escolha)
        {
            case 1:
                //Cadastro do usuário
                System.out.println("*===Dados Cadastrais===*");
                System.out.println("Informe seu Nome:");
                nome = sc.nextLine();
                System.out.println("Informe seu CPF:");
                cpf = sc.nextLine();
                System.out.println("Informe sua Data de Nascimento (dd/mm/aaaa):");
                dataN = sc.nextLine();
                System.out.println("Informe seu Endereço Completo:");
                endereco = sc.nextLine();
                System.out.println("Informe seu email:");
                email = sc.nextLine();
                System.out.println("Crie uma senha (máximo 10 caracteres):");
                senha = sc.nextLine();
                Usuario usuario = new Usuario(nome, dataN, endereco, email);
                if(usuarioDB.insereUsuario(usuario))
                {
                    idUser = usuarioDB.buscaUsuario(cpf);
                    System.out.println(idUser);
                    Login login = new Login(cpf, senha, idUser);
                    usuarioDB.insereLogin(login);
                    System.out.println("Cadastro realizado com sucesso!");
                    livraria.menuSecundario(usuarioDB, cpf, senha);
                }
                break;
            case 2:
                //Login do usuário
                System.out.println("*===LOGIN===*");
                System.out.println("Entre com seu CPF:");
                cpf = sc.nextLine();
                do
                {
                    System.out.println("Entre com sua senha:");
                    senha = sc.nextLine();
                    if(usuarioDB.buscaCpf(cpf, senha))
                    {
                        senhaV = true;
                    }
                    else
                        System.out.println("Senha inválida. Digite novamente a senha!");
                }while (!senhaV);
                livraria.menuSecundario(usuarioDB, cpf, senha);
                break;
            case 3:
                //Sair da Livraria
                System.out.println("Você saiu!");
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }
}
