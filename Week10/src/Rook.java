
public class Rook extends Piece {

	boolean nevermoved;
	
	Rook(int x, int y, char c) {
		
		this.pos_x = x;
		this.pos_y = y;
		this.color = c;
		this.type = 'R';
		
		this.nevermoved = true;
	}
	
	@Override
	void move(int x, int y) {
		
		this.pos_x = x;
		this.pos_y = y;
		
		this.nevermoved = false;
	}
	
	void findMovables(Square[][] board) {
		for(int i=0; i<8; i++) for(int j=0; j<8; j++) Moveable[i][j]=false;
		if(this.color=='W'){
			for(int i=this.pos_x+1; i<8; i++){ //아래쪽 방향
				if(board[i][this.pos_y]==null) Moveable[i][this.pos_y]=true;
				else if(board[i][this.pos_y].color=='W'){
					Moveable[i][this.pos_y]=true;
					break;
				}
				else break;
			}

			for(int i=this.pos_x-1; i>=0; i--){ //위쪽 방향
				if(board[i][this.pos_y]==null) Moveable[i][this.pos_y]=true;
				else if(board[i][this.pos_y].color=='W'){
					Moveable[i][this.pos_y]=true;
					break;
				}
				else break;
			}

			for(int j=this.pos_y+1; j<8; j++){ //오른쪽 방향
				if(board[this.pos_x][j]==null) Moveable[this.pos_x][j]=true;
				else if(board[this.pos_x][j].color=='W'){
					Moveable[this.pos_x][j]=true;
					break;
				}
				else break;
			}

			for(int j=this.pos_y-1; j>=0; j--){ //왼쪽 방향
				if(board[this.pos_x][j]==null) Moveable[this.pos_x][j]=true;
				else if(board[this.pos_x][j].color=='W'){
					Moveable[this.pos_x][j]=true;
					break;
				}
				else break;
			}
		}

		if(this.color=='W'){
			for(int i=this.pos_x+1; i<8; i++){
				if(board[i][this.pos_y]==null) Moveable[i][this.pos_y]=true;
				else if(board[i][this.pos_y].color=='B'){
					Moveable[i][this.pos_y]=true;
					break;
				}
				else break;
			}

			for(int i=this.pos_x-1; i>=0; i--){
				if(board[i][this.pos_y]==null) Moveable[i][this.pos_y]=true;
				else if(board[i][this.pos_y].color=='B'){
					Moveable[i][this.pos_y]=true;
					break;
				}
				else break;
			}

			for(int j=this.pos_y+1; j<8; j++){
				if(board[this.pos_x][j]==null) Moveable[this.pos_x][j]=true;
				else if(board[this.pos_x][j].color=='B'){
					Moveable[this.pos_x][j]=true;
					break;
				}
				else break;
			}

			for(int j=this.pos_y-1; j>=0; j--){
				if(board[this.pos_x][j]==null) Moveable[this.pos_x][j]=true;
				else if(board[this.pos_x][j].color=='B'){
					Moveable[this.pos_x][j]=true;
					break;
				}
				else break;
			}
		}
	}
}
