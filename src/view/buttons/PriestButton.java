package view.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class PriestButton extends HeroButton implements ActionListener {
	public PriestButton() {
		setName("Priest");
		ImageIcon priest = new ImageIcon("Icons/Priest.png");
		setIcon(priest);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		super.actionPerformed(arg0);
		ArrayList<HeroButton> ref = getBag();
		for (int i = 0; i < ref.size(); i++) {
			if (!(ref.get(i) instanceof PriestButton)
					&& (ref.get(i).getActionCommand().equals("Selected"))
					|| ref.get(i).getActionCommand().equals("Error")) {
				if (ref.get(i) instanceof HunterButton) {
					ref.get(i).setIcon(new ImageIcon("Icons/Hunter.png"));
					ref.get(i).setActionCommand("this");
				}
				if (ref.get(i) instanceof MageButton) {
					ref.get(i).setIcon(new ImageIcon("Icons/Mage.png"));
					ref.get(i).setActionCommand("this");
				}
				if (ref.get(i) instanceof PaladinButton) {
					ref.get(i).setIcon(new ImageIcon("Icons/Paladin.png"));
					ref.get(i).setActionCommand("this");
				}
				if (ref.get(i) instanceof WarlockButton) {
					ref.get(i).setIcon(new ImageIcon("Icons/Warlock.png"));
					ref.get(i).setActionCommand("this");
				}
				if (ref.get(i) instanceof ConfirmButton) {
					ref.get(i).setIcon(new ImageIcon("Icons/Confirm.png"));
					ref.get(i).setActionCommand("this");
				}
			}
		}

		if (getActionCommand().equals("this")) {
			setIcon(new ImageIcon("Icons/Pri Sel.png"));
			setActionCommand("Selected");
		} else {
			if (getActionCommand().equals("Selected")) {
				setIcon(new ImageIcon("Icons/Priest.png"));
				setActionCommand("this");
			}
		}
	}

}
