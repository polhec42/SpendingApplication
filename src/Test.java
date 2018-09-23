import java.awt.List;
import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Test {
		
	public  String databaseName = "test.db";
	 
    //public static String url = "jdbc:sqlite:C:\\Users\\Uporabnik\\Documents\\Dokumenti DELL XPS13\\Eclipse_Workspace\\SpendingApp\\resources\\test.db";
    public  String url = "jdbc:sqlite:resources/" + databaseName;
    
    public  String getDatabaseName() {
    	return this.databaseName;
    }
    public  void setDatabaseName(String name) {
    	this.databaseName = name;
    	this.url = "jdbc:sqlite:resources/" + this.databaseName;
    }
    	
    //Create new Database, only for first use
    public  void createNewDatabase(String fileName) {
 
        String url = "jdbc:sqlite:resources/" + fileName;
 
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
                setDatabaseName(fileName);
                url = "jdbc:sqlite:resources/" + databaseName;
                System.out.println(databaseName + " " + url);
               
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void createNewTableCategories() {
    	String sql = "CREATE TABLE IF NOT EXISTS categories (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	description text NOT NULL \n"
                + ");";

    	try (Connection conn = DriverManager.getConnection(url);
    			Statement stmt = conn.createStatement()) {
    				// create a new table
    			stmt.execute(sql);
    	} catch (SQLException e) {
    			System.out.println(e.getMessage());
    	}
    }
    
    public void createNewTableTransactions() {
    	String sql = "CREATE TABLE IF NOT EXISTS transactions (\n"
    			                + "	id integer PRIMARY KEY,\n"
    			                + "	description text NOT NULL,\n"
    			                + "	date text NOT NULL,\n"
    			                + " account text NOT NULL,\n"
    			                + " amount real NOT NULL,\n"
    			                + " currency text NOT NULL, \n"
    			                + " category text NOT NULL, \n"
    			                + " type text NOT NULL \n"
    			                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void createTableCategory() {
    	String sql = "CREATE TABLE IF NOT EXISTS categories (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	description text NOT NULL \n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void newCategory(String description) {
    	 String sql = "INSERT INTO categories(description) VALUES(?)";
    	 
         try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
             pstmt.setString(1, description);
             pstmt.executeUpdate();
         } catch (SQLException e) {
             System.out.println(e.getMessage());
         }
    }
    
    public ArrayList<String> vrniKategorije() {
    	String sql = "SELECT id, description FROM categories";
        ArrayList<String> kategorije = new ArrayList<>();
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
               kategorije.add(rs.getString("description"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return kategorije;
    }
    
    private Connection connect() {
        // SQLite connection string
    	//File file = new File(url);
        //String url = file.getAbsolutePath();
        //System.out.println(url);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public void insert(String description, String date, String account, double amount, String currency, String category, String type) {
        String sql = "INSERT INTO transactions(description,date,account,amount,currency,category, type) VALUES(?,?,?,?,?,?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, description);
            pstmt.setString(2,date);
            pstmt.setString(3, account);
            pstmt.setDouble(4, amount);
            pstmt.setString(5, currency);
            pstmt.setString(6, category);
            pstmt.setString(7, type);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //Update transacation's amount and description
    public void update(int id, String description, double amount) {
        String sql = "UPDATE transactions SET description = ? , "
                + "amount = ? "
                + "WHERE id = ?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setString(1, description);
            pstmt.setDouble(2, amount);
            pstmt.setInt(3, id);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void update(int id, String description, String date, String account, String amount, String currency, String category, String type) {
        String sql = "UPDATE transactions SET description = ? , "
        		+ "date = ? ,"
        		+ "account = ? ,"
                + "amount = ? ,"
        		+ "currency = ? ,"
                + "category = ? ,"
        		+ "type = ?"
                + "WHERE id = ?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setString(1, description);
            pstmt.setString(2, date);
            pstmt.setString(3, account);
            pstmt.setDouble(4, Double.parseDouble(amount));
            pstmt.setString(5, currency);
            pstmt.setString(6, category);
            pstmt.setString(7, type);
            pstmt.setInt(8, id);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void selectAll(){
        String sql = "SELECT id, description, date, account, amount, currency, category, type FROM transactions";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  " " + 
                                   rs.getString("description") + " " +
                                   rs.getString("date") + " " +
                                   rs.getString("account") + " " +
                                   rs.getDouble("amount") + " " +
                                   rs.getString("currency") + " " +
                                   rs.getString("category") + " " +
                                   rs.getString("type"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //Delete any row from any table
    public void deleteData(String table, int id) {
        String sql = "DELETE FROM '"+table+"' WHERE id = ?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //Delete any table
    public void deleteTable(String tableName) {
    	
    	String sql = "DROP TABLE IF EXISTS '"+ tableName +"'"; //popravi ta del z '' in ""
    	
    	try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            
            // execute the delete statement
            pstmt.executeUpdate();
            System.out.println("Table " + tableName + " was successfully deleted");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    //Return every transactions from given account
    public ArrayList<Transakcija> vrniTransakcijeIzRacuna(String account){
    	String sql = "SELECT id, description, date, account, amount, currency, category, type FROM transactions WHERE account = ?";
        ArrayList<Transakcija> transakcije = new ArrayList<>();
        try (Connection conn = this.connect();
        	PreparedStatement pstmt  = conn.prepareStatement(sql)){
     
        	// set the value
        	pstmt.setString(1, account);
        	//
        	ResultSet rs  = pstmt.executeQuery();
        	
        	// loop through the result set
        	while (rs.next()) {
        		Transakcija transakcija = new Transakcija(rs.getInt("id"), 
        				rs.getString("description"), rs.getString("date"), 
        				rs.getString("account"), rs.getDouble("amount"), 
        				rs.getString("currency"), rs.getString("category"), 
        				rs.getString("type"));
                transakcije.add(transakcija);
            }
        	
        	
        } 
        catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return transakcije;
    }
    
    public void printAccounts() {
    	 String sql = "SELECT id, description FROM accounts";
         
         try (Connection conn = this.connect();
              Statement stmt  = conn.createStatement();
              ResultSet rs    = stmt.executeQuery(sql)){
             
             // loop through the result set
             while (rs.next()) {
                 System.out.println(rs.getInt("id") +  " " + 
                                    rs.getString("description"));
             }
         } catch (SQLException e) {
             System.out.println(e.getMessage());
         }
    }
   
    public void newAccount(String description) {
    	String sql = "INSERT INTO accounts(description) VALUES(?)";
   	 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, description);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    /*
     * Tip: Expense/Income
     * */
    public ArrayList<Transakcija> vrniTipTransakcij(String tip) {
    	String sql = "SELECT id, description, date, account, amount, currency, category, type FROM transactions WHERE type = ?";
        ArrayList<Transakcija> transakcije = new ArrayList<>();
        try (Connection conn = this.connect();
        	PreparedStatement pstmt  = conn.prepareStatement(sql)){
     
        	// set the value
        	pstmt.setString(1, tip);
        	//
        	ResultSet rs  = pstmt.executeQuery();
        	
        	// loop through the result set
        	while (rs.next()) {
        		Transakcija transakcija = new Transakcija(rs.getInt("id"), 
        				rs.getString("description"), rs.getString("date"), 
        				rs.getString("account"), rs.getDouble("amount"), 
        				rs.getString("currency"), rs.getString("category"), 
        				rs.getString("type"));
                transakcije.add(transakcija);
            }
        } 
        catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return transakcije;
    	
    }
    
    //Vrne seznam transakcij, ki spadajo v doloèeno kategorijo
    public ArrayList<Transakcija> vrniTransakcijeIzKategorije(String category){
    	String sql = "SELECT id, description, date, account, amount, currency, category, type FROM transactions WHERE category = ?";
        ArrayList<Transakcija> transakcije = new ArrayList<>();
        try (Connection conn = this.connect();
        	PreparedStatement pstmt  = conn.prepareStatement(sql)){
     
        	// set the value
        	pstmt.setString(1, category);
        	//
        	ResultSet rs  = pstmt.executeQuery();
        	
        	// loop through the result set
        	while (rs.next()) {
        		Transakcija transakcija = new Transakcija(rs.getInt("id"), 
        				rs.getString("description"), rs.getString("date"), 
        				rs.getString("account"), rs.getDouble("amount"), 
        				rs.getString("currency"), rs.getString("category"), 
        				rs.getString("type"));
                transakcije.add(transakcija);
            }
        	
        	
        } 
        catch (SQLException e) {
        	System.out.println(e.getMessage());
        }
        return transakcije;
    }
    
    /*
     *	Test main method for testing database implementation
     * 
    public static void main(String[] args) {
        //createNewDatabase("test.db");
    	/*
    	createNewTable();
    	Test test = new Test();
    	test.insert("Kosilo", "4.5.2018", "Wallet", 1.86, "Euro");
    	test.insert("Zdravilo", "5.5.2018", "Denarnica", 5.00, "Euro");
    	test.selectAll();
    	test.update(2, "Antibiotik", 6.40);
    	test.selectAll();
    	test.deleteData("transactions", 1);
    	test.deleteTable("transactions");
    	test.selectAll();
    	
    }
	*//*
    public static void main(String args[]) {
    	Test test = new Test();
    	//test.newAccount("Wallet");
    	//test.newAccount("Bank");
    	
    	ArrayList<Transakcija> list = test.vrniTransakcijeIzKategorije("Technology");
    	for(Transakcija x : list) {
    		System.out.println(x);
    	}
    	
    }*/
}