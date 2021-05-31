package view.gamePanels;

import java.awt.Toolkit;

@SuppressWarnings("serial")
public class SpeechTwo extends SpeechOne {
	public SpeechTwo(String s) {
		super(s);
		this.setBounds(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.025),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.56),
				this.getWidth(), this.getHeight());
		this.revalidate();
		this.repaint();
	}

}
