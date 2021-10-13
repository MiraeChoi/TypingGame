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
	
	//�����ڿ� ����� ����
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

	//�̺�Ʈ�� ����� ����
	private int life = 0;
	private int scorenum = 0;
	private int speed = 3500;
	private JLabel label[] = new JLabel[10000];

	//�����忡 ����� ����
	private int i = 0;
	private String[] word =
		{"����", "����ɴ�", "����", "����", "����", "�����ϴ�", "�ܵ����", "����ϴ�",
		 "��¦", "��", "���ڴ�", "���", "��â", "�ѹ��ѹ�", "�پ��", "������",
		 "��Ȯ�ϴ�", "�̷�", "����", "�ݴ��ϴ�", "�ݹ�", "��ȣ�ϴ�", "�ؾ", "���",
		 "����", "�����ϴ�", "�����ϴ�", "�����ϴ�", "��ǳ��", "�ҳ���", "�հ���", "��������",
		 "���ٲ���", "���", "�������", "����", "����", "�켮", "����ϴ�", "��ġ�ϴ�",
		 "�̺�", "�Ͼ��", "�ڹ�", "���ϴ�", "������", "�����ϴ�", "���Ҹ�", "���ΰ�",
		 "���ϴ�", "�����ϴ�", "����", "å", "�ʷ�", "�ʹ�", "�Ķ���", "����"};

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

		//������
		f = new Frame("�꼺��");
		f.setSize(1200, 900);
		f.setLocationRelativeTo(null);
		f.setLayout(null);

		//Ÿ��Ʋ �� �޴� ��Ʈ ����
		Font font_50b = new Font("���� ���", Font.BOLD, 50);
		Font font_20b = new Font("���� ���", Font.BOLD, 20);
		Font font_20p = new Font("���� ���", Font.PLAIN, 20);

		//��� �̹��� ����
		ImageIcon img = new ImageIcon("gray_bg.jpg");
		JLabel bgImg = new JLabel(img);
		bgImg.setBounds(0, 0, 1920, 1050);

		//----------------------------------------------------
		//���� ������
		readyF = new Frame("���� ����");
		readyF.setSize(500, 600);
		readyF.setLocationRelativeTo(null);
		readyF.setLayout(null);

		selectLevel = new JLabel("������ �����ϼ���.");
		selectLevel.setBounds(160, 100, 400, 50);
		selectLevel.setFont(font_20b);
		readyF.add(selectLevel);

		//���� ����Ʈ�� ������ JList
		levelList = new Choice();
		levelList.setBounds(100, 200, 300, 0);
		for(int i = 0; i < gameLv.length; i++) {
			levelList.add(gameLv[i]);
		}//for
		levelList.setFont(font_20p);
		readyF.add(levelList);

		//���� �̺�Ʈ
		readyF.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				readyF.dispose();
				f.dispose();
			}
		});
		//----------------------------------------------------

		//����ڰ� ������ �Է��� �ؽ�Ʈ�ʵ�
		userInput = new JTextField();
		userInput.setBounds(300, 800, 550, 40);
		userInput.setColumns(20);
		userInput.setFont(font_20p);
		f.add(userInput);

		//���� ��ư
		btnStart = new JButton("����");// ���� ��ư
		btnStart.setBounds(195, 450, 100, 50);
		btnStart.setFont(font_20b);
		new ButtonEffect(btnStart);
		readyF.add(btnStart);

		//���� ��ư�� ������ ��Ÿ�� ���� ȭ�� �г�
		panelScreen = new JPanel();
		panelScreen.setBounds(100, 150, 1000, 600);
		panelScreen.setBackground(Color.ORANGE);
		panelScreen.setForeground(Color.ORANGE);
		panelScreen.setLayout(null);
		panelScreen.setVisible(false);
		f.add(panelScreen);

		//�꼺�� ����� ������ ��(���� ������ ���� ����)
		JPanel redLine = new JPanel(); // ����ȭ�鿡�� ������ ��
		redLine.setBounds(0, 530, 1000, 7);
		redLine.setBackground(Color.RED);
		panelScreen.add(redLine);

		//���� ���� ID ����
		viewId = new JLabel(userId);
		viewId.setBounds(360, 80, 400, 40);
		viewId.setHorizontalAlignment(SwingConstants.CENTER);
		viewId.setFont(font_20b);
		f.add(viewId);

		//���� ���� ���� ���� ����
		userScore = new JLabel("0��");
		userScore.setBounds(850, 80, 300, 40);
		userScore.setHorizontalAlignment(SwingConstants.CENTER);
		userScore.setFont(font_20b);
		f.add(userScore);

		//����ڰ� �÷����ϴ� ���� ���� ����
		viewLevel = new JLabel();
		viewLevel.setBounds(950, 780, 200, 60);
		viewLevel.setHorizontalAlignment(SwingConstants.CENTER);
		viewLevel.setFont(font_20b);
		f.add(viewLevel);

		//����(�� 3��)
		panelLife1 = new JLabel("��");
		panelLife1.setBounds(100, 800, 20, 20);
		panelLife1.setBackground(new Color(0, 0, 0, 0));
		panelLife1.setFont(font_20p);
		panelLife1.setForeground(Color.RED);
		f.add(panelLife1);

		panelLife2 = new JLabel("��");
		panelLife2.setBounds(130, 800, 20, 20);
		panelLife2.setBackground(new Color(0, 0, 0, 0));
		panelLife2.setFont(font_20p);
		panelLife2.setForeground(Color.RED);
		f.add(panelLife2);

		panelLife3 = new JLabel("��");
		panelLife3.setBounds(160, 800, 20, 20);
		panelLife3.setBackground(new Color(0, 0, 0, 0));
		panelLife3.setFont(font_20p);
		panelLife3.setForeground(Color.RED);
		f.add(panelLife3);

		//----------------------------------------------------
		//���� ���� ������
		endF = new Frame();
		endF.setSize(500, 600);
		endF.setLocationRelativeTo(null);
		endF.setLayout(null);

		endF.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int exitOption = JOptionPane.showConfirmDialog
						(null, "�����Ͻðڽ��ϱ�?", "���α׷� ����", JOptionPane.YES_NO_OPTION);
				if(exitOption == JOptionPane.YES_OPTION) {
					endF.dispose();
					f.dispose();
				} else {
					return;
				}
			}
		});

		//���� ���� ��
		gameOver = new JLabel("Game Over");
		gameOver.setBounds(50, 130, 400, 50);
		gameOver.setForeground(Color.DARK_GRAY);
		gameOver.setFont(font_50b);
		gameOver.setHorizontalAlignment(SwingConstants.CENTER);
		endF.add(gameOver);

		//ȹ���� ���� ǥ��
		viewScore = new JLabel("Score");
		viewScore.setBounds(50, 250, 400, 50);
		viewScore.setHorizontalAlignment(SwingConstants.CENTER);
		viewScore.setForeground(Color.PINK);
		viewScore.setFont(font_50b);
		endF.add(viewScore);

		//�ٽ� ���� ��ư
		btnRestart = new JButton("�ٽ� �ϱ�");// ���� ��ư
		btnRestart.setBounds(150, 450, 200, 50);
		btnRestart.setFont(font_20p);
		new ButtonEffect(btnRestart);
		endF.add(btnRestart);

		//----------------------------------------------------

		//�̺�Ʈ ���� �� �ؽ�Ʈ �ʵ� ��Ŀ��
		btnStart.addActionListener(this); //���� ��ư �̺�Ʈ
		userInput.addKeyListener(this); //Ű �̺�Ʈ
		levelList.addItemListener(this); //����Ʈ �̺�Ʈ
		userInput.requestFocus(); //�ؽ�Ʈ �ʵ� Ŀ��
		btnRestart.addActionListener(this); //�ٽ� ���� ��ư �̺�Ʈ

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

		f.add(bgImg);
		//readyF.add(bgImg);
		//endF.add(bgImg);

		f.setVisible(true);
		readyF.setVisible(true);
		endF.setVisible(false);
	}//������

	//���� ���� ��ư �̺�Ʈ ó��
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().toString().equals("����")) {
			if (e.getSource().equals(btnStart)) {
				//������ 0 = �������� ���� ���� ����(�� 3��)
				life = 0;
				//���� ���� �� ������
				userScore.setText("0��");
				scorenum = 0;

				//���� ��� ȭ���� �̸� �� �ִٸ� ������ �ʰ� ����
				if (endF.isVisible()) {
					endF.setVisible(false);// ���ȭ�� �Ⱥ��̰�
				}

				//���� ���� ���̶�� �����带 �������� ����
				if (label[0] != null) {
					for (int i = 0; i < label.length; i++) {
						//�����尡 ���ư��� �ִٸ� �����ϴ� �ڵ�
						if (!new WordSetting().isAlive() || !new WordMoving().isAlive()) {
							new WordSetting().interrupt(); //�ܾ� ������ ����
							new WordMoving().interrupt();  //�������� ������ ����
						}
						//���� ���� �ܾ ������� �ִٸ� ������ �ʰ� ����
						if (label[i] != null) {
							label[i].setVisible(false);
						}
					}//for
				}//if

				//���� ���� �� ������ ����
				readyF.setVisible(false);

				//���� ���� �� �ܾ� �Է��� �ؽ�Ʈ�ʵ忡 ��Ŀ�� �ֱ�
				userInput.requestFocus();

				//�ܾ �ѷ��ִ� ������ ����
				new WordSetting().start();

			}//if
		} else if(e.getActionCommand().toString().equals("�ٽ� �ϱ�")) {
			readyF.setVisible(true);
			endF.setVisible(false);
		}
	}//actionPerformed()

	//���� Ű �̺�Ʈ ó�� : �ܾ �Է��Ͽ� ���� �ܾ� Ȯ�� �� ����
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			//�ؽ�Ʈ �ʵ忡 ���� �ִ��� ������ Ȯ��
			if (!userInput.getText().equals("")) {
				//String ������ �ؽ�Ʈ �� �ֱ�
				String answer = userInput.getText().toString();

				//����ڰ� �Է��� ���� ������ �ܾ� �ִ��� �˻�
				//���� �ܾ �ִٸ� �� ���̰� �ϰ� ī��Ʈ ���� ����
				//�˻� �� ���� ���ο� ������� �ؽ�Ʈ �ʵ忡 ����ڰ� �Է��� �� ����
				outer : for (int i = 0; i < label.length; i++) {
					try {
						//������ �ܾ� �˻�
						if (answer.equals(label[i].getText())) {
							//������ �ܾ ȭ�鿡 ������ �ִ��� �˻�
							if (label[i].isVisible()) {
								//�ϳ� ���� ������ 1���� �߰�
								++scorenum;
								//�������� ���� ����
								userScore.setText(scorenum + "��");
								//�ܾ� ȭ�鿡�� ����
								label[i].setVisible(false);
								//����ڰ� �Է��� �ؽ�Ʈ �� ���ֱ�
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


	//�ܾ �ϳ��� �Ѹ��� ������
	class WordSetting extends Thread {
		@Override
		public void run() {
			//����ȭ�� ����
			panelScreen.setVisible(true);
			//�ܾ ������ ���� �ӵ��� ���� ȭ�鿡 x�� �������� ����
			for (i = 0; i <= 10000; i++) {
				try {
					Random rnd = new Random();
					label[i] = new JLabel(word[rnd.nextInt(word.length)]);

					//������ �ܾ��� �ʱ� ��ġ ����
					label[i].setBounds(0, 0, 100, 50);
					//�ܾ� �߰�
					panelScreen.add(label[i]);
					//x�� �������� ������
					label[i].setLocation(rnd.nextInt(940), 3);

					//�ܾ �����̴� ������ ����
					new WordMoving().start();

					//���� ������ ���� �ܾ� �ӵ�
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}//for
		}//run()
	}

	//�ܾ �������� �ϰ� ���� ��� Ȯ���ϴ� ������
	class WordMoving extends Thread {
		@Override
		public void run() {
			//�ܾ� ���� ������ŭ for���� ���� �� �ܾ�� y�� ����
			for(int n = 0; n < i; n++) {
				if (label[n].isVisible()) {
					int sp = label[n].getY();
					int xp = label[n].getX();

					label[n].setLocation(xp, sp + 10);
				}//if

				//�ܾ ���� ���� �Ѿ����� Ȯ��
				if (label[n].isVisible() && label[n].getY() > 510) {
					//�ܾ ���� ������ �ܾ� �����
					label[n].setVisible(false);
					//������ 1 = ������ 1�� ����
					life++;
				}
			}//for

			switch (life) {
			//������ ������ ����
			case 0: 
				panelLife1.setForeground(Color.RED);
				panelLife2.setForeground(Color.RED);
				panelLife3.setForeground(Color.RED);
				break;
			//������ 1�� ������
			case 1: 
				panelLife1.setForeground(Color.RED);
				panelLife2.setForeground(Color.RED);
				panelLife3.setForeground(Color.DARK_GRAY);
				break;
			//������ 2�� ������
			case 2:
				panelLife1.setForeground(Color.RED);
				panelLife2.setForeground(Color.DARK_GRAY);
				panelLife3.setForeground(Color.DARK_GRAY);
				break;
			//������ 3�� ������ -> Game Over
			case 3:
				panelLife1.setForeground(Color.DARK_GRAY);
				panelLife2.setForeground(Color.DARK_GRAY);
				panelLife3.setForeground(Color.DARK_GRAY);

				//���� ���� ȭ�� ����
				panelScreen.setVisible(false);
				endF.setVisible(true);
				viewScore.setText(userScore.getText());
				
				//��ŷ�� �ö� ��¥
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
				Date time = new Date();
				date = format1.format(time);
				
				//Rain ���� �����
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
				
				//������ ����
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
			speed = 2500;// ���Ӽӵ�
			break;
		case 1:
			speed = 1700;// ���Ӽӵ�
			break;
		case 2:
			speed = 1200;// ���Ӽӵ�
			break;
		case 3:
			speed = 700;// ���Ӽӵ�
			break;
		case 4:
			speed = 200;// ���Ӽӵ�
			break;
		default:
			break;
		}//switch

		String str = e.getItem().toString();
		viewLevel.setText(str);
	}//itemStateChanged()
}
