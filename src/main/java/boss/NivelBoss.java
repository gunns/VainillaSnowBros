package boss;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;

public class NivelBoss extends GameComponent<SnowBrosScene>{
	Sprite imagenFondo;
	
	
	Integer tiempoEnAparecerBoss;
	boolean bossAparece;

	public NivelBoss()
	{
		
		imagenFondo = Sprite.fromImage("bossStageImage.png");
		
	tiempoEnAparecerBoss = 600;
	bossAparece = false;
	}
	
	public void update(DeltaState deltaState)
	{	
		if(!bossAparece)
		{
		if(tiempoEnAparecerBoss < 0)
			{
			Boss boss = new Boss(this.getScene().getGameDimension());
			this.getScene().addComponent(boss);
			bossAparece = true;
			}
		else
			{
			tiempoEnAparecerBoss = tiempoEnAparecerBoss - 1;
			}
	}
	
	
	}

	public Sprite getImagenFondo() {
		return imagenFondo;
	}

	public void setImagenFondo(Sprite imagenFondo) {
		this.imagenFondo = imagenFondo;
	}
	
	
}
