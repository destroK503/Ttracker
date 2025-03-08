import java.util.ArrayList;



public class Initer {
  public static void main(String[] args) {
    System.out.println("Ran!");
  
    ArrayList<String> s = new ArrayList<>();
    s.add("Sii");
    s.add("Nooo");
    s.add("Maybe");


    TaskPrinter.printTask(s);
  }
}
