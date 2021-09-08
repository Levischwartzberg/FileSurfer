package common.helpers;

public class FileType {

    public static String getFileType(String file) {
        return file.substring(file.length() - 3);
    }
}
