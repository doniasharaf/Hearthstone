package view.gamePanels;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JLabel;

import model.cards.minions.Minion;
import view.buttons.MinionButton;
import controller.Controller;

@SuppressWarnings("serial")
public class FieldPanelTwo extends JLabel {
	Controller c;

	public FieldPanelTwo(Controller c, ArrayList<Minion> field,
			boolean isAttacker) {
		super();
		this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth() * 0.667), (int) (Toolkit.getDefaultToolkit()
				.getScreenSize().height * 0.12));
		this.setBounds(
				0,
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.65),
				this.getWidth(), this.getHeight());
		this.setLayout(new FlowLayout());
		for (int i = 0; i < field.size(); i++) {

			if (isAttacker) {
				MinionButton button = new MinionButton(c, field.get(i));
				button.setActionCommand("attacking");
				this.add(button);
			} else {
				MinionButton button = new MinionButton(c, field.get(i));
				button.setActionCommand("target");
				this.add(button);
			}

		}

		this.setOpaque(false);
		setVisible(true);
		validate();
		repaint();
	}
}
