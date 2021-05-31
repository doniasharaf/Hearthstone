package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import model.cards.minions.Minion;
import model.cards.spells.AOESpell;
import model.cards.spells.DivineSpirit;
import model.cards.spells.FieldSpell;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.Polymorph;
import model.cards.spells.SealOfChampions;
import model.cards.spells.Spell;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;
import view.Board;
import view.HeroSelectionPanel;
import view.Sound;
import view.View;
import view.WelcomePanel;
import view.buttons.AttackButton;
import view.buttons.CastSpell;
import view.buttons.ConfirmButton;
import view.buttons.ConfirmTarget;
import view.buttons.EndTurnOne;
import view.buttons.EndTurnTwo;
import view.buttons.HeroButton;
import view.buttons.HeroPowerOne;
import view.buttons.HeroPowerTwo;
import view.buttons.HunterButton;
import view.buttons.MageButton;
import view.buttons.PaladinButton;
import view.buttons.PriestButton;
import view.buttons.SummonButton;
import view.buttons.WarlockButton;
import engine.Game;
import engine.GameListener;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;

public class Controller implements GameListener, ActionListener, MouseListener {

	private View view;
	private Board board;
	private Minion attacker;
	private Minion target;
	private boolean attackClicked;
	private boolean spellClicked;
	private boolean powerClicked;
	private boolean isMinion;
	private Spell spell;
	private Game game;
	private Sound sound = new Sound();
	private int playersSelected = 0;
	private ArrayList<Hero> players = new ArrayList<Hero>();
	private Hero p1;
	private Hero p2;

	public Controller() throws LineUnavailableException {
		view = new View();
		sound.startTheme();
		WelcomePanel p = new WelcomePanel();
		view.add(p);
		p.getStart().addActionListener(this);
		view.setExtendedState(JFrame.MAXIMIZED_BOTH);
		view.revalidate();
		view.repaint();
		HeroButton.myBag().clear();
		ConfirmButton.mySelectedHeroes().clear();
		ConfirmButton.setFlag(0);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton b = (JButton) arg0.getSource();
		if (b.getActionCommand().equals("Start")) {
			try {
				sound.startGame();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			JLabel command = new JLabel();
			ImageIcon comm = new ImageIcon("Icons/PLAYER1.png");
			comm = View.resizeIcon(comm, comm.getIconWidth(), (int) (Toolkit
					.getDefaultToolkit().getScreenSize().height * 0.05));
			command.setIcon(comm);
			selectPlayers(command);
			return;
		}
		if (b instanceof ConfirmButton && playersSelected == 1
				&& ((ConfirmButton) b).isConfirmValid()) {
			view.addLoading();
			try {
				sound.confirmHero();
			} catch (LineUnavailableException e1) {
			}
			playersSelected++;
			ArrayList<HeroButton> heroes = ((ConfirmButton) b)
					.getSelectedHeroes();
			obtainHeroes(heroes);
			p1 = players.get(0);
			p2 = players.get(1);
			try {
				game = new Game(p1, p2);
				game.setListener(this);
			} catch (FullHandException | CloneNotSupportedException e) {
				if (e instanceof FullHandException)
					view.addError(e);
				else
					e.printStackTrace();
			}
			if (game.getCurrentHero().equals(p1))
				view.generatePower(game.getCurrentHero(), 1);
			else
				view.generatePower(game.getCurrentHero(), 2);
			view.removeLoading();
			start();
			return;
		}
		if (b instanceof ConfirmButton && playersSelected == 0
				&& ((ConfirmButton) b).isConfirmValid()) {
			try {
				sound.confirmHero();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			playersSelected += 1;
			JLabel command = new JLabel();
			ImageIcon comm = new ImageIcon("Icons/PLAYER2.png");
			comm = View.resizeIcon(comm, comm.getIconWidth(), (int) (Toolkit
					.getDefaultToolkit().getScreenSize().height * 0.05));
			command.setIcon(comm);
			selectPlayers(command);
			return;
		}
		if (b instanceof SummonButton) {
			attacker = ((SummonButton) b).getM();
			try {
				game.getCurrentHero().playMinion(attacker);
				sound.summon();
				toggleHand();
			} catch (NotYourTurnException | NotEnoughManaException
					| FullFieldException | LineUnavailableException e) {
				view.addError(e);
			}
			spellClicked = false;
			attackClicked = false;
			powerClicked = false;
			isMinion = false;
			return;
		}
		if (b instanceof AttackButton) {
			attacker = ((AttackButton) b).getM();
			try {
				if (attacker.getAttack() <= 0)
					throw new CannotAttackException("This minion Cannot Attack");
				if (attacker.isSleeping())
					throw new CannotAttackException(
							"Give this minion a turn to get ready");
				if (attacker.isAttacked())
					throw new CannotAttackException(
							"This minion has already attacked");
			} catch (CannotAttackException e) {
				view.addError(e);
				return;
			}
			attackClicked = true;
			spellClicked = false;
			powerClicked = false;
			return;
		}
		if (b instanceof ConfirmTarget && attackClicked) {
			target = ((ConfirmTarget) b).getMinion();
			try {

				game.getCurrentHero().attackWithMinion(attacker, target);
				if (game.getCurrentHero() == p1) {
					view.getS1().setLocation(view.getM1().getX(),
							view.getS1().getY());
					view.addSpeechOne();
				} else {
					view.getS2().setLocation(view.getM2().getX(),
							view.getS2().getY());
					view.addSpeechTwo();
				}
				sound.attack();
				Timer timer = new Timer(1750, new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (game.getCurrentHero() == p1)
							view.removeSpeechOne();
						else
							view.removeSpeechTwo();
					}
				});
				timer.setRepeats(false);
				timer.start();
			} catch (CannotAttackException | NotYourTurnException
					| TauntBypassException | InvalidTargetException
					| NotSummonedException | LineUnavailableException e) {
				if (e instanceof LineUnavailableException) {
					e.printStackTrace();
					return;
				}

				else
					view.addError(e);
			}
			spellClicked = false;
			attackClicked = false;
			powerClicked = false;
			isMinion = false;
			toggleHand();
			return;
		}
		if (b instanceof EndTurnOne || b instanceof EndTurnTwo) {
			try {
				sound.endTurn();
				game.endTurn();
			} catch (FullHandException | CloneNotSupportedException
					| LineUnavailableException e) {
				if (e instanceof FullHandException)
					view.addError(e);
				else {
					e.printStackTrace();
					return;
				}

			}
			if (game.getCurrentHero().equals(p1)) {
				view.getH1().setVisible(true);
				view.getE1().setVisible(true);
				view.getM1().setVisible(true);
				view.getH2().setVisible(false);
				view.getE2().setVisible(false);
				view.getM2().setVisible(false);
				view.generatePower(game.getCurrentHero(), 1);
				view.repaint();
				view.validate();
			} else {
				view.getH2().setVisible(true);
				view.getE2().setVisible(true);
				view.getM2().setVisible(true);
				view.getH1().setVisible(false);
				view.getE1().setVisible(false);
				view.getM1().setVisible(false);
				view.generatePower(game.getCurrentHero(), 2);
				view.repaint();
				view.validate();
			}
			attackClicked = false;
			spellClicked = false;
			powerClicked = false;
			isMinion = false;
			toggleHand();
			return;
		}
		if (b instanceof CastSpell) {
			spell = ((CastSpell) b).getSpell();
			if (b.getActionCommand().equals("target minion")) {
				try {
					game.validateManaCost(spell);

				} catch (NotEnoughManaException e) {
					view.addError(e);
					return;
				}
				spellClicked = true;
				attackClicked = false;
				powerClicked = false;
			}
			if (spell instanceof AOESpell) {
				try {

					game.getCurrentHero().castSpell((AOESpell) spell,
							game.getOpponent().getField());
					sound.castSpell();
				} catch (NotYourTurnException | NotEnoughManaException
						| LineUnavailableException e) {
					if (e instanceof LineUnavailableException) {
						e.printStackTrace();
						return;
					} else {
						view.addError(e);
						return;
					}
				}
			}
			if (spell instanceof FieldSpell) {
				try {

					game.getCurrentHero().castSpell((FieldSpell) spell);
					sound.castSpell();
				} catch (NotYourTurnException | NotEnoughManaException
						| LineUnavailableException e) {
					if (e instanceof LineUnavailableException) {
						e.printStackTrace();
						return;
					} else {
						view.addError(e);
						return;
					}
				}
			}
			if (b.getActionCommand().equals("target hero")) {
				try {
					game.getCurrentHero().castSpell((HeroTargetSpell) spell,
							game.getOpponent());
					sound.castSpell();
				} catch (NotYourTurnException | NotEnoughManaException
						| LineUnavailableException e) {

					if (e instanceof LineUnavailableException) {
						e.printStackTrace();
						return;
					} else {
						view.addError(e);
						return;
					}
				}
			}
			if ((spell instanceof MinionTargetSpell && !(b.getActionCommand()
					.equals("target hero"))) || spell instanceof LeechingSpell)
				view.addMessage("Please select your target minion.");
			if (spell instanceof DivineSpirit
					|| spell instanceof SealOfChampions) {
				friendlyToggle();
			} else {
				toggleHand();
			}
			return;
		}
		if (b instanceof ConfirmTarget && spellClicked) {
			target = ((ConfirmTarget) b).getMinion();
			if (spell instanceof MinionTargetSpell) {
				try {
					game.getCurrentHero().castSpell((MinionTargetSpell) spell,
							target);
					if (spell instanceof Polymorph) {
						view.addSheep();
						sound.polymorph();
						Timer t2 = new Timer(750, new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								view.removeSheep();
							}
						});
						t2.setRepeats(false);
						t2.start();
					} else
						sound.castSpell();
				} catch (NotYourTurnException | NotEnoughManaException
						| InvalidTargetException | LineUnavailableException e) {
					if (e instanceof LineUnavailableException) {
						e.printStackTrace();
						return;
					} else {
						view.addError(e);
						return;
					}
				}
				spellClicked = false;
				attackClicked = false;
				powerClicked = false;
				isMinion = false;
			} else {
				if (spell instanceof LeechingSpell) {
					try {

						game.getCurrentHero().castSpell((LeechingSpell) spell,
								target);
						sound.castSpell();

					} catch (NotYourTurnException | NotEnoughManaException
							| LineUnavailableException e) {
						if (e instanceof LineUnavailableException) {
							e.printStackTrace();
							return;
						} else {
							view.addError(e);
							return;
						}
					}
					spellClicked = false;
					attackClicked = false;
					powerClicked = false;
					isMinion = false;
				}
			}
			toggleHand();
			return;
		}

		if (b instanceof HeroPowerOne || b instanceof HeroPowerTwo) {
			try {
				game.validateUsingHeroPower(game.getCurrentHero());
			} catch (NotEnoughManaException | HeroPowerAlreadyUsedException e) {

				view.addError(e);
				return;

			}

			if (game.getCurrentHero() instanceof Hunter
					|| game.getCurrentHero() instanceof Paladin
					|| game.getCurrentHero() instanceof Warlock) {
				try {
					game.getCurrentHero().useHeroPower();
					if (game.getCurrentHero() == p1) {
						view.getSs1().setLocation(view.getM1().getX(),
								view.getSs1().getY());
						view.addYayOne();
					} else {
						view.getSs2().setLocation(view.getM2().getX(),
								view.getSs2().getY());
						view.addYayTwo();
					}
					sound.heroPower();
					Timer timer = new Timer(1500, new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (game.getCurrentHero() == p1)
								view.removeYayOne();
							else
								view.removeYayTwo();
						}
					});
					timer.setRepeats(false);
					timer.start();
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException
						| LineUnavailableException e) {
					if (!(e instanceof CloneNotSupportedException)) {
						view.addError(e);
						return;

					} else
						e.printStackTrace();
				}
			}
			if (game.getCurrentHero() instanceof Mage
					|| game.getCurrentHero() instanceof Priest) {
				view.addHeroPowerMessage(this);
				powerClicked = true;
			}
			isMinion = false;
			toggleHand();
			return;
		}
		if ((b.getActionCommand().equals("player1") || b.getActionCommand()
				.equals("player2")) && powerClicked) {
			if (game.getCurrentHero() instanceof Mage) {
				try {
					if (b.getActionCommand().equals("player1")) {
						((Mage) (game.getCurrentHero())).useHeroPower(p1);

					} else {
						((Mage) (game.getCurrentHero())).useHeroPower(p2);

					}
					if (game.getCurrentHero() == p1) {
						view.getSs1().setLocation(view.getM1().getX(),
								view.getSs1().getY());
						view.addYayOne();
					} else {
						view.getSs2().setLocation(view.getM2().getX(),
								view.getSs2().getY());
						view.addYayTwo();
					}
					sound.heroPower();
					Timer timer = new Timer(1500, new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (game.getCurrentHero() == p1)
								view.removeYayOne();
							else
								view.removeYayTwo();
						}
					});
					timer.setRepeats(false);
					timer.start();

				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException
						| LineUnavailableException e) {
					if (!(e instanceof CloneNotSupportedException)) {
						view.addError(e);
						return;
					} else
						e.printStackTrace();
				}
			} else {
				try {
					if (b.getActionCommand().equals("player1")) {
						((Priest) (game.getCurrentHero())).useHeroPower(p1);
					} else {
						((Priest) (game.getCurrentHero())).useHeroPower(p2);
					}
					if (game.getCurrentHero() == p1) {
						view.getSs1().setLocation(view.getM1().getX(),
								view.getSs1().getY());
						view.addYayOne();
					} else {
						view.getSs2().setLocation(view.getM2().getX(),
								view.getSs2().getY());
						view.addYayTwo();
					}
					sound.heroPower();
					Timer timer = new Timer(1500, new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (game.getCurrentHero() == p1)
								view.removeYayOne();
							else
								view.removeYayTwo();
						}
					});
					timer.setRepeats(false);
					timer.start();

				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException
						| LineUnavailableException e) {
					if (!(e instanceof CloneNotSupportedException)) {
						view.addError(e);
						return;
					} else
						e.printStackTrace();
				}
			}
			spellClicked = false;
			attackClicked = false;
			powerClicked = false;
			isMinion = false;
			toggleHand();
			return;
		}

		if (b.getActionCommand().equals("minion") && powerClicked) {
			attackClicked = false;
			spellClicked = false;
			powerClicked = true;
			isMinion = true;
			friendlyToggle();
			return;
		}
		if (b instanceof ConfirmTarget && powerClicked) {
			target = ((ConfirmTarget) b).getMinion();
			if (game.getCurrentHero() instanceof Mage) {
				try {
					((Mage) game.getCurrentHero()).useHeroPower(target);
					if (game.getCurrentHero() == p1) {
						view.getSs1().setLocation(view.getM1().getX(),
								view.getSs1().getY());
						view.addYayOne();
					} else {
						view.getSs2().setLocation(view.getM2().getX(),
								view.getSs2().getY());
						view.addYayTwo();
					}
					sound.heroPower();
					Timer timer = new Timer(1500, new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (game.getCurrentHero() == p1)
								view.removeYayOne();
							else
								view.removeYayTwo();
						}
					});
					timer.setRepeats(false);
					timer.start();
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException
						| LineUnavailableException e) {
					if (!(e instanceof CloneNotSupportedException)) {
						view.addError(e);
						return;
					} else
						e.printStackTrace();
				}
			} else {
				try {
					((Priest) game.getCurrentHero()).useHeroPower(target);
					if (game.getCurrentHero() == p1) {
						view.getSs1().setLocation(view.getM1().getX(),
								view.getSs1().getY());
						view.addYayOne();
					} else {
						view.getSs2().setLocation(view.getM2().getX(),
								view.getSs2().getY());
						view.addYayTwo();
					}
					sound.heroPower();
					Timer timer = new Timer(1500, new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (game.getCurrentHero() == p1)
								view.removeYayOne();
							else
								view.removeYayTwo();
						}
					});
					timer.setRepeats(false);
					timer.start();
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException
						| NotYourTurnException | FullHandException
						| FullFieldException | CloneNotSupportedException
						| LineUnavailableException e) {
					if (!(e instanceof CloneNotSupportedException)) {
						view.addError(e);
						return;
					} else
						e.printStackTrace();
				}
			}
			spellClicked = false;
			attackClicked = false;
			powerClicked = false;
			isMinion = false;
			toggleHand();
			return;
		}
		if (attackClicked && b.getActionCommand().equals("player1")) {
			try {

				game.getCurrentHero().attackWithMinion(attacker, p1);
				sound.attackHero();
			} catch (CannotAttackException | NotYourTurnException
					| TauntBypassException | NotSummonedException
					| InvalidTargetException | LineUnavailableException e) {
				if (e instanceof LineUnavailableException) {
					e.printStackTrace();
					return;
				} else {
					view.addError(e);
				}
			}
			attackClicked = false;
			spellClicked = false;
			powerClicked = false;
			isMinion = false;
			toggleHand();
			return;
		}
		if (attackClicked && b.getActionCommand().equals("player2")) {
			try {
				game.getCurrentHero().attackWithMinion(attacker, p2);
				sound.attackHero();
			} catch (CannotAttackException | NotYourTurnException
					| TauntBypassException | NotSummonedException
					| InvalidTargetException | LineUnavailableException e) {
				if (e instanceof LineUnavailableException) {
					e.printStackTrace();
					return;
				} else {
					view.addError(e);
					return;
				}
			}

			attackClicked = false;
			spellClicked = false;
			powerClicked = false;
			isMinion = false;
			toggleHand();
			return;
		}
		if (spellClicked && spell instanceof HeroTargetSpell
				&& b.getActionCommand().equals("player1")) {

			try {
				game.getCurrentHero().castSpell((HeroTargetSpell) spell, p1);
			} catch (NotYourTurnException | NotEnoughManaException e) {
				view.addError(e);
				return;
			}

			attackClicked = false;
			spellClicked = false;
			powerClicked = false;
			isMinion = false;
			toggleHand();
			return;
		}
		if (spellClicked && spell instanceof HeroTargetSpell
				&& b.getActionCommand().equals("player2")) {
			try {
				game.getCurrentHero().castSpell((HeroTargetSpell) spell, p2);
			} catch (NotYourTurnException | NotEnoughManaException e) {
				view.addError(e);
				return;
			}
			attackClicked = false;
			spellClicked = false;
			powerClicked = false;
			isMinion = false;
			toggleHand();
			return;
		}

		if (b.getActionCommand().equals("new"))
			try {
				view.getGameOver().dispose();
				view.setVisible(false);
				view.dispose();
				sound.stopWinTheme();
				new Controller();
				return;
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		if (b.getActionCommand().equals("end"))
			System.exit(0);
	}

	public boolean isSpellClicked() {
		return spellClicked;
	}

	public boolean isMinion() {
		return isMinion;
	}

	public void selectPlayers(JLabel command) {
		HeroSelectionPanel s = new HeroSelectionPanel();
		if (playersSelected == 1) {
			ArrayList<HeroButton> bag = HeroButton.myBag();
			for (int i = 0; i < bag.size(); i++) {
				bag.get(i).setActionCommand("this");
			}
		}

		s.getSel().addActionListener(this);
		command.setPreferredSize(new Dimension(view.getWidth(), 50));
		view.selectPlayers(command, s);

	}

	public void obtainHeroes(ArrayList<HeroButton> heroes) {
		for (int i = 0; i < 2; i++) {
			if (heroes.get(i) instanceof HunterButton)
				try {
					players.add(new Hunter());
				} catch (IOException | CloneNotSupportedException e) {
					e.printStackTrace();
				}
			if (heroes.get(i) instanceof MageButton)
				try {
					players.add(new Mage());
				} catch (IOException | CloneNotSupportedException e) {
					e.printStackTrace();
				}
			if (heroes.get(i) instanceof PaladinButton)
				try {
					players.add(new Paladin());
				} catch (IOException | CloneNotSupportedException e) {
					e.printStackTrace();
				}
			if (heroes.get(i) instanceof PriestButton)
				try {
					players.add(new Priest());
				} catch (IOException | CloneNotSupportedException e) {
					e.printStackTrace();
				}
			if (heroes.get(i) instanceof WarlockButton)
				try {
					players.add(new Warlock());
				} catch (IOException | CloneNotSupportedException e) {
					e.printStackTrace();
				}
		}

	}

	public void start() {
		board = new Board();
		view.addBoard(board, this);
		if (game.getCurrentHero().equals(p1)) {
			view.getH2().setVisible(false);
			view.getE2().setVisible(false);
			view.getM2().setVisible(false);
			view.repaint();
			view.validate();
		} else {
			view.getH1().setVisible(false);
			view.getE1().setVisible(false);
			view.getM1().setVisible(false);
			view.repaint();
			view.validate();
		}
		toggleHand();
	}

	public void toggleHand() {
		if (p1 == game.getCurrentHero()) {
			view.generateHand(p1, p2, this, true, false);
			view.generateField(p1, p2, this, true, false);
		} else {
			view.generateHand(p1, p2, this, false, true);
			view.generateField(p1, p2, this, false, true);
		}

	}

	public void friendlyToggle() {
		if (p1 == game.getCurrentHero()) {
			view.generateHand(p1, p2, this, true, false);
			view.generateField(p1, p2, this, false, false);
		} else {
			view.generateHand(p1, p2, this, false, true);
			view.generateField(p1, p2, this, false, false);
		}
	}

	public boolean isAttackClicked() {
		return attackClicked;
	}

	public boolean isPowerClicked() {
		return powerClicked;
	}

	public Hero getP1() {
		return p1;
	}

	public Hero getP2() {
		return p2;
	}

	@Override
	public void onGameOver() {
		sound.stopTheme();
		try {
			sound.win();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		if (p1.getCurrentHP() == 0)
			view.addOver("Player 2 wins !!!", this);
		if (p2.getCurrentHP() == 0)
			view.addOver("Player 1 wins !!!", this);

	}

	public static void main(String[] args) throws LineUnavailableException {
		new Controller();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		((JButton) arg0.getSource()).setForeground(Color.CYAN);
		if (arg0.getSource() instanceof HeroPowerOne) {
			view.addHeroPowerDescription(game.getCurrentHero(), 1);

		}
		if (arg0.getSource() instanceof HeroPowerTwo) {
			view.addHeroPowerDescription(game.getCurrentHero(), 2);

		}

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		((JButton) arg0.getSource()).setForeground(Color.WHITE);
		if (arg0.getSource() instanceof HeroPowerOne
				|| arg0.getSource() instanceof HeroPowerTwo)
			view.removeHeroPowerDescription();

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

}
