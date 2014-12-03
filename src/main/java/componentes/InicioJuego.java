package componentes;

import java.awt.Dimension;

import sonidoContinuo.MusicaInicio;
import Cartel.CartelPresioneStart;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Sprite;
import com.uqbar.vainilla.events.constants.Key;

public class InicioJuego extends GameComponent<SnowBrosScene>{

	
	public Sprite imagenFondo;
	public MusicaInicio musica;
	public CartelPresioneStart boton;
	boolean agregueCartel;
	
	
	public InicioJuego(Dimension dim) throws Exception
	{
		imagenFondo = Sprite.fromImage("inicioSnowBros.png");
		musica = new MusicaInicio();
		
		boton = new CartelPresioneStart(dim);
		agregueCartel = false;
	
	}
	
	public void reproducir(){
		musica.reproducir();
	}
	public void parar(){
		musica.parar();
	}
	
	public void update(DeltaState deltaState)
	{
		if(!this.agregueCartel)
			{
			this.getScene().addComponent(boton);
			this.agregueCartel = true;
			}
		if (deltaState.isKeyPressed(Key.P))
		{
		this.getScene().comenzarJuego();
		this.parar();
		this.destroy();
		boton.destroy();
		}
	}

	public Sprite getImagenFondo() {
		return imagenFondo;
	}

	public void setImagenFondo(Sprite imagenFondo) {
		this.imagenFondo = imagenFondo;
	}

	public MusicaInicio getMusica() {
		return musica;
	}

	public void setMusica(MusicaInicio musica) {
		this.musica = musica;
	}
	
	
	
}



