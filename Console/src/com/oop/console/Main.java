package com.oop.console;

import com.oop.dao.DirectoryDAO;
import com.oop.dao.FileDAO;
import com.oop.dao.mysql.DirectoryDAOImpl;
import com.oop.dao.mysql.FileDAOImpl;
import com.oop.fo.Directory;
import com.oop.fo.FileObj;
import com.oop.fo.Test;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class Main {
    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
	// write your code here
//        System.out.println("test");

//        Test test = new Test();
//        test.Test();

//        recursiveFiles(new File("/home/levi/IdeaProjects/com.oop.assessment"));
        getInput();
        DisplayMenuOptions();
    }

    private static void recursiveFiles(File dir, int dirCount) {
        System.out.println(dirCount);
        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                dirCount++;
                File[] fileList = file.listFiles();
                if (file.isDirectory()) {
                    Directory directory = new Directory();
                    directory.setDirName(file.getName());
                    directory.setDirSize(getFolderSize(file));
                    directory.setPath(file.getCanonicalPath());
                    directory.setNumberOfFiles(fileList.length);

                    DirectoryDAO directoryDAO = new DirectoryDAOImpl();
                    directoryDAO.insertDirectory(directory);

                    System.out.println("Directory: " + file.getCanonicalPath() + "  " + file.getName() + "  " + file.length());
                    recursiveFiles(file, dirCount);
                }
                else {
                    System.out.println("File: " + file.getCanonicalPath()  + "  " + file.getName()  + "  " + file.length());
                    FileObj fileObj = new FileObj();

                    fileObj.setFileName(file.getName());
                    fileObj.setFileSize(file.length());
                    fileObj.setFileType(common.helpers.FileType.getFileType(file.getName()));
                    fileObj.setPath(file.getCanonicalPath());
                    fileObj.setDirectoryId(dirCount);

                    FileDAO fileDAO = new FileDAOImpl();
                    fileDAO.insertFile(fileObj);
                }
            }
        } catch (IOException ioEX) {
            System.out.println("Feck");
        }
    }

    private static void getInput() {
        clearDB();
        System.out.println("Enter in a directory name.");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        recursiveFiles(new File(input), 1);
    }

    private static void clearDB() {
        FileDAO fileDAO = new FileDAOImpl();
        DirectoryDAO directoryDAO = new DirectoryDAOImpl();

        directoryDAO.clearTable();
        fileDAO.clearTable();
    }

    private static void DisplayMenuOptions() {
        System.out.println("You have entered into the database all the files and directories starting from you specified directory.");
        System.out.println("Choose from the following options to interact with the database (enter option number).");

        System.out.println("---------------------");
        System.out.println("1: Display Directory with the most files");
        System.out.println("2: Display Directory with largest size");
        System.out.println("3: Display 5 largest files");
        System.out.println("4: Display all files of certain type");
        System.out.println("5: Clear Database");
        System.out.println("6: Exit");

        Scanner input = new Scanner(System.in);
        int selection = input.nextInt();

        if (selection == 1) {
            DirectoryDAO directoryDAO = new DirectoryDAOImpl();
            Directory directory = directoryDAO.displayPopulousDirectory();
            System.out.println(directory.getDirName());
        }
        if (selection == 2) {
            DirectoryDAO directoryDAO = new DirectoryDAOImpl();
            Directory directory = directoryDAO.displayLargestDirectory();
            System.out.println(directory.getDirName());
        }
        if (selection == 3) {
            FileDAO fileDAO = new FileDAOImpl();
            List<FileObj> fileList = fileDAO.Select5LargestFiles();
            for (FileObj file : fileList) {
                System.out.println(file.getFileName());
            }
        }
        if (selection == 4) {
            System.out.println("Enter in a file type.");
            Scanner typeInput = new Scanner(System.in);
            String type = typeInput.nextLine();
            FileDAO fileDAO = new FileDAOImpl();
            List<FileObj> fileList = fileDAO.SelectByFileType(type);
            for (FileObj file : fileList) {
                System.out.println(file.getFileName());
            }
        }
        if (selection == 5) {
            clearDB();
        }
        if (selection == 6) {
            System.out.println("Exited Menu");
        }
    }

    private static long getFolderSize(File folder) {
        long length = 0;
        File[] files = folder.listFiles();

        int count = files.length;

        for (int i = 0; i < count; i++) {
            if (files[i].isFile()) {
                length += files[i].length();
            }
            else {
                length += getFolderSize(files[i]);
            }
        }
        return length;
    }
}
