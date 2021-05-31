package view.gamePanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextPane;
import javax.swing.border.MatteBorder;

import model.cards.minions.Minion;
import view.buttons.AttackButton;
import view.buttons.ConfirmTarget;
import controller.Controller;

@SuppressWarnings("serial")
public class MinionViewField extends JFrame implements ActionListener {
	private static ArrayList<MinionViewField> bag = new ArrayList<MinionViewField>();

	public MinionViewField(Controller c, Minion m, boolean isAttacker) {
		super();
		this.setTitle("Minion Details");
		this.setSize(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.25),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.96));
		setBounds(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.75),
				0,
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.25),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.96));
		setExtendedState(JFrame.MAXIMIZED_VERT);
		JLayeredPane layer = new JLayeredPane();
		layer.setSize(this.getSize());
		layer.setOpaque(true);
		layer.setBackground(Color.gray);
		CardIcon icon = new CardIcon(m);
		icon.setBounds((int) (getWidth() * 0.2), (int) (getHeight() * 0.01),
				icon.getWidth(), icon.getHeight());
		icon.setIcon(icon.resizeIcon(icon.getIcon(), (int) (getWidth() * 0.6),
				(int) (getHeight() * 0.4)));
		layer.add(icon, 0);
		JTextPane text = new JTextPane();
		text.setSize(new Dimension(getWidth(), (int) (getHeight() * 0.4)));
		text.setBounds(0, (int) (getHeight() * 0.5), getWidth(),
				(int) (getHeight() * 0.4));
		text.setFont(new Font("Showcard Gothic", Font.BOLD, (int) (Toolkit
				.getDefaultToolkit().getScreenSize().getHeight() / 45)));
		text.setOpaque(false);
		String s = "Name: " + m.getName() + "\nMana Cost: " + m.getManaCost()
				+ "\nRarity: " + m.getRarity() + "\nAttack: " + m.getAttack()
				+ "\nCurrent HP: " + m.getCurrentHP() + "\nTaunt: "
				+ m.isTaunt() + "\nDivine: " + m.isDivine() + "\nCharge: "
				+ m.isCharge();
		if (isAttacker) {
				AttackButton attack = new AttackButton();
				attack.addActionListener(c);
				attack.addActionListener(this);
				attack.setM(m);
				attack.setSize(new Dimension((int)(getWidth()*0.955),
						(int) (getHeight() * 0.1)));
				attack.setBounds(0, (int) (getHeight() * 0.855), (int)(getWidth()*0.955),
						(int) (getHeight() * 0.1));
				attack.setFocusable(false);
				attack.setFocusPainted(false);
				attack.setBackground(Color.white);
				attack.setBorder(new MatteBorder(3, 3, 3, 3, Color.cyan));
				attack.setFont(new Font("Chiller", Font.BOLD, (int) (Toolkit
						.getDefaultToolkit().getScreenSize().getHeight() / 35)));
				attack.setOpaque(true);
				attack.revalidate();
				attack.repaint();
				add(attack, 0);

				if (m.isSleeping())
					s += "\n \nThis minion is still sleeping.";
				if (m.isAttacked())
					s += "\n \nThis minion has already attacked.";
				if ((m.isCharge() || (!m.isSleeping())) && (!m.isAttacked()))
					s += "\n \nThis minion is ready to attack.";
		} else {
			if (c.isAttackClicked() || c.isSpellClicked() || (c.isPowerClicked()&&c.isMinion())) {
				ConfirmTarget target = new ConfirmTarget();
				target.addActionListener(c);
				target.addActionListener(this);
				target.setMinion(m);
				target.setSize(new Dimension((int)(getWidth()*0.955),
						(int) (getHeight() * 0.1)));
				target.setBounds(0, (int) (getHeight() * 0.855), (int)(getWidth()*0.955),
						(int) (getHeight() * 0.1));
				target.setFocusable(false);
				target.setFocusPainted(false);
				target.setBackground(Color.white);
				target.setBorder(new MatteBorder(3, 3, 3, 3, Color.cyan));
				target.setFont(new Font("Chiller", Font.BOLD, (int) (Toolkit
						.getDefaultToolkit().getScreenSize().getHeight() / 35)));
				target.setOpaque(true);
				target.revalidate();
				target.repaint();
				add(target, 0);
			}
		}

		text.setText(s);
		text.setEditable(false);
		text.setVisible(true);
		text.revalidate();
		text.repaint();
		text.setBackground(Color.GRAY);
		layer.add(text, 1);
		layer.setVisible(true);
		layer.revalidate();
		layer.repaint();
		this.add(layer);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.revalidate();
		this.repaint();

		bag.add(this);

	}

	public static ArrayList<MinionViewField> getBag() {
		return bag;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		setVisible(false);
		dispose();
	}

}
