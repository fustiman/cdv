package es.cdv;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameStage extends Stage {

    Character player;
    Background background;

    Block[][] blocks;

    public GameStage(Viewport viewport) {
        super(viewport);

        background = new Background();
        addActor(background);

        //Creamos y añadimos al escenario el actor que queramos
        player = new Character(50, 200);
        addActor(player);

        //Creamos a lo bruto un montón de suelo
        blocks = new Block[20][100];
        for (int i = 0; i < 100; i++) {
            blocks[0][i] = new Block(Block.width * i, 0);
            addActor(blocks[0][i]);
        }

        // Le decimos a la aplicación que aplique la entrada a este escenario.
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int keyCode) {
        switch (keyCode) {
            case Keys.A:
                player.moveX(-1);
                break;
            case Keys.D:
                player.moveX(1);
                break;
            case Keys.W:
                player.jump();
                break;
            case Keys.S:
                player.changeCharacter();
                break;
            case Keys.K:
                player.testAction();
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keyCode) {
        switch (keyCode) {
            case Keys.A:
            case Keys.D:
                player.stopX();
                break;
        }
        return true;    }

    @Override
    public void act(float delta) {
        super.act(delta);

        // Actualizamos la posición de la cámara para que siga al personaje
        getCamera().position.x = ((int) player.getX() + player.getWidth()/2f);
        //getCamera().position.y = ((int) player.getY());
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
