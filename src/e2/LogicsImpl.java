package e2;
import java.util.Random;
import java.util.*;

public class LogicsImpl implements Logics {

    private final Set<Pair<Integer, Integer>> mines = new HashSet<>();
    private final static int NUMBER_OF_MINES = 10;

    public LogicsImpl(int size) {
        Random random = new Random();
        while (this.mines.size() <  NUMBER_OF_MINES){
            this.mines.add(new Pair<>(random.nextInt(size), random.nextInt(size)));
        }
    }

    @Override
    public int getNumberOfMines() {
        return this.mines.size();
    }
}
