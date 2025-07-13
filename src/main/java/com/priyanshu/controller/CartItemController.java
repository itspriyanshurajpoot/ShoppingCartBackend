package com.priyanshu.controller;

import com.priyanshu.dtos.cartItem.CartItemResponseDTO;
import com.priyanshu.entity.CartItem;
import com.priyanshu.response.ApiResponse;
import com.priyanshu.service.cartItem.ICartItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cart")
@AllArgsConstructor
@Tag(name = "Cart Controller")
public class CartItemController {

    private final ICartItemService cartService;

    @Operation(summary = "Add Product to cart")
    @PostMapping("/user/{userId}/product/{productId}/quantity/{quantity}")
    public ResponseEntity<CartItemResponseDTO> addProduct(@PathVariable Long userId, @PathVariable Long productId, @PathVariable int quantity){
        return ResponseEntity.ok(cartService.addProductToCart(userId, productId, quantity));
    }

    @Operation(summary = "Get Cart of a user")
    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<CartItemResponseDTO>> getCartOfUser(@PathVariable Long userId){
        return ResponseEntity.ok(cartService.getAllProductOfCartByUser(userId));
    }

    @Operation(summary = "Update quantity of a product")
    @PutMapping("/cart/{cartId}/update-quantity/{quantity}")
    public ResponseEntity<ApiResponse> updateQuantity(@PathVariable Long cartId, @PathVariable int quantity){
        ApiResponse response = ApiResponse.builder()
                .data(cartService.updateQuantity(cartId, quantity))
                .message("Quantity updation successful")
                .success(true)
                .build();

        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Remove single cart by id")
    @DeleteMapping("/remove-cart-item/{cartId}")
    public ResponseEntity<ApiResponse> removeCartItem(@PathVariable Long cartId){
        ApiResponse response = ApiResponse.builder()
                .message(cartService.deleteCartItemById(cartId))
                .success(true)
                .build();

        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Clear the cart of a user")
    @DeleteMapping("/clear-cart/{userId}")
    public ResponseEntity<ApiResponse> clearCart(@PathVariable Long userId){
        ApiResponse response = ApiResponse.builder()
                .message(cartService.clearCartOfUser(userId))
                .success(true)
                .build();

        return ResponseEntity.ok(response);
    }
}
