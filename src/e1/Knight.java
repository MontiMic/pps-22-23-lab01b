package e1;

public class Knight implements MovementStrategy{

    public Knight(){}

    @Override
    public boolean isLegalMove(Logics logics, int row, int col) {
        // Below a compact way to express allowed moves for the knight
        int x = row-logics.getKnight().getX();
        int y = col-logics.getKnight().getY();
        return x!=0 && y!=0 && Math.abs(x)+Math.abs(y)==3;
    }
}
