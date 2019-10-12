package je.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import je.project.pojo.StrLink;

public interface StrLinkMapper {
	String name = "arg";
	
	public Integer insert(@Param(name) StrLink link);
	
	public void update(@Param(name) StrLink link);
	
	public List<StrLink> select(@Param(name) StrLink link);
	
}
