package sonidoContinuo;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class MusicaInicio {
	AudioClip sonido;

	public MusicaInicio() throws Exception{
		//URL url;
		URL url = Musica.class.getResource("snowBrosTitle.wav");
		sonido = Applet.newAudioClip(url);
		
		//sonido.play();
		//Thread.sleep(1000);
		//sonido.stop();
	}
	
	
	public void reproducir(){
		sonido.loop();
		sonido.play();
	}
	public void parar(){
		this.sonido.stop();
	}


}
