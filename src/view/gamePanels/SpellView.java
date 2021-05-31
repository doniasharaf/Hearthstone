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

import model.cards.spells.HeroTargetSpell;
import model.cards.spells.KillCommand;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.Pyroblast;
import model.cards.spells.Spell;
import view.buttons.CastSpell;
import controller.Controller;

@SuppressWarnings("serial")
public class SpellView extends JFrame implements ActionListener {
	private static ArrayList<SpellView> bag = new ArrayList<SpellView>();
	Spell spell;

	public SpellView(Controller c, Spell spell) {
		super();
		this.setTitle("Spell Details");
		this.setSize(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.25),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.96));
		setBounds(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.75),
				0,
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.25),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.96));
		setExtendedState(JFrame.MAXIMIZED_VERT);
		setBackground(Color.GRAY);
		JLayeredPane layer = new JLayeredPane();
		layer.setSize(this.getSize());
		layer.setOpaque(true);
		layer.setBackground(Color.gray);
		CardIcon icon = new CardIcon(spell);
		icon.setBounds((int) (getWidth() * 0.2), (int) (getHeight() * 0.01),
				icon.getWidth(), icon.getHeight());
		icon.setIcon(icon.resizeIcon(icon.getIcon(), (int) (getWidth() * 0.6),
				(int) (getHeight() * 0.4)));
		layer.add(icon, 0);
		JTextPane text = new JTextPane();
		text.setSize(new Dimension(getWidth(), (int) (getHeight() * 0.5)));
		text.setBounds(0, (int) (getHeight() * 0.6), getWidth(),
				(int) (getHeight() * 0.4));
		text.setFont(new Font("Showcard Gothic", Font.BOLD, (int) (Toolkit
				.getDefaultToolkit().getScreenSize().getHeight() / 40)));

		String s = "Name: " + spell.getName() + "\nMana Cost: "
				+ spell.getManaCost() + "\nRarity: " + spell.getRarity();
		text.setText(s);
		text.setEditable(false);
		text.setVisible(true);
		text.revalidate();
		text.repaint();
		text.setBackground(Color.GRAY);
		if (spell instanceof KillCommand || spell instanceof Pyroblast) {
			CastSpell castSpellMinion = new CastSpell();
			castSpellMinion.addActionListener(c);
			castSpellMinion.addActionListener(this);
			castSpellMinion.setSpell(spell);
			castSpellMinion.setBounds(0, (int) (getHeight() * 0.855),
					(int) (getWidth() * 0.478), (int) (getHeight() * 0.1));
			castSpellMinion.setActionCommand("target minion");
			castSpellMinion.setFocusable(false);
			castSpellMinion.setFocusPainted(false);
			castSpellMinion.setBackground(Color.white);
			castSpellMinion.setBorder(new MatteBorder(3, 3, 3, 2, Color.cyan));
			castSpellMinion.setFont(new Font("Chiller", Font.BOLD,
					(int) (Toolkit.getDefaultToolkit().getScreenSize()
							.getHeight() / 35)));
			castSpellMinion.setText("Target Minion");
			castSpellMinion.setOpaque(true);
			castSpellMinion.revalidate();
			castSpellMinion.repaint();
			add(castSpellMinion, 0);
			CastSpell castSpellHero = new CastSpell();
			castSpellHero.addActionListener(c);
			castSpellHero.addActionListener(this);
			castSpellHero.setSpell(spell);
			castSpellHero.setBounds((int) (getWidth() * 0.478),
					(int) (getHeight() * 0.855), (int) (getWidth() * 0.478),
					(int) (getHeight() * 0.1));
			castSpellHero.setActionCommand("target hero");
			castSpellHero.setFocusable(false);
			castSpellHero.setFocusPainted(false);
			castSpellHero.setBackground(Color.white);
			castSpellHero.setBorder(new MatteBorder(3, 1, 3, 3, Color.cyan));
			castSpellHero.setFont(new Font("Chiller", Font.BOLD, (int) (Toolkit
					.getDefaultToolkit().getScreenSize().getHeight() / 35)));
			castSpellHero.setText("Target Hero");
			castSpellHero.setOpaque(true);
			castSpellHero.revalidate();
			castSpellHero.repaint();
			add(castSpellHero, 0);
		} else {
			CastSpell castSpell = new CastSpell();
			castSpell.addActionListener(c);
			castSpell.addActionListener(this);
			castSpell.setSpell(spell);
			castSpell.setBounds(0, (int) (getHeight() * 0.855),
					(int) (getWidth() * 0.955), (int) (getHeight() * 0.1));
			if (spell instanceof MinionTargetSpell
					|| spell instanceof LeechingSpell)
				castSpell.setActionCommand("target minion");
			if (spell instanceof HeroTargetSpell)
				castSpell.setActionCommand("target hero");

			castSpell.setFocusable(false);
			castSpell.setFocusPainted(false);
			castSpell.setBackground(Color.white);
			castSpell.setBorder(new MatteBorder(3, 3, 3, 3, Color.cyan));
			castSpell.setFont(new Font("Chiller", Font.BOLD, (int) (Toolkit
					.getDefaultToolkit().getScreenSize().getHeight() / 35)));
			castSpell.setOpaque(true);
			castSpell.revalidate();
			castSpell.repaint();
			add(castSpell, 0);
		}
		layer.add(text, 1);
		this.add(layer);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.revalidate();
		this.repaint();
		bag.add(this);
	}

	public static ArrayList<SpellView> getBag() {
		return bag;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		setVisible(false);
		dispose();
	}
}
