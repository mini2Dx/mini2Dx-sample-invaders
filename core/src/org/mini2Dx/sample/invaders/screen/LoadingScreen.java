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
package org.mini2Dx.sample.invaders.screen;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.GameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.transition.FadeInTransition;
import org.mini2Dx.core.screen.transition.FadeOutTransition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * Implements the loading {@link GameScreen} and handles loading resources
 */
public class LoadingScreen extends BasicGameScreen {
	private AssetManager assetManager;

	private BitmapFont font;
	private Texture background;
	private String loadingText = "Loading";
	private float textUpdateTimer = 0.5f;

	public LoadingScreen(AssetManager assetManager) {
		this.assetManager = assetManager;
	}

	@Override
	public void initialise(GameContainer gc) {
		loadResourcesRequiredOnStartup();
		queueResourcesForLoading();
	}

	@Override
	public void update(GameContainer gc,
			ScreenManager<? extends GameScreen> screenManager, float delta) {
		textUpdateTimer -= delta;
		if(textUpdateTimer <= 0f) {
			loadingText += ".";
			if(loadingText.endsWith("....")) {
				loadingText = "Loading";
			}
			textUpdateTimer = 0.5f;
		}
		
		if (assetManager.update()) {
			screenManager.enterGameScreen(ScreenIds.GAME_ENGINE_SCREEN_ID,
					new FadeOutTransition(), new FadeInTransition());
		}
	}

	@Override
	public void interpolate(GameContainer gc, float alpha) {
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		// Repeat the background texture to fill the screen
		for (int x = 0; x < gc.getWidth(); x += background.getWidth()) {
			for (int y = 0; y < gc.getHeight(); y += background.getHeight()) {
				g.drawTexture(background, x, y);
			}
		}

		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString(loadingText, 32f, gc.getHeight() - font.getXHeight() - 32f);
	}
	
	private void loadResourcesRequiredOnStartup() {
		assetManager.load("textures/backgrounds/darkPurple.png", Texture.class);
		assetManager.finishLoading();

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
				Gdx.files.internal("fonts/kenvector_future_thin.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.flip = true;
		parameter.size = 32;

		font = generator.generateFont(parameter);
		background = assetManager.get("textures/backgrounds/darkPurple.png");

		generator.dispose();
	}
	
	private void queueResourcesForLoading() {
		assetManager.load("textures/backgrounds/blue.png", Texture.class);
		assetManager.load("textures/backgrounds/purple.png", Texture.class);
		
		assetManager.load("textures/enemy_laser.png", Texture.class);
		assetManager.load("textures/enemy.png", Texture.class);
		assetManager.load("textures/meteor_big.png", Texture.class);
		assetManager.load("textures/meteor_medium.png", Texture.class);
		assetManager.load("textures/meteor_small.png", Texture.class);
		assetManager.load("textures/player_laser.png", Texture.class);
		assetManager.load("textures/player.png", Texture.class);
		
		assetManager.load("sounds/sfx_laser1.ogg", Sound.class);
		assetManager.load("sounds/sfx_laser2.ogg", Sound.class);
		assetManager.load("sounds/sfx_lose.ogg", Sound.class);
		assetManager.load("sounds/sfx_twoTone.ogg", Sound.class);
		assetManager.load("sounds/sfx_zap.ogg", Sound.class);
	}

	@Override
	public int getId() {
		return ScreenIds.LOADING_SCREEN_ID;
	}
}
