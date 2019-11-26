package hoops_root;

import hoops_root.stats.statistical_comparators.StatComparator;

import java.util.*;

public class Game {
  final Team away;
  final Team home;
  final private Date date;
  int[] score = new int[2]; // {away, home}
  private List<int[]> scoreBySegment;
  private Map<Player, PlayerGameStats> playerStats;
  private Player[] awayPlaying;
  private Player[] homePlaying;
  boolean played;

  private enum Trait {
    HEIGHT,
    HEIGHT_REBOUNDING
  }

  // STAT INDICES
  private static final int SHOOTING = 0, REBOUNDING = 1, ATHLETICISM = 2,
          PASSING = 3, EFFICIENCY = 4, CLUTCH = 5, FREE_THROW = 6, DEFENSE = 7,
          SCORING = 8, PRONENESS_TO_INJURY = 9, FOUL_TENDENCY = 10;

  Game(Team away, Team home) {
    this.away = away;
    this.home = home;
    this.date = new Date(2019, 1, 1); // TODO: placeholder
    this.score = new int[] {0, 0};
    this.scoreBySegment = new ArrayList<>();
    this.playerStats = new HashMap<>();
    this.awayPlaying = away.substitutions();
    this.homePlaying = home.substitutions();
    this.played = false;

    away.addGameToSchedule(this);
    home.addGameToSchedule(this);
  }

  private void initializeStats() {
    for (Player a : away.getRoster()) {
      if (a.status() == Player.Status.FIT
              || a.status() == Player.Status.FATIGUED) {
        playerStats.put(a, PlayerGameStats.startGame(a, date));
      }
    }
    for (Player h : home.getRoster()) {
      if (h.status() == Player.Status.FIT
              || h.status() == Player.Status.FATIGUED) {
        playerStats.put(h, PlayerGameStats.startGame(h, date));
      }
    }

    /*
    if (playerStats.size() != 24) {
      System.out.print("PANIC");
    }
    */
  }

  private int contestAll(Trait trait) {
    // determine winner of a trait contest
    int aBest = 0;
    int hBest = 0;

    for (Player player : awayPlaying) {
      int val = getContestStat(player, trait);

      if (val > aBest) {
        aBest = val;
      }
    }

    for (Player player : homePlaying) {
      int val = getContestStat(player, trait);

      if (val > hBest) {
        hBest = val;
      }
    }

    double aRand = (4 + Math.random()) / 5.0;
    double hRand = (4 + Math.random()) / 5.0;

    if (aBest * aRand > hBest * hRand) {
      return 0;
    }
    return 1;
  }

  private int getContestStat(Player player, Trait trait) {
    switch (trait) {
      case HEIGHT_REBOUNDING:
        return (player.height / 2) + (player.getStats()[2]);
      case HEIGHT:
        return player.height;
      default:
        return 0;
    }
  }

  private Player getPlayer(int team, int handler) {
    if (team == 0) {
      return awayPlaying[handler];
    }
    return homePlaying[handler];
  }

  private int alleyOopTeammate(int team, int handler) {
    Player[] candidates = new Player[4];
    int[] indices = new int[4];
    int inserted = 0;
    Player[] alleyOopTeam;
    if (team == 0) {
      alleyOopTeam = awayPlaying;
    } else {
      alleyOopTeam = homePlaying;
    }
    for (int i = 0; i < 5; i++) {
      if (i != handler) {
        candidates[inserted] = alleyOopTeam[i];
        indices[inserted] = i;
        inserted++;
      }
    }
    int totalVision = 0;
    int[] thresholds = new int[] {candidates[0].getStats()[SCORING],
            candidates[0].getStats()[SCORING] + candidates[1].getStats()[SCORING],
            candidates[0].getStats()[SCORING] + candidates[1].getStats()[SCORING] + candidates[2].getStats()[SCORING]};
    for (Player candidate : candidates) {
      totalVision += candidate.getStats()[4];
    }
    int seed = (int)(Math.random() * totalVision);
    for (int i = 0; i < 3; i++) {
      if (seed < thresholds[i]) {
        return indices[i];
      }
    }
    return indices[3];
  }

  private int rebound(int def, boolean clutch) {
    // less likelihood of having a back court player contesting for a rebound
    int awayIndex = (int)(Math.random() * 5);
    if (awayIndex < 2) {
      awayIndex = (int)(Math.random() * 5);
    }
    int homeIndex = (int)(Math.random() * 5);
    if (homeIndex < 2) {
      homeIndex = (int)(Math.random() * 5);
    }

    Player awayPlayer = awayPlaying[awayIndex];
    Player homePlayer = homePlaying[homeIndex];
    return contestRebound(awayPlayer, homePlayer, def, clutch);
  }

  private int contestRebound(Player awayPlayer, Player homePlayer, int def,
                             boolean clutch) {
    double aRand = (4 + Math.random()) / 5.0;
    double hRand = (4 + Math.random()) / 5.0;

    if (def == 0) {
      aRand += 0.3;
    } else {
      hRand += 0.3;
    }

    boolean make = getContestStat(awayPlayer, Trait.HEIGHT_REBOUNDING) * aRand >
            getContestStat(homePlayer, Trait.HEIGHT_REBOUNDING) * hRand;

    if (clutch) {
      make = getContestStat(awayPlayer, Trait.HEIGHT_REBOUNDING) * aRand +
              awayPlayer.getStats()[CLUTCH] >
              (getContestStat(homePlayer, Trait.HEIGHT_REBOUNDING) * hRand) +
                      homePlayer.getStats()[CLUTCH];
    }

    if (make) {
      playerStats.get(awayPlayer).makeRebound(true, clutch);
      playerStats.get(homePlayer).makeRebound(false, clutch);
      return 0;
    }
    playerStats.get(homePlayer).makeRebound(true, clutch);
    playerStats.get(awayPlayer).makeRebound(false, clutch);
    return 1;
  }

  private boolean shoot (Player player, int team, Player assist, boolean clutch) {
    double odds = (int)(Math.random() * 225);
    if (clutch) {
      odds += (int)(Math.random() * 60) - player.getStats()[CLUTCH];
    }
    boolean make = player.getStats()[SHOOTING] > odds;

    if (odds % 2 == 0) {
      if (make) {
        scoreBySegment.get(scoreBySegment.size() - 1)[team] += 3;
        if (assist != null) {
          playerStats.get(assist).makeAssist();
        }
      }
      playerStats.get(player).take3P(make, clutch);
    } else {
      if (make) {
        scoreBySegment.get(scoreBySegment.size() - 1)[team] += 2;
        if (assist != null) {
          playerStats.get(assist).makeAssist();
        }
      }
      playerStats.get(player).take2P(make, clutch);
    }
    return make;
  }

  private boolean drive(Player player, int team, Player assist, boolean clutch) {
    int opps = 1 - team;
    Player defender = getPlayer(opps, (int)(Math.random() * 5));

    // dunk is made if player is better dunker than defender is blocker or if player is more athletic
    boolean make = (player.height * 2 + player.getStats()[SCORING] + (40 * Math.random()) >
            defender.height * 2 + defender.getStats()[DEFENSE] + (20 * Math.random()) ||
            player.height * 2 + player.getStats()[ATHLETICISM] + (40 * Math.random()) >
                    defender.height * 2 + defender.getStats()[ATHLETICISM] + (20 * Math.random()));
    if (clutch) {
      make = (player.height * 2 + player.getStats()[SCORING] + (40 * Math.random()) >
              defender.height * 2 + defender.getStats()[DEFENSE] + (20 * Math.random()) ||
              player.height * 2 + player.getStats()[ATHLETICISM] + (40 * Math.random()) >
                      defender.height * 2 + defender.getStats()[ATHLETICISM] + (20 * Math.random()) ||
              player.getStats()[CLUTCH] > defender.getStats()[CLUTCH]);
    }
    if (make) {
      scoreBySegment.get(scoreBySegment.size() - 1)[team] += 2;
      if (assist != null) {
        playerStats.get(assist).makeAssist();
      }
    } else {
      playerStats.get(defender).makeBlock(clutch);
    }
    playerStats.get(player).take2P(make, clutch);

    if ((1 + defender.getStats()[FOUL_TENDENCY]) / 10.0 > Math.random()) {
      // foul
      playerStats.get(defender).makeFoul();
      if (playerStats.get(defender).getFouls() == 6) {
        // TODO: foul out
        defender.setStatus(Player.Status.FOULED_OUT);
        if (opps == 0) {
          awayPlaying = away.substitutions();
        } else {
          homePlaying = home.substitutions();
        }
      }

      if (make) {
        // And 1
        freeThrow(player, team, 1, scoreBySegment.size() >= 4);
      } else {
        make = freeThrow(player, team, 2, scoreBySegment.size() >= 4);
      }
    }

    return make;
  }

  private boolean pass(Player player, int team) {
    int opps = 1 - team;
    Player defender = getPlayer(opps, (int)(Math.random() * 5));

    // pass can be intercepted
    double defOdds = defender.getStats()[DEFENSE] + (20 * Math.random());
    double offOdds = player.getStats()[PASSING] + (Math.sqrt(Math.random() * 1e4));

    boolean make = offOdds > defOdds;

    if (!make) {
      playerStats.get(defender).makeSteal();
      playerStats.get(player).makeTurnover();
    }

    // return !make because if the pass is made, possession does NOT switch
    return !make;
  }

  private boolean freeThrow(Player player, int team, int amount, boolean clutch) {
    int taken = 0;
    boolean made = false;
    while (taken < amount) {
      double odds = Math.random() * 100;
      if (clutch) {
        odds += (int)(Math.random() * 100) - player.getStats()[5];
      }
      boolean make = (player.getStats()[FREE_THROW] - 6 > odds);
      if (make) {
        scoreBySegment.get(scoreBySegment.size() - 1)[team] += 1;
      }
      playerStats.get(player).takeFT(make, clutch);
      made = made || make;
      taken++;
    }
    return made;
  }

  private int choosePass(int team, int handler, double urgency) {
    int passTo;

    // Non-random or random recipient
    if (urgency > Math.random()) {
      List<Player> teammates = new ArrayList<>();
      Map<Player, Integer> playersIndices = new HashMap<>();

      for (int i = 0; i < 5; i++) {
        if (i == handler) continue;
        teammates.add(getPlayer(team, i));
        playersIndices.put(getPlayer(team, i), i);
      }

      teammates.sort(new StatComparator(StatComparator.Stat.OVR));

      int listIndex = (int)(Math.pow(Math.random() * 1.8, 2.0));
      passTo = playersIndices.get(teammates.get(listIndex));
    } else {
      do {
        passTo = (int)(Math.random() * 5);
      } while (passTo == handler);
    }

    return passTo;
  }

  private void simSegment(int seconds, int team,
                  int handler) {
    int clock = 25;
    boolean switchPos;
    Player assist = null;
    while (seconds > 0) {
      // a play
      boolean clutch = seconds < 120 && scoreBySegment.size() >= 4;
      Player hasBall = getPlayer(team, handler);
      int time = 4 + (int)(6 * Math.random());
      if (clock <= time || seconds <= time) {
        if (hasBall.getStats()[SCORING] > hasBall.getStats()[SHOOTING] &&
                Math.random() < .7) {
          switchPos = drive(hasBall, team, assist, clutch);
        } else {
          switchPos = shoot(hasBall, team, assist, clutch);
          if (!switchPos) {
            int newTeam = rebound(1 - team, clutch);
            switchPos = newTeam != team;
            handler = (int)(Math.random() * 5);
          }
        }
      } else {
        // pass, shoot, or drive
        double decision = Math.random();

        double tShoot = hasBall.getStats()[SHOOTING] / 300.0;
        double tDrive = tShoot + (hasBall.getStats()[SCORING] / 300.0);
        double tAlleyOop = tDrive + (hasBall.getStats()[PASSING] / 700.0);

        double passingMultiplier = (90 / (double) hasBall.getStats()[PASSING]);
        double timeMultiplier = ((25 - (clock - time)) / (double)25);

        double multiplier = timeMultiplier * passingMultiplier;

        tShoot *= multiplier;
        tDrive *= multiplier;
        tAlleyOop *= multiplier;

        if (decision < tShoot) {
          switchPos = shoot(hasBall, team, assist, clutch);
          if (!switchPos) {
            int newTeam = rebound(1 - team, clutch);
            switchPos = newTeam != team;
            handler = (int)(Math.random() * 5);
          }
        } else if (decision < tDrive) {
          switchPos = drive(hasBall, team, assist, clutch);
          if (!switchPos) {
            int newTeam = rebound(1 - team, clutch);
            switchPos = newTeam != team;
            handler = (int)(Math.random() * 5);
          }
        } else if (decision < tAlleyOop) {
          // alley-oop
          handler = alleyOopTeammate(team, handler);
          assist = hasBall;
          switchPos = drive(getPlayer(team, handler), team, assist, clutch);
          if (!switchPos) {
            int newTeam = rebound(1 - team, clutch);
            switchPos = newTeam != team;
            handler = (int)(Math.random() * 5);
          }
        } else {
          // pass
          int passTo = choosePass(team, handler, timeMultiplier);

          assist = hasBall;
          handler = passTo;
          // pass can be intercepted and a steal and turnover is logged
          switchPos = pass(hasBall, team);
        }
      }

      // MINUTE PASSED, STAT UPDATE
      if (seconds % 60 < 30 && (seconds - time) % 60 > 30) {
        for (Player a : awayPlaying) {
          playerStats.get(a).playMinute();
        }
        for (Player h : homePlaying) {
          playerStats.get(h).playMinute();
        }
        // substitute players based on fatigue
        awayPlaying = away.substitutions();
        homePlaying = home.substitutions();
      }

      clock -= time;
      seconds -= time;
      if (switchPos || clock <= 0) {
        clock = 25;
        team = 1 - team;
        handler = (int)(Math.random() * 5);
        if (Math.random() < 0.5 && handler != 0)
          handler = (int)(Math.random() * 5);
        assist = null;
      }
    }
  }

  void sim() {
    assert (!played);
    // initialize stats map
    initializeStats();
    // get starters and bench
    awayPlaying = away.substitutions();
    homePlaying = home.substitutions();
    assert (awayPlaying.length == 5 && homePlaying.length == 5);

    // start game
    int quarter = 0;
    while (quarter < 4 || score[0] == score[1]) {
      scoreBySegment.add(new int[] {0, 0});

      // tip: Possession is either 0 (away) or 1 (home) depending on tip winner
      int possession = contestAll(Trait.HEIGHT_REBOUNDING);
      // ball is tipped back to a random player (possessor: 0 - 5)
      int possessor = (int)(Math.random() * 5);
      if (quarter < 4) {
        simSegment(720, possession, possessor); // 720 seconds in 12 minutes / a quarter
      } else {
        simSegment(300, possession, possessor); // 300 seconds in 5 minutes / an OT
      }

      score[0] += scoreBySegment.get(quarter)[0];
      score[1] += scoreBySegment.get(quarter)[1];

      quarter++;
    }
  }

  void simulate() {
    assert (!played);

    sim();

    played = true;
    outcome();
  }

  void outcome() {
    assert (played);
    assert (score[0] != score[1]);

    if (score[0] > score[1]) {
      away.getRecord().addWin();
      home.getRecord().addLoss();
    } else {
      away.getRecord().addLoss();
      home.getRecord().addWin();
    }

    saveStats();

    away.shiftGameToPast(this);
    home.shiftGameToPast(this);
  }

  void saveStats() {
    Set<Player> played = playerStats.keySet();

    for (Player player : played) {
      player.addToSeasonStats(playerStats.get(player));
      player.statUpdate();

      if (player.status() == Player.Status.FOULED_OUT) {
        player.unfoul();
      }
    }
  }

  /*
  Team winner() {
    assert (played);
    assert (score[0] != score[1]);

    if (score[0] > score[1]) {
      return away;
    }
    return home;
  }
  */

  private void printXTimes(String msg, int x) {
    for (int i = 0; i < x; i++) {
      System.out.print(msg);
    }
  }

  private void printRow(String[] sections, int[] spaces) {
    assert (sections.length == spaces.length);
    for (int i = 0; i < sections.length; i++) {
      System.out.print(sections[i]);
      printXTimes(" ", spaces[i] - sections[i].length());
      System.out.print("|");
    }

    System.out.println();
  }

  private void teamBoxScore(Team team) {
    System.out.println(team.name());
    printXTimes("-", 83);
    System.out.println();
    printRow(new String[] {"PLAYER", "MIN", "FG", "3PT", "FT",
                    "REB", "AST", "BLK", "STL", "TOV", "FLS", "PTS"},
            new int[] {27, 5, 6, 6, 6, 3, 3, 3, 3, 3, 3, 3});
    int teamFG = 0;
    int teamFGA = 0;
    int team3P = 0;
    int team3PA = 0;
    int teamFT = 0;
    int teamFTA = 0;
    int teamREB = 0;
    int teamAST = 0;
    int teamBLK = 0;
    int teamSTL = 0;
    int teamTOV = 0;
    int teamFLS = 0;
    int teamPTS = 0;
    for (Player player : team.getRoster()) {
      if (playerStats.containsKey(player)) {
        PlayerGameStats plStats = playerStats.get(player);
        printRow(new String[] {player.goesBy(), String.valueOf(plStats.mins()), plStats.FG(),
                        plStats.THREE(), plStats.FT(), String.valueOf(plStats.getRebounds()),
                        String.valueOf(plStats.getAssists()), String.valueOf(plStats.getBlocks()),
                        String.valueOf(plStats.getSteals()), String.valueOf(plStats.getTurnovers()),
                        String.valueOf(plStats.getFouls()), String.valueOf(plStats.getPoints())},
                new int[] {27, 5, 6, 6, 6, 3, 3, 3, 3, 3, 3, 3});
        teamFG += plStats.getFieldGoals();
        teamFGA += plStats.getFieldGoalAttempts();
        team3P += plStats.getT3fieldGoals();
        team3PA += plStats.getT3fieldGoalAttempts();
        teamFT += plStats.getFreeThrows();
        teamFTA += plStats.getFreeThrowAttempts();
        teamREB += plStats.getRebounds();
        teamAST += plStats.getAssists();
        teamBLK += plStats.getBlocks();
        teamSTL += plStats.getSteals();
        teamTOV += plStats.getTurnovers();
        teamFLS += plStats.getFouls();
        teamPTS += plStats.getPoints();
      } else {
        if (player.status() == Player.Status.INJURED) {
          System.out.println("INJURED - DID NOT PLAY");
        } else if (player.status() == Player.Status.SUSPENDED) {
          System.out.println("SUSPENDED");
        } else {
          System.out.println();
        }
      }
    }
    printRow(new String[] {"TEAM", "N/A", teamFG + "-" + teamFGA,
                    team3P + "-" + team3PA, teamFT + "-" + teamFTA,
                    String.valueOf(teamREB), String.valueOf(teamAST),
                    String.valueOf(teamBLK), String.valueOf(teamSTL),
                    String.valueOf(teamTOV), String.valueOf(teamFLS),
                    String.valueOf(teamPTS)},
            new int[] {27, 5, 6, 6, 6, 3, 3, 3, 3, 3, 3, 3});
    printXTimes("-", 83);
    System.out.println();
  }

  private void printScore() {
    String[] header = new String[scoreBySegment.size() + 2];
    header[0] = "";
    header[1] = "1";
    header[2] = "2";
    header[3] = "3";
    header[4] = "4";
    for (int i = 4; i < scoreBySegment.size(); i++) {
      header[i + 1] = "OT" + String.valueOf(i - 3);
    }
    header[header.length - 1] = "T";
    int[] spaces = new int[header.length];
    for (int i = 0; i < spaces.length; i++) {
      spaces[i] = 3;
    }
    printRow(header, spaces);

    String[] awayScores = new String[header.length];
    awayScores[0] = away.code();
    awayScores[awayScores.length - 1] = String.valueOf(score[0]);
    String[] homeScores = new String[header.length];
    homeScores[0] = home.code();
    homeScores[homeScores.length - 1] = String.valueOf(score[1]);

    for (int i = 0; i < scoreBySegment.size(); i++) {
      awayScores[i + 1] = String.valueOf(scoreBySegment.get(i)[0]);
      homeScores[i + 1] = String.valueOf(scoreBySegment.get(i)[1]);
    }

    printRow(awayScores, spaces);
    printRow(homeScores, spaces);
  }

  void printBoxScore() {
    assert (played);
    System.out.println("\n" + this + "\nBox Score:");
    printXTimes("-", 10);
    System.out.println();

    teamBoxScore(away);
    teamBoxScore(home);

    System.out.println();
    printScore();
  }

  @Override
  public String toString() {
    if (played) {
      if (score[0] > score[1]) {
        return away + " def. " + home + " " + score[0] + "-" + score[1];
      }
      return home + " def. " + away + " " + score[1] + "-" + score[0];
    }
    return away + " at " + home;
  }
}
