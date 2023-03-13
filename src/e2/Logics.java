package e2;

import java.util.Map;
import java.util.Set;

public interface Logics {

    int getNumberOfMines();

    int hit(Pair<Integer, Integer> pos);

    Map<Pair<Integer, Integer>, Integer> getOpenCells();

    boolean isWin();

    Set<Pair<Integer, Integer>> getFlags();

    void setFlag(Pair<Integer, Integer> pos);
}
