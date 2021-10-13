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
		//������
		Frame f = new Frame("Ÿ�ڰ���");
		f.setSize(1200, 900);
		f.setLocationRelativeTo(null); //�������� ������� �߾ӿ� ��ġ�ϰ� ��
		f.setLayout(null);

		//Ÿ��Ʋ �� �޴� ��Ʈ ����
		Font font_50b = new Font("���� ���", Font.BOLD, 50);
		Font font_20b = new Font("���� ���", Font.BOLD, 20);
		Font font_18p = new Font("���� ���", Font.BOLD, 18);

		//��� �̹��� ����
		ImageIcon img = new ImageIcon("gray_bg.jpg");
		JLabel bgImg = new JLabel(img);
		bgImg.setBounds(0, 0, 1920, 1050);

		//Ÿ��Ʋ ��
		JLabel title = new JLabel("��ŷ");
		title.setBounds(550, 100, 500, 80);
		title.setForeground(Color.DARK_GRAY);
		title.setFont(font_50b);

		f.add(title);

		//---------------------------------------------------------
		//��ư �߰�(���� ����, �꼺��)
		JButton btnSentence = new JButton("���� ����");
		JButton btnRain = new JButton("�꼺��");

		btnSentence.setBounds(150, 200, 300, 70);
		btnRain.setBounds(750, 200, 300, 70);

		btnSentence.setFont(font_20b);
		btnRain.setFont(font_20b);
		
		new ButtonEffect(btnSentence);
		new ButtonEffect(btnRain);

		//ª�� �� ���� - ��ư �߰�(����, ���̵�, ���, ��¥)
		JButton btnRank = new JButton("����");
		JButton btnName = new JButton("���̵�");
		JButton btnScore = new JButton("���");
		JButton btnDate = new JButton("��¥");

		btnRank.setBounds(80, 300, 100, 60);
		btnName.setBounds(190, 300, 100, 60);
		btnScore.setBounds(300, 300, 100, 60);
		btnDate.setBounds(410, 300, 100, 60);

		btnRank.setFont(font_20b);
		btnName.setFont(font_20b);
		btnScore.setFont(font_20b);
		btnDate.setFont(font_20b);

		//ButtonEffect �޼����� ��ư ���� �߰�
		new ButtonEffect(btnRank);
		new ButtonEffect(btnName);
		new ButtonEffect(btnScore);
		new ButtonEffect(btnDate);

		//���� �Է� ��Ŀ�� ȭ�鿡 �������� ��----------------------------------------------------------
		JLabel rankS, idS, scoreS, dateS;

		UserInfo_S uis;
		ArrayList<UserInfo_S> sArr = new ArrayList<>();
		ArrayList<Integer> tempS = new ArrayList<>();
		//������ ������ ���� �迭
		int[] orderS = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		//������ ������ ������� ��Ÿ�� �迭
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
				nothing.setText("���� ����� �����ϴ�.");
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

		//���� �� ��� ��, �� ���� ������ �Է��� ����� ���� �ε����� ��ŷ���� 1�� �߰���(ex : 1�� -> 2��)
		for(int i = 0; i < tempS.size(); i++) {
			for(int j = 0; j < tempS.size(); j++) {
				if(tempS.get(j) > tempS.get(i)) {
					orderS[i]++;
				}
			}//inner
		}//outer

		//1����� ���ʴ�� ���� ����
		for(int i = 0; i < tempS.size(); i++) {
			indexS[orderS[i] - 1] = i;
		}//for

		//��ŷ�� ��ϵ� �� �� ����
		int spacing = 40;

		//�� ����
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

			rankS.setText(orderS[r] + "��");
			idS.setText(sArr.get(r).getId());
			scoreS.setText(sArr.get(r).getScore() + "��");
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
		//���� �Է� ��� �� ��----------------------------------------------------------

		//�꼺�� - ��ư �߰�(����, ���̵�, ���, ��¥)
		JButton btnRank2 = new JButton("����");
		JButton btnName2 = new JButton("���̵�");
		JButton btnScore2 = new JButton("���");
		JButton btnDate2 = new JButton("��¥");

		btnRank2.setBounds(670, 300, 100, 60);
		btnName2.setBounds(780, 300, 100, 60);
		btnScore2.setBounds(900, 300, 100, 60);
		btnDate2.setBounds(1010, 300, 100, 60);

		btnRank2.setFont(font_20b);
		btnName2.setFont(font_20b);
		btnScore2.setFont(font_20b);
		btnDate2.setFont(font_20b);

		//ButtonEffect �޼����� ��ư ���� �߰�
		new ButtonEffect(btnRank2);
		new ButtonEffect(btnName2);
		new ButtonEffect(btnScore2);
		new ButtonEffect(btnDate2);

		//�꼺�� ��� ��Ŀ�� ȭ�鿡 �������� ��----------------------------------------------------------
		//����� 1���̶�� 1�� ������, 3���̶�� 3�� �ִ� 5����� �� ����
		JLabel rankR, idR, scoreR, dateR;

		UserInfo_R uir;
		ArrayList<UserInfo_R> rArr = new ArrayList<>();
		ArrayList<Integer> tempR = new ArrayList<>();
		//������ ������ ���� �迭
		int[] orderR = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		//������ ������ ������� ��Ÿ�� �迭
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
				nothing.setText("���� ����� �����ϴ�.");
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

		//���� �� ��� ��, �� ���� ������ �Է��� ����� ���� �ε����� ��ŷ���� 1�� �߰���(ex : 1�� -> 2��)
		for(int i = 0; i < tempR.size(); i++) {
			for(int j = 0; j < tempR.size(); j++) {
				if(tempR.get(j) > tempR.get(i)) {
					orderR[i]++;
				}
			}//inner
		}//outer

		//1����� ���ʴ�� ���� ����
		for(int i = 0; i < tempR.size(); i++) {
			indexR[orderR[i] - 1] = i;
		}//for

		//�� ����
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

			rankR.setText(orderR[r] + "��");
			idR.setText(rArr.get(r).getId());
			scoreR.setText(rArr.get(r).getScore() + "��");
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
		//�꼺�� ��� �� ��----------------------------------------------------------
		
		//��ư �߰�(Ȩ����)
		JButton btnHome = new JButton("Ȩ����");
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

		//��ҵ� �����ӿ� �߰�
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

		//������ �ݱ� ��ư�� �̺�Ʈ �ְ� ����
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				f.dispose();
			}
		});

		f.setVisible(true);
	}

}
