package utilities;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsUtil {

    public static String GetDownloadFolder()
    {
        String codeBase = System.getProperty("user.dir");
        Path path = Paths.get(codeBase, "downloads");
        return path.toString();
    }
}
