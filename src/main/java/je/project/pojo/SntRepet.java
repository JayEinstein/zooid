package je.project.pojo;

public class SntRepet {
	
	private String sr_id;
	private String ss_id;
	private Integer appear;
	public String getSr_id() {
		return sr_id;
	}
	public void setSr_id(String sr_id) {
		this.sr_id = sr_id;
	}
	public String getSs_id() {
		return ss_id;
	}
	public void setSs_id(String ss_id) {
		this.ss_id = ss_id;
	}
	public Integer getAppear() {
		return appear;
	}
	public void setAppear(Integer appear) {
		this.appear = appear;
	}
	@Override
	public String toString() {
		return "StrRepet [sr_id=" + sr_id + ", ss_id=" + ss_id + ", appear=" + appear + "]";
	}
	
}
