package com.spobrefy.hur;

import com.spobrefy.model.users.Artist;
import com.spobrefy.model.users.User;

public class UpgradeUserToArtist implements IUpgradeTo<User, Artist> {

    @Override
    public Artist upgrade(User obj, String cpf, String birthDate) {
        return new Artist(obj.getId(), obj.getNickname(), obj.getEmail(), obj.getPassword(), cpf, birthDate);
    }

}
