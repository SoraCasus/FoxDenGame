package com.soracasus.foxsden.graphics;

import com.soracasus.foxsden.util.FDFile;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Objects;

public class Assets {

	private static Assets _instance = null;

	public static Assets GetInstance() {
		if ( _instance == null ) {
			_instance = new Assets();
		}
		return _instance;
	}

	private final HashMap< String, BufferedImage > _textures;

	private Assets() {
		_textures = new HashMap<>();
	}

	/**
	 * Retrieve the texture from the hash map. If the texture is not found in the hash map, then try to load the texture
	 * if the texture cannot be loaded into memory, then return null.
	 *
	 * @param path The path to the texture to be loaded in
	 * @return The texture loaded into memory, if the texture doesn't exist, return null
	 */
	public BufferedImage GetTexture( String path ) {
		if ( _textures.containsKey( path ) ) {
			return _textures.get( path );
		} else {
			// Try to load new textures
			BufferedImage image = LoadImage( path );
			if ( image == null ) {
				// Throw an error since texture doesn't exist
				// Todo(Joshua): Put an error log here once implemented
				return null;
			} else {
				_textures.put( path, image );
				return image;
			}
		}
	}

	/**
	 * Loads an image into a Buffered Image from a file path
	 *
	 * @param path The path to the image file
	 * @return The Image loaded into a BufferedImage
	 */
	@Nullable
	public static BufferedImage LoadImage( String path ) {
		FDFile file = new FDFile( path );
			try ( InputStream stream = file.GetIStream() ) {
				return ImageIO.read( stream );
			} catch ( IOException e ) {
				e.printStackTrace();
			}
		return null;
	}
}
