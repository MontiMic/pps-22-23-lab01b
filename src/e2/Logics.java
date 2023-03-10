package e2;

import java.util.Map;

public interface Logics {

    int getNumberOfMines();

    int hit(Pair<Integer, Integer> pos);

    Map<Pair<Integer, Integer>, Integer> getOpenCells();

}
