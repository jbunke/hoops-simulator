package hoops_root.traits.positional_comparators;

import hoops_root.Player;
import hoops_root.traits.Position;

import java.util.Comparator;

public class PFComparator implements Comparator<Player> {

  @Override
  public int compare(Player a, Player b) {
    return (Player.overall(b, Position.POWER_FORWARD) + (b.getEnergy() / 30)) -
            (Player.overall(a, Position.POWER_FORWARD) + (a.getEnergy() / 30));
  }
}
