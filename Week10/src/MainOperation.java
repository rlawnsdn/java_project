
public class MainOperation {

	public static void main(String[] args) {
		
		ChessBoard chessBoard = new ChessBoard();
		
		while (!chessBoard.isFinish()) {
			
			chessBoard.printBoard();
			
			break; // Remove this line after implementing below
			
			// 1. Input(get) position of the Piece to move
			// 2. Check for the Squares the Piece can move onto
			// 3. Move if valid
			// ... and so on
		}
	}

}
