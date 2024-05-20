package com.rankuw.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "t_order_line_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineItems {

    @Id
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

//    @OneToOne
//    @JoinColumn(name = "order_id")
//    private Order order;
}
