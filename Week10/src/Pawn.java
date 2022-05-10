
public class Pawn extends Piece {

	boolean nevermoved;
	
	Pawn(int x, int y, char c) {
		
		this.pos_x = x;
		this.pos_y = y;
		this.color = c;
		this.type = 'P';
		
		this.nevermoved = true;
	}
	
	@Override
	void move(int x, int y) {
		
		this.pos_x = x;
		this.pos_y = y;
		
		this.nevermoved = false;
		
		// Promote if a Pawn reaches pos_y = 0 or 7.
	}
	
	void findMovables() {
		
	}
}
