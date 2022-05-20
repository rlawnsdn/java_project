
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
						if(board[i][j].piece==null){
							if(j==this.pos_y) Moveable[i][j]=true; //폰은 앞으로 한 칸 전진할 수 있다
							else{}
						}
						else if(board[i][j].piece.color=='b' && (Math.abs(this.pos_y-j)==1)) Moveable[i][j]=true; //폰은 상대 말을 잡을 때에는 대각선으로 한 칸 이동한다
						else Moveable[i][j]=false;
					}
					else Moveable[i][j]=false;
				}
			}
			if(this.pos_x==1 && board[3][this.pos_y].piece==null && board[2][this.pos_y].piece==null) Moveable[3][this.pos_y]=true; //처음 움직이는 폰은 두 칸 움직일 수 있다
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
