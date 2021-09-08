package com.oop.fo;

public class FileObj {

    private int fileId;
    private String fileName;
    private long fileSize;
    private String fileType;
    private String path;
    private int directoryId;


    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(int directoryId) {
        this.directoryId = directoryId;
    }

    public FileObj() {}

    public FileObj(int fileId, String fileName, long fileSize, String fileType, String path, int directoryId) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.path = path;
        this.directoryId = directoryId;
    }


}
