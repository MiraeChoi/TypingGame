package typing2_Login;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginFrame extends JFrame{
	
	//�α��� ȭ��
	JPanel jp, jpl, jp1, jp2;
	JTextField jt1, jt2;
	JButton jb, jb2;
	JLabel jl0, jl1, jl2;
	private Logdate Id = new Logdate();
	private Logdate Password = new Logdate();
	File fl;
	
	Frame f= new Frame();

	public LoginFrame() {	
		setTitle("�α���");
		setSize(300, 180);
		setLocationRelativeTo(null);
		
		jp = new JPanel(); 
		jpl = new JPanel(); 
		jp1 = new JPanel(); 
		jp2 = new JPanel(); 
		jl0 = new JLabel("Ÿ�ڿ��� �α���"); 
		jt1 = new JTextField(10); 
		jt2 = new JTextField(10); 
		jl1 = new JLabel("ID "); 
		jl2 = new JLabel("PW "); 
		jb = new JButton("�α���"); 
		jb2 = new JButton("ȸ������"); 
		jpl.add(jl0); 
		jp.add(jl1); 
		jp.add(jt1); 
		jp1.add(jl2); 
		jp1.add(jt2); 
		jp2.add(jb); 
		jp2.add(jb2);
		
		setLayout(new GridLayout(4,1)); 
		add(jpl); 
		add(jp);
		add(jp1); 
		add(jp2); 
		setEnabled(true); 
		setResizable(true); 
		setVisible(true); 

		/*jt1.addTextListener(new TextListener() {
			@Override
			public void textValueChanged(TextEvent e) {
				if(jt1.getText().equals("")) {
					jt1.setEnabled(false);
				}
				jt2.setEnabled(true);
			}
		});*/
		
		//�α��� ��ư�� �̺�Ʈ ó��
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println(jt1.getText());
				Id.setId(jt1.getText());
				Password.setPassword(jt2.getText());
				
				jt1.setText("");
				jt2.setText("");
				
				File fl = new File("C:\\TypingGame\\Info\\" + Id.getId() + ".txt");
				FileReader fr = null;
				BufferedReader br = null;
				
				try {
					fr = new FileReader(fl);
					br = new BufferedReader(fr);
					
					String getId_date = "";
					String[] arr = null;
					
					while((getId_date = br.readLine()) != null) {
						arr = getId_date.split("/");
						//�α��� ����
						if(arr[1].equals(Id.getId()) && arr[2].equals(Password.getPassword())) {
							JOptionPane.showMessageDialog(f, "�α��� ����");
							
							//���� ���� ȭ���� �� â���� ���
							SelectGame sg = new SelectGame();
							String id = Id.getId();
							sg.gameSelect(id);
							
							//�α��� ȭ�� ����
							dispose();
							break;
						} else {
							JOptionPane.showMessageDialog(f, "��й�ȣ�� �ٽ� Ȯ���ϼ���.");
							break;
						}
					}//while
				} catch (FileNotFoundException e2) {
					//�ش� ���� ���̵�� ������� ������ ����
					JOptionPane.showMessageDialog(f, "���̵� �ٽ� Ȯ���ϼ���.");
				} catch(Exception e3) {
					e3.printStackTrace();
				} finally {
					try {
						if(fr != null) {
							br.close();
							fr.close();	
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}//try
			}
		});
		
		//ȸ������ ��ư�� �̺�Ʈ ó�� : ȸ������ â�� ��
		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				New_date nd = new New_date();
			}
		});
	}//������
	
} 

