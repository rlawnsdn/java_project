
public class Bishop extends Piece {
	
	Bishop(int x, int y, char c) {
		
		this.pos_x = x;
		this.pos_y = y;
		this.color = c;
		this.type = 'B';
	}
	
	void findMovables(Square[][] board, boolean bqc, boolean bkc, boolean wqc, boolean wkc) {
		
		for(int i=0; i<8; i++) for(int j=0; j<8; j++) Moveable[i][j]=false;
		
		if(this.color=='b'){
			
			// Upper Right Side
			int i = this.pos_x+1, j = this.pos_y+1;
			while(0<=i && i<8 && 0<=j && j<8){
				if(board[i][j].piece==null) Moveable[i][j]=true;
				else if(board[i][j].piece.color=='b') break;
				else{
					Moveable[i][j]=true;
					break;
				}
				i++; j++;
			}
			
			// Upper Left Side
			i=this.pos_x+1; j=this.pos_y-1;
			while(0<=i && i<8 && 0<=j && j<8){
				if(board[i][j].piece==null) Moveable[i][j]=true;
				else if(board[i][j].piece.color=='b') break;
				else{
					Moveable[i][j]=true;
					break;
				}
				i++; j--;
			}
			
			// Lower Right Side
			i=this.pos_x-1; j=this.pos_y+1;
			while(0<=i && i<8 && 0<=j && j<8){
				if(i==0 && j>0 && j<4) wqc=false;
				if(i==0 && j>4 && j<8) wkc=false;
				if(board[i][j].piece==null) Moveable[i][j]=true;
				else if(board[i][j].piece.color=='b') break;
				else{
					Moveable[i][j]=true;
					break;
				}
				i--; j++;
			}
			
			// Lower Left Side
			i=this.pos_x-1; j=this.pos_y-1;
			while(0<=i && i<8 && 0<=j && j<8){
				if(i==0 && j>0 && j<4) wqc=false;
				if(i==0 && j>4 && j<8) wkc=false;
				if(board[i][j].piece==null) Moveable[i][j]=true;
				else if(board[i][j].piece.color=='b') break;
				else{
					Moveable[i][j]=true;
					break;
				}
				i--; j--;
			}
		}
		
		if(this.color=='w'){
			
			// Upper Right Side
			int i=this.pos_x+1, j=this.pos_y+1;
			while(0<=i && i<8 && 0<=j && j<8){
				if(i==7 && j>0 && j<4) bqc=false;
				if(i==7 && j>4 && j<8) bkc=false;
				if(board[i][j].piece==null) Moveable[i][j]=true;
				else if(board[i][j].piece.color=='w') break;
				else{
					Moveable[i][j]=true;
					break;
				}
				i++; j++;
			}

			// Upper Left Side
			i=this.pos_x+1; j=this.pos_y-1;
			while(0<=i && i<8 && 0<=j && j<8){
				if(i==7 && j>0 && j<4) bqc=false;
				if(i==7 && j>4 && j<8) bkc=false;
				if(board[i][j].piece==null) Moveable[i][j]=true;
				else if(board[i][j].piece.color=='w') break;
				else{
					Moveable[i][j]=true;
					break;
				}
				i++; j--;
			}

			// Lower Right Side
			i=this.pos_x-1; j=this.pos_y+1;
			while(0<=i && i<8 && 0<=j && j<8){
				if(board[i][j].piece==null) Moveable[i][j]=true;
				else if(board[i][j].piece.color=='w') break;
				else{
					Moveable[i][j]=true;
					break;
				}
				i--; j++;
			}

			// Lower Left Side
			i=this.pos_x-1; j=this.pos_y-1;
			while(0<=i && i<8 && 0<=j && j<8){
				if(board[i][j].piece==null) Moveable[i][j]=true;
				else if(board[i][j].piece.color=='w') break;
				else{
					Moveable[i][j]=true;
					break;
				}
				i--; j--;
			}
		}
	}
}

