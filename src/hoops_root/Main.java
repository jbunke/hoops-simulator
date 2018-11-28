package hoops_root;

import hoops_root.traits.DominantHand;
import hoops_root.traits.Position;

import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    // TODO - Pacific hoops_root.Division (GSW, LAC, LAL, PHX, SAC)
    Player[] gswRoster = new Player[12];
    /*
    hoops_root.Player lebron = new hoops_root.Player(new String[] {"LeBron", "Raymone", "James"},
            "LeBron", new hoops_root.Date(1984, 12, 30),
            Position.SMALL_FORWARD, DominantHand.RIGHT, 23,
            new int[] {95, 90, 96, 85, 94, 82, 80, 90, 98, 0, 2});
    */
    // System.out.println(lebron + " - " + lebron.overall());
    gswRoster[0] = new Player(new String[] {"Wardell", "Stephen", "Curry"},
            "Steph", new Date(1988, 3, 14),
            Position.POINT_GUARD, DominantHand.RIGHT, 30, 75, 190,
            new int[] {98, 60, 70, 90, 90, 90, 98, 55, 95, 3, 1}); // s, r, a, p, v, c, f, b, d
    // System.out.println(gswRoster[0] + " - " + gswRoster[0].overall());
    gswRoster[1] = new Player(new String[] {"Kevin", "Wayne", "Durant"},
            "Kevin", new Date(1988, 9, 29),
            Position.SMALL_FORWARD, DominantHand.RIGHT, 35, 81, 240,
            new int[] {91, 75, 75, 80, 85, 95, 96, 75, 95, 1, 2});
    // System.out.println(gswRoster[1] + " - " + gswRoster[1].overall());
    gswRoster[2] = new Player(new String[] {"Draymond", "Jamal", "Green"},
            "Draymond", new Date(1990, 3, 4),
            Position.POWER_FORWARD, DominantHand.RIGHT, 23, 79, 230,
            new int[] {70, 82, 75, 70, 60, 80, 78, 75, 82, 0, 4});
    // System.out.println(gswRoster[2] + " - " + gswRoster[2].overall());
    gswRoster[3] = new Player(new String[] {"Klay", "Alexander", "Thompson"},
            "Klay", new Date(1988, 9, 29),
            Position.SHOOTING_GUARD, DominantHand.RIGHT, 11, 79, 215,
            new int[] {96, 55, 70, 85, 80, 91, 90, 60, 70, 1, 1});
    // System.out.println(gswRoster[3] + " - " + gswRoster[3].overall());
    // TODO
    for (int i = 4; i < 12; i++) {
      gswRoster[i] = new Player(new String[] {String.valueOf(i)},
              "GSW Bot", new Date(2000, 1 ,1));
    }
    Team gsw = new Team("GSW", "Golden State Warriors", gswRoster);
    Team lac = Team.botTeam("LAC", "Los Angeles Clippers");
    Team lal = Team.botTeam("LAL", "Los Angeles Lakers");
    Team phx = Team.botTeam("PHX", "Phoenix Suns");
    Team sac = Team.botTeam("SAC", "Sacramento Kings");

    List<Team> pacificTeams = new ArrayList<>();
    pacificTeams.add(gsw);
    pacificTeams.add(lac);
    pacificTeams.add(lal);
    pacificTeams.add(phx);
    pacificTeams.add(sac);

    // TODO - testing
    // hoops_root.Player MVP = gsw.getPlayer("Steph Curry");

    Division pacific = new Division("Pacific", pacificTeams);

    List<Team> southwestTeams = new ArrayList<>();
    southwestTeams.add(Team.botTeam("MEM", "Memphis Grizzlies"));
    southwestTeams.add(Team.botTeam("NOP", "New Orleans Pelicans"));
    southwestTeams.add(Team.botTeam("DAL", "Dallas Mavericks"));
    southwestTeams.add(Team.botTeam("HOU", "Houston Rockets"));
    southwestTeams.add(Team.botTeam("SAS", "San Antonio Spurs"));
    Division southwest = new Division("Southwest", southwestTeams);

    List<Team> northwestTeams = new ArrayList<>();
    northwestTeams.add(Team.botTeam("DEN", "Denver Nuggets"));
    northwestTeams.add(Team.botTeam("OKC", "Oklahoma City Thunder"));
    northwestTeams.add(Team.botTeam("POR", "Portland Trailblazers"));
    northwestTeams.add(Team.botTeam("MIN", "Minnesota Timberwolves"));
    northwestTeams.add(Team.botTeam("UTA", "Utah Jazz"));
    Division northwest = new Division("Northwest", northwestTeams);

    Conference western = new Conference("Western",
            List.of(pacific, southwest, northwest));

    List<Team> atlanticTeams = new ArrayList<>();
    atlanticTeams.add(Team.botTeam("TOR", "Toronto Raptors"));
    atlanticTeams.add(Team.botTeam("PHI", "Philadelphia 76ers"));
    atlanticTeams.add(Team.botTeam("BOS", "Boston Celtics"));
    atlanticTeams.add(Team.botTeam("BRK", "Brooklyn Nets"));
    atlanticTeams.add(Team.botTeam("NYK", "New York Knicks"));
    Division atlantic = new Division("Atlantic", atlanticTeams);

    List<Team> centralTeams = new ArrayList<>();
    centralTeams.add(Team.botTeam("MIL", "Milwaukee Bucks"));
    centralTeams.add(Team.botTeam("DET", "Detroit Pistons"));
    centralTeams.add(Team.botTeam("IND", "Indiana Pacers"));
    centralTeams.add(Team.botTeam("CHI", "Chicago Bulls"));
    centralTeams.add(Team.botTeam("CLE", "Cleveland Cavaliers"));
    Division central = new Division("Central", centralTeams);

    List<Team> southeastTeams = new ArrayList<>();
    southeastTeams.add(Team.botTeam("ORL", "Orlando Magic"));
    southeastTeams.add(Team.botTeam("CHA", "Charlotte Hornets"));
    southeastTeams.add(Team.botTeam("WSH", "Washington Wizards"));
    southeastTeams.add(Team.botTeam("MIA", "Miami Heat"));
    southeastTeams.add(Team.botTeam("ATL", "Atlanta Hawks"));
    Division southeast = new Division("Southeast", southeastTeams);

    Conference eastern = new Conference("Eastern",
            List.of(atlantic, central, southeast));

    League NBA = new League("National Basketball Association", 2019,
            western, eastern, 82);
    NBA.simulateSeason();
    // western.printStandings();
    // eastern.printStandings();
  }
}
