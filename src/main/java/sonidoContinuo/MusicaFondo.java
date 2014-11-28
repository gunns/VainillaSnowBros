package sonidoContinuo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.Timer;




import javax.swing.Timer;

import com.uqbar.snowBros.SnowBrosScene;
//import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.sound.Sound;
//import com.uqbar.vainilla.sound.SoundBuilder;
//import com.uqbar.vainilla.sound.SoundPlay;
//import com.uqbar.vainilla.sound.SoundPlayer;

public class MusicaFondo extends GameComponent<SnowBrosScene>{
	
	
	Timer timer;
	Sound gameSound;
	//SoundPlay sonido;
	
	
	
	
	public MusicaFondo(Sound sound)
		{
		this.gameSound= sound;
		timer = new Timer(178000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				timer.setRepeats(true);
				gameSound.play();
			}
			
			
			//sonido = new SoundPlay(gameSound, 1);
			
			//sonido.setWrittenSamples(44000);
		});
		}	
	
	public void reproducir()
	{
		gameSound.play();
		timer.start();
	}
	
	public void parar()
	{
		//SoundPlayer.INSTANCE.parar(this.gameSound);
		timer.stop();
		//this.gameSound.parar();
		//Sound sonidoGameOver = new SoundBuilder().buildSound(this.getClass().getClassLoader().getResourceAsStream("gameOver.wav"));
	    //gameSound = sonidoGameOver;
		//gameSound.play();
	    this.destroy();
	}

	public Sound getGameSound() {
		return gameSound;
	}

	public void setGameSound(Sound gameSound) {
		this.gameSound = gameSound;
	}
	
	
	
}
