package com.priyanshu.service.cartItem;

import com.priyanshu.dtos.cartItem.CartItemResponseDTO;
import com.priyanshu.entity.CartItem;
import com.priyanshu.entity.Product;
import com.priyanshu.entity.User;
import com.priyanshu.mappers.CartMapper;
import com.priyanshu.repository.CartItemRepository;
import com.priyanshu.repository.ProductRepository;
import com.priyanshu.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService implements ICartItemService {


    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    public CartItemService(ProductRepository productRepository, CartItemRepository cartItemRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CartItemResponseDTO addProductToCart(Long userId, Long productId, int quantity) {
        // Check If product exists
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with the product id : " + productId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with the user id : " + userId));

        // Check if item already exists in user's cart
        CartItem item = cartItemRepository.findByUserAndProduct(user, product)
                .orElseGet(() -> new CartItem());


        // if yes, increase the quantity
        // if not, create new cart

        if (item.getId() == null) {
            item.setProduct(product);
            item.setQuantity(quantity);
            item.setUser(user);
        } else {
            item.setQuantity(item.getQuantity() + quantity);
        }

        // save to the database
        CartItem savedCart = cartItemRepository.save(item);

        return CartMapper.toDto(savedCart);
    }

    @Override
    public List<CartItemResponseDTO> getAllProductOfCartByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with user id : " + userId));

        List<CartItem> carts = cartItemRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart do not exists"));


        return carts.stream()
                .map(CartMapper::toDto)
                .toList();
    }

    @Override
    public CartItemResponseDTO updateQuantity(Long cartId, int quantity) {
        CartItem item = cartItemRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart item not found with cart id : " + cartId));


        item.setQuantity(quantity);
        CartItem savedItem = cartItemRepository.save(item);
        return CartMapper.toDto(savedItem);
    }

    @Override
    public String deleteCartItemById(Long cartId) {
        cartItemRepository.findById(cartId)
                .ifPresentOrElse(cartItemRepository::delete,
                        () -> {
                            throw new RuntimeException("Cart not found");
                        });

        return "Cart item deleted successfully";
    }

    @Override
    public String clearCartOfUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with user id : " + userId));

        List<CartItem> cartItems = cartItemRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("No Cart items exists"));

        cartItemRepository.deleteAll(cartItems);

        return "Cart Cleared";
    }




}
