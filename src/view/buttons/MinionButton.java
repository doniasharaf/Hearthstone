package view.buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolTip;

import model.cards.minions.Minion;
import view.Sound;
import view.View;
import view.gamePanels.MinionViewField;
import view.gamePanels.MinionViewHand;
import view.gamePanels.SpellView;
import controller.Controller;

@SuppressWarnings("serial")
public class MinionButton extends JButton implements ActionListener,
		MouseListener, MouseMotionListener {
	public Minion minion;
	Sound sound = new Sound();
	MinionViewHand viewHand;
	MinionViewField viewField;
	SpellView viewSpell;
	Controller c;

	public MinionButton(Controller c, Minion minion) {
		super();
		this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth() * 0.667) / 12, (int) (Toolkit.getDefaultToolkit()
				.getScreenSize().height * 0.12));
		ImageIcon i = new ImageIcon("Icons/" + minion.getName() + ".png");

		i = View.resizeIcon(i, this.getWidth(), this.getHeight());
		this.setIcon(i);
		this.setActionCommand("Show Minion");
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		this.setToolTipText("Click the minion for details.");
		setBorder(null);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setOpaque(false);
		this.addActionListener(this);
		this.setVisible(true);
		this.minion = minion;
		this.c = c;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (!MinionViewHand.getBag().isEmpty()) {
			MinionViewHand.getBag().get(0).setVisible(false);
			MinionViewHand.getBag().get(0).dispose();
			MinionViewHand.getBag().remove(0);
		}
		if (!MinionViewField.getBag().isEmpty()) {
			MinionViewField.getBag().get(0).setVisible(false);
			MinionViewField.getBag().get(0).dispose();
			MinionViewField.getBag().remove(0);
		}
		if (!SpellView.getBag().isEmpty()) {
			SpellView.getBag().get(0).setVisible(false);
			SpellView.getBag().get(0).dispose();
			SpellView.getBag().remove(0);
		}
		if (getActionCommand().equals("Show Minion"))
			viewHand = new MinionViewHand(c, minion);

		else {
			if (getActionCommand().equals("attacking"))
				viewField = new MinionViewField(c, minion, true);
			else
				viewField = new MinionViewField(c, minion, false);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		try {
			sound.myCard();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
	public JToolTip createToolTip() {
		JToolTip tooltip = super.createToolTip();
		tooltip.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		tooltip.setBackground(Color.WHITE);
		tooltip.setFont(new Font("Chiller", Font.BOLD, (int) (Toolkit
				.getDefaultToolkit().getScreenSize().getHeight() / 48)));
		tooltip.setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.1),(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.075) );
		return tooltip;
	}

}
