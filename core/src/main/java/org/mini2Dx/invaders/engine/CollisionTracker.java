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
package org.mini2Dx.invaders.engine;

import java.util.ArrayList;
import java.util.List;

import org.mini2Dx.core.collisions.RegionQuadTree;
import org.mini2Dx.core.engine.PositionChangeListener;
import org.mini2Dx.core.game.GameContainer;

/**
 *
 * @author Thomas Cashman
 */
public class CollisionTracker implements PositionChangeListener<GameObject> {
	private RegionQuadTree<GameObject> trackedObjects;
	private List<GameObject> objectsMoved;
	private List<GameObject> detectedCollisions;
	
	private boolean playerDestroyed;
	
	public CollisionTracker(GameContainer gc) {
		trackedObjects = new RegionQuadTree<GameObject>(8, 0, 0, gc.getWidth(), gc.getHeight());
		objectsMoved = new ArrayList<GameObject>();
		detectedCollisions = new ArrayList<GameObject>();
		playerDestroyed = false;
	}
	
	public void preUpdate() {
		objectsMoved.clear();
	}
	
	public void postUpdate() {
		for(int i = objectsMoved.size() - 1; i >= 0; i--) {
			GameObject objectMoved = objectsMoved.get(i);
			
			detectedCollisions.clear();
			trackedObjects.getElementsWithinArea(detectedCollisions, objectMoved);
			for(GameObject collision : detectedCollisions) {
				objectMoved.notifyCollision(this, collision);
			}
		}
	}
	
	public void add(GameObject obj) {
		trackedObjects.add(obj);
	}
	
	public void remove(GameObject obj) {
		trackedObjects.remove(obj);
		objectsMoved.remove(obj);
	}

	@Override
	public void positionChanged(GameObject gameObject) {
		objectsMoved.add(gameObject);
	}

	public boolean isPlayerDestroyed() {
		return playerDestroyed;
	}

	public void setPlayerDestroyed(boolean playerDestroyed) {
		this.playerDestroyed = playerDestroyed;
	}

}
