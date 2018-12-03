package hoops_root;

import hoops_root.traits.*;

import java.util.ArrayList;
import java.util.List;

public class Player {
  private final String[] name;
  private String prefName;
  private final Date birthDate;
  private Team team;
  private int jerseyNumber;

  // OVER TIME
  private Status status;
  private List<PlayerGameStats> season;
  private List<List<PlayerGameStats>> pastSeasons;

  private double PPG = 0.0;
  private double ASTPG = 0.0;
  private double REBPG = 0.0;
  private double BLKPG = 0.0;
  private double STLPG = 0.0;
  private double TOVPG = 0.0;
  private double MINPG = 0.0;
  private int PTS = 0;
  private double FLSPG = 0.0;
  private double THREEPERC = 0.0;
  private int THREE = 0;

  // TRAITS
  private Position position;
  private DominantHand dominantHand;
  final int prefJerseyNumber;
  final int height; // inches
  final int weight; // pounds
  private int energy; // 0 - 1000

  // STATS
  private int stats[];
  private int overall;

  public enum Status {
    FIT,
    INJURED,
    SUSPENDED,
    FOULED_OUT,
    FATIGUED
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
    this.energy = 1000;
    this.status = Status.FIT;
    this.season = new ArrayList<>();
    this.pastSeasons = new ArrayList<>();
    this.stats = new int[] {62 + (int)(Math.random() * 30), // shooting 0 - 99
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
    this.energy = 1000;
    this.status = Status.FIT;
    this.season = new ArrayList<>();
    this.pastSeasons = new ArrayList<>();
    this.stats = stats;
    this.overall = Player.overall(this, this.position);
  }

  void setJerseyNumber(int jerseyNumber) { this.jerseyNumber = jerseyNumber; }

  Team getTeam() { return team; }

  void setTeam(Team team) {
    this.team = team;
  }

  int[] getStats() { return stats; }

  void addToSeasonStats(PlayerGameStats gameStats) {
    this.season.add(gameStats);
  }

  void statUpdate() {
    double gamesPlayed = (double)season.size();
    int points = 0;
    int assists = 0;
    int rebounds = 0;
    int blocks = 0;
    int fouls = 0;
    int steals = 0;
    int turnovers = 0;
    int minutes = 0;
    int threes = 0;
    int threesAttempted = 0;
    for (PlayerGameStats gameStats : season) {
      points += gameStats.getPoints();
      assists += gameStats.getAssists();
      rebounds += gameStats.getRebounds();
      blocks += gameStats.getBlocks();
      fouls += gameStats.getFouls();
      steals += gameStats.getSteals();
      turnovers += gameStats.getTurnovers();
      minutes += gameStats.mins();
      threes += gameStats.getT3fieldGoals();
      threesAttempted += gameStats.getT3fieldGoalAttempts();
    }
    PPG = points / gamesPlayed;
    PTS = points;
    ASTPG = assists / gamesPlayed;
    REBPG = rebounds / gamesPlayed;
    BLKPG = blocks / gamesPlayed;
    FLSPG = fouls / gamesPlayed;
    STLPG = steals / gamesPlayed;
    TOVPG = turnovers / gamesPlayed;
    MINPG = minutes / gamesPlayed;
    THREEPERC = threes / (double)threesAttempted;
    THREE = threes;

    // Stamina replenish
    energy = Math.min(1000, energy + 100 + (int)(400 * Math.random()));
  }

  public double getPPG() {
    return PPG;
  }

  public int getPTS() {
    return PTS;
  }

  public double getASTPG() {
    return ASTPG;
  }

  public double getBLKPG() {
    return BLKPG;
  }

  public double getREBPG() {
    return REBPG;
  }

  public double getFLSPG() {
    return FLSPG;
  }

  public double getSTLPG() {
    return STLPG;
  }

  public double getTOVPG() {
    return TOVPG;
  }

  public double getMINPG() {
    return MINPG;
  }

  public double getTHREEPERC() {
    return THREEPERC;
  }

  public int getTHREE() {
    return THREE;
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

  void replenishEnergy() {
    energy += stats[2];
    energy = Math.min(energy, 1000);
    if (energy > 500 && status == Status.FATIGUED) {
      setStatus(Status.FIT);
    }
  }

  void dropEnergy() {
    energy -= (2000 / stats[2]);
    if (energy < 100) {
      setStatus(Status.FATIGUED);
    }
  }

  void unfoul() {
    setStatus(Status.FIT);
    energy = 1000;
  }

  public Position position() { return position; }

  Status status() { return status; }

  void setStatus(Status status) { this.status = status; }

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
