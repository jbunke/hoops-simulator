package hoops_root;

import hoops_root.traits.*;

import java.util.ArrayList;
import java.util.List;

public class Player {
  private static final int SHOOTING = 0, REBOUNDING = 1, ATHLETICISM = 2,
          PASSING = 3, EFFICIENCY = 4, CLUTCH = 5, FREE_THROW = 6, DEFENSE = 7,
          SCORING = 8, TOTAL = 9;

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
  private double FTPERC = 0.0;
  private double CLUTCHPERC = 0.0;
  private int THREE = 0;
  private int TWO = 0;

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
    this.position = Position.best(this);
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

  private void ratingUpdate() {
    int[] update = new int[11];
    // shooting
    update[0] = Math.min(99, 50 + (int)(THREEPERC * 100));
    if (THREE < (MINPG * season.size()) / 48) { update[0] -= 10; }
    update[0] = Math.max(0, update[0]);

    // rebounds
    update[1] = Math.min(50 + (int)(Math.pow(REBPG, 0.5) * 15), 99);

    // athleticism
    update[2] = Math.min(50 + (int)(PPG + (MINPG / 2)), 99);

    // passing
    update[3] = Math.min(50 + (int)(Math.sqrt(ASTPG) * 20), 99);
    update[3] = Math.max(0, update[3] - (int)(TOVPG * 5));

    // vision
    update[4] = (int)(((5 - TOVPG) * 13) + (STLPG * 7));
    update[4] = Math.min(99, Math.max(0, update[4]));

    // clutch
    update[5] = Math.max(0,  99 - (int)((1 - CLUTCHPERC) * 167));

    // free throw
    update[6] = Math.min(99, (int)(FTPERC * 100) + 6);

    // blocking
    update[7] = Math.min(50 + (int)(Math.sqrt(BLKPG) * 20), 99);

    // driving
    update[8] = Math.min(50 +
            (int)(PPG * Math.pow(TWO / (double)season.size(), 0.27)), 99);

    update[9] = stats[9];
    update[10] = stats[10];

    // Capping changes per update
    for (int i = 0; i < 11; i++) {
      if (Math.abs(stats[i] - update[i]) > 3) {
        stats[i] += Math.signum(update[i] - stats[i]) * 3;
      } else {
        stats[i] = update[i];
      }
    }

    this.overall = Player.overall(this, this.position);
  }

  void statUpdate() {
    double gamesPlayed = 0.0;
    int points = 0;
    int fts = 0;
    int ftas = 0;
    int clutchPlays = 0;
    int clutchOpportunities = 0;
    int assists = 0;
    int rebounds = 0;
    int blocks = 0;
    int fouls = 0;
    int steals = 0;
    int turnovers = 0;
    int minutes = 0;
    int twos = 0;
    int threes = 0;
    int threesAttempted = 0;
    for (PlayerGameStats gameStats : season) {
      if (gameStats.mins() > 0) {
        points += gameStats.getPoints();
        fts += gameStats.getFreeThrows();
        ftas += gameStats.getFreeThrowAttempts();
        assists += gameStats.getAssists();
        rebounds += gameStats.getRebounds();
        blocks += gameStats.getBlocks();
        fouls += gameStats.getFouls();
        steals += gameStats.getSteals();
        turnovers += gameStats.getTurnovers();
        minutes += gameStats.mins();
        threes += gameStats.getT3fieldGoals();
        threesAttempted += gameStats.getT3fieldGoalAttempts();
        twos += gameStats.getFieldGoals() - gameStats.getT3fieldGoals();
        clutchPlays += gameStats.getClutchPlays();
        clutchOpportunities += gameStats.getClutchOpportunities();
        gamesPlayed++;
      }
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
    FTPERC = fts / (double)ftas;
    THREEPERC = threes / (double)threesAttempted;
    CLUTCHPERC = clutchPlays / (double)clutchOpportunities;
    THREE = threes;
    TWO = twos;

    // Stamina replenish
    energy = Math.min(1000, energy + 100 + (int)(400 * Math.random()));

    // Check to call for ratings update (every 10 games of season)
    if (season.size() % 10 == 0 && season.size() >= 10) {
      // ratingUpdate();
    }
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

  public double getCLUTCHPERC() {
    return CLUTCHPERC;
  }

  public int getTHREE() {
    return THREE;
  }

  public int overall() { return overall; }

  public static int overall(Player player, Position position) {
    int[] weight = new int[10];

    switch (position) {
      case POINT_GUARD:
        weight[SHOOTING] = 4;
        weight[REBOUNDING] = 2;
        weight[ATHLETICISM] = 2;
        weight[PASSING] = 6;
        weight[EFFICIENCY] = 5;
        weight[CLUTCH] = 3;
        weight[FREE_THROW] = 2;
        weight[DEFENSE] = 2;
        weight[SCORING] = 4;
        weight[TOTAL] = 30;
        break;
      case SHOOTING_GUARD:
        weight[SHOOTING] = 6;
        weight[REBOUNDING] = 3;
        weight[ATHLETICISM] = 2;
        weight[PASSING] = 4;
        weight[EFFICIENCY] = 3;
        weight[CLUTCH] = 3;
        weight[FREE_THROW] = 2;
        weight[DEFENSE] = 4;
        weight[SCORING] = 3;
        weight[TOTAL] = 30;
        break;
      case POWER_FORWARD:
        weight[SHOOTING] = 2;
        weight[REBOUNDING] = 5;
        weight[ATHLETICISM] = 6;
        weight[PASSING] = 2;
        weight[EFFICIENCY] = 2;
        weight[CLUTCH] = 3;
        weight[FREE_THROW] = 2;
        weight[DEFENSE] = 4;
        weight[SCORING] = 4;
        weight[TOTAL] = 30;
        break;
      case SMALL_FORWARD:
        weight[SHOOTING] = 3;
        weight[REBOUNDING] = 4;
        weight[ATHLETICISM] = 3;
        weight[PASSING] = 3;
        weight[EFFICIENCY] = 3;
        weight[CLUTCH] = 3;
        weight[FREE_THROW] = 2;
        weight[DEFENSE] = 3;
        weight[SCORING] = 6;
        weight[TOTAL] = 30;
        break;
      case CENTER:
      default:
        weight[SHOOTING] = 3;
        weight[REBOUNDING] = 6;
        weight[ATHLETICISM] = 5;
        weight[PASSING] = 2;
        weight[EFFICIENCY] = 2;
        weight[CLUTCH] = 3;
        weight[FREE_THROW] = 2;
        weight[DEFENSE] = 5;
        weight[SCORING] = 2;
        weight[TOTAL] = 30;
        break;
    }

    int statSum = 0;
    for (int i = 0; i < 9; i++) {
      weight[i] += 3;
      statSum += (player.stats[i] * weight[i]);
    }
    weight[TOTAL] += (9 * 3);
    return Math.min(99, (statSum / weight[TOTAL]) + 5);
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
    if (energy > 600 && status == Status.FATIGUED) {
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

  public String goesBy() {
    // Brazilian rule
    if (prefName.equals(name[name.length - 1])) return prefName;

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
