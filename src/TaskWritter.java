
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.time.LocalDateTime;

/**
 * <h1> TaskWritter </h1>
 *
 * <p>
 *
 *  Writes a task into the record 
 *  following the next format 
 *
 *
 * </p>
 *
 *
 * <code>
 *  === 1 // this will be the index of the task
 *    
 *  NAME: <Name for the tasks 
 *  DATE: <time thet task was created>
 *  TYPE: <tyoe> // this can something like 'school' or 'family'
 *
 *
 *  ===
 *
 * </code>
 *
 */
public class TaskWritter {
  private static Path path = Paths.get(
    System.getProperty("user.home"), 
    ".Ttracker",
    "Tasks"
  );
  private record metaData(double time, String type)
  {}
  
  private static boolean initer(){
    if (!Files.exists(path)){
      System.out.print(
        "Attemted to write into Tasks file; It could not be found!\n"
      );
      System.out.println("Try running the setup function first");
      return false;
    }
    return true;
  }


  public static void write(String task, String type){
    //if (!initer())
      //return;

    String data = String.format(
      "===%nNAME: %s%nDATE: %s%nTIME: %s%nTYPE:%s%n===",
      task,
      getCurrentTime(),
      type
    );

    System.out.println(data);


    System.out.println("'Write' method has not been implemented yet");
    return;
  }


  /*=====================
   *
   * Helpers
   *
   * -- Destro 
   *
   *=====================
   */ 


  private static String getCurrentTime(){
    LocalDateTime ahora = LocalDateTime.now();
        
    int hours = ahora.getHour();
    int min = ahora.getMinute();
    int segundos = ahora.getSecond();
        
    int hora12 = (hours == 0) ? 12 : (hours > 12 ? hours - 12 : hours);
    String periodo = (hours < 12) ? "AM" : "PM";

    return String.format("%02d:%02d:%02d %s%n", hora12, min, segundos, periodo);

  }


  private static void internal_writter(){

  }



  public static void main(String[] args) {
    System.out.println("Entering debug mode!");
    write("Do something", "school");
  }
 
}
