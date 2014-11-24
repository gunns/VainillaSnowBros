package componentes;

import java.awt.Color;
import java.awt.Font;

import com.uqbar.snowBros.SnowBrosScene;
import com.uqbar.vainilla.DeltaState;
import com.uqbar.vainilla.GameComponent;
import com.uqbar.vainilla.appearances.Label;

public class Puntaje extends GameComponent<SnowBrosScene>{

Integer puntos;
Bros bros;

public Puntaje(Bros bros, double x, double y)
{
this.bros = bros;
puntos = 0;

Font font = new Font("Verdana", Font.BOLD + Font.ITALIC, 20);
Label label =new Label(font ,Color.blue , "score" +(this.puntos).toString());

this.setAppearance(label);

this.setX(x);
this.setY(y);

}

@Override
public void update(DeltaState deltaState) {
 Font font = new Font("Verdana", Font.BOLD + Font.ITALIC, 20);
 this.setAppearance(new Label(font ,Color.blue ,"score " +(this.puntos).toString()));

}


public void sumarPuntaje(Integer puntos)
{
this.puntos = this.puntos + puntos; 
}
 
}