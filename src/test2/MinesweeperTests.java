package test2;
import e2.Logics;
import e2.LogicsImpl;
import e2.Pair;
import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MinesweeperTests {

    private Logics logic;
    private final static int SIZE = 9;
    private final Set<Pair<Integer, Integer>> mines = new HashSet<>();

    @BeforeEach
    void setUp(){
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 2; j++){
                this.mines.add(new Pair<>(i, j));
            }
        }
        this.logic = new LogicsImpl(mines);
    }
    @Test
    void testNumberOfMines(){
        assertEquals(10, this.logic.getNumberOfMines());
    }

    @Test
    void testLoseByHittingMine(){
        assertEquals(-1, this.logic.hit(new Pair<>(0, 0)));
    }
    @Test
    void testCountOpenCells(){
        int count = 0;
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++) {
                if (this.logic.hit(new Pair<>(i, j)) >= 0) {
                    count = count + 1;
                }
            }
        }
        assertEquals(count, this.logic.getOpenCells().size());
    }
    @Test
    void testCountNonZeros(){
        assertNotEquals(0, this.logic.hit(new Pair<>(5, 0)));
    }

    @Test
    void testAutoExpansion(){
        assertEquals(0, this.logic.hit(new Pair<>(9, 9)));
        assertNotEquals(1, this.logic.getOpenCells().size());
    }
}
