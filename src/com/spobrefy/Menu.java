package com.spobrefy;
import java.util.Scanner;


public class Menu {
    public final Scanner scan = new Scanner(System.in);
    private final Sistema sistema;

    public Menu(Sistema sistema) {
        this.sistema = sistema;
    }

    public void init() {
        System.out.println("=======================================================================");
        System.out.println("Olá querido usuário, para acessar o sistema será necessário logar:");
        if(!dialogLogin()) return;

        dialogAction();
    }

    private Boolean dialogLogin() {
        int aux;
        System.out.println("| 1 - Logar\n| 2 - Não possuo conta");
        aux = scan.nextInt();
        scan.nextLine();

        if(aux == 1) {
            if(sistema.login()) return true;
        }

        if(aux == 2) {
            sistema.registerUser();
            return sistema.login();
        }

        return false;
    }

    private void dialogAction() {
        System.out.println("+ Oque deseja fazer?\n| 1 - Ver Músicas\n| 2 - Ver Artistas\n| 3 - Ir para o perfil\n| 4 - Deslogar");
        int aux = scan.nextInt();
        scan.nextLine();
        System.out.println("=======================================================================");
        if(aux == 1) {
            sistema.showMusics();
            System.out.println("|");
            System.out.println("+ Deseja fazer algumas das seguintes ações com uma música de sua escolha?");
            System.out.println("=======================================================================");
            dialogAction();
        }
        if(aux == 2) {
            sistema.showArtists();
            System.out.println("|");
            System.out.println("+ Deseja fazer algumas das seguintes ações com um artista de sua escolha?");
            System.out.println("=======================================================================");
            dialogAction();
        }
        if(aux == 3) {
            sistema.getLoggedUser().print();
            dialogUpdateProperty();
            System.out.println("=======================================================================");
        }
    }

    private void dialogUpdateProperty() {
        System.out.println("+ Deseja atualizar alguma informação do seu Perfil?");
        System.out.println("| 1 - Atualizar \n| 2 - Voltar para o início");
        int aux = scan.nextInt();
        scan.nextLine();
        System.out.println("=======================================================================");

        if(aux == 1) {
            System.out.println("+ Escolha qual propriedade deseja mudar.");
            System.out.println("| 1 - Nickname \n| 2 - Senha\n| 3 - Email");
            aux = scan.nextInt();
            scan.nextLine();
            if(aux == 1) {
                System.out.println("+ Digite o seu novo nickname abaixo:");
                String newNick = scan.nextLine();
                sistema.getLoggedUser().setNickname(newNick);
                System.out.println("=======================================================================");
                System.out.println("NICKNAME ALTERADO COM SUCESSO PARA "+newNick);
                System.out.println("=======================================================================");
                dialogAction();
            }

            if(aux == 2) {
                System.out.println("+ Para trocar de senha será necessário confirmar sua antiga senha!\n+ Digite abaixo sua antiga senha:");
                String lastPass = scan.nextLine();
                System.out.println("+ Agora digite sua nova senha:");
                String newPass = scan.nextLine();
                if(sistema.getLoggedUser().changePassword(lastPass, newPass)) {
                    System.out.println("=======================================================================");
                    System.out.println("SENHA ALTERADA COM SUCESSO!");
                    System.out.println("=======================================================================");
                    dialogAction();
                }
                System.out.println("=======================================================================");
                System.out.println("ALGO DEU ERRADO... Repita o processo e tente novamente.");
                System.out.println("=======================================================================");
                dialogUpdateProperty();
            }

            if(aux == 3) {
                System.out.println("+ Digite o seu novo email abaixo:");
                String newEmail = scan.nextLine();
                sistema.getLoggedUser().setEmail(newEmail);
                System.out.println("=======================================================================");
                System.out.println("EMAIL ALTERADO COM SUCESSO PARA "+newEmail);
                System.out.println("=======================================================================");
                dialogAction();
            }
        }

        if(aux == 2) {
            dialogAction();
        }
    }

}
