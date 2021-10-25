package com.soracasus.foxsden.state;

import com.soracasus.foxsden.Game;

import java.awt.*;

public abstract class State {

	private static State _CurrentState = null;

	protected final Game _game;

	protected State( Game game ) {
		_game = game;
	}

	public static void SetState( State state ) {
		_CurrentState = state;
	}

	public static State GetState() {
		return _CurrentState;
	}

	public abstract void Tick();

	public abstract void Render( Graphics graphics );

}
