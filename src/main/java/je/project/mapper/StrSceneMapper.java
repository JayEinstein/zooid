package je.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import je.project.pojo.StrScene;

public interface StrSceneMapper {
	String name = "strsce";
	
	public List<StrScene> select(@Param(name) StrScene strsce);
	
	public Integer insert(@Param(name) StrScene strsce);
	
	public void update(@Param(name) StrScene strsce);
}
