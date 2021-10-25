package com.soracasus.foxsden.state;

import com.soracasus.foxsden.Game;
import com.soracasus.foxsden.entities.mob.Player;

import java.awt.*;

public class GameState extends State {

	private Player _player;

	public GameState( Game game ) {
		super( game );
		_player = new Player( game, 100, 100 );
	}

	@Override
	public void Tick() {
		_player.Tick();
	}

	@Override
	public void Render( Graphics graphics ) {
		_player.Render( graphics );
	}
}
