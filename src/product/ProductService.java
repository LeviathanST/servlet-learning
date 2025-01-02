package product;

import product.dtos.CreateProductDTO;
import product.dtos.SearchProductDTO;
import product.dtos.DeleteProductDTO;
import product.dtos.UpdateProductDTO;

import jakarta.servlet.ServletContext;

import java.util.List;
import java.util.ArrayList;
import java.lang.RuntimeException;

public class ProductService {
	public List<ProductEntity> getAll(ServletContext c, SearchProductDTO dto)
			throws RuntimeException {
		ProductDAO productDAO = new ProductDAO();
		List<ProductEntity> products = productDAO.search(c, dto);
		return products;
	}

	public void createOne (ServletContext c, CreateProductDTO dto) throws RuntimeException {
		ProductDAO productDAO = new ProductDAO();
		boolean status = productDAO.insertOne(c, dto);

		if (!status) {
			throw new RuntimeException("No row effected");
		};
	}

	public void deleteOne (ServletContext c, DeleteProductDTO dto) throws RuntimeException {
		ProductDAO productDAO = new ProductDAO();
		boolean status = productDAO.deleteOneById(c, dto);

		if (!status) {
			throw new RuntimeException("No row effected");
		};
	}

	public void updateOne (ServletContext c, UpdateProductDTO dto) throws RuntimeException {
		ProductDAO productDAO = new ProductDAO();
		boolean status = productDAO.updateOneById(c, dto);

		if (!status) {
			throw new RuntimeException("No row effected");
		};
	}
}
