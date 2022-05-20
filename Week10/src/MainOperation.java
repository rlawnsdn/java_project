import java.util.Scanner;

public class MainOperation {

	public static void main(String[] args) {
		
		ChessBoard chessBoard = new ChessBoard();
		Scanner scn = new Scanner(System.in); // Input for Testing with Console
		int x1, y1, x2, y2;
		
		while (!chessBoard.isFinish()) {
			
			chessBoard.printBoard();

			//1. 클릭으로 원래 좌표(x1, y1)과 이동할 좌표(x2, y2)를 추출
			System.out.println((chessBoard.turn%2==0? "White":"Black") + "'s Turn");
			x1 = scn.nextInt();
			y1 = scn.nextInt();
			x2 = scn.nextInt();
			y2 = scn.nextInt();
			
			//2. chessBoard.movepiece() 실행
			chessBoard.movepiece(x1, y1, x2, y2);
			
			//3. wr1, wr2, wk, br1, br2, bk와 wkc, wqc, bkc, bqc를 가지고 캐슬링 가능여부 계산
			//4. 모든 Square에 대하여 findMovables 계산
			//5. 기타 등등...

			chessBoard.turn++;
		}
	}

}
