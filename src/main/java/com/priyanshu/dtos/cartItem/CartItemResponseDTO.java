package com.priyanshu.dtos.cartItem;


import com.priyanshu.dtos.ProductImageDTO.ProductImageResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class CartItemResponseDTO {

    private Long cartId;
    private Long productId;
    private String productName;
    private double price;
    private int quantity;
    private double totalPrice;
    private List<ProductImageResponseDTO> productImage;
}
