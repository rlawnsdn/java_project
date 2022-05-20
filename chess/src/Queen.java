
public class Queen extends Piece {
	
	Queen(int x, int y, char c) {
		
		this.pos_x = x;
		this.pos_y = y;
		this.color = c;
		this.type = 'Q';
	}
	
	void findMovables(Square[][] board, boolean bqc, boolean bkc, boolean wqc, boolean wkc) {
		for(int i=0; i<8; i++) for(int j=0; j<8; j++) Moveable[i][j]=false;

            if(this.color=='b'){
                int i=this.pos_x+1, j=this.pos_y+1;
                while(0<=i && i<8 && 0<=j && j<8){
                    if(board[i][j]==null) Moveable[i][j]=true;
                    else if(board[i][j].color=='b') break;
                    else{
                        Moveable[i][j]=true;
                        break;
                    }
                    i++; j++; //우측 하단
                }

                i=this.pos_x+1; j=this.pos_y-1;
                while(0<=i && i<8 && 0<=j && j<8){
                    if(board[i][j]==null) Moveable[i][j]=true;
                    else if(board[i][j].color=='b') break;
                    else{
                        Moveable[i][j]=true;
                        break;
                    }
                    i++; j--; //좌측 하단
                }

                i=this.pos_x-1; j=this.pos_y+1;
                while(0<=i && i<8 && 0<=j && j<8){
                    if(board[i][j]==null) Moveable[i][j]=true;
                    else if(board[i][j].color=='b') break;
                    else{
                        Moveable[i][j]=true;
                        break;
                    }
                    i--; j++; //우측 상단
                }

                i=this.pos_x-1; j=this.pos_y-1;
                while(0<=i && i<8 && 0<=j && j<8){
                    if(board[i][j]==null) Moveable[i][j]=true;
                    else if(board[i][j].color=='b') break;
                    else{
                        Moveable[i][j]=true;
                        break;
                    }
                    i--; j--; //좌측 상단
                }

                for(i=this.pos_x+1; i<8; i++){ //아래쪽
                    if(board[i][this.pos_y]==null) Moveable[i][this.pos_y]=true;
                    else if(board[i][this.pos_y].color=='w'){
                        Moveable[i][this.pos_y]=true;
                        break;
                    }
                    else break;
                }

                for(i=this.pos_x-1; i>=0; i--){ //위쪽
                    if(board[i][this.pos_y]==null) Moveable[i][this.pos_y]=true;
                    else if(board[i][this.pos_y].color=='w'){
                        Moveable[i][this.pos_y]=true;
                        break;
                    }
                    else break;
                }

                for(j=this.pos_y+1; j<8; j++){ //오른쪽
                    if(board[this.pos_x][j]==null) Moveable[this.pos_x][j]=true;
                    else if(board[this.pos_x][j].color=='w'){
                        Moveable[this.pos_x][j]=true;
                        break;
                    }
                    else break;
                }

                for(j=this.pos_y-1; j>=0; j--){ //왼쪽
                    if(board[this.pos_x][j]==null) Moveable[this.pos_x][j]=true;
                    else if(board[this.pos_x][j].color=='w'){
                        Moveable[this.pos_x][j]=true;
                        break;
                    }
                    else break;
                }
            }

            if(this.color=='w'){
                int i=this.pos_x+1, j=this.pos_y+1;
                while(0<=i && i<8 && 0<=j && j<8){
                    if(board[i][j]==null) Moveable[i][j]=true;
                    else if(board[i][j].color=='w') break;
                    else{
                        Moveable[i][j]=true;
                        break;
                    }
                    i++; j++;
                }

                i=this.pos_x+1; j=this.pos_y-1;
                while(0<=i && i<8 && 0<=j && j<8){
                    if(board[i][j]==null) Moveable[i][j]=true;
                    else if(board[i][j].color=='w') break;
                    else{
                        Moveable[i][j]=true;
                        break;
                    }
                    i++; j--;
                }

                i=this.pos_x-1; j=this.pos_y+1;
                while(0<=i && i<8 && 0<=j && j<8){
                    if(board[i][j]==null) Moveable[i][j]=true;
                    else if(board[i][j].color=='w') break;
                    else{
                        Moveable[i][j]=true;
                        break;
                    }
                    i--; j++;
                }

                i=this.pos_x-1; j=this.pos_y-1;
                while(0<=i && i<8 && 0<=j && j<8){
                    if(board[i][j]==null) Moveable[i][j]=true;
                    else if(board[i][j].color=='w') break;
                    else{
                        Moveable[i][j]=true;
                        break;
                    }
                    i--; j--;
                }

                for(i=this.pos_x+1; i<8; i++){
                    if(board[i][this.pos_y]==null) Moveable[i][this.pos_y]=true;
                    else if(board[i][this.pos_y].color=='b'){
                        Moveable[i][this.pos_y]=true;
                        break;
                    }
                    else break;
                }

                for(i=this.pos_x-1; i>=0; i--){
                    if(board[i][this.pos_y]==null) Moveable[i][this.pos_y]=true;
                    else if(board[i][this.pos_y].color=='b'){
                        Moveable[i][this.pos_y]=true;
                        break;
                    }
                    else break;
                }

                for(j=this.pos_y+1; j<8; j++){
                    if(board[this.pos_x][j]==null) Moveable[this.pos_x][j]=true;
                    else if(board[this.pos_x][j].color=='b'){
                        Moveable[this.pos_x][j]=true;
                        break;
                    }
                    else break;
                }

                for(j=this.pos_y-1; j>=0; j--){
                    if(board[this.pos_x][j]==null) Moveable[this.pos_x][j]=true;
                    else if(board[this.pos_x][j].color=='b'){
                        Moveable[this.pos_x][j]=true;
                        break;
                    }
                    else break;
                }
            }
	}
}
