package com.spobrefy.fdata;

import com.spobrefy.model.UpgradeRequest;
import com.spobrefy.model.UpgradeType;

import java.util.ArrayList;

public class UpgradeRequestFileHandler extends FileHandler implements IFileHandler<UpgradeRequest>{
    private static final String fileName = "upgradeRequests.csv";
    private static final UpgradeRequestFileHandler instance = new UpgradeRequestFileHandler();
    public static UpgradeRequestFileHandler getInstance() {
        return instance;
    }

    public ArrayList<UpgradeRequest> readFileData() {
        ArrayList<UpgradeRequest> upgrades = new ArrayList<>();
        String spliter = ";";

        for(String line : super.readFileData(fileName)) {
            String[] values = line.split(spliter);
            UpgradeRequest newUpgradeRequest = new UpgradeRequest(Integer.parseInt(values[0]), Integer.parseInt(values[1]), UpgradeType.valueOf(values[2]), Boolean.valueOf(values[3]), Boolean.valueOf(values[4]), values[5], values[6]);
            upgrades.add(newUpgradeRequest);
        }
        return upgrades;
    }

    @Override
    public void writeFileData(UpgradeRequest obj) {
        super.writeFileData(fileName, obj.toCsvString());
    }

    @Override
    public void updateFileData(UpgradeRequest obj) {
        super.updateFileData(fileName, obj);
    }

    @Override
    public void deleteFileData(UpgradeRequest obj) {
        super.deleteFileData(fileName, obj);
    }
}
