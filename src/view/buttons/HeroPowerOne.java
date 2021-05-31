package view.buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class HeroPowerOne extends JButton{
	public HeroPowerOne() {
		super();
		setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.1),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.075));
		setBounds(
				0,
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.325),
				this.getWidth(), this.getHeight());
		this.setFont(new Font("Castellar", Font.BOLD, (int) (Toolkit
				.getDefaultToolkit().getScreenSize().getHeight() / 62)));
		setText("Use Hero Power");
		this.setForeground(Color.white);
		Border b = BorderFactory.createLineBorder(Color.CYAN);
		setBorder(b);
		setBorderPainted(true);
		setContentAreaFilled(false);
		setFocusable(false);
		setFocusPainted(false);
		setOpaque(false);
		setVisible(true);
		revalidate();
		repaint();
	}

	
}
