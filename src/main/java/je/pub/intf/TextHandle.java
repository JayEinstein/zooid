package je.pub.intf;

import java.util.List;

public interface TextHandle {
	
	public List<List<Character>> handle(String text);
	
	/**
	 * 文本处理的长度，小于0为默认长度
	 * @return
	 */
	public int handleSize();
	
}
