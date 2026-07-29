package com.example.service;

import com.example.dto.request.OrderRequest;
import com.example.dto.response.OrderResponse;
import com.example.entity.Order;
import com.example.entity.Product;
import com.example.entity.User;
import com.example.exceptions.InsufficientQuantityException;
import com.example.exceptions.ProductNotFoundException;
import com.example.exceptions.UserNotFoundException;
import com.example.mapper.OrderMapper;
import com.example.repositories.OrderRepository;
import com.example.repositories.ProductRepository;
import com.example.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public OrderResponse placeOrder(String userEmail, OrderRequest request) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException(userEmail));

        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new ProductNotFoundException(request.productId()));

        if (product.getQuantityAvailable() < request.quantity()) {
            throw new InsufficientQuantityException(
                    product.getName(), request.quantity(), product.getQuantityAvailable());
        }

        product.reduceStock(request.quantity());

        BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(request.quantity()));

        Order order = new Order(request.quantity(), totalPrice, product);
        user.addOrder(order);

        Order savedOrder = orderRepository.save(order);
        log.info("Order placed: {} by user {}", savedOrder.getOrderNumber(), userEmail);

        return OrderMapper.toResponse(savedOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrderHistory(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException(userEmail));

        return orderRepository.findByUserIdOrderByOrderDateDesc(user.getId()).stream()
                .map(OrderMapper::toResponse)
                .toList();
    }
}
