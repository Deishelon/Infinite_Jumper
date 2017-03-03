package net.gametutorial.andengineframework;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Factory for the Enemy
 * Same as others Factory
 * Generates a Physics body
 *
 *  Again, we use a single instance of this factory. We need a method to return the
    single instance and a method to create the factory itself. Lastly, we need a method
    to create enemies. We have seen all this code before. The only difference here is that
    we are calling the animate() method to animate the ﬂy—change frames each 75
    milliseconds and we set the enemy to move with a small velocity leftwards.
 */

public class EnemyFactory {

    public static final FixtureDef ENEMY_FIXTURE = PhysicsFactory.createFixtureDef(1f, 0f, 1f, true);
    private static EnemyFactory INSTANCE = new EnemyFactory();
    private PhysicsWorld physicsWorld;
    private VertexBufferObjectManager vbom;
    private EnemyFactory() { }

    public static EnemyFactory getInstance() {
        return INSTANCE;
    }
    public void create(PhysicsWorld physicsWorld, VertexBufferObjectManager vbom) {
        this.physicsWorld = physicsWorld;
        this.vbom = vbom;
    }
    public Enemy createEnemy(float x, float y) {
        Enemy enemy = new Enemy(x, y, ResourceManager.getInstance().enemyTextureRegion, vbom);
        Body enemyBody = PhysicsFactory.createBoxBody(physicsWorld, enemy, BodyDef.BodyType.KinematicBody, ENEMY_FIXTURE);
        enemyBody.setUserData(enemy);
        physicsWorld.registerPhysicsConnector(new PhysicsConnector(enemy,enemyBody));
        enemy.setBody(enemyBody);
        enemyBody.setLinearVelocity(-2, 0);
        enemy.animate(75);
        enemy.setZIndex(1);
        return enemy;
    }

}
