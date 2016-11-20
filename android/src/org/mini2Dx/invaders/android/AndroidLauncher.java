package org.mini2Dx.invaders.android;

import org.mini2Dx.android.AndroidMini2DxConfig;

import com.badlogic.gdx.backends.android.AndroidMini2DxGame;

import android.os.Bundle;

import org.mini2Dx.invaders.InvadersGame;

public class AndroidLauncher extends AndroidMini2DxGame {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidMini2DxConfig config = new AndroidMini2DxConfig(InvadersGame.GAME_IDENTIFIER);
        initialize(new InvadersGame(), config);
    }
}
