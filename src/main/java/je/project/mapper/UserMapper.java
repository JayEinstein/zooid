package je.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import je.project.pojo.User;

public interface UserMapper {

	public List<User> getAllUser();
	
	public List<User> getUsers(@Param("user") User user);
 }
