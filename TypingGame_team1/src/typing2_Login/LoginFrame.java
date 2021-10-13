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
	
	//로그인 화면
	JPanel jp, jpl, jp1, jp2;
	JTextField jt1, jt2;
	JButton jb, jb2;
	JLabel jl0, jl1, jl2;
	private Logdate Id = new Logdate();
	private Logdate Password = new Logdate();
	File fl;
	
	Frame f= new Frame();

	public LoginFrame() {	
		setTitle("로그인");
		setSize(300, 180);
		setLocationRelativeTo(null);
		
		jp = new JPanel(); 
		jpl = new JPanel(); 
		jp1 = new JPanel(); 
		jp2 = new JPanel(); 
		jl0 = new JLabel("타자연습 로그인"); 
		jt1 = new JTextField(10); 
		jt2 = new JTextField(10); 
		jl1 = new JLabel("ID "); 
		jl2 = new JLabel("PW "); 
		jb = new JButton("로그인"); 
		jb2 = new JButton("회원가입"); 
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
		
		//로그인 버튼에 이벤트 처리
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
						//로그인 성공
						if(arr[1].equals(Id.getId()) && arr[2].equals(Password.getPassword())) {
							JOptionPane.showMessageDialog(f, "로그인 성공");
							
							//게임 선택 화면을 새 창으로 띄움
							SelectGame sg = new SelectGame();
							String id = Id.getId();
							sg.gameSelect(id);
							
							//로그인 화면 종료
							dispose();
							break;
						} else {
							JOptionPane.showMessageDialog(f, "비밀번호를 다시 확인하세요.");
							break;
						}
					}//while
				} catch (FileNotFoundException e2) {
					//해당 유저 아이디로 만들어진 폴더가 없음
					JOptionPane.showMessageDialog(f, "아이디를 다시 확인하세요.");
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
		
		//회원가입 버튼에 이벤트 처리 : 회원가입 창이 뜸
		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				New_date nd = new New_date();
			}
		});
	}//생성자
	
} 

