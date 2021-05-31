package view.buttons;

import javax.swing.JButton;

import model.cards.minions.Minion;

@SuppressWarnings("serial")
public class ConfirmTarget extends JButton {
	private Minion minion;

	public ConfirmTarget() {
		super();
		setActionCommand("confirm target");
		setText("Confirm Target");
		setVisible(true);
		revalidate();
		repaint();
	}

	public Minion getMinion() {
		return minion;
	}

	public void setMinion(Minion minion) {
		this.minion = minion;
	}

}
