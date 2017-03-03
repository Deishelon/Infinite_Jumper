package net.gametutorial.andengineframework;

import org.andengine.extension.physics.box2d.util.constants.PhysicsConstants;

/**
 * Created by User on 26/01/2017.
 */

public class Utils {

    public static void wraparound(CollidableEntity ce) {
        if (ce.getX() + ce.getWidth() / 2 < 0) {
            ce.getBody().setTransform((GameActivity.CAMERA_WIDTH + ce.getWidth() / 2) / PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT, ce.getBody().getPosition().y, 0);
        } else if (ce.getX() - ce.getWidth() / 2 > GameActivity.CAMERA_WIDTH) {
            ce.getBody().setTransform((- ce.getWidth() / 2) /
                    PhysicsConstants.PIXEL_TO_METER_RATIO_DEFAULT,
                    ce.getBody().getPosition().y, 0);
        }
    }
}