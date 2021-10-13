package typing3_GameSentence;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import typing1_Main.ButtonEffect;

public class SentenceView {

	private ArrayList<String> sArr;
	private Frame f;
	private JTextField inputText;
	private Frame timer;
	private int x = 59; //타이머 제한시간
	private Random rnd = new Random();
	private String userId;
	private int scorenum = 0;

	public void gameSentenceView(String id) {
		userId = id;

		f = new Frame("문장 연습 게임");
		f.setSize(1200, 900);
		f.setLocationRelativeTo(null);
		//f.setLayout(null);

		//타이틀 및 메뉴 폰트 설정
		Font font_30b = new Font("맑은 고딕", Font.BOLD, 30);
		Font font_20b = new Font("맑은 고딕", Font.BOLD, 20);
		Font font_20p = new Font("맑은 고딕", Font.PLAIN, 20);

		//게임 위에 ID 띄우기
		JLabel viewId = new JLabel(userId);
		viewId.setBounds(360, 100, 400, 40);
		viewId.setHorizontalAlignment(SwingConstants.CENTER);
		viewId.setFont(font_30b);
		f.add(viewId);

		//게임 위에 점수 띄우기
		JLabel userScore = new JLabel("0개");
		userScore.setBounds(850, 100, 300, 40);
		userScore.setHorizontalAlignment(SwingConstants.CENTER);
		userScore.setFont(font_30b);
		f.add(userScore);

		//배경 이미지 삽입
		ImageIcon img = new ImageIcon("gray_bg.jpg");
		JLabel bgImg = new JLabel(img);
		bgImg.setBounds(0, 0, 1920, 1050);

		//주어진 문장 입력할 라벨
		JLabel staticText = new JLabel();
		staticText.setBounds(200, 320, 800, 70);
		staticText.setFont(font_20b);
		f.add(staticText);

		//사용자가 문장을 입력할 텍스트필드
		inputText = new JTextField(50);
		inputText.setBounds(200, 400, 800, 70);
		inputText.setFont(font_20p);
		f.add(inputText);

		//정답을 확인할 라벨
		JLabel outcome = new JLabel("이곳에 정답 여부가 표시됩니다.");
		outcome.setBounds(200, 500, 800, 70);
		outcome.setFont(font_20b);
		outcome.setForeground(Color.BLUE);
		f.add(outcome);
		
		//시작 및 종료 버튼
		Panel ps = new Panel();
		ps.setBackground(new Color(220, 220, 220, 100));
		
		JButton startBtn = new JButton("시작");
		startBtn.setBounds(300, 650, 200, 60);
		startBtn.setFont(font_30b);
		JButton exitBtn = new JButton("종료");
		exitBtn.setBounds(620, 650, 200, 60);
		exitBtn.setFont(font_30b);
		
		new ButtonEffect(startBtn);
		new ButtonEffect(exitBtn);
		
		ps.add(startBtn);
		ps.add(exitBtn);
		
		f.add(ps, BorderLayout.SOUTH);

		//스레드 메서드
		ThreadS ts = new ThreadS();

		//스타트 버튼 이벤트
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ts.start();
				staticText.setText(sArr.get(rnd.nextInt(sArr.size())));
				//f.requestFocusInWindow();
				//inputText.requestFocus();
			}
		});

		//종료버튼 누르면 이벤트 실행
		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int exitOption = JOptionPane.showConfirmDialog
						(null, "종료하시겠습니까?", "프로그램 종료", JOptionPane.YES_NO_OPTION);
				if(exitOption == JOptionPane.YES_OPTION) {
					f.dispose();
					timer.dispose();
				} else {
					return;
				}
			}
		});

		//엔터값을 감지하여 입력한 값이 정답인지 확인, x초 안에 많은 문장 입력하기
		inputText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (staticText.getText().equals(inputText.getText())) {
						++scorenum;
						
						//점수판의 개수 변경
						userScore.setText(scorenum + "개");
						outcome.setForeground(Color.BLUE);
						outcome.setText("맞았습니다!");
						staticText.setText(sArr.get(rnd.nextInt(sArr.size())));
						inputText.setText(""); //텍스트필드 빈 칸으로 바꾸기
					} else {
						outcome.setForeground(Color.RED);
						outcome.setText("틀렸습니다.");
						inputText.setText("");

					}
				}//if
			}
		});


		String[] word = {"겨울이 오면 봄이 머지 않으리.",
				"그대의 하루하루를 그대의 마지막 날이라고 생각하라.",
				"나 자신이 내 명예의 수호자다.",
				"말은 마음의 초상이다.",
				"성공해서 만족하는 것이 아니라 만족하고 있었기 때문에 성공한 것이다.",
				"세상은 고통으로 가득하지만 극복하는 사람들도 가득하다.",
				"악을 피하기 위해 선을 저지름은 선일 수 없다.",
				"예의는 남과 화목함을 으뜸으로 삼는다.",
				"우리가 무슨 생각을 하느냐가 어떤 사람이 될지 결정한다.",
				"인생을 다시 산다면 다음 번에는 더 많은 실수를 저지르리라.",
				"자신을 내보여라. 그러면 재능이 드러날 것이다.",
				"작은 기회로부터 종종 위대한 업적이 시작된다.",
				"최고에 도달하려면 최저에서 시작하라.",
				"킹갓제너럴 정용훈 선생님의 강의는 F강의실",
				"한 번 실패와 영원한 실패를 혼동하지 마라."};

		sArr = new ArrayList<String>();

		for(int i = 0; i < word.length; i++) {
			sArr.add(word[i]);
		}//for

		//배경 프레임에 추가
		f.add(bgImg);

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

		f.setVisible(true);

	}

	public class ThreadS extends Thread {
		public void run() {
			timer = new Frame("타이머");
			timer.setBounds(100, 100, 300, 100);
			
			JLabel sec = new JLabel();
			sec.setText("01 : 00");
			sec.setFont(new Font("맑은 고딕", Font.BOLD, 30));
			sec.setHorizontalAlignment(SwingConstants.CENTER);
			sec.setForeground(Color.BLUE);
			
			timer.add(sec);
			timer.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					timer.dispose();
				}
			});
			timer.setVisible(true);
			
			f.requestFocusInWindow();
			inputText.requestFocus();
			for (int i = x; i >= 0; i--) {
				try {
					Thread.sleep(1000);
					sec.setText((String.format("%02d : %02d", 0, i)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			timer.setVisible(false);
			JOptionPane.showMessageDialog(null, "60초 동안 " + scorenum + " 개 입력하셨습니다.");

			//랭킹에 올라갈 날짜
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			Date time = new Date();
			String date = format1.format(time);

			//사용자 랭킹 파일 만들기
			FileWriter fw = null;
			BufferedWriter bw = null;

			File dir = new File("C:\\TypingGame\\Sentence\\");	

			try {
				if(!dir.exists()) {
					dir.mkdirs();		
				}

				fw = new FileWriter("c:\\TypingGame\\Sentence\\" + userId + ".txt");
				bw = new BufferedWriter(fw);

				//bw.write("이름/아이디/비밀번호/추가사항\n");
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
					e3.printStackTrace();
				}
			}//try
		}//run()
	}

}