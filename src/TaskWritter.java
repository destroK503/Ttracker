import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.Path;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
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
 * <p>
 * === 1 // this will be the index of the task
 * </p>
 * <p>
 *  NAME: <Name for the tasks 
 * </p> 
 * <p> 
 *  DATE: <time thet task was created>
 * </p> 
 * <p> 
 *  TYPE: <tyoe> // this can something like 'school' or 'family'
 *  </p>
 * ===
 * 
 *
 */
public class TaskWritter {
  private static Path path = Paths.get(
    System.getProperty("user.home"), 
    ".Ttracker",
    "Tasks"
  );
  
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


  public static void write(String task, String type, String status){
    if (!initer())
      return;

    String data = String.format(
      "===%nNAME: %s%nDATE: %s%nTIME: %s%nSTATUS: %s%nTYPE: %s%n===",
      task,
      getCurrentDate(),
      getCurrentTime(),
      status,
      type
    );

    System.out.println(data);

    internalWritter(data);
  }

  public static void write(String task, String type){
    write(task, type, "pending");
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

    return String.format("%02d:%02d:%02d %s", hora12, min, segundos, periodo);

  }

  private static String getCurrentDate(){
    return String.format("%s", LocalDate.now());
  }


  private static void internalWritter(String data){

    try (
      BufferedWriter bf = Files.newBufferedWriter(path, StandardOpenOption.APPEND)
    ){
      bf.newLine();
      bf.write(data);
    } catch (IOException e){
      e.printStackTrace();
      return;
    }
  }

  public static void main(String[] args) {
    write("Something important", "city");
    write("Something even more important", "School", "delayed");
  }

}
