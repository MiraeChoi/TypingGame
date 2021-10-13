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
		System.out.println("������ ����� : " + id);
		
		//������
		Frame f = new Frame("Ÿ�ڰ���");
		f.setSize(1200, 900);
		f.setLocationRelativeTo(null); //�������� ������� �߾ӿ� ��ġ�ϰ� ��
		f.setLayout(null);

		//Ÿ��Ʋ �� �޴� ��Ʈ ����
		Font font_50b = new Font("���� ���", Font.BOLD, 50);
		Font font_40b = new Font("���� ���", Font.BOLD, 40);
		Font font_20b = new Font("���� ���", Font.BOLD, 20);

		//��� �̹��� ����
		ImageIcon img = new ImageIcon("gray_bg.jpg");
		JLabel bgImg = new JLabel(img);
		bgImg.setBounds(0, 0, 1920, 1050);

		//Ÿ��Ʋ ��
		JLabel title = new JLabel("���� ����");
		title.setBounds(470, 100, 500, 80);
		title.setForeground(Color.DARK_GRAY);
		title.setFont(font_50b);

		f.add(title);

		//��ư �߰�(���� ����, �꼺��, Ȩ����)
		JButton btnSentence = new JButton("���� ����");
		JButton btnRain = new JButton("�꼺��");
		JButton btnHome = new JButton("Ȩ����");

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

		//���忬�� ��ư�� �̺�Ʈ ó��
		btnSentence.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SentenceView sv = new SentenceView();
				sv.gameSentenceView(id);
			}
		});
		
		//�꼺�� ��ư�� �̺�Ʈ ó��
		btnRain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RainView rv = new RainView();
				rv.gameRainView(id);
			}
		});
		
		//Ȩ���� ��ư�� �̺�Ʈ ó��
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
