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

	public boolean Exists() {
		File file = new File( _path );
		return file.exists();
	}

	public InputStream GetIStream() {
		return this.getClass().getResourceAsStream( _path );
	}

	public BufferedReader GetReader() {
		return new BufferedReader( new InputStreamReader( GetIStream(), StandardCharsets.UTF_8 ) );
	}

	public FileOutputStream GetOStream() throws IOException {
		File file = new File( _path );
		//noinspection ResultOfMethodCallIgnored
		file.createNewFile();
		return new FileOutputStream( file );
	}

	public BufferedWriter GetWriter() throws IOException {
		return new BufferedWriter( new FileWriter( _path.substring( 1 ) ) );
	}

	public String GetPath() {
		return _path;
	}

	public String GetName() {
		return _name;
	}

	public String GetType() {
		return _type;
	}

	@Override
	public String toString() {
		return "FDFile{" +
				"_path='" + _path + '\'' +
				", _name='" + _name + '\'' +
				", _type='" + _type + '\'' +
				'}';
	}
}
