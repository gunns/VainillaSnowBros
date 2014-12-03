package componentes;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;

public class Explosion extends GameComponent<SnowBrosScene> {

	Integer duracion;

	public Explosion(double x, double y) {
		duracion = 150;
		this.setX(x);
		this.setY(y);
		Sprite sprite = Sprite.fromImage("explode.png");
		duracion = 150;
		this.setAppearance(sprite);

	}

	public void update(DeltaState deltaState) {
		if (duracion < 1) {
			this.destroy();
		} else {
			this.duracion = this.duracion - 1;
		}

	}

}
