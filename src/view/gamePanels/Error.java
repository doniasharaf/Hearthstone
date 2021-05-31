package view.gamePanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import model.cards.Card;
import model.cards.minions.Minion;
import exceptions.FullHandException;

@SuppressWarnings("serial")
public class Error extends JFrame implements ActionListener {
	public Error(Exception e) {
		super();
		setTitle("Ooops");
		JLayeredPane layer = new JLayeredPane();
		if (!(e instanceof FullHandException)) {
			this.setPreferredSize(new Dimension((int) (Toolkit
					.getDefaultToolkit().getScreenSize().width / 1.5), Toolkit
					.getDefaultToolkit().getScreenSize().height / 4));
			pack();
			setExtendedState(JFrame.NORMAL);

			JLabel text = new JLabel(e.getMessage(), SwingConstants.CENTER);
			text.setFont(new Font("Showcard Gothic", Font.BOLD, (int) (Toolkit
					.getDefaultToolkit().getScreenSize().getHeight() / 40)));
			text.setOpaque(true);
			text.setBackground(Color.LIGHT_GRAY);
			this.add(text, BorderLayout.CENTER);
			this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width
					/ 2 - this.getWidth() / 2, Toolkit.getDefaultToolkit()
					.getScreenSize().height / 2 - this.getHeight() / 2);
		} else {
			Card card = ((FullHandException) e).getBurned();
			this.setPreferredSize(new Dimension((int) (Toolkit
					.getDefaultToolkit().getScreenSize().width / 1.5), Toolkit
					.getDefaultToolkit().getScreenSize().height / 2));
			pack();
			setExtendedState(JFrame.NORMAL);
			layer.setSize(this.getSize());
			JTextPane text = new JTextPane();
			text.setBackground(Color.LIGHT_GRAY);
			text.setEditable(false);
			text.setSize(this.getWidth(), (int) (this.getHeight() * 0.1));
			text.setFont(new Font("Showcard Gothic", Font.BOLD, (int) (Toolkit
					.getDefaultToolkit().getScreenSize().getHeight() / 40)));
			text.setBounds(0, (int) (this.getHeight() * 0.05), this.getWidth(),
					(int) (this.getHeight() * 0.1));
			text.setText(e.getMessage());
			text.setBorder(new MatteBorder(0, 0, 3, 0, Color.BLACK));
			StyledDocument doc = text.getStyledDocument();
			SimpleAttributeSet center = new SimpleAttributeSet();
			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
			doc.setParagraphAttributes(0, doc.getLength(), center, false);
			text.revalidate();
			text.repaint();
			text.setVisible(true);
			CardIcon icon = new CardIcon(card);
			icon.setIcon(icon.resizeIcon(icon.getIcon(),
					(int) (getWidth() * 0.27), (int) (getHeight() * 0.7)));
			icon.setBackground(Color.LIGHT_GRAY);
			icon.setBounds((int) (this.getWidth() * 0.1), (int) (this
					.getHeight() * 0.15), icon.getIcon().getIconWidth(), icon
					.getIcon().getIconHeight());
			JTextPane details = new JTextPane();
			details.setEditable(false);
			details.setBackground(Color.LIGHT_GRAY);
			details.setSize((int) (this.getWidth() * 0.5),
					(int) (this.getHeight() * 0.6));
			details.setFont(new Font("Showcard Gothic", Font.BOLD, (int) (Toolkit
					.getDefaultToolkit().getScreenSize().getHeight() / 44)));
			details.setBounds((int) (this.getWidth() / 1.75),
					(int) (this.getHeight() * 0.2), details.getWidth(),
					details.getHeight());
			String s = "This card has been burned\n\n";

			if (card instanceof Minion) {
				Minion card1 = (Minion) card;
				s += "Name: " + card1.getName() + "\nMana Cost: "
						+ card1.getManaCost() + "\nRarity: "
						+ card1.getRarity() + "\nAttack: " + card1.getAttack()
						+ "\nCurrent HP: " + card1.getCurrentHP() + "\nTaunt: "
						+ card1.isTaunt() + "\nDivine: " + card1.isDivine()
						+ "\nCharge: " + card1.isCharge();
			} else {
				s += "Name: " + card.getName() + "\nMana Cost: "
						+ card.getManaCost() + "\nRarity: " + card.getRarity();
			}

			details.setText(s);
			StyledDocument doc1 = text.getStyledDocument();
			SimpleAttributeSet center1 = new SimpleAttributeSet();
			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
			doc.setParagraphAttributes(0, doc1.getLength(), center1, false);
			details.revalidate();
			details.repaint();
			details.setVisible(true);

			layer.add(text, 0);
			layer.add(icon, 0);
			layer.add(details, 0);
			layer.setBackground(Color.LIGHT_GRAY);
			layer.setOpaque(true);
			layer.setVisible(true);
			layer.revalidate();
			layer.repaint();
			this.add(layer);
			this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width
					/ 2 - this.getWidth() / 2, Toolkit.getDefaultToolkit()
					.getScreenSize().height / 2 - this.getHeight() / 2);

		}

		JButton j = new JButton();
		j.setSize(this.getWidth(),(int)(this.getHeight()*0.1));
		j.addActionListener(this);
		j.setFocusable(false);
		j.setFocusPainted(false);
		j.setBackground(Color.white);
		j.setBorder(new MatteBorder(3, 3, 3, 3, Color.cyan));
		j.setFont(new Font("Chiller", Font.BOLD, (int) (Toolkit
				.getDefaultToolkit().getScreenSize().getHeight() / 44)));
		j.setText("Ok");
		this.setBackground(Color.LIGHT_GRAY);
		add(j, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		revalidate();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		dispose();
		setVisible(false);
	}

}
