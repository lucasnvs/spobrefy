package com.spobrefy.menu;
import com.spobrefy.Sistema;
import com.spobrefy.model.Music;
import com.spobrefy.model.UpgradeRequest;
import com.spobrefy.model.UpgradeType;
import com.spobrefy.model.users.User;

import java.lang.reflect.Array;
import java.util.*;


public class Menu {
    public final Scanner scan = new Scanner(System.in);
    private final Sistema sistema;

    public Menu(Sistema sistema) {
        this.sistema = sistema;
    }

    public void init() {
        sysLine();
        System.out.println("Olá querido usuário, para acessar o sistema será necessário logar:");

        while (true) {
            if(!dialogLogin()) break;
            while(true) {
                if(!dialogFirstMenu()) break;
            }
        }

    }
    public static void sysLine() {
        System.out.println("=======================================================================");
    }
    public static void sysMessage(String message) {
        sysLine();
        System.out.println(message);
        sysLine();
    }

    private int optionSelectHandler(List<Integer> options) {
        int option = -1;
        do {
            try {
                System.out.print("Ação: ");
                option = scan.nextInt();
                if(!options.contains(option)){
                    option = -1;
                    sysMessage("Por favor escolha valores entre as opções disponíveis!");
                }
            } catch (InputMismatchException e) {
                sysMessage("Por favor escolha valores entre as opções disponíveis!");
            }

            scan.nextLine();
        }while (option == -1);

        return option;
    }
    // TODO: fazer uma operação de verificar somente o tipo do input de scan para n dar erro!
    private Boolean dialogLogin() {
        System.out.println("| 1 - Logar\n| 2 - Não possuo conta\n| 3 - SAIR DO SISTEMA");

        List<Integer> options = Arrays.asList(1, 2, 3);

        int option = optionSelectHandler(options);
        switch (option) {
            case 1 -> {
                return sistema.login();
            }
            case 2 -> {
                sistema.registerUser();
                return sistema.login();
            }
            default -> {
                return false;
            }
        }
    }

    private Boolean dialogFirstMenu() {
        String roleLoggedUser = sistema.getLoggedUser().getClass().getSimpleName();
        System.out.println(":: Logado como "+sistema.getLoggedUser().getNickname());
        System.out.println("+ Oque deseja fazer?\n| 1 - Ver Músicas\n| 2 - Ver Artistas\n| 3 - Ir para o perfil");
        if(roleLoggedUser.equals("User")) {
            System.out.println("| 4 - Deslogar");
        } else {
            System.out.println("| 4 - Área do "+ roleLoggedUser);
            System.out.println("| 5 - Deslogar");
        }

        List<Integer> options = Arrays.asList(1, 2, 3, 4, 5);
        int option = optionSelectHandler(options);
        sysLine();
        switch (option) {
            case 1 -> {
                sistema.showMusics();
                System.out.println("|");
                System.out.println("+ Deseja fazer algumas das seguintes ações com uma música de sua escolha?"); // TODO: detalheszitos
                sysLine();
            }
            case 2 -> {
                sistema.showArtists();
                System.out.println("|");
                System.out.println("+ Deseja fazer algumas das seguintes ações com um artista de sua escolha?");
                sysLine();
            }
            case 3 -> {
                while (true) {
                    System.out.println(sistema.getLoggedUser());
                    if (!dialogUpdateProperty()) break;
                    sysLine();
                }
            }
        }

        if(option == 4 && roleLoggedUser.equals("User")) return false;
        if(option == 4) {
            switch (sistema.getLoggedUser().getClass().getSimpleName()) {
                case "Artist" -> {
                    while (true) {
                        if (!dialogArtistArea()) break;
                    }
                }
                case "Admin" -> {
                    while (true) {
                        if (!dialogAdminArea()) break;
                    }
                }
            }
        }

        return option != 5;
    }

    private Boolean dialogArtistArea() {
        System.out.println("+ Menu de Ações:");
        System.out.println("| 1 - Publicar Música");
        System.out.println("| 2 - Voltar");

        List<Integer> options = Arrays.asList(1, 2);
        int option = optionSelectHandler(options);

        switch (option) {
            case 1 -> sistema.registerMusic();
            case 2 -> {
                return false;
            }
        }
        sysLine();
        return true;
    }
    private Boolean dialogAdminArea() {
        System.out.println("+ Menu de Ações:");
        System.out.println("| 1 - Excluir Usuário\n| 2 - Ver usuários\n| 3 - Banir Música\n| 4 - Solicitações de Upgrade\n| 5 - Voltar");

        List<Integer> options = Arrays.asList(1, 2, 3, 4, 5);
        int option = optionSelectHandler(options);
        switch (option) {
            case 1 -> {
                sysLine();
                System.out.println("+ Digite o Id do usuário que deseja remover do sistema:");
                int id = scan.nextInt();
                User user = sistema.getAllUsers().findById(id);
                sistema.getAllUsers().deleteById(id);
                sysMessage("USUÁRIO DE ID "+id+" E NICKNAME "+user.getNickname()+" FOI REMOVIDO COM SUCESSO!");
            }
            case 2 -> sistema.showUsers();
            case 3 -> {
                sistema.showMusics();
                sysLine();
                System.out.println("+ Digite o Id da música a ser banida:");
                int id = scan.nextInt();
                Music music = sistema.getAllMusics().findById(id);
                sistema.getAllMusics().deleteById(id);
                sysMessage("A MÚSICA "+music.getName()+" DE "+music.getAuthor().getNickname()+" FOI BANIDA!");
            }
            case 4 -> {
                sistema.showUpgradeRequests();
                sysLine();
                System.out.println("+ Deseja responder algum upgrade ?");
                System.out.println("| 1 - SIM\n| 2 - NÃO");
                options = Arrays.asList(1, 2);

                option = optionSelectHandler(options);

                switch (option) {
                    case 1 -> {
                        sysLine();
                        System.out.println("+ Digite o Id de Upgrade a responder:");
                        int id = scan.nextInt();
                        System.out.println("+ Resposta:");
                        System.out.println("| 1 - SIM\n| 2 - NÃO");
                        options = Arrays.asList(1, 2);
                        int optionAnswer = optionSelectHandler(options);
                        if(sistema.setUpgradeRequestSystemAnswer(id, optionAnswer == 1)) {
                            sysMessage("RESPOSTA ENVIADA COM SUCESSO!");
                        } else {
                            sysMessage("NÃO FOI POSSÍVEL REALIZAR ESSA AÇÃO!");
                        }
                    }
                    case 2 -> {
                        return true;
                    }
                }
            }
            case 5 -> {
                return false;
            }
        }

        sysLine();
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

        List<Integer> options = Arrays.asList(1, 2, 3);
        int aux = optionSelectHandler(options);
        sysLine();

        if(aux == 1) {
            System.out.println("+ Escolha qual propriedade deseja mudar.");
            System.out.println("| 1 - Nickname \n| 2 - Senha\n| 3 - Email\n| 4 - Voltar");
            List<Integer> optionsChange = Arrays.asList(1, 2, 3, 4);
            aux = optionSelectHandler(optionsChange);

            switch (aux) {
                case 1 -> {
                    System.out.println("+ Digite o seu novo nickname abaixo:");
                    String newNick = scan.nextLine();
                    sistema.getLoggedUser().setNickname(newNick);
                    sistema.getAllUsers().update(sistema.getLoggedUser());
                    sysMessage("NICKNAME ALTERADO COM SUCESSO PARA "+newNick);
                }
                case 2 -> {
                    System.out.println("+ Para trocar de senha será necessário confirmar sua antiga senha!\n+ Digite abaixo sua antiga senha:");
                    String lastPass = scan.nextLine();
                    System.out.println("+ Agora digite sua nova senha:");
                    String newPass = scan.nextLine();
                    if(sistema.getLoggedUser().changePassword(lastPass, newPass)) {
                        sistema.getAllUsers().update(sistema.getLoggedUser());
                        sysMessage("SENHA ALTERADA COM SUCESSO!");
                        return true;
                    }
                    sysMessage("ALGO DEU ERRADO... Repita o processo e tente novamente.");
                    dialogUpdateProperty();
                }
                case 3 -> {
                    System.out.println("+ Digite o seu novo email abaixo:");
                    String newEmail = scan.nextLine();
                    sistema.getLoggedUser().setEmail(newEmail);
                    sysMessage("EMAIL ALTERADO COM SUCESSO PARA "+newEmail);
                }
                case 4 -> {
                    return true;
                }
            }
        }

        if(aux == 2 && !sistema.getLoggedUser().getClass().getSimpleName().equals("User")) return false;

        if(aux == 2 && sistema.getLoggedUser().getClass().getSimpleName().equals("User") && !sistema.userSendedUpgradeRequest(sistema.getLoggedUser().getId())) {
            UpgradeRequest request = new UpgradeRequest(sistema.getLoggedUser(), UpgradeType.USER_TO_ARTIST);

            sistema.sendUpgradeRequest(request);
            sysMessage("Solicitação enviada com sucesso!");
        }

        return aux != 3;
    }
}