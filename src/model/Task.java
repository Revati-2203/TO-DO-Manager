package model;

public class Task {

	private int task_id;
	private String taskName;
	private String taskDescription;
	private String taskAssignedto;
	private boolean taskStatus;
	public Task(int task_id, String taskName, String taskDescription, String taskAssignedto, boolean taskStatus) {
		super();
		this.task_id = task_id;
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.taskAssignedto = taskAssignedto;
		this.taskStatus = taskStatus;
	}
	public Task() {
		// TODO Auto-generated constructor stub
	}
	public int getTask_id() {
		return task_id;
	}
	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public String getTaskAssignedto() {
		return taskAssignedto;
	}
	public void setTaskAssignedto(String taskAssignedto) {
		this.taskAssignedto = taskAssignedto;
	}	
	public boolean isTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(boolean taskStatus) {
		this.taskStatus = taskStatus;
	}
	@Override
	public String toString() {
		return "Task [task_id=" + task_id + ", taskName=" + taskName + ", taskDescription=" + taskDescription
				+ ", taskAssignedto=" + taskAssignedto + ", taskStatus=" + taskStatus + "]";
	}
	
}
