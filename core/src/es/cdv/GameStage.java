package es.cdv;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameStage extends Stage {

    Character player;
    Background background;

    Block[][] blocks;

    boolean cameraLocked = true;

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
            case Keys.UP:
                zoomCamera(((OrthographicCamera) getCamera()).zoom, 2);
                break;
            case Keys.DOWN:
                zoomCamera(((OrthographicCamera) getCamera()).zoom, -2f);
                break;
            case Keys.LEFT:
                cameraLocked = false;
                panCamera(getCamera().position.x, getCamera().position.x + 400);
                break;
            case Keys.RIGHT:
                cameraLocked = false;
                panCamera(getCamera().position.x, getCamera().position.x - 400);
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
            case Keys.LEFT:
            case Keys.RIGHT:
                cameraLocked = true;
                break;
        }
        return true;
    }

    private void zoomCamera(final float initial, final float factor) {

        Action action = new TemporalAction(1f, Interpolation.bounceOut) {

            @Override
            protected void update(float percent) {
                float zoom = initial;

                zoom += factor * percent;

                zoom = MathUtils.clamp(zoom, 1, 4);
                ((OrthographicCamera) getCamera()).zoom = zoom;
            }


        };

        addAction(action);
    }

    private void panCamera(final float initial, final float finalPos) {

        final float dist = finalPos - initial;

        Action action = new TemporalAction(1f, Interpolation.linear) {

            @Override
            protected void update(float percent) {
                float posX = initial;

                posX += dist * percent;


                getCamera().position.x = posX;
            }


        };

        addAction(action);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        // Actualizamos la posición de la cámara para que siga al personaje
        if (cameraLocked)
            panCamera(getCamera().position.x, ((int) player.getX() + player.getWidth()/2f));
        //getCamera().position.y = ((int) player.getY());
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
