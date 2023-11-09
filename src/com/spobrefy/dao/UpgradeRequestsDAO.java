package com.spobrefy.dao;

import com.spobrefy.model.UpgradeRequest;
import com.spobrefy.model.UpgradeType;

import java.util.ArrayList;
import java.util.List;

public class UpgradeRequestsDAO implements IDao<UpgradeRequest> {
    private static final UpgradeRequestsDAO instance = new UpgradeRequestsDAO();
    private final ArrayList<UpgradeRequest> upgradeRequestsList;

    public UpgradeRequestsDAO() {
        upgradeRequestsList = new ArrayList<>();
        upgradeRequestsList.add(new UpgradeRequest(UsersDAO.getInstance().findById(5), UpgradeType.USER_TO_ARTIST));
    }

    public static UpgradeRequestsDAO getInstance() {
        return instance;
    }
    @Override
    public UpgradeRequest findById(int id) {
        for (UpgradeRequest u : upgradeRequestsList) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }
    @Override
    public List<UpgradeRequest> findAll() {
        return upgradeRequestsList;
    }

    @Override
    public void save(UpgradeRequest object) {
        upgradeRequestsList.add(object);
    }

    @Override
    public void update(UpgradeRequest object) {
        UpgradeRequest ur = findById(object.getId());
        delete(ur);
        save(object);
    }

    @Override
    public void delete(UpgradeRequest object) {
        upgradeRequestsList.remove(object);
    }

    @Override
    public void deleteById(int id) {
        delete(findById(id));
    }
}
