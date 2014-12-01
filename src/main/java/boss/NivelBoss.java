package boss;

import java.awt.Dimension;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;

public class NivelBoss extends GameComponent<SnowBrosScene>{
	Sprite imagenFondo;
	//sonido fondo
	
	
	Integer tiempoEnAparecerBoss;
	boolean bossAparece;
	//MusicaBoss
	//IMPORTANTE: CREAR  en SnowBrosscene una variable nivelBoss seteada en false, se setea en true recien al llegar a este nivel, por lo que
	//el bros no se irá para arriba al terminar con el boss ni exterminando enemigos;
	//usar !nivelBoss en enemigos exterminados.
	
	public NivelBoss()
	{
		
		imagenFondo = Sprite.fromImage("bossStageImage.png");
		
				//this.backGround = new GameComponent<GameScene>(sprite.scale(0.9, 1.2), 0, 0);
	tiempoEnAparecerBoss = 600;
	bossAparece = false;
	//setearmusicadelboss
	// setear una imagen de fondo
	}
	//crear un nivel para el boss
	
	public void update(DeltaState deltaState)
	{	
		if(!bossAparece)
		{
		if(tiempoEnAparecerBoss < 0)
			{
			//aparece el boss: se crea el boss y aparece en la parte superior derecha en el estado caida brusca: cae rapidamente
			//hasta el suelo, luego de caer, suena un estruendoso sonido 
			//musica comienza a sonar
			Boss boss = new Boss(this.getScene().getGameDimension());
			this.getScene().addComponent(boss);
			bossAparece = true;
			}
		else
			{
			tiempoEnAparecerBoss = tiempoEnAparecerBoss - 1;
			//no c, podría suceder algo mientras tanto.
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
