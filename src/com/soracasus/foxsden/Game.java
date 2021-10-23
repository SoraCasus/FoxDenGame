package com.soracasus.foxsden;

import com.soracasus.foxsden.display.Display;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

	private static final String GAME_TITLE = "";
	private static final int GAME_WIDTH = 1920;
	private static final int GAME_HEIGHT = 1080;


	private Display _display;
	private boolean _running;
	private Thread _thread;

	private BufferStrategy _bufferStrategy;
	private Graphics _graphics;

	public Game() {
		_running = false;
	}

	private void Initialize() {
		_display = new Display( GAME_TITLE, GAME_WIDTH, GAME_HEIGHT );
	}

	private void Tick() {

	}

	private void Render() {
		_bufferStrategy = _display.GetCanvas().getBufferStrategy();

		// If there is no buffer strategy and begin rendering on the next frame
		if( _bufferStrategy == null ) {
			// Use triple buffering rendering ( Allows us to run two frames in advance in the event
			// lag becomes an issue )
			_display.GetCanvas().createBufferStrategy( 3 );
			return;
		}

		_graphics = _bufferStrategy.getDrawGraphics();

		// Clear the screen
		_graphics.fillRect( 0, 0, GAME_WIDTH, GAME_HEIGHT );
		// Begin rendering here

		// End rendering
		// Swap the buffers
		_bufferStrategy.show();

		// Because we are done rendering on the current buffer the graphics object
		// we have is no longer needed, so we must dispose of it.
		_graphics.dispose();
	}

	@Override
	public void run() {
		Initialize();

		while( _running ) {
			Tick();
			Render();
		}

		Stop();
	}

	public synchronized void Start() {
		// If we are already running, don't follow start up protocols
		if( _running ) {
			return;
		}

		_running = true;
		_thread = new Thread( this );
		_thread.start();
	}


	public synchronized void Stop() {
		// If we aren't already running, don't allow shutdown procedures
		if( !_running ) {
			return;
		}

		_running = false;
		try {
			_thread.join();
		} catch ( InterruptedException e ) {
			e.printStackTrace();
		}
	}
}
