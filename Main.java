import java.io.*;
import java.util.*;

public class Main {

	static final String SOURCE = "folders.txt";
	static final String DESTINATIONDIR = "P:\\Backup\\";
	static final String EXE = "C:\\Program Files\\7-Zip\\7z.exe";
	static String name;
	// static final String COMMAND = "7z u -t7z -mx9 out.7z file.ext";

	static ArrayList<String> arguments = new ArrayList<String>();


	public static void main(String[] args) throws Exception {

		setName();

		arguments.add(EXE);
		arguments.add("u");
		arguments.add("-pASDFGHJKL");
		arguments.add("-mhe");
		arguments.add("-t7z");
		arguments.add("-mx9");
		arguments.add(DESTINATIONDIR + name);

		File f = new File(SOURCE);
		Scanner sc = new Scanner(f);

		while (sc.hasNextLine()) {
			arguments.add(sc.nextLine());
		}
		sc.close();



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