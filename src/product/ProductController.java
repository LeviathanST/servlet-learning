package product;
import etc.Response;
import config.Database;
import utils.HttpUtil;
import product.dtos.SearchProductDTO;
import product.dtos.CreateProductDTO;
import product.dtos.DeleteProductDTO;
import product.dtos.UpdateProductDTO;

import java.io.IOException;
import java.io.PrintWriter; 
import java.io.BufferedReader;
import java.lang.RuntimeException;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/product/*")
public class ProductController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ProductService productService = new ProductService();
		String route = (req.getPathInfo() != null) ? req.getPathInfo().substring(1) : "";
		Gson gson = new Gson();
		try {
			switch (route) {
				case "list":
					// TODO: use SearchProductDTO
					HttpUtil<SearchProductDTO> util = new HttpUtil();
					SearchProductDTO dto = util.getBodyContentFromReq(req, SearchProductDTO.class);
					List<ProductEntity> products = productService.getAll(getServletContext(), dto);

					res.setContentType("application/json");
					Response<List<ProductEntity>> response = new Response(200,
							"Get products successfully!", products);
					try (PrintWriter out = res.getWriter()) {
						out.println(gson.toJson(response));
					}
					break;
				default:
					res.setContentType("text/html;charset=UTF-8");
					try (PrintWriter out = res.getWriter()) {
						out.println("NotFound");
					}
					break;
			}
		} catch (RuntimeException e) {
			Response<Object> response = new Response(500, e.getMessage(), null);
			res.setContentType("application/json");
			try (PrintWriter out = res.getWriter()) {
				out.println(gson.toJson(response));
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ProductService productService = new ProductService();
		String route = (req.getPathInfo() != null) ? req.getPathInfo().substring(1) : "";
		Gson gson = new Gson();
		try {
			switch (route) {
				case "":
					HttpUtil<CreateProductDTO> util = new HttpUtil();
					CreateProductDTO dto= util.getBodyContentFromReq(req, CreateProductDTO.class);
					productService.createOne(getServletContext(), dto);

					res.setContentType("application/json;charset=UTF-8");
					Response<Object> response = new Response(200, "Create a product successfully!!", null);
					try (PrintWriter out = res.getWriter()) {
						out.println(gson.toJson(response));
					}
					break;
				default:
					res.setContentType("text/html;charset=UTF-8");
					try (PrintWriter out = res.getWriter()) {
						out.println("NotFound");
					}
					break;
			}
		} catch (RuntimeException e) {
			Response<Object> response = new Response(500, e.getMessage(), null);
			res.setContentType("application/json");
			try (PrintWriter out = res.getWriter()) {
				out.println(gson.toJson(response));
			}
		}
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ProductService productService = new ProductService();
		String route = (req.getPathInfo() != null) ? req.getPathInfo().substring(1) : "";
		Gson gson = new Gson();
		try {
			switch (route) {
				case "":
					HttpUtil<DeleteProductDTO> util = new HttpUtil();
					DeleteProductDTO dto = util.getBodyContentFromReq(req, DeleteProductDTO.class);
					productService.deleteOne(getServletContext(), dto);

					res.setContentType("application/json;charset=UTF-8");
					Response<Object> response = new Response(200, "Delete a product successfully!!", null);
					try (PrintWriter out = res.getWriter()) {
						out.println(gson.toJson(response));
					}
					break;
				default:
					res.setContentType("text/html;charset=UTF-8");
					try (PrintWriter out = res.getWriter()) {
						out.println("NotFound");
					}
					break;
			}
		} catch (RuntimeException e) {
			Response<Object> response = new Response(500, e.getMessage(), null);
			res.setContentType("application/json");
			try (PrintWriter out = res.getWriter()) {
				out.println(gson.toJson(response));
			}
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ProductService productService = new ProductService();
		String route = (req.getPathInfo() != null) ? req.getPathInfo().substring(1) : "";
		Gson gson = new Gson();
		try {
			switch (route) {
				case "":
					HttpUtil<UpdateProductDTO> util = new HttpUtil();
					UpdateProductDTO dto = util.getBodyContentFromReq(req, UpdateProductDTO.class);
					productService.updateOne(getServletContext(), dto);

					res.setContentType("application/json;charset=UTF-8");
					Response<Object> response = new Response(200, "Delete a product successfully!!", null);
					try (PrintWriter out = res.getWriter()) {
						out.println(gson.toJson(response));
					}
					break;
				default:
					res.setContentType("text/html;charset=UTF-8");
					try (PrintWriter out = res.getWriter()) {
						out.println("NotFound");
					}
					break;
			}
		} catch (RuntimeException e) {
			Response<Object> response = new Response(500, e.getMessage(), null);
			res.setContentType("application/json");
			try (PrintWriter out = res.getWriter()) {
				out.println(gson.toJson(response));
			}
		}
	}
}
