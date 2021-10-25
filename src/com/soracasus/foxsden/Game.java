package com.soracasus.foxsden;

import com.soracasus.foxsden.display.Display;
import com.soracasus.foxsden.graphics.Assets;
import com.soracasus.foxsden.input.Keyboard;
import com.soracasus.foxsden.state.GameState;
import com.soracasus.foxsden.state.MenuState;
import com.soracasus.foxsden.state.State;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable {

	public static boolean COUNT_FRAMES = false;

	private static final String GAME_TITLE = "Test Title";
	private static final int GAME_WIDTH = 1920;
	private static final int GAME_HEIGHT = 1080;

	private Display _display;
	private boolean _running;
	private Thread _thread;

	private BufferStrategy _bufferStrategy;
	private Graphics _graphics;

	private State _gameState;
	private State _menuState;

	private Keyboard _keyboard;

	public Game() {
		_running = false;
		_keyboard = new Keyboard();
	}

	private void Initialize() {
		_display = new Display( GAME_TITLE, GAME_WIDTH, GAME_HEIGHT );

		_display.GetFrame().addKeyListener( _keyboard );
		_display.GetCanvas().addKeyListener( _keyboard );

		// Note: This is typically bad practice as loading the entire game into memory can lead to running out of memory
		// It is best to only have the relevant states loaded into memory when needed and release them when no longer
		// needed
		_gameState = new GameState( this );
		_menuState = new MenuState( this );

		State.SetState( _gameState );
	}

	private void Tick() {
		if ( State.GetState() != null ) {
			State.GetState().Tick();
		}
	}

	private void Render() {
		_bufferStrategy = _display.GetCanvas().getBufferStrategy();

		// If there is no buffer strategy and begin rendering on the next frame
		if ( _bufferStrategy == null ) {
			// Use triple buffering rendering ( Allows us to run two frames in advance in the event
			// lag becomes an issue )
			_display.GetCanvas().createBufferStrategy( 3 );
			return;
		}

		_graphics = _bufferStrategy.getDrawGraphics();

		// Clear the screen
		_graphics.clearRect( 0, 0, GAME_WIDTH, GAME_HEIGHT );
		// Begin rendering here

		if ( State.GetState() != null ) {
			State.GetState().Render( _graphics );
		}

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

		int fps = 60;
		double timePerTick = 1e9 / fps;
		double delta = 0;
		long now = 0;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while ( _running ) {
			now = System.nanoTime();
			delta += ( now - lastTime ) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if ( delta >= 1 ) {
				Tick();
				Render();
				ticks++;
				delta--;
			}

			if ( COUNT_FRAMES && timer >= 1e9 ) {
				System.out.println( "Ticks/Frames: " + ticks );
				ticks = 0;
				timer = 0;
			}
		}

		Stop();
	}

	public synchronized void Start() {
		// If we are already running, don't follow start up protocols
		if ( _running ) {
			return;
		}

		_running = true;
		_thread = new Thread( this );
		_thread.start();
	}


	public synchronized void Stop() {
		// If we aren't already running, don't allow shutdown procedures
		if ( !_running ) {
			return;
		}

		_running = false;
		try {
			_thread.join();
		} catch ( InterruptedException e ) {
			e.printStackTrace();
		}
	}

	public Keyboard GetKeyboard() {
		return _keyboard;
	}
}
