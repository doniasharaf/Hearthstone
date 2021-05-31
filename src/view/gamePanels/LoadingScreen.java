package view.gamePanels;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class LoadingScreen extends JFrame {
	public LoadingScreen(){
		this.setSize(this.getMaximumSize());
		this.pack();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setTitle("Hearthstone");
		ImageIcon gif = new ImageIcon("Icons/loading.gif");
		JLabel loadgif = new JLabel(gif);
		loadgif.setIcon(gif);
		loadgif.setBackground(new Color(25, 31, 38));
		loadgif.setOpaque(true);
		loadgif.setVisible(true);
		loadgif.revalidate();
		loadgif.repaint();
		this.add(loadgif,BorderLayout.CENTER);
		this.setVisible(false);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.revalidate();
		this.repaint();
	}
	
}
