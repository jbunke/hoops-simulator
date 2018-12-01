package hoops_root;

import hoops_root.NBA.NBATeams;

import java.util.ArrayList;
import java.util.List;

public class Main {
  private static final boolean online = false;

  public static void main(String[] args) {
    Team legends = NBATeams.LEGENDS();
    System.out.println(legends.name() + " " + legends.quality());
    List<Team> pacificTeams = new ArrayList<>();
    pacificTeams.add(NBATeams.GSW(online));
    pacificTeams.add(Team.botTeam("LAC", "Los Angeles Clippers"));
    pacificTeams.add(NBATeams.LAL(online));
    pacificTeams.add(Team.botTeam("PHX", "Phoenix Suns"));
    pacificTeams.add(Team.botTeam("SAC", "Sacramento Kings"));

    Division pacific = new Division("Pacific", pacificTeams);

    List<Team> southwestTeams = new ArrayList<>();
    southwestTeams.add(Team.botTeam("MEM", "Memphis Grizzlies"));
    southwestTeams.add(Team.botTeam("NOP", "New Orleans Pelicans"));
    southwestTeams.add(Team.botTeam("DAL", "Dallas Mavericks"));
    southwestTeams.add(NBATeams.HOU(online));
    southwestTeams.add(Team.botTeam("SAS", "San Antonio Spurs"));
    Division southwest = new Division("Southwest", southwestTeams);

    List<Team> northwestTeams = new ArrayList<>();
    northwestTeams.add(Team.botTeam("DEN", "Denver Nuggets"));
    northwestTeams.add(NBATeams.OKC(online));
    northwestTeams.add(Team.botTeam("POR", "Portland Trailblazers"));
    northwestTeams.add(NBATeams.MIN(online));
    northwestTeams.add(Team.botTeam("UTA", "Utah Jazz"));
    Division northwest = new Division("Northwest", northwestTeams);

    Conference western = new Conference("Western",
            List.of(pacific, southwest, northwest));

    List<Team> atlanticTeams = new ArrayList<>();
    atlanticTeams.add(NBATeams.TOR(online));
    atlanticTeams.add(NBATeams.PHI(online));
    atlanticTeams.add(NBATeams.BOS(online));
    atlanticTeams.add(Team.botTeam("BRK", "Brooklyn Nets"));
    atlanticTeams.add(Team.botTeam("NYK", "New York Knicks"));
    Division atlantic = new Division("Atlantic", atlanticTeams);

    List<Team> centralTeams = new ArrayList<>();
    centralTeams.add(NBATeams.MIL(online));
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
    NBA.printTeamsQuality();
    NBA.simulateSeason();
    western.printStandings();
    eastern.printStandings();
  }
}
