
public class King extends Piece {

	boolean checked;
	
	King(int x, int y, char c) {
		
		this.pos_x = x;
		this.pos_y = y;
		this.color = c;
		this.type = 'K';
	}

	
	void findMovables(Square[][] board, boolean bqc, boolean bkc, boolean wqc, boolean wkc) {
		for(int i=0; i<8; i++) for(int j=0; j<8; j++) Moveable[i][j]=false;
            if(this.color=='b'){
                for(int i=this.pos_x-1; i<=this.pos_x+1; i++){
                    for(int j=this.pos_y-1; j<=this.pos_y+1; j++){
                        if(0<=i && i<8 && 0<=j && j<8){
                            if(board[i][j]==null) Moveable[i][j]=true;
                            else if(board[i][j].color=='w') Moveable[i][j]=true;
                            else continue;
                        }
                    }
                }
                if(bqc && board[7][1]==null && board[7][2]==null && board[7][3]==null) Moveable[7][2]=true;
                if(bkc && board[7][5]==null && board[7][6]==null) Moveable[7][6]=true;
            }
            if(this.color=='w'){
                for(int i=this.pos_x-1; i<=this.pos_x+1; i++){
                    for(int j=this.pos_y-1; j<=this.pos_y+1; j++){
                        if(0<=i && i<8 && 0<=j && j<8){
                            if(board[i][j]==null) Moveable[i][j]=true;
                            else if(board[i][j].color=='b') Moveable[i][j]=true;
                            else continue;
                        }
                    }
                }
                if(wqc && board[0][2]==null && board[0][3]==null && board[0][1]==null) Moveable[0][2]=true;
                if(wkc && board[0][5]==null && board[0][6]==null) Moveable[0][6]=true;
            }
	}
}
