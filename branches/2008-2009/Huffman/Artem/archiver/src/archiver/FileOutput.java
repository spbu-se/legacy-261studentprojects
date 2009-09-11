package archiver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Artem Mironov
 * @copyright 2009 Artem Mironov
 * @license GNU/GPL v2
 */

public class FileOutput {

	private FileOutputStream fileStream;

	public FileOutput(String fileName) throws IOException
	{
		File file = new File(fileName);
		if (file.exists())
			file.delete();

		if(file.createNewFile())
			fileStream = new FileOutputStream(file);
	}

	public void write(byte[] data) throws IOException
	{
		fileStream.write(data);
	}
	public void write(byte data) throws IOException
	{
		fileStream.write(data);
	}
	public void write(String data) throws IOException
	{
		byte[] bytes = new byte[data.length()];
		for (int i = 0; i < data.length(); i++) {
			bytes[i] = (byte) data.charAt(i);
		}
		write(bytes);
	}

	public void flush() throws IOException
	{
		this.fileStream.flush();
		this.fileStream.close();
	}

}