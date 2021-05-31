package view.buttons;

import javax.swing.JButton;

import model.cards.spells.Spell;
import view.Sound;

@SuppressWarnings("serial")
public class CastSpell extends JButton{
	private Spell spell;
	Sound sound = new Sound();
	public CastSpell() {
		super();
		setActionCommand("cast spell");
		setText("Cast Spell");
		setVisible(true);
		revalidate();
		repaint();
	}

	public Spell getSpell() {
		return spell;
	}

	public void setSpell(Spell spell) {
		this.spell = spell;
	}


}
