package com.spobrefy;
import java.text.ParseException;
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
//        if(!dialogLogin()) return;

        while(true) {
            if(!dialogFirstMenu()) break;
        }
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

    private Boolean dialogFirstMenu() {
        String roleLoggedUser = sistema.getLoggedUser().getClass().getSimpleName();

        System.out.println("+ Oque deseja fazer?\n| 1 - Ver Músicas\n| 2 - Ver Artistas\n| 3 - Ir para o perfil");
        if(roleLoggedUser.equals("User")) {
            System.out.println("| 4 - Deslogar");
        } else {
            System.out.println("| 4 - Área do "+ roleLoggedUser);
            System.out.println("| 5 - Deslogar");
        }
        int aux = scan.nextInt();
        scan.nextLine();
        System.out.println("=======================================================================");
        if(aux == 1) {
            sistema.showMusics();
            System.out.println("|");
            System.out.println("+ Deseja fazer algumas das seguintes ações com uma música de sua escolha?");
            System.out.println("=======================================================================");
        }
        if(aux == 2) {
            sistema.showArtists();
            System.out.println("|");
            System.out.println("+ Deseja fazer algumas das seguintes ações com um artista de sua escolha?");
            System.out.println("=======================================================================");
        }
        if(aux == 3) {
            sistema.getLoggedUser().print();
            dialogUpdateProperty();
            System.out.println("=======================================================================");
        }
        // sistema.getLoggedUser().getRoleMenuByPermission();
        // roleAction(RoleAction); // handler de acao por role que recebe um enum

        if(aux == 4 && roleLoggedUser.equals("User")) return false;

        if(aux == 4 && !roleLoggedUser.equals("User")) {
            switch (sistema.getLoggedUser().getClass().getSimpleName()){
                case "Artista": dialogArtistArea();
                    break;
                case "Admin": dialogAdminArea();
                    break;
            }
        }

        if(aux == 5) return false;

        return true;
    }

    private void dialogArtistArea() {

    }
    private void dialogAdminArea() {
        System.out.println("+ Menu de Ações:");
        System.out.println("| 1 - Excluir Usuário\n| 2 - Ver usuários\n| 3 - Banir Música\n| 4 - Solicitações de Upgrade");

        System.out.println("=======================================================================");
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
            }
        }

        if(aux == 2) {
            dialogFirstMenu();
        }
    }

}
