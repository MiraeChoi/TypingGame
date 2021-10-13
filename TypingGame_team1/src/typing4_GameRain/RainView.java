package typing4_GameRain;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import typing1_Main.ButtonEffect;
import typing2_Login.SelectGame;

public class RainView extends KeyAdapter implements ActionListener, ItemListener {
	
	private ArrayList<UserInfo_R> userArr = new ArrayList<>();
	SelectGame sg = new SelectGame();
	
	//생성자에 사용할 변수
	private Frame f;
	private Frame readyF;
	private JLabel selectLevel;
	private JTextField userInput;
	private Choice levelList;
	private String[] gameLv =
		{"Level 1", "Level 2",
		 "Level 3", "Level 4", "Level 5"};
	private JButton btnStart;
	private JPanel panelScreen;
	private JLabel viewId;
	private JLabel viewLevel;
	private JLabel userScore;
	private JLabel panelLife1;
	private JLabel panelLife2;
	private JLabel panelLife3;
	private Frame endF;
	private JLabel gameOver;
	private JLabel viewScore;
	private JButton btnRestart;
	private String userId;
	private String date;

	//이벤트에 사용할 변수
	private int life = 0;
	private int scorenum = 0;
	private int speed = 3500;
	private JLabel label[] = new JLabel[10000];

	//스레드에 사용할 변수
	private int i = 0;
	private String[] word =
		{"가다", "가라앉다", "가방", "감다", "감사", "건전하다", "겨드랑이", "고용하다",
		 "깜짝", "꿈", "나쁘다", "대기", "대창", "뚜벅뚜벅", "뛰어가다", "마라톤",
		 "명확하다", "미래", "믿음", "반대하다", "반발", "보호하다", "붕어빵", "사과",
		 "삼겹살", "생각하다", "선명하다", "선택하다", "선풍기", "소나기", "손가락", "수도꼭지",
		 "숨바꼭질", "쏘다", "아장아장", "어진", "오다", "우석", "우수하다", "위치하다",
		 "이별", "일어나다", "자바", "전하다", "정용훈", "존경하다", "종소리", "주인공",
		 "착하다", "찬성하다", "찬형", "책", "초록", "초밥", "파란색", "할인"};

	public ArrayList<UserInfo_R> getUserArr() {
		return userArr;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getScorenum() {
		return scorenum;
	}
	public void setScorenum(int scorenum) {
		this.scorenum = scorenum;
	}

	public void gameRainView(String id) {
		userId = id;

		//프레임
		f = new Frame("산성비");
		f.setSize(1200, 900);
		f.setLocationRelativeTo(null);
		f.setLayout(null);

		//타이틀 및 메뉴 폰트 설정
		Font font_50b = new Font("맑은 고딕", Font.BOLD, 50);
		Font font_20b = new Font("맑은 고딕", Font.BOLD, 20);
		Font font_20p = new Font("맑은 고딕", Font.PLAIN, 20);

		//배경 이미지 삽입
		ImageIcon img = new ImageIcon("gray_bg.jpg");
		JLabel bgImg = new JLabel(img);
		bgImg.setBounds(0, 0, 1920, 1050);

		//----------------------------------------------------
		//시작 프레임
		readyF = new Frame("게임 선택");
		readyF.setSize(500, 600);
		readyF.setLocationRelativeTo(null);
		readyF.setLayout(null);

		selectLevel = new JLabel("레벨을 선택하세요.");
		selectLevel.setBounds(160, 100, 400, 50);
		selectLevel.setFont(font_20b);
		readyF.add(selectLevel);

		//레벨 리스트를 보여줄 JList
		levelList = new Choice();
		levelList.setBounds(100, 200, 300, 0);
		for(int i = 0; i < gameLv.length; i++) {
			levelList.add(gameLv[i]);
		}//for
		levelList.setFont(font_20p);
		readyF.add(levelList);

		//종료 이벤트
		readyF.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				readyF.dispose();
				f.dispose();
			}
		});
		//----------------------------------------------------

		//사용자가 문장을 입력할 텍스트필드
		userInput = new JTextField();
		userInput.setBounds(300, 800, 550, 40);
		userInput.setColumns(20);
		userInput.setFont(font_20p);
		f.add(userInput);

		//시작 버튼
		btnStart = new JButton("시작");// 시작 버튼
		btnStart.setBounds(195, 450, 100, 50);
		btnStart.setFont(font_20b);
		new ButtonEffect(btnStart);
		readyF.add(btnStart);

		//시작 버튼을 누르면 나타날 게임 화면 패널
		panelScreen = new JPanel();
		panelScreen.setBounds(100, 150, 1000, 600);
		panelScreen.setBackground(Color.ORANGE);
		panelScreen.setForeground(Color.ORANGE);
		panelScreen.setLayout(null);
		panelScreen.setVisible(false);
		f.add(panelScreen);

		//산성비 모드의 빨간색 선(선에 닿으면 게임 오버)
		JPanel redLine = new JPanel(); // 게임화면에서 빨간색 선
		redLine.setBounds(0, 530, 1000, 7);
		redLine.setBackground(Color.RED);
		panelScreen.add(redLine);

		//게임 위에 ID 띄우기
		viewId = new JLabel(userId);
		viewId.setBounds(360, 80, 400, 40);
		viewId.setHorizontalAlignment(SwingConstants.CENTER);
		viewId.setFont(font_20b);
		f.add(viewId);

		//게임 옆에 맞힌 개수 띄우기
		userScore = new JLabel("0개");
		userScore.setBounds(850, 80, 300, 40);
		userScore.setHorizontalAlignment(SwingConstants.CENTER);
		userScore.setFont(font_20b);
		f.add(userScore);

		//사용자가 플레이하는 중인 레벨 띄우기
		viewLevel = new JLabel();
		viewLevel.setBounds(950, 780, 200, 60);
		viewLevel.setHorizontalAlignment(SwingConstants.CENTER);
		viewLevel.setFont(font_20b);
		f.add(viewLevel);

		//생명(총 3개)
		panelLife1 = new JLabel("♥");
		panelLife1.setBounds(100, 800, 20, 20);
		panelLife1.setBackground(new Color(0, 0, 0, 0));
		panelLife1.setFont(font_20p);
		panelLife1.setForeground(Color.RED);
		f.add(panelLife1);

		panelLife2 = new JLabel("♥");
		panelLife2.setBounds(130, 800, 20, 20);
		panelLife2.setBackground(new Color(0, 0, 0, 0));
		panelLife2.setFont(font_20p);
		panelLife2.setForeground(Color.RED);
		f.add(panelLife2);

		panelLife3 = new JLabel("♥");
		panelLife3.setBounds(160, 800, 20, 20);
		panelLife3.setBackground(new Color(0, 0, 0, 0));
		panelLife3.setFont(font_20p);
		panelLife3.setForeground(Color.RED);
		f.add(panelLife3);

		//----------------------------------------------------
		//게임 오버 프레임
		endF = new Frame();
		endF.setSize(500, 600);
		endF.setLocationRelativeTo(null);
		endF.setLayout(null);

		endF.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int exitOption = JOptionPane.showConfirmDialog
						(null, "종료하시겠습니까?", "프로그램 종료", JOptionPane.YES_NO_OPTION);
				if(exitOption == JOptionPane.YES_OPTION) {
					endF.dispose();
					f.dispose();
				} else {
					return;
				}
			}
		});

		//게임 오버 라벨
		gameOver = new JLabel("Game Over");
		gameOver.setBounds(50, 130, 400, 50);
		gameOver.setForeground(Color.DARK_GRAY);
		gameOver.setFont(font_50b);
		gameOver.setHorizontalAlignment(SwingConstants.CENTER);
		endF.add(gameOver);

		//획득한 점수 표시
		viewScore = new JLabel("Score");
		viewScore.setBounds(50, 250, 400, 50);
		viewScore.setHorizontalAlignment(SwingConstants.CENTER);
		viewScore.setForeground(Color.PINK);
		viewScore.setFont(font_50b);
		endF.add(viewScore);

		//다시 시작 버튼
		btnRestart = new JButton("다시 하기");// 시작 버튼
		btnRestart.setBounds(150, 450, 200, 50);
		btnRestart.setFont(font_20p);
		new ButtonEffect(btnRestart);
		endF.add(btnRestart);

		//----------------------------------------------------

		//이벤트 설정 및 텍스트 필드 포커스
		btnStart.addActionListener(this); //시작 버튼 이벤트
		userInput.addKeyListener(this); //키 이벤트
		levelList.addItemListener(this); //리스트 이벤트
		userInput.requestFocus(); //텍스트 필드 커서
		btnRestart.addActionListener(this); //다시 시작 버튼 이벤트

		//프레임 닫기 버튼에 이벤트 주고 실행
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int exitOption = JOptionPane.showConfirmDialog
						(null, "종료하시겠습니까?", "프로그램 종료", JOptionPane.YES_NO_OPTION);
				if(exitOption == JOptionPane.YES_OPTION) {
					f.dispose();
				} else {
					return;
				}
			}
		});

		f.add(bgImg);
		//readyF.add(bgImg);
		//endF.add(bgImg);

		f.setVisible(true);
		readyF.setVisible(true);
		endF.setVisible(false);
	}//생성자

	//게임 시작 버튼 이벤트 처리
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().toString().equals("시작")) {
			if (e.getSource().equals(btnStart)) {
				//라이프 0 = 라이프를 잃지 않은 상태(총 3개)
				life = 0;
				//게임 시작 시 점수판
				userScore.setText("0점");
				scorenum = 0;

				//게임 결과 화면이 미리 떠 있다면 보이지 않게 설정
				if (endF.isVisible()) {
					endF.setVisible(false);// 결과화면 안보이게
				}

				//게임 시작 전이라면 스레드를 실행하지 않음
				if (label[0] != null) {
					for (int i = 0; i < label.length; i++) {
						//스레드가 돌아가고 있다면 중지하는 코드
						if (!new WordSetting().isAlive() || !new WordMoving().isAlive()) {
							new WordSetting().interrupt(); //단어 스레드 중지
							new WordMoving().interrupt();  //내려가는 스레드 중지
						}
						//시작 전에 단어가 만들어져 있다면 보이지 않게 설정
						if (label[i] != null) {
							label[i].setVisible(false);
						}
					}//for
				}//if

				//게임 시작 시 프레임 닫음
				readyF.setVisible(false);

				//게임 시작 시 단어 입력할 텍스트필드에 포커스 주기
				userInput.requestFocus();

				//단어를 뿌려주는 스레드 실행
				new WordSetting().start();

			}//if
		} else if(e.getActionCommand().toString().equals("다시 하기")) {
			readyF.setVisible(true);
			endF.setVisible(false);
		}
	}//actionPerformed()

	//엔터 키 이벤트 처리 : 단어를 입력하여 동일 단어 확인 및 제거
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			//텍스트 필드에 값이 있는지 없는지 확인
			if (!userInput.getText().equals("")) {
				//String 변수에 텍스트 값 주기
				String answer = userInput.getText().toString();

				//사용자가 입력한 값과 동일한 단어 있는지 검사
				//동일 단어가 있다면 안 보이게 하고 카운트 개수 감소
				//검사 후 정답 여부에 관계없이 텍스트 필드에 사용자가 입력한 값 삭제
				outer : for (int i = 0; i < label.length; i++) {
					try {
						//동일한 단어 검사
						if (answer.equals(label[i].getText())) {
							//동일한 단어가 화면에 보여져 있는지 검사
							if (label[i].isVisible()) {
								//하나 맞힐 때마다 1점씩 추가
								++scorenum;
								//점수판의 개수 변경
								userScore.setText(scorenum + "개");
								//단어 화면에서 제거
								label[i].setVisible(false);
								//사용자가 입력한 텍스트 값 없애기
								userInput.setText("");
								break outer;
							}
						} else {
							userInput.setText("");
						}
					} catch (Exception e2) {
						e2.getMessage();
					}

				}//outer
			}//if_inner
		}//if_outer
	}//keyPressed()


	//단어를 하나씩 뿌리는 스레드
	class WordSetting extends Thread {
		@Override
		public void run() {
			//게임화면 등장
			panelScreen.setVisible(true);
			//단어를 레벨에 따른 속도로 게임 화면에 x값 랜덤으로 생성
			for (i = 0; i <= 10000; i++) {
				try {
					Random rnd = new Random();
					label[i] = new JLabel(word[rnd.nextInt(word.length)]);

					//내려올 단어의 초기 위치 설정
					label[i].setBounds(0, 0, 100, 50);
					//단어 추가
					panelScreen.add(label[i]);
					//x값 랜덤으로 보여줌
					label[i].setLocation(rnd.nextInt(940), 3);

					//단어를 움직이는 스레드 실행
					new WordMoving().start();

					//게임 레벨에 따른 단어 속도
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}//for
		}//run()
	}

	//단어를 내려가게 하고 게임 결과 확인하는 스레드
	class WordMoving extends Thread {
		@Override
		public void run() {
			//단어 생성 개수만큼 for문을 돌려 각 단어마다 y값 변경
			for(int n = 0; n < i; n++) {
				if (label[n].isVisible()) {
					int sp = label[n].getY();
					int xp = label[n].getX();

					label[n].setLocation(xp, sp + 10);
				}//if

				//단어가 빨간 선을 넘었는지 확인
				if (label[n].isVisible() && label[n].getY() > 510) {
					//단어가 선을 넘으면 단어 사라짐
					label[n].setVisible(false);
					//라이프 1 = 라이프 1개 감소
					life++;
				}
			}//for

			switch (life) {
			//감소한 라이프 없음
			case 0: 
				panelLife1.setForeground(Color.RED);
				panelLife2.setForeground(Color.RED);
				panelLife3.setForeground(Color.RED);
				break;
			//라이프 1개 없어짐
			case 1: 
				panelLife1.setForeground(Color.RED);
				panelLife2.setForeground(Color.RED);
				panelLife3.setForeground(Color.DARK_GRAY);
				break;
			//라이프 2개 없어짐
			case 2:
				panelLife1.setForeground(Color.RED);
				panelLife2.setForeground(Color.DARK_GRAY);
				panelLife3.setForeground(Color.DARK_GRAY);
				break;
			//라이프 3개 없어짐 -> Game Over
			case 3:
				panelLife1.setForeground(Color.DARK_GRAY);
				panelLife2.setForeground(Color.DARK_GRAY);
				panelLife3.setForeground(Color.DARK_GRAY);

				//게임 오버 화면 띄우기
				panelScreen.setVisible(false);
				endF.setVisible(true);
				viewScore.setText(userScore.getText());
				
				//랭킹에 올라갈 날짜
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				Date time = new Date();
				date = format1.format(time);
				
				//Rain 파일 만들기
				FileWriter fw = null;
				BufferedWriter bw = null;
				
				File dir = new File("C:\\TypingGame\\Rain\\");	
				
				try {
					if(!dir.exists()) {
						dir.mkdirs();		
					}
					
					fw = new FileWriter("c:\\TypingGame\\Rain\\" + userId + ".txt");
					bw = new BufferedWriter(fw);
					
					bw.write(userId + "/");
					bw.write(scorenum + "/");
					bw.write(date + "/");
				} catch (Exception e2){
					e2.printStackTrace();
				} finally {
					try {
						bw.close();
						fw.close();
					} catch (IOException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
				}//try
				
				//스레드 중지
				new WordSetting().interrupt();
				new WordMoving().interrupt();	
				break;
			default:
				break;
			}//switch
			
		}//run()
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		switch (levelList.getSelectedIndex()) {
		case 0:
			speed = 2500;// 게임속도
			break;
		case 1:
			speed = 1700;// 게임속도
			break;
		case 2:
			speed = 1200;// 게임속도
			break;
		case 3:
			speed = 700;// 게임속도
			break;
		case 4:
			speed = 200;// 게임속도
			break;
		default:
			break;
		}//switch

		String str = e.getItem().toString();
		viewLevel.setText(str);
	}//itemStateChanged()
}
