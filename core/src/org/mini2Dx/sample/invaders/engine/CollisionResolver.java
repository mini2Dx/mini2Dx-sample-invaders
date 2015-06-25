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

import org.mini2Dx.sample.invaders.engine.entity.Asteroid;
import org.mini2Dx.sample.invaders.engine.entity.Invader;
import org.mini2Dx.sample.invaders.engine.entity.Laser.LaserSource;
import org.mini2Dx.sample.invaders.engine.entity.Player;
import org.mini2Dx.sample.invaders.engine.entity.Laser;

/**
 *
 */
public class CollisionResolver {

	public static void resolveCollision(CollisionTracker collisionTracker,
			Asteroid asteroid1, Asteroid asteroid2) {

	}

	public static void resolveCollision(CollisionTracker collisionTracker,
			Asteroid asteroid, Invader invader) {

	}

	public static void resolveCollision(CollisionTracker collisionTracker,
			Asteroid asteroid, Player player) {

	}

	public static void resolveCollision(CollisionTracker collisionTracker,
			Asteroid asteroid, Laser laser) {

	}

	public static void resolveCollision(CollisionTracker collisionTracker,
			Invader invader, Player player) {
		// TODO Auto-generated method stub

	}

	public static void resolveCollision(CollisionTracker collisionTracker,
			Invader invader, Invader invader2) {
		// TODO Auto-generated method stub

	}

	public static void resolveCollision(CollisionTracker collisionTracker,
			Invader invader, Laser laser) {
		if(laser.getSource().equals(LaserSource.INVADER)) {
			return;
		}
		collisionTracker.remove(laser);
		collisionTracker.remove(invader);
	}

	public static void resolveCollision(CollisionTracker collisionTracker,
			Laser laser, Laser laser2) {
		if(laser.getSource().equals(laser2.getSource())) {
			return;
		}
		collisionTracker.remove(laser);
		collisionTracker.remove(laser2);
	}

	public static void resolveCollision(CollisionTracker collisionTracker,
			Laser laser, Player player) {
		if(laser.getSource().equals(LaserSource.PLAYER)) {
			return;
		}
		collisionTracker.remove(laser);
		collisionTracker.remove(player);
		collisionTracker.setPlayerDestroyed(true);
	}

	public static void resolveCollision(CollisionTracker collisionTracker,
			Player player, Player player2) {
	}

	public static void resolveCollision(CollisionTracker collisionTracker,
			Invader invader, Asteroid asteroid) {
		resolveCollision(collisionTracker, asteroid, invader);
	}

	public static void resolveCollision(CollisionTracker collisionTracker,
			Laser laser, Invader invader) {
		resolveCollision(collisionTracker, invader, laser);
	}

	public static void resolveCollision(CollisionTracker collisionTracker,
			Laser laser, Asteroid asteroid) {
		resolveCollision(collisionTracker, asteroid, laser);
	}

	public static void resolveCollision(CollisionTracker collisionTracker,
			Player player, Laser laser) {
		resolveCollision(collisionTracker, laser, player);
	}

	public static void resolveCollision(CollisionTracker collisionTracker,
			Player player, Invader invader) {
		resolveCollision(collisionTracker, invader, player);
	}

	public static void resolveCollision(CollisionTracker collisionTracker,
			Player player, Asteroid asteroid) {
		resolveCollision(collisionTracker, asteroid, player);
	}
}
