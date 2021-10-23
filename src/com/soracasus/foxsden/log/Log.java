package com.soracasus.foxsden.log;

/**
 * A singleton class to manage and handle all the logging needed
 * for the engine.
 */
public class Log {

	private static Log _Instance = null;

	public static Log GetInstance() {
		if( _Instance == null ) {
			_Instance = new Log();
		}
		return _Instance;
	}

	private LogLevel _logLevel;

	private Log() {
		_logLevel = LogLevel.INFO;
	}

	public void SetLogLevel( LogLevel logLevel ) { _logLevel = logLevel; }

	public LogLevel GetLogLevel() {  return _logLevel; }
}
