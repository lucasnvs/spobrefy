package com.spobrefy.fdata;

import com.spobrefy.model.UpgradeRequest;
import com.spobrefy.model.UpgradeType;

import java.util.ArrayList;

public class UpgradeRequestFileHandler implements IFileHandler<UpgradeRequest>{
    private static final String fileName = "upgradeRequests.csv";
    private static final UpgradeRequestFileHandler instance = new UpgradeRequestFileHandler();
    public static UpgradeRequestFileHandler getInstance() {
        return instance;
    }

    @Override
    public ArrayList<UpgradeRequest> readData() {
        ArrayList<UpgradeRequest> upgrades = new ArrayList<>();
        String spliter = ";";

        for(String line : FileHandler.readFileData(fileName)) {
            String[] values = line.split(spliter);
            UpgradeRequest newUpgradeRequest = new UpgradeRequest(Integer.parseInt(values[0]), Integer.parseInt(values[1]), UpgradeType.valueOf(values[2]), Boolean.valueOf(values[3]), Boolean.valueOf(values[4]), values[5], values[6]);
            upgrades.add(newUpgradeRequest);
        }
        return upgrades;
    }

    @Override
    public void writeData(UpgradeRequest obj) {
        FileHandler.writeFileData(fileName, obj.toCsvString());
    }

    @Override
    public void updateData(UpgradeRequest obj) {
        FileHandler.updateFileData(fileName, obj);
    }

    @Override
    public void removeData(UpgradeRequest obj) {
        FileHandler.deleteFileData(fileName, obj);
    }
}
