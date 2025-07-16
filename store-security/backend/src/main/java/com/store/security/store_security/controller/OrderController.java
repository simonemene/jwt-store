package com.store.security.store_security.controller;

import com.store.security.store_security.dto.AllOrderDto;
import com.store.security.store_security.dto.ArticlesOrderDto;
import com.store.security.store_security.exceptions.OrderException;
import com.store.security.store_security.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

	private final IOrderService orderService;

	@PreAuthorize("(#articlesOrderDto.username == authentication.name && hasRole('ROLE_USER')) || hasRole('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<ArticlesOrderDto> createOrder(@RequestBody ArticlesOrderDto articlesOrderDto)
			throws OrderException {
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.orderArticles(articlesOrderDto));
	}

	@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_TRACK')")
	@GetMapping("/{id}")
	public ResponseEntity<AllOrderDto> getAllOrders(@PathVariable("id") Long id)
			throws OrderException {
		return ResponseEntity.status(HttpStatus.OK).body(orderService.allOrderByUser(id));
	}




}