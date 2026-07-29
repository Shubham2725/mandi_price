package com.example.mapper;

import com.example.dto.response.OrderResponse;
import com.example.entity.Order;

/**
 * Maps {@link Order} to {@link OrderResponse}.
 *
 * <p>Design note: pulls {@code productName} from the associated Product
 * entity. Callers must ensure the Order's Product association is
 * initialized (i.e. accessed within an active transaction/session) before
 * calling this mapper — see OrderServiceImpl, which runs within a
 * {@code @Transactional} service method.</p>
 */
public final class OrderMapper {

    private OrderMapper() {
        // utility class
    }

    public static OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getOrderNumber(),
                order.getProduct().getName(),
                order.getQuantity(),
                order.getTotalPrice(),
                order.getOrderDate()
        );
    }
}
