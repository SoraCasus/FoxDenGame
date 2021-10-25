package com.soracasus.foxsden.util;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FDFile {

	private final String _path;

	private final String _name;

	private final String _type;

	/**
	 * Represents a file for I/O to generalize a lot of the I/O needed
	 *
	 * @param path The path to the file
	 */
	public FDFile( @NotNull String path ) {
		String[] dirs = path.split( "/" );
		_name = dirs[ dirs.length - 1 ];
		_path = "/" + path;
		dirs = _name.split("\\.");
		_type = dirs[ dirs.length - 1 ];
	}

	/**
	 * Retrieves the input stream for reading in files. The caller
	 * is responsible for closing the stream when finished.
	 * @return The input stream
	 */
	public InputStream GetIStream() {
		return this.getClass().getResourceAsStream( _path );
	}

	/**
	 * Opens a buffered reader for reading in the file. The caller
	 * is responsible for closing the buffered reader when finished
	 * @return The Buffered reader
	 */
	public BufferedReader GetReader() {
		return new BufferedReader( new InputStreamReader( GetIStream(), StandardCharsets.UTF_8 ) );
	}

	/**
	 * Opens an output stream for writing to the file. The caller
	 * is responsible for closing the stream when finished
	 * @return The FileOutputStream for the file
	 * @throws IOException If an error occurs when the stream is opened
	 */
	public FileOutputStream GetOStream() throws IOException {
		File file = new File( _path );
		//noinspection ResultOfMethodCallIgnored
		file.createNewFile();
		return new FileOutputStream( file );
	}

	/**
	 * Opens a buffered writer for writing to the file. The caller
	 * is responsible for closing the buffered writer when finished.
	 * @return The BufferedWriter for the file
	 * @throws IOException If an error occurs when the buffered writer is created.
	 */
	public BufferedWriter GetWriter() throws IOException {
		return new BufferedWriter( new FileWriter( _path.substring( 1 ) ) );
	}

	/**
	 * Retrieves the relative file path of the file
	 * @return The file path to the file.
	 */
	public String GetPath() {
		return _path;
	}

	/**
	 * Retrieves the name of the file with the extension
	 * @return The name of the file
	 */
	public String GetName() {
		return _name;
	}

	/**
	 * Retrieves the type of the file by returning the file extension as a String
	 * @return The file extension
	 */
	public String GetType() {
		return _type;
	}

	/**
	 * Converts the file into a String for printing to log
	 * @return The String form of the FDFile.
	 */
	@Override
	public String toString() {
		return "FDFile{" +
				"_path='" + _path + '\'' +
				", _name='" + _name + '\'' +
				", _type='" + _type + '\'' +
				'}';
	}
}
