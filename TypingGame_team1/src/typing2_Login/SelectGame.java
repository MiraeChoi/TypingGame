package typing2_Login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import typing1_Main.ButtonEffect;
import typing3_GameSentence.SentenceView;
import typing4_GameRain.RainView;

public class SelectGame {

	private String userId;
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}

	public void gameSelect(String id) {
		userId = id;
		System.out.println("접속한 사용자 : " + id);
		
		//프레임
		Frame f = new Frame("타자게임");
		f.setSize(1200, 900);
		f.setLocationRelativeTo(null); //프레임이 모니터의 중앙에 위치하게 됨
		f.setLayout(null);

		//타이틀 및 메뉴 폰트 설정
		Font font_50b = new Font("맑은 고딕", Font.BOLD, 50);
		Font font_40b = new Font("맑은 고딕", Font.BOLD, 40);
		Font font_20b = new Font("맑은 고딕", Font.BOLD, 20);

		//배경 이미지 삽입
		ImageIcon img = new ImageIcon("gray_bg.jpg");
		JLabel bgImg = new JLabel(img);
		bgImg.setBounds(0, 0, 1920, 1050);

		//타이틀 라벨
		JLabel title = new JLabel("게임 선택");
		title.setBounds(470, 100, 500, 80);
		title.setForeground(Color.DARK_GRAY);
		title.setFont(font_50b);

		f.add(title);

		//버튼 추가(문장 연습, 산성비, 홈으로)
		JButton btnSentence = new JButton("문장 연습");
		JButton btnRain = new JButton("산성비");
		JButton btnHome = new JButton("홈으로");

		btnSentence.setBounds(400, 300, 350, 150);
		btnRain.setBounds(400, 500, 350, 150);
		btnHome.setBounds(1050, 800, 100, 50);

		btnSentence.setFont(font_40b);
		btnRain.setFont(font_40b);
		btnHome.setFont(font_20b);

		ButtonEffect btnEffect;
		new ButtonEffect(btnSentence);
		new ButtonEffect(btnRain);
		btnHome.setBackground(new Color(0, 0, 0, 0));
		btnHome.setBorderPainted(false);

		//문장연습 버튼에 이벤트 처리
		btnSentence.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SentenceView sv = new SentenceView();
				sv.gameSentenceView(id);
			}
		});
		
		//산성비 버튼에 이벤트 처리
		btnRain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RainView rv = new RainView();
				rv.gameRainView(id);
			}
		});
		
		//홈으로 버튼에 이벤트 처리
		btnHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});

		f.add(btnSentence);
		f.add(btnRain);
		f.add(btnHome);

		f.add(bgImg);

		//프레임 닫기 버튼에 이벤트 주고 실행
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				f.dispose();
			}
		});

		f.setVisible(true);
	}

}
