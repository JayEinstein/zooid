package je.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import je.project.pojo.CharRepet;

public interface SntRepetMapper {
	String name = "repet";
	
	public Integer insert(@Param(name) CharRepet repet);
	
	public void update(@Param(name) CharRepet repet);
	
	public List<CharRepet> select(@Param(name) CharRepet repet);
	
}
