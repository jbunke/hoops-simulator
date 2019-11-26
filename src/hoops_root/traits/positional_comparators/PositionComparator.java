package hoops_root.traits.positional_comparators;

import hoops_root.Player;

import java.util.Comparator;

public class PositionComparator implements Comparator<Player> {

  @Override
  public int compare(Player a, Player b) {
    return (Player.overall(b, b.position()) + (b.getEnergy() / 30)) -
            (Player.overall(a, a.position()) + (a.getEnergy() / 30));
  }
}
