package view.gamePanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextPane;
import javax.swing.border.MatteBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import controller.Controller;

@SuppressWarnings("serial")
public class GameOver extends JFrame {
	public GameOver(String s, Controller c) {
		super();
		setTitle("Game Over");
		this.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit()
				.getScreenSize().width / 1.75), (int) (Toolkit
				.getDefaultToolkit().getScreenSize().height / 2)));
		pack();
		setExtendedState(JFrame.NORMAL);
		JLayeredPane layer = new JLayeredPane();
		layer.setSize(this.getWidth(), this.getHeight());
		ImageIcon gif = new ImageIcon("Icons/minionsgif.gif");
		JLabel gifLabel = new JLabel();
		gifLabel.setIcon(gif);
		gifLabel.setOpaque(false);
		gifLabel.setSize((int) (gif.getIconWidth()),
				(int) (gif.getIconHeight()));
		gifLabel.setVisible(true);
		gifLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		JTextPane text = new JTextPane();
		text.setFont(new Font("Showcard Gothic", Font.BOLD, (int) (Toolkit
				.getDefaultToolkit().getScreenSize().getHeight() / 32)));
		text.setEditable(false);
		text.setSize(this.getWidth(), (int) (this.getHeight() * 0.1));
		text.setForeground(Color.white);
		StyledDocument doc = text.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		text.setBounds(0, (int) (this.getHeight() * 0.05), this.getWidth(),
				(int) (this.getHeight() * 0.1));
		text.setText(s);
		text.setOpaque(false);
		text.setVisible(true);
		text.revalidate();
		text.repaint();
		JButton newGame = new JButton();
		JButton endGame = new JButton();
		newGame.setSize((int) (this.getWidth() * 0.2),
				(int) (this.getHeight() * 0.1));
		endGame.setSize((int) (this.getWidth() * 0.2),
				(int) (this.getHeight() * 0.1));
		newGame.addActionListener(c);
		endGame.addActionListener(c);
		newGame.setActionCommand("new");
		endGame.setActionCommand("end");
		newGame.setBackground(Color.white);
		newGame.setBorder(new MatteBorder(3, 3, 3, 3, Color.cyan));
		newGame.setFont(new Font("Chiller", Font.BOLD, (int) (Toolkit
				.getDefaultToolkit().getScreenSize().getHeight() / 44)));
		newGame.setText("New Game");
		endGame.setBackground(Color.white);
		endGame.setBorder(new MatteBorder(3, 3, 3, 3, Color.cyan));
		endGame.setFont(new Font("Chiller", Font.BOLD, (int) (Toolkit
				.getDefaultToolkit().getScreenSize().getHeight() / 44)));
		endGame.setText("Exit");
		newGame.setFocusable(false);
		newGame.setFocusPainted(false);
		endGame.setFocusable(false);
		endGame.setFocusPainted(false);

		newGame.setBounds((int) (this.getWidth() * 0.2),
				(int) (this.getHeight() * 0.175 + gifLabel.getHeight() + this
						.getHeight() * 0.1), newGame.getWidth(), newGame
						.getHeight());
		endGame.setBounds((int) (this.getWidth() * 0.6),
				(int) (this.getHeight() * 0.175 + gifLabel.getHeight() + this
						.getHeight() * 0.1), endGame.getWidth(), endGame
						.getHeight());
		newGame.setOpaque(true);
		newGame.revalidate();
		newGame.repaint();
		endGame.setOpaque(true);
		endGame.revalidate();
		endGame.repaint();

		this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2
				- this.getWidth() / 2, Toolkit.getDefaultToolkit()
				.getScreenSize().height / 2 - this.getHeight() / 2);
		gifLabel.setBounds(this.getWidth() / 2 - gifLabel.getWidth() / 2,
				(int) (this.getHeight() * 0.175), (int) (gif.getIconWidth()),
				(int) (gif.getIconHeight()));

		layer.add(gifLabel, 1);
		layer.add(text, 1);
		layer.add(newGame, 0);
		layer.add(endGame, 0);
		layer.setBackground(new Color(230, 14, 54));
		layer.setOpaque(true);
		layer.setVisible(true);
		layer.revalidate();
		layer.repaint();
		this.add(layer);

		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
		revalidate();
		repaint();
	}

}
