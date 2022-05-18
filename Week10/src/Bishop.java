
public class Bishop extends Piece {
	
	Bishop(int x, int y, char c) {
		
		this.pos_x = x;
		this.pos_y = y;
		this.color = c;
		this.type = 'B';
	}
	
	void findMovables(Square[][] board) {
		for(int i=0; i<8; i++) for(int j=0; j<8; j++) Moveable[i][j]=false;
		if(this.color=='B'){ //비숍은 대각선으로 원하는 만큼 이동할 수 있다. 단, 앞에 말이 가로막고 있으면 이동할 수 없고 상대 말이면 그 말을 잡을 수 있다
			int i = this.pos_x+1, j = this.pos_y+1;
			while(0<=i && i<8 && 0<=j && j<8){
				if(board[i][j]==null) Moveable[i][j]=true;
				else if(board[i][j].color=='B') break;
				else{
					Moveable[i][j]=true;
					break;
				}
				i++; j++; //우측 하단 방향
			}
			i=this.pos_x+1; j=this.pos_y-1;
			while(0<=i && i<8 && 0<=j && j<8){
				if(board[i][j]==null) Moveable[i][j]=true;
				else if(board[i][j].color=='B') break;
				else{
					Moveable[i][j]=true;
					break;
				}
				i++; j--; //좌측 하단 방향
			}
			i=this.pos_x-1; j=this.pos_y+1;
			while(0<=i && i<8 && 0<=j && j<8){
				if(board[i][j]==null) Moveable[i][j]=true;
				else if(board[i][j].color=='B') break;
				else{
					Moveable[i][j]=true;
					break;
				}
				i--; j++; //우측 상단 방향
			}
			i=this.pos_x-1; j=this.pos_y-1;
			while(0<=i && i<8 && 0<=j && j<8){
				if(board[i][j]==null) Moveable[i][j]=true;
				else if(board[i][j].color=='B') break;
				else{
					Moveable[i][j]=true;
					break;
				}
				i--; j--; //좌측 상단 방향
			}
		}
		if(this.color=='W'){
			int i=this.pos_x+1, j=this.pos_y+1;
			while(0<=i && i<8 && 0<=j && j<8){
				if(board[i][j]==null) Moveable[i][j]=true;
				else if(board[i][j].color=='W') break;
				else{
					Moveable[i][j]=true;
					break;
				}
				i++; j++;
			}

			i=this.pos_x+1; j=this.pos_y-1;
			while(0<=i && i<8 && 0<=j && j<8){
				if(board[i][j]==null) Moveable[i][j]=true;
				else if(board[i][j].color=='W') break;
				else{
					Moveable[i][j]=true;
					break;
				}
				i++; j--;
			}

			i=this.pos_x-1; j=this.pos_y+1;
			while(0<=i && i<8 && 0<=j && j<8){
				if(board[i][j]==null) Moveable[i][j]=true;
				else if(board[i][j].color=='W') break;
				else{
					Moveable[i][j]=true;
					break;
				}
				i--; j++;
			}

			i=this.pos_x-1; j=this.pos_y-1;
			while(0<=i && i<8 && 0<=j && j<8){
				if(board[i][j]==null) Moveable[i][j]=true;
				else if(board[i][j].color=='W') break;
				else{
					Moveable[i][j]=true;
					break;
				}
				i--; j--;
			}
		}
	}
}

