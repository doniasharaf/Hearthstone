package view.buttons;

import java.awt.Toolkit;

@SuppressWarnings("serial")
public class HeroPowerTwo extends HeroPowerOne {
	public HeroPowerTwo() {
		super();
		setBounds(
				0,
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.5),
				this.getWidth(), this.getHeight());
		revalidate();
		repaint();
	}

}
