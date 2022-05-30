
public class Rook extends Piece {
	
	Rook(int x, int y, char c) {
		
		this.pos_x = x;
		this.pos_y = y;
		this.color = c;
		this.type = 'R';
	}
	
	void findMovables(Square[][] board, boolean bqc, boolean bkc, boolean wqc, boolean wkc) {
		for(int i=0; i<8; i++) for(int j=0; j<8; j++) Moveable[i][j]=false;
		if(this.color=='b'){
			
			// Lower Side
			for(int i=this.pos_x+1; i<8; i++){
				if(board[i][this.pos_y].piece==null || board[i][this.pos_y].piece.type=='F') Moveable[i][this.pos_y]=true;
				else if(board[i][this.pos_y].piece.color=='w'){
					Moveable[i][this.pos_y]=true;
					break;
				}
				else break;
			}
			// Upper Side
			for(int i=this.pos_x-1; i>=0; i--){
				if(board[i][this.pos_y].piece==null || board[i][this.pos_y].piece.type=='F') Moveable[i][this.pos_y]=true;
				else if(board[i][this.pos_y].piece.color=='w'){
					Moveable[i][this.pos_y]=true;
					break;
				}
				else break;
			}
			// Right Side
			for(int j=this.pos_y+1; j<8; j++){
				if(board[this.pos_x][j].piece==null || board[this.pos_x][j].piece.type=='F') Moveable[this.pos_x][j]=true;
				else if(board[this.pos_x][j].piece.color=='w'){
					Moveable[this.pos_x][j]=true;
					break;
				}
				else break;
			}
			// Left Side
			for(int j=this.pos_y-1; j>=0; j--){
				if(board[this.pos_x][j].piece==null || board[this.pos_x][j].piece.type=='F') Moveable[this.pos_x][j]=true;
				else if(board[this.pos_x][j].piece.color=='w'){
					Moveable[this.pos_x][j]=true;
					break;
				}
				else break;
			}
		}

		if(this.color=='w'){

			for(int i=this.pos_x+1; i<8; i++){
				if(board[i][this.pos_y].piece==null || board[i][this.pos_y].piece.type=='F') Moveable[i][this.pos_y]=true;
				else if(board[i][this.pos_y].piece.color=='b'){
					Moveable[i][this.pos_y]=true;
					break;
				}
				else break;
			}

			for(int i=this.pos_x-1; i>=0; i--){
				if(board[i][this.pos_y].piece==null || board[i][this.pos_y].piece.type=='F') Moveable[i][this.pos_y]=true;
				else if(board[i][this.pos_y].piece.color=='b'){
					Moveable[i][this.pos_y]=true;
					break;
				}
				else break;
			}

			for(int j=this.pos_y+1; j<8; j++){
				if(board[this.pos_x][j].piece==null || board[this.pos_x][j].piece.type=='F') Moveable[this.pos_x][j]=true;
				else if(board[this.pos_x][j].piece.color=='b'){
					Moveable[this.pos_x][j]=true;
					break;
				}
				else break;
			}

			for(int j=this.pos_y-1; j>=0; j--){
				if(board[this.pos_x][j].piece==null || board[this.pos_x][j].piece.type=='F') Moveable[this.pos_x][j]=true;
				else if(board[this.pos_x][j].piece.color=='b'){
					Moveable[this.pos_x][j]=true;
					break;
				}
				else break;
			}
		}
	}
}
