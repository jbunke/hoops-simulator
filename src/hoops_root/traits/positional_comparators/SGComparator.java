package hoops_root.traits.positional_comparators;

import hoops_root.Player;
import hoops_root.traits.Position;

import java.util.Comparator;

public class SGComparator implements Comparator<Player> {

  @Override
  public int compare(Player a, Player b) {
    return (Player.overall(b, Position.SHOOTING_GUARD) + (b.getEnergy() / 30)) -
            (Player.overall(a, Position.SHOOTING_GUARD) + (a.getEnergy() / 30));
  }
}
