package sonidoContinuo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.sound.Sound;

public class MusicaFondo extends GameComponent<SnowBrosScene> {

	Timer timer;
	Sound gameSound;

	public MusicaFondo(Sound sound) {
		this.gameSound = sound;
		timer = new Timer(178000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				timer.setRepeats(true);
				gameSound.play();
			}
		});
	}

	public void reproducir() {
		gameSound.play();
		timer.start();
	}

	public void parar() {
		timer.stop();

		this.destroy();
	}

	public Sound getGameSound() {
		return gameSound;
	}

	public void setGameSound(Sound gameSound) {
		this.gameSound = gameSound;
	}

}
