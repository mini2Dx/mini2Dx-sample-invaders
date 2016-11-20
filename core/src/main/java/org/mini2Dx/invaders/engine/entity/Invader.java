/**
 * Copyright (c) 2015, mini2Dx Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name of the mini2Dx nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.mini2Dx.invaders.engine.entity;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.invaders.engine.CollisionResolver;
import org.mini2Dx.invaders.engine.CollisionTracker;
import org.mini2Dx.invaders.engine.GameObject;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author Thomas Cashman
 */
public class Invader extends GameObject {
	private static final long serialVersionUID = 1236420163028788295L;
	public static final float WIDTH = 48f;
	public static final float HEIGHT = 39f;
	private static final float SPEED = 0.5f;
	
	private final float minX;
	private final float maxX;
	private float targetY;
	
	private Sprite sprite;
	private Direction direction;

	public Invader(float x, float y) {
		super(x, y, WIDTH, HEIGHT);
		targetY = y;
		
		minX = x - (WIDTH * 3f);
		maxX = x + (WIDTH * 3f);
		direction = Direction.RIGHT;
	}
	
	@Override
	public void loadTexture(AssetManager assetManager) {
		sprite = new Sprite(assetManager.get("textures/enemy.png", Texture.class));
	}

	@Override
	public void behave(GameContainer gc, float delta) {
		if(getX() >= maxX) {
			if(direction == Direction.RIGHT) {
				targetY += HEIGHT;
				direction = Direction.LEFT;
			}
		}
		if(getX() <= minX) {
			if(direction == Direction.LEFT) {
				targetY += HEIGHT;
				direction = Direction.RIGHT;
			}
		}
		
		if(getY() < targetY) {
			setY(getY() + SPEED);
		} else {
			switch(direction) {
			case LEFT:
				setX(getX() - SPEED);
				break;
			case RIGHT:
				setX(getX() + SPEED);
				break;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		sprite.setPosition(getRenderX(), getRenderY());
		g.drawSprite(sprite);
	}

	@Override
	public void notifyCollision(CollisionTracker collisionTracker, GameObject gameObject) {
		gameObject.handleCollisionWith(this, collisionTracker);
	}

	@Override
	public void handleCollisionWith(Player player, CollisionTracker collisionTracker) {
		CollisionResolver.resolveCollision(collisionTracker, this, player);
	}

	@Override
	public void handleCollisionWith(Invader invader, CollisionTracker collisionTracker) {
		CollisionResolver.resolveCollision(collisionTracker, this, invader);
	}

	@Override
	public void handleCollisionWith(Laser shot, CollisionTracker collisionTracker) {
		CollisionResolver.resolveCollision(collisionTracker, this, shot);
	}

	@Override
	public void handleCollisionWith(Asteroid asteroid, CollisionTracker collisionTracker) {
		CollisionResolver.resolveCollision(collisionTracker, this, asteroid);
	}
	
	private enum Direction {
		LEFT, RIGHT
	}
}
