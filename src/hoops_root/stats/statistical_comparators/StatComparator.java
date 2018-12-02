package hoops_root.stats.statistical_comparators;

import hoops_root.Player;

import java.util.Comparator;

public class StatComparator implements Comparator<Player> {
  private Stat stat;

  public StatComparator(Stat stat) {
    this.stat = stat;
  }

  public enum Stat {
    PPG,
    ASTPG,
    REBPG,
    BLKPG,
    FLSPG,
    STLPG,
    TOVPG,
    MINPG,
    PTS,
    THREEPERC,
    THREE
  }

  @Override
  public int compare(Player a, Player b) {
    switch (stat) {
      case PPG:
        return (int)Math.signum(b.getPPG() - a.getPPG());
      case PTS:
        return (int)Math.signum(b.getPTS() - a.getPTS());
      case REBPG:
        return (int)Math.signum(b.getREBPG() - a.getREBPG());
      case ASTPG:
        return (int)Math.signum(b.getASTPG() - a.getASTPG());
      case FLSPG:
        return (int)Math.signum(b.getFLSPG() - a.getFLSPG());
      case STLPG:
        return (int)Math.signum(b.getSTLPG() - a.getSTLPG());
      case TOVPG:
        return (int)Math.signum(b.getTOVPG() - a.getTOVPG());
      case MINPG:
        return (int)Math.signum(b.getMINPG() - a.getMINPG());
      case THREEPERC:
        return (int)Math.signum(b.getTHREEPERC() - a.getTHREEPERC());
      case THREE:
        return (int)Math.signum(b.getTHREE() - a.getTHREE());
      case BLKPG:
      default:
        return (int)Math.signum(b.getBLKPG() - a.getBLKPG());
    }
  }
}