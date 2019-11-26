package hoops_root.NBA;

import hoops_root.Date;
import hoops_root.Player;
import hoops_root.Team;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NBATeams {

  private static final String BBRLINK = "https://www.basketball-reference.com/teams/";
  private static final String TEAM_ROSTERS_PATH = "resources/team_rosters/";
  private static final int CURRENT_YEAR = 2020;

  public static Team ATL(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "ATL", online);
    printRoster(roster);
    return new Team("ATL", "Atlanta Hawks", roster);
  }

  public static Team BOS(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "BOS", online);
    printRoster(roster);
    return new Team("BOS", "Boston Celtics", roster);
  }

  public static Team BRK(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "BRK", online);
    printRoster(roster);
    return new Team("BRK", "Brooklyn Nets", roster);
  }

  public static Team CHO(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "CHO", online);
    printRoster(roster);
    return new Team("CHO", "Charlotte Hornets", roster);
  }

  public static Team CHI(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "CHI", online);
    printRoster(roster);
    return new Team("CHI", "Chicago Bulls", roster);
  }

  public static Team CLE(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "CLE", online);
    printRoster(roster);
    return new Team("CLE", "Cleveland Cavaliers", roster);
  }

  public static Team DAL(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "DAL", online);
    printRoster(roster);
    return new Team("DAL", "Dallas Mavericks", roster);
  }

  public static Team DEN(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "DEN", online);
    printRoster(roster);
    return new Team("DEN", "Denver Nuggets", roster);
  }

  public static Team DET(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "DET", online);
    printRoster(roster);
    return new Team("DET", "Detroit Pistons", roster);
  }

  public static Team GSW(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "GSW", online);
    printRoster(roster);
    return new Team("GSW", "Golden State Warriors", roster);
  }

  public static Team HOU(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "HOU", online);
    printRoster(roster);
    return new Team("HOU", "Houston Rockets", roster);
  }

  public static Team IND(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "IND", online);
    printRoster(roster);
    return new Team("IND", "Indiana Pacers", roster);
  }

  public static Team LAC(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "LAC", online);
    printRoster(roster);
    return new Team("LAC", "Los Angeles Clippers", roster);
  }

  public static Team LAL(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "LAL", online);
    printRoster(roster);
    return new Team("LAL", "Los Angeles Lakers", roster);
  }

  public static Team MEM(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "MEM", online);
    printRoster(roster);
    return new Team("MEM", "Memphis Grizzlies", roster);
  }

  public static Team MIA(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "MIA", online);
    printRoster(roster);
    return new Team("MIA", "Miami Heat", roster);
  }

  public static Team MIL(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "MIL", online);
    printRoster(roster);
    return new Team("MIL", "Milwaukee Bucks", roster);
  }

  public static Team MIN(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "MIN", online);
    printRoster(roster);
    return new Team("MIN", "Minnesota Timberwolves", roster);
  }

  public static Team NOP(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "NOP", online);
    printRoster(roster);
    return new Team("NOP", "New Orleans Pelicans", roster);
  }

  public static Team NYK(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "NYK", online);
    printRoster(roster);
    return new Team("NYK", "New York Knicks", roster);
  }

  public static Team OKC(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "OKC", online);
    printRoster(roster);
    return new Team("OKC", "Oklahoma City Thunder", roster);
  }

  public static Team ORL(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "ORL", online);
    printRoster(roster);
    return new Team("ORL", "Orlando Magic", roster);
  }

  public static Team PHO(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "PHO", online);
    printRoster(roster);
    return new Team("PHO", "Phoenix Suns", roster);
  }

  public static Team POR(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "POR", online);
    printRoster(roster);
    return new Team("POR", "Portland Trail Blazers", roster);
  }

  public static Team PHI(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "PHI", online);
    printRoster(roster);
    return new Team("PHI", "Philadelphia 76ers", roster);
  }

  public static Team SAC(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "SAC", online);
    printRoster(roster);
    return new Team("SAC", "Sacramento Kings", roster);
  }

  public static Team SAS(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "SAS", online);
    printRoster(roster);
    return new Team("SAS", "San Antonio Spurs", roster);
  }

  public static Team TOR(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "TOR", online);
    printRoster(roster);
    return new Team("TOR", "Toronto Raptors", roster);
  }

  public static Team UTA(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "UTA", online);
    printRoster(roster);
    return new Team("UTA", "Utah Jazz", roster);
  }

  public static Team WAS(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = NBARosterFetcher(CURRENT_YEAR, "WAS", online);
    printRoster(roster);
    return new Team("WAS", "Washington Wizards", roster);
  }

  public static Team GUARDS() {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];
    roster[0] = NBAPlayers.getPlayer("Michael Jordan", false, true);
    roster[1] = NBAPlayers.getPlayer("Stephen Curry", false, true);
    roster[2] = NBAPlayers.getPlayer("James Harden", false, true);
    roster[3] = NBAPlayers.getPlayer("Chris Paul", false, true);
    roster[4] = NBAPlayers.getPlayer("Damian Lillard", false, true);
    roster[5] = NBAPlayers.getPlayer("Derrick Rose", false, true);
    roster[6] = NBAPlayers.getPlayer("Russell Westbrook", false, true);
    roster[7] = NBAPlayers.getPlayer("Kyrie Irving", false, true);
    roster[8] = NBAPlayers.getPlayer("Klay Thompson", false, true);
    printRoster(roster);
    roster = botsFrom(9, roster);
    return new Team("GUA", "NBA Guards", roster);
  }

  public static Team YOUNG() {
    Player[] roster = new Player[] {
            NBAPlayers.getPlayer("Luka Dončić", false, false),
            NBAPlayers.getPlayer("Trae Young", false, false),
            NBAPlayers.getPlayer("Giannis Antetokounmpo", false, false),
            NBAPlayers.getPlayer("Nikola Jokić", false, false),
            NBAPlayers.getPlayer("D'Angelo Russell", false, false),
            NBAPlayers.getPlayer("Karl-Anthony Towns", false, false),
            NBAPlayers.getPlayer("De'Aaron Fox", false, false),
            NBAPlayers.getPlayer("Devin Booker", false, false),
            NBAPlayers.getPlayer("Joel Embiid", false, false),
            NBAPlayers.getPlayer("Zach LaVine", false, false),
            NBAPlayers.getPlayer("Donovan Mitchell", false, false),
            NBAPlayers.getPlayer("Bradley Beal", false, false)
    };
    return new Team("LDN", "London Kings", roster);
  }

  public static Team ESTABLISHED() {
    Player[] roster = new Player[] {
            NBAPlayers.getPlayer("Damian Lillard", false, false),
            NBAPlayers.getPlayer("James Harden", false, false),
            NBAPlayers.getPlayer("Kyrie Irving", false, false),
            NBAPlayers.getPlayer("Stephen Curry", false, false),
            NBAPlayers.getPlayer("LeBron James", false, false),
            NBAPlayers.getPlayer("Anthony Davis", false, false),
            NBAPlayers.getPlayer("Kawhi Leonard", false, false),
            NBAPlayers.getPlayer("Paul George", false, false),
            NBAPlayers.getPlayer("Pascal Siakam", false, false),
            NBAPlayers.getPlayer("Russell Westbrook", false, false),
            NBAPlayers.getPlayer("Kevin Durant", false, false),
            NBAPlayers.getPlayer("Kemba Walker", false, false)
    };
    return new Team("MAN", "Manchester Knights", roster);
  }

  public static Team FORWARDS() {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];
    roster[0] = NBAPlayers.getPlayer("Joel Embiid", false, true);
    roster[1] = NBAPlayers.getPlayer("Giannis Antetokounmpo", false, true);
    roster[2] = NBAPlayers.getPlayer("Kareem Abdul-Jabbar", false, true);
    roster[3] = NBAPlayers.getPlayer("Karl-Anthony Towns", false, true);
    roster[4] = NBAPlayers.getPlayer("LeBron James", false, true);
    roster[5] = NBAPlayers.getPlayer("Luka Dončić", false, true);
    printRoster(roster);
    roster = botsFrom(5, roster);
    return new Team("FOR", "NBA Forwards", roster);
  }

  public static Team LEGENDS() {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];
    roster[0] = NBAPlayers.getPlayer("Michael Jordan", false, true);
    roster[1] = NBAPlayers.getPlayer("Kareem Abdul-Jabbar", false, true);
    roster[2] = NBAPlayers.getPlayer("Magic Johnson", false, true);
    roster[3] = NBAPlayers.getPlayer("Kobe Bryant", false, true);
    roster[4] = NBAPlayers.getPlayer("Tim Duncan", false, true);
    printRoster(roster);
    roster = botsFrom(5, roster);
    return new Team("LEG", "NBA Legends", roster);
  }

  private static Player[] getOfflineRoster(String teamCode) {
    String path = TEAM_ROSTERS_PATH + teamCode + ".txt";
    List<Player> players = new ArrayList<>();

    try {
      BufferedReader br = new BufferedReader(new FileReader(path));
      while (br.ready()) {
        String name = br.readLine();

        if (name.trim().length() > 0) {
          players.add(NBAPlayers.NBAPlayerFromName(name, false, false, ""));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return listToArrayPlayers(players);
  }

  private static Player[] listToArrayPlayers(List<Player> players) {
    for (int i = 0; i < players.size(); i++) {
      if (players.get(i) == null) {
        players.remove(i);
        i--;
      }
    }

    Player[] roster = new Player[players.size()];

    for (int i = 0; i < roster.length; i++) {
      roster[i] = players.get(i);
    }

    return roster;
  }

  private static Player[] NBARosterFetcher(int year, String teamCode, boolean online) {
    if (!online) return getOfflineRoster(teamCode);

    String link = BBRLINK + teamCode + "/" + year + ".html";
    URL url = null;
    try {
      url = new URL(link);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    List<Player> dynamicList = new ArrayList<>();

    if (url == null) return new Player[0];

    String line;
    URLConnection connection;

    try {
      connection = url.openConnection();
      Scanner scanner = new Scanner(connection.getInputStream());
      boolean start = false;

      while (scanner.hasNext()) {
        line = scanner.nextLine();

        if (!start && line.contains("<tbody>")) {
          start = true;
        }

        if (start && line.contains("<a href='/players/")) {
          line = line.substring(line.indexOf("/players/") + 9);

          String playerLinkTail = line.substring(0, line.indexOf("html") + 4);

          String name = line.substring(line.indexOf(">") + 1, line.indexOf("<"));

          dynamicList.add(NBAPlayers.NBAPlayerFromName(name, online, false, playerLinkTail));
        }

        if (start && line.contains("</table>"))
          break;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    Player[] roster = listToArrayPlayers(dynamicList);
    writeRoster(teamCode, roster);

    return roster;
  }

  private static void writeRoster(String teamCode, Player[] roster) {
    String path = TEAM_ROSTERS_PATH + teamCode + ".txt";
    try {
      BufferedWriter bw = new BufferedWriter(new FileWriter(path));
      for (Player player : roster) {
        bw.write(player.goesBy());
        bw.newLine();
      }
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void printRoster(Player[] roster) {
    for (Player player : roster) {
      if (player != null) {
        System.out.println(player + " " + player.overall());
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
