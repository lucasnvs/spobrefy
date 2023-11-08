package com.spobrefy.hur;

import com.spobrefy.model.users.Admin;
import com.spobrefy.model.users.User;

public class UpgradeUserToAdmin implements IUpgradeTo<User, Admin> {

    @Override
    public Admin upgrade(User obj) {
        return null;
    }
}
