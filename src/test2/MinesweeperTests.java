package test2;
import e2.Logics;
import e2.LogicsImpl;
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
}
