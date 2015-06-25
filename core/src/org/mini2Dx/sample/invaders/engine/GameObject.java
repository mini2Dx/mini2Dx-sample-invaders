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
package org.mini2Dx.sample.invaders.engine;

import org.mini2Dx.core.engine.PositionChangeListener;
import org.mini2Dx.core.engine.Positionable;
import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.sample.invaders.engine.entity.Asteroid;
import org.mini2Dx.sample.invaders.engine.entity.Invader;
import org.mini2Dx.sample.invaders.engine.entity.Player;
import org.mini2Dx.sample.invaders.engine.entity.Laser;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Thomas Cashman
 */
public abstract class GameObject implements Positionable {
	private CollisionBox collisionBox;
	private Vector2 velocity;
	
	public GameObject(float x, float y, float width, float height) {
		collisionBox = new CollisionBox(x, y, width, height);
		velocity = new Vector2();
	}
	
	@Override
	public void update(GameContainer gc, float delta) {
		collisionBox.preUpdate();
		behave(gc, delta);
	}

	@Override
	public void interpolate(GameContainer gc, float alpha) {
		collisionBox.interpolate(gc, alpha);
	}
	
	public abstract void behave(GameContainer gc, float delta);
	
	public abstract void render(Graphics g);
	
	public abstract void collidedWith(Player player);
	
	public abstract void collidedWith(Invader invader);
	
	public abstract void collidedWith(Laser shot);
	
	public abstract void collidedWith(Asteroid asteroid);

	@Override
	public float getDistanceTo(Positionable positionable) {
		return collisionBox.getDistanceTo(positionable);
	}

	@Override
	public float getX() {
		return collisionBox.getX();
	}

	@Override
	public float getY() {
		return collisionBox.getY();
	}

	@Override
	public <T extends Positionable> void addPostionChangeListener(
			PositionChangeListener<T> listener) {
		collisionBox.addPostionChangeListener(listener);
	}

	@Override
	public <T extends Positionable> void removePositionChangeListener(
			PositionChangeListener<T> listener) {
		collisionBox.removePositionChangeListener(listener);
	}
}
