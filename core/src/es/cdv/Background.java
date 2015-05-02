package es.cdv;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by cyberman on 2/05/15.
 */
public class Background extends Actor {

    TextureRegion texture;

    public Background() {
        //para que ocupe la altura entera
        setBounds(0, 64, 4160, 416);

        texture = AssetsLoader.background;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
