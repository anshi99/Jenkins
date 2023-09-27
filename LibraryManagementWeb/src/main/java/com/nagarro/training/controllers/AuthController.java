package com.nagarro.training.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.training.constants.Constants;
import com.nagarro.training.services.LoginService;

@Controller
public class AuthController {
	@Autowired
	LoginService loginService;

	@GetMapping("/login")
	public ModelAndView loginUserView() {
		ModelAndView mv = new ModelAndView("login");

		return mv;
	}

	@PostMapping("/login")
	@ResponseBody
	public String loginUser(@RequestParam String username, @RequestParam String password, HttpServletRequest request,
			HttpServletResponse response) {
		return loginService.verifyUser(username, password, request, response);
	}

	@GetMapping("/logout")
	public void logoutUser(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute(Constants.USER_SESSION_ATTRIBUTE);

		try {
			response.sendRedirect("./login");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
