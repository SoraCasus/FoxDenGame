package com.soracasus.foxsden.entities.mob;

import com.soracasus.foxsden.Game;
import com.soracasus.foxsden.graphics.Assets;

import java.awt.*;

public class Player extends Mob {

	private final Game _game;

	public Player( Game game, float x, float y ) {
		super( x, y );
		_game = game;
	}

	@Override
	public void Tick() {
		if( _game.GetKeyboard().GetUp() ) {
			_y -= 3;
		}
		if( _game.GetKeyboard().GetDown() ) {
			_y += 3;
		}
		if( _game.GetKeyboard().GetLeft() ) {
			_x -= 3;
		}
		if( _game.GetKeyboard().GetRight() ) {
			_x += 3;
		}
	}

	@Override
	public void Render( Graphics graphics ) {
		graphics.drawImage( Assets.GetInstance().GetTexture( "textures/player.png" ), ( int ) _x, ( int ) _y, null );
	}
}
