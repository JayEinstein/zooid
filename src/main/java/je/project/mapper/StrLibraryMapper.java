package je.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import je.project.pojo.StrLibrary;

public interface StrLibraryMapper {
	String name = "strlib";
	
	public Integer insert(@Param(name) StrLibrary strlib);
	
	public void update(@Param(name) StrLibrary strlib);
	
	public List<StrLibrary> select(@Param(name) StrLibrary strlib);
	
	public List<StrLibrary> selectStr(String str);
	
	public List<StrLibrary> selectSlId(String slid);
	
}
