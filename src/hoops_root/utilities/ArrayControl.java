package hoops_root.utilities;

public class ArrayControl {
  public static <T> T[] difference(T[] from, T[] without) {
    int occurrences = 0;
    boolean[] matched = new boolean[from.length];
    for (int i = 0; i < from.length; i++) {
      for (T u : without) {
        if (from[i].equals(u)) {
          occurrences++;
          matched[i] = true;
          break;
        }
      }
    }

    Object[] differences = new Object[from.length - occurrences];
    int inserted = 0;
    for (int i = 0; i < from.length; i++) {
      if (!matched[i]) {
        differences[inserted] = from[i];
        inserted++;
      }
    }
    return (T[])(differences);
  }
}
