package com.soracasus.foxsden.entities.mob;

import com.soracasus.foxsden.entities.Entity;

public abstract class Mob extends Entity {

	protected int _health;

	public Mob( float x, float y ) {
		super( x, y );
		_health = 10;
	}
}
