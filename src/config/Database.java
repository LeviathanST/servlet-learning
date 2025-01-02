package config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLWarning;

import jakarta.servlet.ServletContext;

public class Database {
	public static Connection connection(ServletContext c) throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		String path = c.getRealPath("/WEB-INF/schema.db");
		Connection connection = DriverManager
				.getConnection("jdbc:sqlite:" + path);
		System.out.println("Connection Successful");
		return connection;
	}
}
