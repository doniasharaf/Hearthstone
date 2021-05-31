package view.gamePanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextArea;

import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;

@SuppressWarnings("serial")
public class HeroPowerDescription extends JTextArea {
	public HeroPowerDescription(Hero h, int n) {
		super();
		this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth() * 0.1), (int) (Toolkit.getDefaultToolkit()
				.getScreenSize().getHeight() * 0.2));
		this.setFont(new Font("Chiller", Font.BOLD, (int) (Toolkit
				.getDefaultToolkit().getScreenSize().getHeight() / 48)));
		this.setForeground(Color.white);
		this.setOpaque(false);
		if (h instanceof Hunter) {
			this.setText("Inflict 2 damage to your opponent hero.");
		}
		if (h instanceof Mage) {
			this.setText("Inflict 1 damage to any target.");
		}
		if (h instanceof Priest) {
			this.setText("Restore 2 health points to any target.");
		}
		if (h instanceof Warlock) {
			this.setText("Draw an extra card and inflict 2 damage to your hero.");
		}
		if (h instanceof Paladin) {
			this.setText("Create a new minion and add it to your field.");
		}
		if (n == 1) {
			this.setBounds(
					(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.11),
					(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.33),
					this.getWidth(), this.getHeight());
		} else {
			this.setBounds(
					(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.11),
					(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.5),
					this.getWidth(), this.getHeight());

		}
		this.setLineWrap(true);
		this.setWrapStyleWord(true);
		this.setEditable(false);
		this.setVisible(true);
		this.revalidate();
		this.repaint();

	}
}
