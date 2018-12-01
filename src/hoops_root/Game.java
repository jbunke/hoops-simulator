package hoops_root;

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

  Game(Team away, Team home) {
    this.away = away;
    this.home = home;
    this.date = new Date(2019, 1, 1); // TODO: placeholder
    this.score = new int[] {0, 0};
    this.scoreBySegment = new ArrayList<>();
    this.playerStats = new HashMap<>();
    this.awayPlaying = away.getStarters();
    this.homePlaying = home.getStarters();
    this.played = false;

    away.addGameToSchedule(this);
    home.addGameToSchedule(this);
  }

  private void initializeStats() {
    for (Player a : away.getRoster()) {
      if (a.status() == Player.Status.FIT) {
        playerStats.put(a, PlayerGameStats.startGame(a, date));
      }
    }
    for (Player h : home.getRoster()) {
      if (h.status() == Player.Status.FIT) {
        playerStats.put(h, PlayerGameStats.startGame(h, date));
      }
    }
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
        return player.height + (player.getStats()[2]);
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

  private int rebound(int def) {
    Player awayPlayer = awayPlaying[(int)(Math.random() * 5)];
    Player homePlayer = homePlaying[(int)(Math.random() * 5)];
    return contestRebound(awayPlayer, homePlayer, def);
  }

  private int contestRebound(Player awayPlayer, Player homePlayer, int def) {
    double aRand = (4 + Math.random()) / 5.0;
    double hRand = (4 + Math.random()) / 5.0;

    if (def == 0) {
      aRand += 0.3;
    } else {
      hRand += 0.3;
    }

    if (getContestStat(awayPlayer, Trait.HEIGHT_REBOUNDING) * aRand >
            getContestStat(homePlayer, Trait.HEIGHT_REBOUNDING) * hRand) {
      playerStats.get(awayPlayer).makeRebound();
      return 0;
    }
    playerStats.get(homePlayer).makeRebound();
    return 1;
  }

  private boolean shoot (Player player, int team, Player assist) {
    double odds = (int)(Math.random() * 196);
    boolean make = player.getStats()[0] > odds;

    if (odds % 3 == 0) {
      if (make) {
        scoreBySegment.get(scoreBySegment.size() - 1)[team] += 3;
        if (assist != null) {
          playerStats.get(assist).makeAssist();
        }
      }
      playerStats.get(player).take3P(make);
    } else {
      if (make) {
        scoreBySegment.get(scoreBySegment.size() - 1)[team] += 2;
        if (assist != null) {
          playerStats.get(assist).makeAssist();
        }
      }
      playerStats.get(player).take2P(make);
    }
    return make;
  }

  private boolean drive(Player player, int team, Player assist) {
    int opps = 1 - team;
    Player defender = getPlayer(opps, (int)(Math.random() * 5));

    // dunk is made if player is better dunker than defender is blocker or if player is more athletic
    boolean make = (player.height + player.getStats()[7] >
            defender.height + defender.getStats()[8] ||
            player.getStats()[2] + (20 * Math.random()) > defender.getStats()[2]);
    if (make) {
      scoreBySegment.get(scoreBySegment.size() - 1)[team] += 2;
      if (assist != null) {
        playerStats.get(assist).makeAssist();
      }
    } else {
      playerStats.get(defender).makeBlock();
    }
    playerStats.get(player).take2P(make);

    return make;
  }

  private void simSegment(int seconds, int team,
                  int handler) {
    int clock = 25;
    boolean switchPos = false;
    Player assist = null;
    while (seconds > 0) {
      // a play
      Player hasBall = getPlayer(team, handler);
      int time = 5 + (int)(6 * Math.random());
      // TODO: clutch ??
      if (clock < time) {
        if (hasBall.getStats()[8] > hasBall.getStats()[0] &&
                Math.random() < .7) {
          switchPos = drive(hasBall, team, assist);
        } else {
          switchPos = shoot(hasBall, team, assist);
          if (!switchPos) {
            int newTeam = rebound(1 - team);
            switchPos = newTeam != team;
            handler = (int)(Math.random() * 5);
          }
        }
      } else {
        // pass, shoot, or drive
        double decision = Math.random();

        double tShoot = Math.pow(hasBall.getStats()[0], 2) / 50000.0;
        double tDrive = tShoot + (hasBall.getStats()[8] / 500.0);

        if (decision < tShoot) {
          switchPos = shoot(hasBall, team, assist);
          if (!switchPos) {
            int newTeam = rebound(1 - team);
            switchPos = newTeam != team;
            handler = (int)(Math.random() * 5);
          }
        } else if (decision < tDrive) {
          switchPos = drive(hasBall, team, assist);
        } else {
          // pass
          int passTo = handler;
          while (passTo == handler) {
            passTo = (int)(Math.random() * 5);
          }
          assist = hasBall;
          handler = passTo;
        }
      }

      clock -= time;
      seconds -= time;
      if (switchPos || clock <= 0) {
        clock = 25;
        team = 1 - team;
        if (Math.random() < .2) {
          handler = 0;
        } else {
          handler = (int)(Math.random() * 5);
        }
        assist = null;
        switchPos = false;
      }
    }
  }

  void sim() {
    assert (!played);
    // initialize stats map
    initializeStats();
    // get starters and bench
    awayPlaying = away.getStarters();
    homePlaying = home.getStarters();
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

  void randomSim() {
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
