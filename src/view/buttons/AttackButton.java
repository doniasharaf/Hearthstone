package view.buttons;

import javax.swing.JButton;

import model.cards.minions.Minion;

@SuppressWarnings("serial")
public class AttackButton extends JButton {

	private Minion m;

	public AttackButton() {
		super();
		setActionCommand("Attack");
		setText("Attack");
		setVisible(true);
		revalidate();
		repaint();

	}

	public Minion getM() {
		return m;
	}

	public void setM(Minion m) {
		this.m = m;
	}

}
