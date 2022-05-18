
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
	
	void findMovables(Square[][] board, boolean bqc, boolean bkc, boolean wqc, boolean wkc) {
		for(int i=0; i<8; i++) for(int j=0; j<8; j++) Moveable[i][j]=false;
            if(this.color=='B'){
                for(int i=this.pos_x-1; i<=this.pos_x+1; i++){
                    for(int j=this.pos_y-1; j<=this.pos_y+1; j++){
                        if(0<=i && i<8 && 0<=j && j<8){
                            if(board[i][j]==null) Moveable[i][j]=true;
                            else if(board[i][j].color=='W') Moveable[i][j]=true;
                            else continue;
                        }
                    }
                }
                if(bqc && board[0][1]==null && board[0][2]==null && board[0][3]==null) Moveable[0][2]=true;
                if(bkc && board[0][5]==null && board[0][6]==null) Moveable[0][6]=true;
            }
            if(this.color=='W'){
                for(int i=this.pos_x-1; i<=this.pos_x+1; i++){
                    for(int j=this.pos_y-1; j<=this.pos_y+1; j++){
                        if(0<=i && i<8 && 0<=j && j<8){
                            if(board[i][j]==null) Moveable[i][j]=true;
                            else if(board[i][j].color=='B') Moveable[i][j]=true;
                            else continue;
                        }
                    }
                }
                if(wqc && board[7][2]==null && board[7][3]==null && board[7][1]==null) Moveable[7][2]=true;
                if(wkc && board[7][5]==null && board[7][6]==null) Moveable[7][6]=true;
            }
	}
}
