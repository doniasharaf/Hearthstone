package view.buttons;

import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import view.Sound;
import view.View;

@SuppressWarnings("serial")
public class CardBack extends JButton implements MouseListener {
	Sound sound = new Sound();

	public CardBack() {
		super();
		this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth() * 0.667) / 12, (int) (Toolkit.getDefaultToolkit()
				.getScreenSize().height * 0.12));
		ImageIcon i = new ImageIcon("Icons/backcard.png");
		i = View.resizeIcon(i, this.getWidth(), this.getHeight());
		this.setIcon(i);
		this.setActionCommand("Show Minion");

		setBorder(null);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setOpaque(false);
		this.addMouseListener(this);
		this.setVisible(true);

		setVisible(true);
		revalidate();
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		try {
			sound.cardBack();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

}
