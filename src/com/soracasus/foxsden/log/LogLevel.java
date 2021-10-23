package com.soracasus.foxsden.log;

public enum LogLevel {

	DEBUG( 0, "DEBUG" ),
	INFO( 1, "INFO" ),
	WARN( 2, "WARN" ),
	ERROR( 3, "ERROR" );

	public final int value;
	public final String tag;

	LogLevel( int value, String tag ) {
		this.value = value;
		this.tag = tag;
	}

	@Override
	public String toString() {
		return tag;
	}
}
