package test2;
import e2.Logics;
import e2.LogicsImpl;
import e2.Pair;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class MinesweeperTests {

    private Logics logic;
    private final static int SIZE = 9;

    @BeforeEach
    void setUp(){
        this.logic = new LogicsImpl(SIZE);
    }
    @Test
    void testNumberOfMines(){
        assertEquals(10, this.logic.getNumberOfMines());
    }

    @Test
    void testLoseOnlyByHittingMine(){
        int count = 0;
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++) {
                if (this.logic.hit(new Pair<>(i, j)) < 0) {
                    count = count + 1;
                }
            }
        }
        assertEquals(this.logic.getNumberOfMines(), count);
    }
}
