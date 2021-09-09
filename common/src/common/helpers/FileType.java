package common.helpers;

public class FileType {

    public static String getFileType(String file) {
        String[] filename = file.split("\\.");
        System.out.println(filename[filename.length-1]);
        return filename[filename.length-1];
    }
}
