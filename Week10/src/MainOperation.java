
public class MainOperation {

	public static void main(String[] args) {
		
		ChessBoard chessBoard = new ChessBoard();
		
		while (!chessBoard.isFinish()) {
			
			chessBoard.printBoard();
			/*
			1. 클릭으로 원래 좌표(x1, y1)과 이동할 좌표(x2, y2)를 추출
			2. ChessBoard.movepiece() 실행
			3. wr1, wr2, wk, br1, br2, bk와 wkc, wqc, bkc, bqc를 가지고 캐슬링 가능여부 계산
			4. 모든 Square에 대하여 findMovables 계산
			5. 기타 등등...


			아직 실행은 안시켜봐서 잘 돌아가는지는 모릅니다.... 일단 코드만 짜놨습니다.
			*/






			break; // Remove this line after implementing below
			
			// 1. Input(get) position of the Piece to move
			// 2. Check for the Squares the Piece can move onto
			// 3. Move if valid
			// ... and so on
		}
	}

}
