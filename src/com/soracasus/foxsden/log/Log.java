package com.soracasus.foxsden.log;

/**
 * A singleton class to manage and handle all the logging needed
 * for the engine.
 */
public class Log {

	/*
		Four functions print DEBUG, INFO, WARN, ERROR

		1. Check to see if the current log level allows the print
		2. Format the string to fit the requirements
			"[Date Time] [LEVEL] Messsage"
			"[ 23-08-2021 25:38:42.943 ] [ INFO ] This is an info message"
			"[ 23-08-2021 25:38:42.943 ] [ DEBUG ] This is an debug message"
			"[ 23-08-2021 25:38:42.943 ] [ ERROR ] This is an error message"
			"[ 23-08-2021 25:38:42.943 ] [ WARN ] This is an warn message"

		3. Set up the log class to hold a BufferedWriter (Double check online)
			Log file name should be the time stamp of when the log class is initialized
			at the start of the program ( Use BufferedWriter )

		4. Print the message to the log file
	 */


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
		// Create the log file here
	}

	public void SetLogLevel( LogLevel logLevel ) { _logLevel = logLevel; }

	public LogLevel GetLogLevel() {  return _logLevel; }

	public void Info( String message ) {
		if( _logLevel.value <= LogLevel.INFO.value ) {
			System.out.println( _logLevel.tag );
		}
	}
}
