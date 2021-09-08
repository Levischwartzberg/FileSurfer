package com.oop.dao;

import com.oop.fo.FileObj;

import java.util.List;

public interface FileDAO {
    public FileObj getFileById(int fileId);
    public List<FileObj> getFileList();

    public int insertFile(FileObj file);
    public boolean updateFile(FileObj file);
    public boolean deleteFile(int fileId);
    public void clearTable();
    public List<FileObj> Select5LargestFiles();
    public List<FileObj> SelectByFileType(String fileType);
}
