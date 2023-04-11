package main;

import java.util.List;
import java.util.Scanner;


import dao.Task_dao;
import dao.User_dao;
import db_connection.DBConnection;
import model.Task;
import model.User;

public class TaskManager {
	private static Scanner scanner = new Scanner(System.in);
	static Task_dao task_dao = new Task_dao();
    static User_dao userDao = new User_dao();
    static User currentUser = new User();
    
	public static void main(String[] args) throws Exception{
		DBConnection.getConnection();
        showLoginMenu();
	    
	}

	private static void showLoginMenu()throws Exception  {
		// TODO Auto-generated method stub
		int choice = -1;
		while(choice != 0) {
            System.out.println("1. Login");
            System.out.println("2. SignUp");
            System.out.println("0. Exit");
            
            choice = scanner.nextInt();
            scanner.nextLine();
            
            switch(choice)
            {
            case 1:
            	System.out.println("Enter your email:");
                String email = scanner.nextLine();
                System.out.println("Enter your password:");
                String password = scanner.nextLine();
                boolean flag = false;
                userDao = new User_dao();
                flag = userDao.login(email, password);
                if (flag) {
                	currentUser.setEmail(email);
                    showMainMenu();
                } 
                else {
                    System.out.println("Invalid credentials!");
                }
                break;
            case 0:
                System.out.println("Exiting...");
                break;
                
            case 2:
            	System.out.println("Enter Your Email Id");
            	String newEmail = scanner.nextLine();
            	System.out.println("Set Your UserName");
            	String newUserName = scanner.nextLine();
            	System.out.println("Set Your Password");
            	String newPassword = scanner.nextLine();
            	
            	userDao = new User_dao();
            	currentUser.setEmail(newEmail);
            	currentUser.setPassword(newPassword);
            	currentUser.setUserName(newUserName);
            	boolean isadded = userDao.addUser(currentUser);
            	if(isadded)
            	{
            		System.out.println("Signed up successfully");
            	}
            	else {
            		System.out.println("try again...");
            	}
            	break;
            	
            	
            default:
                System.out.println("Invalid choice!");
                break;
            	
            }
		
	}

}
	
	private static void showMainMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println("1. Add task");
            System.out.println("2. Update task");
            System.out.println("3. Delete task");
            System.out.println("4. Search task");
            System.out.println("5. View all tasks");
            System.out.println("6. View completed tasks");
            System.out.println("7. View incomplete tasks");
            System.out.println("0. Logout");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    updateTask();
                    break;
                case 3:
                    deleteTask();
                    break;
                case 4:
                    searchTask();
                    break;
                case 5:
                    viewAllTasks();
                    break;
                case 6:
                    viewCompletedTasks();
                    break;
                case 7:
                    viewIncompleteTasks();
                    break;
                case 0:
                    System.out.println("Logging out...");
                    currentUser = null;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
	}

	private static void viewIncompleteTasks() {
		// TODO Auto-generated method stub
		 System.out.println("Incomplete tasks:");
	        task_dao.getIncompleteTasksByStatus(false).forEach(System.out::println);
	}

	private static void viewCompletedTasks() {
		// TODO Auto-generated method stub
		 System.out.println("Completed tasks:");
	        task_dao.getTasksByStatus(true).forEach(System.out::println);
	}

	private static void viewAllTasks() {
		// TODO Auto-generated method stub
		System.out.println("All tasks:");
        try {
			task_dao.getAllTasks().forEach(System.out::println);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void searchTask() {
		// TODO Auto-generated method stub
		 System.out.println("Enter search keyword:");
	        String keyword = scanner.nextLine();
	        System.out.println("Search results:");
	        try {
				List<Task> result = task_dao.searchTasks(keyword,currentUser.getEmail());
				for(Task task:result) {
					System.out.println(task);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private static void updateTask() {
		// TODO Auto-generated method stub
		 System.out.println("Enter task ID:");
	        int task_id = scanner.nextInt();
	        scanner.nextLine();
	        Task task=null;
			try {
				task = task_dao.getTask(task_id);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	        if (task != null && task.getTaskAssignedto().equals(currentUser.getEmail())) {
	            System.out.println("Enter new task title:");
	            String title = scanner.nextLine();
	            System.out.println("Enter new task description:");
	            String description = scanner.nextLine();
	            System.out.println("Enter Status");
	            boolean status = scanner.nextBoolean();
	            task.setTaskName(title);
	            task.setTaskDescription(description);
	            task.setTaskStatus(status);
	            try {
					task_dao.updateTask(task);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	            System.out.println("Task updated successfully!");
	        } else {
	            System.out.println("Invalid task ID or you are not authorized to update this task!");
	        }
	}

	private static void deleteTask() {
		// TODO Auto-generated method stub
		System.out.println("Enter task ID:");
        int taskId = scanner.nextInt();
        scanner.nextLine();
        Task task=null;
		try {
			task = task_dao.getTask(taskId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         if(task.getTask_id()!=0) {
        	if (task.getTaskAssignedto().equals(currentUser.getEmail())) {
            	try {
					task_dao.deleteTask(taskId);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	System.out.println("Task deleted successfully!");
            }
       }
         else {
        	System.out.println("Invalid task ID or you are not authorized to delete this task!");
       }
	}

	
	
	
	private static void addTask() {
		
		System.out.println("Enter the task Id");
    	int taskId = scanner.nextInt();
    	scanner.nextLine(); 
        System.out.println("Enter task name:");
        String name = scanner.nextLine();
        System.out.println("Enter task description:");
        String description = scanner.nextLine();
        System.out.println("Enter task Status");
        boolean status = scanner.nextBoolean();
        
        Task task = new Task(taskId,name, description, currentUser.getEmail(),status);
        System.out.println(task.getTaskAssignedto());
        System.out.println(task.getTask_id());
        System.out.println(task.getTaskDescription());
        System.out.println(task.getTaskName());
        System.out.println(task.isTaskStatus());
        try {
			task_dao.addTask(task);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        System.out.println("Task added successfully!");
    }

}

	
	


	