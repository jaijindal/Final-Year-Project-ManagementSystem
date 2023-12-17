package utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 * File I/O functions
 * @author Siah Wee Hung
 * @version 1.0
 * @since 2023-04-11
 */
public class FileIO 
{
    /**
     * Read txt file
     * @param path Relative / absolute path of file to read from
     * @return File content as array of lines
     */
    public static ArrayList<String> readLines(String path) 
    {
        String line;
        ArrayList<String> lines = new ArrayList<String>();

        // catch IOException from FileWriter()
        try 
        {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            
            // read file to completion, line by line
            while ((line = reader.readLine()) != null) 
            {
                lines.add(line);
            }
            reader.close();
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        return lines;
    }
}
