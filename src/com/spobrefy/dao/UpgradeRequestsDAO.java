package com.spobrefy.dao;

import com.spobrefy.fdata.UpgradeRequestFileHandler;
import com.spobrefy.model.UpgradeRequest;

import java.util.ArrayList;

public class UpgradeRequestsDAO implements IDao<UpgradeRequest> {
    private static final UpgradeRequestsDAO instance = new UpgradeRequestsDAO();

    public static UpgradeRequestsDAO getInstance() {
        return instance;
    }

    @Override
    public int getLastId() {
        ArrayList<UpgradeRequest> list = this.findAll();
        if (list.size() == 0) {
            return 0;
        }
        return list.get(list.size() - 1).getId();
    }
    @Override
    public UpgradeRequest findById(int id) {
        for (UpgradeRequest u : findAll()) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }
    @Override
    public ArrayList<UpgradeRequest> findAll() {
        return UpgradeRequestFileHandler.getInstance().readData();
    }

    @Override
    public void save(UpgradeRequest object) {
        if(object == null) return;
        UpgradeRequestFileHandler.getInstance().writeData(object);
    }

    @Override
    public void update(UpgradeRequest object) {
        if(object == null) return;
        UpgradeRequestFileHandler.getInstance().updateData(object);
    }

    @Override
    public void delete(UpgradeRequest object) {
        if(object == null) return;
        UpgradeRequestFileHandler.getInstance().removeData(object);
    }

    @Override
    public void deleteById(int id) {
        delete(findById(id));
    }
}
