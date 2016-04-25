package pl.spiralarchitect.springboot.asych;

public class TaskRequest {
	
	private String taskName;
	private String taskParameters[];
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String[] getTaskParameters() {
		return taskParameters;
	}
	public void setTaskParameters(String[] taskParameters) {
		this.taskParameters = taskParameters;
	}
	
}
