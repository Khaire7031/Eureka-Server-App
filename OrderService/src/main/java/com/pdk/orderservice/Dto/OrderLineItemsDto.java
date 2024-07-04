package com.pdk.orderservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderLineItemsDto {
    private Long Id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
