package account;

import etc.Response;
import account.AccountDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/account/*")
public class AccountController extends HttpServlet {
	// NOTE: REFACTOR, PLS!
	private AccountDAO accountDAO = new AccountDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String route = (req.getPathInfo() != null) ? req.getPathInfo().substring(1) : "";
		Gson gson = new Gson();
		try {
			switch (route) {
				case "list":
					List<AccountEntity> entities = accountDAO.getAll(getServletContext());

					res.setContentType("application/json");
					Response<List<AccountEntity>> response = new Response(200,
							"Get products successfully!", entities);
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
