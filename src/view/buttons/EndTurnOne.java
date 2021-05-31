package view.buttons;

import java.awt.Toolkit;

@SuppressWarnings("serial")
public class EndTurnOne extends EndTurnTwo{
	public EndTurnOne()
	{
		super();
		setBounds(0, (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.25),this.getWidth(),this.getHeight());
		revalidate();
		repaint();
	}

}
