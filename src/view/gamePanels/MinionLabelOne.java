package view.gamePanels;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import view.View;

@SuppressWarnings("serial")
public class MinionLabelOne extends JLabel {
	public MinionLabelOne() {
		super();
		this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth() * 0.667) / 12, (int) (Toolkit.getDefaultToolkit()
				.getScreenSize().height * 0.1));
		ImageIcon minion = new ImageIcon("Icons/minion.png");
		minion=View.resizeIcon(minion, this.getWidth(), this.getHeight());
		this.setOpaque(false);
		this.setIcon(minion);
		this.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.025),  (int) (Toolkit.getDefaultToolkit()
				.getScreenSize().height * 0.13), this.getWidth(), this.getHeight());
		this.setVisible(true);
		this.revalidate();
		this.repaint();
		
	}
}
