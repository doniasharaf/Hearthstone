package view.gamePanels;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.cards.Card;

@SuppressWarnings("serial")
public class CardIcon extends JLabel {
	private ImageIcon icon;

	public ImageIcon getIcon() {
		return icon;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public CardIcon(Card c) {
		icon = new ImageIcon("Icons/" + c.getName() + ".png");
		
		icon = resizeIcon(icon, (int) (Toolkit.getDefaultToolkit()
				.getScreenSize().width * 0.25), (int) (Toolkit
				.getDefaultToolkit().getScreenSize().height * 0.48));
		setIcon(icon);
		setBounds(
				0,
				0,
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.25),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.48));
		setBackground(Color.GRAY);
		setOpaque(true);
		setVisible(true);
		revalidate();
		repaint();
	}

	public ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
		Image image = icon.getImage();
		Image resizedImage = image.getScaledInstance(width, height,
				java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}
}
