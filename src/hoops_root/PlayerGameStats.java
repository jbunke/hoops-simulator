package hoops_root;

import hoops_root.traits.Position;

class PlayerGameStats {
  final Player player;
  final int seasonYear;
  final int age;
  final Team team;
  final Position position;
  private int minutes;
  private int fieldGoals;
  private int fieldGoalAttempts;
  private int t3fieldGoals;
  private int t3fieldGoalAttempts;
  private int t2fieldGoals;
  private int t2fieldGoalAttempts;
  private int freeThrows;
  private int freeThrowAttempts;
  private int rebounds;
  private int assists;
  private int blocks;
  private int steals;
  private int turnovers;
  private int personalFouls;
  private int points;

  static PlayerGameStats startGame(Player player, Date date) {
    return new PlayerGameStats(player, date);
  }

  private PlayerGameStats(Player player, Date date) {
    this.player = player;
    this.seasonYear = 2019; // TODO: fix
    this.age = player.age(date);
    this.team = player.getTeam();
    this.position = player.position();
    this.minutes = 0;
    this.fieldGoals = 0;
    this.fieldGoalAttempts = 0;
    this.t3fieldGoals = 0;
    this.t3fieldGoalAttempts = 0;
    this.t2fieldGoals = 0;
    this.t2fieldGoalAttempts = 0;
    this.freeThrows = 0;
    this.freeThrowAttempts = 0;
    this.rebounds = 0;
    this.assists = 0;
    this.blocks = 0;
    this.steals = 0;
    this.turnovers = 0;
    this.personalFouls = 0;
    this.points = 0;
  }

  void playMinute() {
    minutes++;
    player.dropEnergy();
  }

  void takeFT(boolean make) {
    freeThrowAttempts++;
    if (make) {
      freeThrows++;
      points++;
    }
  }

  void take2P(boolean make) {
    t2fieldGoalAttempts++;
    fieldGoalAttempts++;
    if (make) {
      t2fieldGoals++;
      fieldGoals++;
      points += 2;
    }
    player.dropEnergy();
  }

  void take3P(boolean make) {
    t3fieldGoalAttempts++;
    fieldGoalAttempts++;
    if (make) {
      t3fieldGoals++;
      fieldGoals++;
      points += 3;
    }
  }

  void makeAssist() {
    assists++;
  }

  void makeRebound() {
    rebounds++;
    player.dropEnergy();
  }

  void makeBlock() {
    blocks++;
    player.dropEnergy();
  }

  void makeSteal() {
    steals++;
    player.dropEnergy();
  }

  void makeTurnover() { turnovers++; }

  void makeFoul() {
    personalFouls++;
  }

  @Override
  public String toString() {
    return player.goesBy() + ": " + points + " PTS, " + assists + " AST, " + rebounds + " REB";
  }

  int mins() {
    return minutes;
  }

  String FG() {
    return fieldGoals + "-" + fieldGoalAttempts;
  }

  String THREE() {
    return t3fieldGoals + "-" + t3fieldGoalAttempts;
  }

  String FT() {
    return freeThrows + "-" + freeThrowAttempts;
  }

  int getRebounds() {
    return rebounds;
  }

  int getAssists() {
    return assists;
  }

  int getBlocks() {
    return blocks;
  }

  int getSteals() {
    return steals;
  }

  int getTurnovers() {
    return turnovers;
  }

  int getPoints() {
    return points;
  }

  int getFouls() {
    return personalFouls;
  }

  int getFieldGoals() { return fieldGoals; }

  int getFieldGoalAttempts() { return fieldGoalAttempts; }

  int getFreeThrows() { return freeThrows; }

  int getFreeThrowAttempts() { return freeThrowAttempts; }

  int getT3fieldGoals() {
    return t3fieldGoals;
  }

  int getT3fieldGoalAttempts() {
    return t3fieldGoalAttempts;
  }
}
