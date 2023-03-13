package e2;
import java.util.Random;
import java.util.*;

public class LogicsImpl implements Logics {

    private final Set<Pair<Integer, Integer>> mines = new HashSet<>();
    private final Map<Pair<Integer, Integer>, Integer> cells = new HashMap<>();
    private final static int NUMBER_OF_MINES = 7;
    private final int size;

    public LogicsImpl(int size) {
        this.size = size;
        Random random = new Random();
        while (this.mines.size() <  NUMBER_OF_MINES){
            this.mines.add(new Pair<>(random.nextInt(size), random.nextInt(size)));
        }
    }

    public LogicsImpl(Set<Pair<Integer, Integer>> mines){
        this.size = 7;
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
            int count = this.countAdjacentMines(pos);
            this.cells.put(pos, count);
            if (count == 0){
                this.autoExpand(pos);
            }
            return count;
        }
    }

    private int countAdjacentMines(Pair<Integer, Integer> pos){
        int counter = 0;
        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++){
                if (this.mines.contains(new Pair<>(pos.getX() + i, pos.getY() + j))){
                    counter = counter + 1;
                }
            }
        }
        return counter;
    }

    private void autoExpand(Pair<Integer, Integer> pos){
        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++){
                Pair<Integer, Integer> neighbor = new Pair<>(pos.getX() + i, pos.getY() + j);
                if (neighbor.getX() < this.size && neighbor.getY() < this.size && neighbor.getX() >=0 && neighbor.getY() >=0 && !neighbor.equals(pos)){
                    if (!this.getOpenCells().containsKey(neighbor)){
                        this.hit(neighbor);
                    }
                }
            }
        }
    }

    @Override
    public Map<Pair<Integer, Integer>, Integer> getOpenCells() {
        return this.cells;
    }

}
