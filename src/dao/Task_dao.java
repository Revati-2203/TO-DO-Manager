package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import db_connection.DBConnection;
import model.Task;

public class Task_dao {
	 Connection conn = DBConnection.getConnection();
	    public boolean addTask(Task task) throws Exception
	    {
	        String sql = "insert into task values(?,?,?,?,?)";
	        System.out.println(sql);
	        try{
	            PreparedStatement statement = conn.prepareStatement(sql);
	            statement.setInt(1,task.getTask_id());
	            statement.setString(2,task.getTaskName());
	            statement.setString(3,task.getTaskDescription());
	            statement.setString(4,task.getTaskAssignedto());
	            statement.setBoolean(5,task.isTaskStatus());

	            statement.execute();
	        }
	        catch(SQLException e){
	            throw new Exception(e.getMessage());
	        }
	        return true;
	    }
	    public List<Task> getAllTasks() throws Exception
	    {
	        String sql = "select * from Task";
	        List<Task> task = new ArrayList<>();
	        try{
	            Statement statement = conn.createStatement();
	            ResultSet rs = statement.executeQuery(sql);
	            while(rs.next()){
	                Task t1 = new Task();
	                t1.setTask_id(rs.getInt(1));
	                t1.setTaskDescription(rs.getString(2));
	                t1.setTaskName(rs.getString(3));
	                t1.setTaskAssignedto(rs.getString(4));
	                t1.setTaskStatus(rs.getBoolean(5));
	                task.add(t1);
	            }
	        }
	        catch(SQLException e){
	            throw new Exception(e.getMessage());
	        }
	        return task;
	    }
	    public boolean updateTask(Task task) throws Exception
	    {
	    	String sql = "UPDATE task SET taskName=?, taskDescription=?, taskAssignedto=?, taskStatus=? WHERE task_id=?";
	        try {
	            PreparedStatement statement = conn.prepareStatement(sql);
	            statement.setString(1, task.getTaskName());
	            statement.setString(2, task.getTaskDescription());
	            statement.setString(3, task.getTaskAssignedto());
	            statement.setBoolean(4, task.isTaskStatus());
	            statement.setInt(5, task.getTask_id());
	            statement.executeUpdate();
	            
	        } catch (SQLException e) {
	            System.out.println("ERROR");
	            throw new Exception(e.getMessage());
	        }
	        return true;
	    }
	    public boolean deleteTask(int taskId) throws Exception
	    {
	        String sql = "delete from Task where task_id=?";

	        try{
	            PreparedStatement statement = conn.prepareStatement(sql);
	            statement.setInt(1,taskId);
	            statement.execute();
	        }
	        catch(SQLException e){
	            throw new Exception(e.getMessage());
	        }
	        return true;
	    }

	    
	    public Task getTask(int taskId) throws Exception {
	    	String sql = "select * from task where task_id=?";
	    	  Task task = null;
		        try{
		            PreparedStatement statement = conn.prepareStatement(sql);
		            statement.setInt(1,taskId);
		            ResultSet rs = statement.executeQuery();

		            if(rs.next()){
		                task = new Task();
		                task.setTask_id(rs.getInt(1));
		                task.setTaskName(rs.getString(2));
		                task.setTaskDescription(rs.getString(3));
		                task.setTaskAssignedto(rs.getString(4));
		                task.setTaskStatus(rs.getBoolean(5));
		            }
		            else
		                throw new Exception("No customer with "+taskId+" found");
		        } catch (SQLException e) {
		            throw new Exception(e.getMessage());
		        }
		        
		      return task;
	    	
	    }
	    public List<Task> searchTasks(String searchQuery, String email) throws Exception {
	        List<Task> searchResults = new ArrayList<>();
	        String sql = "SELECT * FROM task WHERE taskName LIKE ? and taskAssignedto = ?";
	        try {
	            PreparedStatement statement = conn.prepareStatement(sql);
	            statement.setString(1, "%" + searchQuery + "%");
	            statement.setString(2, email);
	            ResultSet rs = statement.executeQuery();
	            while (rs.next()) {
	                int taskId = rs.getInt("taskId");
	                String taskName = rs.getString("taskName");
	                String taskDescription = rs.getString("taskDescription");
	                boolean taskStatus = rs.getBoolean("taskStatus");
	                String taskAssignedto = rs.getString("taskAssignedto");
	                Task task = new Task(taskId, taskName, taskDescription, taskAssignedto, taskStatus);
	                searchResults.add(task);
	            }
	        } catch (SQLException e) {
	            throw new Exception(e.getMessage());
	        }
	        return searchResults;
	    }
	    public List<Task> getTasksByStatus(boolean flag) {
	        List<Task> statusList = new ArrayList<>();
	        String sql = "SELECT * FROM task WHERE taskStatus=?";
	        try {
	            PreparedStatement statement = conn.prepareStatement(sql);
	            statement.setBoolean(1, flag);
	            ResultSet rs = statement.executeQuery();
	            while(rs.next()){
	                Task t1 = new Task();
	                t1.setTask_id(rs.getInt(1));
	                t1.setTaskName(rs.getString(2));
	                t1.setTaskDescription(rs.getString(3));
	                t1.setTaskAssignedto(rs.getString(4));
	                t1.setTaskStatus(rs.getBoolean(5));
	                statusList.add(t1);
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        return statusList;
	    }
	    public List<Task> getIncompleteTasksByStatus(boolean flag) {
	    	List<Task> statusList = new ArrayList<>();
				String sql = "SELECT * FROM task WHERE taskStatus=?";
				try{
		            PreparedStatement statement = conn.prepareStatement(sql);
		            statement.setBoolean(1, flag);
		            
		            ResultSet rs = statement.executeQuery();
		            while(rs.next()){
		                Task t1 = new Task();
		                t1.setTask_id(rs.getInt(1));
		                t1.setTaskName(rs.getString(2));
		                t1.setTaskDescription(rs.getString(3));
		                t1.setTaskAssignedto(rs.getString(4));
		                t1.setTaskStatus(rs.getBoolean(5));
		                statusList.add(t1);
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
			return statusList;
		}

}
