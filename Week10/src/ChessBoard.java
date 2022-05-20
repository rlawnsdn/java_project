class Square {

	char color;
	Piece piece;
	
	Square(int i, int j) {	// Initialize Board
		
		this.color = (i+j)%2==0 ? 'b' : 'w';
		if (i <= 1 || i >= 6) {
			char piececolor = i < 4 ? 'w' : 'b';
			if (i == 1 || i == 6)
				this.piece = new Pawn(i, j, piececolor);
			else {
				switch (j) {
				case 0: case 7: this.piece = new Rook(i, j, piececolor); 	break;
				case 1: case 6: this.piece = new Knight(i, j, piececolor); 	break;
				case 2: case 5: this.piece = new Bishop(i, j, piececolor); 	break;
				case 3:			this.piece = new Queen(i, j, piececolor); 	break;
				case 4:			this.piece = new King(i, j, piececolor);	break;
				}
			}
		}
		else
			this.piece = null;
	}
}

public class ChessBoard {
	
	Square[][] board;
	int turn;
	boolean gameEnds;
	boolean wr1, wr2, wk, br1, br2, bk; // white rook1, white rook2, white king, black rook1, black rook2, black king의 이동여부 (true면 아직 이동안한 것)
	boolean wkc, wqc, bkc, bqc; //캐슬링 가능여부

	ChessBoard() {
		
		this.board = new Square[8][8];
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				board[i][j] = new Square(i, j);
		
		this.turn = 0;
		this.wr1 = true; this.wr2 = true; this.wk = true; this.br1 = true; this.br2 = true; this.bk = true;
		this.wkc = true; this.wqc = true; this.bkc = true; this.bqc = true; 
		this.gameEnds = false;
	}
	
	void printBoard() { // Test with Console before implementing GUI
		
		for (int i=7; i>=0; i--) {
			for (int j=0; j<8; j++) {
				System.out.print('|');
				if (board[i][j].piece != null) { // && board[i][j].piece.type != 'F') {
					System.out.print(board[i][j].piece.color);
					System.out.print(board[i][j].piece.type);
				}
				else
					System.out.print("  ");
			}
			System.out.println('|');
		}
	}
	
	public boolean isFinish() {
		
		return this.gameEnds;
	}
	

	void movepiece(int x1, int y1, int x2, int y2){ //(x1, y1)에서 (x2, y2)로 이동할 때
		
		if(board[x1][y1].piece.type=='K'){
			
			// 킹이 이동하면 캐슬링 불가능 (킹의 움직임이 유효한 상태라 가정)
			if (board[x1][y1].piece.color=='b') {this.bk = false;}
			else {this.wk = false;}
			
			// 캐슬링
			if (x1 == 0 && y1 == 4 && x2 == 0 && y2 == 2){
				board[0][2].piece = board[0][4].piece; //킹 이동
				board[0][4].piece = null;
				board[0][3].piece = board[0][0].piece; //룩 이동
				board[0][0].piece = null;
			}
			else if (x1 == 0 && y1 == 4 && x2 == 0 && y2 == 6){
				board[0][6].piece = board[0][4].piece; //킹 이동
				board[0][4].piece = null;
				board[0][5].piece = board[0][7].piece; //룩 이동
				board[0][7].piece = null;
			}
			else if (x1 == 7 && y1 == 4 && x2 == 7 && y2 == 2){
				board[7][2].piece = board[7][4].piece; //킹 이동
				board[7][4].piece = null;
				board[7][3].piece = board[7][0].piece; //룩 이동
				board[7][0].piece = null;
			}
			else if (x1 == 7 && y1 == 4 && x2 == 7 && y2 == 6){
				board[7][6].piece = board[7][4].piece; //킹 이동
				board[7][4].piece = null;
				board[7][5].piece = board[7][7].piece; //룩 이동
				board[7][7].piece = null;
			}
			else
			{
				board[x2][y2].piece = board[x1][y1].piece;
				board[x1][y1].piece = null;
			}
		}
		else if (board[x1][y1].piece.type=='R'){ // 룩이 이동하면 캐슬링 불가능 (룩의 움직임이 유효한 상태라 가정)
			if(board[x1][y1].piece.color=='b'){
				if(y1==0){this.br1 = false;}
				else{this.br2 = false;}
			}
			else if(board[x1][y1].piece.color=='w'){
				if(y1==0){this.wr1 = false;}
				else{this.wr2 = false;}
			}
			
			board[x2][y2].piece = board[x1][y1].piece;
			board[x1][y1].piece = null;
		}
		else if(board[x1][y1].piece.type=='P'){ //앙파상
			if (Math.abs(x1 - x2) == 2){
				if (x1 < x2){
					board[x1+1][y1].piece = new Pawn(x1+1, y1, board[x1][y1].piece.color);
					board[x1+1][y1].piece.type = 'F'; //fake pawn을 만든다
				}
				else{
					board[x1-1][y1].piece = new Pawn(x1-1, y1, board[x1][y1].piece.color);
					board[x1-1][y1].piece.type = 'F';
				}
			}
			
			if (board[x2][y2].piece != null && board[x2][y2].piece.type =='F') // fake pawn이 있을 시 해당 pawn 잡기
			{
				if (board[x2][y2].piece.color == 'b') board[x2-1][y2].piece = null;
				else if (board[x2][y2].piece.color == 'w') board[x2+1][y2].piece = null;
			}
			
			board[x2][y2].piece = board[x1][y1].piece;
			board[x1][y1].piece = null;
		}
		else{ //평범한 이동
			board[x2][y2].piece = board[x1][y1].piece;
			board[x1][y1].piece = null;
		}
		
		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++)
				if(board[i][j].piece != null && board[i][j].piece.type =='F' && board[i][j].piece.color == (turn%2 == 0 ? 'b':'w'))
					board[i][j].piece = null; // 이전 턴에 만든 fake pawn이 있다면, fake pawn을 제거
	}
	




}
