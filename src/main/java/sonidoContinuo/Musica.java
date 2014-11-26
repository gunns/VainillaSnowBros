package sonidoContinuo;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.GameComponent;


public class Musica extends GameComponent<SnowBrosScene>{
	
	AudioClip sonido;

	public Musica() throws Exception{
		//URL url;
		URL url = Musica.class.getResource("stage1.wav");
		sonido = Applet.newAudioClip(url);
		
		//sonido.play();
		//Thread.sleep(1000);
		//sonido.stop();
	}
	
	
	public void reproducir(){
		sonido.play();
		sonido.loop();
	}
	public void parar(){
		this.sonido.stop();
	}
	
	
	
}