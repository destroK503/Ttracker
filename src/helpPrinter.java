public class helpPrinter {
  private static String introduction = "Ttracker; The not perfect tracker for your tasks\n";
  private static String options = String.format(
      "%s - %s%n",
      "pending", "Will show the tasks that are as pending in the tasks stock");

  public static void print() {
    String str = String.format(
        "%s---%n%s",
        introduction,
        options);
    System.out.print(str);
  }
}
