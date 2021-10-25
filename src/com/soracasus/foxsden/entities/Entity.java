package com.soracasus.foxsden.entities;

import java.awt.*;

public abstract class Entity {

	protected float _x;
	protected float _y;

	public Entity( float x, float y ) {
		_x = x;
		_y = y;
	}

	public abstract void Tick();

	public abstract void Render( Graphics graphics );

}
