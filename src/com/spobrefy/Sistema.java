package com.spobrefy;
import java.util.ArrayList;
import java.util.Scanner;

import com.spobrefy.dao.UpgradeRequestsDAO;
import com.spobrefy.hur.UpgradeUserToAdmin;
import com.spobrefy.hur.UpgradeUserToArtist;
import com.spobrefy.model.Music;
import com.spobrefy.dao.ArtistsDAO;
import com.spobrefy.dao.MusicsDAO;
import com.spobrefy.dao.UsersDAO;
import com.spobrefy.model.UpgradeRequest;
import com.spobrefy.model.users.*;

import static com.spobrefy.menu.Menu.sysLine;
import static com.spobrefy.menu.Menu.sysMessage;
import static com.spobrefy.menu.TableGenerator.createTable;

public class Sistema {
    private final String sysName;
    private User loggedUser;
    private final Scanner scan = new Scanner(System.in);
    private final UsersDAO allUsers = UsersDAO.getInstance();
    private final ArtistsDAO allArtists = ArtistsDAO.getInstance();
    private final MusicsDAO allMusics = MusicsDAO.getInstance();
    private final UpgradeRequestsDAO allUpgradeRequests = UpgradeRequestsDAO.getInstance();

    public Sistema(String sysName) {
        this.sysName = sysName;
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

    public Boolean showUpgradeRequests() {
        if(allUpgradeRequests.findAll().size() == 0) {
            sysLine();
            System.out.println("NENHUMA SOLICITAÇÃO REGISTRADA...");
            return false;
        }

        ArrayList<ArrayList<String>> content = new ArrayList<>();

        System.out.println("| SOLICITAÇÕES DE UPGRADE DO "+sysName.toUpperCase());
        System.out.println("|");
        for( UpgradeRequest upgrade : allUpgradeRequests.findAll()) {
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
        return true;
    }
    public void registerUser() {
        User user = User.create(this.scan);
        this.allUsers.save(user);

        sysMessage("CONTA REGISTRADA COM SUCESSO!");
    }

    public void registerMusic() {
        Music newMusic = Music.create(this.scan, this.getLoggedUser().getId());
        this.allMusics.save(newMusic);

        sysMessage("SUA MÚSICA "+newMusic.getName()+" FOI PUBLICADA COM SUCESSO!");
    }

    public UpgradeRequestsDAO getUpgradeRequests() {
        return allUpgradeRequests;
    }

    public void sendUpgradeRequest(UpgradeRequest request) {
        allUpgradeRequests.save(request);
    }
    public Boolean userSendedUpgradeRequest(int idUser) {
        for(UpgradeRequest upgrade : allUpgradeRequests.findAll()) {
            if(upgrade.getSender().getId() == idUser) return true;
        }
        return false;
    }
    public Boolean setUpgradeRequestSystemAnswer(int idReqUpgrade, Boolean boolAnswer) {
        for(UpgradeRequest ur : allUpgradeRequests.findAll()) {
            if(ur.getId() != idReqUpgrade) continue;
            if(ur.getIsAnswered()) return false;
            ur.setIsAnswered(true);
            ur.setAnswer(boolAnswer);

            User upgradedUser;
            switch (ur.getType()) {
                case USER_TO_ARTIST -> upgradedUser = new UpgradeUserToArtist().upgrade(ur.getSender(), ur.getSenderCPF(), ur.getSenderBirthDate());
                case USER_TO_ADMIN -> upgradedUser = new UpgradeUserToAdmin().upgrade(ur.getSender(), ur.getSenderCPF(), ur.getSenderBirthDate()); // TODO: falta o token
                default -> upgradedUser = null;
            }
            allUpgradeRequests.update(ur);
            allUsers.update(upgradedUser);
            return true;
        }
        return false;
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
            sysLine();
            System.out.println("+ Qual o seu nickname?");
            String nick = this.scan.nextLine();
            System.out.println("+ Qual sua senha?");
            String pass = this.scan.nextLine();

            if(!loginVerify(nick, pass)) {
                sysMessage("SENHA OU NICKNAME INCORRETOS!");
                System.out.println("| Deseja tentar novamente?\n| 1 - Tentar novamente\n| 2 - Desistir...");
                sysLine();
                aux = this.scan.nextInt();

                if(aux == 1) {
                    this.scan.nextLine();
                    return login();
                }
                if(aux == 2) {
                    return false;
                }
            }

            sysMessage("LOGADO COM SUCESSO! SEJA BEM VINDO DE VOLTA "+ nick);
            return true;
    }
}