package view.gamePanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextPane;

import model.heroes.Hero;

@SuppressWarnings("serial")
public class DetailsLeft extends JTextPane {

	public DetailsLeft(Hero p1, Hero p2) {
		super();
		this.setBounds(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.05),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.4),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.3),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.1));
		String s =  p1.getDeck().size()
				+ " / 20" + "\n" + "\n" + p2.getDeck().size() + " / 20";
		this.setFont(new Font("Castellar", Font.BOLD, (int) (Toolkit
				.getDefaultToolkit().getScreenSize().getHeight() / 48)));
		this.setForeground(Color.CYAN);
		this.setText(s);
		this.setEditable(false);
		setOpaque(false);
		setVisible(true);
		revalidate();
		repaint();
	}
}
