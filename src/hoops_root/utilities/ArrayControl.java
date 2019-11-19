package hoops_root.utilities;

import hoops_root.Player;

public class ArrayControl {
  public static Player[] playerDifference(Player[] from, Player[] without) {
    int occurrences = 0;
    boolean[] matched = new boolean[from.length];
    for (int i = 0; i < from.length; i++) {
      for (Player u : without) {
        if (from[i].equals(u)) {
          occurrences++;
          matched[i] = true;
          break;
        }
      }
    }

    Player[] differences = new Player[from.length - occurrences];
    int inserted = 0;
    for (int i = 0; i < from.length; i++) {
      if (!matched[i]) {
        differences[inserted] = from[i];
        inserted++;
      }
    }
    return differences;
  }
}
