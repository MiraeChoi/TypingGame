package typing2_Login;

import javax.swing.JFrame;

public class FramesMain extends JFrame{

	//�α��� ȭ��
	static LoginFrame lf= null; 
	public FramesMain() { 
		lf = new LoginFrame(); 
	} 
	public static void main(String[] args) { 
		new FramesMain(); 
	} 
} 

