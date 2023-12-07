package com.techbuild.techbuild.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techbuild.techbuild.domain.Order;

public interface OrderRepository extends JpaRepository<Order, String> {

}
