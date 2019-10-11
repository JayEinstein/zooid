package je.project.pojo;

import java.util.Date;

public class User {
	private String id;
	private String name;
	private Date time;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", time=" + time + "]";
	}
	
}
