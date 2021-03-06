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
package org.mini2Dx.invaders.screen;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.GameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.Transition;
import org.mini2Dx.invaders.engine.GameEngine;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * Implements the main gameplay {@link GameScreen}
 */
public class GameEngineScreen extends BasicGameScreen {
	private AssetManager assetManager;
	private GameEngine engine;

	private Texture background;

	public GameEngineScreen(AssetManager assetManager) {
		this.assetManager = assetManager;
	}

	@Override
	public void initialise(GameContainer gc) {
		engine = new GameEngine(gc);
	}

	@Override
	public void update(GameContainer gc,
			ScreenManager<? extends GameScreen> screenManager, float delta) {
		switch (engine.getState()) {
		case WIN:
			// TODO: Render win text
			// TODO: Wait for player input
			break;
		case LOSS:
			// TODO: Render loss text
			// TODO: Wait for player input
			break;
		case PLAYING:
			engine.update(gc, delta);
			break;
		}
	}

	@Override
	public void interpolate(GameContainer gc, float alpha) {
		engine.interpolate(gc, alpha);
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		// Repeat the background texture to fill the screen
		for (int x = 0; x < gc.getWidth(); x += background.getWidth()) {
			for (int y = 0; y < gc.getHeight(); y += background.getHeight()) {
				g.drawTexture(background, x, y);
			}
		}

		engine.render(gc, g);
	}

	@Override
	public void preTransitionIn(Transition transitionIn) {
		background = assetManager.get("textures/backgrounds/purple.png");
		engine.reset(assetManager);
	}

	@Override
	public int getId() {
		return ScreenIds.GAME_ENGINE_SCREEN_ID;
	}
}
