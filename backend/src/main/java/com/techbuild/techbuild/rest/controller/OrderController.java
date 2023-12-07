package com.techbuild.techbuild.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techbuild.techbuild.domain.Order;
import com.techbuild.techbuild.domain.OrderDetail;
import com.techbuild.techbuild.domain.Product;
import com.techbuild.techbuild.domain.User;
import com.techbuild.techbuild.service.OrderService;
import com.techbuild.techbuild.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {
	private final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;

	@GetMapping
	public String order(Model model, HttpSession session) {
		logger.info("Entering order");
		if (session.getAttribute("user") != null) {
			logger.info("HttpSession: {}", session.getAttribute("user").toString());
			User user = (User) session.getAttribute("user");
			logger.info("Session User: {}", user.toString());
			model.addAttribute("session", user);
			if (user.getRole().equals("ADMIN")) {
				logger.info("Returning admin/index");
				return "admin/order";
			}
			return "user/order";
		} else {
			logger.info("Session User is NULL");
			return "all/order";
		}
	}

	@PostMapping("/order")
	public String getMethodName(Model model, @RequestParam String productId,
			@RequestParam int amount,
			HttpSession session) {
		Order order = new Order();
		OrderDetail orderDetail = new OrderDetail();

		Product product = productService.getProductById(productId);
		logger.info("Added product: {}", product.toString());
		logger.info("Amount: {}", amount);

		orderDetail.setQuantity(amount);
		orderDetail.setProductId(productId);
		orderDetail.setOrderId(order.getId());

		order.getProducts().add(orderDetail);
		order.setTotal(Double.valueOf(product.getPrice()) * amount);
		logger.info("Order: {}", order.toString());

		Order createdOrder = orderService.createOrder(order);

		model.addAttribute("session", session);
		model.addAttribute("cart", order.getProducts());
		model.addAttribute("orden", createdOrder);

		return "usuario/carrito";
	}

}
