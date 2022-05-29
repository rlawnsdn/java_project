
public class Pawn extends Piece {

	Pawn(int x, int y, char c) {
		
		this.pos_x = x;
		this.pos_y = y;
		this.color = c;
		this.type = 'P';
	}
	
	void findMovables(Square[][] board, boolean bqc, boolean bkc, boolean wqc, boolean wkc) {
		
		if(this.color=='w'){
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if(this.pos_x+1 == i){
						// Basically, a Pawn may move a single square straightly forward
						if(board[i][j].piece==null){
							if(j==this.pos_y) Moveable[i][j]=true;
							else{}
						}
						// If a Pawn catches other pieces, move a single square diagonally forward
						else if(board[i][j].piece.color=='b' && (Math.abs(this.pos_y-j)==1)) Moveable[i][j]=true;
						else Moveable[i][j]=false;
					}
					else Moveable[i][j]=false;
				}
			}
			// A Pawn can move two squares straightly forward on its very first move
			if(this.pos_x==1 && board[3][this.pos_y].piece==null && board[2][this.pos_y].piece==null) Moveable[3][this.pos_y]=true;
		}
		else{
			for(int i=0; i<8; i++){
				for(int j=0; j<8; j++){
					if(this.pos_x-1 == i){
						if(board[i][j].piece==null){
							if(j==this.pos_y) Moveable[i][j]=true;
							else {}
						}
						else if(board[i][j].piece.color=='w' && (Math.abs(this.pos_y-j)==1)) Moveable[i][j]=true;
						else Moveable[i][j]=false;
					}
					else Moveable[i][j]=false;
				}
				if(this.pos_x==6 && board[4][this.pos_y].piece==null && board[5][this.pos_y].piece==null) Moveable[4][this.pos_y]=true;
			}
		}
	}
}
