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
    pacificTeams.add(NBATeams.LAC(online));
    pacificTeams.add(NBATeams.LAL(online));
    pacificTeams.add(NBATeams.PHO(online));
    pacificTeams.add(NBATeams.SAC(online));

    Division pacific = new Division("Pacific", pacificTeams);

    List<Team> southwestTeams = new ArrayList<>();
    southwestTeams.add(NBATeams.MEM(online));
    southwestTeams.add(NBATeams.NOP(online));
    southwestTeams.add(NBATeams.DAL(online));
    southwestTeams.add(NBATeams.HOU(online));
    southwestTeams.add(NBATeams.SAS(online));
    Division southwest = new Division("Southwest", southwestTeams);

    List<Team> northwestTeams = new ArrayList<>();
    northwestTeams.add(NBATeams.DEN(online));
    northwestTeams.add(NBATeams.OKC(online));
    northwestTeams.add(NBATeams.POR(online));
    northwestTeams.add(NBATeams.MIN(online));
    northwestTeams.add(NBATeams.UTA(online));
    // northwestTeams.add(NBATeams.YOUNG());
    Division northwest = new Division("Northwest", northwestTeams);

    Conference western = new Conference("Western",
            List.of(pacific, southwest, northwest));

    List<Team> atlanticTeams = new ArrayList<>();
    atlanticTeams.add(NBATeams.TOR(online));
    atlanticTeams.add(NBATeams.PHI(online));
    atlanticTeams.add(NBATeams.BOS(online));
    atlanticTeams.add(NBATeams.BRK(online));
    atlanticTeams.add(NBATeams.NYK(online));
    // atlanticTeams.add(NBATeams.ESTABLISHED());
    Division atlantic = new Division("Atlantic", atlanticTeams);

    List<Team> centralTeams = new ArrayList<>();
    centralTeams.add(NBATeams.MIL(online));
    centralTeams.add(NBATeams.DET(online));
    centralTeams.add(NBATeams.IND(online));
    centralTeams.add(NBATeams.CHI(online));
    centralTeams.add(NBATeams.CLE(online));
    Division central = new Division("Central", centralTeams);

    List<Team> southeastTeams = new ArrayList<>();
    southeastTeams.add(NBATeams.ORL(online));
    southeastTeams.add(NBATeams.CHO(online));
    southeastTeams.add(NBATeams.WAS(online));
    southeastTeams.add(NBATeams.MIA(online));
    southeastTeams.add(NBATeams.ATL(online));
    Division southeast = new Division("Southeast", southeastTeams);

    Conference eastern = new Conference("Eastern",
            List.of(atlantic, central, southeast));

    League NBA = new League("National Basketball Association", 2020,
            western, eastern, 82);
    NBA.printTeamsQuality();
    NBA.printLeaders(StatComparator.Stat.OVR, Player::overall, "Ratings");
    // NBA.simulateRegularSeason();
    NBA.simulateSeason();
    western.printStandings();
    eastern.printStandings();
    NBA.printLeaders(StatComparator.Stat.OVR, Player::overall, "Ratings");
    NBA.printLeaders(StatComparator.Stat.PTS, Player::getPTS, "Scoring");
    NBA.printLeaders(StatComparator.Stat.ASTPG, Player::getASTPG, "Assists");
    NBA.printLeaders(StatComparator.Stat.REBPG, Player::getREBPG, "Rebounds");
    NBA.printLeaders(StatComparator.Stat.BLKPG, Player::getBLKPG, "Blocks");
    NBA.printLeaders(StatComparator.Stat.STLPG, Player::getSTLPG, "Steals");
    NBA.printLeaders(StatComparator.Stat.TOVPG, Player::getTOVPG, "Turnovers");
    NBA.printLeaders(StatComparator.Stat.THREE, Player::getTHREE, "Three-Point");
    NBA.printLeaders(StatComparator.Stat.MINPG, Player::getMINPG, "Minutes");
    Game faceOff = new Game(NBATeams.GUARDS(), NBATeams.FORWARDS());
    faceOff.simulate();
    faceOff.printBoxScore();
    faceOff = new Game(NBATeams.TOR(false), NBATeams.MIL(false));
    faceOff.simulate();
    faceOff.printBoxScore();
  }
}
