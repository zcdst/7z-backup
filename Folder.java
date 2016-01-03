import java.io.*;
import java.util.*;


public class Folder {

	public String path;
	public ArrayList<String> exclusions = new ArrayList<String>();

	public Folder(String path, ArrayList<String> exclusions) {
		this.path = path;
		this.exclusions = exclusions;
	}


}