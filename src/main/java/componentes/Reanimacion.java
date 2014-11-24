package componentes;
import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;

public class Reanimacion extends GameComponent<SnowBrosScene>{
	
Bros bros;
Integer tiempoReanimacion;
SnowBrosScene sns;

public Reanimacion(Bros bros, SnowBrosScene sns){
	this.bros = bros;
	this.sns = sns;
	tiempoReanimacion = 400;
	
}

public void update(DeltaState deltaState)
	{
	if(this.tiempoReanimacion < 1)
		{
		//reposicionarBros
		Sprite spriteVive = Sprite.fromImage("BrosDrc.png");
		bros.setAppearance(spriteVive);
		bros.setX(sns.getGameDimension().getWidth()/2-bros.getAppearance().getWidth()/2);
		bros.setY(sns.getGameDimension().getHeight()-(bros.getAppearance().getHeight())-25);
		bros.setZ(1);
		sns.addComponent(bros);
		this.destroy();
		}
		else
			{
			tiempoReanimacion = tiempoReanimacion -1;
			}
	}


}
