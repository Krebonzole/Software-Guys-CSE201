import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UsernameInfo {

	public static void main(String[] args) {
		userNameToFile();
	}
	
	public static void userNameToFile() { 
		try {
			System.out.println("Enter Username");
			Scanner sc = new Scanner(System.in);
			String username = sc.next();
			FileWriter fw = new FileWriter("ListOfUsernames.txt");
			fw.write(username);
			fw.close();
		} catch (IOException e) {
			System.out.println("File not Found.");
		}
	}
}
