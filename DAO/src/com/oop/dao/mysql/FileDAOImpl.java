package com.oop.dao.mysql;

import com.oop.dao.FileDAO;
import com.oop.fo.FileObj;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileDAOImpl extends MySQL implements FileDAO {
    @Override
    public FileObj getFileById(int fileId) {
        Connect();
        FileObj file = null;

        try {
            String sp = "{call GetFile(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1,GET_BY_ID);
            cStmt.setInt(2,fileId);
            ResultSet rs = cStmt.executeQuery();

            if(rs.next()) {
                file = HydrateFile(rs);
            }
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return file;
    }

    @Override
    public List<FileObj> getFileList() {
        Connect();
        List<FileObj> fileList = new ArrayList<FileObj>();

        try {
            String sp = "{call GetFile(?,?)}";
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1,GET_COLLECTION);
            cStmt.setInt(2,0);
            ResultSet rs = cStmt.executeQuery();

            while(rs.next()) {
                FileObj file = HydrateFile(rs);

                fileList.add(file);
            }
        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return fileList;
    }

    @Override
    public int insertFile(FileObj file) {
        return ExecFile(file, INSERT);
    }

    @Override
    public boolean updateFile(FileObj file) {
        return ExecFile(file, UPDATE) > 0;
    }

    @Override
    public boolean deleteFile(int fileId) {
        return ExecFile(getFileById(fileId), DELETE) > 0;
    }

    public static FileObj HydrateFile(ResultSet rs) throws SQLException {
        FileDAO fileDAO = new FileDAOImpl();

        FileObj file = new FileObj();
        file.setFileId(rs.getInt(1));
        file.setFileName(rs.getString(2));
        file.setFileSize(rs.getLong(3));
        file.setFileType(rs.getString(4));
        file.setPath(rs.getString(5));
        file.setDirectoryId(rs.getInt(6));

        return file;
    }

    public static int ExecFile(FileObj file, int operation) {
        Connect();
        int id = 0;

        try {
            String sp = "{call ExecFile(?,?,?,?,?,?,?)}";

            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setInt(1,operation);
            cStmt.setInt(2,file.getFileId());
            cStmt.setString(3,file.getFileName());
            cStmt.setLong(4,file.getFileSize());
            cStmt.setString(5,file.getFileType());
            cStmt.setString(6,file.getPath());
            cStmt.setInt(7,file.getDirectoryId());

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
            String sp = "{call CLearFiles}";
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.executeQuery();

        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }

    }

    @Override
    public List<FileObj> Select5LargestFiles() {
        Connect();
        List<FileObj> fileList = new ArrayList<FileObj>();
        try {
            String sp = "{call Select5LargestFiles}";
            CallableStatement cStmt = connection.prepareCall(sp);
            ResultSet rs = cStmt.executeQuery();

            while(rs.next()) {
                FileObj file = HydrateFile(rs);

                fileList.add(file);
            }

        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return fileList;
    }

    public List<FileObj> SelectByFileType(String fileType) {
        Connect();
        List<FileObj> fileList = new ArrayList<FileObj>();
        try {
            String sp = "{call SelectByFileType(?)}";
            CallableStatement cStmt = connection.prepareCall(sp);
            cStmt.setString(1,fileType);
            ResultSet rs = cStmt.executeQuery();

            while(rs.next()) {
                FileObj file = HydrateFile(rs);

                fileList.add(file);
            }

        } catch (SQLException sqlEx) {
            logger.error(sqlEx);
        }
        return fileList;
    }
}
