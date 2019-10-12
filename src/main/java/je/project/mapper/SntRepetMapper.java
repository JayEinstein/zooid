package je.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import je.project.pojo.SntRepet;

public interface SntRepetMapper {
	String name = "arg";
	
	public Integer insert(@Param(name) SntRepet repet);
	
	public void update(@Param(name) SntRepet repet);
	
	public List<SntRepet> select(@Param(name) SntRepet repet);
	
}
