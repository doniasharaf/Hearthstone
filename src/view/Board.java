package view;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class Board extends JLayeredPane {
	JPanel j = new JPanel();

	public Board() {
		super();
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		ImageIcon icon = new ImageIcon("Icons/board.jpg");
		icon = View.resizeIcon(icon, Toolkit.getDefaultToolkit()
				.getScreenSize().width, Toolkit.getDefaultToolkit()
				.getScreenSize().height);
		JLabel background = new JLabel(icon);
		background.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.add(background, 0);
		JLabel side1 = new JLabel();
		side1.setOpaque(false);
		Border b = BorderFactory.createMatteBorder(0, 0, 4, 0, Color.BLACK);

		side1.setBorder(b);
		side1.setBounds(0, 0, (int) (Toolkit.getDefaultToolkit()
				.getScreenSize().width), (int) (Toolkit.getDefaultToolkit()
				.getScreenSize().height * 0.445));

		this.add(side1, 0);

		JLabel manaLabel1 = new JLabel();
		JLabel manaLabel2 = new JLabel();
		JLabel healthLabel1 = new JLabel();
		JLabel healthLabel2 = new JLabel();
		ImageIcon mana = new ImageIcon("Icons/mana2.png");
		ImageIcon health = new ImageIcon("Icons/health.png");
		mana = View.resizeIcon(mana, (int) (Toolkit.getDefaultToolkit()
				.getScreenSize().width * 0.035), (int) (Toolkit
				.getDefaultToolkit().getScreenSize().height * 0.035));
		health = View.resizeIcon(health, (int) (Toolkit.getDefaultToolkit()
				.getScreenSize().width * 0.025), (int) (Toolkit
				.getDefaultToolkit().getScreenSize().height * 0.03));
		manaLabel1.setIcon(mana);

		healthLabel1.setIcon(health);

		manaLabel2.setIcon(mana);

		healthLabel2.setIcon(health);

		healthLabel1
				.setBounds(
						(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.66),
						(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.013),
						(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.025),
						(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.03));
		manaLabel1
				.setBounds(
						(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.655),
						(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.063),
						(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.035),
						(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.035));
		healthLabel2
				.setBounds(
						(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.66),
						(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.8),
						(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.025),
						(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.03));
		manaLabel2
				.setBounds(
						(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.655),
						(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.85),
						(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.035),
						(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.035));
		manaLabel1.setOpaque(false);
		healthLabel1.setOpaque(false);
		manaLabel2.setOpaque(false);
		healthLabel2.setOpaque(false);

		JLabel deck1 = new JLabel();
		deck1.setSize(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.05),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.045));
		JLabel deck2 = new JLabel();
		deck2.setSize(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.05),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.045));
		ImageIcon deck = new ImageIcon("Icons/deck.png");
		deck = View.resizeIcon(deck, (int) (Toolkit.getDefaultToolkit()
				.getScreenSize().width * 0.05), (int) (Toolkit
				.getDefaultToolkit().getScreenSize().height * 0.045));
		deck1.setIcon(deck);
		deck2.setIcon(deck);
		deck1.setBounds(
				0,
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.3975),
				deck1.getWidth(), deck1.getHeight());
		deck2.setBounds(
				0,
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.45),
				deck2.getWidth(), deck2.getHeight());
		deck1.setOpaque(false);
		deck2.setOpaque(false);

		this.add(manaLabel1, 0);
		this.add(manaLabel2, 0);
		this.add(healthLabel2, 0);
		this.add(healthLabel1, 0);
		this.add(deck1, 0);
		this.add(deck2, 0);

		this.setVisible(false);

	}

	public void setDescription(JPanel minion) {
		j.add(minion);
	}

}
