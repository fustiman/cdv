package es.cdv;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Main extends Game {
	
	@Override
	public void create () {
        //Creamos al empezar una pantalla
        setScreen(new GameScreen());
	}

	@Override
	public void render () {
        //OpenGL limpia la pantalla
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Cogemos la pantalla actual y la renderizamos pasándole el tiempo
        //que ha pasado desde la última vez que se ha actualizado
		getScreen().render(Gdx.graphics.getDeltaTime());

	}
}
