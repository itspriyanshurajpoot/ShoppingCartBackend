package com.priyanshu.mappers;


import com.priyanshu.dtos.cartItem.CartItemResponseDTO;
import com.priyanshu.entity.CartItem;

public class CartMapper {

    public static CartItemResponseDTO toDto(CartItem item){
        return CartItemResponseDTO.builder()
                .cartId(item.getId())
                .quantity(item.getQuantity())
                .price(item.getProduct().getPrice())
                .productId(item.getProduct().getProductId())
                .productName(item.getProduct().getName())
                .totalPrice(item.getProduct().getPrice() * item.getQuantity())
                .productImage(ProductImageMapper.toDto(item.getProduct().getProductImages()))
                .build();
    }


}
