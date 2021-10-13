package typing1_Main;

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
import javax.swing.JOptionPane;

import typing2_Login.FramesMain;

public class GameMain {
	public static void main(String[] args) {

		//프레임
		Frame f = new Frame("타자게임");
		f.setSize(1200, 900);
		f.setLocationRelativeTo(null); //프레임이 모니터의 중앙에 위치하게 됨
		f.setLayout(null);

		//타이틀 및 메뉴 폰트 설정
		Font font_50b = new Font("맑은 고딕", Font.BOLD, 50);
		Font font_40b = new Font("맑은 고딕", Font.BOLD, 40);

		//배경 이미지 삽입
		ImageIcon img = new ImageIcon("gray_bg.jpg");
		JLabel bgImg = new JLabel(img);
		bgImg.setBounds(0, 0, 1920, 1050);

		//타이틀 라벨
		JLabel title = new JLabel("Typing Game");
		title.setBounds(450, 100, 500, 80);
		title.setForeground(Color.DARK_GRAY);
		title.setFont(font_50b);

		//버튼 추가
		JButton btnStart = new JButton("시작");
		JButton btnRank = new JButton("랭킹");
		JButton btnExit = new JButton("종료");

		btnStart.setBounds(460, 250, 300, 150);
		btnRank.setBounds(460, 430, 300, 150);
		btnExit.setBounds(460, 610, 300, 150);

		btnStart.setFont(font_40b);
		btnRank.setFont(font_40b);
		btnExit.setFont(font_40b);

		//ButtonEffect 메서드의 버튼 설정 추가
		new ButtonEffect(btnStart);
		new ButtonEffect(btnRank);
		new ButtonEffect(btnExit);
		
		//시작 버튼에 이벤트 처리 : 로그인 화면을 새 창으로 띄움
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FramesMain fm = new FramesMain();
			}
		});

		//랭킹 버튼에 이벤트 처리 : 랭커 목록을 보여줌
		btnRank.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Ranking rank = new Ranking();
				rank.ranking();
			}
		});

		//종료 버튼에 이벤트 처리 : 팝업창을 한 번 더 띄운 뒤 사용자의 의사에 따라 종료
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int exitOption = JOptionPane.showConfirmDialog
					(null, "게임을 종료하시겠습니까?", "프로그램 종료", JOptionPane.YES_NO_OPTION);
				
				if(exitOption == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else {
					return;
				}
			}
		});

		//요소들 프레임에 추가
		f.add(title);

		f.add(btnStart);
		f.add(btnRank);
		f.add(btnExit);

		f.add(bgImg);

		//프레임 닫기 버튼에 이벤트 주고 실행
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int exitOption = JOptionPane.showConfirmDialog
					(null, "게임을 종료하시겠습니까?", "프로그램 종료", JOptionPane.YES_NO_OPTION);
				
				if(exitOption == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else {
					return;
				}
			}
		});

		f.setVisible(true);

	}//main
}
