import java.io.*;
import java.util.*;



public class Main {

	static final String SOURCE = "folders.txt";
	static final String DESTINATIONDIR = "P:\\Backup\\";
	static final String EXE = "C:\\Program Files\\7-Zip\\7z.exe";
	static String name;
	static int k = 0;
	// static final String COMMAND = "7z u -t7z -mx9 out.7z file.ext";
	// single exclusions: -x!c:\full\path
	// exclude all instances: -xr!name

	static ArrayList<String> arguments = new ArrayList<String>();
	static ArrayList<Folder> f = new ArrayList<Folder>();


	public static void main(String[] args) throws Exception {

		setName();

		File source = new File(SOURCE);
		Scanner sc = new Scanner(source);
		String strang = "";
		String currFolder = "";
		Boolean isFolder = true;
		ArrayList<String> tmpex = new ArrayList<String>();

		while (sc.hasNextLine()) {

			strang = sc.nextLine();

			if (strang.startsWith("#")) {
				continue;
			}

			if (isFolder && strang.length() > 0) {
				isFolder = false;
				currFolder = strang;
				continue;
			}

			if (strang.length() > 0) {
				// tmpex.add(currFolder + "\\" + strang);
				tmpex.add("-x!" + currFolder.substring(currFolder.lastIndexOf("\\") + 1, currFolder.length()) + strang);
			} else if (currFolder.length() > 0) {
				isFolder = true;
				f.add(new Folder(currFolder, new ArrayList<String>(tmpex)));
				tmpex.clear();
				currFolder = "";
			}
			
		}

		sc.close();

		arguments.add(EXE);
		arguments.add("u");	// update
		arguments.add("-pASDFGHJKL");	// password
		arguments.add("-mhe");	// encrypt headers
		arguments.add("-t7z");	// 7zip compression
		arguments.add("-mx9");	// max compression
		arguments.add(DESTINATIONDIR + name);	// output file

		for (Folder z : f) {
			arguments.add(z.path);
			for (String x : z.exclusions) {
				arguments.add(x);
			}
		}


		for (String x : arguments) {
			System.out.println(x);
		}


			ProcessBuilder cspb = new ProcessBuilder(arguments);
			cspb.inheritIO();
			Process csp = cspb.start();
			csp.waitFor();
	}


	private static void setName() {

		File f1 = new File(DESTINATIONDIR + "Backup_1.7z");
		File f2 = new File(DESTINATIONDIR + "Backup_2.7z");
		File f3 = new File(DESTINATIONDIR + "Backup_3.7z");

		if (f1.exists() && f2.exists() && f3.exists()) {
			if (System.currentTimeMillis() - f3.lastModified() < 170000000) {
				name = "Backup_3.7z";
			} else {
				f1.delete();
				f2.renameTo(f1);
				f3.renameTo(f2);
				name = "Backup_3.7z";
			}
		} else if (f1.exists() && f2.exists()) {
			name = "Backup_3.7z";
		} else if (f1.exists()) {
			name = "Backup_2.7z";
		} else {
			System.out.println("Folder does not contain previous backups...");
			name = "Backup_1.7z";
		}


	}





}