package view.gamePanels;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import view.View;
import controller.Controller;

@SuppressWarnings("serial")
public class HeroPowerMessage extends JFrame implements ActionListener {
	public HeroPowerMessage(Controller c) {
		this.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit()
				.getScreenSize().width / 2), (int) (Toolkit.getDefaultToolkit()
				.getScreenSize().height / 3.3)));
		this.setTitle("Select Your Target");
		pack();
		setExtendedState(JFrame.NORMAL);
		this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2
				- this.getWidth() / 2, Toolkit.getDefaultToolkit()
				.getScreenSize().height / 2 - this.getHeight() / 2);
		JLabel label = new JLabel();
		label.setSize(getSize());
		label.setLayout(new GridLayout(1, 2));
		JButton hero = new JButton();
		hero.setSize(getWidth() / 2, getHeight());
		hero.setActionCommand("hero");
		ImageIcon heroIcon = new ImageIcon("Icons/domdom.png");
		heroIcon = View.resizeIcon(heroIcon, (int)(hero.getWidth()*1.4),(int)( hero.getHeight()*1.4));
		hero.setIcon(heroIcon);
		hero.setOpaque(false);
		hero.revalidate();
		hero.repaint();
		JButton minion = new JButton();
		minion.setSize(getWidth() / 2, getHeight());
		minion.setActionCommand("minion");
		minion.setText("minion");
		ImageIcon minionIcon = new ImageIcon("Icons/don.png");
		View.resizeIcon(minionIcon, minion.getWidth(), minion.getHeight());
		minion.setIcon(minionIcon);
		minion.setOpaque(false);
		minion.revalidate();
		minion.repaint();
		label.add(hero);
		label.add(minion);
		hero.addActionListener(c);
		hero.addActionListener(this);
		minion.addActionListener(c);
		minion.addActionListener(this);
		add(label);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		revalidate();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.dispose();

	}
}
