package Cartel;

import java.awt.Dimension;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.sound.Sound;
import com.uqbar.vainilla.sound.SoundBuilder;

public class CartelWin extends GameComponent<SnowBrosScene> {

	Sprite imagen;
	Sound sonido;

	public CartelWin(Dimension dim) {
		imagen = Sprite.fromImage("winGame.png");
		this.setAppearance(imagen);
		this.setX(dim.getWidth() / 2 - this.getAppearance().getWidth() / 2);
		this.setY(dim.getHeight() / 2 - this.getAppearance().getHeight());
		sonido = new SoundBuilder().buildSound(this.getClass().getClassLoader()
				.getResourceAsStream("winGame.wav"));
		sonido.play();
	}

}
