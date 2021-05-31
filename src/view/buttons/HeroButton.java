package view.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;

import view.Sound;

@SuppressWarnings({ "serial" })
public class HeroButton extends JButton implements ActionListener {
	private static ArrayList<HeroButton> bag = new ArrayList<HeroButton>();
	private String name;
	private Sound s = new Sound();
	public HeroButton() {
		super();
		setVisible(true);
		setSize(100, 100);
		setActionCommand("this");
		bag.add(this);
	}


	public String toString() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void queueMusic() throws LineUnavailableException{
		s.heroButton();
	}

	public String getName() {
		return name;
	}

	public ArrayList<HeroButton> getBag() {
		return bag;
	}
	public static ArrayList<HeroButton> myBag()
	{
		return bag;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			if(!(this instanceof ConfirmButton))
				queueMusic();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}
