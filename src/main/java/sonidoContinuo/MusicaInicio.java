package sonidoContinuo;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class MusicaInicio {
	AudioClip sonido;

	public MusicaInicio() throws Exception{
		URL url = Musica.class.getResource("snowBrosTitle.wav");
		sonido = Applet.newAudioClip(url);

	}
	
	
	public void reproducir(){
		sonido.loop();
		sonido.play();
		sonido.loop();
	}
	public void parar(){
		this.sonido.stop();
	}


}
