package org.mini2Dx.sample.invaders.desktop;

import org.mini2Dx.desktop.DesktopMini2DxGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.mini2Dx.sample.invaders.InvadersGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.stencil = 8;
		config.vSyncEnabled = true;
        config.foregroundFPS = 0;
        config.backgroundFPS = 0;
		new LwjglApplication(new DesktopMini2DxGame(InvadersGame.GAME_IDENTIFIER, new InvadersGame()), config);
	}
}
