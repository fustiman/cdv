package es.cdv;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Block extends Actor {

    //Tamaño, depende del sprite
    public static float width = 64;
    public static float height = 64;

    //Textura para dibujar, sin animación
    TextureRegion texture;

    public Block (float x, float y) {
        setBounds(x, y, width, height);

        //cargamos a lo bruto, cuando tengamos más será de otra forma
        texture = AssetsLoader.tiles[0][1];
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
