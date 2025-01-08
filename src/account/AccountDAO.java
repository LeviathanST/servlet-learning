package account;

import config.Database;
import account.AccountEntity;
import jakarta.servlet.ServletContext;
import auth.dtos.RegisterDTO;
import auth.dtos.LoginDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

public class AccountDAO {
	public List<AccountEntity> getAll(ServletContext c) throws RuntimeException {
		String query = "SELECT * FROM account";
		List<AccountEntity> list = new ArrayList<>();

		try {
			try (Connection conn = Database.connection(c)) {
				PreparedStatement stmt = conn.prepareStatement(query);

				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					AccountEntity entity = new AccountEntity(
							rs.getInt("id"),
							rs.getString("username"),
							rs.getString("password"),
							rs.getInt("role"));

					list.add(entity);
				}
				return list;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Some troubles occur when get all account!");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Database class is not found!");
		}
	}

	public void insertOne(ServletContext c, RegisterDTO dto) throws RuntimeException {
		String query = """
					INSERT INTO account (username, password, role)
					VALUES (?, ?, ?)
				""";

		try {
			try (Connection conn = Database.connection(c)) {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, dto.getUsername());
				stmt.setString(2, dto.getPwd());
				stmt.setInt(3, 1); // Default is Customer

				if (stmt.executeUpdate() <= 0) {
					throw new RuntimeException("No rows effected!");
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Some troubles occur when insert account!");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Database class is not found!");
		}
	}

	public AccountEntity findOne(ServletContext c, LoginDTO dto) throws RuntimeException {
		String query = """
					SELECT * FROM account
					WHERE username = ?
				""";
		try {
			try (Connection conn = Database.connection(c)) {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, dto.getUsername());

				ResultSet rs = stmt.executeQuery();

				if (!rs.next()) {
					throw new RuntimeException("Username is not exists!");
				}

				AccountEntity entity = new AccountEntity();
				entity.setId(rs.getInt("id"));
				entity.setUsername(rs.getString("username"));
				entity.setPwd(rs.getString("password"));
				entity.setRole(rs.getInt("role"));

				return entity;
			}

		} catch (SQLException e) {
			throw new RuntimeException("Some troubles occurs when find a account");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Database class is not found!");

		}
	}
}
