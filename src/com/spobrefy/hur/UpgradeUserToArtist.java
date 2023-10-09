package com.spobrefy.hur;

import com.spobrefy.hur.IUpgradeTo;
import com.spobrefy.users.Artist;
import com.spobrefy.users.User;

public class UpgradeUserToArtist implements IUpgradeTo<User, Artist> {

    @Override
    public Artist upgrade(User obj) {
        // remover da lista que ele pertence e add na lista de artistas, nao sei se faço por aq ou no metodo do sistema
        String birthDate = "";
        String cpf = "";
        Artist newArtist = new Artist(obj.getId(), obj.getNickname(), obj.getEmail(), obj.getPassword(), cpf, birthDate);
        return newArtist;
    }
}
