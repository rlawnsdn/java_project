
public class Knight extends Piece {
	
	Knight(int x, int y, char c) {
		
		this.pos_x = x;
		this.pos_y = y;
		this.color = c;
		this.type = 'N';
	}
	
	void findMovables(Square[][] board, boolean bqc, boolean bkc, boolean wqc, boolean wkc) {
		
		if(this.color=='b'){
			for(int i=0; i<8; i++) {
				for (int j = 0; j < 8; j++) {
					if ((Math.abs(this.pos_x-i) == 2 && Math.abs(this.pos_y-j) == 1) || (Math.abs(this.pos_x-i) == 1 && Math.abs(this.pos_y-j) == 2)) {
						if (board[i][j].piece == null) Moveable[i][j] = true;
						else if (board[i][j].piece.color == 'w') Moveable[i][j] = true;
						else Moveable[i][j] = false;
					}
					else Moveable[i][j] = false;
				}
			}
		}
		
		if(this.color=='w'){
			for(int i=0; i<8; i++) {
				for (int j = 0; j < 8; j++) {
					if ((Math.abs(this.pos_x-i) == 2 && Math.abs(this.pos_y-j) == 1) || (Math.abs(this.pos_x-i) == 1 && Math.abs(this.pos_y-j) == 2)) {
						if (board[i][j].piece == null) Moveable[i][j] = true;
						else if (board[i][j].piece.color == 'b') Moveable[i][j] = true;
						else Moveable[i][j] = false;
					}
					else Moveable[i][j] = false;
				}
			}
		}
	}
}