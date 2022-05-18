
public class ChessBoard {
	
	Square[][] board;
	int turn;
	boolean gameEnds;
	
	ChessBoard() {
		
		this.board = new Square[8][8];
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				board[i][j] = new Square(i, j);
		
		this.turn = 0;
		this.gameEnds = false;
	}
	
	void printBoard() { // Test with Console before implementing GUI
		
		for (int i=7; i>=0; i--) {
			for (int j=0; j<8; j++) {
				System.out.print('|');
				if (board[i][j].piece != null) {
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
}

class Square {
	
	char color;
	Piece piece;
	
	Square(int i, int j) {	// Initialize Board
		
		this.color = (i+j)%2==0 ? 'B' : 'W';
		if (i <= 1 || i >= 6) {
			char piececolor = i < 4 ? 'B' : 'W';
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
