package com.nagarro.training.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.training.constants.Constants;
import com.nagarro.training.dao.UserDao;
import com.nagarro.training.models.User;

@Component
public class LoginService {

	@Autowired
	UserDao userDao;

	public String verifyUser(String username, String password, HttpServletRequest request,
			HttpServletResponse response) {
		User user = userDao.getUser(username);

		// Check if the user object is null
		if (user == null) {
			// Return that the user does not exists in the database
			return "{\"" + Constants.USERNAME_ERROR_FIELD + "\":\"" + Constants.USERNAME_DOES_NOT_EXISTS + "\"}";
		} else if (!user.getPassword().equals(password)) {
			// Check if the username matches to password
			// else return incorrect password
			return "{\"" + Constants.PASSWORD_ERROR_FIELD + "\":\"" + Constants.INCORRECT_PASSWORD + "\"}";
		}

		try {
			// if it is a valid user save user to session
			request.getSession().setAttribute(Constants.USER_SESSION_ATTRIBUTE, user);
		} catch (IllegalStateException ise) {
			System.out.println(Constants.UNKNOWN_ERROR);
		}catch(Exception e) {
			System.out.println(Constants.UNKNOWN_ERROR);
		}
		// send user to the products jsp page
		return "";
	}

}
