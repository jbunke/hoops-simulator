package hoops_root;

import hoops_root.traits.Position;

class PlayerGameStats {
  final Player player;
  final int seasonYear;
  final int age;
  final Team team;
  final Position position;
  private int minutes;
  private int clutchPlays;
  private int clutchOpportunities;
  private int fieldGoals;
  private int fieldGoalAttempts;
  private int t3fieldGoals;
  private int t3fieldGoalAttempts;
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

  void takeFT(boolean make, boolean clutch) {
    freeThrowAttempts++;
    if (make) {
      freeThrows++;
      points++;
    }
    if (clutch) { makeClutch(make); }
  }

  void take2P(boolean make, boolean clutch) {
    fieldGoalAttempts++;
    if (make) {
      fieldGoals++;
      points += 2;
    }
    player.dropEnergy();
    if (clutch) { makeClutch(make); }
  }

  void take3P(boolean make, boolean clutch) {
    t3fieldGoalAttempts++;
    fieldGoalAttempts++;
    if (make) {
      t3fieldGoals++;
      fieldGoals++;
      points += 3;
    }
    if (clutch) { makeClutch(make); }
  }

  void makeAssist() {
    assists++;
  }

  void makeRebound(boolean make, boolean clutch) {
    if (make) { rebounds++; }
    if (clutch) { makeClutch(true); }
    player.dropEnergy();
  }

  void makeBlock(boolean clutch) {
    blocks++;
    player.dropEnergy();
    if (clutch) { makeClutch(true); }
  }

  void makeSteal() {
    steals++;
    player.dropEnergy();
  }

  void makeTurnover() { turnovers++; }

  void makeFoul() {
    personalFouls++;
  }

  private void makeClutch(boolean make) {
    clutchOpportunities++;
    if (make) { clutchPlays++; }
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

  int getClutchPlays() { return clutchPlays; }

  int getClutchOpportunities() { return clutchOpportunities; }

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
