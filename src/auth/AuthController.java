package auth;

import utils.HttpUtil;

import etc.Response;
import auth.dtos.RegisterDTO;
import auth.dtos.LoginDTO;
import exceptions.InvalidPassword;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/*")
public class AuthController extends HttpServlet {
	private AuthService authService = new AuthService();
	private HttpUtil util = new HttpUtil();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String route = (req.getPathInfo() != null) ? req.getPathInfo().substring(1) : "";
		Gson gson = new Gson();

		HttpSession session = req.getSession();
		try {
			switch (route) {
				case "register":
					RegisterDTO registerDto = util.getBodyContentFromReq(req, RegisterDTO.class);
					authService.register(getServletContext(), registerDto);

					res.setContentType("text/html;charset=UTF-8");
					try (PrintWriter out = res.getWriter()) {
						out.println("Register successfully!");
					}
					break;
				case "login":
					LoginDTO loginDto = util.getBodyContentFromReq(req, LoginDTO.class);
					int role = authService.login(getServletContext(), loginDto);

					session.setAttribute("uname", loginDto.getUsername());
					session.setAttribute("role", role);

					res.setContentType("text/html;charset=UTF-8");
					try (PrintWriter out = res.getWriter()) {
						out.println("Login successfully!");
					}
					break;
				case "logout":
					session.removeAttribute("uname");
					session.removeAttribute("role");
					break;
				default:
					res.setContentType("text/html;charset=UTF-8");
					try (PrintWriter out = res.getWriter()) {
						out.println("NotFound");
					}
					break;
			}
		} catch (RuntimeException | InvalidPassword e) {
			Response<Object> response = new Response(500, e.getMessage(), null);
			res.setContentType("application/json");
			try (PrintWriter out = res.getWriter()) {
				out.println(gson.toJson(response));
			}
		}
	}
}
