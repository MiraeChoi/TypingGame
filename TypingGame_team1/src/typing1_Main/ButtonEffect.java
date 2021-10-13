package typing1_Main;

import java.awt.Color;

import javax.swing.JButton;

public class ButtonEffect extends JButton{
	
	public ButtonEffect(JButton btn) {
		btn.setBackground(new Color(0, 0, 0, 40));
		btn.setBorderPainted(false);
		btn.setForeground(Color.WHITE);
	}
	
}
