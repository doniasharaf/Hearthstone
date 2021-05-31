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
import view.buttons.SummonButton;
import controller.Controller;

@SuppressWarnings("serial")
public class MinionViewHand extends JFrame implements ActionListener {
	private static ArrayList<MinionViewHand> bag = new ArrayList<MinionViewHand>();

	public MinionViewHand(Controller c, Minion m) {
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
		text.setBounds(0, (int) (getHeight() * 0.6), getWidth(),
				(int) (getHeight() * 0.4));
		text.setFont(new Font("Showcard Gothic", Font.BOLD, (int) (Toolkit
				.getDefaultToolkit().getScreenSize().getHeight() / 45)));
		text.setOpaque(false);
		String s = "Name: " + m.getName() + "\nMana Cost: " + m.getManaCost()
				+ "\nRarity: " + m.getRarity() + "\nAttack: " + m.getAttack()
				+ "\nCurrent HP: " + m.getCurrentHP() + "\nTaunt: "
				+ m.isTaunt() + "\nDivine: " + m.isDivine() + "\nCharge: "
				+ m.isCharge();
		text.setText(s);
		text.setEditable(false);
		text.setVisible(true);
		text.revalidate();
		text.repaint();
		text.setBackground(Color.GRAY);
		SummonButton summon = new SummonButton();
		summon.addActionListener(c);
		summon.addActionListener(this);
		summon.setM(m);
		summon.setSize(new Dimension((int)(getWidth()*0.955),
				(int) (getHeight() * 0.1)));
		summon.setBounds(0, (int) (getHeight() * 0.855), (int)(getWidth()*0.955),
				(int) (getHeight() * 0.1));
		summon.setFocusable(false);
		summon.setFocusPainted(false);
		summon.setBackground(Color.white);
		summon.setBorder(new MatteBorder(3, 3, 3, 3, Color.cyan));
		summon.setFont(new Font("Chiller", Font.BOLD, (int) (Toolkit
				.getDefaultToolkit().getScreenSize().getHeight() / 35)));
		summon.setOpaque(true);
		summon.revalidate();
		summon.repaint();
		add(summon, 0);
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

	public static ArrayList<MinionViewHand> getBag() {
		return bag;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		setVisible(false);
		dispose();
	}
}
