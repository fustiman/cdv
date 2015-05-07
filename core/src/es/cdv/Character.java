package es.cdv;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;

public class Character extends Actor {

    //Valores y vectores internos de posición
    float gravity = -1000;
    float xAccel = 1000; //Aceleración horizontal
    float jumpSpeed = 600;
    float maxSpeed = 250;

    Vector2 acceleration = new Vector2(0, gravity);
    Vector2 speed = new Vector2(0, 0);
    Vector2 position;

    //Tamaño, depende del sprite
    float width = 128;
    float height = 128;

    //Animaciones
    Animation[] animation;
    float frameDuration = 1/10f;
    float currentFrameTime = 0;
    int currentCharacter = 0;

    boolean flipped;

    public Character (float x, float y) {
        position = new Vector2(x, y);

        //ponemos la posición y el tamaño al actor
        setBounds(x, y, width, height);

        //Cargamos la animación, para más animaciones se complica la cosa, pero no mucho
        animation = new Animation[2];

        //Cargar un array
        animation[0] = new Animation(frameDuration, AssetsLoader.playerSummer[0]); //La primera fila

        //Cargar con texturas sueltas
        animation[1] = new Animation(frameDuration,
                            AssetsLoader.playerAutumn[0][0],
                            AssetsLoader.playerAutumn[0][1],
                            AssetsLoader.playerAutumn[0][2],
                            AssetsLoader.playerAutumn[0][3],
                            AssetsLoader.playerAutumn[0][4],
                            AssetsLoader.playerAutumn[0][5]);

    }

    /*
     *  Actualiza la acelación horizontal.
     *  Se le pasa un valor entre -1 y 1 dependiendo de la dirección
     */
    public void moveX(float amount) {
        acceleration.x = xAccel * amount;
    }

    public void stopX() {
        acceleration.x = 0;
        speed.x = 0;
    }

    public void jump() {
        //Si está en lo que hemos llamado suelo, con un margen de 1 pixel
        if (MathUtils.isEqual(getY(), Block.height, 1)) {
            speed.y = jumpSpeed;
        }
    }

    public void testAction() {
        //Test tonto de acciones
        if (getColor().a == 1 || getColor().a == 0) {
            AlphaAction disappear = new AlphaAction();

            if (getColor().a == 1)
                disappear.setAlpha(0);
            else
                disappear.setAlpha(1);

            disappear.setDuration(0.5f);
            addAction(disappear);
        }
    }

    public void changeCharacter() {
        currentCharacter = (currentCharacter+1)%animation.length;
    }

    @Override
    public void act(float delta) {
        //actualizar el tiempo de las animaciones
        currentFrameTime += delta;

        //speed.x += acc.x * delta;
        //actualizamos la velocidad y la posición en función del tiempo
        speed.mulAdd(acceleration, delta);

        /*
        if (speed.x > 0) {
            speed.x = Math.min(maxSpeed, speed.x);
        } else {
            speed.x = Math.max(-maxSpeed, speed.x);
        }
           //esto equivale a:
        */
        speed.x = MathUtils.clamp(speed.x, -maxSpeed, maxSpeed);

        flipSprite();

        position.mulAdd(speed, delta);


        //detección de colisión con suelo fijado, hay que mejorarla

        //si la posición a la cual vamos a actualizar está por debajo del suelo
        //Block.height es un valor arbitrário y que deberemos cambiar cuando
        //hagamos la implementación seria
        if (position.y < Block.height) {
            speed.y = 0; //paramos de bajar
            position.y = Block.height; //en lugar pasar el borde nos ponemos justo en él
        }


        //actualizamos el valor real de la posición
        setPosition(position.x, position.y);

        super.act(delta);
    }

    private void flipSprite() {
        if (speed.x > 0)
            flipped = false;
        else if (speed.x < 0)
            flipped = true;
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {

        //Podemos tintar la imagen del color que queramos con el nivel de transparencia que queramos.
        batch.setColor(getColor());
        TextureRegion frame = animation[currentCharacter].getKeyFrame(currentFrameTime, true);

        frame.flip(frame.isFlipX() != flipped, false);

        batch.draw(frame, getX(), getY(), getWidth(), getHeight());

        //Luego hay que volver a ponerlo en blanco para que no afecte al resto.
        batch.setColor(Color.WHITE);
    }

}
