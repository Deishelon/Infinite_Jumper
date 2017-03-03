package net.gametutorial.andengineframework;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MyContactListener implements ContactListener {

    Player player;

    public MyContactListener(Player player) {
        this.player = player;
    }

    @Override
    public void beginContact(Contact contact) {
        //The first stage of the contact
        //In this method contact music, sounds, explosion animation

        if (checkContact(contact, Player.TYPE, Enemy.TYPE)) {  player.die(); }

    }

    @Override
    public void endContact(Contact contact) {
        //Two fixtures stop overlapping

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        //This method called after the collision detected

        if (checkContact(contact, Player.TYPE, Platform.TYPE)) {
                // Contact of player and platform
            if (!player.isDead() && player.isFalling() /* player.getBody().getLinearVelocity().y < 0*/) {
                ResourceManager.getInstance().soundJump.play();
                player.getBody().setLinearVelocity(new Vector2(0, 35));
            } else {
                contact.setEnabled(false); //Other cases contact did not happer
            }
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        //Called after preSolve
        //Cannot destroy the body / player / bullet
        //Will lead to crash

    }

    private boolean checkContact(Contact contact, String typeA, String typeB) {

        if (contact.getFixtureA().getBody().getUserData() instanceof CollidableEntity && contact.getFixtureB().getBody().getUserData() instanceof CollidableEntity) {
            CollidableEntity ceA = (CollidableEntity) contact.getFixtureA().getBody().getUserData();
            CollidableEntity ceB = (CollidableEntity) contact.getFixtureB().getBody().getUserData();
            if (typeA.equals(ceA.getType()) && typeB.equals(ceB.getType()) || typeA.equals(ceB.getType()) && typeB.equals(ceA.getType()))
            {
                return true;
            }
        }
        return false;
    }

}