package es.cdv;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by cyberman on 2/05/15.
 */
public class AssetsLoader {

    public static TextureRegion[][] playerSummer;
    public static TextureRegion[][] playerAutumn;

    public static TextureRegion[][] tiles;

    public static TextureRegion background;

    public static void loadAssets() {
        //Cargamos en un array una imagen dividida en trozos de 128x128
        playerSummer = getSplit("player_summer.png", 128, 128);
        playerAutumn = getSplit("player_autumn.png", 128, 128);
        tiles = getSplit("tiles.jpg", 64, 64);

        background = new TextureRegion(new Texture("background.jpg"));
    }

    private static TextureRegion[][] getSplit(String name, int width, int height) {
        return new TextureRegion(new Texture(name)).split(width, height);
    }
}
