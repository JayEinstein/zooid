package je.project.pojo;

public class StrLink {
	
	private String slnk_id;
	private String scn_id;
	private String item_id;
	private String next_id;
	private String prev_id;
	private Integer appear;
	public String getScn_id() {
		return scn_id;
	}
	public void setScn_id(String scn_id) {
		this.scn_id = scn_id;
	}
	public String getSlnk_id() {
		return slnk_id;
	}
	public void setSlnk_id(String slnk_id) {
		this.slnk_id = slnk_id;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getNext_id() {
		return next_id;
	}
	public void setNext_id(String next_id) {
		this.next_id = next_id;
	}
	public String getPrev_id() {
		return prev_id;
	}
	public void setPrev_id(String prev_id) {
		this.prev_id = prev_id;
	}
	public Integer getAppear() {
		return appear;
	}
	public void setAppear(Integer appear) {
		this.appear = appear;
	}
	@Override
	public String toString() {
		return "StrLink [slnk_id=" + slnk_id + ", item_id=" + item_id + ", next_id=" + next_id + ", prev_id=" + prev_id
				+ ", appear=" + appear + "]";
	}
	
}
