
public class King extends Piece {

	boolean nevermoved;
	boolean checked;
	
	King(int x, int y, char c) {
		
		this.pos_x = x;
		this.pos_y = y;
		this.color = c;
		this.type = 'K';
		
		this.nevermoved = true;
	}
	
	@Override
	void move(int x, int y) {
		
		this.pos_x = x;
		this.pos_y = y;
		
		this.nevermoved = false;
	}
	
	void findMovables() {
		
	}
}
