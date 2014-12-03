package sonidoContinuo;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.GameComponent;

public class Musica extends GameComponent<SnowBrosScene> {

	AudioClip sonido;

	public Musica() {
		URL url = Musica.class.getResource("stage1.wav");
		sonido = Applet.newAudioClip(url);
	}

	public void reproducir() {
		sonido.loop();
		sonido.play();
		sonido.loop();
	}

	public void parar() {
		this.sonido.stop();
	}

	public void actualizar(Integer numeroNivel) {

		if (this.getScene().nivelBoss) {
			this.parar();
			URL url = Musica.class.getResource("bossStage.wav");
			sonido = Applet.newAudioClip(url);
			this.reproducir();
		} else if (numeroNivel == 5) {
			this.parar();
			URL url = Musica.class.getResource("stage2.wav");
			sonido = Applet.newAudioClip(url);
			this.reproducir();
		} else if (numeroNivel == 10) {
			this.parar();
			URL url = Musica.class.getResource("stage5.3.wav");
			sonido = Applet.newAudioClip(url);
			this.reproducir();
		}

	}

}