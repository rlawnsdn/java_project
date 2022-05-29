import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.*;

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
	
	void updateEmo(boolean myself, int s) {
		
		if (s < 0) {
			if (myself) {
				ImageIcon icn = new ImageIcon("src/emo/" + (cboard.playsWhite ? 'w':'b') + "R.png");
				bframe.player1.emoidx = s;
				bframe.player1.emoimg.setIcon(icn);
			}
			else {
				ImageIcon icn = new ImageIcon("src/emo/" + (cboard.playsWhite ? 'b':'w') + "L.png");
				bframe.player2.emoidx = s;
				bframe.player2.emoimg.setIcon(icn);
			}
		}
		else {
			if (myself) {
				ImageIcon icn = new ImageIcon("src/emo/" + s + "R.png");
				bframe.player1.emoidx = s;
				bframe.player1.emoimg.setIcon(icn);
			}
			else {
				ImageIcon icn = new ImageIcon("src/emo/" + s + "L.png");
				bframe.player2.emoidx = s;
				bframe.player2.emoimg.setIcon(icn);
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
	
	JTextPane systemmsg;
	JPanel optionbuttons1; 	// Suggest DRAW, SURRENDER
	JPanel optionbuttons2; 	// Accept or Reject the DRAW suggestion
	
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
		
		JPanel sys = new JPanel();
		sys.setBounds(0, 400, 256, 128);
		sys.setAlignmentY(CENTER_ALIGNMENT);
		sys.setBackground(Color.black);
		
		systemmsg = new JTextPane();
		systemmsg.setSize(220, 100);
		systemmsg.setOpaque(true);
		systemmsg.setBackground(Color.black);
		systemmsg.setForeground(Color.yellow);
		systemmsg.setFont(new Font("Serif", Font.PLAIN, 18));
		
		StyledDocument doc = systemmsg.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		systemmsg.setText("Waiting for\nAnother Player");
		
		sys.add(systemmsg);
		c.add(sys);
		
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
		
		optionbuttons1 = new JPanel();
		optionbuttons2 = new JPanel();
		optionbuttons1.setBounds(0, 540, 256, 176);
		optionbuttons2.setBounds(0, 540, 256, 176);
		optionbuttons1.setLayout(null);
		optionbuttons2.setLayout(null);
		
		for (int i=0; i<4; i++)
		{
			JButton btn = new JButton();
			btn.setBounds(20, 20 + 72*(i%2), 216, 64);
			//btn.setContentAreaFilled(false);
			btn.addActionListener(new IntClick(i) {
				@Override
				public void actionPerformed (ActionEvent e) {
					cb.enterSpecialCommand(i);
				}
			});
			
			String str = "";
			switch (i) {
			case 0: str = "Suggest DRAW";	btn.setBackground(Color.getHSBColor(96/360f, 0.44f, 1f));	break;
			case 1: str = "SURRENDER";		btn.setBackground(Color.getHSBColor(350/360f, 0.60f, 1f));	break;
			case 2: str = "Accept";			btn.setBackground(Color.getHSBColor(160/360f, 0.60f, 1f));	break;
			case 3: str = "Reject";			btn.setBackground(Color.getHSBColor(0, 0, 0.64f));	break;
			}
			JLabel lbl = new JLabel(str);
			lbl.setForeground(Color.black);
			lbl.setFont(new Font("Serif", Font.PLAIN, 18));
			btn.add(lbl);	
			
			if (i/2 == 0)
				optionbuttons1.add(btn);
			else
				optionbuttons2.add(btn);
		}
		
		c.add(optionbuttons1);
		c.add(optionbuttons2);
		
		repaint();
		setVisible(true);
		
		cb.systemmsg = this.systemmsg;
	}
	
	void setSystemMsg(String s, Color c) {
		systemmsg.setText(s);
		systemmsg.setForeground(c);
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

class PlayerInfo extends JPanel {

	boolean myself;
	JLabel emoimg;
	int emoidx;
	
	PlayerInfo(boolean myself) {
		
		this.myself = true;
		
		if (myself) setBounds(1024, 384, 256, 384);
		else setBounds(0, 0, 256, 384);
		setLayout(null);
		setBackground(Color.getHSBColor(32/255f, 0, 0.7f));
		
		ImageIcon icn = new ImageIcon("src/emo/p" + (myself ? 'R' : 'L') + ".png");
		emoimg = new JLabel(icn);
		emoimg.setBounds(20, 20, 216, 216);
		emoimg.setAlignmentX(CENTER_ALIGNMENT);
		emoimg.setAlignmentY(CENTER_ALIGNMENT);
		
		this.add(emoimg);
		this.emoidx = -1;
	}
	
	void updatePlayerInfo(boolean myself, char color) {
		
		this.myself = true;
		
		if (myself) setBounds(1024, 384, 256, 384);
		else setBounds(0, 0, 256, 384);
		setLayout(null);
		setBackground(Color.getHSBColor(32/255f, 0, 0.7f));
		
		ImageIcon icn = new ImageIcon("src/emo/" + color + (myself ? 'R' : 'L') + ".png");
		emoimg.setIcon(icn);
		emoimg.setBounds(20, 20, 216, 216);
		emoimg.setAlignmentX(CENTER_ALIGNMENT);
		emoimg.setAlignmentY(CENTER_ALIGNMENT);
	}
}

class Emotion extends JPanel {

	PlayerInfo pInfo;
	
	Emotion(PlayerInfo p) {
		
		this.pInfo = p;
		
		setBounds(1024, 200, 256, 184);
		setLayout(null);
		setBackground(Color.getHSBColor(32/255f, 0, 0.5f));
		
		JPanel emos = new JPanel();
		emos.setBounds(20, 20, 216, 144);
		emos.setLayout(null);
		
		for (int i=0; i<6; i++)
		{
			JButton btn = new JButton();
			btn.setBounds(72*(i%3), 72*(i/3), 72, 72);
			btn.setContentAreaFilled(false);
			btn.addActionListener(new IntClick(i) {
				@Override
				public void actionPerformed (ActionEvent e) {
					ImageIcon icn = new ImageIcon("src/emo/" + i + "R.png");
					pInfo.emoimg.setIcon(icn);
					pInfo.emoidx = i;
				}
			});
			emos.add(btn);
			
			ImageIcon icn = new ImageIcon("src/emo/x" + i + ".png");
			JLabel lbl = new JLabel(icn);
			lbl.setBounds(72*(i%3), 72*(i/3), 72, 72);
			emos.add(lbl);			

		}
		
		this.add(emos);
	}
}

class IntClick implements ActionListener{
	
    final int i;
    
    IntClick(int i){
        this.i = i;
    }
    
	public void actionPerformed (ActionEvent e) {

	}
}

class Promotion extends JPanel {

	ChessBoard chessBoard;
	JLabel[] lbl;
	
	Promotion(ChessBoard cb) {
		
		this.chessBoard = cb;
		lbl = new JLabel[4];
		
		setBounds(1024, 56, 256, 144);
		setLayout(null);
		setBackground(Color.getHSBColor(32/255f, 0, 0.3f));
		
		JPanel promos = new JPanel();
		promos.setBounds(20, 20, 216, 104);
		promos.setLayout(null);
		
		char[] pchar = {'Q', 'N', 'B', 'R'};
		for (int i=0; i<4; i++)
		{
			JButton btn = new JButton();
			btn.setBounds(108*(i%2), 54*(i/2), 108, 54);
			btn.setContentAreaFilled(false);
			btn.addActionListener(new PromoClick(i, pchar[i]) {
				@Override
				public void actionPerformed (ActionEvent e) {
					chessBoard.preferredPromotion = p;
					updatePromoGUI(i, (cb.playsWhite ? 'w':'b'));
				}
			});
			promos.add(btn);
			
			ImageIcon icn = new ImageIcon("src/prmt/" + (cb.playsWhite ? 'w':'b') + pchar[i]
					+ (cb.preferredPromotion == pchar[i] ? "_c.png" : "_n.png"));
			lbl[i] = new JLabel(icn);
			lbl[i].setBounds(108*(i%2), 54*(i/2), 108, 54);
			promos.add(lbl[i]);			

		}
		
		this.add(promos);
	}
	
	void updatePromoGUI(int idx, char c) {
		char[] pchar = {'Q', 'N', 'B', 'R'};
		for (int i=0; i<4; i++) {
			lbl[i].setIcon(new ImageIcon("src/prmt/" + c + pchar[i] + (i==idx ? "_c.png" : "_n.png")));
		}
	}
}

class PromoClick implements ActionListener{
	
	final int i;
    final char p;
    
    PromoClick(int i, char p){
    	this.i = i;
        this.p = p;
    }
    
	public void actionPerformed (ActionEvent e) {

	}
}