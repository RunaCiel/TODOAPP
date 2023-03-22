package model;

import java.io.Serializable;
import java.util.Date;

public class ToDo implements Serializable {
	private int id;
	private String task;
	private Date date;
	private int done;
	private int taskId;
	
	public ToDo() {}
	public ToDo(String task, Date date, int done, int taskId) {
		this.task = task;
		this.date = date;	
		this.done = done;
		this.taskId = taskId;
	}
	
	public ToDo(int id, String task, Date date, int done, int taskId) {
		this.id = id;
		this.task = task;
		this.date = date;
		this.done = done;
		this.taskId = taskId;
	}
	
	public int getId() {return id;}
	public String getTask() {return task;}
	public Date getDate() {return date;}
	public int getDone() {return done;}
	public int getTaskId() {return taskId;}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setTask(String task) {
		this.task = task;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
}
