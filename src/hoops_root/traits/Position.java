package hoops_root.traits;

public enum Position {
  POINT_GUARD(0),
  SHOOTING_GUARD(1),
  POWER_FORWARD(2),
  SMALL_FORWARD(3),
  CENTER(4);

  public final int index;

  Position(int index) {
    this.index = index;
  }

  public static Position fromIndex(int index) {
    assert (index >= 0 && index < 5);

    switch (index) {
      case 0:
        return POINT_GUARD;
      case 1:
        return SHOOTING_GUARD;
      case 2:
        return POWER_FORWARD;
      case 3:
        return SMALL_FORWARD;
      case 4:
      default:
        return CENTER;
    }
  }

  public static Position random() {
    int draw = (int)(Math.random() * 5);

    return Position.fromIndex(draw);
  }

  @Override
  public String toString() {
    switch (this) {
      case POINT_GUARD:
        return "PG";
      case SHOOTING_GUARD:
        return "SG";
      case POWER_FORWARD:
        return "PF";
      case SMALL_FORWARD:
        return "SF";
      case CENTER:
      default:
        return "C";
    }
  }
}
