package com.spobrefy.fdata;

import com.spobrefy.shared.IAbleToSave;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

abstract class FileHandler {
    private static final String mainPath = String.join(File.separator, Arrays.asList(".","src","com","spobrefy","fdata","files_data"));

    interface MiddleFunction {
        void run(String[] values, IAbleToSave obj, PrintStream ps, String line);
    }

    public void tempFileHandler(String fileName, IAbleToSave obj, MiddleFunction middleFunction) {
        String spliter = ";";
        String tempFileName = "temp_"+fileName;
        String path = mainPath+File.separator+tempFileName;
        File tempFile = new File(path);

        if (!tempFile.exists()) {
            try {
                tempFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        PrintStream ps;
        try {
            ps = new PrintStream(tempFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        for(String line : readFileData(fileName)) {
            String[] values = line.split(spliter);

            middleFunction.run(values, obj, ps, line);
        }
        ps.close();

        String oldPath = mainPath+File.separator+fileName;
        File oldFile = new File(oldPath);
        oldFile.delete();

        tempFile.renameTo(new File(oldPath));
    }

    public ArrayList<String> readFileData(String fileName) {
        ArrayList<String> totalLines = new ArrayList<>();

        String path =  mainPath+File.separator+fileName;
        File file = new File(path);

        if(!file.exists()) return new ArrayList<>();

        Scanner sc;
        try {
            sc = new Scanner(file);
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (sc.hasNext()) {
            String line = sc.nextLine();
            totalLines.add(line);
        }

        sc.close();

        return totalLines;
    }

    public void writeFileData(String fileName, String newLine) {
        String path =  mainPath+File.separator+fileName;
        File file = new File(path);

        if(!file.exists()) {
            try {
                file.createNewFile();
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        FileWriter fw;
        // TODO: Fazer opção para escrever do zero ou linha a linha
        try {
            fw = new FileWriter(file, true);
            fw.append(newLine).append("\n");
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateFileData(String fileName, IAbleToSave obj) {

        MiddleFunction updateMiddleFunction = (values, object, ps, line) -> {
            if(Integer.parseInt(values[0]) == object.getId()) {
                ps.println(object.toCsvString());
            } else {
                ps.println(line);
            }
        };

        tempFileHandler(fileName, obj, updateMiddleFunction);
    }

    public void deleteFileData(String fileName, IAbleToSave obj) {

        MiddleFunction deleteMiddleFunction = (values, object, ps, line) -> {
            if(Integer.parseInt(values[0]) != object.getId()) {
                ps.println(line);
            }
        };

        tempFileHandler(fileName, obj, deleteMiddleFunction);
    }
}
