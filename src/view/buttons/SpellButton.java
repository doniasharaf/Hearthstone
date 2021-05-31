package view.buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolTip;

import model.cards.spells.Spell;
import view.Sound;
import view.View;
import view.gamePanels.MinionViewField;
import view.gamePanels.MinionViewHand;
import view.gamePanels.SpellView;
import controller.Controller;

@SuppressWarnings("serial")
public class SpellButton extends JButton implements ActionListener,
		MouseListener {
	public Spell spell;
	MinionViewHand viewHand;
	MinionViewField viewField;
	SpellView viewSpell;
	Controller c;
	private Sound sound = new Sound();

	public SpellButton(Controller c, Spell spell) {
		super();
		this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth() * 0.667) / 12, (int) (Toolkit.getDefaultToolkit()
				.getScreenSize().height * 0.12));
		ImageIcon i = new ImageIcon("Icons/" + spell.getName() + ".png");
		i = View.resizeIcon(i, this.getWidth(), this.getHeight());
		this.setIcon(i);

		this.setActionCommand("Show Spell");

		this.setToolTipText("Click the spell for details.");
		setBorder(null);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setOpaque(false);
		this.addActionListener(this);
		addMouseListener(this);
		this.setVisible(true);
		this.spell = spell;
		this.c = c;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (!MinionViewHand.getBag().isEmpty()) {
			MinionViewHand.getBag().get(0).dispose();
			MinionViewHand.getBag().remove(0);
		}
		if (!MinionViewField.getBag().isEmpty()) {
			MinionViewField.getBag().get(0).dispose();
			MinionViewField.getBag().remove(0);
		}
		if (!SpellView.getBag().isEmpty()) {
			SpellView.getBag().get(0).dispose();
			SpellView.getBag().remove(0);
		}
		viewSpell = new SpellView(c, spell);
	}

	public JToolTip createToolTip() {
		JToolTip tooltip = super.createToolTip();
		tooltip.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		tooltip.setBackground(Color.WHITE);
		tooltip.setFont(new Font("Chiller", Font.BOLD, (int) (Toolkit
				.getDefaultToolkit().getScreenSize().getHeight() / 48)));
		tooltip.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth() * 0.1), (int) (Toolkit.getDefaultToolkit()
				.getScreenSize().getHeight() * 0.075));
		return tooltip;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		try {
			sound.myCard();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

}
