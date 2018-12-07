package hoops_root.NBA;

import hoops_root.Date;
import hoops_root.Player;
import hoops_root.Team;

public class NBATeams {

  public static Team ATL(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Trae Young", online, false);
    roster[1] = NBAPlayers.getPlayer("Vince Carter", online, false);
    roster[2] = NBAPlayers.getPlayer("Kent Bazemore", online, false);
    roster[3] = NBAPlayers.getPlayer("Jeremy Lin", online, false);

    printRoster(roster);
    roster = botsFrom(4, roster);
    return new Team("ATL", "Atlanta Hawks", roster);
  }

  public static Team BOS(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Kyrie Irving", online, false);
    roster[1] = NBAPlayers.getPlayer("Jayson Tatum", online, false);
    roster[2] = NBAPlayers.getPlayer("Al Horford", online, false);
    roster[3] = NBAPlayers.getPlayer("Jaylen Brown", online, false);
    roster[4] = NBAPlayers.getPlayer("Gordon Hayward", online, false);
    roster[5] = NBAPlayers.getPlayer("Terry Rozier", online, false);

    printRoster(roster);
    roster = botsFrom(6, roster);
    return new Team("BOS", "Boston Celtics", roster);
  }

  public static Team BRK(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("DAngelo Russell", online, false);
    roster[1] = NBAPlayers.getPlayer("Allen Crabbe", online, false);
    roster[2] = NBAPlayers.getPlayer("Caris LeVert", online, false);
    roster[3] = NBAPlayers.getPlayer("Spencer Dinwiddie", online, false);

    printRoster(roster);
    roster = botsFrom(4, roster);
    return new Team("BRK", "Brooklyn Nets", roster);
  }

  public static Team CHA(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Kemba Walker", online, false);
    roster[1] = NBAPlayers.getPlayer("Jeremy Lamb", online, false);
    roster[2] = NBAPlayers.getPlayer("Michael KiddGilchrist", online, false);
    roster[3] = NBAPlayers.getPlayer("Frank Kaminsky", online, false);

    printRoster(roster);
    roster = botsFrom(4, roster);
    return new Team("CHA", "Charlotte Hornets", roster);
  }

  public static Team CHI(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Zach LaVine", online, false);
    roster[1] = NBAPlayers.getPlayer("Wendell Carter", online, false);
    roster[2] = NBAPlayers.getPlayer("Jabari Parker", online, false);
    roster[3] = NBAPlayers.getPlayer("Robin Lopez", online, false);

    printRoster(roster);
    roster = botsFrom(4, roster);
    return new Team("CHI", "Chicago Bulls", roster);
  }

  public static Team CLE(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Kevin Love", online, false);
    roster[1] = NBAPlayers.getPlayer("Tristan Thompson", online, false);
    roster[2] = NBAPlayers.getPlayer("Jordan Clarkson", online, false);
    roster[3] = NBAPlayers.getPlayer("JR Smith", online, false);

    printRoster(roster);
    roster = botsFrom(4, roster);
    return new Team("CLE", "Cleveland Cavaliers", roster);
  }

  public static Team DAL(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Luka Doncic", online, false);
    roster[1] = NBAPlayers.getPlayer("DeAndre Jordan", online, false);
    roster[2] = NBAPlayers.getPlayer("Dwight Powell", online, false);
    roster[3] = NBAPlayers.getPlayer("Harrison Barnes", online, false);

    printRoster(roster);
    roster = botsFrom(4, roster);
    return new Team("DAL", "Dallas Mavericks", roster);
  }

  public static Team DEN(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Jamal Murray", online, false);
    roster[1] = NBAPlayers.getPlayer("Paul Millsap", online, false);
    roster[2] = NBAPlayers.getPlayer("Nikola Jokic", online, false);
    roster[3] = NBAPlayers.getPlayer("Juan Hernangomez", online, false);
    roster[4] = NBAPlayers.getPlayer("Malik Beasley", online, false);

    printRoster(roster);
    roster = botsFrom(5, roster);
    return new Team("DEN", "Denver Nuggets", roster);
  }

  public static Team DET(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Blake Griffin", online, false);
    roster[1] = NBAPlayers.getPlayer("Andre Drummond", online, false);
    roster[2] = NBAPlayers.getPlayer("Reggie Jackson", online, false);
    roster[3] = NBAPlayers.getPlayer("Zaza Pachulia", online, false);
    roster[4] = NBAPlayers.getPlayer("Ish Smith", online, false);
    roster[5] = NBAPlayers.getPlayer("Reggie Bullock", online, false);

    printRoster(roster);
    roster = botsFrom(6, roster);
    return new Team("DET", "Detroit Pistons", roster);
  }

  public static Team GSW(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Steph Curry", online, false);
    roster[1] = NBAPlayers.getPlayer("Kevin Durant", online, false);
    roster[2] = NBAPlayers.getPlayer("Draymond Green", online, false);
    roster[3] = NBAPlayers.getPlayer("Klay Thompson", online, false);
    roster[4] = NBAPlayers.getPlayer("Kevon Looney", online, false);
    roster[5] = NBAPlayers.getPlayer("DeMarcus Cousins", online, false);
    roster[6] = NBAPlayers.getPlayer("Andre Iguodala", online, false);
    roster[7] = NBAPlayers.getPlayer("Shaun Livingston", online, false);
    roster[8] = NBAPlayers.getPlayer("Quinn Cook", online, false);
    roster[9] = NBAPlayers.getPlayer("Jordan Bell", online, false);
    roster[10] = NBAPlayers.getPlayer("Damian Jones", online, false);
    roster[11] = NBAPlayers.getPlayer("Jonas Jerebko", online, false);

    printRoster(roster);
    return new Team("GSW", "Golden State Warriors", roster);
  }

  public static Team HOU(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("James Harden", online, false);
    roster[1] = NBAPlayers.getPlayer("Chris Paul", online, false);
    roster[2] = NBAPlayers.getPlayer("Clint Capela", online, false);
    roster[3] = NBAPlayers.getPlayer("Eric Gordon", online, false);
    roster[4] = NBAPlayers.getPlayer("Gerald Green", online, false);

    printRoster(roster);
    roster = botsFrom(5, roster);
    return new Team("HOU", "Houston Rockets", roster);
  }

  public static Team IND(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Victor Oladipo", online, false);
    roster[1] = NBAPlayers.getPlayer("Bojan Bogdanovic", online, false);
    roster[2] = NBAPlayers.getPlayer("Thaddeus Young", online, false);

    printRoster(roster);
    roster = botsFrom(3, roster);
    return new Team("IND", "Indiana Pacers", roster);
  }

  public static Team LAC(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Tobias Harris", online, false);
    roster[1] = NBAPlayers.getPlayer("Danilo Gallinari", online, false);
    roster[2] = NBAPlayers.getPlayer("Patrick Beverly", online, false);
    roster[3] = NBAPlayers.getPlayer("Lou Williams", online, false);
    roster[4] = NBAPlayers.getPlayer("Boban Marjanovic", online, false);
    roster[5] = NBAPlayers.getPlayer("Shai Gilgeous-Alexander", online, false);
    roster[6] = NBAPlayers.getPlayer("Montrezl Harrell", online, false);

    printRoster(roster);
    roster = botsFrom(7, roster);
    return new Team("LAC", "Los Angeles Clippers", roster);
  }

  public static Team LAL(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Lonzo Ball", online, false);
    roster[1] = NBAPlayers.getPlayer("Kyle Kuzma", online, false);
    roster[2] = NBAPlayers.getPlayer("LeBron James", online, false);
    roster[3] = NBAPlayers.getPlayer("Brandon Ingram", online, false);
    roster[4] = NBAPlayers.getPlayer("Tyson Chandler", online, false);
    roster[5] = NBAPlayers.getPlayer("Lance Stephenson", online, false);
    roster[6] = NBAPlayers.getPlayer("Rajon Rondo", online, false);
    roster[7] = NBAPlayers.getPlayer("Josh Hart", online, false);
    roster[8] = NBAPlayers.getPlayer("JaVale McGee", online, false);
    roster[9] = NBAPlayers.getPlayer("Michael Beasley", online, false);
    roster[10] = NBAPlayers.getPlayer("Moritz Wagner", online, false);
    roster[11] = NBAPlayers.getPlayer("Kentavious Caldwell-Pope", online, false);

    printRoster(roster);
    return new Team("LAL", "Los Angeles Lakers", roster);
  }

  public static Team MEM(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Mike Conley", online, false);
    roster[1] = NBAPlayers.getPlayer("Marc Gasol", online, false);
    roster[2] = NBAPlayers.getPlayer("Garrett Temple", online, false);
    roster[3] = NBAPlayers.getPlayer("Wayne Selden", online, false);

    printRoster(roster);
    roster = botsFrom(4, roster);
    return new Team("MEM", "Memphis Grizzlies", roster);
  }

  public static Team MIA(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Goran Dragic", online, false);
    roster[1] = NBAPlayers.getPlayer("Justise Winslow", online, false);
    roster[2] = NBAPlayers.getPlayer("Bam Adebayo", online, false);
    roster[3] = NBAPlayers.getPlayer("Hassan Whiteside", online, false);

    printRoster(roster);
    roster = botsFrom(4, roster);
    return new Team("MIA", "Miami Heat", roster);
  }

  public static Team MIL(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Giannis Antetokounmpo", online, false);
    roster[1] = NBAPlayers.getPlayer("Eric Bledsoe", online, false);
    roster[2] = NBAPlayers.getPlayer("Khris Middleton", online, false);
    roster[3] = NBAPlayers.getPlayer("John Henson", online, false);
    roster[4] = NBAPlayers.getPlayer("Malcolm Brogdon", online, false);
    roster[5] = NBAPlayers.getPlayer("Donte DiVincenzo", online, false);
    roster[6] = NBAPlayers.getPlayer("Brook Lopez", online, false);

    printRoster(roster);
    roster = botsFrom(7, roster);
    return new Team("MIL", "Milwaukee Bucks", roster);
  }

  public static Team MIN(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Karl-Anthony Towns", online, false);
    roster[1] = NBAPlayers.getPlayer("Derrick Rose", online, false);
    roster[2] = NBAPlayers.getPlayer("Dario Saric", online, false);
    roster[3] = NBAPlayers.getPlayer("Robert Covington", online, false);
    roster[4] = NBAPlayers.getPlayer("Josh Okogie", online, false);
    roster[5] = NBAPlayers.getPlayer("Andrew Wiggins", online, false);
    roster[6] = NBAPlayers.getPlayer("Jeff Teague", online, false);

    printRoster(roster);
    roster = botsFrom(7, roster);
    return new Team("MIN", "Minnesota Timberwolves", roster);
  }

  public static Team NOP(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Anthony Davis", online, false);
    roster[1] = NBAPlayers.getPlayer("Jrue Holiday", online, false);
    roster[2] = NBAPlayers.getPlayer("ETwaun Moore", online, false);
    roster[3] = NBAPlayers.getPlayer("Nikola Mirotic", online, false);

    printRoster(roster);
    roster = botsFrom(4, roster);
    return new Team("NOP", "New Orleans Pelicans", roster);
  }

  public static Team NYK(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Kristaps Porzingis", online, false);
    roster[1] = NBAPlayers.getPlayer("Enes Kanter", online, false);
    roster[2] = NBAPlayers.getPlayer("Emmaunuel Mudiay", online, false);
    roster[3] = NBAPlayers.getPlayer("Trey Burke", online, false);

    printRoster(roster);
    roster = botsFrom(4, roster);
    return new Team("NYK", "New York Knicks", roster);
  }

  public static Team OKC(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Russell Westbrook", online, false);
    roster[1] = NBAPlayers.getPlayer("Paul George", online, false);
    roster[2] = NBAPlayers.getPlayer("Steven Adams", online, false);

    printRoster(roster);
    roster = botsFrom(3, roster);
    return new Team("OKC", "Oklahoma City Thunder", roster);
  }

  public static Team ORL(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Aaron Gordon", online, false);
    roster[1] = NBAPlayers.getPlayer("Nikola Vucevic", online, false);
    roster[2] = NBAPlayers.getPlayer("DJ Augustin", online, false);
    roster[3] = NBAPlayers.getPlayer("Mo Bamba", online, false);
    roster[4] = NBAPlayers.getPlayer("Terrence Ross", online, false);

    printRoster(roster);
    roster = botsFrom(5, roster);
    return new Team("ORL", "Orlando Magic", roster);
  }

  public static Team PHX(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Devin Booker", online, false);
    roster[1] = NBAPlayers.getPlayer("Deandre Ayton", online, false);
    roster[2] = NBAPlayers.getPlayer("Jamal Crawford", online, false);

    printRoster(roster);
    roster = botsFrom(3, roster);
    return new Team("PHX", "Phoenix Suns", roster);
  }

  public static Team POR(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Damian Lillard", online, false);
    roster[1] = NBAPlayers.getPlayer("CJ McCollum", online, false);
    roster[2] = NBAPlayers.getPlayer("Seth Curry", online, false);

    printRoster(roster);
    roster = botsFrom(3, roster);
    return new Team("POR", "Portland Trail Blazers", roster);
  }

  public static Team PHI(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Joel Embiid", online, false);
    roster[1] = NBAPlayers.getPlayer("Ben Simmons", online, false);
    roster[2] = NBAPlayers.getPlayer("Jimmy Butler", online, false);

    printRoster(roster);
    roster = botsFrom(3, roster);
    return new Team("PHI", "Philadelphia 76ers", roster);
  }

  public static Team SAC(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Willie Cauley-Stein", online, false);
    roster[1] = NBAPlayers.getPlayer("DeAaron Fox", online, false);
    roster[2] = NBAPlayers.getPlayer("Bogdan Bogdanovic", online, false);
    roster[3] = NBAPlayers.getPlayer("Buddy Hield", online, false);
    roster[4] = NBAPlayers.getPlayer("Justin Jackson", online, false);

    printRoster(roster);
    roster = botsFrom(5, roster);
    return new Team("SAC", "SAcramento Kings", roster);
  }

  public static Team SAS(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("LaMarcus Aldridge", online, false);
    roster[1] = NBAPlayers.getPlayer("DeMar DeRozan", online, false);
    roster[2] = NBAPlayers.getPlayer("Rudy Gay", online, false);
    roster[3] = NBAPlayers.getPlayer("Marco Belinelli", online, false);

    printRoster(roster);
    roster = botsFrom(4, roster);
    return new Team("SAS", "San Antonio Spurs", roster);
  }

  public static Team TOR(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Kawhi Leonard", online, false);
    roster[1] = NBAPlayers.getPlayer("Kyle Lowry", online, false);
    roster[2] = NBAPlayers.getPlayer("Jonas Valanciunas", online, false);
    roster[3] = NBAPlayers.getPlayer("Pascal Siakam", online, false);
    roster[4] = NBAPlayers.getPlayer("OG Anunoby", online, false);
    roster[5] = NBAPlayers.getPlayer("Serge Ibaka", online, false);
    roster[6] = NBAPlayers.getPlayer("Fred VanVleet", online, false);
    roster[7] = NBAPlayers.getPlayer("Danny Green", online, false);

    printRoster(roster);
    roster = botsFrom(8, roster);
    return new Team("TOR", "Toronto Raptors", roster);
  }

  public static Team UTA(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("Rudy Gobert", online, false);
    roster[1] = NBAPlayers.getPlayer("Donovan Mitchell", online, false);
    roster[2] = NBAPlayers.getPlayer("Ricky Rubio", online, false);

    printRoster(roster);
    roster = botsFrom(3, roster);
    return new Team("UTA", "Utah Jazz", roster);
  }

  public static Team WSH(boolean online) {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];

    roster[0] = NBAPlayers.getPlayer("John Wall", online, false);
    roster[1] = NBAPlayers.getPlayer("Bradley Beal", online, false);
    roster[2] = NBAPlayers.getPlayer("Kelly Oubre", online, false);
    roster[3] = NBAPlayers.getPlayer("Austin Rivers", online, false);
    roster[4] = NBAPlayers.getPlayer("Dwight Howard", online, false);

    printRoster(roster);
    roster = botsFrom(5, roster);
    return new Team("WSH", "Washington Wizards", roster);
  }

  public static Team GUARDS() {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];
    roster[0] = NBAPlayers.getPlayer("Michael Jordan", false, true);
    roster[1] = NBAPlayers.getPlayer("Steph Curry", false, true);
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

  public static Team FORWARDS() {
    // s, r, a, p, v, c, f, b, d
    Player[] roster = new Player[12];
    roster[0] = NBAPlayers.getPlayer("Joel Embiid", false, true);
    roster[1] = NBAPlayers.getPlayer("Giannis Antetokounmpo", false, true);
    roster[2] = NBAPlayers.getPlayer("Kareem Abdul-Jabbar", false, true);
    roster[3] = NBAPlayers.getPlayer("DeMarcus Cousins", false, true);
    roster[4] = NBAPlayers.getPlayer("LeBron James", false, true);
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
