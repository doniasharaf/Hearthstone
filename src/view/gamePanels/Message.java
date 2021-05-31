package view.gamePanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class Message extends JFrame implements ActionListener {
	
	public Message(String s) {
		super();
		setTitle("pls");
		this.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit()
				.getScreenSize().width / 1.5), Toolkit.getDefaultToolkit()
				.getScreenSize().height / 4));
		pack();
		setExtendedState(JFrame.NORMAL);
		JLabel text = new JLabel(s, SwingConstants.CENTER);
		text.setFont(new Font("Showcard Gothic", Font.BOLD, (int) (Toolkit
				.getDefaultToolkit().getScreenSize().getHeight() / 40)));
		this.add(text, BorderLayout.CENTER);
		this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2
				- this.getWidth() / 2, Toolkit.getDefaultToolkit()
				.getScreenSize().height / 2 - this.getHeight() / 2);
		setVisible(true);
		JButton j = new JButton();	
		j.setSize((int)(this.getWidth()*0.98),(int)(this.getHeight()*0.1));
		j.addActionListener(this);
		j.setFocusable(false);
		j.setFocusPainted(false );
		j.setBackground(Color.white);
		j.setBorder(new MatteBorder(3, 3, 3, 3, Color.cyan));
		j.setFont(new Font("Chiller", Font.BOLD, (int) (Toolkit
				.getDefaultToolkit().getScreenSize().getHeight() / 44)));
		j.setText("Ok");
		add(j, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		revalidate();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		dispose();
	}
}
