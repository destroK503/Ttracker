import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

/**
 * <h1>
 * TaskReader
 * </h1>
 *
 * <h1>This class is designed to be placed on the heap</h1>
 *
 * <p>
 * [RESUME]
 * </p>
 * <p>
 * Class designed to read both task that are pending
 * or tasks that are done
 * </p>
 * <p>
 * Thee tasks will be represented
 * as an ArrayList from the java std
 *
 *
 */
public class TaskReader {
  private static Path pathToTasks = Paths.get(
      System.getProperty("user.home"),
      ".Ttracker",
      "Tasks");
  private static Path pathToDone = Paths.get(
      System.getProperty("user.home"),
      ".Ttracker",
      "Done");
  private List<String> doneFileLines;
  private List<String> taskFileLines;
  private ArrayList<_Task> doneTasks = new ArrayList<>();
  private ArrayList<_Task> activeTasks = new ArrayList<>();

  public TaskReader() {

    Thread readDoneFile = new Thread(() -> {
      try {
        doneFileLines = Files.readAllLines(pathToDone);
      } catch (IOException e) {
        System.out.println("Could not read done file!");
        e.printStackTrace();
      }
    });
    Thread readTaskFile = new Thread(() -> {
      try {
        taskFileLines = Files.readAllLines(pathToTasks);
      } catch (IOException e) {
        System.out.println("Could not read done file!");
        e.printStackTrace();
      }
    });

    readDoneFile.start();
    readTaskFile.start();

    try {
      readDoneFile.join();
      readTaskFile.join();
    } catch (InterruptedException e) {
      System.out.println("Thread was interrupted!");
      e.printStackTrace();
    }

  }

  public ArrayList<_Task> getTaskDone() {
    if (doneFileLines.isEmpty()) {
      System.out.println("Tried to read done file; got null!");
      return new ArrayList<_Task>();
    }
    for (int i = 0; i < doneFileLines.size() - 1; i++) {
      if (doneFileLines.get(i).equals("===")) {
        String name = "", date = "", time = "", status = "", type = "";
        i++;

        while (i < doneFileLines.size() - 1 && !doneFileLines.get(i).equals("===")) {
          String line = doneFileLines.get(i).trim();
          if (line.startsWith("NAME: ")) {
            name = line.substring(6).trim();
          } else if (line.startsWith("DATE: ")) {
            date = line.substring(6).trim();
          } else if (line.startsWith("TIME: ")) {
            time = line.substring(6).trim();
          } else if (line.startsWith("STATUS: ")) {
            status = line.substring(8).trim();
          } else if (line.startsWith("TYPE: ")) {
            type = line.substring(6).trim();
          }
          i++;
        }
        doneTasks.add(new _Task(name, date, time, status, type));
      }
    }
    return doneTasks;
  }

  public ArrayList<_Task> getTasks() {
    if (taskFileLines.isEmpty()) {
      System.out.println("Tried to read task file; got null!");
      return new ArrayList<_Task>(); 
    }

    for (int i = 0; i < taskFileLines.size() - 1; i++) {
      if (taskFileLines.get(i).equals("===")) {
        String name = "", date = "", time = "", status = "", type = "";
        i++; 

        while (i < taskFileLines.size() && !taskFileLines.get(i).equals("===")) {
          String line = taskFileLines.get(i).trim();
          if (line.startsWith("NAME: ")) {
            name = line.substring(6).trim();
          } else if (line.startsWith("DATE: ")) {
            date = line.substring(6).trim();
          } else if (line.startsWith("TIME: ")) {
            time = line.substring(6).trim();
          } else if (line.startsWith("STATUS: ")) {
            status = line.substring(8).trim();
          } else if (line.startsWith("TYPE: ")) {
            type = line.substring(6).trim();
          }
          i++; 
        }
        activeTasks.add(new _Task(name, date, time, type, status));
      }
    }

    return activeTasks;
  }

  // public ArrayList<String> getTaskWithStatus(String status) {
  // System.out.println("This method is not implemented yet!");
  // }

  // public ArrayList<String> getTasksWithType(String type) {

  // }

  // public static void main(String[] args) {
  // System.out.println("Entering debug mode:\n");
  // }
  public static void main(String[] args) {
    TaskReader customReader = new TaskReader();
    TaskPrinter.printTask(customReader.getTaskDone());
  }
}
