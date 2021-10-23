package com.soracasus.foxsden.display;

import javax.swing.*;
import java.awt.*;

public class Display {

	private JFrame _frame;
	private Canvas _canvas;

	private String _title;
	private int _width;
	private int _height;

	/**
	 * Represents the window in which the game is executed in
	 * @param title The title of the window, to be displayed in the title bar
	 * @param width The width of the window in pixels
	 * @param height The height of the window in pixels
	 */
	public Display( String title, int width, int height ) {
		_title = title;
		_width = width;
		_height = height;

		CreateDisplay();
	}

	/**
	 * Creates the display by initializing the frame and canvas
	 * sets the window to be visible, called by the constructor.
	 */
	private void CreateDisplay() {
		Dimension dims = new Dimension( _width, _height );

		_frame = new JFrame( _title );
		_frame.setSize( dims );
		_frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		_frame.setResizable( false );
		_frame.setLocationRelativeTo( null );
		_frame.setVisible( true );
		_frame.setTitle( _title );

		_canvas = new Canvas();
		_canvas.setPreferredSize( dims );
		_canvas.setMaximumSize( dims );
		_canvas.setMinimumSize( dims );

		_frame.add( _canvas );
		_frame.pack();
	}

	public Canvas GetCanvas() { return _canvas; }

	public JFrame GetFrame() { return _frame; }

	public int GetWidth() { return _width; }

	public int GetHeight() { return _height; }
}
