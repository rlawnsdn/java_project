import java.util.Scanner;

public class MainOperation {

	public static void main(String[] args) {
		
		ChessBoard chessBoard = new ChessBoard();
		Scanner scn = new Scanner(System.in); // Input for Testing with Console
		
		BoardGUI bgui = new BoardGUI(chessBoard);
		chessBoard.btnInit();
		
		int turncheck = 0;
		
		while (!chessBoard.isFinish()) {

			turncheck = chessBoard.turn;
			chessBoard.printBoard();
			
			//1. 클릭으로 원래 좌표(x1, y1)과 이동할 좌표(x2, y2)를 추출
			System.out.println((chessBoard.turn%2==0? "White":"Black") + "'s Turn");		
			
			//2. chessBoard.movepiece() 실행
			//3. wr1, wr2, wk, br1, br2, bk와 wkc, wqc, bkc, bqc를 가지고 캐슬링 가능여부 계산
			//4. 모든 Square에 대하여 findMovables 계산
			//5. 기타 등등...
			
			while (chessBoard.turn == turncheck)
			{
				System.out.print(""); // chessBoard.turn의 변경이 감지될 때까지 기다리는 부분. 추후 스레드 관련으로 변환 필요.
			}
			
			bgui.updateBoardGUI(chessBoard.sq);
		}
	}

}
