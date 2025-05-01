/**
 * tuiWindows
 */
public class tuiWindows {
  _terminalHandlerWindow handler;

  public tuiWindows() {
    handler = new _terminalHandlerWindow();
    handler.terminalToRaw();
  }

  /*
   * helpers 
   *
   */ 
  
  
  /*
   * <h1> [RESUME] /h1>
   * <p>
   *  Will display the basic input box for a task 
   *</p>
   *
   *
   */ 
  public void inputForNewTask()
  {
    Pair<Integer, Integer> dimetions = handler.getTerminalDimentions();
    System.out.println("Hey X is: " + dimetions.first() + " " + "Hey y is " + dimetions.second());
  }

  public void close(){
    handler.close();
  }

      }
    }
  }
}
