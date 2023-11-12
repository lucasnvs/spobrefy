package com.spobrefy.fdata;

import com.spobrefy.shared.IAbleToSave;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

abstract class FileHandler {
    private static final String mainPath = String.join(File.separator, Arrays.asList(".","src","com","spobrefy","fdata","files_data"));
    public static ArrayList<String> readFileData(String fileName) {
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

    public static void writeFileData(String fileName, String newLine) {
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

    public static void updateFileData(String fileName, IAbleToSave obj) {
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

        for(String line : FileHandler.readFileData(fileName)) {
            String[] values = line.split(spliter);

            if(Integer.parseInt(values[0]) == obj.getId()) {
                ps.println(obj.toCsvString()); // única diferença
            } else {
                ps.println(line);
            }
        }
        ps.close();

        String oldPath = mainPath+File.separator+fileName;
        File oldFile = new File(oldPath);
        oldFile.delete();

        tempFile.renameTo(new File(oldPath));
    }

    public static void deleteFileData(String fileName, IAbleToSave obj) {
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

        for(String line : FileHandler.readFileData(fileName)) {
            String[] values = line.split(spliter);

            if(Integer.parseInt(values[0]) != obj.getId()) {
                ps.println(line);
            }
        }
        ps.close();

        String oldPath = mainPath+File.separator+fileName;
        File oldFile = new File(oldPath);
        oldFile.delete();

        tempFile.renameTo(new File(oldPath));
    }
}
