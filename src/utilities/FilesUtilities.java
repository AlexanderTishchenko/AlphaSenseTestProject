package utilities;

import core.BrowserDriver;

import java.io.File;

public class FilesUtilities {

    public static void removeAllDownloadedFiles() {
        File folder = new File(PathsUtil.GetDownloadFolder());
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles == null) return;
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                listOfFile.delete();
            }
        }
    }

    public static boolean isFileExist(BrowserDriver driver, String expectedFileName) {
        File folder = new File(PathsUtil.GetDownloadFolder());

        Wait.waitForFile(driver, folder);
        File[] listOfFiles = folder.listFiles();
        boolean found = false;

        if (listOfFiles == null) return false;
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                String fileName = listOfFile.getName();
                System.out.println("File " + listOfFile.getName());
                if (fileName.matches(expectedFileName)) {
                    found = true;
                }
            }
        }
        return found;
    }
}
