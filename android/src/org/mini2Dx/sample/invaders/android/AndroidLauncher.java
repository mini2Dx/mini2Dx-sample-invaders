package org.mini2Dx.sample.invaders.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import org.mini2Dx.android.AndroidMini2DxGame;
import org.mini2Dx.sample.invaders.InvadersGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.stencil = 8;
		initialize(new AndroidMini2DxGame(getApplicationContext(), 
			InvadersGame.GAME_IDENTIFIER, new InvadersGame()), config);
	}
}
