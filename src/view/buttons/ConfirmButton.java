package view.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class ConfirmButton extends HeroButton implements ActionListener {
	private static ArrayList<HeroButton> selectedHeroes = new ArrayList<HeroButton>();
	private static int flag = 0;

	public ConfirmButton() {
		setActionCommand("this");
		addActionListener(this);
		setVisible(true);
		ImageIcon confirm = new ImageIcon("Icons/Confirm.png");
		setIcon(confirm);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ImageIcon confirm = new ImageIcon("Icons/Confirm.png");
		ImageIcon consel = new ImageIcon("Icons/2.png");
		if (!isConfirmValid2() && !isConfirmValid()) {
			setIcon(consel);
			setActionCommand("Error");
		} else {
			if (getActionCommand().equals("Error")) {
				setIcon(confirm);
				setActionCommand("this");
			}
		}
	}

	public boolean isConfirmValid() {
		ArrayList<HeroButton> ref = getBag();

		for (int i = 0; i < ref.size(); i++) {
			if (ref.get(i).getActionCommand().equals("Selected")) {
				if (flag == 0) {
					selectedHeroes.add(ref.get(i));
					flag = 1;

					return true;

				}

				if (flag == 1 && !(selectedHeroes.contains(ref.get(i)))) {
					selectedHeroes.add(ref.get(i));

					flag = 2;

					return true;
				}

			}

		}
		return false;
	}

	public boolean isConfirmValid2() {
		return flag == 2;
	}

	public ArrayList<HeroButton> getSelectedHeroes() {
		return selectedHeroes;
	}

	public static ArrayList<HeroButton> mySelectedHeroes() {
		return selectedHeroes;
	}

	public static void setFlag(int flag) {
		ConfirmButton.flag = flag;
	}

}
