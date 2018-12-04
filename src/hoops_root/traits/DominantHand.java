package hoops_root.traits;

public enum DominantHand {
  RIGHT,
  LEFT;

  public static DominantHand fromString(String string) {
    switch (string.toUpperCase()) {
      case "LEFT":
        return LEFT;
      default:
        return RIGHT;
    }
  }
}
