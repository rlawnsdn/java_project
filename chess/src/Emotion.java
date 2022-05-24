import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Emotion extends JPanel {

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
			//btn.setOpaque(false);
			btn.setContentAreaFilled(false);
			btn.addActionListener(new EmoClick(i) {
				@Override
				public void actionPerformed (ActionEvent e) {
					ImageIcon icn = new ImageIcon("src/emo/" + i + "R.png");
					pInfo.emoimg.setIcon(icn);
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

class EmoClick implements ActionListener{
	
    final int i;;
    
    EmoClick(int i){
        this.i = i;
    }
    
	public void actionPerformed (ActionEvent e) {

	}
}