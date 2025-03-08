import java.util.ArrayList;


public class TaskPrinter {
  public static void printTask(ArrayList<String> task){
    for (int i = 0; i < task.size(); i++)
    {
      System.out.println(task.get(i));
    }
  }
}
