java import java.util.Random;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

/**
 * tuiWindows
 *
 * please make sure to always call .close()
 * at the end
 */
public class tuiWindows {
  private interface Kernel32 extends Library {
    Kernel32 INSTANCE = Native.load("Kernel32", Kernel32.class);

    int STD_INPUT_HANDLE = 0xFFFFFFF6;
    int ENABLE_PROCESSED_INPUT = 0x0001;
    int ENABLE_LINE_INPUT = 0x0002;
    int ENABLE_ECHO_INPUT = 0x0004;

    Pointer GetStdHandle(int nStdHandle);

    boolean GetConsoleMode(Pointer hConsoleHandle, IntByReference lpMode);

    boolean SetConsoleMode(Pointer hConsoleHandle, int dwMode);

    boolean AttachConsole(int dwProcessId);

    int GetFileType(Pointer hFile);

    int GetLastError();
  }

  int originalMode;
  Pointer stdIn;
  Kernel32 k32;
  boolean isStoped = false;
  boolean shouldClose = false;

  public tuiWindows() {
    k32 = Kernel32.INSTANCE;
    stdIn = k32.GetStdHandle(Kernel32.STD_INPUT_HANDLE);

    if (stdIn == Pointer.NULL || Pointer.nativeValue(stdIn) == -1) {
      System.out.println("stdHandle could not be resolved!");
      return;
    }

    IntByReference mode = new IntByReference();
    if (!k32.GetConsoleMode(stdIn, mode)) {
      System.out.printf("Could not get current terminal mode: %n\t%s: %d",
          "Got error code",
          k32.GetLastError());
      return;
    }

    originalMode = mode.getValue();
    int rawMode = originalMode & ~(Kernel32.ENABLE_ECHO_INPUT |
        Kernel32.ENABLE_LINE_INPUT |
        Kernel32.ENABLE_PROCESSED_INPUT);

    if (!k32.SetConsoleMode(stdIn, rawMode)) {
      System.out.printf(
          "Could not set terminal mode into raw %n\t%s: %d",
          "Got error code",
          k32.GetLastError());
      return;
    }
  }

  public void close() {
    k32.SetConsoleMode(stdIn, originalMode);
    isStoped = true;
  }



  /*
   * Will generate a element in screen like this:
   *
   * ==========
   *  TASK:
   *  DESCRIPTION:
   *
   *  DONE
   * ==========
   *
   */
  public _Task tuiForWritter() {

    final String label = "Name for yout task here";
    final String label2 = "description for your taks";
    final String label3 = "type";
    final String buttonLabel = "DONE";

    if (isStoped) {
      System.out.println("Could no create input TUI!");
      System.out.println("Non valid intance of tuiWindows could be found!");
      return new _Task("", "", "", "", "");
    }

    // We need to make a method to print the chars as
    // a window

    // for (int i = 0; i < 20; i++){
    // for (int j = 0; j < 10; j++) {
    // }
    // }

    int seconds = 0;
    System.out.println(_TuiCodesWindows.CLEAR_SCREEN);

    while (!shouldClose) {
      seconds++;
    }

    return new _Task("", "", "", "", "");

  }

  /*
   *
   * Helpers
   *
   */

  private static void _Helper_drawTuiForWritter(){
    for (int i = 0; i < max; i++) {
      for (int j= 0; j < max; i++) {

      }
    }
  }
}
