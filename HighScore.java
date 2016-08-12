import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URISyntaxException;

public class HighScore implements Serializable {

	int hS;
	public HighScore()
	{
		
	}
	void setHighScore(int x) throws FileNotFoundException, URISyntaxException, IOException
	{
		hS=x;
		
	}
	
	
}
