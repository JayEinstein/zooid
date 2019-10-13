package je.cache.wave;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import je.jdbc.sql.MySpring;
import je.project.mapper.StrLibraryMapper;
import je.project.pojo.StrLibrary;

public class WaveCache {
	private static Map<String,Integer> gather = new HashMap<>();
	
	public static void gather(String str) {
		Integer num = gather.get(str);
		if(num == null) {
			gather.put(str, 1);
		}else {
			gather.put(str,++num);
		}
	}
	
	public static void main(String[] args) {
		ApplicationContext startSpring = MySpring.startSpring();
		StrLibraryMapper bean = startSpring.getBean(StrLibraryMapper.class);
		List<StrLibrary> list = bean.select(new StrLibrary());
		WaveCache wc = new WaveCache();
		Map<String, StrLibrary> changeToMap = wc.changeToMap(list);
		System.out.println(changeToMap);
	}
	
	public Map<String, StrLibrary> changeToMap(List<StrLibrary> list) {
		Map<String,StrLibrary> map = new HashMap<String, StrLibrary>();
		for (StrLibrary str: list) {
			map.put(str.getStr(),str);
		}
		return map;
	}
	
}

