/*
 *
 * Contains a bunch of metaDeta necesary
 * for both windows and linux TUIs
 *
 */
public class _tuiMetaData {
  static char wall = '│';
  static char wallH = '─';
  static char cornerTopLeft = '┌';
  static char cornerTopRight = '┐';
  static char cornerBottomLeft = '└';
  static char cornerBottomRight = '┘';
  static char junctionT = '┬';
  static char junctionL = '├';
  static char junctionR = '┤';
  static char junctionB = '┴';
  static char junctionCenter = '┼';
  static String ESCkey = "\u001B";

  /*
   * <h1> [RESUME] </h1>
   *
   * <p>
   * Will move the cursor to the provide cordinates
   * </p>
   *
   *
   */
  public static void moveCursor(int x, int y) {
    System.out.print(String.format("%s[%d;%dH", ESCkey, x, y));
  }
}
