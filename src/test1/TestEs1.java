package test1;

import e1.LogicsImpl;
import e1.Pair;
import org.junit.jupiter.api.*;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class TestEs1 {

    private final static int SIZE = 5;
    private LogicsImpl logics;

    @BeforeEach
    void setUp(){
        this.logics = new LogicsImpl(SIZE, new Pair<>(0, 0), new Pair<>(1, 2));
    }

    /*IntStream.range(0, SIZE).mapToObj(i ->
                IntStream.range(0, SIZE).mapToObj(j -> new Pair<>(i, j))
        ).flatMap(s -> s)
                .filter(p -> logics.hasKnight(p.getX(), p.getY()))
                .findFirst();

    /*private void findPieces(){
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Pair<Integer, Integer> check = new Pair<>(i, j);
                if (logics.hasKnight(check.getX(), check.getY())) {
                    this.knight = check;
                } else if (logics.hasPawn(check.getX(), check.getY())) {
                    this.pawn = check;
                }
            }
        }
    }*/

    @Test
    void testNoIllegalMoves() {
        this.logics.hit(0, 1);
        assertFalse(this.logics.hasKnight(0, 1));
    }

    @Test
    void testLegalMove(){
        this.logics.hit(2, 1);
        assertTrue(this.logics.hasKnight(2, 1));
    }

    @Test
    void testStayInChessBoard(){
        assertThrows(IndexOutOfBoundsException.class, () -> this.logics.hit(-1, -1));
    }

    @Test
    void testEatsPawn(){
        assertTrue(this.logics.hit(1, 2));
    }

    @Test
    void testMovesWithoutEating(){
        assertFalse(this.logics.hit(2, 1));
    }

}
