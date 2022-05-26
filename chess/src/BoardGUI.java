import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BoardGUI {

	BoardFrame bframe;
	ChessBoard cboard;
	
	BoardGUI(ChessBoard cb, boolean white) {
		bframe = new BoardFrame(cb.sq, white);
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
	
	BoardFrame(Square[][] sq, boolean white) {
		setTitle("Chess");
		
		PlayerInfo player1 = new PlayerInfo(true, white ? 'w':'b');
		PlayerInfo player2 = new PlayerInfo(false, white ? 'b':'w');
		JPanel chessboard = new JPanel();
		
		chessboard.setBounds(320, 64, 640, 640);
		chessboard.setAlignmentX(CENTER_ALIGNMENT);
		chessboard.setAlignmentY(CENTER_ALIGNMENT);
		chessboard.setLayout(new GridLayout(8,8));
		
		for (int i=7; i>=0; i--)
			for (int j=0; j<8; j++)
				chessboard.add(white ? sq[i][j] : sq[7-i][7-j]);
		
		Container c = getContentPane();
		setResizable(false);

		c.setBackground(Color.getHSBColor(32/360f, 0.4f, 0.6f));
		c.setLayout(null);
		
		c.add(player2);
		c.add(chessboard);
		c.add(player1);
		
		Emotion emo = new Emotion(player1);
		c.add(emo);
		
		//setUndecorated(true); // SetSize가 의도대로 동작하지 않아 타이틀바 제거.
		pack();

		setSize(1280, 800);
		setLocationRelativeTo(null);
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