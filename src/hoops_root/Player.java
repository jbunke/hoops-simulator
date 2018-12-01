package hoops_root;

import hoops_root.traits.*;

public class Player {
  private final String[] name;
  private String prefName;
  private final Date birthDate;
  private Team team;
  private int jerseyNumber;
  private Status status;

  // TRAITS
  private Position position;
  private DominantHand dominantHand;
  final int prefJerseyNumber;
  private final int height; // inches
  private int weight; // pounds

  // STATS
  private int stats[];
  private int overall;

  public enum Status {
    FIT,
    INJURED,
    SUSPENDED
  }

  public Player(String[] name, String prefName, Date birthDate) {
    this.name = name;
    this.prefName = prefName;
    this.birthDate = birthDate;
    this.position = Position.random();
    this.dominantHand = DominantHand.RIGHT;
    this.prefJerseyNumber = -1; // RANDOM ASSIGNMENT VALUE // TODO
    this.jerseyNumber = -1;
    this.height = 70 + (int)(Math.random() * 15);
    this.weight = 155 + ((this.height - 70) * 7) + (int)(Math.random() * 10);
    this.status = Status.FIT;
    this.stats = new int[] {50 + (int)(Math.random() * 30), // shooting 0 - 99
                            50 + (int)(Math.random() * 30), // rebounding 0 - 99
                            50 + (int)(Math.random() * 30), // athleticism 0 - 99
                            50 + (int)(Math.random() * 30), // passing 0 - 99
                            50 + (int)(Math.random() * 30), // vision 0 - 99
                            50 + (int)(Math.random() * 30), // clutch 0 - 99
                            50 + (int)(Math.random() * 30), // free throw 0 - 99
                            50 + (int)(Math.random() * 30), // block 0 - 99
                            50 + (int)(Math.random() * 30), // driving 0 - 99
                            2,  // injury-prone 0 - 4
                            2}; // flagrant 0 - 4 (11 stats total)
    this.overall = Player.overall(this, this.position);
  }

  public Player(String[] name, String prefName, Date birthDate, Position position,
         DominantHand dominantHand, int prefJerseyNumber,
         int height, int weight, int[] stats) {
    this.name = name;
    this.prefName = prefName;
    this.birthDate = birthDate;
    this.position = position;
    this.dominantHand = dominantHand;
    this.prefJerseyNumber = prefJerseyNumber;
    this.jerseyNumber = -1;
    this.height = height;
    this.weight = weight;
    this.status = Status.FIT;
    this.stats = stats;
    this.overall = Player.overall(this, this.position);
  }

  void setJerseyNumber(int jerseyNumber) { this.jerseyNumber = jerseyNumber; }

  Team getTeam() { return team; }

  void setTeam(Team team) {
    this.team = team;
  }

  public int overall() { return overall; }

  public static int overall(Player player, Position position) {
    int[] weight = new int[10];

    switch (position) {
      case POINT_GUARD:
        weight[0] = 4;
        weight[1] = 2;
        weight[2] = 2;
        weight[3] = 5;
        weight[4] = 5;
        weight[5] = 3;
        weight[6] = 1;
        weight[7] = 2;
        weight[8] = 3;
        weight[9] = 27;
        break;
      case SHOOTING_GUARD:
        weight[0] = 5;
        weight[1] = 1;
        weight[2] = 1;
        weight[3] = 4;
        weight[4] = 4;
        weight[5] = 3;
        weight[6] = 1;
        weight[7] = 1;
        weight[8] = 1;
        weight[9] = 21;
        break;
      case POWER_FORWARD:
        weight[0] = 2;
        weight[1] = 4;
        weight[2] = 5;
        weight[3] = 2;
        weight[4] = 2;
        weight[5] = 3;
        weight[6] = 1;
        weight[7] = 4;
        weight[8] = 4;
        weight[9] = 27;
        break;
      case SMALL_FORWARD:
        weight[0] = 3;
        weight[1] = 3;
        weight[2] = 3;
        weight[3] = 3;
        weight[4] = 3;
        weight[5] = 3;
        weight[6] = 1;
        weight[7] = 3;
        weight[8] = 5;
        weight[9] = 27;
        break;
      case CENTER:
      default:
        weight[0] = 1;
        weight[1] = 5;
        weight[2] = 4;
        weight[3] = 1;
        weight[4] = 1;
        weight[5] = 3;
        weight[6] = 1;
        weight[7] = 5;
        weight[8] = 2;
        weight[9] = 23;
        break;
    }

    int statSum = 0;
    for (int i = 0; i < 9; i++) {
      weight[i] += 3;
      statSum += (player.stats[i] * weight[i]);
    }
    weight[9] += 27;
    return Math.min(99, (statSum / weight[9]) + 5);
  }

  int age(Date referenceDate) {
    assert (referenceDate.year > birthDate.year); // player cannot be unborn or an infant
    int age = referenceDate.year - birthDate.year;
    if (birthDate.month > referenceDate.month ||
            (birthDate.month == referenceDate.month &&
                    birthDate.day > referenceDate.day)) {
      age--;
    }
    return age;
  }

  public Position position() { return position; }

  Status status() { return status; }

  String goesBy() {
    return prefName + " " + name[name.length - 1];
  }

  @Override
  public String toString() {
    if (team != null) {
      return goesBy() + " (" + position + " - #" + jerseyNumber + ")";
    }
    return goesBy();
  }
}
