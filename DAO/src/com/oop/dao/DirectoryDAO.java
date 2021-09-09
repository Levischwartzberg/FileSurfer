package com.oop.dao;

import com.oop.fo.Directory;

import java.util.List;

public interface DirectoryDAO {
    public Directory getDirectoryById(int directoryId);
    public List<Directory> getDirectoryList();

    public int insertDirectory(Directory directory);
    public boolean updateDirectory(Directory directory);
    public boolean deleteDirectory(int directoryId);
    public void clearTable();
    public Directory displayPopulousDirectory();
    public Directory displayLargestDirectory();
    public Directory getMostRecentDirectory();
}
