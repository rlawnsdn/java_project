import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerInfo extends JPanel {

	boolean myself;
	JLabel emoimg;
	
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
