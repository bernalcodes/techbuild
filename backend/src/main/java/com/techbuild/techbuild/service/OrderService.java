package com.techbuild.techbuild.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techbuild.techbuild.dao.OrderRepository;
import com.techbuild.techbuild.domain.Order;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	// CREATE
	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}

	// READ
	public List<Order> getOrders() {
		return orderRepository.findAll();
	}

	public Order getOrderById(String id) {
		return orderRepository.getReferenceById(id);
	}

	// UPDATE
	public Order updateOrder(Order order) {
		return orderRepository.saveAndFlush(order);
	}

	// DELETE
	public boolean deleteOrder(Order order) {
		if (orderRepository.existsById(order.getId())) {
			orderRepository.delete(order);
			return true;
		}
		return false;
	}

	public boolean deleteOrderById(String orderId) {
		if (orderRepository.existsById(orderId)) {
			orderRepository.deleteById(orderId);
			return true;
		}
		return false;
	}
}
