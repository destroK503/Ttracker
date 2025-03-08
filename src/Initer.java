import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

/**
 * <h1>
 * Initer
 * </h1>
 */
public class Initer {
  private static Path dirPath = Paths.get(
      System.getProperty("user.home"),
      ".Ttracker");

  /*
   * The only entry point of the app
   *
   */
  public static void main(String[] args) {
    if (!Files.exists(dirPath)) {
      Setter.init();
    } else {
      System.out.println("Everyhting good to go!");
    }
  }

}
