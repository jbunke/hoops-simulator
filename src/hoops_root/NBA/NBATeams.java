package hoops_root.NBA;

import hoops_root.Date;
import hoops_root.Player;
import hoops_root.Team;

public class NBATeams {
  public static Team BOS(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Kyrie Irving", online);
    roster[1] = NBAPlayers.getPlayer("Jayson Tatum", online);
    roster[2] = NBAPlayers.getPlayer("Al Horford", online);
    roster[3] = NBAPlayers.getPlayer("Jaylen Brown", online);
    roster[4] = NBAPlayers.getPlayer("Gordon Hayward", online);

    printRoster(roster);
    roster = botsFrom(5, roster);
    return new Team("BOS", "Boston Celtics", roster);
  }

  public static Team GSW(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Steph Curry", online);
    roster[1] = NBAPlayers.getPlayer("Kevin Durant", online);
    roster[2] = NBAPlayers.getPlayer("Draymond Green", online);
    roster[3] = NBAPlayers.getPlayer("Klay Thompson", online);
    roster[4] = NBAPlayers.getPlayer("Kevon Looney", online);
    roster[5] = NBAPlayers.getPlayer("DeMarcus Cousins", online);

    printRoster(roster);
    roster = botsFrom(6, roster);
    return new Team("GSW", "Golden State Warriors", roster);
  }

  public static Team HOU(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("James Harden", online);
    roster[1] = NBAPlayers.getPlayer("Chris Paul", online);
    roster[2] = NBAPlayers.getPlayer("Clint Capela", online);

    printRoster(roster);
    roster = botsFrom(3, roster);
    return new Team("HOU", "Houston Rockets", roster);
  }

  public static Team LAL(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Lonzo Ball", online);
    roster[1] = NBAPlayers.getPlayer("Kyle Kuzma", online);
    roster[2] = NBAPlayers.getPlayer("LeBron James", online);
    roster[3] = NBAPlayers.getPlayer("Brandon Ingram", online);
    roster[4] = NBAPlayers.getPlayer("Tyson Chandler", online);

    printRoster(roster);
    roster = botsFrom(5, roster);
    return new Team("LAL", "Los Angeles Lakers", roster);
  }

  public static Team MIL(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Giannis Antetokounmpo", online);

    printRoster(roster);
    roster = botsFrom(1, roster);
    return new Team("MIL", "Milwaukee Bucks", roster);
  }

  public static Team MIN(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Karl-Anthony Towns", online);
    roster[1] = NBAPlayers.getPlayer("Derrick Rose", online);

    printRoster(roster);
    roster = botsFrom(2, roster);
    return new Team("MIN", "Minnesota Timberwolves", roster);
  }

  public static Team OKC(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    // Russell Westbrook
    roster[0] = NBAPlayers.getPlayer("Russell Westbrook", online);
    // Paul George
    roster[1] = NBAPlayers.getPlayer("Paul George", online);
    // Steven Adams
    roster[2] = NBAPlayers.getPlayer("Steven Adams", online);

    printRoster(roster);
    roster = botsFrom(3, roster);
    return new Team("OKC", "Oklahoma City Thunder", roster);
  }

  public static Team PHI(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    // Joel Embiid
    roster[0] = NBAPlayers.getPlayer("Joel Embiid", online);
    // Ben Simmons
    roster[1] = NBAPlayers.getPlayer("Ben Simmons", online);
    // Jimmy Butler
    roster[2] = NBAPlayers.getPlayer("Jimmy Butler", online);

    printRoster(roster);
    roster = botsFrom(3, roster);
    return new Team("PHI", "Philadelphia 76ers", roster);
  }

  public static Team TOR(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Kawhi Leonard", online);
    // Kyle Lowry
    roster[1] = NBAPlayers.getPlayer("Kyle Lowry", online);
    // Jonas Valanciunas
    roster[2] = NBAPlayers.getPlayer("Jonas Valanciunas", online);

    printRoster(roster);
    roster = botsFrom(3, roster);
    return new Team("TOR", "Toronto Raptors", roster);
  }

  public static Team LEGENDS() {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];
    try {
      roster[0] = NBAPlayers.getPlayer("Michael Jordan", false);
      roster[1] = NBAPlayers.getPlayer("Kareem Abdul-Jabbar", false);
      roster[2] = NBAPlayers.getPlayer("Magic Johnson", false);
      roster[3] = NBAPlayers.getPlayer("Kobe Bryant", false);
      roster[4] = NBAPlayers.getPlayer("Tim Duncan", false);
    } catch (Exception e) {
      e.printStackTrace();
    }
    printRoster(roster);
    roster = botsFrom(5, roster);
    return new Team("LEG", "NBA Legends", roster);
  }

  public static Team shell(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("shell", online);

    printRoster(roster);
    roster = botsFrom(0, roster);
    return new Team("SHL", "shell", roster);
  }

  private static void printRoster(Player[] roster) {
    for (Player player : roster) {
      if (player != null) {
        System.out.println(player + " - " + player.overall());
      }
    }
  }

  private static Player[] botsFrom(int index, Player[] roster) {
    for (int i = index; i < 12; i++) {
      roster[i] = new Player(new String[] {String.valueOf(i)}, "Bot",
              new Date(2000, 1, 1));
    }
    return roster;
  }
}
