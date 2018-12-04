package hoops_root;

import hoops_root.NBA.NBATeams;
import hoops_root.stats.statistical_comparators.StatComparator;

import java.util.ArrayList;
import java.util.List;

public class Main {
  private static final boolean online = false;

  public static void main(String[] args) {
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
    southwestTeams.add(NBATeams.DAL(online));
    southwestTeams.add(NBATeams.HOU(online));
    southwestTeams.add(Team.botTeam("SAS", "San Antonio Spurs"));
    Division southwest = new Division("Southwest", southwestTeams);

    List<Team> northwestTeams = new ArrayList<>();
    northwestTeams.add(NBATeams.DEN(online));
    northwestTeams.add(NBATeams.OKC(online));
    northwestTeams.add(NBATeams.POR(online));
    northwestTeams.add(NBATeams.MIN(online));
    northwestTeams.add(NBATeams.UTA(online));
    Division northwest = new Division("Northwest", northwestTeams);

    Conference western = new Conference("Western",
            List.of(pacific, southwest, northwest));

    List<Team> atlanticTeams = new ArrayList<>();
    atlanticTeams.add(NBATeams.TOR(online));
    atlanticTeams.add(NBATeams.PHI(online));
    atlanticTeams.add(NBATeams.BOS(online));
    atlanticTeams.add(Team.botTeam("BRK", "Brooklyn Nets"));
    atlanticTeams.add(NBATeams.NYK(online));
    Division atlantic = new Division("Atlantic", atlanticTeams);

    List<Team> centralTeams = new ArrayList<>();
    centralTeams.add(NBATeams.MIL(online));
    centralTeams.add(NBATeams.DET(online));
    centralTeams.add(NBATeams.IND(online));
    centralTeams.add(NBATeams.CHI(online));
    centralTeams.add(Team.botTeam("CLE", "Cleveland Cavaliers"));
    Division central = new Division("Central", centralTeams);

    List<Team> southeastTeams = new ArrayList<>();
    southeastTeams.add(Team.botTeam("ORL", "Orlando Magic"));
    southeastTeams.add(NBATeams.CHA(online));
    southeastTeams.add(NBATeams.WSH(online));
    southeastTeams.add(Team.botTeam("MIA", "Miami Heat"));
    southeastTeams.add(Team.botTeam("ATL", "Atlanta Hawks"));
    Division southeast = new Division("Southeast", southeastTeams);

    Conference eastern = new Conference("Eastern",
            List.of(atlantic, central, southeast));

    League NBA = new League("National Basketball Association", 2019,
            western, eastern, 82);
    NBA.printTeamsQuality();
    NBA.simulateRegularSeason();
    western.printStandings();
    eastern.printStandings();
    NBA.printLeaders(StatComparator.Stat.PPG, Player::getPPG, "Points");
    NBA.printLeaders(StatComparator.Stat.ASTPG, Player::getASTPG, "Assists");
    NBA.printLeaders(StatComparator.Stat.REBPG, Player::getREBPG, "Rebounds");
    NBA.printLeaders(StatComparator.Stat.BLKPG, Player::getREBPG, "Blocks");
    NBA.printLeaders(StatComparator.Stat.STLPG, Player::getREBPG, "Steals");
    NBA.printLeaders(StatComparator.Stat.TOVPG, Player::getREBPG, "Turnovers");
    NBA.printLeaders(StatComparator.Stat.THREE, Player::getTHREE, "Three-Point");
    NBA.printLeaders(StatComparator.Stat.MINPG, Player::getMINPG, "Minutes");
    Game faceOff = new Game(NBATeams.GUARDS(), NBATeams.FORWARDS());
    faceOff.simulate();
    faceOff.printBoxScore();
  }
}
