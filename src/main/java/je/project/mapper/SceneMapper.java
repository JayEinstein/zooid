package je.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import je.project.pojo.Scene;

public interface SceneMapper {
	String name = "sce";
	
	public Integer insert(@Param(name) Scene sce);
	
	public void update(@Param(name) Scene sce);
	
	public List<Scene> select(@Param(name) Scene sce);
	
}
