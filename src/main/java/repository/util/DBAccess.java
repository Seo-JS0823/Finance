package repository.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBAccess {
	/* DB 연결 정보 List */
	private List<String> dbs = new ArrayList<>();
	
	/* DBAccess 인스턴스 초기화 */
	private DBAccess() {
		init();
	}
	
	/* static DBAccess Instance */
	public static DBAccess getInstance() {
		return new DBAccess();
	}
	
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(dbs.get(0));
			con = DriverManager.getConnection(dbs.get(1), dbs.get(2), dbs.get(3));
		} catch(ClassNotFoundException e) {
			throw new IllegalStateException();
		} catch(SQLException e) {
			throw new IllegalStateException();
		}
		return con;
	}
	
	private void init() {
		String path = "src/main/resources/rdbms.properties";
		Properties pps = new Properties();
		try {
			FileReader fileReader = new FileReader(path);
			BufferedReader bufReader = new BufferedReader(fileReader);
			
			pps.load(bufReader);
			
			String driver = pps.getProperty("mysql.driver");
			String url = pps.getProperty("mysql.url");
			String username = pps.getProperty("mysql.username");
			String password = pps.getProperty("mysql.password");
			
			dbs.add(0, driver);
			dbs.add(1, url);
			dbs.add(2, username);
			dbs.add(3, password);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("FileNotFoundException: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("IOException: " + e.getMessage());
		}
		/* Log */
		System.out.println("Access 초기화 완료");
	}
	
	
	
}