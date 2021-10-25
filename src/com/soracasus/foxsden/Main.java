package com.soracasus.foxsden;

public class Main {

	public static void main( String[] args ) {

		for( String s : args ) {
			if( s.equalsIgnoreCase( "-countframes" ) ) {
				Game.COUNT_FRAMES = true;
			} else {
				PrintHelp();
				System.exit( 0 );
			}
		}
		Game game = new Game();
		game.Start();
	}

	private static void PrintHelp() {
		String sb = """
				Command line arguments:\s
				\t -help \t Prints this help screen\s
				\t -countframes \t Prints the number of frames rendered in a second\s
				""";

		System.out.println( sb );
	}

}
