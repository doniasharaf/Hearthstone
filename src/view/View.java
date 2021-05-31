package view;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import model.cards.Card;
import model.cards.minions.Minion;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;
import view.buttons.EndTurnOne;
import view.buttons.EndTurnTwo;
import view.buttons.HeroPowerOne;
import view.buttons.HeroPowerTwo;
import view.gamePanels.DetailsLeft;
import view.gamePanels.Error;
import view.gamePanels.FieldPanelOne;
import view.gamePanels.FieldPanelTwo;
import view.gamePanels.GameOver;
import view.gamePanels.HandPanelOne;
import view.gamePanels.HandPanelTwo;
import view.gamePanels.HeroDetailsOne;
import view.gamePanels.HeroDetailsTwo;
import view.gamePanels.HeroPowerDescription;
import view.gamePanels.HeroPowerMessage;
import view.gamePanels.Instructions;
import view.gamePanels.LoadingScreen;
import view.gamePanels.Message;
import view.gamePanels.MinionLabelOne;
import view.gamePanels.MinionLabelTwo;
import view.gamePanels.SheepLabel;
import view.gamePanels.SpeechOne;
import view.gamePanels.SpeechTwo;
import controller.Controller;

@SuppressWarnings("serial")
public class View extends JFrame implements ActionListener {
	private Board b;
	private HandPanelOne firstHand;
	private HandPanelTwo secondHand;
	private FieldPanelOne firstField;
	private FieldPanelTwo secondField;
	private EndTurnOne e1 = new EndTurnOne();
	private EndTurnTwo e2 = new EndTurnTwo();
	private HeroPowerOne h1 = new HeroPowerOne();
	private HeroPowerTwo h2 = new HeroPowerTwo();
	private DetailsLeft deckDetails;
	private HeroDetailsOne hd1;
	private HeroDetailsTwo hd2;
	private ImageIcon icon1;
	private ImageIcon icon2;
	private JButton label1 = new JButton();
	private JButton label2 = new JButton();
	private HeroPowerDescription powerDetails;
	private GameOver gameOver;
	private LoadingScreen loading = new LoadingScreen();
	private MinionLabelOne m1 = new MinionLabelOne();
	private MinionLabelTwo m2 = new MinionLabelTwo();
	private SpeechOne s1 = new SpeechOne("banana");
	private SpeechTwo s2 = new SpeechTwo("banana");
	private SpeechOne ss1 = new SpeechOne("yay");
	private SpeechTwo ss2 = new SpeechTwo("yay");
	private Timer t = new Timer(30, this);
	private int m1x = m1.getX();

	@SuppressWarnings("unused")
	private int m2x = m2.getX();

	private int speedX = 1;

	private SheepLabel sheep = new SheepLabel();

	public View() {
		setTitle("Hearthstone");
		setVisible(true);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		revalidate();
		repaint();
	}

	public void selectPlayers(JLabel command, HeroSelectionPanel s) {
		this.getContentPane().removeAll();
		add(command, BorderLayout.NORTH);
		this.add(s, BorderLayout.CENTER);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		revalidate();
		repaint();
	}

	public void addBoard(Board board, Controller c) {
		getContentPane().removeAll();
		b = board;
		add(board);
		e1.addActionListener(c);
		e2.addActionListener(c);
		h1.addActionListener(c);
		h2.addActionListener(c);

		h1.addMouseListener(c);
		h2.addMouseListener(c);
		b.add(h1, 0);
		b.add(h2, 0);
		b.add(e1, 0);
		b.add(e2, 0);
		if (c.getP1() instanceof Hunter)
			icon1 = new ImageIcon("Icons/hunterp1.png");
		if (c.getP1() instanceof Mage)
			icon1 = new ImageIcon("Icons/magep1.png");
		if (c.getP1() instanceof Paladin)
			icon1 = new ImageIcon("Icons/paladinp1.png");
		if (c.getP1() instanceof Priest)
			icon1 = new ImageIcon("Icons/priestp1.png");
		if (c.getP1() instanceof Warlock)
			icon1 = new ImageIcon("Icons/warlockp1.png");
		if (c.getP2() instanceof Hunter)
			icon2 = new ImageIcon("Icons/hunterp2.png");
		if (c.getP2() instanceof Mage)
			icon2 = new ImageIcon("Icons/magep2.png");
		if (c.getP2() instanceof Paladin)
			icon2 = new ImageIcon("Icons/paladinp2.png");
		if (c.getP2() instanceof Priest)
			icon2 = new ImageIcon("Icons/priestp2.png");
		if (c.getP2() instanceof Warlock)
			icon2 = new ImageIcon("Icons/warlockp2.png");
		icon1 = resizeIcon(icon1, (int) (Toolkit.getDefaultToolkit()
				.getScreenSize().width * 0.15), (int) (Toolkit
				.getDefaultToolkit().getScreenSize().height * 0.35));
		icon2 = resizeIcon(icon2, (int) (Toolkit.getDefaultToolkit()
				.getScreenSize().width * 0.15), (int) (Toolkit
				.getDefaultToolkit().getScreenSize().height * 0.35));
		label1.setIcon(icon1);
		label2.setIcon(icon2);
		label1.setBounds(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.615),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.1),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.15),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.35));
		label2.setBounds(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.615),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.46),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.15),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.35));
		label1.setOpaque(false);
		label2.setOpaque(false);
		label1.setBorder(null);
		label1.setBorderPainted(false);
		label1.setContentAreaFilled(false);
		label1.setVisible(true);
		label2.setBorder(null);
		label2.setBorderPainted(false);
		label2.setContentAreaFilled(false);
		label2.setVisible(true);
		label1.setActionCommand("player1");
		label2.setActionCommand("player2");
		label1.addActionListener(c);
		label2.addActionListener(c);
		label1.setFocusable(false);
		label2.setFocusable(false);
		label1.setFocusPainted(false);
		label2.setFocusPainted(false);

		b.add(label1, 0);
		b.add(label2, 0);
		deckDetails = new DetailsLeft(c.getP1(), c.getP2());
		hd1 = new HeroDetailsOne(c.getP1());
		hd2 = new HeroDetailsTwo(c.getP2());
		b.add(deckDetails, 0);
		b.add(hd1, 0);
		b.add(hd2, 0);
		b.add(m1, 0);
		b.add(m2, 0);
		b.revalidate();
		b.repaint();
		t.start();
		revalidate();
		repaint();
	}

	public void generateHand(Hero p1, Hero p2, Controller c, Boolean b1,
			Boolean b2) {
		if (firstHand != null) {
			b.remove(firstHand);
		}
		if (secondHand != null) {
			b.remove(secondHand);
		}
		ArrayList<Card> hand1 = p1.getHand();
		ArrayList<Card> hand2 = p2.getHand();
		firstHand = new HandPanelOne(c, hand1, b1);
		secondHand = new HandPanelTwo(c, hand2, b2);
		b.add(firstHand, 0);
		b.add(secondHand, 0);
		b.remove(deckDetails);
		b.remove(hd1);
		b.remove(hd2);
		deckDetails = new DetailsLeft(c.getP1(), c.getP2());
		hd1 = new HeroDetailsOne(c.getP1());
		hd2 = new HeroDetailsTwo(c.getP2());
		b.add(deckDetails, 0);
		b.add(hd1, 0);
		b.add(hd2, 0);
		b.revalidate();
		b.repaint();
		this.revalidate();
		this.repaint();
	}

	public void generateField(Hero p1, Hero p2, Controller c, Boolean b1,
			Boolean b2) {
		if (firstField != null) {
			b.remove(firstField);
		}
		if (secondField != null)
			b.remove(secondField);
		ArrayList<Minion> field1 = p1.getField();
		ArrayList<Minion> field2 = p2.getField();
		firstField = new FieldPanelOne(c, field1, b1);
		secondField = new FieldPanelTwo(c, field2, b2);
		b.add(firstField, 0);
		b.add(secondField, 0);
		b.remove(deckDetails);
		b.remove(hd1);
		b.remove(hd2);
		deckDetails = new DetailsLeft(c.getP1(), c.getP2());
		hd1 = new HeroDetailsOne(c.getP1());
		hd2 = new HeroDetailsTwo(c.getP2());
		b.add(deckDetails, 0);
		b.add(hd1, 0);
		b.add(hd2, 0);
		b.revalidate();
		b.repaint();
		this.revalidate();
		this.repaint();
	}

	public void addError(Exception e) {
		new Error(e);

	}

	public void addMessage(String s) {
		new Message(s);
	}

	public void addHeroPowerMessage(Controller c) {
		new HeroPowerMessage(c);
	}

	public void generatePower(Hero p, int n) {
		powerDetails = new HeroPowerDescription(p, n);
	}

	public void addHeroPowerDescription(Hero p, int n) {
		b.add(powerDetails, 0);
		b.revalidate();
		b.repaint();
	}

	public void removeHeroPowerDescription() {
		b.remove(powerDetails);
		b.revalidate();
		b.repaint();
	}

	public void addOver(String s, Controller c) {
		gameOver = new GameOver(s, c);
	}

	public void addLoading() {
		Timer timer = new Timer(0, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loading.setVisible(true);
				loading.revalidate();
				loading.repaint();
			}
		});
		timer.setRepeats(false);
		timer.start();
	}

	public void removeLoading() {
		Timer timer = new Timer(4000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b.setVisible(true);
				loading.dispose();
				addRules();
			}
		});
		timer.setRepeats(false);
		timer.start();
	}

	public void addRules() {
		new Instructions();
	}

	public void addSpeechOne() {
		b.add(s1, 0);
		b.revalidate();
		b.repaint();
		this.revalidate();
		this.repaint();
	}

	public void addSpeechTwo() {
		b.add(s2, 0);
		b.revalidate();
		b.repaint();
		this.revalidate();
		this.repaint();
	}

	public void removeSpeechOne() {
		b.remove(s1);
		b.revalidate();
		b.repaint();
		this.revalidate();
		this.repaint();
	}

	public void removeSpeechTwo() {
		b.remove(s2);
		b.revalidate();
		b.repaint();
		this.revalidate();
		this.repaint();
	}
	public void addYayOne() {
		b.add(ss1, 0);
		b.revalidate();
		b.repaint();
		this.revalidate();
		this.repaint();
	}

	public void addYayTwo() {
		b.add(ss2, 0);
		b.revalidate();
		b.repaint();
		this.revalidate();
		this.repaint();
	}

	public void removeYayOne() {
		b.remove(ss1);
		b.revalidate();
		b.repaint();
		this.revalidate();
		this.repaint();
	}

	public void removeYayTwo() {
		b.remove(ss2);
		b.revalidate();
		b.repaint();
		this.revalidate();
		this.repaint();
	}
	public void addSheep(){
		b.add(sheep,0);
		b.revalidate();
		b.repaint();
		this.revalidate();
		this.repaint();
	}
	public void removeSheep()
	{
		b.remove(sheep);
		b.revalidate();
		b.repaint();
		this.revalidate();
		this.repaint();
	}

	public GameOver getGameOver() {
		return gameOver;
	}

	public static ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
		Image image = icon.getImage();
		Image resizedImage = image.getScaledInstance(width, height,
				java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}

	public Board getB() {
		return b;
	}

	public void setB(Board b) {
		this.b = b;
	}

	public HandPanelOne getFirstHand() {
		return firstHand;
	}

	public void setFirstHand(HandPanelOne firstHand) {
		this.firstHand = firstHand;
	}

	public HandPanelTwo getSecondHand() {
		return secondHand;
	}

	public void setSecondHand(HandPanelTwo secondHand) {
		this.secondHand = secondHand;
	}

	public FieldPanelOne getFirstField() {
		return firstField;
	}

	public void setFirstField(FieldPanelOne firstField) {
		this.firstField = firstField;
	}

	public FieldPanelTwo getSecondField() {
		return secondField;
	}

	public void setSecondField(FieldPanelTwo secondField) {
		this.secondField = secondField;
	}

	public EndTurnOne getE1() {
		return e1;
	}

	public void setE1(EndTurnOne e1) {
		this.e1 = e1;
	}

	public EndTurnTwo getE2() {
		return e2;
	}

	public void setE2(EndTurnTwo e2) {
		this.e2 = e2;
	}

	public HeroPowerOne getH1() {
		return h1;
	}

	public void setH1(HeroPowerOne h1) {
		this.h1 = h1;
	}

	public HeroPowerTwo getH2() {
		return h2;
	}

	public void setH2(HeroPowerTwo h2) {
		this.h2 = h2;
	}

	public MinionLabelOne getM1() {
		return m1;
	}

	public MinionLabelTwo getM2() {
		return m2;
	}

	public SpeechOne getS1() {
		return s1;
	}

	public SpeechTwo getS2() {
		return s2;
	}

	public SpeechOne getSs1() {
		return ss1;
	}

	public SpeechTwo getSs2() {
		return ss2;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (m1.getX() >= this.m1x
				+ (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.025)
				|| m1.getX() <= this.m1x
						- (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.025)) {
			this.speedX = -(this.speedX);

		}
		m1.setLocation(m1.getX() + speedX, m1.getY());
		m2.setLocation(m2.getX() + speedX, m2.getY());

	}

}
