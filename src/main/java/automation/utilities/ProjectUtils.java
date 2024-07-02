package automation.utilities;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * <b>PAGES : UTILITIES</b> [Project]: Project related Utils
 */
public class ProjectUtils {
    /**
     * <b>[Method]</b> - Archive HTML report<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality archives all the files stored under Source directory<br>
     * into Destination directory, which both are paths passed as parameters from outside<br>
     *
     * @param destinationFolder File/io
     * @param sourceFolder      File/io
     */
    public void archiveReport(File destinationFolder, File sourceFolder) {
        // Check if destination folder exists, if not then create destination path
        if (!destinationFolder.mkdirs()) {
            System.out.println("ERROR: Cannot create destination archive folder!");
        }

        // Check weather source exists and it is folder.
        if (sourceFolder.exists() && sourceFolder.isDirectory()) {
            // Get list of the files and iterate over them
            File[] listOfFiles = sourceFolder.listFiles();

            // Iterate through list of files in source
            if (listOfFiles != null) {
                for (File file : listOfFiles) {
                    // Move files to destination folder
                    if (!file.getName().equals("archive")) {
                        try {
                            FileUtils.copyFileToDirectory(file, destinationFolder);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        } else {
            System.out.println("ERROR: Source does not exist or it is not directory: " + sourceFolder);
        }
    }

    /**
     * Directory Clean up method is working after each execution and if numOfFoldersToRemain > current count of
     * folders/files than it is deleting directories till count of numOfFoldersToRemain
     *
     * @param directory            which archive folder to delete
     * @param numOfFoldersToRemain number of folders will remain after deletion
     */
    public void directoryCleanUp(File directory, int numOfFoldersToRemain) {
        File[] listOfFolders = directory.listFiles();
        if (Objects.nonNull(listOfFolders)) {
            Arrays.sort(listOfFolders);
            List<File> sortedFileList = Arrays.stream(listOfFolders).toList();

            int countOfFiles = sortedFileList.size();

            if (numOfFoldersToRemain > countOfFiles) {
                return;
            }

            int numOfFoldersForDelete = countOfFiles - numOfFoldersToRemain;
            sortedFileList
                    .stream()
                    .limit(numOfFoldersForDelete)
                    .forEach(f -> {
                        try {
                            FileUtils.deleteDirectory(f);
                        } catch (IOException e) {
                            System.out.println("ERROR:" + e);
                        }
                    });
        }
    }

    public void fileCleanUp(File directory) {
        File[] listOfFiles = directory.listFiles();
        if (Objects.nonNull(listOfFiles)) {
            Stream.of(listOfFiles)
                    .filter(f -> !f.getName().equals("archive") && f.exists())
                    .forEach(file -> {
                        try {
                            FileUtils.delete(file);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
    }
}
