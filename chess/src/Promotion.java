import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Promotion extends JPanel {

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
			//ImageIcon icn = new ImageIcon("prmt/" + (cb.playsWhite ? 'w':'b') + pchar[i]
			//+ (cb.preferredPromotion == pchar[i] ? "_c.png" : "_n.png"));
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