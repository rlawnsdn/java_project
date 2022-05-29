
public class Queen extends Piece {
	
	Queen(int x, int y, char c) {
		
		this.pos_x = x;
		this.pos_y = y;
		this.color = c;
		this.type = 'Q';
	}
	
	void findMovables(Square[][] board, boolean bqc, boolean bkc, boolean wqc, boolean wkc) {
		for(int i=0; i<8; i++) for(int j=0; j<8; j++) Moveable[i][j]=false;

			// Rook's movable squares + Bishop's movable squares
		
            if(this.color=='b'){
                int i=this.pos_x+1, j=this.pos_y+1;
                while(0<=i && i<8 && 0<=j && j<8){
                    if(board[i][j].piece==null) Moveable[i][j]=true;
                    else if(board[i][j].piece.color=='b') break;
                    else{
                        Moveable[i][j]=true;
                        break;
                    }
                    i++; j++;
                }

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

                i=this.pos_x-1; j=this.pos_y+1;
                while(0<=i && i<8 && 0<=j && j<8){
                    if(board[i][j].piece==null) Moveable[i][j]=true;
                    else if(board[i][j].piece.color=='b') break;
                    else{
                        Moveable[i][j]=true;
                        break;
                    }
                    i--; j++;
                }

                i=this.pos_x-1; j=this.pos_y-1;
                while(0<=i && i<8 && 0<=j && j<8){
                    if(board[i][j].piece==null) Moveable[i][j]=true;
                    else if(board[i][j].piece.color=='b') break;
                    else{
                        Moveable[i][j]=true;
                        break;
                    }
                    i--; j--;
                }

                for(i=this.pos_x+1; i<8; i++){
                    if(board[i][this.pos_y].piece==null) Moveable[i][this.pos_y]=true;
                    else if(board[i][this.pos_y].piece.color=='w'){
                        Moveable[i][this.pos_y]=true;
                        break;
                    }
                    else break;
                }

                for(i=this.pos_x-1; i>=0; i--){
                    if(board[i][this.pos_y].piece==null) Moveable[i][this.pos_y]=true;
                    else if(board[i][this.pos_y].piece.color=='w'){
                        Moveable[i][this.pos_y]=true;
                        break;
                    }
                    else break;
                }

                for(j=this.pos_y+1; j<8; j++){
                    if(board[this.pos_x][j].piece==null) Moveable[this.pos_x][j]=true;
                    else if(board[this.pos_x][j].piece.color=='w'){
                        Moveable[this.pos_x][j]=true;
                        break;
                    }
                    else break;
                }

                for(j=this.pos_y-1; j>=0; j--){
                    if(board[this.pos_x][j].piece==null) Moveable[this.pos_x][j]=true;
                    else if(board[this.pos_x][j].piece.color=='w'){
                        Moveable[this.pos_x][j]=true;
                        break;
                    }
                    else break;
                }
            }

            if(this.color=='w'){
                int i=this.pos_x+1, j=this.pos_y+1;
                while(0<=i && i<8 && 0<=j && j<8){
                    if(board[i][j].piece==null) Moveable[i][j]=true;
                    else if(board[i][j].piece.color=='w') break;
                    else{
                        Moveable[i][j]=true;
                        break;
                    }
                    i++; j++;
                }

                i=this.pos_x+1; j=this.pos_y-1;
                while(0<=i && i<8 && 0<=j && j<8){
                    if(board[i][j].piece==null) Moveable[i][j]=true;
                    else if(board[i][j].piece.color=='w') break;
                    else{
                        Moveable[i][j]=true;
                        break;
                    }
                    i++; j--;
                }

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

                for(i=this.pos_x+1; i<8; i++){
                    if(board[i][this.pos_y].piece==null) Moveable[i][this.pos_y]=true;
                    else if(board[i][this.pos_y].piece.color=='b'){
                        Moveable[i][this.pos_y]=true;
                        break;
                    }
                    else break;
                }

                for(i=this.pos_x-1; i>=0; i--){
                    if(board[i][this.pos_y].piece==null) Moveable[i][this.pos_y]=true;
                    else if(board[i][this.pos_y].piece.color=='b'){
                        Moveable[i][this.pos_y]=true;
                        break;
                    }
                    else break;
                }

                for(j=this.pos_y+1; j<8; j++){
                    if(board[this.pos_x][j].piece==null) Moveable[this.pos_x][j]=true;
                    else if(board[this.pos_x][j].piece.color=='b'){
                        Moveable[this.pos_x][j]=true;
                        break;
                    }
                    else break;
                }

                for(j=this.pos_y-1; j>=0; j--){
                    if(board[this.pos_x][j].piece==null) Moveable[this.pos_x][j]=true;
                    else if(board[this.pos_x][j].piece.color=='b'){
                        Moveable[this.pos_x][j]=true;
                        break;
                    }
                    else break;
                }
            }
	}
}
