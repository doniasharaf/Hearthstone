package view.gamePanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.MatteBorder;

import view.View;

@SuppressWarnings("serial")
public class Instructions extends JFrame implements ActionListener {
	public Instructions() {
		super();
		setTitle("Getting Started");
		this.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit()
				.getScreenSize().width / 2.5), (int) (Toolkit
				.getDefaultToolkit().getScreenSize().width / 3)));
		pack();
		setExtendedState(JFrame.NORMAL);
		JLayeredPane layer = new JLayeredPane();
		layer.setSize(this.getSize());

		ImageIcon icon = new ImageIcon("Icons/realinstructions.png");
		icon = View.resizeIcon(icon, (int)(this.getWidth()*0.98), this.getHeight()
				- (int) (this.getHeight() * 0.11));

		JLabel label = new JLabel();
		label.setSize(icon.getIconWidth(), icon.getIconHeight());
		label.setIcon(icon);
		label.setBounds(0, 0, label.getWidth(), label.getHeight());
		label.setOpaque(true);
		label.revalidate();
		label.repaint();
		layer.add(label, 1);
		layer.setOpaque(true);
		layer.revalidate();
		layer.repaint();

		
		JButton j = new JButton();
		j.setSize(this.getWidth(), (int) (this.getHeight() * 0.1));
		j.addActionListener(this);
		j.setFocusable(false);
		j.setFocusPainted(false);
		j.setBackground(Color.white);
		j.setBorder(new MatteBorder(3, 3, 3, 3, Color.cyan));
		j.setFont(new Font("Showcard Gothic", Font.BOLD, (int) (Toolkit
				.getDefaultToolkit().getScreenSize().getHeight() / 44)));
		j.setText("Let's Go");
		this.add(layer);
		add(j, BorderLayout.SOUTH);

		this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2
				- this.getWidth() / 2, Toolkit.getDefaultToolkit()
				.getScreenSize().height / 2 - this.getHeight() / 2);
		setVisible(true);
		revalidate();
		repaint();
	}

	public void actionPerformed(ActionEvent arg0) {
		dispose();
	}

}
