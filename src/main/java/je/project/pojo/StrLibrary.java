package je.project.pojo;

public class StrLibrary {

	private String sl_id;
	private String str;
	private Integer len;
	public String getSl_id() {
		return sl_id;
	}
	public void setSl_id(String sl_id) {
		this.sl_id = sl_id;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public Integer getLen() {
		return len;
	}
	public void setLen(Integer len) {
		this.len = len;
	}
	@Override
	public String toString() {
		return "Str_library [sl_id=" + sl_id + ", str=" + str + ", len=" + len + "]";
	}
}
