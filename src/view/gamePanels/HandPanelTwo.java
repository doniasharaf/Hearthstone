package view.gamePanels;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JLabel;

import model.cards.Card;
import model.cards.minions.Minion;
import model.cards.spells.Spell;
import view.buttons.CardBack;
import view.buttons.MinionButton;
import view.buttons.SpellButton;
import controller.Controller;

@SuppressWarnings("serial")
public class HandPanelTwo extends JLabel {
	Controller c;

	public HandPanelTwo(Controller c, ArrayList<Card> hand, Boolean b) {
		super();
		this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth() * 0.667), (int) (Toolkit.getDefaultToolkit()
				.getScreenSize().height * 0.12));
		this.setBounds(
				0,
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.8),
				this.getWidth(), this.getHeight());
		this.setLayout(new FlowLayout());
		if (b == true) {
			for (int i = 0; i < hand.size(); i++) {
				if (hand.get(i) instanceof Minion) {
					this.add(new MinionButton(c, (Minion) hand.get(i)));
				} else
					this.add(new SpellButton(c, (Spell) hand.get(i)));
			}
		} else {
			for (int i = 0; i < hand.size(); i++)
				this.add(new CardBack());
		}

		this.setOpaque(false);
		setVisible(true);
		validate();
		repaint();
	}

}
