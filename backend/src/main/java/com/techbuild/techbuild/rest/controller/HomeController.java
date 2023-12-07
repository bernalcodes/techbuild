package com.techbuild.techbuild.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techbuild.techbuild.domain.Product;
import com.techbuild.techbuild.domain.User;
import com.techbuild.techbuild.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private ProductService productService;

	@GetMapping("")
	public String home(Model model,
			@RequestParam(value = "login_success", required = false) boolean loginSuccess,
			@RequestParam(value = "logout_success", required = false) boolean logoutSuccess,
			HttpSession session) {
		logger.info("Entering home");

		List<Product> productList = productService.getProducts();
		productList.forEach(product -> logger.info(product.toString()));
		model.addAttribute("productList", productList);

		if (logoutSuccess) {
			model.addAttribute("logout_success", "Ha cerrado sesión correctamente");
			logger.info("Returning all/index with logout_success");
			return "all/index";
		}

		if (loginSuccess) {
			model.addAttribute("login_success", "Bienvenido: ");
		}

		if (session.getAttribute("user") != null) {
			logger.info("HttpSession: {}", session.getAttribute("user").toString());
			User user = (User) session.getAttribute("user");
			logger.info("Session User: {}", user.toString());
			model.addAttribute("session", user);
			if (user.getRole().equals("ADMIN")) {
				logger.info("Returning admin/index");
				return "admin/index";
			}
			return "user/index";
		} else {
			logger.info("Session User is NULL");
			return "all/index";
		}
	}

	@GetMapping("/login")
	public String login(Model model,
			@RequestParam(value = "signup_success", required = false) boolean signupSuccess,
			@RequestParam(value = "login_error", required = false) boolean loginError) {
		if (signupSuccess) {
			model.addAttribute("signup_success", "Uusario creado con éxito");
		}
		if (loginError) {
			model.addAttribute("login_error", "Email o contraseña incorrectos");
		}
		model.addAttribute("user", new User());
		return "all/login";
	}

	@GetMapping("/signup")
	public String signup(Model model,
			@RequestParam(value = "signup_error", required = false) boolean signupError) {
		if (signupError) {
			model.addAttribute("error", "User already exists");
		}
		model.addAttribute("user", new User());
		return "all/signup";
	}
}
