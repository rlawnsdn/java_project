import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

class Square extends JPanel {

	int x, y;
	JLabel pieceimg;
	JButton btn;
	boolean clickable;
	
	char color;
	Piece piece;
	
	// Initialize Board
	Square(int i, int j) {
		
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
	
	Square(Square s) {
		this.x = s.x;
		this.y = s.y;
		this.clickable = s.clickable;
		this.color = s.color;
		if (s.piece == null)
			this.piece = null;
		else {
			switch (s.piece.type) {
			case 'P': this.piece = new Pawn(this.x, this.y, s.piece.color);	break;
			case 'K': this.piece = new King(this.x, this.y, s.piece.color); break;
			case 'Q': this.piece = new Queen(this.x, this.y, s.piece.color); break;
			case 'R': this.piece = new Rook(this.x, this.y, s.piece.color); break;
			case 'B': this.piece = new Bishop(this.x, this.y, s.piece.color); break;
			case 'N': this.piece = new Knight(this.x, this.y, s.piece.color); break;
			}
		}
	}
	
	void pasteSquare(Square s) {
		this.x = s.x;
		this.y = s.y;
		this.clickable = s.clickable;
		this.color = s.color;
		if (s.piece == null)
			this.piece = null;
		else {
			switch (s.piece.type) {
			case 'P': this.piece = new Pawn(this.x, this.y, s.piece.color);	break;
			case 'K': this.piece = new King(this.x, this.y, s.piece.color); break;
			case 'Q': this.piece = new Queen(this.x, this.y, s.piece.color); break;
			case 'R': this.piece = new Rook(this.x, this.y, s.piece.color); break;
			case 'B': this.piece = new Bishop(this.x, this.y, s.piece.color); break;
			case 'N': this.piece = new Knight(this.x, this.y, s.piece.color); break;
			}
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
	boolean wr1, wr2, wk, br1, br2, bk;	// whether Rooks and King have not moved yet
	boolean wkc, wqc, bkc, bqc; 		// whether Castling is possible
	
	boolean selectstate; // if true, the button may work to determine the destination of the selected piece
	boolean playsWhite;
	
	Piece wKing, bKing;
	
	char preferredPromotion;
	int x1, y1, x2, y2;
	
	int commandType;
	JTextPane systemmsg;
	
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
		this.commandType = -1;
		
		this.systemmsg = new JTextPane();
		
		btnInit();
	}
	
	Square[][] copyBoard(Square[][] sq) {
		
		Square[][] prev = new Square[8][8];
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				prev[i][j] = new Square(sq[i][j]);
		
		return prev;
	}
	
	void pasteBoard(Square[][] prev) {
		
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++) {
				sq[i][j].pasteSquare(prev[i][j]);
				if (sq[i][j].piece != null && sq[i][j].piece.type == 'K') {
					if (sq[i][j].piece.color == 'w') 		wKing = sq[i][j].piece;
					else if (sq[i][j].piece.color == 'b')	bKing = sq[i][j].piece;
				}
			}

	}
	
	// Set what the Buttons on the Squares should do
	void btnInit() {
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
							
							s.piece.findMovables(sq, bqc, bkc, wqc, wkc);
							selectstate = true;
							
							setClickable(s.piece.Moveable);
							s.showSelectedPiece();
						}
						else if (selectstate && sq[i][j].clickable) {
							
							updateAllPanels();
							
							Square[][] prev = copyBoard(sq); // Save the current state of the board
							boolean tmp_wr1 = wr1;	boolean tmp_wr2 = wr2;	boolean tmp_wk = wk;
							boolean tmp_br1 = br1;	boolean tmp_br2 = br2;	boolean tmp_bk = bk;
							
							System.out.println("Square Selected. " + i + "," + j);
							x2 = i;
							y2 = j;
							movepiece(x1, y1, x2, y2, preferredPromotion);
							
							selectstate = false;		
							updateMovableForAllPieces();

							// If the attempted move cannot escape Check, cancel the move
							if (checkCheck((turn%2 == 0 ? wKing : bKing), (turn%2 == 0 ? 'w' : 'b')))
							{
								pasteBoard(prev); // sq <- prev
								wr1 = tmp_wr1;	wr2 = tmp_wr2;	wk = tmp_wk;
								br1 = tmp_br1;	br2 = tmp_br2;	wk = tmp_bk;
								
								systemmsg.setText("CANNOT MOVE!\nThe King will be attacked!\nTry another move.");
								systemmsg.setForeground(Color.orange);
								updateMovableForAllPieces();
								setClickable(true);
							}
							else {
								setClickable(false);
								
								commandType = -1;
								turn++;
							}
						}
					}
				});
			}
		}
	}
	
	void enterSpecialCommand(int type) {
		
		updateAllPanels();
		selectstate = false;
		setClickable(false);
		commandType = type;
		turn++;
	}
	
	boolean checkCheck(Piece king, char c) {

		return !notAttacked(king.pos_x, king.pos_y, c);
	}
	
	void setClickable(boolean[][] mov) {
		for (int i=7; i>=0; i--)
			for (int j=0; j<8; j++) {
				sq[i][j].clickable = mov[i][j];
				sq[i][j].showMovable(mov[i][j]);
			}
	}
	
	void updateAllPanels() {
		for (int i=7; i>=0; i--)
			for (int j=0; j<8; j++) {
				sq[i][j].updatePanel();
			}
	}
	
	void setClickable(boolean clk) {
		
		for (int i=7; i>=0; i--)
			for (int j=0; j<8; j++)
				sq[i][j].clickable = clk;
	}
	
	public boolean isFinish() {
		
		return this.gameEnds;
	}
	
	// Move a piece from (x1, y1) to (x2, y2), assuming the move is valid.
	void movepiece(int x1, int y1, int x2, int y2, char pref) {
		
		// If you are moving King...
		if(sq[x1][y1].piece.type=='K'){
			
			// If King moves once... set bk/wk to false not to allow Castling later
			if (sq[x1][y1].piece.color=='b') {this.bk = false;}
			else {this.wk = false;}
			
			// Also move the Rook if Castling
			if (x1 == 0 && y1 == 4 && x2 == 0 && y2 == 2){
				sq[0][2].piece = sq[0][4].piece;
				sq[0][4].piece = null;
				sq[0][3].piece = sq[0][0].piece;
				sq[0][0].piece = null;
			}
			else if (x1 == 0 && y1 == 4 && x2 == 0 && y2 == 6){
				sq[0][6].piece = sq[0][4].piece;
				sq[0][4].piece = null;
				sq[0][5].piece = sq[0][7].piece;
				sq[0][7].piece = null;
			}
			else if (x1 == 7 && y1 == 4 && x2 == 7 && y2 == 2){
				sq[7][2].piece = sq[7][4].piece;
				sq[7][4].piece = null;
				sq[7][3].piece = sq[7][0].piece;
				sq[7][0].piece = null;
			}
			else if (x1 == 7 && y1 == 4 && x2 == 7 && y2 == 6){
				sq[7][6].piece = sq[7][4].piece;
				sq[7][4].piece = null;
				sq[7][5].piece = sq[7][7].piece;
				sq[7][7].piece = null;
			}
			else
			{
				sq[x2][y2].piece = sq[x1][y1].piece;
				sq[x1][y1].piece = null;
			}
		}
		
		// If you are moving Rook...
		else if (sq[x1][y1].piece.type=='R'){
			
			// If Rook moves once... set bk/wk to false not to allow Castling later
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
		
		// If you are moving Pawn...
		else if(sq[x1][y1].piece.type=='P'){
			
			// If it moves two squares, make a fake Pawn for En Passant
			if (Math.abs(x1 - x2) == 2){
				if (x1 < x2){
					sq[x1+1][y1].piece = new Pawn(x1+1, y1, sq[x1][y1].piece.color);
					sq[x1+1][y1].piece.type = 'F'; // F: Fake Pawn
				}
				else{
					sq[x1-1][y1].piece = new Pawn(x1-1, y1, sq[x1][y1].piece.color);
					sq[x1-1][y1].piece.type = 'F';
				}
			}
			
			// If it catches a fake Pawn, also remove a real Pawn corresponding
			if (sq[x2][y2].piece != null && sq[x2][y2].piece.type =='F')
			{
				if (sq[x2][y2].piece.color == 'b') sq[x2-1][y2].piece = null;
				else if (sq[x2][y2].piece.color == 'w') sq[x2+1][y2].piece = null;
			}
			
			sq[x2][y2].piece = sq[x1][y1].piece;
			sq[x1][y1].piece = null;
			
			// If it reaches the end of the board, promote it to the piece you prefer
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
		
		// Normal moves
		else{
			sq[x2][y2].piece = sq[x1][y1].piece;
			sq[x1][y1].piece = null;
		}
		
		for(int i=0; i<8; i++)
			for(int j=0; j<8; j++) {
				// Fake Pawn, which is made at last turn from the opponent, should be eliminated after the move
				if(sq[i][j].piece != null && sq[i][j].piece.type =='F' && sq[i][j].piece.color == (turn%2 == 0 ? 'b':'w'))
					sq[i][j].piece = null;	
			}
	}

	// whether the square (x, y) is being attacked by the opponent of the following color
	boolean notAttacked(int x, int y, char color) {
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
		
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				sq[i][j].updatePiecePosition();

		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				if (sq[i][j].piece != null) {
					sq[i][j].piece.findMovables(sq, bqc, bkc, wqc, wkc);
				}
			}
		}
		
		// Update whether the each following Castling is possible
		wqc = wk && wr1 && notAttacked(0, 2, 'w') && notAttacked(0, 3, 'w') && notAttacked(0, 4, 'w');
		wkc = wk && wr2 && notAttacked(0, 4, 'w') && notAttacked(0, 5, 'w') && notAttacked(0, 6, 'w');
		bqc = bk && br1 && notAttacked(7, 2, 'b') && notAttacked(7, 3, 'b') && notAttacked(7, 4, 'b');
		bkc = bk && br2 && notAttacked(7, 4, 'b') && notAttacked(7, 5, 'b') && notAttacked(7, 6, 'b');
	}
}
