package view.gamePanels;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import view.View;

@SuppressWarnings("serial")
public class SpeechOne extends JLabel {
	public SpeechOne(String s) {
		super();
		this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth() * 0.667) / 10, (int) (Toolkit.getDefaultToolkit()
				.getScreenSize().height * 0.1));
		ImageIcon minion;
		if (s.equals("banana")) {
			minion = new ImageIcon("Icons/bubblespeech.png");

		} else {
			minion = new ImageIcon("Icons/yay.png");
		}
		minion = View.resizeIcon(minion, this.getWidth(), this.getHeight());
		this.setOpaque(false);
		this.setIcon(minion);
		this.setBounds(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.025),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.03),
				this.getWidth(), this.getHeight());
		this.setVisible(true);
		this.revalidate();
		this.repaint();
	}
}
