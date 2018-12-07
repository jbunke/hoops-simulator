package hoops_root.stats.statistical_comparators;

import hoops_root.Player;

import java.util.Comparator;
import java.util.function.Function;

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
    THREE,
    OVR
  }

  @Override
  public int compare(Player a, Player b) {
    double aVal;
    double bVal;
    Function<Player, Number> function;
    switch (stat) {
      case OVR:
        return Player.overall(b, b.position()) - Player.overall(a, a.position());
      case PPG:
        function = Player::getPPG;
        break;
      case PTS:
        return b.getPTS() - a.getPTS();
      case REBPG:
        function = Player::getREBPG;
        break;
      case ASTPG:
        function = Player::getASTPG;
        break;
      case FLSPG:
        function = Player::getFLSPG;
        break;
      case STLPG:
        function = Player::getSTLPG;
        break;
      case TOVPG:
        function = Player::getTOVPG;
        break;
      case MINPG:
        function = Player::getMINPG;
        break;
      case THREEPERC:
        function = Player::getTHREEPERC;
        break;
      case THREE:
        return b.getTHREE() - a.getTHREE();
      case BLKPG:
      default:
        function = Player::getBLKPG;
        break;
    }

    aVal = (double)function.apply(a);
    bVal = (double)function.apply(b);

    if (aVal > bVal) {
      return -1;
    } else if (bVal > aVal) {
      return 1;
    }
    return 0;
  }
}