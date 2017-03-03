package net.gametutorial.andengineframework;

import com.badlogic.gdx.physics.box2d.Body;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

//This class is similar to Platform class

public class Enemy extends AnimatedSprite implements CollidableEntity {

    public static final String TYPE = "ENEMY";
    private Body body;


    public Enemy(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
    }

    @Override
    public void setBody(Body body) {
        this.body = body;
    }

    @Override
    public Body getBody() {
        return body;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    protected void onManagedUpdate(float pSecondsElapsed) {
        super.onManagedUpdate(pSecondsElapsed);
        Utils.wraparound(this);
    }
}
