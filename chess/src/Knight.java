
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
					if ((Math.abs(this.pos_x-i) == 2 && Math.abs(this.pos_y-j) == 1) || (Math.abs(this.pos_x-i) == 1 && Math.abs(this.pos_y-j) == 2)) { //나이트는 한 방향으로 두 칸 이동하고 수직한 방향으로 한 칸 이동한다
						if (board[i][j].piece == null) Moveable[i][j] = true;
						else if (board[i][j].piece.color == 'w') Moveable[i][j] = true; //이동한 최종 위치에 상대 기물이 있으면 잡을 수 있다
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