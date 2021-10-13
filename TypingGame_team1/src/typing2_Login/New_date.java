package typing2_Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

import java.awt.*;
public class New_date extends JFrame {
	
	//회원가입 화면
	public New_date(){
		
		//프레임
		setSize(500, 500);
		setLocationRelativeTo(null);
		setTitle("회원가입");
		
		//회원가입 구성 요소
		JPanel p = new JPanel();
		Label l1 = new Label("이름");	
		Label l2 = new Label("아이디");
		Label l3 = new Label("패스워드");
		Label l5 = new Label("추가사항");
		add(l1);
		add(l2);
		add(l3);
		add(l5);
		TextField t1 = new TextField();
		TextField t2 = new TextField();
		TextField t3 = new TextField();
		TextField t5 = new TextField();
		add(t1);
		add(t2);
		add(t3);
		add(t5);
		JButton j1 = new JButton("저장");
		JButton j2 = new JButton("취소");
		add(j1);
		add(j2);
		l1.setBounds(40, 10, 40, 40);
		l2.setBounds(40, 50, 40, 40);
		l3.setBounds(40, 90, 60, 40);
		l5.setBounds(40, 170, 60, 40);
		t1.setBounds(120, 10, 200, 30);
		t2.setBounds(120, 50, 200, 30);
		t3.setBounds(120, 90, 200, 30);
		t5.setBounds(120, 180, 280, 120);
		j1.setBounds(125, 330, 80, 30);
		j2.setBounds(240, 330, 80, 30); 
		
		add(p);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		setVisible(true);
		
		//저장 버튼에 이벤트 처리
		j1.addActionListener(new ActionListener() {
			FileWriter fw = null;
			BufferedWriter bw = null;

			@Override
			public void actionPerformed(ActionEvent T) {
				File dir = new File("C:\\TypingGame\\Info\\");
				try{
					if(!dir.exists()) {
						//System.out.println("폴더를 생성합니다.");
						dir.mkdirs();		
					} else {
						//System.out.println("폴더가 이미 존재합니다.");
					}
					
					fw = new FileWriter("C:\\TypingGame\\Info\\" + t2.getText() + ".txt");
					bw = new BufferedWriter(fw);
					
					//bw.write("이름/아이디/비밀번호/추가사항\n");
					bw.write(t1.getText() + "/");
					bw.write(t2.getText() + "/");
					bw.write(t3.getText() + "/");
					bw.write(t5.getText() + "/");

					JOptionPane.showMessageDialog(null, "회원가입을 축하합니다!!");
					dispose();
				} catch (Exception e){
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "회원가입에 실패했습니다.");
				} finally {
					try {
						bw.close();
						fw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}//try
			}
		});

		//취소 버튼에 이벤트 처리			
		j2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

	}//생성자
}


