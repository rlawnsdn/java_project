import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BoardGUI {

	BoardFrame bframe;
	ChessBoard cboard;
	
	BoardGUI() {
		bframe = new BoardFrame();
	}
	
	void startBoardGUI(ChessBoard cb, boolean white) {
		bframe.startBoardFrame(cb, white);
		cboard = cb;
	}
	
	void updateBoardGUI(Square[][] bsq) {
		
		this.cboard.sq = bsq;
		for (int i=7; i>=0; i--) {
			for (int j=0; j<8; j++) {
				this.cboard.sq[i][j].updatePanel();
				this.cboard.sq[i][j].updatePiecePosition();
			}
		}
	}
}

class BoardFrame extends JFrame {
	
	PlayerInfo player1;
	PlayerInfo player2;
	JPanel chessboard;
	
	Emotion emo;
	JPanel promotion;
	
	BoardFrame() {
		setTitle("Chess");
		
		player1 = new PlayerInfo(true);
		player2 = new PlayerInfo(false);
		
		Container c = getContentPane();
		setResizable(false);

		c.setBackground(Color.getHSBColor(32/360f, 0.4f, 0.6f));
		c.setLayout(null);
		
		c.add(player2);
		c.add(player1);
		
		emo = new Emotion(player1);
		c.add(emo);
		
		//setUndecorated(true); // SetSize가 의도대로 동작하지 않아 타이틀바 제거.
		pack();

		setSize(1296, 800); // 1280 * 768
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	void startBoardFrame(ChessBoard cb, boolean white) {
		setTitle("Chess");
		
		player1.updatePlayerInfo(true, white ? 'w':'b');
		player2.updatePlayerInfo(false, white ? 'b':'w');
		
		chessboard = new JPanel();
		
		chessboard.setBounds(320, 64, 640, 640);
		chessboard.setAlignmentX(CENTER_ALIGNMENT);
		chessboard.setAlignmentY(CENTER_ALIGNMENT);
		chessboard.setLayout(new GridLayout(8,8));
		
		for (int i=7; i>=0; i--)
			for (int j=0; j<8; j++)
				chessboard.add(white ? cb.sq[i][j] : cb.sq[7-i][7-j]);

		Container c = getContentPane();
		setResizable(false);
		
		c.add(chessboard);
		
		promotion = new Promotion(cb);
		c.add(promotion);
		
		repaint();
		setVisible(true);
	}
}

class SquareClick implements ActionListener{
	
    final int i, j;
    final Square s;
    
    SquareClick(int i, int j, Square s){
        this.i = i;
        this.j = j;
        this.s = s;
    }
    
	public void actionPerformed (ActionEvent e) {

	}
}