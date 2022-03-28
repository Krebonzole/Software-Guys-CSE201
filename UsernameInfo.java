import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UsernameInfo {

	public static void main(String[] args) {
		//loginToFile();
		HashMap hm = readFile();
		System.out.println("Username: ");
		Scanner sc = new Scanner(System.in);
		String username = sc.next();
		System.out.println("Password:");
		String password = sc.next();
		loginStatus(hm, username, password);
	}
	
	// Adding login information to database
	public static void loginToFile() { 
		try {
			System.out.println("Enter Username");
			Scanner sc = new Scanner(System.in);
			String username = sc.next();
			System.out.println("Enter Password");
			String password = sc.next();
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(username, password);
			
			
			FileWriter fw = new FileWriter("ListOfUsernames.txt", true);
			int size = map.values().size();
			String[] usernames = new String[size];
			String[] passwords = new String[size];
			map.values().toArray(passwords);
			map.keySet().toArray(usernames);
			
			for (int i = 0; i < size; i++) { 
				fw.write(usernames[i] + " " + passwords[i]);
				fw.write("\n");
			}
			fw.close();
		} catch (IOException e) {
		}
	}
	
	// Looks into a hashmap to see if the login information is correct
	public static boolean loginStatus(HashMap hm, String username, String password) {
		if (!hm.contains(username)) {
			System.out.println("username incorrect");
			return false;
		}
		if (hm.get(username).equals(password)) {
			System.out.println("Login successful");
			return true;
		} else {
			System.out.println("Password incorrect");
			return false;
		}
	}
	
	// Reads file and put it into a hash map
	public static HashMap readFile() {
		HashMap hm = new HashMap();
		try {
			File file = new File("ListOfUsernames.txt");
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				hm.put(sc.next(), sc.next());
				sc.nextLine();
			}
			sc.close();
		} catch (FileNotFoundException e) {
		}
		return hm;
	}
	
}
