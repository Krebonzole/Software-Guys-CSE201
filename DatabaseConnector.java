import java.sql.*;
import java.math.*;
import java.util.Scanner;

public class DatabaseConnector {
	static String username = "root";
	static String password = "Otis2001!!";
	 static void connectToAndQueryDatabase(String username, String password) throws SQLException {
		Scanner in = new Scanner(System.in);
	    Connection con;
		con = DriverManager.getConnection(
			                     "jdbc:mysql://127.0.0.1:3306/CSE201PROJECT?prop1",
			                     username,
			                     password);
			// TODO Auto-generated catch block
		PreparedStatement prestat=con.prepareStatement("blah blah blah",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		System.out.print("Login in or Create Account? (Enter C for Creating Account, Press Any Key to Login)");
		String answer = in.next();
		if(answer.equals("C")) {
			System.out.println("Enter Username:");
		    String newUser = in.next();
		    System.out.println("Enter Password:");
		    String newPass = in.next();
		    String sql = "INSERT INTO Accounts (userName, passWord) VALUES ('%s', '%s')";
		    sql = String.format(sql, newUser, newPass);
		    prestat.executeUpdate(sql);
		    System.out.print("Account Created");
		    return;
		}
	    System.out.println("Enter Username:");
	    String User = in.next();
	    System.out.println("Enter Password:");
	    String Pass = in.next();
	    String sql = "SELECT userName, password, accountID FROM Accounts WHERE Accounts.userName = '%s' AND Accounts.password = '%s'";
	    sql = String.format(sql, User, Pass);
	    ResultSet rs = prestat.executeQuery(sql);
	    if(!(rs.next())) {
	    	System.out.print("username or password incorrect");
	    	return;
	    }
	    
	    int accId = rs.getInt("accountID");
	    con.close();
	    int choice = 0;
	    while (choice != 6) {
	    	choice = menu();
	    	if (choice == 1)
	    		checkBalance(accId);
	    	else if (choice == 2) {
	    		createBalance(accId);
	    	} else if (choice == 3) {
	    		makeTransaction(accId);
	    	} else if (choice == 4) {
	    		makeBusiness(accId);
	    	} else if (choice == 5) {
	    		linkToBusiness(accId);
	    	}
	    		
	    	
	    }
	    
	    
	}
	
	private static void linkToBusiness(int accId) throws SQLException {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		Connection con;
		con = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/CSE201PROJECT?prop1",
                username,
                password);
		
		PreparedStatement upd = con.prepareStatement("blah blah blah",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		String sql1 = "UPDATE Balances SET BusinessID = %d WHERE purpose = '%s' AND accountID = %d";
		String sql2 = "SELECT businessID FROM Business WHERE name = '%s' AND accountID = %d";
		System.out.println("What Balance would you like to link?");
		String purp = in.nextLine();
		System.out.println("What Business would you like to link to this business?");
		String bus = in.nextLine();
		int bId = 0;
		sql2 = String.format(sql2, bus, accId);
		ResultSet rs  = upd.executeQuery(sql2);
		if(!(rs.next())) {
	    	System.out.print("no business found with that name");
	    	return;
	    } else {
	    	bId = rs.getInt("businessID");
	    }
		sql1 = String.format(sql1, bId, purp, accId);
		upd.executeUpdate(sql1);
		
	}

	private static void makeBusiness(int accId) throws SQLException {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		Connection con;
		con = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/CSE201PROJECT?prop1",
                username,
                password);
		Statement upd = con.createStatement();
		String sql = "INSERT INTO Business (accountID, name) VALUES (%d, '%s');";
		System.out.println("What would you like to name this business?");
		String bName = in.nextLine();
		sql = String.format(sql, accId, bName);
		upd.executeUpdate(sql);
		
	}

	public static int menu() {
		Scanner in = new Scanner(System.in);
		System.out.println("What would you like to do?");
		System.out.println("1. Check Account Balances");
		System.out.println("2. Create new Balance");
		System.out.println("3. Make Transaction");
		System.out.println("4. Make Business");
		System.out.println("5. Link Balance to Business");
		System.out.println("6. Exit");
		int ret = in.nextInt();
		return ret;
	}
	
	
	public static void checkBalance(int accID) throws SQLException {
		Scanner in = new Scanner(System.in);
		Connection con;
		con = DriverManager.getConnection(
		                     "jdbc:mysql://127.0.0.1:3306/CSE201PROJECT?prop1",
		                     username,
		                     password);
		//TODO Auto-generated catch block
		PreparedStatement prestat1=con.prepareStatement("blah blah blah",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    	String sql1 = "SELECT money, purpose FROM Balances where accountID = %d";
    	sql1 = String.format(sql1, accID);
    	ResultSet rs1 = prestat1.executeQuery(sql1);
    	while(rs1.next()) {
	    	double mon = rs1.getDouble("money");
	    	String purpose = rs1.getString("purpose");
	    	System.out.println(purpose + " : " + mon);
    	}
	}
	
	public static void createBalance(int accID) throws SQLException {
		Scanner in = new Scanner(System.in);
		Connection con;
		con = DriverManager.getConnection(
		                     "jdbc:mysql://127.0.0.1:3306/CSE201PROJECT?prop1",
		                     username,
		                     password);
		//TODO Auto-generated catch block
		Statement upd = con.createStatement();
    	String sql1 = " INSERT INTO Balances (accountID, purpose, dangerZone, money) VALUES (%d, '%s', '%s', %f)";
    	System.out.println("What is the purpose of this balance?");
    	String purpose = in.next();
    	System.out.println("How low can this balance go?");
    	String dangerZone = in.next();
    	System.out.println("What is the starting balance?");
    	double money = in.nextDouble();
    	sql1 = String.format(sql1, accID, purpose, dangerZone, money);
    	upd.executeUpdate(sql1);
	}
	
	public static void makeTransaction(int accID) throws SQLException {
		Scanner in = new Scanner(System.in);
		Connection con;
		con = DriverManager.getConnection(
		                     "jdbc:mysql://127.0.0.1:3306/CSE201PROJECT?prop1",
		                     username,
		                     password);
		//TODO Auto-generated catch block
		Statement upd = con.createStatement();
    	String sql1 = "INSERT INTO Transactions (accountID, balanceID, purpose, netChange) VALUES (%d, %d, '%s', %f)";
    	String sql2 = "Select money, balanceID FROM Balances WHERE purpose = '%s' and accountID = %d";
    	System.out.println("what balance is this transaction for?");
    	String balanceName = in.next();
    	System.out.println("What is the purpose of this transaction?");
    	String purpose = in.next();
    	System.out.println("will this be a deposit or a withdrawl (enter + or -)");
    	String sign = in.next();
    	System.out.println("How much would you like to deposit or withdraw?");
    	double netChange = in.nextDouble();
    	sql2 = String.format(sql2, balanceName, accID);
    	ResultSet r1 = upd.executeQuery(sql2);
    	r1.next();
    	int bID = r1.getInt("balanceID");
    	double money = r1.getDouble("money");
    	if (sign.equals("-"))
    		netChange = -netChange;
    	sql1 = String.format(sql1, accID, bID, purpose, netChange);
    	double newMoney = money + netChange;
    	String sql3 = "UPDATE Balances SET money = %f WHERE balanceID = %d";
    	sql3 = String.format(sql3, newMoney, bID);
    	upd.executeUpdate(sql1);
    	upd.executeUpdate(sql3);
	}
	
	
	public static void main(String args[]) {
		try {
			connectToAndQueryDatabase("root", "Otis2001!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
