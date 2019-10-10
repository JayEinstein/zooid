package je.jdbc.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sql {
	static Statement stmt;
	
	static {
		try {
			MySql.start();
			stmt = MySql.conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void insert(String sql) throws SQLException {
		stmt.executeUpdate(sql);
	}
	
	public static void insert(String tableName, String into, String values) throws SQLException {
		String sql = "INSERT INTO " + tableName + "(" + into + ") VALUES ("+ values +")" ;
		executeUpdate(sql);
	}
	
	public void select() {
		String sql = "SELECT * FROM XX WHERE ";
	}
	
	public static void executeUpdate(String sql) throws SQLException {
		stmt.executeUpdate(sql);
	}
	
	public static void executeSelect(String sql) throws SQLException {
		ResultSet resultSet = stmt.executeQuery(sql);
	}
	
	public static void close() throws SQLException {
		stmt.close();
		MySql.close();
	}
}
