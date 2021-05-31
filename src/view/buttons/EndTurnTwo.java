package view.buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

import view.Sound;

@SuppressWarnings("serial")
public class EndTurnTwo extends JButton implements MouseListener {
	Sound sound = new Sound();

	public EndTurnTwo() {
		super();
		setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.1),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.075));
		this.setFont(new Font("Castellar", Font.BOLD, (int) (Toolkit
				.getDefaultToolkit().getScreenSize().getHeight() / 50)));
		setText("End Turn");
		this.setForeground(Color.white);
		setBounds(
				0,
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.575),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.1),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.075));
		Border b = BorderFactory.createLineBorder(Color.CYAN);
		this.addMouseListener(this);
		setBorder(b);
		setBorderPainted(true);
		setContentAreaFilled(false);
		setOpaque(false);
		setFocusable(false);
		setFocusPainted(false);
		setActionCommand("end turn");
		setVisible(true);
		revalidate();
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setForeground(Color.CYAN);

	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setForeground(Color.white);

	}

}
