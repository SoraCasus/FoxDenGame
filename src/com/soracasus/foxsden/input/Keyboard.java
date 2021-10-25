package com.soracasus.foxsden.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private boolean[] _keys;

	public Keyboard() {
		_keys = new boolean[256];
	}

	public boolean GetUp() {
		return _keys[ KeyEvent.VK_W ];
	}

	public boolean GetDown() {
		return _keys[ KeyEvent.VK_S ];
	}

	public boolean GetLeft() {
		return _keys[ KeyEvent.VK_A ];
	}

	public boolean GetRight() {
		return _keys[ KeyEvent.VK_D ];
	}

	@Override
	public void keyTyped( KeyEvent e ) {

	}

	@Override
	public void keyPressed( KeyEvent e ) {
		_keys[ e.getKeyCode() ] = true;
	}

	@Override
	public void keyReleased( KeyEvent e ) {
		_keys[ e.getKeyCode() ] = false;
	}
}
