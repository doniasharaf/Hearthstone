package view.gamePanels;

import java.awt.Toolkit;

import javax.swing.ImageIcon;

import view.View;

@SuppressWarnings("serial")
public class MinionLabelTwo extends MinionLabelOne {
	public MinionLabelTwo() {
		super();
		ImageIcon minion = new ImageIcon("Icons/mai loves u.png");
		minion=View.resizeIcon(minion, this.getWidth(), this.getHeight());
		this.setIcon(minion);
		this.setBounds(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.025),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.66),
				this.getWidth(), this.getHeight());
		this.revalidate();
		this.repaint();
	}

}
