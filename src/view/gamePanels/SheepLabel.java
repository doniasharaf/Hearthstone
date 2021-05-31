package view.gamePanels;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import view.View;

@SuppressWarnings("serial")
public class SheepLabel extends JLabel {
	public SheepLabel() {
		super();
		ImageIcon sheep = new ImageIcon("Icons/realsheep.png");
		sheep = View.resizeIcon(sheep, (int) (Toolkit.getDefaultToolkit()
				.getScreenSize().getWidth() / 2), (int) (Toolkit
				.getDefaultToolkit().getScreenSize().getHeight() / 2));
		this.setOpaque(false);
		this.setIcon(sheep);
		this.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth() / 2 - sheep.getIconWidth() / 2), (int) (Toolkit
				.getDefaultToolkit().getScreenSize().getHeight() / 2 - sheep
				.getIconHeight() / 2), sheep.getIconWidth(), sheep
				.getIconHeight());
		this.setVisible(true);
		this.revalidate();
		this.repaint();

	}
}
