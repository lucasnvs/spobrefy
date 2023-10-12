package com.spobrefy;
import java.util.ArrayList;
import java.util.Scanner;

import com.spobrefy.model.Music;
import com.spobrefy.dao.ArtistsDAO;
import com.spobrefy.dao.MusicsDAO;
import com.spobrefy.dao.UsersDAO;
import com.spobrefy.model.UpgradeRequest;
import com.spobrefy.model.UpgradeType;
import com.spobrefy.model.users.*;

import static com.spobrefy.menu.TableGenerator.createTable;

public class Sistema {
    private final String sysName;
    private User loggedUser;
    private final Scanner scan = new Scanner(System.in);
    private final UsersDAO allUsers = UsersDAO.getInstance();
    private final ArtistsDAO allArtists = ArtistsDAO.getInstance();
    private final MusicsDAO allMusics = MusicsDAO.getInstance();
    private ArrayList<UpgradeRequest> upgradeRequests;

    public Sistema(String sysName) {
        this.sysName = sysName;
        this.upgradeRequests = new ArrayList<>();
        sendUpgradeRequest(new UpgradeRequest(UsersDAO.getInstance().findById(5), UpgradeType.USER_TO_ARTIST)); // gambiarra pra add um de exemplo
    }

    public UsersDAO getAllUsers() {
        return allUsers;
    }

    public ArtistsDAO getAllArtists() {
        return allArtists;
    }

    public MusicsDAO getAllMusics() {
        return allMusics;
    }

    public User getLoggedUser() {
        if(loggedUser == null) return UsersDAO.getInstance().findById(999);

        return loggedUser;
    }

    public void showUsers() {
        ArrayList<ArrayList<String>> content = new ArrayList<>();

        System.out.println("| USUÁRIOS DO "+sysName.toUpperCase());
        System.out.println("|");
        for(User user : allUsers.findAll()) {
            ArrayList<String> line = new ArrayList<>();
            line.add(String.valueOf(user.getId()));
            line.add(user.getNickname());
            line.add(user.getClass().getSimpleName());
            content.add(line);
        }
        ArrayList<String> header = new ArrayList<>();
        header.add("ID");
        header.add("NOME");
        header.add("ROLE");

        String table = createTable(header, content);
        System.out.println(table);
    }

    public void showArtists() {
        ArrayList<ArrayList<String>> content = new ArrayList<>();

        System.out.println("| ARTISTAS DO "+sysName.toUpperCase());
        System.out.println("|");
        for(Artist artist : allArtists.findAll()) {
            ArrayList<String> line = new ArrayList<>();
            line.add(String.valueOf(artist.getId()));
            line.add(artist.getNickname());
            line.add(String.valueOf(artist.getAuthoredPlaylist().size()));
            content.add(line);
        }
        ArrayList<String> header = new ArrayList<>();
        header.add("ID");
        header.add("NOME");
        header.add("MÚSICAS POSTADAS");

        String table = createTable(header, content);
        System.out.println(table);
    }

    public void showMusics() {
        ArrayList<ArrayList<String>> content = new ArrayList<>();

        System.out.println("| MÚSICAS DO "+sysName.toUpperCase());
        System.out.println("|");
        for( Music music : allMusics.findAll()) {
            ArrayList<String> line = new ArrayList<>();
            line.add(String.valueOf(music.getId()));
            line.add(music.getName());
            line.add(music.getAuthor().getNickname());
            content.add(line);
        }
        ArrayList<String> cabecalho = new ArrayList<>();
        cabecalho.add("ID");
        cabecalho.add("NOME");
        cabecalho.add("AUTHOR");

        String tabela = createTable(cabecalho, content);
        System.out.println(tabela);
    }

    public void showUpgradeRequests() {
        if(upgradeRequests.size() == 0) {
            System.out.println("=======================================================================");
            System.out.println("NENHUMA SOLICITAÇÃO REGISTRADA...");
            return;
        }

        ArrayList<ArrayList<String>> content = new ArrayList<>();

        System.out.println("| SOLICITAÇÕES DE UPGRADE DO "+sysName.toUpperCase());
        System.out.println("|");
        for( UpgradeRequest upgrade : getUpgradeRequests()) {
            ArrayList<String> line = new ArrayList<>();
            line.add(String.valueOf(upgrade.getId()));
            line.add(String.valueOf(upgrade.getSender().getId()));
            line.add(upgrade.getSender().getNickname());
            line.add(upgrade.getType().toString());

            if(upgrade.getIsAnswered()) {
                line.add("RESPONDIDO");
            } else {
                line.add("NÃO RESPONDIDO");
            }
            if(upgrade.getAnswer()) {
                line.add("SIM");
            } else {
                line.add("NÃO");
            }

            content.add(line);
        }
        ArrayList<String> cabecalho = new ArrayList<>();
        cabecalho.add("ID");
        cabecalho.add("ID USUÁRIO");
        cabecalho.add("NOME DO USUÁRIO");
        cabecalho.add("TIPO");
        cabecalho.add("STATUS");
        cabecalho.add("RESPOSTA");

        String tabela = createTable(cabecalho, content);
        System.out.println(tabela);
    }
    public void registerUser() {
        User user = User.create(this.scan);
        this.allUsers.save(user);

        System.out.println("=======================================================================");
        System.out.println("CONTA REGISTRADA COM SUCESSO!");
        System.out.println("=======================================================================");
    }

    public void registerMusic() {
        Music newMusic = Music.create(this.scan, this.getLoggedUser().getId());
        this.allMusics.save(newMusic);

        System.out.println("=======================================================================");
        System.out.println("SUA MÚSICA "+newMusic.getName()+" FOI PUBLICADA COM SUCESSO!");
        System.out.println("=======================================================================");
    }

    public ArrayList<UpgradeRequest> getUpgradeRequests() {
        return upgradeRequests;
    }

    public void sendUpgradeRequest(UpgradeRequest request) {
        upgradeRequests.add(request);
    }
    public Boolean userSendedUpgradeRequest(int idUser) {
        for(UpgradeRequest upgrade : upgradeRequests) {
            if(upgrade.getSender().getId() == idUser) return true;
        }
        return false;
    }
    public void upgradeUser(User user) {
        // allUsers.update();// usuario upgradado
    }

    private boolean loginVerify(String nick, String pass) {
        for (User user : allUsers.findAll()) {
            if (user.getNickname().equals(nick) && user.getPassword().equals(pass)) {
                loggedUser = user;
                return true;
            }
        }
        return false;
    }

    public Boolean login() {
            int aux;
            System.out.println("LOGIN DE USUÁRIO:");
            System.out.println("=======================================================================");
            System.out.println("+ Qual o seu nickname?");
            String nick = this.scan.nextLine();
            System.out.println("+ Qual sua senha?");
            String pass = this.scan.nextLine();

            if(!loginVerify(nick, pass)) {
                System.out.println("=======================================================================");
                System.out.println("SENHA OU NICKNAME INCORRETOS!");
                System.out.println("=======================================================================");
                System.out.println("| Deseja tentar novamente?\n| 1 - Tentar novamente\n| 2 - Desistir...");
                System.out.println("=======================================================================");
                aux = this.scan.nextInt();

                if(aux == 1) {
                    this.scan.nextLine();
                    return login();
                }
                if(aux == 2) {
                    return false;
                }
            }
            
            System.out.println("=======================================================================");      
            System.out.println("LOGADO COM SUCESSO! SEJA BEM VINDO DE VOLTA "+ nick);
            System.out.println("=======================================================================");
            return true;
    }
}