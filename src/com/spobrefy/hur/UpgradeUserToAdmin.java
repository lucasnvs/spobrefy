package com.spobrefy.hur;

import com.spobrefy.hur.IUpgradeTo;
import com.spobrefy.users.Admin;
import com.spobrefy.users.User;

public class UpgradeUserToAdmin implements IUpgradeTo<User, Admin> {

    @Override
    public Admin upgrade(User obj) {
        return null;
    }
}
