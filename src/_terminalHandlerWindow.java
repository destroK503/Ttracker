import com.sun.jna.Native;
import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;
import java.util.Arrays;
import java.util.List;

/**
 * TerminaHandler
 * <h1>Must create an instance of this class!</h1>
 *
 * <h1>[RESUME]</h1>
 *
 * <h1>This method must be placed in the heap for its use</h1>
 *
 * <p>
 * This class works as handler for the windows
 * terminal which allows you to work with the
 * terminal
 * </p>
 *
 * <h3>METHODS</h3>
 *
 * <ul>
 * <li>terminalToRaw</li>
 * </ul>
 *
 */
public class _terminalHandlerWindow {
  public static class COORD extends Structure {
    public short X;
    public short Y;

    @Override
    protected List<String> getFieldOrder() {
      return Arrays.asList("X", "Y");

    }
  }

  public static class SMALL_RECT extends Structure {
    public short Left;
    public short Top;
    public short Right;
    public short Bottom;

    @Override
    protected List<String> getFieldOrder() {
      return Arrays.asList("Left", "Top", "Right", "Bottom");

    }

  }

  public static class CONSOLE_SCREEN_BUFFER_INFO extends Structure {
    public COORD dwSize;
    public COORD dwCursorPosition;
    public short wAttributes;
    public SMALL_RECT srWindow;
    public COORD dwMaximumWindowSize;

    @Override
    protected List<String> getFieldOrder() {
      return Arrays.asList(
          "dwSize",
          "dwCursorPosition",
          "wAttributes",
          "srWindow",
          "dwMaximumWindowSize");

    }

  }

  private interface Kernel32 extends Library {
    Kernel32 INSTANCE = Native.load("Kernel32", Kernel32.class);

    int STD_INPUT_HANDLE = 0xFFFFFFF6;
    int STD_OUTPUT_HANDLE = -11;
    int ENABLE_PROCESSED_INPUT = 0x0001;
    int ENABLE_LINE_INPUT = 0x0002;
    int ENABLE_ECHO_INPUT = 0x0004;

    Pointer GetStdHandle(int nStdHandle);

    boolean GetConsoleMode(Pointer hConsoleHandle, IntByReference lpMode);

    boolean SetConsoleMode(Pointer hConsoleHandle, int dwMode);

    boolean AttachConsole(int dwProcessId);

    boolean GetConsoleScreenBufferInfo(
        Pointer hConsoleOutput,
        CONSOLE_SCREEN_BUFFER_INFO lpConsoleScreenBufferInfo);

    int GetFileType(Pointer hFile);

    int GetLastError();
  }

  int originalMode;
  Pointer stdIn;
  Pointer stdOut;
  Kernel32 k32;
  boolean isStoped;

  public _terminalHandlerWindow() {
    k32 = Kernel32.INSTANCE;
    stdIn = k32.GetStdHandle(Kernel32.STD_INPUT_HANDLE);
    stdOut = k32.GetStdHandle(Kernel32.STD_OUTPUT_HANDLE);
    isStoped = false;

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
  }

  /*
   * Terminal To Raw
   * <h1> [Resume] </h1>
   * 
   * <p>
   * Will make calls into the windows API to stablish
   * the mode of the terminal into raw
   * </p>
   *
   *
   * <h1> Must call `restoreTerminal` when you are done with the terminal! </h1>
   *
   *
   */
  public void terminalToRaw() {
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

  /*
   * restore Terminal
   *
   * <h1> [RESUME] </h1>
   * <p>
   * will restore the terminal into its original mode
   * </p>
   *
   */
  public void close() {
    isStoped = true;
    k32.SetConsoleMode(stdIn, originalMode);
  }

  /*
   * <h1> [RESUME UTIL] </h1>
   *
   * <p>
   * This method will return the whole size of the terminal
   * represented as two Integer 'first' for x and 'second' for y
   *
   * </p>
   *
   *
   */
  public Pair<Integer, Integer> getTerminalDimentions() {
    if (isStoped) {
      System.out.println("Could not find a valid instance of the terminalHandler");
      System.out.println("Could not calculate the size of the terminal correctly!");
      return null;
    }

    CONSOLE_SCREEN_BUFFER_INFO info = new CONSOLE_SCREEN_BUFFER_INFO();

    if (!k32.GetConsoleScreenBufferInfo(stdOut, info)) {
      System.out.println("Could not get terminal dimentions!");
    }

    int cols = info.srWindow.Right - info.srWindow.Left + 1;
    int rows = info.srWindow.Bottom - info.srWindow.Top + 1;

    return new Pair<Integer, Integer>(cols, rows);
  }

  /*
   * <h1> [RESUME] </h1>
   * <p>
   * Will return a pair representhing the aviable space from the promt
   * </p>
   *
   */
  public Pair<Integer, Integer> getTerminalAviableDimentions() {
    return new Pair<>(1, 1);

  }

}
