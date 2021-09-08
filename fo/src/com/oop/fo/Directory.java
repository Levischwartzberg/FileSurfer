package com.oop.fo;

public class Directory {

    private int directoryId;
    private String dirName;
    private long dirSize;
    private int numberOfFiles;
    private String path;

    public int getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(int directoryId) {
        this.directoryId = directoryId;
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public long getDirSize() {
        return dirSize;
    }

    public void setDirSize(long dirSize) {
        this.dirSize = dirSize;
    }

    public int getNumberOfFiles() {
        return numberOfFiles;
    }

    public void setNumberOfFiles(int numberOfFiles) {
        this.numberOfFiles = numberOfFiles;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Directory(int directoryId, String dirName, long dirSize, int numberOfFiles, String path) {
        this.directoryId = directoryId;
        this.dirName = dirName;
        this.dirSize = dirSize;
        this.numberOfFiles = numberOfFiles;
        this.path = path;
    }

    public Directory() {

    }


}
