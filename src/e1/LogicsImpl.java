package e1;

import javax.management.InstanceNotFoundException;
import java.util.*;

public class LogicsImpl implements Logics {
	
	private final Pair<Integer,Integer> pawn;
	private Pair<Integer,Integer> knight;
	private final Random random = new Random();
	private final int size;

	private final MovementStrategy moves;
	 
    public LogicsImpl(int size){
    	this.size = size;
        this.pawn = this.randomEmptyPosition();
        this.knight = this.randomEmptyPosition();
		this.moves = new Knight();
    }

	public LogicsImpl(int size, Pair<Integer, Integer> knight, Pair<Integer, Integer> pawn){
		this.size = size;
		this.pawn = new Pair<>(pawn.getX(), pawn.getY());
		this.knight = new Pair<>(knight.getX(), knight.getY());
		this.moves = new Knight();
	}
    
	private final Pair<Integer,Integer> randomEmptyPosition(){
    	Pair<Integer,Integer> pos = new Pair<>(this.random.nextInt(size),this.random.nextInt(size));
    	// the recursive call below prevents clash with an existing pawn
    	return this.pawn!=null && this.pawn.equals(pos) ? randomEmptyPosition() : pos;
    }
    
	@Override
	public boolean hit(int row, int col) {
		if (row<0 || col<0 || row >= this.size || col >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		if (this.moves.isLegalMove(this, row, col)) {
			this.knight = new Pair<>(row,col);
			return this.pawn.equals(this.knight);
		}
		return false;
	}

	@Override
	public boolean hasKnight(int row, int col) {
		return this.knight.equals(new Pair<>(row,col));
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.pawn.equals(new Pair<>(row,col));
	}

	@Override
	public Pair<Integer, Integer> getKnight() {
		return new Pair<>(this.knight.getX(), this.knight.getY());
	}
}
