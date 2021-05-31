package view.buttons;

import javax.swing.JButton;

import model.cards.minions.Minion;

@SuppressWarnings("serial")
public class SummonButton extends JButton{
	private Minion m;
	public SummonButton(){
		super();
		setActionCommand("Summon");
		setText("Summon");
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
