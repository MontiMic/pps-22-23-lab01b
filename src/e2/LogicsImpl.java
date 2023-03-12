package e2;
import java.util.Random;
import java.util.*;

public class LogicsImpl implements Logics {

    private final Set<Pair<Integer, Integer>> mines = new HashSet<>();
    private final Map<Pair<Integer, Integer>, Integer> cells = new HashMap<>();
    private final static int NUMBER_OF_MINES = 10;

    public LogicsImpl(int size) {
        Random random = new Random();
        while (this.mines.size() <  NUMBER_OF_MINES){
            this.mines.add(new Pair<>(random.nextInt(size), random.nextInt(size)));
        }
    }

    public LogicsImpl(Set<Pair<Integer, Integer>> mines){
        this.mines.addAll(mines);
    }

    @Override
    public int getNumberOfMines() {
        return this.mines.size();
    }

    @Override
    public int hit(Pair<Integer, Integer> pos) {
        if (this.mines.contains(pos)){
            return -1;
        } else {
            int count = 0;
            for (int i = -1; i < 2; i++){
                for (int j = -1; j < 2; j++){
                    if (this.mines.contains(new Pair<>(pos.getX() + i, pos.getY() + j))){
                        count = count + 1;
                    }
                }
            }
            this.cells.put(pos, count);
            return count;
        }
    }

    @Override
    public Map<Pair<Integer, Integer>, Integer> getOpenCells() {
        return this.cells;
    }

}
