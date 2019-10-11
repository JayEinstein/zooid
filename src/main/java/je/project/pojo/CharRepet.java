package je.project.pojo;

public class CharRepet {
	
	private String cr_id;
	private String slnk_id;
	private Integer appear;
	public String getCr_id() {
		return cr_id;
	}
	public void setCr_id(String cr_id) {
		this.cr_id = cr_id;
	}
	public String getSlnk_id() {
		return slnk_id;
	}
	public void setSlnk_id(String slnk_id) {
		this.slnk_id = slnk_id;
	}
	public Integer getAppear() {
		return appear;
	}
	public void setAppear(Integer appear) {
		this.appear = appear;
	}
	@Override
	public String toString() {
		return "CharRepet [cr_id=" + cr_id + ", slnk_id=" + slnk_id + ", appear=" + appear + "]";
	}
}
