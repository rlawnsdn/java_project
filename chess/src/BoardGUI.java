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
	
	BoardGUI(ChessBoard cb) {
		bframe = new BoardFrame(cb.sq);
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
	
	BoardFrame(Square[][] sq) {
		setTitle("Chess");
		
		JPanel player1 = new JPanel();
		JPanel player2 = new JPanel();
		JPanel chessboard = new JPanel();
		
		player1.setBounds(1024, 384, 256, 384);
		player1.setAlignmentY(BOTTOM_ALIGNMENT);
		player1.setBackground(Color.getHSBColor(32/255f, 0, 0.7f));
		player2.setBounds(0, 0, 256, 384);
		player2.setAlignmentY(TOP_ALIGNMENT);
		player2.setBackground(Color.getHSBColor(32/255f, 0, 0.7f));
		chessboard.setBounds(320, 64, 640, 640);
		chessboard.setAlignmentX(CENTER_ALIGNMENT);
		chessboard.setAlignmentY(CENTER_ALIGNMENT);
		chessboard.setLayout(new GridLayout(8,8));
		
		for (int i=7; i>=0; i--)
			for (int j=0; j<8; j++)
				chessboard.add(sq[i][j]);
		
		Container c = getContentPane();
		setResizable(false);

		c.setBackground(Color.getHSBColor(32/360f, 0.4f, 0.6f));
		c.setLayout(null);
		
		c.add(player2);
		c.add(chessboard);
		c.add(player1);
		
		setUndecorated(true); // SetSize가 의도대로 동작하지 않아 타이틀바 제거.
		pack();

		setSize(1280, 768);
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