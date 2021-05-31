package view;

import java.awt.GridLayout;

import javax.swing.JPanel;

import view.buttons.ConfirmButton;
import view.buttons.HunterButton;
import view.buttons.MageButton;
import view.buttons.PaladinButton;
import view.buttons.PriestButton;
import view.buttons.WarlockButton;

@SuppressWarnings("serial")
public class HeroSelectionPanel extends JPanel  {
	private ConfirmButton Sel = new ConfirmButton();
	public HeroSelectionPanel() {
		super();
		this.setLayout(new GridLayout(2, 3));
		HunterButton Hun = new HunterButton();
		this.add(Hun);
		MageButton Mag = new MageButton();
		this.add(Mag);
		PaladinButton Pal = new PaladinButton();
		this.add(Pal);
		PriestButton Pri = new PriestButton();
		this.add(Pri);
		WarlockButton War = new WarlockButton();
		this.add(War);
		this.add(Sel);
		Sel.setActionCommand("Confirm");
		setVisible(true);
		revalidate();
		repaint();
	}

	
	
	

	public ConfirmButton getSel() {
		return Sel;
	}

	public static void main(String[] args) {
	
	}

	
}
