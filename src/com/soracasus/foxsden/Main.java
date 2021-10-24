package com.soracasus.foxsden;

import com.soracasus.foxsden.log.Log;
import com.soracasus.foxsden.util.FDFile;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

	public static void main( String[] args ) {

		FDFile log = new FDFile( "tmp.log" );
		try ( BufferedWriter writer = log.GetWriter() ) {
			writer.write( "This is a test message" );
		} catch ( IOException e ) {
			e.printStackTrace();
		}

		Game game = new Game();
		game.Start();
	}

}
