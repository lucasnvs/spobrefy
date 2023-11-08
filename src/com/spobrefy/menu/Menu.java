package com.spobrefy.menu;
import com.spobrefy.Sistema;
import com.spobrefy.model.Music;
import com.spobrefy.model.UpgradeRequest;
import com.spobrefy.model.UpgradeType;
import com.spobrefy.model.users.User;

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
            System.out.println("+ Deseja fazer algumas das seguintes ações com uma música de sua escolha?"); // TODO: detalheszitos
            System.out.println("=======================================================================");
        }
        if(aux == 2) {
            sistema.showArtists();
            System.out.println("|");
            System.out.println("+ Deseja fazer algumas das seguintes ações com um artista de sua escolha?");
            System.out.println("=======================================================================");
        }
        if(aux == 3) {
            System.out.println(sistema.getLoggedUser());
            dialogUpdateProperty();
            System.out.println("=======================================================================");
        }

        if(aux == 4 && roleLoggedUser.equals("User")) return false;

        if(aux == 4 && !roleLoggedUser.equals("User")) {
            switch (sistema.getLoggedUser().getClass().getSimpleName()){
                case "Artist": while(true) {
                    if(!dialogArtistArea()) break;
                }
                    break;
                case "Admin": while(true) {
                    if(!dialogAdminArea()) break;
                }
                    break;
            }
        }

        if(aux == 5) return false;

        return true;
    }

    private Boolean dialogArtistArea() {
        System.out.println("+ Menu de Ações:");
        System.out.println("| 1 - Publicar Música");
        System.out.println("| 2 - Voltar");
        int aux = scan.nextInt();
        scan.nextLine();
        if(aux == 1) {
            sistema.registerMusic();
        }
        if(aux == 2) {
            return false;
        }
        System.out.println("=======================================================================");

        return true;
    }
    private Boolean dialogAdminArea() {
        System.out.println("+ Menu de Ações:");
        System.out.println("| 1 - Excluir Usuário\n| 2 - Ver usuários\n| 3 - Banir Música\n| 4 - Solicitações de Upgrade\n| 5 - Voltar");
        int aux = scan.nextInt();
        scan.nextLine();
        if(aux == 1) {
            System.out.println("=======================================================================");
            System.out.println("+ Digite o Id do usuário que deseja remover do sistema:");
            int id = scan.nextInt();
            User user = sistema.getAllUsers().findById(id);
            sistema.getAllUsers().deleteById(id);
            System.out.println("=======================================================================");
            System.out.println("USUÁRIO DE ID "+id+" E NICKNAME "+user.getNickname()+" FOI REMOVIDO COM SUCESSO!");
            System.out.println("=======================================================================");
        }
        if(aux == 2) {
            sistema.showUsers();
        }
        if(aux == 3) {
            sistema.showMusics();
            System.out.println("=======================================================================");
            System.out.println("+ Digite o Id da música a ser banida:");
            int id = scan.nextInt();
            Music music = sistema.getAllMusics().findById(id);
            sistema.getAllMusics().deleteById(id);
            System.out.println("=======================================================================");
            System.out.println("A MÚSICA "+music.getName()+" DE "+music.getAuthor().getNickname()+" FOI BANIDA!");
            System.out.println("=======================================================================");
        }
        if(aux == 4) {
            sistema.showUpgradeRequests();
        }

        if(aux == 5) {
          return false;
        }

        System.out.println("=======================================================================");
        return true;
    }

    private Boolean dialogUpdateProperty() {
        System.out.println("+ Deseja atualizar alguma informação do seu Perfil?");
        System.out.println("| 1 - Atualizar");
        if(sistema.getLoggedUser().getClass().getSimpleName().equals("User")) {
            if(sistema.userSendedUpgradeRequest(sistema.getLoggedUser().getId())){
                System.out.println("| ! - Solicitação para se tornar Artista já enviada e esperando resposta...");
            }
            else {
                System.out.println("| 2 - Tornar-se Artista");
            }
            System.out.println("| 3 - Voltar para o início");
        } else {
            System.out.println("| 2 - Voltar para o início");
        }

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

        if(aux == 2 && !sistema.getLoggedUser().getClass().getSimpleName().equals("User")) return false;

        if(aux == 2 && sistema.getLoggedUser().getClass().getSimpleName().equals("User") && !sistema.userSendedUpgradeRequest(sistema.getLoggedUser().getId())) {
            UpgradeRequest request = new UpgradeRequest(sistema.getLoggedUser(), UpgradeType.USER_TO_ARTIST);

            sistema.sendUpgradeRequest(request);
            System.out.println("=======================================================================");
            System.out.println("Solicitação enviada com sucesso!");
            System.out.println("=======================================================================");
        }

        if(aux == 3) return false;

        return true;
    }

}
