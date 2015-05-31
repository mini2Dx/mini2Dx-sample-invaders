package org.mini2Dx.sample.invaders;

import org.mini2Dx.core.game.BasicGame;
import org.mini2Dx.core.graphics.Graphics;

public class InvadersGame extends BasicGame {
	public static final String GAME_IDENTIFIER = "org.mini2Dx.sample.invaders";
	
	@Override
    public void initialise() {
    }
    
    @Override
    public void update(float delta) {
    
    }
    
    @Override
    public void interpolate(float alpha) {
    
    }
    
    @Override
    public void render(Graphics g) {
		g.drawString(g.getCurrentWidth() + "x" + g.getCurrentHeight(), 32f, 32f);
		g.drawString(getWidth() + "x" + getHeight(), 32f, 64f);
    }
}
