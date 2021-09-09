package com.oop.dao.mysql;

import com.oop.dao.DirectoryDAO;
import com.oop.fo.Directory;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectoryDAOImpl extends MySQL implements DirectoryDAO {
    @Override
    public Directory getDirectoryById(int directoryId) {
        Connect();
        Directory directory = null;

        try {
            String sp = "{call GetDirectory(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1,GET_BY_ID);
            cStmt.setInt(2,directoryId);
            ResultSet rs = cStmt.executeQuery();

            if(rs.next()) {
                directory = HydrateDirectory(rs);
            }
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return directory;
    }

    @Override
    public List<Directory> getDirectoryList() {
        Connect();
        List<Directory> directoryList = new ArrayList<Directory>();

        try {
            String sp = "{call GetDirectory(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1,GET_COLLECTION);
            cStmt.setInt(2,0);
            ResultSet rs = cStmt.executeQuery();

            while(rs.next()) {
                Directory directory = HydrateDirectory(rs);

                directoryList.add(directory);
            }
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return directoryList;
    }

    @Override
    public int insertDirectory(Directory directory) {
        return ExecDirectory(directory, INSERT);
    }

    @Override
    public boolean updateDirectory(Directory directory) {
        return ExecDirectory(directory, UPDATE) > 0;
    }

    @Override
    public boolean deleteDirectory(int directoryId) {
        return ExecDirectory(getDirectoryById(directoryId), DELETE) > 0;
    }

    public static Directory HydrateDirectory(ResultSet rs) throws SQLException {
        DirectoryDAO directoryDAO = new DirectoryDAOImpl();

        Directory directory = new Directory();
        directory.setDirectoryId(rs.getInt(1));
        directory.setDirName(rs.getString(2));
        directory.setDirSize(rs.getLong(3));
        directory.setNumberOfFiles(rs.getInt(4));
        directory.setPath(rs.getString(5));

        return directory;
    }

    public static int ExecDirectory(Directory directory, int operation) {
        Connect();
        int id = 0;

        try {
            String sp = "{call ExecDirectory(?,?,?,?,?,?)}";

            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1,operation);
            cStmt.setInt(2,directory.getDirectoryId());
            cStmt.setString(3,directory.getDirName());
            cStmt.setLong(4,directory.getDirSize());
            cStmt.setInt(5,directory.getNumberOfFiles());
            cStmt.setString(6,directory.getPath());


            ResultSet rs = cStmt.executeQuery();
            if(rs.next()) {
                id = rs.getInt(1);
                return id;
            }
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return id;
    }

    @Override
    public void clearTable() {
        Connect();
        try {
            String sp = "{call ClearDirectory}";
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.executeQuery();

        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }

    }

    @Override
    public Directory displayPopulousDirectory() {
        Connect();
        Directory directory = null;
        try {
            String sp = "{call DisplayMostPopulousDirectory}";
            CallableStatement cStmt = connection.prepareCall(sp);
            ResultSet rs = cStmt.executeQuery();

            if(rs.next()) {
                directory = HydrateDirectory(rs);
            }
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return directory;
    }

    @Override
    public Directory displayLargestDirectory() {
        Connect();
       Directory directory = null;
        try {
            String sp = "{call DisplayLargestDirectory}";
            CallableStatement cStmt = connection.prepareCall(sp);
            ResultSet rs = cStmt.executeQuery();

            if(rs.next()) {
                directory = HydrateDirectory(rs);
            }

        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return directory;
    }

    @Override
    public Directory getMostRecentDirectory() {
        Connect();
        Directory directory = null;
        try {
            String sp = "{call GetMostRecentDirectory}";
            CallableStatement cStmt = connection.prepareCall(sp);
            ResultSet rs = cStmt.executeQuery();

            if(rs.next()) {
                directory = HydrateDirectory(rs);
            }

        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return directory;
    }
}
