package archiver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileInput {
	private InputStream fileStream;
	private static int  readBytes = Main.readBytes;


	public FileInput(String fileName) throws IOException
	{
		File file = new File(fileName);
		if(file.exists()){
			fileStream = new FileInputStream(file);
		}
		else throw new IOException();
	}

	public byte[] read() throws IOException
	{
		return read(1);
	}

	public byte[] read(int len) throws IOException
	{
		if(len>fileStream.available())
			len = fileStream.available();

		byte[] buf = new byte[len];
		fileStream.read(buf,0, len);
		return buf;
	}

	public int lenLeft() throws IOException
	{
		//System.out.println("lenLeft "+fileStream.available());
		return fileStream.available();
	}

	public void flush() throws IOException
	{
		this.fileStream.close();
	}
}
