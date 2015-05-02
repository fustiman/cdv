package es.cdv;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class GameScreen implements Screen {

    GameStage stage; //Escenario donde pondremos todos los actores

    @Override
    public void show() {
        AssetsLoader.loadAssets();
        stage = new GameStage(new ExtendViewport(640, 480)); //Tamaño en pixels de lo que se verá en pantalla
    }

    @Override
    public void render(float delta) {
        //"actuamos" -> actualizamos las posiciones
        stage.act(delta);

        //dibujamos después de actualizar
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height); //cuando reescalamos actualizamos el viewport
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        //necesario para que el garbage collector lo pueda eliminar
        stage.dispose();
    }
}
