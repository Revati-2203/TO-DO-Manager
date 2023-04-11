package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db_connection.DBConnection;
import model.User;

public class User_dao {
	Connection conn = DBConnection.getConnection();

	public User_dao() {
    	
    }
	
	public boolean login(String email, String password) throws Exception{
		String sql = "Select passwords from users where email=?";
    	PreparedStatement statement;
    	
    	try {
                statement = conn.prepareStatement(sql);
                statement.setString(1,email);
                ResultSet rs = statement.executeQuery();

                if(rs.next()){
                    if(password.equals(rs.getString(1)))
                        return true;
                }throw new Exception("Invalid Credentials");
        }
        catch(SQLException e){
            throw new Exception(e.getMessage());
        }
    	
	}
  	
	public boolean addUser(User user)throws Exception{
		
		String sql =  "insert into users values(?,?,?) ";
		System.out.println(sql);
		
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1,user.getUserName()); 
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getPassword());
			statement.execute();
        }
        catch(SQLException e){
            throw new Exception(e.getMessage());
        }
        return true;
    }
	
	public List<User> getAllUser() throws Exception{
		String sql = "select * from users";

		List<User> user = new ArrayList();
		try {
			Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                User u1 = new User();
                u1.setUserName(rs.getString(1));
                u1.setEmail(rs.getString(2));
                u1.setPassword(rs.getString(3));
                user.add(u1);
            }
		} catch (SQLException e){
            throw new Exception(e.getMessage());
        }
        return user;
	}
	
	public boolean updateUser(User user) throws Exception {
		String sql = "UPDATE users SET username=?, password=? WHERE email=?";
		 try{
	            PreparedStatement statement = conn.prepareStatement(sql);
	            statement.setString(1, user.getUserName());
	            statement.setString(2,user.getPassword());
	            statement.execute();
	        }
	        catch(SQLException e){
	            System.out.println("ERROR");
	            throw new Exception(e.getMessage());
	        }
	        return true;

	}
	
	public boolean deleteUser(String email) throws Exception
    {
        String sql = "delete from users where email=?";

        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,email);
            statement.execute();
        }
        catch(SQLException e){
            throw new Exception(e.getMessage());
        }
        return true;
    }
    public User getUserById(String email) throws Exception
    {
        String sql = "select * from users where email=?";
        User user = null;
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,email);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                user = new User();
                user.setEmail(rs.getString(1));
                user.setUserName(rs.getString(2));
            }
            else
                throw new Exception("No customer with "+email+" found");
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
        return user;
    }

	

	
}
