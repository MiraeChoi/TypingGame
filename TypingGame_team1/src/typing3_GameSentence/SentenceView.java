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
	private int x = 59; //Ÿ�̸� ���ѽð�
	private Random rnd = new Random();
	private String userId;
	private int scorenum = 0;

	public void gameSentenceView(String id) {
		userId = id;

		f = new Frame("���� ���� ����");
		f.setSize(1200, 900);
		f.setLocationRelativeTo(null);
		//f.setLayout(null);

		//Ÿ��Ʋ �� �޴� ��Ʈ ����
		Font font_30b = new Font("���� ���", Font.BOLD, 30);
		Font font_20b = new Font("���� ���", Font.BOLD, 20);
		Font font_20p = new Font("���� ���", Font.PLAIN, 20);

		//���� ���� ID ����
		JLabel viewId = new JLabel(userId);
		viewId.setBounds(360, 100, 400, 40);
		viewId.setHorizontalAlignment(SwingConstants.CENTER);
		viewId.setFont(font_30b);
		f.add(viewId);

		//���� ���� ���� ����
		JLabel userScore = new JLabel("0��");
		userScore.setBounds(850, 100, 300, 40);
		userScore.setHorizontalAlignment(SwingConstants.CENTER);
		userScore.setFont(font_30b);
		f.add(userScore);

		//��� �̹��� ����
		ImageIcon img = new ImageIcon("gray_bg.jpg");
		JLabel bgImg = new JLabel(img);
		bgImg.setBounds(0, 0, 1920, 1050);

		//�־��� ���� �Է��� ��
		JLabel staticText = new JLabel();
		staticText.setBounds(200, 320, 800, 70);
		staticText.setFont(font_20b);
		f.add(staticText);

		//����ڰ� ������ �Է��� �ؽ�Ʈ�ʵ�
		inputText = new JTextField(50);
		inputText.setBounds(200, 400, 800, 70);
		inputText.setFont(font_20p);
		f.add(inputText);

		//������ Ȯ���� ��
		JLabel outcome = new JLabel("�̰��� ���� ���ΰ� ǥ�õ˴ϴ�.");
		outcome.setBounds(200, 500, 800, 70);
		outcome.setFont(font_20b);
		outcome.setForeground(Color.BLUE);
		f.add(outcome);
		
		//���� �� ���� ��ư
		Panel ps = new Panel();
		ps.setBackground(new Color(220, 220, 220, 100));
		
		JButton startBtn = new JButton("����");
		startBtn.setBounds(300, 650, 200, 60);
		startBtn.setFont(font_30b);
		JButton exitBtn = new JButton("����");
		exitBtn.setBounds(620, 650, 200, 60);
		exitBtn.setFont(font_30b);
		
		new ButtonEffect(startBtn);
		new ButtonEffect(exitBtn);
		
		ps.add(startBtn);
		ps.add(exitBtn);
		
		f.add(ps, BorderLayout.SOUTH);

		//������ �޼���
		ThreadS ts = new ThreadS();

		//��ŸƮ ��ư �̺�Ʈ
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ts.start();
				staticText.setText(sArr.get(rnd.nextInt(sArr.size())));
				//f.requestFocusInWindow();
				//inputText.requestFocus();
			}
		});

		//�����ư ������ �̺�Ʈ ����
		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int exitOption = JOptionPane.showConfirmDialog
						(null, "�����Ͻðڽ��ϱ�?", "���α׷� ����", JOptionPane.YES_NO_OPTION);
				if(exitOption == JOptionPane.YES_OPTION) {
					f.dispose();
					timer.dispose();
				} else {
					return;
				}
			}
		});

		//���Ͱ��� �����Ͽ� �Է��� ���� �������� Ȯ��, x�� �ȿ� ���� ���� �Է��ϱ�
		inputText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (staticText.getText().equals(inputText.getText())) {
						++scorenum;
						
						//�������� ���� ����
						userScore.setText(scorenum + "��");
						outcome.setForeground(Color.BLUE);
						outcome.setText("�¾ҽ��ϴ�!");
						staticText.setText(sArr.get(rnd.nextInt(sArr.size())));
						inputText.setText(""); //�ؽ�Ʈ�ʵ� �� ĭ���� �ٲٱ�
					} else {
						outcome.setForeground(Color.RED);
						outcome.setText("Ʋ�Ƚ��ϴ�.");
						inputText.setText("");

					}
				}//if
			}
		});


		String[] word = {"�ܿ��� ���� ���� ���� ������.",
				"�״��� �Ϸ��Ϸ縦 �״��� ������ ���̶�� �����϶�.",
				"�� �ڽ��� �� ���� ��ȣ�ڴ�.",
				"���� ������ �ʻ��̴�.",
				"�����ؼ� �����ϴ� ���� �ƴ϶� �����ϰ� �־��� ������ ������ ���̴�.",
				"������ �������� ���������� �غ��ϴ� ����鵵 �����ϴ�.",
				"���� ���ϱ� ���� ���� �������� ���� �� ����.",
				"���Ǵ� ���� ȭ������ �������� ��´�.",
				"�츮�� ���� ������ �ϴ��İ� � ����� ���� �����Ѵ�.",
				"�λ��� �ٽ� ��ٸ� ���� ������ �� ���� �Ǽ��� ����������.",
				"�ڽ��� ��������. �׷��� ����� �巯�� ���̴�.",
				"���� ��ȸ�κ��� ���� ������ ������ ���۵ȴ�.",
				"�ְ� �����Ϸ��� �������� �����϶�.",
				"ŷ�����ʷ� ������ �������� ���Ǵ� F���ǽ�",
				"�� �� ���п� ������ ���и� ȥ������ ����."};

		sArr = new ArrayList<String>();

		for(int i = 0; i < word.length; i++) {
			sArr.add(word[i]);
		}//for

		//��� �����ӿ� �߰�
		f.add(bgImg);

		//������ �ݱ� ��ư�� �̺�Ʈ �ְ� ����
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int exitOption = JOptionPane.showConfirmDialog
						(null, "�����Ͻðڽ��ϱ�?", "���α׷� ����", JOptionPane.YES_NO_OPTION);
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
			timer = new Frame("Ÿ�̸�");
			timer.setBounds(100, 100, 300, 100);
			
			JLabel sec = new JLabel();
			sec.setText("01 : 00");
			sec.setFont(new Font("���� ���", Font.BOLD, 30));
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
			JOptionPane.showMessageDialog(null, "60�� ���� " + scorenum + " �� �Է��ϼ̽��ϴ�.");

			//��ŷ�� �ö� ��¥
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			Date time = new Date();
			String date = format1.format(time);

			//����� ��ŷ ���� �����
			FileWriter fw = null;
			BufferedWriter bw = null;

			File dir = new File("C:\\TypingGame\\Sentence\\");	

			try {
				if(!dir.exists()) {
					dir.mkdirs();		
				}

				fw = new FileWriter("c:\\TypingGame\\Sentence\\" + userId + ".txt");
				bw = new BufferedWriter(fw);

				//bw.write("�̸�/���̵�/��й�ȣ/�߰�����\n");
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