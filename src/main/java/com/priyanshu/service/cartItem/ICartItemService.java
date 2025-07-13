package com.priyanshu.service.cartItem;

import com.priyanshu.dtos.cartItem.CartItemResponseDTO;

import java.util.List;

public interface ICartItemService {

    // add product to cart
    CartItemResponseDTO addProductToCart(Long userId, Long productId, int quantity);


    // Get All Cart items of a user
    List<CartItemResponseDTO> getAllProductOfCartByUser(Long userId);


    // Update quantity
    CartItemResponseDTO updateQuantity(Long cartId, int quantity);


    // Delete Cart Item by id
    String deleteCartItemById(Long cartId);

    String clearCartOfUser(Long userId);
}
