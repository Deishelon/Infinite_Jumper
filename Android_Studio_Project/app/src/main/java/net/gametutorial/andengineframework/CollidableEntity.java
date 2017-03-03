/**
 * Copyright (C) 2013 Martin Varga <android@kul.is>
 */
package net.gametutorial.andengineframework;

import org.andengine.entity.IEntity;

import com.badlogic.gdx.physics.box2d.Body;

public interface CollidableEntity extends IEntity {
	public void setBody(Body body);
	public Body getBody();
	public String getType();
}
