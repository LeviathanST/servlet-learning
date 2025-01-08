package product;

import config.Database;
import category.CategoryEntity;
import product.dtos.CreateProductDTO;
import product.dtos.SearchProductDTO;
import product.dtos.DeleteProductDTO;
import product.dtos.UpdateProductDTO;

import java.lang.RuntimeException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletContext;

public class ProductDAO {
	public String constructSearchQuery(String productName, Integer categoryId, Float price, String filterBy) {
		String query = """
					SELECT p.id, p.name, p.price, p.product_year, p.image, c.name AS category_name, c.id as category_id
					FROM product p
					JOIN category c ON p.category_id = c.id
					WHERE 1=1
				""";
		if (productName != null && !productName.trim().isEmpty()) {
			query += "AND p.name = ?";
		}
		if (categoryId != null) {
			query += "AND p.category_id= ?";
		}
		if (price != null && !filterBy.trim().isEmpty() && filterBy != null) {
			if (filterBy.equals("Under")) {
				query += "AND p.price <= ?";
			} else if (filterBy.equals("Above")) {
				query += "AND p.price >= ?";
			}
		}
		return query;
	}

	public String constructUpdateQuery(UpdateProductDTO dto) {
		StringBuilder query = new StringBuilder("UPDATE product SET ");
		boolean isFirst = true;

		// Dynamically add fields to the SET clause
		if (dto.getName() != null && !dto.getName().trim().isEmpty()) {
			query.append(isFirst ? "" : ", ").append("name = ?");
			isFirst = false;
		}
		if (dto.getPrice() != null) {
			query.append(isFirst ? "" : ", ").append("price = ?");
			isFirst = false;
		}
		if (dto.getProductYear() != null) {
			query.append(isFirst ? "" : ", ").append("product_year = ?");
			isFirst = false;
		}
		if (dto.getImage() != null && !dto.getImage().trim().isEmpty()) {
			query.append(isFirst ? "" : ", ").append("image = ?");
			isFirst = false;
		}
		if (dto.getCategoryId() != null) {
			query.append(isFirst ? "" : ", ").append("category_id = ?");
		}

		// Ensure the WHERE clause is added
		query.append(" WHERE id = ?");

		return query.toString();
	}

	public List<ProductEntity> search(ServletContext c, SearchProductDTO dto)
			throws RuntimeException {
		List<ProductEntity> list = new ArrayList();
		try {
			String query = constructSearchQuery(
					dto.getProductName(),
					dto.getCategoryId(),
					dto.getPrice(),
					dto.getFilterBy());
			try (Connection conn = Database.connection(c)) {
				PreparedStatement stmt = conn.prepareStatement(query);
				int paramIndex = 1;
				if (dto.getProductName() != null && !dto.getProductName().trim().isEmpty()) {
					stmt.setString(paramIndex++, dto.getProductName());
				}
				;
				if (dto.getCategoryId() != null) {
					stmt.setInt(paramIndex++, dto.getCategoryId());
				}
				;
				if (dto.getPrice() != null && !dto.getFilterBy().trim().isEmpty()
						&& dto.getFilterBy() != null) {
					stmt.setFloat(paramIndex++, dto.getPrice());
				}
				;
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					ProductEntity p = new ProductEntity();
					p.setId(rs.getInt("id"));
					p.setName(rs.getString("name"));
					p.setPrice(rs.getFloat("price"));
					p.setProductYear(rs.getInt("product_year"));
					p.setImage(rs.getString("image"));

					CategoryEntity category = new CategoryEntity(
							rs.getInt("category_id"), rs.getString("category_name"));
					p.setCategory(category);
					list.add(p);
				}
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Some troubles occur when searching!");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Database class is not found!");
		}
	}

	public boolean insertOne(ServletContext c, CreateProductDTO dto) throws RuntimeException {
		String query = """
					INSERT INTO product (name, price, product_year, image, category_id)
					VALUES (?, ?, ?, ?, ?)
				""";
		boolean status = false;
		try {
			try (Connection conn = Database.connection(c)) {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setString(1, dto.getName());
				stmt.setFloat(2, dto.getPrice());
				stmt.setInt(3, dto.getProductYear());
				stmt.setString(4, dto.getImage());
				stmt.setInt(5, dto.getCategoryId());
				int row = stmt.executeUpdate();
				if (row > 0) {
					status = true;
				}
			}
			return status;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Some troubles occurs when create a product!");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Database class is not found!");
		}
	}

	// TODO:
	public boolean deleteOneById(ServletContext c, DeleteProductDTO dto) {
		String query = "DELETE FROM product WHERE id = ?";
		boolean status = false;
		try {
			try (Connection conn = Database.connection(c)) {
				PreparedStatement stmt = conn.prepareStatement(query);
				stmt.setInt(1, dto.getId());
				int row = stmt.executeUpdate();
				if (row > 0) {
					status = true;
				}
			}
			return status;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Some troubles occurs when delete a product!");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Database class is not found!");
		}
	}

	// TODO:
	public boolean updateOneById(ServletContext c, UpdateProductDTO dto) {
		String query = constructUpdateQuery(dto);
		boolean status = false;
		try {
			try (Connection conn = Database.connection(c)) {
				PreparedStatement stmt = conn.prepareStatement(query);
				int i = 1;
				if (dto.getName() != null && !dto.getName().trim().isEmpty()) {
					stmt.setString(i++, dto.getName());
				}
				if (dto.getPrice() != null) {
					stmt.setFloat(i++, dto.getPrice());
				}
				if (dto.getProductYear() != null) {
					stmt.setInt(i++, dto.getProductYear());
				}
				if (dto.getImage() != null && !dto.getImage().trim().isEmpty()) {
					stmt.setString(i++, dto.getImage());
				}
				if (dto.getCategoryId() != null) {
					stmt.setInt(i++, dto.getCategoryId());
				}
				stmt.setInt(i++, dto.getId());
				int row = stmt.executeUpdate();
				if (row > 0) {
					status = true;
				}
			}
			return status;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Some troubles occurs when update a product!");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Database class is not found!");
		}
	}
}
