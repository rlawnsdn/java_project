import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Square extends JPanel {

	int x, y;
	JLabel pieceimg;
	JButton btn;
	boolean clickable;
	
	char color;
	Piece piece;
	
	Square(int i, int j) {	// Initialize Board
		
		this.x = i;
		this.y = j;
		
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
		
		setSize(80, 80);
		setLayout(null);
		setBackground(this.color == 'b' ? Color.getHSBColor(32/360f, 0.90f, 0.18f) : Color.getHSBColor(32/360f, 0.23f, 0.84f));
		
		ImageIcon icn;
		if (this.piece != null && this.piece.type != 'F')
		{
			icn = new ImageIcon("src/img/" + this.piece.color + this.piece.type + "_n.png");
			pieceimg = new JLabel(icn);
		}
		else
		{
			icn = new ImageIcon("src/img/none.png");
			pieceimg = new JLabel(icn);
		}
		pieceimg.setBounds(0, 0, 80, 80);
		pieceimg.setAlignmentX(CENTER_ALIGNMENT);
		pieceimg.setAlignmentY(CENTER_ALIGNMENT);
		
		btn = new JButton();
		btn.setBounds(0, 0, 80, 80);
		btn.setAlignmentX(CENTER_ALIGNMENT);
		btn.setAlignmentY(CENTER_ALIGNMENT);
		btn.setContentAreaFilled(false);
		
		this.add(pieceimg);
		this.add(btn);
		
		clickable = false;
	}
	
	Square(Square s) { // Copy the state of the Square
		this.x = s.x;
		this.y = s.y;
		
		this.pieceimg = s.pieceimg;
		this.btn = s.btn;
		this.clickable = s.clickable;
		
		this.color = s.color;
		if (s.piece == null)
			this.piece = null;
		switch (s.piece.type) {
			case 'P': this.piece = new Pawn(s.piece.pos_x, s.piece.pos_y, s.piece.color);	break;
			case 'K': this.piece = new King(s.piece.pos_x, s.piece.pos_y, s.piece.color); break;
			case 'Q': this.piece = new Queen(s.piece.pos_x, s.piece.pos_y, s.piece.color); break;
			case 'R': this.piece = new Rook(s.piece.pos_x, s.piece.pos_y, s.piece.color); break;
			case 'B': this.piece = new Bishop(s.piece.pos_x, s.piece.pos_y, s.piece.color); break;
			case 'N': this.piece = new Knight(s.piece.pos_x, s.piece.pos_y, s.piece.color); break;
		}
	}
	
	void updatePiecePosition()
	{
		if (piece == null) return;
		
		piece.pos_x = x;
		piece.pos_y = y;
	}
	
	void updatePanel()
	{
		ImageIcon icn;
		if (this.piece != null && this.piece.type != 'F')
		{
			icn = new ImageIcon("src/img/" + this.piece.color + this.piece.type + "_n.png");
			pieceimg.setIcon(icn);
		}
		else
		{
			icn = new ImageIcon("src/img/none.png");
			pieceimg.setIcon(icn);
		}
		setBackground(this.color == 'b' ? Color.getHSBColor(32/360f, 0.90f, 0.18f) : Color.getHSBColor(32/360f, 0.23f, 0.84f));
	}
	
	void showMovable(boolean movable)
	{
		if (movable) {		
			if (this.piece != null && this.piece.type != 'F') {
				ImageIcon icn = new ImageIcon("src/img/" + this.piece.color + this.piece.type + "_t.png");
				pieceimg.setIcon(icn);
			}
			setBackground(this.color == 'b' ? Color.getHSBColor(100/360f, 0.70f, 0.30f) : Color.getHSBColor(100/360f, 0.55f, 0.45f));
		}
		else
		{
			if (this.piece != null && this.piece.type != 'F')
			{
				ImageIcon icn = new ImageIcon("src/img/" + this.piece.color + this.piece.type + "_n.png");
				pieceimg.setIcon(icn);
			}
			setBackground(this.color == 'b' ? Color.getHSBColor(32/360f, 0.90f, 0.18f) : Color.getHSBColor(32/360f, 0.23f, 0.84f));
		}
	}
	
	void showSelectedPiece()
	{
		if (this.piece != null && this.piece.type != 'F') {
			ImageIcon icn = new ImageIcon("src/img/" + this.piece.color + this.piece.type + "_c.png");
			pieceimg.setIcon(icn);
		}
		setBackground(this.color == 'b' ? Color.getHSBColor(210/360f, 0.70f, 0.30f) : Color.getHSBColor(210/360f, 0.55f, 0.45f));
	}
}

public class ChessBoard extends JFrame {
	
	Square[][] sq;
	int turn;
	boolean gameEnds;
	boolean wr1, wr2, wk, br1, br2, bk; // white rook1, white rook2, white king, black rook1, black rook2, black king의 이동여부 (true면 아직 이동안한 것)
	boolean wkc, wqc, bkc, bqc; //캐슬링 가능여부
	
	boolean selectstate; // false: 기물 선택, true: 위치 선택
	boolean playsWhite;
	
	Piece wKing, bKing;
	
	char preferredPromotion;
	
	int x1, y1, x2, y2;

	ChessBoard() {
		
		this.sq = new Square[8][8];
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				sq[i][j] = new Square(i, j);
		
		wKing = sq[0][4].piece;
		bKing = sq[7][4].piece;
		
		this.turn = 0;
		this.wr1 = true; this.wr2 = true; this.wk = true; this.br1 = true; this.br2 = true; this.bk = true;
		this.wkc = true; this.wqc = true; this.bkc = true; this.bqc = true; 
		this.gameEnds = false;
		
		this.selectstate = false;
		
		this.preferredPromotion = 'Q';
		
		btnInit();
	}
	
	void printBoard() { // Test with Console before implementing GUI
		
		for (int i=7; i>=0; i--) {
			for (int j=0; j<8; j++) {
				System.out.print('|');
				if (sq[i][j].piece != null) {
					System.out.print(sq[i][j].piece.color);
					System.out.print(sq[i][j].piece.type);
				}
				else
					System.out.print("  ");
			}
			System.out.println('|');
		}
	}
	
	Square[][] copyBoard(Square[][] sq) {
		
		Square[][] prev = new Square[8][8];
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				prev[i][j] = new Square(sq[i][j]);
		
		return prev;
	}
	
	void btnInit() { // 버튼 컴포넌트 초기설정
		
		for (int i=7; i>=0; i--) {
			for (int j=0; j<8; j++) {
				sq[i][j].btn.addActionListener(new SquareClick(i, j, sq[i][j]) {
					@Override
					public void actionPerformed (ActionEvent e) {
						
						if ((turn%2 == 0) != playsWhite) return;
						
						System.out.println("Button Clicked. " + i + "," + j);
						if (s.piece != null && s.piece.color == (turn%2 == 0 ? 'w' : 'b')) {
							System.out.println("Piece Selected. " + i + "," + j);
							x1 = i;
							y1 = j;
							
							System.out.println(notAttacked(7, 5, 'b'));
							System.out.println(notAttacked(7, 6, 'b'));

							s.piece.findMovables(sq, bqc, bkc, wqc, wkc);
							
							System.out.println(notAttacked(7, 5, 'b'));
							System.out.println(notAttacked(7, 6, 'b'));
							
							if (s.piece.type == 'K')
								updateMovableForAllPieces();
							
							System.out.println(notAttacked(7, 5, 'b'));
							System.out.println(notAttacked(7, 6, 'b'));

							
							s.piece.checkMovables(); // Debug: 콘솔에서 유효이동칸 확인하는 용도로 쓴 후 지우기
							selectstate = true;
							
							setClickable(s.piece.Moveable);
							s.showSelectedPiece();
						}
						else if (selectstate && sq[i][j].clickable) {
							
							Square[][] prev = copyBoard(sq); // 현재 board 상태 저장
							
							System.out.println("Square Selected. " + i + "," + j);
							x2 = i;
							y2 = j;
							movepiece(x1, y1, x2, y2, preferredPromotion);
							
							selectstate = false;		
							updateMovableForAllPieces();
							
							// ** Check 판단 하고서 만약 check이면 prev를 다시 sq에 먹이기
							if (checkCheck((turn%2 == 0 ? wKing : bKing), (turn%2 == 0 ? 'w' : 'b')))
							{
								//wk, rk 등 변수 되돌릴 방법 생각해봐야
								System.out.println("Checked!!");
								sq = prev;
								cancelSelections();
								setClickable(true);
							}
							else {
								setClickable(false);
								turn++;
							}
						}
					}
				});
			}
		}
	}
	
	boolean checkCheck(Piece king, char c) {
		
		return !notAttacked(king.pos_x, king.pos_y, c);
	}
	
	void setClickable(boolean[][] mov) { // 버튼 활성화 여부 결정
		
		for (int i=7; i>=0; i--)
			for (int j=0; j<8; j++) {
				sq[i][j].clickable = mov[i][j];
				sq[i][j].showMovable(mov[i][j]);
			}
	}
	
	void cancelSelections() { // 선택 해제
		
		for (int i=7; i>=0; i--)
			for (int j=0; j<8; j++) {
				sq[i][j].updatePanel();
			}
	}
	
	void setClickable(boolean clk) { // 버튼 활성화 여부 결정 (일괄)
		
		for (int i=7; i>=0; i--)
			for (int j=0; j<8; j++)
					sq[i][j].clickable = clk;
	}
	
	public boolean isFinish() {
		
		return this.gameEnds;
	}
	

	void movepiece(int x1, int y1, int x2, int y2, char pref){ //(x1, y1)에서 (x2, y2)로 이동할 때
		
		if(sq[x1][y1].piece.type=='K'){
			
			// 킹이 이동하면 캐슬링 불가능 (킹의 움직임이 유효한 상태라 가정)
			if (sq[x1][y1].piece.color=='b') {this.bk = false;}
			else {this.wk = false;}
			
			// 캐슬링
			if (x1 == 0 && y1 == 4 && x2 == 0 && y2 == 2){
				sq[0][2].piece = sq[0][4].piece; //킹 이동
				sq[0][4].piece = null;
				sq[0][3].piece = sq[0][0].piece; //룩 이동
				sq[0][0].piece = null;
			}
			else if (x1 == 0 && y1 == 4 && x2 == 0 && y2 == 6){
				sq[0][6].piece = sq[0][4].piece; //킹 이동
				sq[0][4].piece = null;
				sq[0][5].piece = sq[0][7].piece; //룩 이동
				sq[0][7].piece = null;
			}
			else if (x1 == 7 && y1 == 4 && x2 == 7 && y2 == 2){
				sq[7][2].piece = sq[7][4].piece; //킹 이동
				sq[7][4].piece = null;
				sq[7][3].piece = sq[7][0].piece; //룩 이동
				sq[7][0].piece = null;
			}
			else if (x1 == 7 && y1 == 4 && x2 == 7 && y2 == 6){
				sq[7][6].piece = sq[7][4].piece; //킹 이동
				sq[7][4].piece = null;
				sq[7][5].piece = sq[7][7].piece; //룩 이동
				sq[7][7].piece = null;
			}
			else
			{
				sq[x2][y2].piece = sq[x1][y1].piece;
				sq[x1][y1].piece = null;
			}
		}
		else if (sq[x1][y1].piece.type=='R'){ // 룩이 이동하면 캐슬링 불가능 (룩의 움직임이 유효한 상태라 가정)
			if(sq[x1][y1].piece.color=='b'){
				if(y1==0){this.br1 = false;}
				else{this.br2 = false;}
			}
			else if(sq[x1][y1].piece.color=='w'){
				if(y1==0){this.wr1 = false;}
				else{this.wr2 = false;}
			}
			
			sq[x2][y2].piece = sq[x1][y1].piece;
			sq[x1][y1].piece = null;
		}
		else if(sq[x1][y1].piece.type=='P'){ //앙파상 & 프로모션
			if (Math.abs(x1 - x2) == 2){
				if (x1 < x2){
					sq[x1+1][y1].piece = new Pawn(x1+1, y1, sq[x1][y1].piece.color);
					sq[x1+1][y1].piece.type = 'F'; //fake pawn을 만든다
				}
				else{
					sq[x1-1][y1].piece = new Pawn(x1-1, y1, sq[x1][y1].piece.color);
					sq[x1-1][y1].piece.type = 'F';
				}
			}
			
			if (sq[x2][y2].piece != null && sq[x2][y2].piece.type =='F') // fake pawn이 있을 시 해당 pawn 잡기
			{
				if (sq[x2][y2].piece.color == 'b') sq[x2-1][y2].piece = null;
				else if (sq[x2][y2].piece.color == 'w') sq[x2+1][y2].piece = null;
			}
			
			sq[x2][y2].piece = sq[x1][y1].piece;
			sq[x1][y1].piece = null;
			
			if (sq[x2][y2].piece.color == 'b' && x2 == 0) {
				switch (pref) {
				case 'Q':	sq[x2][y2].piece = new Queen(x2, y2, 'b'); 		break;
				case 'N':	sq[x2][y2].piece = new Knight(x2, y2, 'b'); 	break;
				case 'B': 	sq[x2][y2].piece = new Bishop(x2, y2, 'b'); 	break;
				case 'R':	sq[x2][y2].piece = new Rook(x2, y2, 'b');		break;
				}
			}
			else if (sq[x2][y2].piece.color == 'w' && x2 == 7) {
				switch (pref) {
				case 'Q':	sq[x2][y2].piece = new Queen(x2, y2, 'w'); 		break;
				case 'N':	sq[x2][y2].piece = new Knight(x2, y2, 'w'); 	break;
				case 'B': 	sq[x2][y2].piece = new Bishop(x2, y2, 'w'); 	break;
				case 'R':	sq[x2][y2].piece = new Rook(x2, y2, 'w');		break;
				}
			}
		}
		else{ //평범한 이동
			sq[x2][y2].piece = sq[x1][y1].piece;
			sq[x1][y1].piece = null;
		}
		
		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++)
				if(sq[i][j].piece != null && sq[i][j].piece.type =='F' && sq[i][j].piece.color == (turn%2 == 0 ? 'b':'w'))
					sq[i][j].piece = null; // 이전 턴에 만든 fake pawn이 있다면, fake pawn을 제거
	}

	boolean notAttacked(int x, int y, char color) { // 입력된 칸이 'color'의 입장에서 공격받는 상태인지 체크 (color가 흑이면 흑의 입장에서 백에게 공격받는지?)
		color = color == 'b' ? 'w':'b';
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				if (sq[i][j].piece != null && sq[i][j].piece.color == color && sq[i][j].piece.type != 'F' && sq[i][j].piece.Moveable[x][y] == true) {
					return false;
				}
			}
		}
		return true;
	}

	void updateMovableForAllPieces() { 
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				if (sq[i][j].piece != null)
					sq[i][j].piece.findMovables(sq, bqc, bkc, wqc, wkc);
			}
		}
		
		// 캐슬링 가능 여부 최종 확인(킹과 룩 사이 칸들이 공격받지 않아야 한다.)
		wqc = wk && wr1 && notAttacked(0, 1, 'w') && notAttacked(0, 2, 'w') && notAttacked(0, 3, 'w') && notAttacked(0, 4, 'w');
		wkc = wk && wr2 && notAttacked(0, 4, 'w') && notAttacked(0, 5, 'w') && notAttacked(0, 6, 'w');
		bqc = bk && br1 && notAttacked(7, 1, 'b') && notAttacked(7, 2, 'b') && notAttacked(7, 3, 'b') && notAttacked(7, 4, 'b');
		bkc = bk && br2 && notAttacked(7, 4, 'b') && notAttacked(7, 5, 'b') && notAttacked(7, 6, 'b');
	}
}
