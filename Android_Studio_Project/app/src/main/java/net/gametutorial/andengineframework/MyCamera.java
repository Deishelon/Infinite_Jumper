package net.gametutorial.andengineframework;

import org.andengine.engine.camera.SmoothCamera;
import org.andengine.entity.IEntity;

/**
 * Created by User on 21/01/2017.
 */

public class MyCamera extends SmoothCamera {

    private IEntity chaseEntity;
    private boolean gameOver = false;

    public MyCamera(float pX, float pY, float pWidth, float pHeight) {
        super(pX, pY, pWidth, pHeight, 3000f, 1000f, 1f);
        /*
        *   The two large number parameters are pixel per second and maximal
        *   velocities, and the last parameter is zoom velocity, which we are not going to use
         */
    }

    @Override
    public void setChaseEntity(IEntity pChaseEntity) {
        super.setChaseEntity(pChaseEntity);
        this.chaseEntity = pChaseEntity;
    }

    @Override
    public void updateChaseEntity() {
        if (chaseEntity != null) {
            if (chaseEntity.getY() > getCenterY()) {
                //Check whether the character moved upwards
                setCenter(getCenterX(), chaseEntity.getY());

            } else if (chaseEntity.getY() < getYMin() && !gameOver) {
                //Check for the falling
                setCenter(getCenterX(), chaseEntity.getY() - getHeight());
                gameOver = true;
            }
        }
    }

    //Reset camera => so can restart game
    @Override
    public void reset() {
        super.reset();
        gameOver = false;
        set(0, 0, GameActivity.CAMERA_WIDTH, GameActivity.CAMERA_HEIGHT);
        setCenterDirect(GameActivity.CAMERA_WIDTH / 2, GameActivity.CAMERA_HEIGHT / 2); // This method to stop any smooth movement of the camera that might be in progress
    }
}
