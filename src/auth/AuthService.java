package auth;

import account.AccountEntity;

import auth.dtos.RegisterDTO;
import auth.dtos.LoginDTO;

import exceptions.InvalidPassword;

import account.AccountDAO;

import jakarta.servlet.ServletContext;

public class AuthService {
	private AccountDAO accountDAO = new AccountDAO();

	public void register(ServletContext c, RegisterDTO dto) throws RuntimeException {
		accountDAO.insertOne(c, dto);
	}

	/// Return role
	public int login(ServletContext c, LoginDTO dto) throws RuntimeException, InvalidPassword {
		AccountEntity account = accountDAO.findOne(c, dto);

		if (!account.getPwd().equals(dto.getPwd())) {
			throw new InvalidPassword("Password is wrong!");
		}
		;

		return account.getRole();
	}
}
