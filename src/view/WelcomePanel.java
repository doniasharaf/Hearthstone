package view;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WelcomePanel extends JPanel implements ActionListener {
	private JButton Start = new JButton();

	public WelcomePanel() {
		super();
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setVisible(true);
		ImageIcon i = new ImageIcon("Icons/1.png");
		i = View.resizeIcon(i,
				Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit
						.getDefaultToolkit().getScreenSize().height);
		Start.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		Start.setIcon(i);
		Start.setVisible(true);
		Start.setActionCommand("Start");
		add(Start, BorderLayout.CENTER);
	}

	public JButton getStart() {
		return Start;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}

}
