package typing1_Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import typing3_GameSentence.UserInfo_S;
import typing4_GameRain.UserInfo_R;

public class Ranking {
	
	public void ranking() {
		//프레임
		Frame f = new Frame("타자게임");
		f.setSize(1200, 900);
		f.setLocationRelativeTo(null); //프레임이 모니터의 중앙에 위치하게 됨
		f.setLayout(null);

		//타이틀 및 메뉴 폰트 설정
		Font font_50b = new Font("맑은 고딕", Font.BOLD, 50);
		Font font_20b = new Font("맑은 고딕", Font.BOLD, 20);
		Font font_18p = new Font("맑은 고딕", Font.BOLD, 18);

		//배경 이미지 삽입
		ImageIcon img = new ImageIcon("gray_bg.jpg");
		JLabel bgImg = new JLabel(img);
		bgImg.setBounds(0, 0, 1920, 1050);

		//타이틀 라벨
		JLabel title = new JLabel("랭킹");
		title.setBounds(550, 100, 500, 80);
		title.setForeground(Color.DARK_GRAY);
		title.setFont(font_50b);

		f.add(title);

		//---------------------------------------------------------
		//버튼 추가(문장 연습, 산성비)
		JButton btnSentence = new JButton("문장 연습");
		JButton btnRain = new JButton("산성비");

		btnSentence.setBounds(150, 200, 300, 70);
		btnRain.setBounds(750, 200, 300, 70);

		btnSentence.setFont(font_20b);
		btnRain.setFont(font_20b);
		
		new ButtonEffect(btnSentence);
		new ButtonEffect(btnRain);

		//짧은 글 연습 - 버튼 추가(순위, 아이디, 기록, 날짜)
		JButton btnRank = new JButton("순위");
		JButton btnName = new JButton("아이디");
		JButton btnScore = new JButton("기록");
		JButton btnDate = new JButton("날짜");

		btnRank.setBounds(80, 300, 100, 60);
		btnName.setBounds(190, 300, 100, 60);
		btnScore.setBounds(300, 300, 100, 60);
		btnDate.setBounds(410, 300, 100, 60);

		btnRank.setFont(font_20b);
		btnName.setFont(font_20b);
		btnScore.setFont(font_20b);
		btnDate.setFont(font_20b);

		//ButtonEffect 메서드의 버튼 설정 추가
		new ButtonEffect(btnRank);
		new ButtonEffect(btnName);
		new ButtonEffect(btnScore);
		new ButtonEffect(btnDate);

		//문장 입력 랭커가 화면에 보여지는 라벨----------------------------------------------------------
		JLabel rankS, idS, scoreS, dateS;

		UserInfo_S uis;
		ArrayList<UserInfo_S> sArr = new ArrayList<>();
		ArrayList<Integer> tempS = new ArrayList<>();
		//유저의 순위를 정할 배열
		int[] orderS = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		//유저의 순위를 순서대로 나타낼 배열
		int[] indexS = new int[orderS.length];

		FileReader frS = null;
		BufferedReader brS = null;

		try {
			File file = new File("c:\\TypingGame\\Sentence\\");
			String[] names = file.list();
			String[] arr = null;
			
			if(names == null) {
				JLabel nothing = new JLabel();
				nothing.setBounds(100, 450, 430, 80);
				nothing.setText("아직 기록이 없습니다.");
				nothing.setHorizontalAlignment(JLabel.CENTER);
				nothing.setFont(font_20b);
				f.add(nothing);
			} else {
				for(int i = 0; i < names.length; i++) {
					uis = new UserInfo_S();

					frS = new FileReader("c:\\TypingGame\\Sentence\\" + names[i]);
					brS = new BufferedReader(frS);

					String getUser = "";

					while((getUser = brS.readLine()) != null) {
						arr = getUser.split("/");

						uis.setId(arr[0]);
						uis.setScore(Integer.parseInt(arr[1]));
						uis.setDate(arr[2]);

						sArr.add(uis);
					}//while
				}//for	
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(brS != null) {
				try {
					brS.close();
					frS.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}//try
		
		System.out.println("---------------RankerArray_S---------------");
		for(int i = 0; i < sArr.size(); i++) {
			System.out.print("ID : " + sArr.get(i).getId() + " / ");
			System.out.print("SCORE : " + sArr.get(i).getScore() + " / ");
			System.out.println("DATE : " + sArr.get(i).getDate());
		}//for
		System.out.println("-------------------------------------------");

		for(int i = 0; i < sArr.size(); i++) {
			tempS.add(sArr.get(i).getScore());
		}//for

		//유저 간 기록 비교, 더 적은 개수를 입력한 사람이 본인 인덱스의 랭킹에서 1씩 추가됨(ex : 1위 -> 2위)
		for(int i = 0; i < tempS.size(); i++) {
			for(int j = 0; j < tempS.size(); j++) {
				if(tempS.get(j) > tempS.get(i)) {
					orderS[i]++;
				}
			}//inner
		}//outer

		//1등부터 차례대로 순서 정렬
		for(int i = 0; i < tempS.size(); i++) {
			indexS[orderS[i] - 1] = i;
		}//for

		//랭킹이 기록될 라벨 간 여백
		int spacing = 40;

		//라벨 정렬
		for(int i = 0; i < sArr.size() && i < 5; i++) {
			int r = indexS[i];

			rankS = new JLabel();
			idS = new JLabel();
			scoreS = new JLabel();
			dateS = new JLabel();

			rankS.setBounds(80, (370 + spacing * i), 100, 60);
			idS.setBounds(190, (370 + spacing * i), 100, 60);
			scoreS.setBounds(300, (370 + spacing * i), 100, 60);
			dateS.setBounds(410, (370 + spacing * i), 100, 60);

			rankS.setText(orderS[r] + "위");
			idS.setText(sArr.get(r).getId());
			scoreS.setText(sArr.get(r).getScore() + "개");
			dateS.setText(sArr.get(r).getDate());

			rankS.setFont(font_18p);
			idS.setFont(font_18p);
			scoreS.setFont(font_18p);
			dateS.setFont(font_18p);

			rankS.setHorizontalAlignment(JLabel.CENTER);
			idS.setHorizontalAlignment(JLabel.CENTER);
			scoreS.setHorizontalAlignment(JLabel.CENTER);
			dateS.setHorizontalAlignment(JLabel.CENTER);

			f.add(rankS);
			f.add(idS);
			f.add(scoreS);
			f.add(dateS);
		}//for
		//문장 입력 모드 라벨 끝----------------------------------------------------------

		//산성비 - 버튼 추가(순위, 아이디, 기록, 날짜)
		JButton btnRank2 = new JButton("순위");
		JButton btnName2 = new JButton("아이디");
		JButton btnScore2 = new JButton("기록");
		JButton btnDate2 = new JButton("날짜");

		btnRank2.setBounds(670, 300, 100, 60);
		btnName2.setBounds(780, 300, 100, 60);
		btnScore2.setBounds(900, 300, 100, 60);
		btnDate2.setBounds(1010, 300, 100, 60);

		btnRank2.setFont(font_20b);
		btnName2.setFont(font_20b);
		btnScore2.setFont(font_20b);
		btnDate2.setFont(font_20b);

		//ButtonEffect 메서드의 버튼 설정 추가
		new ButtonEffect(btnRank2);
		new ButtonEffect(btnName2);
		new ButtonEffect(btnScore2);
		new ButtonEffect(btnDate2);

		//산성비 모드 랭커가 화면에 보여지는 라벨----------------------------------------------------------
		//결과가 1명이라면 1명만 나오게, 3명이라면 3명 최대 5명까지 해 보자
		JLabel rankR, idR, scoreR, dateR;

		UserInfo_R uir;
		ArrayList<UserInfo_R> rArr = new ArrayList<>();
		ArrayList<Integer> tempR = new ArrayList<>();
		//유저의 순위를 정할 배열
		int[] orderR = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		//유저의 순위를 순서대로 나타낼 배열
		int[] indexR = new int[orderR.length];

		FileReader frR = null;
		BufferedReader brR = null;

		try {
			File file = new File("c:\\TypingGame\\Rain\\");
			String[] names = file.list();
			String[] arr = null;
			
			if(names == null) {
				JLabel nothing = new JLabel();
				nothing.setBounds(690, 450, 430, 80);
				nothing.setText("아직 기록이 없습니다.");
				nothing.setHorizontalAlignment(JLabel.CENTER);
				nothing.setFont(font_20b);
				f.add(nothing);
			} else {
				for(int i = 0; i < names.length; i++) {
					uir = new UserInfo_R();

					frR = new FileReader("c:\\TypingGame\\Rain\\" + names[i]);
					brR = new BufferedReader(frR);

					String getUser = "";

					while((getUser = brR.readLine()) != null) {
						arr = getUser.split("/");

						uir.setId(arr[0]);
						uir.setScore(Integer.parseInt(arr[1]));
						uir.setDate(arr[2]);

						rArr.add(uir);
					}//while
				}//for	
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(brR != null) {
				try {
					brR.close();
					frR.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}//try
		
		System.out.println("---------------RankerArray_R---------------");
		for(int i = 0; i < rArr.size(); i++) {
			System.out.print("ID : " + rArr.get(i).getId() + " / ");
			System.out.print("SCORE : " + rArr.get(i).getScore() + " / ");
			System.out.println("DATE : " + rArr.get(i).getDate());
		}//for
		System.out.println("-------------------------------------------");

		for(int i = 0; i < rArr.size(); i++) {
			tempR.add(rArr.get(i).getScore());
		}//for

		//유저 간 기록 비교, 더 적은 개수를 입력한 사람이 본인 인덱스의 랭킹에서 1씩 추가됨(ex : 1위 -> 2위)
		for(int i = 0; i < tempR.size(); i++) {
			for(int j = 0; j < tempR.size(); j++) {
				if(tempR.get(j) > tempR.get(i)) {
					orderR[i]++;
				}
			}//inner
		}//outer

		//1등부터 차례대로 순서 정렬
		for(int i = 0; i < tempR.size(); i++) {
			indexR[orderR[i] - 1] = i;
		}//for

		//라벨 정렬
		for(int i = 0; i < rArr.size() && i < 5; i++) {
			int r = indexR[i];

			rankR = new JLabel();
			idR = new JLabel();
			scoreR = new JLabel();
			dateR = new JLabel();

			rankR.setBounds(670, (370 + spacing * i), 100, 60);
			idR.setBounds(780, (370 + spacing * i), 100, 60);
			scoreR.setBounds(900, (370 + spacing * i), 100, 60);
			dateR.setBounds(1010, (370 + spacing * i), 100, 60);

			rankR.setText(orderR[r] + "위");
			idR.setText(rArr.get(r).getId());
			scoreR.setText(rArr.get(r).getScore() + "개");
			dateR.setText(rArr.get(r).getDate());

			rankR.setFont(font_18p);
			idR.setFont(font_18p);
			scoreR.setFont(font_18p);
			dateR.setFont(font_18p);

			rankR.setHorizontalAlignment(JLabel.CENTER);
			idR.setHorizontalAlignment(JLabel.CENTER);
			scoreR.setHorizontalAlignment(JLabel.CENTER);
			dateR.setHorizontalAlignment(JLabel.CENTER);

			f.add(rankR);
			f.add(idR);
			f.add(scoreR);
			f.add(dateR);
		}//for
		//산성비 모드 라벨 끝----------------------------------------------------------
		
		//버튼 추가(홈으로)
		JButton btnHome = new JButton("홈으로");
		btnHome.setBounds(1050, 800, 100, 50);
		btnHome.setFont(font_20b);
		btnHome.setBackground(new Color(0, 0, 0, 0));
		btnHome.setBorderPainted(false);

		btnHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});

		//요소들 프레임에 추가
		f.add(btnSentence);
		f.add(btnRain);

		f.add(btnRank);
		f.add(btnName);
		f.add(btnScore);
		f.add(btnDate);

		f.add(btnRank2);
		f.add(btnName2);
		f.add(btnScore2);
		f.add(btnDate2);
		
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
