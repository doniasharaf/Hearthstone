package view;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	private Clip clip;
	private Clip winClip;

	public void heroButton() throws LineUnavailableException {

		try {
			File effect1 = new File("SFX/heroButton.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(effect1));
			clip.start();
		} catch (IOException | UnsupportedAudioFileException error) {
		}
	}

	public void confirmHero() throws LineUnavailableException {

		try {
			File effect1 = new File("SFX/confirmHero.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(effect1));
			clip.start();
		} catch (IOException | UnsupportedAudioFileException error) {
		}
	}

	public void startTheme() throws LineUnavailableException {

		try {
			File effect1 = new File("SFX/Theme.wav");
			Clip clip = AudioSystem.getClip();
			this.clip = clip;
			clip.open(AudioSystem.getAudioInputStream(effect1));
			clip.start();
			clip.loop(1000000000);
		} catch (IOException | UnsupportedAudioFileException error) {
		}
	}

	public void win() throws LineUnavailableException {

		try {
			File effect1 = new File("SFX/win.wav");
			Clip clip = AudioSystem.getClip();
			this.winClip = clip;
			clip.open(AudioSystem.getAudioInputStream(effect1));
			clip.start();
			clip.loop(1000000000);
		} catch (IOException | UnsupportedAudioFileException error) {
		}
	}

	public void stopWinTheme() {
		winClip.stop();
	}

	public void stopTheme() {
		clip.stop();
	}

	public void attack() throws LineUnavailableException {

		try {
			File effect1 = new File("SFX/Banana.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(effect1));
			clip.start();
		} catch (IOException | UnsupportedAudioFileException error) {
		}
	}
	public void summon() throws LineUnavailableException {

		try {
			File effect1 = new File("SFX/summon.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(effect1));
			clip.start();
		} catch (IOException | UnsupportedAudioFileException error) {
		}
	}

	public void endTurn() throws LineUnavailableException {

		try {
			File effect1 = new File("SFX/EndTurn.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(effect1));
			clip.start();
		} catch (IOException | UnsupportedAudioFileException error) {
		}
	}

	public void cardBack() throws LineUnavailableException {

		try {
			File effect1 = new File("SFX/card.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(effect1));
			clip.start();
		} catch (IOException | UnsupportedAudioFileException error) {
		}
	}

	public void myCard() throws LineUnavailableException {

		try {
			File effect1 = new File("SFX/myCard.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(effect1));
			clip.start();

		} catch (IOException | UnsupportedAudioFileException error) {
		}
	}

	public void castSpell() throws LineUnavailableException {

		try {
			File effect1 = new File("SFX/castSpell.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(effect1));
			clip.start();

		} catch (IOException | UnsupportedAudioFileException error) {
		}
	}

	public void attackHero() throws LineUnavailableException {

		try {
			File effect1 = new File("SFX/attackHero.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(effect1));
			clip.start();

		} catch (IOException | UnsupportedAudioFileException error) {
		}
	}

	public void polymorph() throws LineUnavailableException {

		try {
			File effect1 = new File("SFX/polymorph.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(effect1));
			clip.start();

		} catch (IOException | UnsupportedAudioFileException error) {
		}
	}

	public void startGame() throws LineUnavailableException {

		try {
			File effect1 = new File("SFX/startGame.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(effect1));
			clip.start();
		} catch (IOException | UnsupportedAudioFileException error) {
		}
	}
	public void heroPower() throws LineUnavailableException {

		try {
			File effect1 = new File("SFX/heroPower.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(effect1));
			clip.start();
		} catch (IOException | UnsupportedAudioFileException error) {
		}
	}

}
