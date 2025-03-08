import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;

/**
 * <h1>
 * Setter
 * </h1>
 * <p>
 * Speciall class to setup
 * everything that Ttrackers needs
 * </p>
 */
public class Setter {
  private static Path dirPath = Paths.get(
      System.getProperty("user.home"),
      ".Ttracker");

  private static String[] filesToBeCreated = {
      "task",
      "done",
  };

  public static void init() {
    try {
      Files.createDirectory(dirPath);
    } catch (IOException e) {
      System.out.println("Could not create folder for the Ttracker project!");
      e.printStackTrace();
      return;
    }

    for (String file : filesToBeCreated) {
      Path tpmFile = dirPath.resolve(file);
      try {
        Files.createFile(tpmFile);
      } catch (IOException e) {
        System.out.println("Setter could not create files!");
        e.printStackTrace();
        return;
      }
    }

    // we assume that everything went right!
    System.out.println("Setup finished with no erros!");
  }
}
