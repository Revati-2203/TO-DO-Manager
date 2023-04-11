package db_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskdb","root","Password@03");
            System.out.println("Connected");
			
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			 e.printStackTrace();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return conn;
		}
}
