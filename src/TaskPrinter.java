import java.util.ArrayList;

public class TaskPrinter {

  static String icon = """
          ,*-.
          |  |
      ,.  |  |
      | |_|  | ,.
      `---.  |_| |
          |  .--`
          |  |
          |  | Destro

        A veces la vida es como un captus
        """;

  public static void printTask(ArrayList<_Task> tasks) {
    System.out.printf("%s%n", icon);

    for (_Task task : tasks) {
      System.out.print(
          String.format(
              "\033[33m%s\033[0m \tthis is for: \033[32m%s\033[0m%n",
              task.name(),
              task.type()));
    }

    System.out.println(String.format("\n\033[34mTotal task found: %d\033[0m", tasks.size()));

  }
}
