
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
                            if(board[i][j].piece==null) Moveable[i][j]=true;
                            else if(board[i][j].piece.color=='w') Moveable[i][j]=true;
                            else continue;
                        }
                    }
                }
                
                // Check whether the King may move two squares, being able to Castle
                if(bqc && board[7][1].piece==null && board[7][2].piece==null && board[7][3].piece==null) Moveable[7][2]=true;
                if(bkc && board[7][5].piece==null && board[7][6].piece==null) Moveable[7][6]=true;
            }
            
            if(this.color=='w'){
            	
                for(int i=this.pos_x-1; i<=this.pos_x+1; i++){
                    for(int j=this.pos_y-1; j<=this.pos_y+1; j++){
                        if(0<=i && i<8 && 0<=j && j<8){
                            if(board[i][j].piece==null) Moveable[i][j]=true;
                            else if(board[i][j].piece.color=='b') Moveable[i][j]=true;
                            else continue;
                        }
                    }
                }
                
                // Check whether the King may move two squares, being able to Castle
                if (wqc && board[0][2].piece==null && board[0][3].piece==null && board[0][1].piece==null) Moveable[0][2]=true;
                if (wkc && board[0][5].piece==null && board[0][6].piece==null) Moveable[0][6]=true;
            }
	}
}
