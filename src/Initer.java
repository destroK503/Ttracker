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
    }
    if (args.length <= 0) {
      System.out.println("Everyhting good to go!");
      System.out.println("Try passing the 'help' argument");
    }

    for (String arg : args) {
      if (arg.equals("pending"))
        TaskPrinter.printTask(reader.getTasks());

      if (arg.equals("help"))
        helpPrinter.print();
    }
    _Task poke = tuiwindows.tuiForWritter();
    tuiwindows.close();


    tuiWindows tui = new tuiWindows();

    tui.inputForNewTask();

    tui.close();
  }

}
