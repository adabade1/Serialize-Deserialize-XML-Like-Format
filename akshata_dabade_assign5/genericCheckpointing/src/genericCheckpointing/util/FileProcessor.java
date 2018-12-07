package genericCheckpointing.util;


import java.io.*;

public class FileProcessor
{
	 private BufferedReader input;
	 private FileWriter fileWriter;
	 private File f;
	 public FileProcessor(String file) throws Exception
	 {
	     f = new File(file);
    	input = new BufferedReader(new FileReader(f));
    	if(f.length()==0)
    		fileWriter = new FileWriter(f);
	 }
	/**
	 * This method is used to read input file line
	 * by line.
	 * @return String This returns one line at a time.
	 */
	public String getLineFromFile() throws IOException {
		String line;
		if((line = input.readLine()) != null)
		{
			if (line.equals("<DPSerialization>"))
			{
				line = input.readLine();
			}
			return line;
		}
		line = null;
		return line;
	}


	public void writeToFile(String str)
	{
		try
		{
			fileWriter.write(str);
			fileWriter.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
