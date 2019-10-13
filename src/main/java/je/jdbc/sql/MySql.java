package je.jdbc.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import je.project.mapper.StrLibraryMapper;
import je.project.pojo.StrLibrary;

public class MySql {
	static Connection conn = null;
	
	public static void start() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zooid","root","root");
	}
	
	public static ApplicationContext startSpring() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		return context;
	}
	
	public static void close() throws SQLException {
		conn.close();
	}
	
}
