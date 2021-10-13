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

		//������
		Frame f = new Frame("Ÿ�ڰ���");
		f.setSize(1200, 900);
		f.setLocationRelativeTo(null); //�������� ������� �߾ӿ� ��ġ�ϰ� ��
		f.setLayout(null);

		//Ÿ��Ʋ �� �޴� ��Ʈ ����
		Font font_50b = new Font("���� ���", Font.BOLD, 50);
		Font font_40b = new Font("���� ���", Font.BOLD, 40);

		//��� �̹��� ����
		ImageIcon img = new ImageIcon("gray_bg.jpg");
		JLabel bgImg = new JLabel(img);
		bgImg.setBounds(0, 0, 1920, 1050);

		//Ÿ��Ʋ ��
		JLabel title = new JLabel("Typing Game");
		title.setBounds(450, 100, 500, 80);
		title.setForeground(Color.DARK_GRAY);
		title.setFont(font_50b);

		//��ư �߰�
		JButton btnStart = new JButton("����");
		JButton btnRank = new JButton("��ŷ");
		JButton btnExit = new JButton("����");

		btnStart.setBounds(460, 250, 300, 150);
		btnRank.setBounds(460, 430, 300, 150);
		btnExit.setBounds(460, 610, 300, 150);

		btnStart.setFont(font_40b);
		btnRank.setFont(font_40b);
		btnExit.setFont(font_40b);

		//ButtonEffect �޼����� ��ư ���� �߰�
		new ButtonEffect(btnStart);
		new ButtonEffect(btnRank);
		new ButtonEffect(btnExit);
		
		//���� ��ư�� �̺�Ʈ ó�� : �α��� ȭ���� �� â���� ���
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FramesMain fm = new FramesMain();
			}
		});

		//��ŷ ��ư�� �̺�Ʈ ó�� : ��Ŀ ����� ������
		btnRank.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Ranking rank = new Ranking();
				rank.ranking();
			}
		});

		//���� ��ư�� �̺�Ʈ ó�� : �˾�â�� �� �� �� ��� �� ������� �ǻ翡 ���� ����
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int exitOption = JOptionPane.showConfirmDialog
					(null, "������ �����Ͻðڽ��ϱ�?", "���α׷� ����", JOptionPane.YES_NO_OPTION);
				
				if(exitOption == JOptionPane.YES_OPTION) {
					System.exit(0);
				} else {
					return;
				}
			}
		});

		//��ҵ� �����ӿ� �߰�
		f.add(title);

		f.add(btnStart);
		f.add(btnRank);
		f.add(btnExit);

		f.add(bgImg);

		//������ �ݱ� ��ư�� �̺�Ʈ �ְ� ����
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int exitOption = JOptionPane.showConfirmDialog
					(null, "������ �����Ͻðڽ��ϱ�?", "���α׷� ����", JOptionPane.YES_NO_OPTION);
				
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
