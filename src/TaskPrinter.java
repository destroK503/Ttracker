import java.util.ArrayList;

public class TaskPrinter {
  public static void printTask(ArrayList<_Task> tasks) {
    for (_Task task : tasks) {
      System.out.print(
          String.format(
              "%s this is for: %s%n",
              task.name(),
              task.type()
        ));
    }
    System.out.println(String.format("\033[34m[MSG] Total task found: %d\033[0m", tasks.size()));

  }
}
