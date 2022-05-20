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
	Square[][] sq;
	
	BoardGUI(Square[][] bsq) {
		bframe = new BoardFrame(bsq);
	}
	
	void updateBoardGUI(Square[][] bsq) {
		
		this.sq = bsq;
		for (int i=7; i>=0; i--) {
			for (int j=0; j<8; j++) {
				bframe.bpanel[i][j].updatePanel(bsq[i][j]);
			}
		}
	}
}

class BoardFrame extends JFrame {
	
	BoardPanel[][] bpanel;
	
	BoardFrame(Square[][] sq) {
		setTitle("Chess");
		
		JPanel player1 = new JPanel();
		JPanel player2 = new JPanel();
		JPanel chessboard = new JPanel();
		
		player1.setBounds(1024, 384, 256, 384);
		player1.setAlignmentY(BOTTOM_ALIGNMENT);
		player1.setBackground(Color.getHSBColor(38/255f, 84/255f, 83/255f));
		player2.setBounds(0, 0, 256, 384);
		player2.setAlignmentY(TOP_ALIGNMENT);
		player2.setBackground(Color.getHSBColor(38/255f, 84/255f, 83/255f));
		chessboard.setBounds(320, 64, 640, 640);
		chessboard.setAlignmentX(CENTER_ALIGNMENT);
		chessboard.setAlignmentY(CENTER_ALIGNMENT);
		chessboard.setBackground(Color.getHSBColor(37/255f, 36/255f, 52/255f));
		chessboard.setLayout(new GridLayout(8,8));
		
		bpanel = new BoardPanel[8][8];
		for (int i=7; i>=0; i--)
			for (int j=0; j<8; j++) {
				bpanel[i][j] = new BoardPanel(i, j, sq[i][j]);
				chessboard.add(bpanel[i][j]);
			}

		
		Container c = getContentPane();
		setResizable(false);

		c.setBackground(Color.getHSBColor(37/255f, 0/255f, 82/255f));
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
	
	class ButtonClickListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {

		}
	}
}

class BoardPanel extends JPanel {
	
	int x, y;
	Square sq;
	JLabel pieceimg;
	
	BoardPanel(int x, int y, Square sq) {
		
		this.x = x;
		this.y = y;
		this.sq = sq;
		
		setSize(80, 80);
		setLayout(new GridLayout(1,1));
		setBackground(sq.color == 'b' ? Color.getHSBColor(0/255f, 0/255f, 0/255f) : Color.getHSBColor(0/255f, 0/255f, 255/255f));
		
		ImageIcon icn;
		if (sq.piece != null && sq.piece.type != 'F')
		{
			icn = new ImageIcon("src/img/" + sq.piece.color + sq.piece.type + "_n.png");
			pieceimg = new JLabel(icn);
		}
		else
		{
			icn = new ImageIcon("src/img/none.png");
			pieceimg = new JLabel(icn);
		}
		
		pieceimg.setPreferredSize(new Dimension(80, 80));
		pieceimg.setAlignmentX(CENTER_ALIGNMENT);
		pieceimg.setAlignmentY(CENTER_ALIGNMENT);
		this.add(pieceimg);
	}
	
	void updatePanel(Square sq)
	{
		ImageIcon icn;
		if (sq.piece != null && sq.piece.type != 'F')
		{
			icn = new ImageIcon("src/img/" + sq.piece.color + sq.piece.type + "_n.png");
			pieceimg.setIcon(icn);
		}
		else
		{
			icn = new ImageIcon("src/img/none.png");
			pieceimg.setIcon(icn);
		}
	}
}