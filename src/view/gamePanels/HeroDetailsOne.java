package view.gamePanels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextPane;

import model.heroes.Hero;

@SuppressWarnings("serial")
public class HeroDetailsOne extends JTextPane {

	public HeroDetailsOne(Hero p1) {
		super();
		this.setBounds(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.685),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height *0.013 ),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.05),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.1));
	    this.setFont(new Font("Castellar", Font.BOLD, (int) (Toolkit
				.getDefaultToolkit().getScreenSize().getHeight() / 48)));
	    this.setForeground(Color.CYAN);
	
		
		String s1 = p1.getCurrentHP() + "\n\n" + p1.getCurrentManaCrystals()
				+ " / " + p1.getTotalManaCrystals();
		
        this.setText(s1);

		this.setEditable(false);
		this.setVisible(true);
		this.setOpaque(false);
		this.revalidate();
		this.repaint();

	}
}
